package com.dsi.ebankback.services;

import com.dsi.ebankback.dtos.AccountHistoryDto;
import com.dsi.ebankback.dtos.OperationsDto;
import com.dsi.ebankback.entities.AccountOperation;
import com.dsi.ebankback.exceptions.BankAccountNotFoundException;

import java.util.List;

public interface OperationAccountService {
    List<OperationsDto> operationsAccount();

    List<OperationsDto> operationByAccount(String rib);

    AccountHistoryDto getAccountHistory(String id, int page, int sizePage) throws BankAccountNotFoundException;
}
