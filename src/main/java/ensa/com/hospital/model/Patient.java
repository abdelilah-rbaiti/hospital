package ensa.com.hospital.model;

import ensa.com.hospital.model.security.AppUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom  ;
    private String prenom ;
    private int age;
    private String genre;
    private Boolean malade;


    @ManyToOne
    @JoinColumn(name="id_fichier")
    private Fichier fichier;

    @ManyToOne
    @JoinColumn(name="id_user")
    private AppUser user ;


}
