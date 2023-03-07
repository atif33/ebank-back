package com.dsi.ebankback.controllers;

import com.dsi.ebankback.entities.Rule;
import com.dsi.ebankback.services.RuleService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/rules")
@Data
@AllArgsConstructor
public class RuleController {
    private RuleService ruleService;

    @PostMapping("/addRule")
    public Rule addRule(@RequestBody Rule rule) {
        return ruleService.addNewRule(rule);
    }
}
