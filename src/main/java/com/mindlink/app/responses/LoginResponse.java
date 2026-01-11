package com.mindlink.app.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private String token;
    private long expiresIn;
    private String email;
    private String username;
    private String role;

    public LoginResponse(String token, long expiresIn, String email, String username, String role) {
        this.email = email;
        this.username = username;
        this.token = token;
        this.expiresIn = expiresIn;
        this.role= role;
    }
}