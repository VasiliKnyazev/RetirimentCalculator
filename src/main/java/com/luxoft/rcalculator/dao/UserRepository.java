package com.luxoft.rcalculator.dao;

import com.luxoft.rcalculator.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "select * from users where login = :login", nativeQuery = true)
    User findByLogin(@Param("login") String login);

    @Query(value = "select * from users where email = :email", nativeQuery = true)
    User findByEmail(@Param("email") String email);

}
