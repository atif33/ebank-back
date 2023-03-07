package com.dsi.ebankback.services.impl;


import com.dsi.ebankback.dtos.AccountHistoryDto;
import com.dsi.ebankback.dtos.OperationsDto;
import com.dsi.ebankback.entities.AccountOperation;
import com.dsi.ebankback.entities.BankAccount;
import com.dsi.ebankback.exceptions.BankAccountNotFoundException;
import com.dsi.ebankback.mappers.OperationsMapper;
import com.dsi.ebankback.repositories.AccountOperationRepository;
import com.dsi.ebankback.repositories.BankAccountRepository;
import com.dsi.ebankback.services.OperationAccountService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OperationAccountImpl implements OperationAccountService {
    private AccountOperationRepository operationRepository;
    private BankAccountRepository accountRepository;
    private OperationsMapper dtoMapper;

    @Override
    public List<OperationsDto> operationsAccount() {
        List<AccountOperation> operations = operationRepository.findAll();
        return operations.stream().map(dtoMapper::fromOperationAccount).collect(Collectors.toList());
    }

    @Override
    public List<OperationsDto> operationByAccount(String rib) {
        List<AccountOperation> operationsByAccount = operationRepository.findByBankAccountRib(rib);
        List<OperationsDto> operationsDtos = operationsByAccount.stream().map(dtoMapper::fromOperationAccount).collect(Collectors.toList());
        return operationsDtos;
    }

    @Override
    public AccountHistoryDto getAccountHistory(String id, int page, int sizePage) throws BankAccountNotFoundException {
        BankAccount bankAccount = accountRepository.findById(id)
                .orElseThrow(() -> new BankAccountNotFoundException("Account Not found"));
        Page<AccountOperation> history = operationRepository.findByBankAccountRib(id, PageRequest.of(page, sizePage));
        return dtoMapper.fromOperation(history, bankAccount, page, sizePage);
    }


}
