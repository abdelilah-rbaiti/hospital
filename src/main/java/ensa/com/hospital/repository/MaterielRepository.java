package ensa.com.hospital.repository;


import ensa.com.hospital.model.Materiel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterielRepository extends JpaRepository<Materiel,Long> {
}
