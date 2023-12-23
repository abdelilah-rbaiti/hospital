package ensa.com.hospital.service.security;

import ensa.com.hospital.dto.BaseResponse;
import ensa.com.hospital.dto.ChangePasswordDto;
import ensa.com.hospital.model.security.AppRole;
import ensa.com.hospital.model.security.AppUser;

import java.util.List;

public interface IAccountService {
    AppUser addNewUser(AppUser appUser);
    AppRole addNewRole(AppRole appRole);
    void addRoleToUser(String username,String roleName);
    AppUser loadUserByUsername(String username);
    List<AppUser> findAllUsers();
    boolean isExistByUsername(String username);
    BaseResponse changePassword(ChangePasswordDto changePasswordDto);
    BaseResponse newPassword(ChangePasswordDto changePasswordDto);

    BaseResponse deleteUserByUsername(String username);

}