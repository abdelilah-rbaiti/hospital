package ensa.com.hospital.Exception;

public class FichierNotFoundException extends RuntimeException{
    public FichierNotFoundException(Long id){
        super("Could not found the Fichier with id "+ id);
    }
}
