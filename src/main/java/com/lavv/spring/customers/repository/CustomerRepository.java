package com.lavv.spring.customers.repository;

import com.lavv.spring.customers.entities.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
}
