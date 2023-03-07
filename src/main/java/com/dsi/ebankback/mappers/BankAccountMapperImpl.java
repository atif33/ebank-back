package com.dsi.ebankback.mappers;

import com.dsi.ebankback.dtos.CurrentBankAccountDto;
import com.dsi.ebankback.dtos.SavingBankAccountDto;
import com.dsi.ebankback.entities.CurrentAccount;
import com.dsi.ebankback.entities.SavingAccount;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service @AllArgsConstructor
public class BankAccountMapperImpl {

    private CustomerMapperImpl customerMapper;
    private OperationsMapper operationsMapper;



    public SavingBankAccountDto fromSavingBankAccount(SavingAccount bankAccount) {
        SavingBankAccountDto savingBankAccountDto = new SavingBankAccountDto();
        BeanUtils.copyProperties(bankAccount, savingBankAccountDto);
        savingBankAccountDto.setCustomer(customerMapper.fromCustomer(bankAccount.getCustomer()));
        savingBankAccountDto.setTypeAccount("SavingAccount");

        return savingBankAccountDto;
    }

    public SavingAccount fromSavingBankAccountDto(SavingBankAccountDto dto) {
        SavingAccount bankAccount = new SavingAccount();
        BeanUtils.copyProperties(dto, bankAccount);
        bankAccount.setCustomer(customerMapper.fromCustomerDto(dto.getCustomer()));
        return bankAccount;
    }

    public CurrentBankAccountDto fromCurrentBankAccount(CurrentAccount bankAccount) {
        CurrentBankAccountDto currentBankAccountDto = new CurrentBankAccountDto();
        BeanUtils.copyProperties(bankAccount, currentBankAccountDto);
        currentBankAccountDto.setCustomerEmail(bankAccount.getCustomer().getEmail());
        currentBankAccountDto.setTypeAccount("CurrentAccount");

        return currentBankAccountDto;
    }

    public CurrentAccount fromCurrentBankAccountDto(CurrentBankAccountDto dto) {
        CurrentAccount bankAccount = new CurrentAccount();
        BeanUtils.copyProperties(dto, bankAccount);
        return bankAccount;
    }

 /*   public BankAccountDto fromBankAccount(BankAccount bankAccount) {
        BankAccountDto dto = new BankAccountDto();
        BeanUtils.copyProperties(bankAccount, dto);
        dto.setCustomerDto(customerMapper.fromCustomer(bankAccount.getCustomer()));
        return dto;
    }*/


}
