package com.mindlink.app.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "doctors")
@Getter
@Setter
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String specialty;
    private Double consultationFee; // Consultation fee for this doctor
    private LocalDateTime createdAt;
    private String password;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    @com.fasterxml.jackson.annotation.JsonIgnoreProperties({"doctor", "password", "verificationCode", "verificationCodeExpiresAt", "authorities", "accountNonExpired", "accountNonLocked", "credentialsNonExpired"})
    private User user;
}
