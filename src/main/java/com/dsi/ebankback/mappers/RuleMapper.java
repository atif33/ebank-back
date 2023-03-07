package com.dsi.ebankback.mappers;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.dsi.ebankback.dtos.RuleRequest;
import com.dsi.ebankback.entities.Rule;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class RuleMapper {
    public RuleRequest ruleRequest(Rule rule) {
        RuleRequest request = new RuleRequest();
        BeanUtils.copyProperties(rule, request);
        return request;
    }
}
