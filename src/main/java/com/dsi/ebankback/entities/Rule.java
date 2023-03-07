package com.dsi.ebankback.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class Rule {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ruleId;
    private String ruleName;
}
