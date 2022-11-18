
package com.example.serviceprojetrecherche.feign;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="Service-Etudiants")
public interface EtudiantServiceClient {
    @GetMapping("/etudiants/{id}")
    public Etudiant findEtudiantsById(@PathVariable(name="id") Long id);
    @GetMapping("/etudiants")
    public PagedModel<Etudiant> findAllEtudiants();
}