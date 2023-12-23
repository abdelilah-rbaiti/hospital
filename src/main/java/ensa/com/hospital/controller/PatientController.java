package ensa.com.hospital.controller;


import ensa.com.hospital.model.Patient;
import ensa.com.hospital.service.PatientService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@Transactional
@CrossOrigin("http://localhost:3000")
public class PatientController {
    private final PatientService patientService;

    @GetMapping(path = "/patient")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN') and hasAuthority('SCOPE_PERSONMEDICAL')")
    public List<Patient> Patients(){
        return patientService.getPatient() ;
    }


    @PostMapping(path = "/savepatient")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN') and hasAuthority('SCOPE_PERSONMEDICAL')")
    public Patient savePatient(@RequestBody  Patient patient){
        return patientService.savePatient(patient);
    }


    @GetMapping(path = "/patient/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN') and hasAuthority('SCOPE_PERSONMEDICAL')")
    public Patient getPatientById(@PathVariable Long id){
        return patientService.getPatientById(id);
    }

    @PutMapping(path = "/patient/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN') and hasAuthority('SCOPE_PERSONMEDICAL')")
    public Patient updatePatient(@RequestBody Patient newPatient,@PathVariable Long id){
        return patientService.updatePatient(newPatient,id);
    }

    @DeleteMapping(path = "/patient/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN') and hasAuthority('SCOPE_PERSONMEDICAL')")
    public String deletePatient(@PathVariable Long id){
        return patientService.deletePatient(id);
    }

}
