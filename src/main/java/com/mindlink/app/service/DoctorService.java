package com.mindlink.app.service;

import com.mindlink.app.dto.CreateDoctorDto;
import com.mindlink.app.model.Doctor;
import com.mindlink.app.model.User;
import com.mindlink.app.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;

@Service
public class DoctorService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DoctorService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createDoctor(CreateDoctorDto dto) {
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");
        }

        User user = new User(dto.getUsername(), dto.getEmail(), passwordEncoder.encode(dto.getPassword()), User.Role.DOCTOR);
        user.setEnabled(true);

        Doctor doctor = new Doctor();
        doctor.setName(dto.getName());
        doctor.setEmail(dto.getEmail());
        doctor.setSpecialty(dto.getSpecialty());
        doctor.setConsultationFee(dto.getConsultationFee());
        doctor.setCreatedAt(dto.getCreatedAt() != null ? dto.getCreatedAt() : LocalDateTime.now());
        doctor.setUser(user);

        user.setDoctor(doctor);

        return userRepository.save(user); // Cascade will save doctor too
    }

    public boolean emailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

}
