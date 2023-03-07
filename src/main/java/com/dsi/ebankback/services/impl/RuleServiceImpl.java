package com.dsi.ebankback.services.impl;

import com.dsi.ebankback.entities.Rule;
import com.dsi.ebankback.repositories.RuleRepository;
import com.dsi.ebankback.services.RuleService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@Data
@AllArgsConstructor
public class RuleServiceImpl implements RuleService {
    private RuleRepository ruleRepository;

    @Override
    public Rule addNewRule(Rule rule) {
        return ruleRepository.save(rule);
    }
}
