package org.example.demoeventsourcingcqrs.query.controllers;

import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.queryhandling.SubscriptionQueryResult;
import org.example.demoeventsourcingcqrs.query.dtos.AccountStatementResponseDTO;
import org.example.demoeventsourcingcqrs.query.entities.Account;
import org.example.demoeventsourcingcqrs.query.entities.AccountOperation;
import org.example.demoeventsourcingcqrs.query.queries.GetAccountStatement;
import org.example.demoeventsourcingcqrs.query.queries.GetAllAccountsQuery;
import org.example.demoeventsourcingcqrs.query.queries.WatchEventQuery;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/query/accounts")
@CrossOrigin("*")
public class AccountQueryController {
    private QueryGateway queryGateway;

    public AccountQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping("/all")
    public CompletableFuture<List<Account>> getAllAccounts(){
        CompletableFuture<List<Account>> response = queryGateway.query(
                new GetAllAccountsQuery(),
                ResponseTypes.multipleInstancesOf(Account.class
                ));
        return response;
    }

    @GetMapping("/accountStatement/{accountId}")
    public CompletableFuture<AccountStatementResponseDTO> getAccountStatement(@PathVariable String accountId){
        return queryGateway.query(new GetAccountStatement(accountId),
                ResponseTypes.instanceOf(AccountStatementResponseDTO.class));

    }

    @GetMapping(value = "/watch/{accountId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<AccountOperation> watch(@PathVariable String accountId){
        SubscriptionQueryResult<AccountOperation, AccountOperation> result = queryGateway.subscriptionQuery(new WatchEventQuery(accountId),
                ResponseTypes.instanceOf(AccountOperation.class),
                ResponseTypes.instanceOf(AccountOperation.class)
        );
        return result.initialResult().concatWith(result.updates());
    }

}
