package com.lavv.spring.customers.services;

import com.lavv.spring.customers.entities.Customer;
import com.lavv.spring.customers.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer getCustomer(Integer id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) return customer.get();
        return null;
    }

    public List<Customer> getAllCustomers() {
        return null;
    }

    public void removeCustomer(Integer id) {
    }

    public void addCustomer(Customer customer) {
    }

    public void updateCustomer(Integer id, Customer updateCustomer) {

    }

    public List<Customer> searchCustomer(String email, String address) {
      return null;
    }
}
