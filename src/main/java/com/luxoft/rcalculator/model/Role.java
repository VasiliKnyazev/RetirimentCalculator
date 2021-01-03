package com.luxoft.rcalculator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "role", nullable = false)
    private String role;

    public Integer getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public void setId(Integer roleId) {
        this.id = roleId;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return role;
    }

    //GrantedAuthority method

    @Override
    @JsonIgnore
    public String getAuthority() {
        return role;
    }
}
