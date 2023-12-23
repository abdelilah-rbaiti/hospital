package ensa.com.hospital.controller;

import ensa.com.hospital.dto.BaseResponse;
import ensa.com.hospital.dto.UserDto;
import ensa.com.hospital.model.security.AppUser;
import ensa.com.hospital.service.security.IAccountService;
import jakarta.transaction.Transactional;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Transactional
@CrossOrigin("http://localhost:3000")
public class Usercontroller {
    private final IAccountService accountService;

    public Usercontroller(IAccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/addNewUser")
    @PreAuthorize("hasAuthority('SCOPE_SUPERADMIN')")
    public String saveUser (@RequestBody UserDto userDto){
        if(userDto.getPassword().equals(userDto.getConfirmpassword())) {
            AppUser appUser1 = new AppUser(0, userDto.getUsername(), userDto.getPassword(), new ArrayList<>(), new ArrayList<>(),new ArrayList<>());
            accountService.addNewUser(appUser1);
            if(accountService.isExistByUsername(appUser1.getUsername())){
                accountService.addRoleToUser(appUser1.getUsername(), userDto.getRole());
            }
            return "success";
        }else return "error" ;
    }

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('SCOPE_SUPERADMIN')")
    public List<AppUser> users (){
        return accountService.findAllUsers();
    }

    @GetMapping("/user/{username}")
    @PreAuthorize("hasAuthority('SCOPE_SUPERADMIN')")
    public AppUser findbyUsername(@PathVariable String username){
        return accountService.loadUserByUsername(username);
    }

    @DeleteMapping("/user/{username}")
    @PreAuthorize("hasAuthority('SCOPE_SUPERADMIN')")
    public BaseResponse deleteByUserName(@PathVariable String username){
        accountService.deleteUserByUsername(username);
        return BaseResponse.success();
    }


}