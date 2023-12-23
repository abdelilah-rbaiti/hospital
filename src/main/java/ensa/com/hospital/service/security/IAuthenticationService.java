package ensa.com.hospital.service.security;

import ensa.com.hospital.dto.AuthenticationResponse;

public interface IAuthenticationService {
    AuthenticationResponse accessToken(String username, String password);
    AuthenticationResponse refreshToken(String refreshToken);

}