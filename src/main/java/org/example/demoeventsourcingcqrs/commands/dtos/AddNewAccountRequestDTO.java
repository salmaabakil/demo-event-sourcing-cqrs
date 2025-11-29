package org.example.demoeventsourcingcqrs.commands.dtos;

public record AddNewAccountRequestDTO(double initialBalance, String currency) {
}
