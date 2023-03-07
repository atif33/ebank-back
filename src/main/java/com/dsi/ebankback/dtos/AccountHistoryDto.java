package com.dsi.ebankback.dtos;

import lombok.Data;

import java.util.List;

@Data
public class AccountHistoryDto {
    private String accountId;
    private String accountName;
    private String typeAccount;
    private double balance;
    private int currentPage;
    private int totalPage;
    private int pageSize;
    private List<OperationsDto> operations;


}
