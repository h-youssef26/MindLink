package com.mindlink.app.controller;

import com.mindlink.app.dto.LoginUserDto;
import com.mindlink.app.dto.RegisterUserDto;
import com.mindlink.app.dto.VerifyUserDto;
import com.mindlink.app.model.User;
import com.mindlink.app.responses.LoginResponse;
import com.mindlink.app.service.AuthenticationService;
import com.mindlink.app.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final JwtService jwtService;

    public AuthenticationController(AuthenticationService authenticationService, JwtService jwtService) {
        this.authenticationService = authenticationService;
        this.jwtService = jwtService;
    }


    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody RegisterUserDto input) {
        try {
            User user = authenticationService.signup(input);
            String token = jwtService.generateToken(user);
            LoginResponse response = new LoginResponse(
                    token,
                    jwtService.getExpirationTime(),
                    user.getEmail(),
                    user.getUsername(),
                    user.getRole().name()
            );
            return ResponseEntity.ok(response);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginUserDto input) {
        try {
            User user = authenticationService.authenticate(input);
            String token = jwtService.generateToken(user);
            LoginResponse response = new LoginResponse(
                    token,
                    jwtService.getExpirationTime(),
                    user.getEmail(),
                    user.getUsername(),
                    user.getRole().name()
            );
            return ResponseEntity.ok(response);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }


    @PostMapping("/verify")
    public ResponseEntity<?> verify(@RequestBody VerifyUserDto input) {
        try {
            authenticationService.verifyUser(input);
            return ResponseEntity.ok("Account verified successfully!");
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    @PostMapping("/resend")
    public ResponseEntity<?> resend(@RequestParam String email) {
        try {
            authenticationService.resendVerificationCode(email);
            return ResponseEntity.ok("Verification code resent to email.");
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }
}
