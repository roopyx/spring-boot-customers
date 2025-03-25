package com.lavv.spring.customers.services;

import com.google.common.hash.Hashing;
import com.lavv.spring.customers.entities.User;
import com.lavv.spring.customers.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    private static String SECRET_KEY;

    @Value("${customers.app.env}")
    public void setSecretKey(String secretKey) {
        SECRET_KEY = secretKey;
    }

    public User getUser(Integer id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    public void removeUser(Integer id) {
        userRepository.deleteById(id);
    }

    public void addUser(User user) {
        String hashPassword = Hashing.sha256().hashString(user.getPassword() + UserServiceImpl.SECRET_KEY, StandardCharsets.UTF_8).toString();
        user.setPassword(hashPassword);
        userRepository.save(user);
    }

    public void updateUser(Integer id, User updateUser) {
        updateUser.setId(id);
        userRepository.save(updateUser);
    }

    public List<User> searchUser(String email, String address) {
        return userRepository.findByEmailOrAddress(email, address);
    }
}

