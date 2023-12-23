package ensa.com.hospital.model.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ensa.com.hospital.model.Fichier;
import ensa.com.hospital.model.Materiel;
import ensa.com.hospital.model.Patient;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "APPLICATION_USER")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idUser;
    @Column(unique=true,nullable = false,length = 25)
    String username;
    @Column(nullable = false)
    @JsonIgnore
    String password;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Patient> patients;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Fichier> fichiers;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "USER_ROLE",
            joinColumns = @JoinColumn(name = "ID_USER"),
            inverseJoinColumns = @JoinColumn(name = "ID_ROLE"))
    List<AppRole> roles = new ArrayList<>();


}