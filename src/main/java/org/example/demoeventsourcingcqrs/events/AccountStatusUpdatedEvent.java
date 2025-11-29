package org.example.demoeventsourcingcqrs.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.demoeventsourcingcqrs.enums.AccountStatus;

@Getter
@AllArgsConstructor
public class AccountStatusUpdatedEvent {
    private String accountId;
    private AccountStatus status;
}
