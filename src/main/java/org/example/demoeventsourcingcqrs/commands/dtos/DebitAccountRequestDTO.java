package org.example.demoeventsourcingcqrs.commands.dtos;

public record DebitAccountRequestDTO(String accountId, double amount, String currency) {
}
