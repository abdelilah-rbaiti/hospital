package ensa.com.hospital.service;

import ensa.com.hospital.Exception.PatientNotFoundException;
import ensa.com.hospital.model.Fichier;

import ensa.com.hospital.model.security.AppUser;
import ensa.com.hospital.repository.FichierRepository;
import ensa.com.hospital.repository.PatientRepository;
import ensa.com.hospital.repository.security.AppUserRepository;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class FichierService {

    private final FichierRepository fichierRepository;
    private final AppUserRepository userRepository;

    public FichierService(FichierRepository fichierRepository, AppUserRepository userRepository){
        this.fichierRepository=fichierRepository;
        this.userRepository = userRepository;
    }

    public List<Fichier> getFichier(){
        return fichierRepository.findAll();
    }

    public Fichier getFichierById(Long id){
        return fichierRepository.findById(id).orElseThrow();
    }

    public Fichier saveFichier(Fichier fichier){
        return fichierRepository.save(fichier);
    }

    public Fichier updateFichier( Fichier newFichier ,  Long id){
        return fichierRepository.findById(id)
                .map(fichier -> {
                    fichier.setDescription(fichier.getDescription());
                    return fichierRepository.save(fichier);
                }).orElse(null);
    }

    public String deleteFichier( Long id){
        if (!fichierRepository.existsById(id)){
            throw new PatientNotFoundException(id);
        }
        fichierRepository.deleteById(id);
        return  "Fichier with id "+id+" has been deleted success.";
    }

    public void saveCsv() {
        try {
            String cheminFichierCSV = "classpath:files/fichiers.csv";
            BufferedReader lecteurCSV = new BufferedReader(new FileReader(ResourceUtils.getFile(cheminFichierCSV)));

            String ligne;
            List<Fichier> fichiers = new ArrayList<>();

            List<AppUser> users = userRepository.findAll();


            lecteurCSV.readLine();
            Random random = new Random();

            while ((ligne = lecteurCSV.readLine()) != null) {
                String[] donnees = ligne.split(",");

                Fichier fichier = new Fichier();
                fichier.setDescription(donnees[1]);
                fichier.setNom(donnees[0]);
                int randomIndex = random.nextInt(users.size());
                AppUser randomUser = users.get(randomIndex);
                fichier.setUser(randomUser);




                fichiers.add(fichier);
            }

            lecteurCSV.close();
            fichierRepository.saveAll(fichiers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
