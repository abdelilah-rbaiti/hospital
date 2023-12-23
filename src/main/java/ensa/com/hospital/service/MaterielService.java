package ensa.com.hospital.service;


import ensa.com.hospital.Exception.MaterielNotFoundException;
import ensa.com.hospital.model.Materiel;
import ensa.com.hospital.model.Patient;
import ensa.com.hospital.repository.MaterielRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MaterielService {
    private final MaterielRepository materielRepository;

    public MaterielService(MaterielRepository materielRepository){
        this.materielRepository=materielRepository;
    }

    public List<Materiel> getMateriel(){
        return materielRepository.findAll();
    }

    public Materiel getMaterielById(Long id){
        return materielRepository.findById(id).orElseThrow();
    }

    public Materiel saveMateriel(Materiel materiel){
        return materielRepository.save(materiel);
    }

    public Materiel updateMateriel( Materiel newMateriel ,  Long id){
        return materielRepository.findById(id)
                .map(materiel -> {
                    materiel.setType_materiel(newMateriel.getType_materiel());
                    materiel.setMarque(newMateriel.getMarque());
                    return materielRepository.save(materiel);
                }).orElse(null);
    }

    public String deleteMateriel( Long id){
        if (!materielRepository.existsById(id)){
            throw new MaterielNotFoundException(id);
        }
        materielRepository.deleteById(id);
        return  "Materiel with id "+id+" has been deleted success.";
    }

    public void saveCsv() {
        try {
            String cheminFichierCSV = "classpath:files/materiels.csv";
            BufferedReader lecteurCSV = new BufferedReader(new FileReader(ResourceUtils.getFile(cheminFichierCSV)));

            String ligne;
            List<Materiel> materiels = new ArrayList<>();

            lecteurCSV.readLine();

            while ((ligne = lecteurCSV.readLine()) != null) {
                String[] donnees = ligne.split(",");

                Materiel materiel = new Materiel();
                materiel.setMarque(donnees[0]);
                materiel.setType_materiel(donnees[1]);



                materiels.add(materiel);
            }

            lecteurCSV.close();
            materielRepository.saveAll(materiels);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
