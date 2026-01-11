package com.mindlink.app.controller;

import com.mindlink.app.dto.CreateDoctorDto;
import com.mindlink.app.model.User;
import com.mindlink.app.service.DoctorService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin") // Admin-only endpoints
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping("/doctors")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createDoctor(@RequestBody CreateDoctorDto dto) {
        User doctorUser = doctorService.createDoctor(dto);
        return ResponseEntity.ok("Doctor created with ID: " + doctorUser.getId());
    }
}
