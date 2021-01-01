package com.luxoft.rcalculator.dao;

import com.luxoft.rcalculator.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDAO extends JpaRepository<User, Long> {

    List<User> getAllUsers();

    User findByMailAddress(String lastName);

    User findById(long id);

}
