package org.example.demoeventsourcingcqrs.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.demoeventsourcingcqrs.enums.AccountStatus;

@Getter
@AllArgsConstructor
public class AccountCreditEvent {
    private String accountId;
    private double amount;
    private String currency;
}

