package com.luxoft.rcalculator.service;

import com.luxoft.rcalculator.model.Role;
import com.luxoft.rcalculator.model.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findById(int id);

    User findByLogin(String login);

    void add(User user);

    void deleteById(int userId);

    void edit(User user);

    Role getRole(String role);

}
