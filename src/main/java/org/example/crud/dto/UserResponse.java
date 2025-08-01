package org.example.crud.dto;

import lombok.Getter;

@Getter
public class UserResponse {
    private final Long id;
    private final String name;
    private final String password;

    public UserResponse(Long id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }
}
