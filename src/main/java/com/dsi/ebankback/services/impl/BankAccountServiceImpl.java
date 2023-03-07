package com.dsi.ebankback.services.impl;

import com.dsi.ebankback.dtos.AccountHistoryDto;
import com.dsi.ebankback.dtos.BankAccountDto;
import com.dsi.ebankback.dtos.CurrentBankAccountDto;
import com.dsi.ebankback.dtos.SavingBankAccountDto;
import com.dsi.ebankback.entities.*;
import com.dsi.ebankback.enums.TypeOperation;
import com.dsi.ebankback.exceptions.BankAccountNotFoundException;
import com.dsi.ebankback.exceptions.CustomerNotFoundException;
import com.dsi.ebankback.exceptions.EmptyListAccountException;
import com.dsi.ebankback.exceptions.InsufficientBalanceException;
import com.dsi.ebankback.mappers.BankAccountMapperImpl;
import com.dsi.ebankback.repositories.AccountOperationRepository;
import com.dsi.ebankback.repositories.BankAccountRepository;
import com.dsi.ebankback.repositories.CustomerRepository;
import com.dsi.ebankback.services.BankAccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class BankAccountServiceImpl implements BankAccountService {
    private CustomerRepository customerRepository;
    private BankAccountRepository bankAccountRepository;
    private AccountOperationRepository accountOperationRepository;
    private BankAccountMapperImpl dtoMapper;
    // remplace par lambok
    //Logger log = LoggerFactory.getLogger(this.getClass().getName());


    @Override
    public CurrentBankAccountDto saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null)
            throw new CustomerNotFoundException("Customer not found");
        CurrentAccount currentAccount = new CurrentAccount();
        currentAccount.setCreateDate(new Date());
        currentAccount.setOverDraft(overDraft);
        currentAccount.setRib(UUID.randomUUID().toString());
        currentAccount.setBalance(initialBalance);
        currentAccount.setCustomer(customer);

        CurrentAccount current = bankAccountRepository.save(currentAccount);
        return dtoMapper.fromCurrentBankAccount(current);
    }

    @Override
    public SavingBankAccountDto saveSavingBankAccount(double initialBalance, double interestRate, Long customerId) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null)
            throw new CustomerNotFoundException("Customer Not Found");
        SavingAccount savingAccount = new SavingAccount();
        savingAccount.setCreateDate(new Date());
        savingAccount.setInterestRate(interestRate);
        savingAccount.setRib(UUID.randomUUID().toString());
        savingAccount.setBalance(initialBalance);
        savingAccount.setCustomer(customer);

        return dtoMapper.fromSavingBankAccount(bankAccountRepository.save(savingAccount));
    }


    @Override
    public BankAccountDto getBankAccount(String accountId) throws BankAccountNotFoundException {

        BankAccount bankAccount = geAccount(accountId);
        /*return dtoMapper.fromBankAccount(bankAccount);*/

        if (bankAccount instanceof SavingAccount) {
            SavingAccount account = (SavingAccount) bankAccount;
            return dtoMapper.fromSavingBankAccount(account);
        } else {
            CurrentAccount account = (CurrentAccount) bankAccount;
            return dtoMapper.fromCurrentBankAccount(account);

        }
    }


    @Override
    public List<BankAccountDto> getAllAccounts() throws EmptyListAccountException {
        // findAny return an optional
/*        return bankAccountRepository.findAll().stream()
                .findAny()
                .map(e -> bankAccountRepository.findAll())
                .orElseThrow(() -> new EmptyListAccountException("empty list Account"));*/

        List<BankAccount> bankAccounts = bankAccountRepository.findAll();

         return bankAccounts.stream().map(acc -> {
             if (acc instanceof SavingAccount) {
                 SavingAccount savingAccount = (SavingAccount) acc;
                 return dtoMapper.fromSavingBankAccount(savingAccount);

             } else {
                 CurrentAccount currentAccount = (CurrentAccount) acc;
                 return dtoMapper.fromCurrentBankAccount(currentAccount);
             }
         }).collect(Collectors.toList());

    }

    @Override
    public void debit(String accountId, double amountOperation, String description) throws BankAccountNotFoundException, InsufficientBalanceException {
        BankAccount bankAccount = geAccount(accountId);
        if (bankAccount.getBalance() < amountOperation)
            throw new InsufficientBalanceException("insufficient balance");
        AccountOperation accountOperation = new AccountOperation();
        accountOperation.setDateOperatio(new Date());
        accountOperation.setBankAccount(bankAccount);
        accountOperation.setDescription(description);
        accountOperation.setAmount(amountOperation);
        accountOperation.setTypeOperation(TypeOperation.DEBIT);
        accountOperationRepository.save(accountOperation);

        // maj du crédit de compte
        bankAccount.setBalance(bankAccount.getBalance() - amountOperation);
        bankAccountRepository.save(bankAccount);
    }

    @Override
    public void credit(String accountId, double amountOperation, String description) throws BankAccountNotFoundException {
        BankAccount bankAccount = geAccount(accountId);

        AccountOperation accountOperation = new AccountOperation();
        accountOperation.setDateOperatio(new Date());
        accountOperation.setBankAccount(bankAccount);
        accountOperation.setDescription(description);
        accountOperation.setAmount(amountOperation);
        accountOperation.setTypeOperation(TypeOperation.CREDIT);
        accountOperationRepository.save(accountOperation);

        // maj du crédit de compte
        bankAccount.setBalance(bankAccount.getBalance() + amountOperation);
        bankAccountRepository.save(bankAccount);

    }

    @Override
    public void transfert(String accountIdSource, String accountDestination, double amount) throws BankAccountNotFoundException, InsufficientBalanceException {
        debit(accountIdSource, amount, "TRANSFERT FROM" + accountIdSource);
        credit(accountDestination, amount, "TRANSFERT TO" + accountDestination);
    }


    private BankAccount geAccount(String accountId) throws BankAccountNotFoundException {
        return bankAccountRepository.findById(accountId).orElseThrow(() -> new BankAccountNotFoundException("Bank Account Not found"));
    }

}
