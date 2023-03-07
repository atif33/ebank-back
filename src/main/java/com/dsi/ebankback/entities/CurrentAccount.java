package com.dsi.ebankback.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CA") // ==> il va infecter CUR au champ TYPE
@Data @NoArgsConstructor @AllArgsConstructor
public class CurrentAccount extends BankAccount {

    // découvert
    private double overDraft;
}
