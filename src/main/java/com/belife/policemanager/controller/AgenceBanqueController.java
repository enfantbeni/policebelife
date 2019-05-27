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

import com.belife.policemanager.model.entity.Agence;
import com.belife.policemanager.model.entity.AgenceBanque;
import com.belife.policemanager.model.repository.AgenceBanqueRepository;
import com.belife.policemanager.model.repository.BanqueRepository;
import com.belife.policemanager.model.repository.RolesRepository;
import com.belife.policemanager.model.repository.SourcePoliceRepository;
import com.belife.policemanager.model.repository.UtilisateurRepository;

@Controller
public class AgenceBanqueController  {
	
	 @Autowired
     UtilisateurRepository utilisateurRepository; 
	 
	 @Autowired
     RolesRepository rolesRepository;
	 
	 @Autowired
     SourcePoliceRepository sourcePoliceRepository;
	 
	 @Autowired
     BanqueRepository banqueRepository;
	 
	 @Autowired
     AgenceBanqueRepository agenceBanqueRepository;
	 
	 String identifiantSession=null;
	 
	 @Transactional
	 @RequestMapping(value = {"/gestionAgenceBanque" }, method = RequestMethod.GET)
	    public String gestionBanque(Model model, HttpSession session, @PageableDefault(size = 100) Pageable pageable, HttpServletRequest request) { 
		 
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
			model.addAttribute("gestionMenuAgence", "gestionMenuAgence");
			model.addAttribute("gestionMenuAgent", "gestionMenuAgent");
			model.addAttribute("gestionMenuSociete", "gestionMenuSociete");
			model.addAttribute("gestionMenuAgenceBanque", "gestionMenuAgenceBanque");		
			model.addAttribute("accueilDeux", "accueilDeux");
			model.addAttribute("boutonMAJ", "boutonMAJ");
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionGuichet", " Agence de Banque >");
			model.addAttribute("titre", " Agence de Banque");	
			model.addAttribute("gestionConnexion", "gestionConnexion");
			Page<AgenceBanque> agenceBanquesPage =agenceBanqueRepository.findAllAgenceBanquesPage(pageable);
			model.addAttribute("agenceBanques", agenceBanquesPage);
			model.addAttribute("listeBanque", "listeBanque");
			model.addAttribute("gestionBanque", "gestionBanque");
			model.addAttribute("menuNavigation", "menuNavigation");
	        return "espaceUtilisateur";			
	    }
	 
		@Transactional
		@RequestMapping(value = "/agenceBanques")
	    public ModelAndView listagenceBanquesPageByPage(@RequestParam(name="page", defaultValue="0") int page,@RequestParam(name="size", defaultValue="100") int size,  @ModelAttribute("agenceBanque") AgenceBanque agenceBanque, Model model, HttpSession session, HttpServletRequest request) {
	        ModelAndView modelAndView = new ModelAndView("espaceUtilisateur");
	       
	        PageRequest pageable = PageRequest.of(page-1, size);
	        Page<AgenceBanque> agenceBanquePage = agenceBanqueRepository.findAllAgenceBanquesPage(pageable);

			model.addAttribute("agenceBanques", agenceBanquePage);				
			model.addAttribute("gestionMenuUtilisateur", "gestionMenuUtilisateur");
			model.addAttribute("gestionMenuBanque", "gestionMenuBanque");
			model.addAttribute("gestionMenuAgence", "gestionMenuAgence");
			model.addAttribute("gestionMenuAgent", "gestionMenuAgent");
			model.addAttribute("gestionMenuSociete", "gestionMenuSociete");
			model.addAttribute("gestionMenuAgenceBanque", "gestionMenuAgenceBanque");		
			model.addAttribute("accueilDeux", "accueilDeux");
			model.addAttribute("boutonMAJ", "boutonMAJ");
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionGuichet", " Agence de Banque >");
			model.addAttribute("titre", " Agence de Banque");	
			model.addAttribute("listeBanque", "listeBanque");
			model.addAttribute("gestionBanque", "gestionBanque");
			model.addAttribute("menuNavigation", "menuNavigation");
			model.addAttribute("gestionConnexion", "gestionConnexion");
	        return modelAndView;
		}
		
		 @Transactional
		 @RequestMapping(value = {"/rechercheAgenceBanque" }, method = RequestMethod.GET)
		    public String rechercheAgenceBanque(Model model, HttpSession session, @PageableDefault(size = 100) Pageable pageable, HttpServletRequest request) { 
			 
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
//				gestion Menu 
				model.addAttribute("gestionMenuUtilisateur", "gestionMenuUtilisateur");
				model.addAttribute("gestionMenuBanque", "gestionMenuBanque");
				model.addAttribute("gestionMenuAgence", "gestionMenuAgence");
				model.addAttribute("gestionMenuAgent", "gestionMenuAgent");
				model.addAttribute("gestionMenuSociete", "gestionMenuSociete");
				model.addAttribute("gestionMenuAgenceBanque", "gestionMenuAgenceBanque");	
				model.addAttribute("formulaireNumeroRechercheAgenceBanque", "formulaireNumeroRechercheAgenceBanque");	
				model.addAttribute("accueilDeux", "accueilDeux");
				model.addAttribute("boutonMAJ", "boutonMAJ");
				model.addAttribute("identifiantSession", identifiantSession);
				model.addAttribute("cheminAccueil", "Accueil >");
				model.addAttribute("cheminGestionGuichet", " Agence de Banque >");
				model.addAttribute("titre", " Agence de Banque");	
				model.addAttribute("gestionConnexion", "gestionConnexion");
				Page<AgenceBanque> agenceBanquesPage =agenceBanqueRepository.findAllAgenceBanquesPage(pageable);
				model.addAttribute("agenceBanques", agenceBanquesPage);
				model.addAttribute("gestionBanque", "gestionBanque");
				model.addAttribute("menuNavigation", "menuNavigation");
		        return "espaceUtilisateur";			
		    }
		
		 @Transactional
		 @RequestMapping(value = {"/resultatRechercheAgenceBanque" }, method = RequestMethod.POST)
		    public String resultatRechercheAgence(Model model, @ModelAttribute("agenceBanque") AgenceBanque agenceBanque, HttpSession session) {
				String resultat=null;
				try {
					identifiantSession=session.getAttribute("identifiantSession").toString().trim();
				}
				catch(Exception e) {
					resultat="pageErreur";
					return resultat;
				}
//				gestion Menu 
				model.addAttribute("gestionMenuUtilisateur", "gestionMenuUtilisateur");
				model.addAttribute("gestionMenuBanque", "gestionMenuBanque");
				model.addAttribute("gestionMenuGuichet", "gestionMenuGuichet");
				model.addAttribute("gestionMenuAgence", "gestionMenuAgence");
				model.addAttribute("gestionMenuAgent", "gestionMenuAgent");
				model.addAttribute("gestionMenuSociete", "gestionMenuSociete");
				model.addAttribute("accueilDeux", "accueilDeux");
				model.addAttribute("gestionMenuAgenceBanque", "gestionMenuAgenceBanque");
				model.addAttribute("gestionConnexion", "gestionConnexion");
				String codeGuichet=agenceBanque.getCodeGuichet().trim();
				
				session.setAttribute("codeAgenceBanqueCache", codeGuichet);
				AgenceBanque agenceBanqueRecherche=agenceBanqueRepository.findAgenceBanquecodeGuichet(codeGuichet);
				
				if( agenceBanqueRecherche == null) {				
					 return "redirect:/messageAgenceBanqueNonExistante";  
				}	
				model.addAttribute("codeGuichet", codeGuichet);
				model.addAttribute("cheminAccueil", "Accueil >");
				model.addAttribute("cheminGestionAgence", " Agence Banque >");
				model.addAttribute("cheminRechercherAgence", "Rechercher une Agence >");
				model.addAttribute("titre", " Agence de Banque");
		
				model.addAttribute("agenceBanqueRecherche", agenceBanqueRecherche);			
				model.addAttribute("resultatRechercheGuichet", "resultatRechercheGuichet");	
//				model.addAttribute("actionTroisBouton", "actionTroisBouton");	
				model.addAttribute("identifiantSession", identifiantSession);
				model.addAttribute("gestionBanque", "gestionBanque");
				model.addAttribute("menuNavigation", "menuNavigation");		
		        return "espaceUtilisateur";	
		    }
		 
		 @Transactional
		 @RequestMapping(value = {"/messageAgenceBanqueNonExistante" }, method = RequestMethod.GET)
		    public String messageAgenceNonExistante(Model model, @ModelAttribute("agence") Agence agence, HttpSession session, @PageableDefault(size = 100) Pageable pageable, HttpServletRequest request) {
				String resultat=null;
				try {
					identifiantSession=session.getAttribute("identifiantSession").toString().trim();
				}
				catch(Exception e) {
					resultat="pageErreur";
					return resultat;
				}
//				gestion Menu 
				model.addAttribute("gestionMenuUtilisateur", "gestionMenuUtilisateur");
				model.addAttribute("gestionMenuBanque", "gestionMenuBanque");
				model.addAttribute("gestionMenuGuichet", "gestionMenuGuichet");
				model.addAttribute("gestionMenuAgence", "gestionMenuAgence");
				model.addAttribute("gestionMenuAgent", "gestionMenuAgent");
				model.addAttribute("gestionMenuSociete", "gestionMenuSociete");
				model.addAttribute("accueilDeux", "accueilDeux");
				model.addAttribute("gestionMenuAgenceBanque", "gestionMenuAgenceBanque");
				model.addAttribute("gestionConnexion", "gestionConnexion");
				model.addAttribute("cheminAccueil", "Accueil >");
				model.addAttribute("cheminGestionAgence", " Agence Banque >");
				model.addAttribute("titre", " Agence de Banque ");
				String codeAgenceBanqueCache=session.getAttribute("codeAgenceBanqueCache").toString().trim();
				model.addAttribute("codeGuichet", codeAgenceBanqueCache);
				model.addAttribute("actionTroisBouton", "actionTroisBouton");
				model.addAttribute("messageBanqueNonExistante", "messageBanqueNonExistante");
				model.addAttribute("identifiantSession", identifiantSession);
				model.addAttribute("gestionBanque", "gestionBanque");
				model.addAttribute("menuNavigation", "menuNavigation");		
		        return "espaceUtilisateur";	
		    }
	 
	 

}
