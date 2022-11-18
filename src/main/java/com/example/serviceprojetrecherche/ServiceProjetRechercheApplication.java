package com.example.serviceprojetrecherche;

import com.example.serviceprojetrecherche.entities.ProjetRecherche;
import com.example.serviceprojetrecherche.repositories.ProjetRechercheRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ServiceProjetRechercheApplication {


	public static void main(String[] args) {
		SpringApplication.run(ServiceProjetRechercheApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(ProjetRechercheRepository projetRechercheRepository){
		return args -> {
			projetRechercheRepository.save(
					new ProjetRecherche(null, "Titre1","Description1", 1000 + Math.round(new Double(Math.random()*10000)*100.0)/100.0, "1 ans", "Justification1"));
			projetRechercheRepository.save(
					new ProjetRecherche(null, "Titre2","Description2", 1000 + Math.round(new Double(Math.random()*10000)*100.0)/100.0, "2 ans", "Justification2"));
			projetRechercheRepository.save(
					new ProjetRecherche(null, "Titre3","Description3", 1000 + Math.round(new Double(Math.random()*10000)*100.0)/100.0, "3 ans", "Justification3"));
			projetRechercheRepository.save(
					new ProjetRecherche(null, "Titre4","Description4", 1000 + Math.round(new Double(Math.random()*10000)*100.0)/100.0, "2 ans", "Justification4"));
			projetRechercheRepository.save(
					new ProjetRecherche(null, "Titre5","Description5", 1000 + Math.round(new Double(Math.random()*10000)*100.0)/100.0, "3 ans", "Justification5"));
			projetRechercheRepository.save(
					new ProjetRecherche(null, "Titre6","Description6", 1000 + Math.round(new Double(Math.random()*10000)*100.0)/100.0, "2 ans", "Justification6"));
			projetRechercheRepository.save(
					new ProjetRecherche(null, "Titre7","Description7", 1000 + Math.round(new Double(Math.random()*10000)*100.0)/100.0, "1 ans", "Justification7"));
			projetRechercheRepository.save(
					new ProjetRecherche(null, "Titre8","Description8", 1000 + Math.round(new Double(Math.random()*10000)*100.0)/100.0, "2 ans", "Justification8"));
			projetRechercheRepository.save(
					new ProjetRecherche(null, "Titre9","Description9", 1000 + Math.round(new Double(Math.random()*10000)*100.0)/100.0, "3 ans", "Justification9"));

			projetRechercheRepository.findAll().forEach(pR->{
				System.out.println(pR.getTitre());
			});
		};
	}
}