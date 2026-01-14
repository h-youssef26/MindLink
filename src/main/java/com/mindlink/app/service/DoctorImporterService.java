package com.mindlink.app.service;

import com.mindlink.app.dto.CreateDoctorDto;
import com.mindlink.app.dto.MockDoctorDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class DoctorImporterService {

    private final DoctorService doctorService;

    public DoctorImporterService(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @Transactional
    public void importDoctorsFromMockaroo() {

        String apiUrl = "https://api.mockaroo.com/api/779f28c0?count=50&key=90b9f880";
        RestTemplate restTemplate = new RestTemplate();

        MockDoctorDto[] doctors = restTemplate.getForObject(apiUrl, MockDoctorDto[].class);
        if (doctors == null) return;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");

        for (MockDoctorDto d : doctors) {

            // Skip if email already exists
            if (doctorService.emailExists(d.getEmail())) {
                System.out.println("Skipped existing doctor: " + d.getEmail());
                continue;
            }

            // Map MockDoctorDto to CreateDoctorDto
            CreateDoctorDto createDto = new CreateDoctorDto();
            createDto.setUsername(d.getUsername());
            createDto.setEmail(d.getEmail());
            createDto.setPassword(d.getPassword() != null ? d.getPassword() : "Default123!");
            createDto.setName(d.getUsername());
            createDto.setSpecialty(d.getSpecialty());
            createDto.setConsultationFee(d.getConsultationFee() != null ? d.getConsultationFee() : 100.0);

            if (d.getCreatedAt() != null) {
                LocalDate date = LocalDate.parse(d.getCreatedAt(), formatter);
                createDto.setCreatedAt(date.atStartOfDay()); // time 00:00
            } else {
                createDto.setCreatedAt(LocalDateTime.now());
            }

            // Create doctor using DoctorService
            doctorService.createDoctor(createDto);
            System.out.println("Imported doctor: " + d.getEmail());
        }
    }
}
