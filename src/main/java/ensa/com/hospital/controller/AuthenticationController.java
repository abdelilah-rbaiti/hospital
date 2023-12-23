package ensa.com.hospital.controller;

import ensa.com.hospital.dto.AuthenticationRequest;
import ensa.com.hospital.dto.AuthenticationResponse;
import ensa.com.hospital.dto.RefreshTokenRequest;
import ensa.com.hospital.service.security.IAuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/api/auth/v1/")
public class AuthenticationController {
    private final IAuthenticationService authenticationService;

    public AuthenticationController(IAuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/token")
    public ResponseEntity<AuthenticationResponse> jwtToken(@RequestBody AuthenticationRequest authenticationRequest){
        return ResponseEntity.ok(authenticationService.accessToken(authenticationRequest.getUsername(),authenticationRequest.getPassword()));
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<AuthenticationResponse> jwtToken( @RequestBody RefreshTokenRequest refreshTokenRequest){
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest.getRefreshToken()));
    }
}