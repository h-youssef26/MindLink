package com.mindlink.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MockDoctorDto {
    private String username;
    private String email;
    private String password;
    private String gender;

    @JsonProperty("created_at")
    private String createdAt; // Parse to LocalDateTime later

    private String specialty;
    private Double consultationFee;
}
