package com.lavv.spring.customers.services;

import com.lavv.spring.customers.entities.User;

public interface AuthService {

    User login(String email, String password);
}
