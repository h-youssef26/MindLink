package com.mindlink.app.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@AllArgsConstructor
@Getter
@Setter
public class VerifyUserDto {
    private String email;
    private String verificationCode;
}