package com.dsi.ebankback.services;

import com.dsi.ebankback.dtos.BankAccountDto;
import com.dsi.ebankback.dtos.CurrentBankAccountDto;
import com.dsi.ebankback.dtos.SavingBankAccountDto;
import com.dsi.ebankback.exceptions.BankAccountNotFoundException;
import com.dsi.ebankback.exceptions.CustomerNotFoundException;
import com.dsi.ebankback.exceptions.EmptyListAccountException;
import com.dsi.ebankback.exceptions.InsufficientBalanceException;

import java.util.List;

public interface BankAccountService {

    CurrentBankAccountDto saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId) throws CustomerNotFoundException;

    SavingBankAccountDto saveSavingBankAccount(double initialBalance, double interestRate, Long customerId) throws CustomerNotFoundException;

    BankAccountDto getBankAccount(String accountId) throws BankAccountNotFoundException;

    List<BankAccountDto> getAllAccounts() throws EmptyListAccountException;

    // retrait
    void debit(String accountId, double amountOperation, String description) throws BankAccountNotFoundException, InsufficientBalanceException;

    void credit(String accountId, double amountOperation, String description) throws BankAccountNotFoundException, InsufficientBalanceException;

    void transfert(String accountIdSource, String accountDestination, double amount) throws BankAccountNotFoundException, InsufficientBalanceException;


}
