package com.dsi.ebankback.repositories;

import com.dsi.ebankback.entities.Rule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RuleRepository extends JpaRepository<Rule, Long> {
    Rule findByRuleName(String roleName);
}
