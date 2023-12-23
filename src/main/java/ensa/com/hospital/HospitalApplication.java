package ensa.com.hospital;

import ensa.com.hospital.model.*;
import ensa.com.hospital.model.security.AppRole;
import ensa.com.hospital.model.security.AppUser;
import ensa.com.hospital.security.RsaKeysConfig;
import ensa.com.hospital.service.FichierService;
import ensa.com.hospital.service.MaterielService;
import ensa.com.hospital.service.PatientService;
import ensa.com.hospital.service.security.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeysConfig.class)
public class HospitalApplication {

    public static void main(String[] args) {
        SpringApplication.run(HospitalApplication.class, args);
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }

    @Bean
    public WebMvcConfigurer configure() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry reg) {
                reg.addMapping("/**").allowedOrigins("*");
            }
        };

    }

    @Bean
	CommandLineRunner start(AccountService accountService,PatientService patientService,MaterielService materielService,FichierService fichierService){
		return args -> {
			accountService.addNewRole(new AppRole(0,"ADMIN"));
			accountService.addNewUser(new AppUser(0,"admin","123",new ArrayList<>(),new ArrayList<>(),new ArrayList<>()));
			accountService.addRoleToUser("admin","ADMIN");

            accountService.addNewRole(new AppRole(0,"SUPERADMIN"));
            accountService.addNewUser(new AppUser(0,"Sadmin","123",new ArrayList<>(),new ArrayList<>(),new ArrayList<>()));
            accountService.addRoleToUser("Sadmin","SUPERADMIN");

            accountService.addNewRole(new AppRole(0,"PERSONMEDICAL"));
            accountService.addNewUser(new AppUser(0,"ABDO","123",new ArrayList<>(),new ArrayList<>(),new ArrayList<>()));
            accountService.addRoleToUser("ABDO","PERSONMEDICAL");

            accountService.addNewRole(new AppRole(0,"PERSONTECHNIQUE"));
            accountService.addNewUser(new AppUser(0,"KHALIL","123",new ArrayList<>(),new ArrayList<>(),new ArrayList<>()));
            accountService.addRoleToUser("KHALIL","PERSONTECHNIQUE");


            fichierService.saveCsv();
            patientService.saveCsv();
            materielService.saveCsv();


		};
	}




    /*@Bean
    CommandLineRunner start(MaterielService materielService, PatientService patientService, FichierService fichierService){
		return args -> {
			materielService.saveMateriel(new Materiel(0L,"hp","Informatique"));
            Fichier fichier =new Fichier(0L,"fichier1","jfdjnfdvdf",new ArrayList<>());
            fichierService.saveFichier(fichier);
            patientService.savePatient(new Patient(0L,"rbaiti","abdo",23,"male",false,fichier));
		};
	}*/

}
