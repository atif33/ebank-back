package com.dsi.ebankback.repositories;

import com.dsi.ebankback.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount, String> {
}
