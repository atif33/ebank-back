package com.dsi.ebankback.services;

import com.dsi.ebankback.dtos.CustomerDTO;
import com.dsi.ebankback.entities.Customer;
import com.dsi.ebankback.entities.Rule;
import com.dsi.ebankback.exceptions.CustomerListEmptyException;
import com.dsi.ebankback.exceptions.CustomerNotFoundException;

import java.util.List;

public interface CustomerService {
    CustomerDTO saveCustomer(CustomerDTO customer);

    // consulter les client
    List<CustomerDTO> listCustomers() throws CustomerListEmptyException;

    CustomerDTO getCustomer(Long CustomerID) throws CustomerNotFoundException;


    CustomerDTO addRuleToUser(Long customerId, String ruleName);

    Customer loadUserByName(String name);
}
