package com.dats.goktugcase.kafka.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;

@Data
public class UserCreateDto implements Serializable
{


    private String name;

    private String password;

    @Getter
    @Enumerated(EnumType.STRING)
    private Role role;

    @JsonGetter("name")
    public String getName() {
        return name;
    }

    @JsonGetter("password")
    public String getPassword() {
        return password;
    }

    @JsonGetter("role")
    public Role getRole() {
        return role;
    }

    public UserCreateDto(String name, String password, Role role) {
        this.name = name;
        this.role = role;
        this.password = password;
    }
    public UserCreateDto() {
    }
}