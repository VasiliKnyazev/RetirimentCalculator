package com.luxoft.rcalculator.service;

import com.luxoft.rcalculator.model.Role;
import com.luxoft.rcalculator.model.User;
import com.luxoft.rcalculator.model.dto.RetirementResultDTO;

import java.util.List;

public interface UserService {

    //async test method
    RetirementResultDTO calculateRetirementYearsLeft(int userAge, int userRetirementYear);

    List<User> findAll();

    User findById(int id);

    User findByLogin(String login);

    void add(User user);

    void deleteById(int userId);

    void edit(User user);

    Role getRole(String role);

}
