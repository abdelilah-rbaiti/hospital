package ensa.com.hospital.controller;

import ensa.com.hospital.model.Materiel;
import ensa.com.hospital.service.MaterielService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@AllArgsConstructor
@Transactional
@CrossOrigin("http://localhost:3000")
public class MaterielController {

    private final MaterielService materielService;

    @GetMapping(path = "/materiel")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN') and hasAuthority('SCOPE_PERSONTECHNIQUE')")
    public List<Materiel> materiels(){
        return materielService.getMateriel() ;
    }


    @PostMapping(path = "/savemateriel")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN') and hasAuthority('SCOPE_PERSONTECHNIQUE')")
    public Materiel saveMateriel(@RequestBody Materiel materiel){
        return materielService.saveMateriel(materiel);
    }


    @GetMapping(path = "/materiel/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN') and hasAuthority('SCOPE_PERSONTECHNIQUE')")
    public Materiel getMaterielById(@PathVariable Long id){
        return materielService.getMaterielById(id);
    }

    @PutMapping(path = "/materiel/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN') and hasAuthority('SCOPE_PERSONTECHNIQUE')")
    public Materiel updateMateriel(@RequestBody Materiel newMateriel,@PathVariable Long id){
        return materielService.updateMateriel(newMateriel,id);
    }

    @DeleteMapping(path = "/materiel/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN') and hasAuthority('SCOPE_PERSONTECHNIQUE')")
    public String deleteMateriel(@PathVariable Long id){
        return materielService.deleteMateriel(id);
    }
}
