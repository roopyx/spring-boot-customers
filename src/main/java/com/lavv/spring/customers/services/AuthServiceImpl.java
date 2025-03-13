package com.lavv.spring.customers.services;

import com.google.common.hash.Hashing;
import com.lavv.spring.customers.entities.User;
import com.lavv.spring.customers.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class AuthServiceImpl implements AuthService{

    @Value('${jwtKey}')
    private static final String SECRET_KEY;

    @Autowired
    private UserRepository userRepository;

    public User login(String email, String password) {

        String hashPassword = Hashing.sha256().hashString(password + SECRET_KEY, StandardCharsets.UTF_8).toString();

        List<User> result = userRepository.findByEmailOrPassword(email, hashPassword);

        if (result.isEmpty()) {
            return null;
        } else {
            return result.get(0);
        }
    }
}
