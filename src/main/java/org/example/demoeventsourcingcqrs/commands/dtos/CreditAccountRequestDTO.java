package org.example.demoeventsourcingcqrs.commands.dtos;

public record CreditAccountRequestDTO(String accountId, double amount, String currency) {
}
