package org.example.demoeventsourcingcqrs.query.dtos;

import org.example.demoeventsourcingcqrs.query.entities.Account;
import org.example.demoeventsourcingcqrs.query.entities.AccountOperation;

import java.util.List;

public record AccountStatementResponseDTO(
        Account account,
        List<AccountOperation> operations
) {}
