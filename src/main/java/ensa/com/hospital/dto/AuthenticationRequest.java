package ensa.com.hospital.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class AuthenticationRequest {
    private String username;
    private String password;
}