package com.lavv.spring.customers.services;

import com.lavv.spring.customers.entities.User;

import java.util.List;

public interface UserService {

    User getUser(Integer id);
    List<User> getAllUsers();
    void removeUser(Integer id);
    void addUser(User User);
    void updateUser(Integer id, User updateUser);
    List<User> searchUser(String email, String address);
}
