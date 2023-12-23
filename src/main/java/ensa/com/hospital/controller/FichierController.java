package ensa.com.hospital.controller;


import ensa.com.hospital.model.Fichier;
import ensa.com.hospital.service.FichierService;
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
public class FichierController {
    private final FichierService fichierService;

    @GetMapping(path = "/fichier")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN') and hasAuthority('SCOPE_PERSONMEDICAL')")
    public List<Fichier> Fichiers(){
        return fichierService.getFichier() ;
    }


    @PostMapping(path = "/savefichier")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN') and hasAuthority('SCOPE_PERSONMEDICAL')")
    public Fichier saveFichier(@RequestBody  Fichier fichier){
        return fichierService.saveFichier(fichier);
    }


    @GetMapping(path = "/fichier/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN') and hasAuthority('SCOPE_PERSONMEDICAL')")
    public Fichier getFichierById(@PathVariable Long id){
        return fichierService.getFichierById(id);
    }

    @PutMapping(path = "/fichier/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN') and hasAuthority('SCOPE_PERSONMEDICAL')")
    public Fichier updateFichier(@RequestBody Fichier newFichier,@PathVariable Long id){
        return fichierService.updateFichier(newFichier,id);
    }

    @DeleteMapping(path = "/fichier/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN') and hasAuthority('SCOPE_PERSONMEDICAL')")
    public String deleteFichier(@PathVariable Long id){
        return fichierService.deleteFichier(id);
    }
}
