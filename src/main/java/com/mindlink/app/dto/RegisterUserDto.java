package com.mindlink.app.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
@Getter
@Setter
@AllArgsConstructor
public class RegisterUserDto {
    private String email;
    private String password;
    private String username;
}