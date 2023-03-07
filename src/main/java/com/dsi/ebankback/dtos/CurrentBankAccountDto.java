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


@Data @NoArgsConstructor @AllArgsConstructor
public class CurrentBankAccountDto extends BankAccountDto{

    private String rib;
    //solde
    private double balance;

    private Date createDate;

    private AccountStatus accountStatus;

    private String customerEmail;

    private double overDraft;

}
