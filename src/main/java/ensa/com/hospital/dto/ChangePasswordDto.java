package ensa.com.hospital.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ChangePasswordDto {
    private String username;
    private String oldPassword;
    private String newPassword;
}