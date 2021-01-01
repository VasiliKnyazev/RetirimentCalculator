package com.luxoft.rcalculator.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class User {

    private @Id Long id;

    private String mailAddress;
    private String firstName;
    private String secondName;
    private int age;

    public User(String mailAddress, String firstName, String secondName, int age) {
        this.mailAddress = mailAddress;
        this.firstName = firstName;
        this.secondName = secondName;
        this.age = age;
    }
}
