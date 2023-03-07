package com.dsi.ebankback.repositories;

import com.dsi.ebankback.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findCustomerByName(String email);
}
