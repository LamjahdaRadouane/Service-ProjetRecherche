package com.example.serviceprojetrecherche.repositories;

import com.example.serviceprojetrecherche.entities.ProjetRecherche;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjetRechercheRepository extends JpaRepository<ProjetRecherche, Long> {
    Page<ProjetRecherche> findByTitreContains(String kw, Pageable pageable);
}
