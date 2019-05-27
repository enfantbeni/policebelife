package com.belife.policemanager.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.belife.policemanager.model.entity.AgenceBanque;
import com.belife.policemanager.model.entity.Societe;
import com.belife.policemanager.model.repository.AgenceRepository;
import com.belife.policemanager.model.repository.BanqueRepository;
import com.belife.policemanager.model.repository.RolesRepository;
import com.belife.policemanager.model.repository.SocieteRepository;
import com.belife.policemanager.model.repository.SourcePoliceRepository;
import com.belife.policemanager.model.repository.UtilisateurRepository;

@Controller
public class SocieteController {
	
	 @Autowired
     UtilisateurRepository utilisateurRepository; 
	 
	 @Autowired
     RolesRepository rolesRepository;
	 
	 @Autowired
     SourcePoliceRepository sourcePoliceRepository;
	 
	 @Autowired
     BanqueRepository banqueRepository;
	 
	 @Autowired
     SocieteRepository societeRepository;
	 
	 @Autowired
     AgenceRepository agenceRepository;
	 
	 String identifiantSession=null;
	 
	 @Transactional
	 @RequestMapping(value = {"/gestionSociete" }, method = RequestMethod.GET)
	    public String gestionSociete(Model model, HttpSession session, @PageableDefault(size = 100) Pageable pageable, HttpServletRequest request) { 
		 
		 String resultat=null;
			try {
				identifiantSession=session.getAttribute("identifiantSession").toString().trim();
			}
			catch(Exception e) {
				resultat="pageErreur";
				return resultat;
			}
			int page = 0;
			int size = 100;			 
			pageable = PageRequest.of(page, size);
//			gestion Menu 
			model.addAttribute("gestionMenuUtilisateur", "gestionMenuUtilisateur");
			model.addAttribute("gestionMenuBanque", "gestionMenuBanque");
			model.addAttribute("gestionMenuGuichet", "gestionMenuGuichet");
			model.addAttribute("gestionMenuAgence", "gestionMenuAgence");
			model.addAttribute("gestionMenuAgent", "gestionMenuAgent");
			model.addAttribute("gestionMenuSociete", "gestionMenuSociete");
			model.addAttribute("accueilDeux", "accueilDeux");
			model.addAttribute("gestionMenuAgenceBanque", "gestionMenuAgenceBanque");
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionSociete", " Sociéte >");
			model.addAttribute("titre", " Sociétés ");
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("gestionConnexion", "gestionConnexion");
			Page<Societe> societesPage =societeRepository.findAllSocietePage(pageable);
			model.addAttribute("societes", societesPage);
						
			model.addAttribute("listeSociete", "listeSociete");
			model.addAttribute("gestionSociete", "gestionSociete");
			model.addAttribute("menuNavigation", "menuNavigation");
	        return "espaceUtilisateur";			
	    }
	 
	 @Transactional
		@RequestMapping(value = "/listeSocietes")
	    public ModelAndView listeSocietesPageByPage(@RequestParam(name="page", defaultValue="0") int page,@RequestParam(name="size", defaultValue="100") int size,  @ModelAttribute("agenceBanque") AgenceBanque agenceBanque, Model model, HttpSession session, HttpServletRequest request) {
	        ModelAndView modelAndView = new ModelAndView("espaceUtilisateur");
	       
	        PageRequest pageable = PageRequest.of(page-1, size);
	        Page<Societe> societePage = societeRepository.findAllSocietePage(pageable);

			model.addAttribute("societes", societePage);
			model.addAttribute("gestionConnexion", "gestionConnexion");		
			model.addAttribute("gestionMenuUtilisateur", "gestionMenuUtilisateur");
			model.addAttribute("gestionMenuBanque", "gestionMenuBanque");
			model.addAttribute("gestionMenuGuichet", "gestionMenuGuichet");
			model.addAttribute("gestionMenuAgence", "gestionMenuAgence");
			model.addAttribute("gestionMenuAgent", "gestionMenuAgent");
			model.addAttribute("gestionMenuSociete", "gestionMenuSociete");
			model.addAttribute("gestionMenuAgenceBanque", "gestionMenuAgenceBanque");
			model.addAttribute("accueilDeux", "accueilDeux");
			
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionSociete", " Sociéte >");
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("listeSociete", "listeSociete");
			model.addAttribute("titre", " Sociétés ");
			model.addAttribute("gestionSociete", "gestionSociete");
			model.addAttribute("menuNavigation", "menuNavigation");
			
	        return modelAndView;
		} 

	 

	 
//	 @Transactional
	 @RequestMapping(value = {"/resultatRechercheSociete" }, method = RequestMethod.POST)
	    public String resultatRechercheSociete(Model model, @ModelAttribute("societe") Societe societe, HttpSession sessionUtilisateur, HttpSession session) {
			String resultat=null;
			try {
				identifiantSession=session.getAttribute("identifiantSession").toString().trim();
			}
			catch(Exception e) {
				resultat="pageErreur";
				return resultat;
			}
//			gestion Menu 
			model.addAttribute("gestionConnexion", "gestionConnexion");
			model.addAttribute("gestionMenuUtilisateur", "gestionMenuUtilisateur");
			model.addAttribute("gestionMenuBanque", "gestionMenuBanque");
			model.addAttribute("gestionMenuGuichet", "gestionMenuGuichet");
			model.addAttribute("gestionMenuAgence", "gestionMenuAgence");
			model.addAttribute("gestionMenuAgent", "gestionMenuAgent");
			model.addAttribute("gestionMenuSociete", "gestionMenuSociete");
			model.addAttribute("accueilDeux", "accueilDeux");
			model.addAttribute("gestionMenuAgenceBanque", "gestionMenuAgenceBanque");
			String status="actif";
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionSociete", " Sociéte >");
			model.addAttribute("cheminRechercherSociete", "Recherche Société >");
			String codeSociete=societe.getCodeSociete().trim();
			session.setAttribute("codeSocieteCache", codeSociete);
			Societe societeRecherche=societeRepository.findSocieteByCodeSocieteSociete(codeSociete);
			if( societeRecherche == null) {				
				 return "redirect:/messageSocieteNonExistante";  
			}		
			model.addAttribute("titre", " Société ");
			model.addAttribute("codeSociete", codeSociete);
			model.addAttribute("societeRecherche", societeRecherche);			
			model.addAttribute("resultatRechercheSociete", "resultatRechercheSociete");	
			model.addAttribute("actionQuatreBouton", "actionQuatreBouton");	
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("gestionSociete", "gestionSociete");
			model.addAttribute("menuNavigation", "menuNavigation");		
	        return "espaceUtilisateur";	
	    }	 
	 
	 
	 @Transactional
	 @RequestMapping(value = {"/messageSocieteNonExistante" }, method = RequestMethod.GET)
	    public String messageSocieteNonExistante(Model model, @ModelAttribute("societe") Societe societe, HttpSession session, @PageableDefault(size = 100) Pageable pageable, HttpServletRequest request) {
			String resultat=null;
			try {
				identifiantSession=session.getAttribute("identifiantSession").toString().trim();
			}
			catch(Exception e) {
				resultat="pageErreur";
				return resultat;
			}
//			gestion Menu 
			model.addAttribute("gestionConnexion", "gestionConnexion");
			model.addAttribute("gestionMenuUtilisateur", "gestionMenuUtilisateur");
			model.addAttribute("gestionMenuBanque", "gestionMenuBanque");
			model.addAttribute("gestionMenuGuichet", "gestionMenuGuichet");
			model.addAttribute("gestionMenuAgence", "gestionMenuAgence");
			model.addAttribute("gestionMenuAgent", "gestionMenuAgent");
			model.addAttribute("gestionMenuSociete", "gestionMenuSociete");
			model.addAttribute("accueilDeux", "accueilDeux");
			model.addAttribute("gestionMenuAgenceBanque", "gestionMenuAgenceBanque");
			String codeSociete=session.getAttribute("codeSocieteCache").toString().trim();
			model.addAttribute("codeSociete", codeSociete);
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionAgence", " Société >");
			model.addAttribute("titre", " Société ");
			model.addAttribute("codeSociete", codeSociete);
			model.addAttribute("actionQuatreBouton", "actionQuatreBouton");	
			model.addAttribute("messageSocieteNonExistante", "messageSocieteNonExistante");	
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("gestionSociete", "gestionSociete");
			model.addAttribute("menuNavigation", "menuNavigation");		
	        return "espaceUtilisateur";	
	    }

	  

}
