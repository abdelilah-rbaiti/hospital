package ensa.com.hospital.repository.security;

import ensa.com.hospital.model.security.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser,Long> {
    Optional<AppUser> findByUsername(String username);
    boolean existsAppUserByUsername(String username);
}