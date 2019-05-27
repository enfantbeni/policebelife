package com.belife.policemanager.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.belife.policemanager.model.repository.AgenceRepository;
import com.belife.policemanager.model.repository.BanqueRepository;
import com.belife.policemanager.model.repository.RolesRepository;
import com.belife.policemanager.model.repository.SourcePoliceRepository;
import com.belife.policemanager.model.repository.UtilisateurRepository;

@Controller
public class UniteTechniqueController {
	
	 @Autowired
     UtilisateurRepository utilisateurRepository; 
	 
	 @Autowired
     RolesRepository rolesRepository;
	 
	 @Autowired
     SourcePoliceRepository sourcePoliceRepository;
	 
	 @Autowired
     BanqueRepository banqueRepository;
	 
	 @Autowired
     AgenceRepository agenceRepository;
	 
	 String identifiantSession=null;
	 
	 @Transactional
	 @RequestMapping(value = {"/gestionMenuUniteTechnique" }, method = RequestMethod.GET)
	    public String gestionUniteTechnique(Model model, HttpSession session) { 
		 
		 String resultat=null;
			try {
				identifiantSession=session.getAttribute("identifiantSession").toString().trim();
			}
			catch(Exception e) {
				resultat="pageErreur";
				return resultat;
			}
			
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionSequencePolice", "Gestion Sequence >");
			model.addAttribute("titre", "Gestion de Sequence");
			model.addAttribute("identifiantSession", identifiantSession);			
			model.addAttribute("gestionSequencePolice", "gestionSequencePolice");
			model.addAttribute("quatreBoutonSequencePolice", "quatreBoutonSequencePolice");
			model.addAttribute("listeSequencePolice", "listeSequencePolice");
			model.addAttribute("menuNavigation", "menuNavigation");	
			
			
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("titre", "Gestion des Agences");
			
//			gestion Menu 			
			model.addAttribute("gestionMenuUniteTechnique", "gestionMenuUniteTechnique");
			
			
			model.addAttribute("menuNavigation", "menuNavigation");
			
	        return "espaceUtilisateur";			
	    }
	 
	  @Transactional
		@RequestMapping(value = {"/accueilDeuxUniteTechnique" }, method = RequestMethod.GET)
	    public String accueilDeux(Model model, HttpSession session) { 
			String resultat=null;
			try {
				identifiantSession=session.getAttribute("identifiantSession").toString().trim();
			}
			catch(Exception e) {
				resultat="pageErreur";
				return resultat;
			}		
//			gestion Menu 			
			model.addAttribute("gestionMenuUniteTechnique", "gestionMenuUniteTechnique");
			model.addAttribute("accueilUniteTechnique", "accueilUniteTechnique");	
			model.addAttribute("accueilDeuxUniteTechnique", "accueilDeuxUniteTechnique");		
			model.addAttribute("accueilUniteTechniqueMessage", "accueilUniteTechniqueMessage");	
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("cheminAccueil", "Accueil >");
			
			
		    return "espaceUtilisateur";
			
	        
	    }
	
	
	

}
