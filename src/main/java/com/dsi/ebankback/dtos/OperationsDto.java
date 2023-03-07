package com.dsi.ebankback.dtos;

import com.dsi.ebankback.entities.BankAccount;
import com.dsi.ebankback.enums.TypeOperation;
import lombok.Data;

import java.util.Date;

@Data
public class OperationsDto {

    private Long operationId;

    private Date dateOperatio;

    private double amount;

    private TypeOperation typeOperation;
    private String description;
}
