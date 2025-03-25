package com.lavv.spring.customers.controllers;

import com.lavv.spring.customers.dto.RequestLogin;
import com.lavv.spring.customers.entities.User;
import com.lavv.spring.customers.services.AuthService;
import com.lavv.spring.customers.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/auth/login")
    public String login(@RequestBody RequestLogin requestLogin) {
        User user = authService.login(requestLogin.getEmail(), requestLogin.getPassword());
        return JwtUtil.generateToken(user);
    }
}
