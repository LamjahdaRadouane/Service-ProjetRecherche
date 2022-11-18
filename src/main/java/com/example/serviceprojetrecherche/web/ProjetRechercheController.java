package com.example.serviceprojetrecherche.web;

import com.example.serviceprojetrecherche.entities.ProjetRecherche;
import com.example.serviceprojetrecherche.repositories.ProjetRechercheRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
@AllArgsConstructor
public class ProjetRechercheController {
    private ProjetRechercheRepository projetRechercheRepository;
    @GetMapping(path = "/index")
    public String sections(Model model,
                           @RequestParam(name = "page",defaultValue = "0") int page,
                           @RequestParam(name = "size",defaultValue = "5") int size,
                           @RequestParam(name = "keyword",defaultValue = "") String keyword
    ){
        Page<ProjetRecherche> pageProjetRecherches=projetRechercheRepository.findByTitreContains(keyword, PageRequest.of(page,size));
        model.addAttribute("listProjetRecherches",pageProjetRecherches.getContent());
        model.addAttribute("pages",new int[pageProjetRecherches.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",keyword);
        return "projetRecherches";
    }

    @GetMapping("/delete")
    public String delete(Long id, String keyword, int page) {
        projetRechercheRepository.deleteById(id);
        return "redirect:/index?page="+page+"&keyword="+keyword;
    }
    @GetMapping("/")
    public String home() {
        return "template";
    }
    @GetMapping("/projetRecherches")
    @ResponseBody
    public List<ProjetRecherche> lisProjetRecherches(){
        return projetRechercheRepository.findAll();
    }

    @RequestMapping(value="/form",method= RequestMethod.GET)
    public String formProjetRecherche(Model model){
        model.addAttribute("projetRecherche", new ProjetRecherche());
        return "FormProjetRecherche";
    }
    @RequestMapping(value="/SaveProjetRecherche",method= RequestMethod.POST)
    public String save(@Valid ProjetRecherche et, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "FormProjetRecherche";
        }
        projetRechercheRepository.save(et);
        return "redirect:/index";
    }
    @RequestMapping(value="/edit")
    public String edit(Long id, Model model){
        ProjetRecherche et = projetRechercheRepository.getOne(id);
        model.addAttribute("projetRecherche", et);
        return "EditProjetRecherche";
    }
    @RequestMapping(value="/UpdateProjetRecherche",method= RequestMethod.POST)
    public String update(@Valid ProjetRecherche et, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "EditProjetRecherche";
        }
        projetRechercheRepository.save(et);
        return "redirect:/index";
    }
    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e, Model model){
        model.addAttribute("errorMessage", "Not Authorized");
        return "errors";
    }
    @GetMapping("/export")
    public void exportToCsv(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        String fileName = "projetRecherches.csv";
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=" +fileName;
        response.setHeader(headerKey, headerValue);
        List<ProjetRecherche> listProjetRecherches = projetRechercheRepository.findAll();
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = {"ID", "Titre", "Description", "Budget", "Duree", "Justification"};
        String[] nameMapping = {"id", "titre", "description", "budget", "duree", "justification"};
        csvWriter.writeHeader(csvHeader);
        for (ProjetRecherche pR : listProjetRecherches) {
            csvWriter.write(pR, nameMapping);
        }
        csvWriter.close();
    }
    /*
    @GetMapping("/ms")
    public String ms(Model model){
        PagedModel<Entity> pageMs = keycloakRestTemplate.getForObject("http://localhost:portMs/nomMs", PagedModel.class);
        model.addAttribute("ms", pageMs);
        return "ms";
    }

    @GetMapping("/jwt")
    @ResponseBody
    public Map<String, String> map(HttpServletRequest request){
        KeycloakAuthenticationToken token = (KeycloakAuthenticationToken) request.getUserPrincipal();
        KeycloakPrincipal principal = (KeycloakPrincipal) token.getPrincipal();
        KeycloakSecurityContext keycloakSecurityContext = principal.getKeycloakSecurityContext();
        Map<String, String> map = new HashMap<>();
        map.put("access_token", keycloakSecurityContext.getTokenString());
        return map;
    }*/
}