package org.example.demoeventsourcingcqrs.query.handlers;

import jdk.dynalink.Operation;
import org.axonframework.queryhandling.QueryHandler;
import org.example.demoeventsourcingcqrs.query.dtos.AccountStatementResponseDTO;
import org.example.demoeventsourcingcqrs.query.entities.Account;
import org.example.demoeventsourcingcqrs.query.entities.AccountOperation;
import org.example.demoeventsourcingcqrs.query.queries.GetAccountStatement;
import org.example.demoeventsourcingcqrs.query.queries.GetAllAccountsQuery;
import org.example.demoeventsourcingcqrs.query.queries.WatchEventQuery;
import org.example.demoeventsourcingcqrs.query.repository.AccountRepository;
import org.example.demoeventsourcingcqrs.query.repository.OperationRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountQueryHandler {
    private AccountRepository accountRepository;
    private OperationRepository operationRepository;

    public AccountQueryHandler(AccountRepository accountRepository, OperationRepository operationRepository) {
        this.accountRepository = accountRepository;
        this.operationRepository = operationRepository;
    }

    @QueryHandler
    public List<Account> on(GetAllAccountsQuery query) {
        return accountRepository.findAll();
    }

    @QueryHandler
    public AccountStatementResponseDTO on(GetAccountStatement query) {
        Account account = accountRepository.findById(query.getAccountId()).get();
        List<AccountOperation> operations = operationRepository.findByAccountId(query.getAccountId());
        return new AccountStatementResponseDTO(account, operations);
    }

    @QueryHandler
    public AccountOperation on(WatchEventQuery query) {
        return AccountOperation.builder().build();
    }
}
