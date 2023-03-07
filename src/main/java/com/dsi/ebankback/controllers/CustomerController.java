package com.dsi.ebankback.controllers;

import com.dsi.ebankback.dtos.CustomerDTO;
import com.dsi.ebankback.exceptions.CustomerListEmptyException;
import com.dsi.ebankback.exceptions.CustomerNotFoundException;
import com.dsi.ebankback.repositories.CustomerRepository;
import com.dsi.ebankback.services.CustomerService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Data
@AllArgsConstructor
@Slf4j
@RequestMapping("/api/v1/")
public class CustomerController {

    private CustomerService customerService;
    private CustomerRepository customerRepository;

    @GetMapping("customers")
    public List<CustomerDTO> getCustomers() throws CustomerListEmptyException {
        return customerService.listCustomers();
    }


    @GetMapping("customer/{customerId}")
    public CustomerDTO getCustomer(@PathVariable(name = "customerId") Long id) throws CustomerNotFoundException {
        return customerService.getCustomer(id);
    }

     @PostMapping("saveCustomer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDto) {
        return customerService.saveCustomer(customerDto);
     }


     @PostMapping("addRuleToUser")
    public CustomerDTO addRuleToUser(@RequestParam(name = "customerId") Long id,
                                     @RequestParam(name = "ruleName") String rule
                                     ) {
        return  customerService.addRuleToUser(id, rule);


    }
}
