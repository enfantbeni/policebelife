package com.belife.policemanager.aaroncontroller;

import java.util.List;

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

import com.belife.policemanager.model.aaronentity.AaronAgence;
import com.belife.policemanager.model.aaronrepository.AaronAgenceRepository;
import com.belife.policemanager.model.entity.Agence;
import com.belife.policemanager.model.repository.AgenceRepository;
import com.belife.policemanager.model.repository.BanqueRepository;
import com.belife.policemanager.model.repository.RolesRepository;
import com.belife.policemanager.model.repository.SourcePoliceRepository;
import com.belife.policemanager.model.repository.UtilisateurRepository;

@Controller
public class AaronAgenceController {


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
	 
	 @Autowired
	 AaronAgenceRepository aaronAgenceRepository;
	 
	 String identifiantSession=null;
	 
	 
	 
	 @Transactional
	 @RequestMapping(value = {"/aaronGestionAgence" }, method = RequestMethod.GET)
	    public String gestionAgence(Model model, HttpSession session, @PageableDefault(size = 100) Pageable pageable, HttpServletRequest request) { 
		 
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
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionAgence", "Gestion Agence >");
			model.addAttribute("titre", "Gestion des Agences de AARON");
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("listeAgence", "listeAgence");
			model.addAttribute("listeAgence", "listeAgence");
			model.addAttribute("gestionMenuAgenceBanque", "gestionMenuAgenceBanque");
//			gestion Menu 
			model.addAttribute("gestionMenuUtilisateur", "gestionMenuUtilisateur");
			model.addAttribute("gestionMenuBanque", "gestionMenuBanque");
			model.addAttribute("gestionMenuGuichet", "gestionMenuGuichet");
			model.addAttribute("gestionMenuAgence", "gestionMenuAgence");
			model.addAttribute("gestionMenuAgent", "gestionMenuAgent");
			model.addAttribute("gestionMenuSociete", "gestionMenuSociete");
			model.addAttribute("accueilDeux", "accueilDeux");
			
			model.addAttribute("gestionAgence", "gestionAgence");
			model.addAttribute("menuNavigation", "menuNavigation");
			
			
			Page<AaronAgence> agences =aaronAgenceRepository.findAllAgencePage(pageable);
			model.addAttribute("agences", agences);
	        return "aaron/espaceUtilisateur";			
	    }
	 
	 @Transactional
	 @RequestMapping(value = {"/aaronAjoutAgence" }, method = RequestMethod.GET)
	    public String ajoutAgence(Model model, HttpSession session,@PageableDefault(size = 100) Pageable pageable, HttpServletRequest request) { 
		 
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
			model.addAttribute("cheminGestionAgence", "Gestion Agence >");
			model.addAttribute("cheminAjouterAgence", "Ajouter une Agence >");
			model.addAttribute("titre", "Gestion des Agences de AARON");
			Page<AaronAgence> agences =aaronAgenceRepository.findAllAgencePage(pageable);
			model.addAttribute("agences", agences);
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("formulaireGestionAgence", "formulaireGestionAgence");
			model.addAttribute("listeAgence", "listeAgence");
			model.addAttribute("gestionAgence", "gestionAgence");
			model.addAttribute("menuNavigation", "menuNavigation");
	        return "aaron/espaceUtilisateur";			
	    }
	 
	 @Transactional
	 @RequestMapping(value = {"/aaronResultatAjoutAgence" }, method = RequestMethod.POST)
	    public String resultatAjoutAgence(Model model, @ModelAttribute("aaronAgence") AaronAgence aaronAgence , HttpSession session, @PageableDefault(size = 100) Pageable pageable, HttpServletRequest request) { 		 
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
			String codeAgence=aaronAgence.getCodeAgence().trim();
			String codeDirect=aaronAgence.getCodeDirect().trim();
			String nomDirect=aaronAgence.getNomDirect().trim();
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionAgence", "Gestion Agence >");
			model.addAttribute("cheminAjouterAgence", "Ajouter une Agence >");
			model.addAttribute("titre", "Gestion des Agences de AARON ");
			
			
			AaronAgence agenceSave=null;
			AaronAgence nomDirectSave=null;
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("gestionAgence", "gestionAgence");
			model.addAttribute("menuNavigation", "menuNavigation");
			if( codeAgence != null && codeAgence.length() > 0 && codeAgence.length()<=5 && codeDirect != null && codeDirect.length() > 0 && codeDirect.length()<=5 && nomDirect != null && nomDirect.length() > 0 && nomDirect.length()<=50 ) {						
					agenceSave=aaronAgenceRepository.findAgenceByCodeDirect(codeDirect);
					nomDirectSave=aaronAgenceRepository.findAgenceByNomDirect(nomDirect);
//					nomAgenceVerification=agenceSave.getNomAgence();
					if(agenceSave==null && nomDirectSave ==null) {
							String status="A";
							aaronAgence.setCodeAgence(codeAgence);
							aaronAgence.setCodeDirect(codeDirect);
							aaronAgence.setNomDirect(nomDirect);
							aaronAgence.setStatus(status);
							
							model.addAttribute("listeAgence", "listeAgence");
							model.addAttribute("gestionAgence", "gestionAgence");
							aaronAgenceRepository.save(aaronAgence);
							model.addAttribute("ajoutSuccesAgence", "Un Direct ajouté avec succès");
							Page<AaronAgence> agences =aaronAgenceRepository.findAllAgencePage(pageable);
							model.addAttribute("agences", agences);					
								return "aaron/espaceUtilisateur";
					}	
					if( agenceSave!=null ) {
						model.addAttribute("codeDirectErreur", " Code Direct déjà existant");
					}
					if(nomDirectSave!=null) {
						model.addAttribute("nomDirectErreur", " Nom Direct déjà existant");
					}
				
			}
			
			model.addAttribute("formErreurAgence", "formErreur");
			if(codeAgence==null || codeAgence.length()==0 || codeAgence.length()>5) {
				model.addAttribute("codeAgenceErreur", "Code  Agence invalide");
			}
			if(codeDirect==null || codeDirect.length()==0 || codeDirect.length()>5) {
				model.addAttribute("codeDirectErreur", "Code Direct invalide");
			}
			Page<AaronAgence> agences =aaronAgenceRepository.findAllAgencePage(pageable);
			model.addAttribute("agences", agences);
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("resultatAjoutAgence", "resultatAjoutAgence");
			model.addAttribute("formulaireGestionAgence", "formulaireGestionAgence");
			model.addAttribute("listeAgence", "listeAgence");
			model.addAttribute("gestionAgence", "gestionAgence");
			model.addAttribute("menuNavigation", "menuNavigation");
	        return "aaron/espaceUtilisateur";			
	 }
	 
	 @Transactional
	 @RequestMapping(value = {"/aaronModifierAgence" }, method = RequestMethod.GET)
	    public String modifierAgence(Model model, HttpSession session, @PageableDefault(size = 100) Pageable pageable, HttpServletRequest request) { 
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
			model.addAttribute("cheminGestionAgence", "Gestion Agence >");
			model.addAttribute("cheminModifierAgence", "Modifier une Agence >");
			model.addAttribute("titre", "Gestion des Agences de AARON");
			
			Page<AaronAgence> agences =aaronAgenceRepository.findAllAgencePage(pageable);
			model.addAttribute("agences", agences);
			model.addAttribute("formulaireNumeroModifAgence", "formulaireNumeroModifAgence");
			model.addAttribute("actionTroisBouton", "actionTroisBouton");	
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("listeAgence", "listeAgence");
			model.addAttribute("gestionAgence", "gestionAgence");
			model.addAttribute("menuNavigation", "menuNavigation");
	        return "aaron/espaceUtilisateur";	
	    }
	 
	 @Transactional
	 @RequestMapping(value = {"/aaronFormulaireNumeroModifAgence" }, method = RequestMethod.POST)
	    public String formulaireNumeroModifAgence(Model model, @ModelAttribute("agence") Agence agence, HttpSession session, @PageableDefault(size = 100) Pageable pageable, HttpServletRequest request) {
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
			model.addAttribute("cheminGestionAgence", "Gestion Agence >");
			model.addAttribute("cheminModifierAgence", "Modifier une Agence >");
			model.addAttribute("titre", " Gestion d'Agence de AARON ");
			String codeDirect=agence.getCodeDirect().trim();
			Agence agenceRecherche=agenceRepository.findAgenceByCodeDirect(codeDirect);				
			
			if( agenceRecherche == null) {
				 return "redirect:/messageAgenceNonExistante";  
			}							
		
			Page<AaronAgence> agences =aaronAgenceRepository.findAllAgencePage(pageable);
			model.addAttribute("agences", agences);
			model.addAttribute("titre", " Gestion d'Agence de AARON ");
			session.setAttribute("codeAgenceCache", codeDirect);	
			model.addAttribute("formulaireGestionModifAgence", "formulaireGestionModifAgence");
			model.addAttribute("actionTroisBouton", "actionTroisBouton");	
			model.addAttribute("agenceRecherche", agenceRecherche);
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("listeAgence", "listeAgence");
			model.addAttribute("gestionAgence", "gestionAgence");
			model.addAttribute("menuNavigation", "menuNavigation");			
	        return "aaron/espaceUtilisateur";	
	        
	    }

	 
//	 Resultat de la modification des données d'un formulaire
	 @Transactional
	 @RequestMapping(value = {"/aaronResultatModifAgence" }, method = RequestMethod.POST)
	    public String resultatModifAgence(Model model, @ModelAttribute("aaronAgence") AaronAgence aaronAgence, HttpSession sessionUtilisateur, HttpSession session, @PageableDefault(size = 100) Pageable pageable, HttpServletRequest request) {
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
			
			String codeAgence=aaronAgence.getCodeAgence().trim();
			String codeDirect=aaronAgence.getCodeDirect().trim();
			String nomDirect=aaronAgence.getNomDirect().trim();
			String status="A";
			
			String codeAgenceCache=session.getAttribute("codeAgenceCache").toString().trim();	
			
			AaronAgence agenceRecherche=aaronAgenceRepository.findAgenceByCodeDirect(codeAgenceCache);
			
			AaronAgence codeDirectRecherche=aaronAgenceRepository.findAgenceByCodeDirect(codeDirect);
			AaronAgence nomDirectSave=aaronAgenceRepository.findAgenceByNomDirect(nomDirect);
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionAgence", "Gestion Agence >");
			model.addAttribute("cheminModifierAgence", "Modifier une Agence >");
			model.addAttribute("titre", "Gestion des Agences de AARON");
			Page<AaronAgence> agences =aaronAgenceRepository.findAllAgencePage(pageable);
			if( codeAgence==null || codeAgence.length()==0 || codeAgence.length()>5 || codeDirect==null || codeDirect.length()==0 || codeDirect.length()>5 || nomDirect==null || nomDirect.length()==0 || nomDirect.length()>50 ) {						
				
				model.addAttribute("agenceRecherche", agenceRecherche);
				model.addAttribute("formErreurAgence", "formErreur");
				model.addAttribute("identifiantSession", identifiantSession);
				model.addAttribute("actionTroisBouton", "actionTroisBouton");	
				model.addAttribute("formulaireGestionModifAgence", "formulaireGestionModifAgence");
				model.addAttribute("listeAgence", "listeAgence");
				model.addAttribute("gestionAgence", "gestionAgence");
				model.addAttribute("menuNavigation", "menuNavigation");	
				
				if(nomDirect==null || nomDirect.length()==0 || nomDirect.length()>50) {
					model.addAttribute("nomDirectErreur", "Nom de l'Agence invalide");
				}
				if(codeAgence==null || codeAgence.length()==0 || codeAgence.length()>5) {
					model.addAttribute("codeAgenceErreur", "Code de l'Agence invalide");
				}
				if(codeDirect==null || codeDirect.length()==0 || codeDirect.length()>5) {
					model.addAttribute("codeAgenceErreur", "Code du Direct invalide");
				}
				if(nomDirectSave !=null) {
					model.addAttribute("nomAgenceExistantErreur", "Nom de l'Agence déjà existant");
				}
				if(codeDirectRecherche !=null) {
					model.addAttribute("codeDirectExistantErreur", "Code du Direct déjà existant");
				}
				
				model.addAttribute("agences", agences);				
				return "aaron/espaceUtilisateur";	
			}
			
			
			Integer idAgenceOmission=agenceRecherche.getIdAgence();
			List<AaronAgence> agencesOmission=aaronAgenceRepository.findAllAgencesOmission(idAgenceOmission);
			Boolean estEgal=false;
			for(AaronAgence ag:agencesOmission) {
				
				if(ag.getCodeDirect().equals(codeDirect)) {
					estEgal=true;
					model.addAttribute("codeDirectExistantErreur", "Code Direct déjà existant");
				}
				if(ag.getNomDirect().equals(nomDirect)) {
					estEgal=true;
					model.addAttribute("nomDirectExistantErreur", "Nom direct déjà existant");
				}
				if(estEgal) {
					model.addAttribute("agences", agences);	
					model.addAttribute("agenceRecherche", agenceRecherche);
					model.addAttribute("formErreurAgence", "formErreur");
					model.addAttribute("identifiantSession", identifiantSession);
					model.addAttribute("actionTroisBouton", "actionTroisBouton");	
					model.addAttribute("formulaireGestionModifAgence", "formulaireGestionModifAgence");
					model.addAttribute("listeAgence", "listeAgence");
					model.addAttribute("gestionAgence", "gestionAgence");
					model.addAttribute("menuNavigation", "menuNavigation");	
					return "aaron/espaceUtilisateur";	
				}
			}
					
			 agenceRecherche.setNomDirect(nomDirect);
			 agenceRecherche.setCodeAgence(codeAgence);
			 agenceRecherche.setCodeDirect(codeDirect);
			 agenceRecherche.setStatus(status);
			 
			aaronAgenceRepository.save(agenceRecherche);
			model.addAttribute("agenceRecherche", agenceRecherche);
			model.addAttribute("agences", agences);	
			model.addAttribute("listeAgence", "listeAgence");
			model.addAttribute("actionTroisBouton", "actionTroisBouton");	
			model.addAttribute("resultatModifAgence", "resultatModifAgence");
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("gestionAgence", "gestionAgence");
			model.addAttribute("menuNavigation", "menuNavigation");			
	        return "aaron/espaceUtilisateur";	
	    }
	 
	 
////	  Action Recherche Banque
	 @Transactional
	 @RequestMapping(value = {"/aaronRechercheAgence" }, method = RequestMethod.GET)
	    public String rechercherAgence(Model model, HttpSession session, @PageableDefault(size = 100) Pageable pageable, HttpServletRequest request) { 
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
			model.addAttribute("cheminGestionAgence", "Gestion Agence >");
			model.addAttribute("cheminRechercherAgence", "Rechercher une Agence >");
			model.addAttribute("titre", "Gestion des Agences de AARON ");
	
			Page<AaronAgence> agences =aaronAgenceRepository.findAllAgencePage(pageable);
			model.addAttribute("agences", agences);
			model.addAttribute("formulaireNumeroRechercheAgence", "formulaireNumeroRechercheAgence");
			model.addAttribute("actionTroisBouton", "actionTroisBouton");	
			model.addAttribute("identifiantSession", identifiantSession);  
			model.addAttribute("listeAgence", "listeAgence");
			model.addAttribute("gestionAgence", "gestionAgence");
			model.addAttribute("menuNavigation", "menuNavigation");
	        return "aaron/espaceUtilisateur";		        
	    }
	 
	 @Transactional
	 @RequestMapping(value = {"/aaronResultatRechercheAgence" }, method = RequestMethod.POST)
	    public String resultatRechercheAgence(Model model, @ModelAttribute("aaronAgence") AaronAgence aaronAgence, HttpSession session, @PageableDefault(size = 100) Pageable pageable, HttpServletRequest request) {
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
			String codeDirect=aaronAgence.getCodeDirect().trim();
			
			session.setAttribute("codeAgenceCache", codeDirect);
			AaronAgence agenceRecherche=aaronAgenceRepository.findAgenceByCodeDirect(codeDirect);
			if( agenceRecherche == null) {				
				 return "redirect:/messageAgenceNonExistante";  
			}	
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionAgence", "Gestion Agence >");
			model.addAttribute("cheminRechercherAgence", "Rechercher une Agence >");
			model.addAttribute("titre", "Gestion des Agences de AARON ");
		
			Page<AaronAgence> agences =aaronAgenceRepository.findAllAgencePage(pageable);
			model.addAttribute("agenceRecherche", agenceRecherche);			
			model.addAttribute("resultatRechercheAgence", "resultatRechercheAgence");	
			model.addAttribute("actionTroisBouton", "actionTroisBouton");	
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("gestionAgence", "gestionAgence");
			model.addAttribute("menuNavigation", "menuNavigation");		
	        return "aaron/espaceUtilisateur";	
	    }
	 
	 @Transactional
	 @RequestMapping(value = {"/aaronMessageAgenceNonExistante" }, method = RequestMethod.GET)
	    public String messageAgenceNonExistante(Model model, @ModelAttribute("agence") Agence agence, HttpSession session, @PageableDefault(size = 100) Pageable pageable, HttpServletRequest request) {
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
			model.addAttribute("cheminGestionAgence", "Gestion Agence >");
			model.addAttribute("cheminRechercherAgence", " Agence non existante >");
			model.addAttribute("titre", "Gestion des Agences");
			String codeAgenceCache=session.getAttribute("codeAgenceCache").toString().trim();
			model.addAttribute("codeAgence", codeAgenceCache);	
			model.addAttribute("messageAgenceNonExistante", "messageAgenceNonExistante");		
			model.addAttribute("actionTroisBouton", "actionTroisBouton");
			
			Page<AaronAgence> agences =aaronAgenceRepository.findAllAgencePage(pageable);
			model.addAttribute("agences", agences);
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("listeAgence", "listeAgence");
			model.addAttribute("gestionAgence", "gestionAgence");
			model.addAttribute("menuNavigation", "menuNavigation");		
	        return "aaron/espaceUtilisateur";	
	    }
	 	
	 /// Action suppression Agence	
	 @Transactional
	 @RequestMapping(value = {"/aaronSupprimerAgence" }, method = RequestMethod.GET)
	    public String supprimerAgence(Model model, HttpSession session, @PageableDefault(size = 100) Pageable pageable, HttpServletRequest request) { 
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
			model.addAttribute("cheminGestionAgence", "Gestion Agence >");
			model.addAttribute("cheminSupprimerAgence", "Supprimer Agence >");
			model.addAttribute("titre", "Gestion des Agences de AARON ");
			
			Page<AaronAgence> agences =aaronAgenceRepository.findAllAgencePage(pageable);
			model.addAttribute("agences", agences);
			model.addAttribute("formulaireNumeroSupprimerAgence", "formulaireNumeroSupprimerAgence");
			model.addAttribute("actionTroisBouton", "actionTroisBouton");	
			model.addAttribute("identifiantSession", identifiantSession);  
			model.addAttribute("listeAgence", "listeAgence");
			model.addAttribute("gestionAgence", "gestionAgence");
			model.addAttribute("menuNavigation", "menuNavigation");
	        return "aaron/espaceUtilisateur";	        
	    }
	 
	 @Transactional
	 @RequestMapping(value = {"/aaronResultatSupprimerAgence" }, method = RequestMethod.POST)
	    public String resultatSuppressionAgence(Model model, @ModelAttribute("aaronAgence") AaronAgence aaronAgence, HttpSession session, @PageableDefault(size = 100) Pageable pageable, HttpServletRequest request) {
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
			model.addAttribute("titre", "Gestion des Agences de AARON ");
			model.addAttribute("accueilDeux", "accueilDeux");
			model.addAttribute("gestionMenuAgenceBanque", "gestionMenuAgenceBanque");
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionAgence", "Gestion Agence >");
			model.addAttribute("cheminSupprimerAgence", "Supprimer Agence >");
			String codeDirect=aaronAgence.getCodeDirect().trim();
			session.setAttribute("codeAgenceCache", codeDirect);
			AaronAgence agenceRecherche=aaronAgenceRepository.findAgenceByCodeDirect(codeDirect);
			if( agenceRecherche == null) {				
				 return "redirect:/messageAgenceNonExistante";  
			}								
		
			Page<AaronAgence> agences =aaronAgenceRepository.findAllAgencePage(pageable);
			model.addAttribute("agenceRecherches", agenceRecherche);			
			model.addAttribute("dialog_boxAgence", "dialog_boxAgence");	
			model.addAttribute("dialog_backgroundAgence", "dialog_backgroundAgence");	
			model.addAttribute("formulaireNumeroSupprimerAgence", "formulaireNumeroSupprimerAgence");
			model.addAttribute("actionTroisBouton", "actionTroisBouton");	
			model.addAttribute("agences", agences);
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("codeAgence", codeDirect);
			model.addAttribute("listeAgence", "listeAgence");
			model.addAttribute("gestionAgence", "gestionAgence");
			model.addAttribute("menuNavigation", "menuNavigation");		
	        return "aaron/spaceUtilisateur";	
	    }
	 
	 @Transactional
	 @RequestMapping(value = {"/aaronSuccesSuppressionAgence" }, method = RequestMethod.POST)
	    public String succesSuppressionAgence(Model model, @ModelAttribute("aaronAgence") AaronAgence aaronAgence, HttpSession session, @PageableDefault(size = 100) Pageable pageable, HttpServletRequest request) {
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
			model.addAttribute("cheminGestionAgence", "Gestion Agence >");
			model.addAttribute("cheminSupprimerAgence", "Supprimer Agence >");
			String codeAgenceSuppression=session.getAttribute("codeAgenceCache").toString().trim();
			 AaronAgence agenceSuppression=aaronAgenceRepository.findAgenceByCodeDirect(codeAgenceSuppression);
	
			 aaronAgenceRepository.save(agenceSuppression);
					
			model.addAttribute("codeAgence", codeAgenceSuppression);
			model.addAttribute("resultatSuppressionAgence", "resultatSuppressionAgence");	
			model.addAttribute("actionTroisBouton", "actionTroisBouton");
	
			Page<AaronAgence> agences =aaronAgenceRepository.findAllAgencePage(pageable);
			model.addAttribute("agences", agences);
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("listeAgence", "listeAgence");
			model.addAttribute("gestionAgence", "gestionAgence");
			model.addAttribute("menuNavigation", "menuNavigation");			
	        return "aaron/espaceUtilisateur";	
	    }
	
	    @Transactional
	 	@RequestMapping(value = {"/aaronResultatModifDonneeAgence" }, method = RequestMethod.POST)
	    public String resultatModifDonneeAgence(Model model, @ModelAttribute("agence") Agence agence, HttpSession session, @PageableDefault(size = 100) Pageable pageable, HttpServletRequest request) {
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
			model.addAttribute("cheminGestionAgence", "Gestion Agence >");
			model.addAttribute("cheminSupprimerAgence", "Supprimer Agence >");
			String codeDirect=agence.getCodeDirect().trim();
			session.removeAttribute("codeAgenceCache");
			session.setAttribute("codeAgenceCache",codeDirect);						
			model.addAttribute("dialog_boxAgence", "dialog_boxAgence");	
			model.addAttribute("dialog_backgroundAgence", "dialog_backgroundAgence");				
		
			model.addAttribute("codeAgence", codeDirect);	
			model.addAttribute("actionTroisBouton", "actionTroisBouton");
		
			Page<AaronAgence> agences =aaronAgenceRepository.findAllAgencePage(pageable);	
			model.addAttribute("agences", agences);
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("listeAgence", "listeAgence");
			model.addAttribute("gestionAgence", "gestionAgence");
			model.addAttribute("menuNavigation", "menuNavigation");			
	        return "aaron/espaceUtilisateur";	
	    }
	 
	    @Transactional
	 	@RequestMapping(value = {"/aaronEnvoiDonneeCacheeAgence" }, method = RequestMethod.POST)
	    public String envoiDonneeCacheeAgence(Model model, @ModelAttribute("aaronAgence") AaronAgence aaronAgence, HttpSession session, @PageableDefault(size = 100) Pageable pageable, HttpServletRequest request) {
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
			//gestion Menu 
			model.addAttribute("gestionMenuUtilisateur", "gestionMenuUtilisateur");
			model.addAttribute("gestionMenuBanque", "gestionMenuBanque");
			model.addAttribute("gestionMenuGuichet", "gestionMenuGuichet");
			model.addAttribute("gestionMenuAgence", "gestionMenuAgence");
			model.addAttribute("gestionMenuAgent", "gestionMenuAgent");
			model.addAttribute("gestionMenuSociete", "gestionMenuSociete");
			model.addAttribute("accueilDeux", "accueilDeux");
			model.addAttribute("gestionMenuAgenceBanque", "gestionMenuAgenceBanque");
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionAgence", "Gestion Agence >");
			model.addAttribute("cheminModifierAgence", "Modifier Agence >");
			session.removeAttribute("codeAgenceCache");
			String codeDirect=aaronAgence.getCodeDirect();
			AaronAgence agenceRecherche=aaronAgenceRepository.findAgenceByCodeDirect(codeDirect);
			
			Page<AaronAgence> agences =aaronAgenceRepository.findAllAgencePage(pageable);
			model.addAttribute("agences", agences);
			session.setAttribute("codeAgenceCache", codeDirect);	
			model.addAttribute("formulaireGestionModifAgence", "formulaireGestionModifAgence");
			model.addAttribute("actionTroisBouton", "actionTroisBouton");	
			model.addAttribute("agenceRecherche", agenceRecherche);
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("listeAgence", "listeAgence");
			model.addAttribute("gestionAgence", "gestionAgence");
			model.addAttribute("menuNavigation", "menuNavigation");			
	        return "aaron/espaceUtilisateur";	
	    }
	    
	    @Transactional
		@RequestMapping(value = "/aaronListeAgences")
	    public ModelAndView listeUtilisateursPageByPage(@RequestParam(name="page", defaultValue="0") int page,@RequestParam(name="size", defaultValue="100") int size,  @ModelAttribute("aaronAgence")  AaronAgence aaronAgence, Model model, HttpSession session, HttpServletRequest request) {
	        ModelAndView modelAndView = new ModelAndView("aaron/espaceUtilisateur");
	       
	        PageRequest pageable = PageRequest.of(page-1, size);
	        Page<AaronAgence> agences =aaronAgenceRepository.findAllAgencePage(pageable);

			model.addAttribute("agences", agences);
					
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
			model.addAttribute("cheminGestionAgence", "Gestion Agence >");
			model.addAttribute("cheminModifierAgence", "Modifier Agence >");
			
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("listeAgence", "listeAgence");
			model.addAttribute("gestionAgence", "gestionAgence");
			model.addAttribute("menuNavigation", "menuNavigation");		
						
	        return modelAndView;
		}	
	 
	 
	

}
