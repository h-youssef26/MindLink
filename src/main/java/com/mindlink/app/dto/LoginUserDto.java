package com.mindlink.app.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
@Getter
@Setter
@AllArgsConstructor
public class LoginUserDto {
    private String email;
    private String password;
}