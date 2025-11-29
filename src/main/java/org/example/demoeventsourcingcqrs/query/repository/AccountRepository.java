package org.example.demoeventsourcingcqrs.query.repository;

import org.example.demoeventsourcingcqrs.query.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {
}
