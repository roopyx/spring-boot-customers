package com.lavv.spring.customers.repository;

import com.lavv.spring.customers.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    @Query("SELECT c FROM User c WHERE email LIKE %:email% OR address LIKE %:address%")
    List<User> findByEmailOrAddress(@Param("email") String email, String address);

    @Query("SELECT c FROM User c WHERE email = :email AND password = :password")
    List<User> findByEmailOrPassword(@Param("email") String email, @Param("password") String password);
}
