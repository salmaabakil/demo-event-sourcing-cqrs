package org.example.demoeventsourcingcqrs.query.handlers;

import io.axoniq.axonserver.grpc.event.Event;
import jdk.dynalink.Operation;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.EventMessage;
import org.axonframework.queryhandling.QueryUpdateEmitter;
import org.example.demoeventsourcingcqrs.enums.OperationType;
import org.example.demoeventsourcingcqrs.events.*;
import org.example.demoeventsourcingcqrs.query.entities.Account;
import org.example.demoeventsourcingcqrs.query.entities.AccountOperation;
import org.example.demoeventsourcingcqrs.query.repository.AccountRepository;
import org.example.demoeventsourcingcqrs.query.repository.OperationRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AccountEventHandler {
    private AccountRepository accountRepository;
    private OperationRepository operationRepository;
    private QueryUpdateEmitter queryUpdateEmitter;

    public AccountEventHandler(AccountRepository accountRepository, OperationRepository operationRepository, QueryUpdateEmitter queryUpdateEmitter) {
        this.accountRepository = accountRepository;
        this.operationRepository = operationRepository;
        this.queryUpdateEmitter = queryUpdateEmitter;
    }

    @EventHandler
    public void on(AccountCreatedEvent event, EventMessage eventMessage){
        log.info("################# Query Side AccountCreatedEvent Received ################");
        Account account = Account.builder()
                .id(event.getAccountId())
                .balance(event.getInitialBalance())
                .status(event.getStatus())
                .currency(event.getCurrency())
                .createdAt(eventMessage.getTimestamp())
                .build();
        accountRepository.save(account);
    }

    @EventHandler
    public void on(AccountActivatedEvent event){
        log.info("################# Query Side AccountActivatedEvent Received ################");
        Account account = accountRepository.findById(event.getAccountId()).get();
        account.setStatus(event.getStatus());
        accountRepository.save(account);
    }

    @EventHandler
    public void on(AccountStatusUpdatedEvent event){
        log.info("################# Query Side AccountStatusUpdatedEvent Received ################");
        Account account = accountRepository.findById(event.getAccountId()).get();
        account.setStatus(event.getStatus());
        accountRepository.save(account);
    }
    @EventHandler
    public void on(AccountDebitEvent event, EventMessage eventMessage){
        log.info("################# Query Side AccountDebitEvent Received ################");
        Account account = accountRepository.findById(event.getAccountId()).get();
        AccountOperation accountOperation = AccountOperation.builder()
                .amount(event.getAmount())
                .date(eventMessage.getTimestamp())
                .type(OperationType.DEBIT)
                .currency(event.getCurrency())
                .account(account)
                .build();
        operationRepository.save(accountOperation);
        account.setBalance(account.getBalance()-accountOperation.getAmount());
        accountRepository.save(account);

        queryUpdateEmitter.emit(e->true, accountOperation);
    }

    @EventHandler
    public void on(AccountCreditEvent event, EventMessage eventMessage){
        log.info("################# Query Side AccountCreditEvent Received ################");
        Account account = accountRepository.findById(event.getAccountId()).get();
        AccountOperation accountOperation = AccountOperation.builder()
                .amount(event.getAmount())
                .date(eventMessage.getTimestamp())
                .type(OperationType.DEBIT)
                .currency(event.getCurrency())
                .account(account)
                .build();
        operationRepository.save(accountOperation);
        account.setBalance(account.getBalance()+accountOperation.getAmount());
        accountRepository.save(account);

        queryUpdateEmitter.emit(e->true, accountOperation);
    }


}
