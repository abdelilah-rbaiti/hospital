package ensa.com.hospital.Exception;

public class MaterielNotFoundException extends RuntimeException{
    public MaterielNotFoundException(Long id){
        super("Could not found the Materiel with id "+ id);
    }
}
