package com.dsi.ebankback.dtos;

import com.dsi.ebankback.entities.Rule;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    private Long customerId;
    private String email;
    private List<String> ruleName;
    private String name;

}
