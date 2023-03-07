package com.dsi.ebankback.mappers;

import com.dsi.ebankback.dtos.CustomerDTO;
import com.dsi.ebankback.entities.Customer;
import com.dsi.ebankback.entities.Rule;
import com.dsi.ebankback.repositories.RuleRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CustomerMapperImpl {
    private RuleRepository ruleRepository;

    public CustomerDTO fromCustomer(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);
        customerDTO.setRuleName(customer.getRuleCollection().stream().map(Rule::getRuleName).collect(Collectors.toList()));
        /*customerDTO.setName(customer.getName());
        customerDTO.setCustomerId(customer.getCustomerId());
        customerDTO.setEmail(customer.getEmail());*/
        return customerDTO;
    }

    public Customer fromCustomerDto(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        return customer;

    }
}
