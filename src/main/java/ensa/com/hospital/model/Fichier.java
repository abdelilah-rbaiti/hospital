package ensa.com.hospital.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ensa.com.hospital.model.security.AppUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Fichier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "fichier")
    private List<Patient> patients;

    @ManyToOne
    @JoinColumn(name="id_user")
    private AppUser user ;
}
