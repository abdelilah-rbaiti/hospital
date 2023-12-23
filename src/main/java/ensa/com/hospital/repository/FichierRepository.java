package ensa.com.hospital.repository;

import ensa.com.hospital.model.Fichier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FichierRepository extends JpaRepository<Fichier,Long> {
}
