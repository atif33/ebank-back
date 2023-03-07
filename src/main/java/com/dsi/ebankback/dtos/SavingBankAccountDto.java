package com.dsi.ebankback.dtos;

import com.dsi.ebankback.entities.AccountOperation;
import com.dsi.ebankback.entities.Customer;
import com.dsi.ebankback.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Data
public class SavingBankAccountDto extends BankAccountDto {
    private String rib;
    //solde
    private double balance;

    private Date createDate;


    private AccountStatus accountStatus;

    private CustomerDTO customer;

    private double interestRate;


}
