package ensa.com.hospital.security;

import ensa.com.hospital.model.security.AppUser;
import ensa.com.hospital.service.security.IAccountService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserDetailsSericeImpl implements UserDetailsService {
    private final IAccountService accountService;

    public UserDetailsSericeImpl(IAccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = accountService.loadUserByUsername(username);
        Collection<GrantedAuthority> authorities = appUser
                .getRoles()
                .stream()
                .map(appRole -> new SimpleGrantedAuthority(appRole.getRoleName()))
                .collect(Collectors.toList());
        return  new User(appUser.getUsername(), appUser.getPassword(),authorities);
    }
}