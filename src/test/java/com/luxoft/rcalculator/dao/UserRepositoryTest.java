package com.luxoft.rcalculator.dao;

import com.luxoft.rcalculator.model.Role;
import com.luxoft.rcalculator.model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("class UserRepository")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    User getUserForSave() {
        Role role = new Role();
        role.setId(2);
        role.setRole("ROLE_USER");
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        User user = new User();
        user.setName("Bob");
        user.setLogin("bob");
        user.setPassword("123");
        user.setEmail("bob@mail.ru");
        user.setRoles(roles);
        logger.info(user.toString());
        return user;
    }

    @DisplayName("should create user and return not null object")
    @Test
    void shouldCreateUserAndReturnNotNullObject() {
        User user = userRepository.save(getUserForSave());
        logger.info(user.toString());
        assertNotNull(getUserForSave());
    }

    @DisplayName("should create user and return user with id more than zero")
    @Test
    void shouldCreateUserAndReturnUserWithIdMoreThanZero() {
        User user = userRepository.save(getUserForSave());
        logger.info(user.toString());
        assertTrue(user.getId() > 0);
    }

    @DisplayName("should create user and return user with non-null role")
    @Test
    void shouldCreateUserAndReturnUserWithNonNullRole() {
        User user = userRepository.save(getUserForSave());
        logger.info(user.toString());
        assertNotNull(user.getRoles());
    }

}