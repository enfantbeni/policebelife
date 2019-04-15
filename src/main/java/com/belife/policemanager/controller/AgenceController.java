package com.belife.policemanager.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.belife.policemanager.model.entity.Agence;
import com.belife.policemanager.model.repository.AgenceRepository;
import com.belife.policemanager.model.repository.BanqueRepository;
import com.belife.policemanager.model.repository.RolesRepository;
import com.belife.policemanager.model.repository.SourcePoliceRepository;
import com.belife.policemanager.model.repository.UtilisateurRepository;

@Controller
public class AgenceController {
	
	
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
	 @RequestMapping(value = {"/gestionAgence" }, method = RequestMethod.GET)
	    public String gestionAgence(Model model, HttpSession session) { 
		 
		 String resultat=null;
			try {
				identifiantSession=session.getAttribute("identifiantSession").toString().trim();
			}
			catch(Exception e) {
				resultat="pageErreur";
				return resultat;
			}
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionAgence", "Gestion Agence >");
			model.addAttribute("titre", "Gestion des Agences");
			Boolean estSupprimer=false;
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("listeAgence", "listeAgence");
			model.addAttribute("gestionAgence", "gestionAgence");
			model.addAttribute("menuNavigation", "menuNavigation");
			List<Agence> agences=new ArrayList<Agence>();		
			agences=agenceRepository.findAllAgences(estSupprimer);
			model.addAttribute("agences", agences);
	        return "espaceUtilisateur";			
	    }
	 @Transactional
	 @RequestMapping(value = {"/ajoutAgence" }, method = RequestMethod.GET)
	    public String ajoutAgence(Model model, HttpSession session) { 
		 
		 String resultat=null;
			try {
				identifiantSession=session.getAttribute("identifiantSession").toString().trim();
			}
			catch(Exception e) {
				resultat="pageErreur";
				return resultat;
			}
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionAgence", "Gestion Agence >");
			model.addAttribute("cheminAjouterAgence", "Ajouter une Agence >");
			model.addAttribute("titre", "Gestion des Agences");
			Boolean estSupprimer=false;
			List<Agence> agences=new ArrayList<Agence>();
			agences=agenceRepository.findAllAgences(estSupprimer);
			model.addAttribute("agences", agences);
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("formulaireGestionAgence", "formulaireGestionAgence");
			model.addAttribute("listeAgence", "listeAgence");
			model.addAttribute("gestionAgence", "gestionAgence");
			model.addAttribute("menuNavigation", "menuNavigation");
	        return "espaceUtilisateur";			
	    }
	 
	 @Transactional
	 @RequestMapping(value = {"/resultatAjoutAgence" }, method = RequestMethod.POST)
	    public String resultatAjoutAgence(Model model, @ModelAttribute("agence") Agence agence , HttpSession session) { 		 
		 String resultat=null;
			try {
				identifiantSession=session.getAttribute("identifiantSession").toString().trim();
			}
			catch(Exception e) {
				resultat="pageErreur";
				return resultat;
			}
		    
			String codeAgence=agence.getCodeAgence().trim();
			String codeDirect=agence.getCodeDirect().trim();
			String nomDirect=agence.getNomDirect().trim();
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionAgence", "Gestion Agence >");
			model.addAttribute("cheminAjouterAgence", "Ajouter une Agence >");
			model.addAttribute("titre", "Gestion des Agences");
			
			List<Agence> agences=new ArrayList<Agence>();
			Boolean estSupprimer=false;
			Agence agenceSave=null;
			Agence nomDirectSave=null;
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("gestionAgence", "gestionAgence");
			model.addAttribute("menuNavigation", "menuNavigation");
			if( codeAgence != null && codeAgence.length() > 0 && codeAgence.length()<=5 && codeDirect != null && codeDirect.length() > 0 && codeDirect.length()<=5 && nomDirect != null && nomDirect.length() > 0 && nomDirect.length()<=50 ) {						
					agenceSave=agenceRepository.findAgenceByCodeDirect(codeDirect);
					nomDirectSave=agenceRepository.findAgenceByNomDirect(nomDirect);
//					nomAgenceVerification=agenceSave.getNomAgence();
					if(agenceSave==null && nomDirectSave ==null) {
							agence.setCodeAgence(codeAgence);
							agence.setCodeDirect(codeDirect);
							agence.setNomDirect(nomDirect);
							agence.setEstSupprimer(estSupprimer);
							
							model.addAttribute("listeAgence", "listeAgence");
							model.addAttribute("gestionAgence", "gestionAgence");
							agenceRepository.save(agence);
							model.addAttribute("ajoutSuccesAgence", "Un Direct ajouté avec succès");	
							agences=agenceRepository.findAllAgences(estSupprimer);
							model.addAttribute("agences", agences);					
								return "espaceUtilisateur";
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
			
			agences=agenceRepository.findAllAgences(estSupprimer);
			model.addAttribute("agences", agences);
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("resultatAjoutAgence", "resultatAjoutAgence");
			model.addAttribute("formulaireGestionAgence", "formulaireGestionAgence");
			model.addAttribute("listeAgence", "listeAgence");
			model.addAttribute("gestionAgence", "gestionAgence");
			model.addAttribute("menuNavigation", "menuNavigation");
	        return "espaceUtilisateur";			
	 }
	 
	 @Transactional
	 @RequestMapping(value = {"/modifierAgence" }, method = RequestMethod.GET)
	    public String modifierAgence(Model model, HttpSession session) { 
			String resultat=null;
			try {
				identifiantSession=session.getAttribute("identifiantSession").toString().trim();
			}
			catch(Exception e) {
				resultat="pageErreur";
				return resultat;
			}	
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionAgence", "Gestion Agence >");
			model.addAttribute("cheminModifierAgence", "Modifier une Agence >");
			model.addAttribute("titre", "Gestion des Agences");
			Boolean estSupprimer=false;
			List<Agence> agences=new ArrayList<Agence>();
			agences=agenceRepository.findAllAgences(estSupprimer);
			model.addAttribute("agences", agences);
			model.addAttribute("formulaireNumeroModifAgence", "formulaireNumeroModifAgence");
			model.addAttribute("actionTroisBouton", "actionTroisBouton");	
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("listeAgence", "listeAgence");
			model.addAttribute("gestionAgence", "gestionAgence");
			model.addAttribute("menuNavigation", "menuNavigation");
	        return "espaceUtilisateur";	
	    }
	 
	 @Transactional
	 @RequestMapping(value = {"/formulaireNumeroModifAgence" }, method = RequestMethod.POST)
	    public String formulaireNumeroModifAgence(Model model, @ModelAttribute("agence") Agence agence, HttpSession sessionUtilisateur, HttpSession session) {
			String resultat=null;
			try {
				identifiantSession=session.getAttribute("identifiantSession").toString().trim();
			}
			catch(Exception e) {
				resultat="pageErreur";
				return resultat;
			}
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionAgence", "Gestion Agence >");
			model.addAttribute("cheminModifierAgence", "Modifier une Agence >");
			model.addAttribute("titre", "Gestion des Agences");
			String codeDirect=agence.getCodeDirect().trim();
			Agence agenceRecherche=agenceRepository.findAgenceByCodeDirect(codeDirect);				
			
			if( agenceRecherche == null) {
				 return "redirect:/messageAgenceNonExistante";  
			}							
			Boolean estSupprimer=false;
			List<Agence> agences=new ArrayList<Agence>();
			agences=agenceRepository.findAllAgences(estSupprimer);
			model.addAttribute("agences", agences);
			model.addAttribute("titre", " Gestion d'Agence");
			session.setAttribute("codeAgenceCache", codeDirect);	
			model.addAttribute("formulaireGestionModifAgence", "formulaireGestionModifAgence");
			model.addAttribute("actionTroisBouton", "actionTroisBouton");	
			model.addAttribute("agenceRecherche", agenceRecherche);
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("listeAgence", "listeAgence");
			model.addAttribute("gestionAgence", "gestionAgence");
			model.addAttribute("menuNavigation", "menuNavigation");			
	        return "espaceUtilisateur";	
	    }

	 
//	 Resultat de la modification des données d'un formulaire
	 @Transactional
	 @RequestMapping(value = {"/resultatModifAgence" }, method = RequestMethod.POST)
	    public String resultatModifAgence(Model model, @ModelAttribute("agence") Agence agence, HttpSession sessionUtilisateur, HttpSession session) {
			String resultat=null;
			try {
				identifiantSession=session.getAttribute("identifiantSession").toString().trim();
			}
			catch(Exception e) {
				resultat="pageErreur";
				return resultat;
			}
			String codeAgence=agence.getCodeAgence().trim();
			String codeDirect=agence.getCodeDirect().trim();
			String nomDirect=agence.getNomDirect().trim();
					
			List<Agence> agences=new ArrayList<Agence>();
			Boolean estSupprimer=false;			
			String codeAgenceCache=session.getAttribute("codeAgenceCache").toString().trim();	
			
			Agence agenceRecherche=agenceRepository.findAgenceByCodeDirect(codeAgenceCache);
			
			Agence codeDirectRecherche=agenceRepository.findAgenceByCodeDirect(codeDirect);
			Agence nomDirectSave=agenceRepository.findAgenceByNomDirect(nomDirect);
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionAgence", "Gestion Agence >");
			model.addAttribute("cheminModifierAgence", "Modifier une Agence >");
			model.addAttribute("titre", "Gestion des Agences");
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
				
				agences=agenceRepository.findAllAgences(estSupprimer);
				model.addAttribute("agences", agences);				
				return "espaceUtilisateur";	
			}
			
			
			Integer idAgenceOmission=agenceRecherche.getIdAgence();
			List<Agence> agencesOmission=agenceRepository.findAllAgencesOmission(idAgenceOmission);
			Boolean estEgal=false;
			for(Agence ag:agencesOmission) {
				
				if(ag.getCodeDirect().equals(codeDirect)) {
					estEgal=true;
					model.addAttribute("codeDirectExistantErreur", "Code Direct déjà existant");
				}
				if(ag.getNomDirect().equals(nomDirect)) {
					estEgal=true;
					model.addAttribute("nomDirectExistantErreur", "Nom direct déjà existant");
				}
				if(estEgal) {
					agences=agenceRepository.findAllAgences(estSupprimer);
					model.addAttribute("agences", agences);	
					model.addAttribute("agenceRecherche", agenceRecherche);
					model.addAttribute("formErreurAgence", "formErreur");
					model.addAttribute("identifiantSession", identifiantSession);
					model.addAttribute("actionTroisBouton", "actionTroisBouton");	
					model.addAttribute("formulaireGestionModifAgence", "formulaireGestionModifAgence");
					model.addAttribute("listeAgence", "listeAgence");
					model.addAttribute("gestionAgence", "gestionAgence");
					model.addAttribute("menuNavigation", "menuNavigation");	
					return "espaceUtilisateur";	
				}
			}
			
			
			
			 agenceRecherche.setNomDirect(nomDirect);
			 agenceRecherche.setCodeAgence(codeAgence);
			 agenceRecherche.setCodeDirect(codeDirect);
			 agenceRecherche.setEstSupprimer(estSupprimer);
			agenceRepository.save(agenceRecherche);
			agences=agenceRepository.findAllAgences(estSupprimer);
			model.addAttribute("agenceRecherche", agenceRecherche);
			model.addAttribute("agences", agences);	
			model.addAttribute("listeAgence", "listeAgence");
			model.addAttribute("actionTroisBouton", "actionTroisBouton");	
			model.addAttribute("resultatModifAgence", "resultatModifAgence");
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("gestionAgence", "gestionAgence");
			model.addAttribute("menuNavigation", "menuNavigation");			
	        return "espaceUtilisateur";	
	    }
	 
	 
////	  Action Recherche Banque
	 @Transactional
	 @RequestMapping(value = {"/rechercheAgence" }, method = RequestMethod.GET)
	    public String rechercherAgence(Model model, HttpSession session) { 
			String resultat=null;
			try {
				identifiantSession=session.getAttribute("identifiantSession").toString().trim();
			}
			catch(Exception e) {
				resultat="pageErreur";
				return resultat;
			}	
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionAgence", "Gestion Agence >");
			model.addAttribute("cheminRechercherAgence", "Rechercher une Agence >");
			model.addAttribute("titre", "Gestion des Agences");
			Boolean estSupprimer=false;
			List<Agence> agences=new ArrayList<Agence>();
			agences=agenceRepository.findAllAgences(estSupprimer);
			model.addAttribute("agences", agences);
			model.addAttribute("formulaireNumeroRechercheAgence", "formulaireNumeroRechercheAgence");
			model.addAttribute("actionTroisBouton", "actionTroisBouton");	
			model.addAttribute("identifiantSession", identifiantSession);  
			model.addAttribute("listeAgence", "listeAgence");
			model.addAttribute("gestionAgence", "gestionAgence");
			model.addAttribute("menuNavigation", "menuNavigation");
	        return "espaceUtilisateur";		        
	    }
	 
	 @Transactional
	 @RequestMapping(value = {"/resultatRechercheAgence" }, method = RequestMethod.POST)
	    public String resultatRechercheAgence(Model model, @ModelAttribute("agence") Agence agence, HttpSession sessionUtilisateur, HttpSession session) {
			String resultat=null;
			try {
				identifiantSession=session.getAttribute("identifiantSession").toString().trim();
			}
			catch(Exception e) {
				resultat="pageErreur";
				return resultat;
			}
			String codeDirect=agence.getCodeDirect().trim();
			
			session.setAttribute("codeAgenceCache", codeDirect);
			Agence agenceRecherche=agenceRepository.findAgenceByCodeDirect(codeDirect);
			if( agenceRecherche == null) {				
				 return "redirect:/messageAgenceNonExistante";  
			}	
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionAgence", "Gestion Agence >");
			model.addAttribute("cheminRechercherAgence", "Rechercher une Agence >");
			model.addAttribute("titre", "Gestion des Agences");
			Boolean estSupprimer=false;
			List<Agence> agences=new ArrayList<Agence>();
			agences=agenceRepository.findAllAgences(estSupprimer);
			model.addAttribute("agenceRecherche", agenceRecherche);			
			model.addAttribute("resultatRechercheAgence", "resultatRechercheAgence");	
			model.addAttribute("actionTroisBouton", "actionTroisBouton");	
			model.addAttribute("agenceRecherche", agenceRecherche);
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("gestionAgence", "gestionAgence");
			model.addAttribute("menuNavigation", "menuNavigation");		
	        return "espaceUtilisateur";	
	    }
	 
	 @Transactional
	 @RequestMapping(value = {"/messageAgenceNonExistante" }, method = RequestMethod.GET)
	    public String messageAgenceNonExistante(Model model, @ModelAttribute("agence") Agence agence, HttpSession sessionUtilisateur, HttpSession session) {
			String resultat=null;
			try {
				identifiantSession=session.getAttribute("identifiantSession").toString().trim();
			}
			catch(Exception e) {
				resultat="pageErreur";
				return resultat;
			}
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionAgence", "Gestion Agence >");
			model.addAttribute("cheminRechercherAgence", " Agence non existante >");
			model.addAttribute("titre", "Gestion des Agences");
			String codeAgenceCache=session.getAttribute("codeAgenceCache").toString().trim();
			model.addAttribute("codeAgence", codeAgenceCache);	
			model.addAttribute("messageAgenceNonExistante", "messageAgenceNonExistante");		
			model.addAttribute("actionTroisBouton", "actionTroisBouton");	
			Boolean estSupprimer=false;
			List<Agence> agences=new ArrayList<Agence>();
			agences=agenceRepository.findAllAgences(estSupprimer);
			model.addAttribute("agences", agences);
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("listeAgence", "listeAgence");
			model.addAttribute("gestionAgence", "gestionAgence");
			model.addAttribute("menuNavigation", "menuNavigation");		
	        return "espaceUtilisateur";	
	    }
	 	
////	 Action suppression Agence	
	 @Transactional
	 @RequestMapping(value = {"/supprimerAgence" }, method = RequestMethod.GET)
	    public String supprimerAgence(Model model, HttpSession session) { 
			String resultat=null;
			try {
				identifiantSession=session.getAttribute("identifiantSession").toString().trim();
			}
			catch(Exception e) {
				resultat="pageErreur";
				return resultat;
			}	
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionAgence", "Gestion Agence >");
			model.addAttribute("cheminSupprimerAgence", "Supprimer Agence >");
			Boolean estSupprimer=false;
			List<Agence> agences=new ArrayList<Agence>();
			agences=agenceRepository.findAllAgences(estSupprimer);
			model.addAttribute("agences", agences);
			model.addAttribute("formulaireNumeroSupprimerAgence", "formulaireNumeroSupprimerAgence");
			model.addAttribute("actionTroisBouton", "actionTroisBouton");	
			model.addAttribute("identifiantSession", identifiantSession);  
			model.addAttribute("listeAgence", "listeAgence");
			model.addAttribute("gestionAgence", "gestionAgence");
			model.addAttribute("menuNavigation", "menuNavigation");
	        return "espaceUtilisateur";	        
	    }
	 
	 @Transactional
	 @RequestMapping(value = {"/resultatSupprimerAgence" }, method = RequestMethod.POST)
	    public String resultatSuppressionAgence(Model model, @ModelAttribute("agence") Agence agence, HttpSession sessionUtilisateur, HttpSession session) {
			String resultat=null;
			try {
				identifiantSession=session.getAttribute("identifiantSession").toString().trim();
			}
			catch(Exception e) {
				resultat="pageErreur";
				return resultat;
			}
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionAgence", "Gestion Agence >");
			model.addAttribute("cheminSupprimerAgence", "Supprimer Agence >");
			String codeDirect=agence.getCodeDirect().trim();
			session.setAttribute("codeAgenceCache", codeDirect);
			Agence agenceRecherche=agenceRepository.findAgenceByCodeDirect(codeDirect);
			if( agenceRecherche == null) {				
				 return "redirect:/messageAgenceNonExistante";  
			}								
			Boolean estSupprimer=false;
			List<Agence> agences=new ArrayList<Agence>();
			agences=agenceRepository.findAllAgences(estSupprimer);
			model.addAttribute("agenceRecherche", agenceRecherche);			
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
	        return "espaceUtilisateur";	
	    }
	 
	 @Transactional
	 @RequestMapping(value = {"/succesSuppressionAgence" }, method = RequestMethod.POST)
	    public String succesSuppressionAgence(Model model, @ModelAttribute("agence") Agence agence, HttpSession sessionUtilisateur, HttpSession session) {
			String resultat=null;
			try {
				identifiantSession=session.getAttribute("identifiantSession").toString().trim();
			}
			catch(Exception e) {
				resultat="pageErreur";
				return resultat;
			}
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionAgence", "Gestion Agence >");
			model.addAttribute("cheminSupprimerAgence", "Supprimer Agence >");
			String codeAgenceSuppression=session.getAttribute("codeAgenceCache").toString().trim();
			Agence agenceSuppression=agenceRepository.findAgenceByCodeDirect(codeAgenceSuppression);
			agenceSuppression.setEstSupprimer(true);
			agenceRepository.save(agenceSuppression);
			Boolean estSupprimer=false;				
			model.addAttribute("codeAgence", codeAgenceSuppression);
			model.addAttribute("resultatSuppressionAgence", "resultatSuppressionAgence");	
			model.addAttribute("actionTroisBouton", "actionTroisBouton");
			List<Agence> agences=new ArrayList<Agence>();
			agences=agenceRepository.findAllAgences(estSupprimer);	
			model.addAttribute("agences", agences);
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("listeAgence", "listeAgence");
			model.addAttribute("gestionAgence", "gestionAgence");
			model.addAttribute("menuNavigation", "menuNavigation");			
	        return "espaceUtilisateur";	
	    }
	
	    @Transactional
	 	@RequestMapping(value = {"/resultatModifDonneeAgence" }, method = RequestMethod.POST)
	    public String resultatModifDonneeAgence(Model model, @ModelAttribute("agence") Agence agence, HttpSession sessionUtilisateur, HttpSession session) {
			String resultat=null;
			try {
				identifiantSession=session.getAttribute("identifiantSession").toString().trim();
			}
			catch(Exception e) {
				resultat="pageErreur";
				return resultat;
			}		
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionAgence", "Gestion Agence >");
			model.addAttribute("cheminSupprimerAgence", "Supprimer Agence >");
			String codeDirect=agence.getCodeDirect().trim();
			session.removeAttribute("codeAgenceCache");
			session.setAttribute("codeAgenceCache",codeDirect);						
			model.addAttribute("dialog_boxAgence", "dialog_boxAgence");	
			model.addAttribute("dialog_backgroundAgence", "dialog_backgroundAgence");				
			Boolean estSupprimer=false;	
			model.addAttribute("codeAgence", codeDirect);	
			model.addAttribute("actionTroisBouton", "actionTroisBouton");
			List<Agence> agences=new ArrayList<Agence>();
			agences=agenceRepository.findAllAgences(estSupprimer);	
			model.addAttribute("agences", agences);
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("listeAgence", "listeAgence");
			model.addAttribute("gestionAgence", "gestionAgence");
			model.addAttribute("menuNavigation", "menuNavigation");			
	        return "espaceUtilisateur";	
	    }
	 
	    @Transactional
	 	@RequestMapping(value = {"/envoiDonneeCacheeAgence" }, method = RequestMethod.POST)
	    public String envoiDonneeCacheeAgence(Model model, @ModelAttribute("agence") Agence agence, HttpSession sessionUtilisateur, HttpSession session) {
			String resultat=null;
			try {
				identifiantSession=session.getAttribute("identifiantSession").toString().trim();
			}
			catch(Exception e) {
				resultat="pageErreur";
				return resultat;
			}		
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionAgence", "Gestion Agence >");
			model.addAttribute("cheminModifierAgence", "Modifier Agence >");
			session.removeAttribute("codeAgenceCache");
			String codeDirect=agence.getCodeDirect();
			Agence agenceRecherche=agenceRepository.findAgenceByCodeDirect(codeDirect);
			Boolean estSupprimer=false;
			List<Agence> agences=new ArrayList<Agence>();
			agences=agenceRepository.findAllAgences(estSupprimer);
			model.addAttribute("agences", agences);
			session.setAttribute("codeAgenceCache", codeDirect);	
			model.addAttribute("formulaireGestionModifAgence", "formulaireGestionModifAgence");
			model.addAttribute("actionTroisBouton", "actionTroisBouton");	
			model.addAttribute("agenceRecherche", agenceRecherche);
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("listeAgence", "listeAgence");
			model.addAttribute("gestionAgence", "gestionAgence");
			model.addAttribute("menuNavigation", "menuNavigation");			
	        return "espaceUtilisateur";	
	    }
	 
	 
	
	
	

}
