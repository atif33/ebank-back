package com.dsi.ebankback.entities;

import com.dsi.ebankback.enums.TypeOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class AccountOperation {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long operationId;

    private Date dateOperatio;

    private double amount;

    @Enumerated(EnumType.STRING)
    private TypeOperation typeOperation;

    @ManyToOne
    private BankAccount bankAccount;
    private String description;
}
