package com.dsi.ebankback.controllers;

import com.dsi.ebankback.dtos.AccountHistoryDto;
import com.dsi.ebankback.dtos.BankAccountDto;
import com.dsi.ebankback.dtos.SavingBankAccountDto;
import com.dsi.ebankback.entities.BankAccount;
import com.dsi.ebankback.exceptions.BankAccountNotFoundException;
import com.dsi.ebankback.exceptions.EmptyListAccountException;
import com.dsi.ebankback.mappers.BankAccountMapperImpl;
import com.dsi.ebankback.services.BankAccountService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Data
@AllArgsConstructor
@Slf4j
@RequestMapping("/api/v1/")
public class BankAccountController {

    private BankAccountService bankAccountService;

    @GetMapping("accounts")
    public List<BankAccountDto> getAllAccounts() throws EmptyListAccountException {
        return bankAccountService.getAllAccounts();
    }

    @GetMapping("account/{rib}")
    public BankAccountDto getBankAccount(@PathVariable(name ="rib")String id) throws BankAccountNotFoundException {
        return bankAccountService.getBankAccount(id);
    }



}
