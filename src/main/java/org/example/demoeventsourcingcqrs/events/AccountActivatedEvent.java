package org.example.demoeventsourcingcqrs.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.demoeventsourcingcqrs.enums.AccountStatus;

@Getter @AllArgsConstructor
public class AccountActivatedEvent {
    private String accountId;
    private AccountStatus status;
}
