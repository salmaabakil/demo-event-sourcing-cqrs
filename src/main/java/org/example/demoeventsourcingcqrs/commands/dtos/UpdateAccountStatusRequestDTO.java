package org.example.demoeventsourcingcqrs.commands.dtos;

import org.example.demoeventsourcingcqrs.enums.AccountStatus;

public record UpdateAccountStatusRequestDTO(String accountId, AccountStatus status) {
}
