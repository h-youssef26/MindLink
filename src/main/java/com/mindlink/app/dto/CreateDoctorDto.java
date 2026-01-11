package com.mindlink.app.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateDoctorDto {
    private String username;        // For User entity
    private String email;           // For both User and Doctor
    private String password;        // For User login
    private String name;            // Doctor full name
    private String specialty;
    private Double consultationFee;
}
