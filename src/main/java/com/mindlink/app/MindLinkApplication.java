package com.mindlink.app;

import com.mindlink.app.service.DoctorImporterService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MindLinkApplication implements CommandLineRunner {

    private final DoctorImporterService importerService;

    public MindLinkApplication(DoctorImporterService importerService) {
        this.importerService = importerService;
    }

    public static void main(String[] args) {
        SpringApplication.run(MindLinkApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Starting automatic import of doctors from Mockaroo...");
        try {
            importerService.importDoctorsFromMockaroo();
        } catch (Exception e) {
            System.err.println("Warning: Could not fetch doctors on startup: " + e.getMessage());
        }
        System.out.println("Import finished!");
    }
}
