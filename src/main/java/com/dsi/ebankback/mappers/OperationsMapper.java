package com.dsi.ebankback.mappers;

import com.dsi.ebankback.dtos.AccountHistoryDto;
import com.dsi.ebankback.dtos.OperationsDto;
import com.dsi.ebankback.entities.AccountOperation;
import com.dsi.ebankback.entities.BankAccount;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OperationsMapper {
    private BankAccountMapperImpl dtoMapper;

    public OperationsDto fromOperationAccount(AccountOperation accountOperation) {
        OperationsDto dto = new OperationsDto();
        BeanUtils.copyProperties(accountOperation, dto);
        return dto;
    }

    public AccountHistoryDto fromOperation(Page<AccountOperation> accountOperation, BankAccount bankAccount, int page, int size) {
        AccountHistoryDto operationDto = new AccountHistoryDto();
        BeanUtils.copyProperties(accountOperation, operationDto);
        List<OperationsDto> operDtos = accountOperation.getContent().stream().map(this::fromOperationAccount).collect(Collectors.toList());
        operationDto.setAccountName(bankAccount.getCustomer().getName());
        operationDto.setBalance(bankAccount.getBalance());
        operationDto.setAccountId(bankAccount.getRib());
        operationDto.setTotalPage(accountOperation.getTotalPages());
        operationDto.setPageSize(size);
        operationDto.setCurrentPage(page);
        operationDto.setOperations(operDtos);

        return  operationDto;
    }
}
