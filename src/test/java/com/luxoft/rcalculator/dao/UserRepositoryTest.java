package com.luxoft.rcalculator.dao;

import com.luxoft.rcalculator.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("class UserRepository")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @DisplayName("should create user and return not null object")
    @Test
    void shouldCreateUserAndReturnNotNullObject() {
        User user = userRepository.save(new User("Bob", "bob", "123", "bob@mail.ru"));
        assertNotNull(user);
    }

    @DisplayName("should create user and return user with id more than zero")
    @Test
    void shouldCreateUserAndReturnUserWithIdMoreThanZero() {
        User user = userRepository.save(new User("Bob", "bob", "123", "bob@mail.ru"));
        assertTrue(user.getId() > 0);
    }

    @DisplayName("should create user and return user with non-null role")
    @Test
    void shouldCreateUserAndReturnUserWithNonNullRole() {
        User user = userRepository.save(new User("Bob", "bob", "123", "bob@mail.ru"));
        assertNotNull(user.getRoles());
    }

}
