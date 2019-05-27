package com.belife.policemanager.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.belife.policemanager.model.dto.SourcePrelevementDto;

@Controller
public class CompteController {
	
	private String identifiantSession=null;
    SourcePrelevementDto sourceDto=new  SourcePrelevementDto();
	
	@RequestMapping(value = {"/gestionEditionCompte" }, method = RequestMethod.GET)
    public String gestionEditionCompte(Model model,  HttpSession session) { 		
		String resultat=null;
		try {
			identifiantSession=session.getAttribute("identifiantSession").toString().trim();
		}
		catch(Exception e) {
			resultat="pageErreur";
			return resultat;
		}
		
		model.addAttribute("cheminAccueil",  "Accueil >");
		model.addAttribute("cheminEditionCompte",  "Edition Compte >");
		model.addAttribute("gestionEditionCompte", "gestionEditionCompte");
		model.addAttribute("identifiantSession", identifiantSession);
		model.addAttribute("titre", " Edition Compte");
//		model.addAttribute("menuNavigation", "menuNavigation");
        return "utilisateur/accueilUtilisateur";

	}
    
    @RequestMapping(value = {"/changerMotDePasse" }, method = RequestMethod.GET)
    public String changerMotDePasse(Model model,  HttpSession session) { 		
		String resultat=null;
		try {
			identifiantSession=session.getAttribute("identifiantSession").toString().trim();
		}
		catch(Exception e) {
			resultat="pageErreur";
			return resultat;
		}
		
		model.addAttribute("cheminAccueil",  "Accueil >");
		model.addAttribute("cheminEditionCompte",  "Edition Compte >");
		model.addAttribute("gestionEditionCompte", "gestionEditionCompte");
		model.addAttribute("identifiantSession", identifiantSession);
		model.addAttribute("titre", " Edition Compte");
		model.addAttribute("changerMotDePasse", "changerMotDePasse");
//		model.addAttribute("menuNavigation", "menuNavigation");
        return "espaceUtilisateur";

	}
    
    
}