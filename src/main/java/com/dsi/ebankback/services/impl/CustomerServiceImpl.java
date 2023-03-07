package com.dsi.ebankback.services.impl;

import com.dsi.ebankback.dtos.CustomerDTO;
import com.dsi.ebankback.entities.Customer;
import com.dsi.ebankback.entities.Rule;
import com.dsi.ebankback.exceptions.CustomerListEmptyException;
import com.dsi.ebankback.exceptions.CustomerNotFoundException;
import com.dsi.ebankback.mappers.BankAccountMapperImpl;
import com.dsi.ebankback.mappers.CustomerMapperImpl;
import com.dsi.ebankback.repositories.CustomerRepository;
import com.dsi.ebankback.repositories.RuleRepository;
import com.dsi.ebankback.services.CustomerService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Data
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;
    private BankAccountMapperImpl dtoMapper;
    private CustomerMapperImpl customerMapper;
    private RuleRepository ruleRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public CustomerDTO saveCustomer(CustomerDTO dto) {
        Customer customer = customerMapper.fromCustomerDto(dto);
        return customerMapper.fromCustomer(customer);
    }

    @Override
    public List<CustomerDTO> listCustomers() throws CustomerListEmptyException {
        List<Customer> customerList = customerRepository.findAll();

        if (customerList.isEmpty())
            throw new CustomerListEmptyException("List Customers is empty");
        return customerList.stream().map(customerMapper::fromCustomer).collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomer(Long customerId) throws CustomerNotFoundException{
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException("Customer Not Found"));
        return customerMapper.fromCustomer(customer);
    }

    @Override
    public CustomerDTO addRuleToUser(Long customerId, String ruleName) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        CustomerDTO dto = customerMapper.fromCustomer(customer);
        Rule rule = ruleRepository.findByRuleName(ruleName);
        if (customer != null)
             customer.getRuleCollection().add(rule);

        return dto;
    }

    @Override
    public Customer loadUserByName(String name) {
        return customerRepository.findCustomerByName(name);
    }

}
