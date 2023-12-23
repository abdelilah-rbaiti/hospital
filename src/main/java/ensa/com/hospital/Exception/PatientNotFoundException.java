package ensa.com.hospital.Exception;

import ensa.com.hospital.model.Patient;

public class PatientNotFoundException extends RuntimeException{
    public PatientNotFoundException(Long id){
        super("Could not found the Patient with id "+ id);
    }
}