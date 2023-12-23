package ensa.com.hospital.service;


import ensa.com.hospital.Exception.MaterielNotFoundException;
import ensa.com.hospital.Exception.PatientNotFoundException;
import ensa.com.hospital.model.Fichier;
import ensa.com.hospital.model.Materiel;
import ensa.com.hospital.model.Patient;
import ensa.com.hospital.model.security.AppUser;
import ensa.com.hospital.repository.FichierRepository;
import ensa.com.hospital.repository.MaterielRepository;
import ensa.com.hospital.repository.PatientRepository;
import ensa.com.hospital.repository.security.AppUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class PatientService {
    private final PatientRepository patientRepository;
    private final FichierRepository fichierRepository;
    private final AppUserRepository appUserRepository;

    public PatientService(PatientRepository patientRepository, FichierRepository fichierRepository, AppUserRepository appUserRepository){
        this.patientRepository=patientRepository;
        this.fichierRepository = fichierRepository;
        this.appUserRepository = appUserRepository;
    }

    public List<Patient> getPatient(){
        return patientRepository.findAll();
    }

    public Patient getPatientById(Long id){
        return patientRepository.findById(id).orElseThrow();
    }

    public Patient savePatient(Patient patient){
        return patientRepository.save(patient);
    }

    public Patient updatePatient( Patient newPatient ,  Long id){
        return patientRepository.findById(id)
                .map(patient -> {
                    patient.setAge(newPatient.getAge());
                    patient.setMalade(newPatient.getMalade());
                    patient.setFichier(newPatient.getFichier());
                return patientRepository.save(patient);
                }).orElse(null);
    }

    public String deletePatient( Long id){
        if (!patientRepository.existsById(id)){
            throw new PatientNotFoundException(id);
        }
        patientRepository.deleteById(id);
        return  "Materiel with id "+id+" has been deleted success.";
    }

    public void saveCsv() {
        try {
            String cheminFichierCSV = "classpath:files/patient.csv";
            BufferedReader lecteurCSV = new BufferedReader(new FileReader(ResourceUtils.getFile(cheminFichierCSV)));

            String ligne;
            List<Patient> patients = new ArrayList<>();
            List<Fichier> fichiers = fichierRepository.findAll();
            List<AppUser> users = appUserRepository.findAll();

            Random random = new Random();
            lecteurCSV.readLine();


            while ((ligne = lecteurCSV.readLine()) != null) {
                String[] donnees = ligne.split(",");

                Patient patient = new Patient();
                patient.setNom(donnees[0]);
                patient.setPrenom(donnees[1]);
                patient.setAge(Integer.parseInt(donnees[2]));
                patient.setGenre(donnees[3]);
                patient.setMalade(Boolean.parseBoolean(donnees[4]));

                int randomIndex = random.nextInt(fichiers.size());
                Fichier randomFichier = fichiers.get(randomIndex);
                patient.setFichier(randomFichier);

                int randomIndex2 = random.nextInt(users.size());
                AppUser randomuser = users.get(randomIndex2);
                patient.setUser(randomuser);



                patients.add(patient);
            }

            lecteurCSV.close();
            patientRepository.saveAll(patients);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
