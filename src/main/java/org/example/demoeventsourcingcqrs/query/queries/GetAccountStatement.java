package org.example.demoeventsourcingcqrs.query.queries;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetAccountStatement {
    private String accountId;
}
