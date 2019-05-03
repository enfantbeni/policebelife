package com.belife.policemanager.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import com.belife.policemanager.model.entity.Banque;
import com.belife.policemanager.model.entity.Societe;
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
	 
	 String identifiantSession=null;
	 
	 @Transactional
	 @RequestMapping(value = {"/gestionSociete" }, method = RequestMethod.GET)
	    public String gestionSociete(Model model, HttpSession session) { 
		 
		 String resultat=null;
			try {
				identifiantSession=session.getAttribute("identifiantSession").toString().trim();
			}
			catch(Exception e) {
				resultat="pageErreur";
				return resultat;
			}
//			gestion Menu 
			model.addAttribute("gestionMenuUtilisateur", "gestionMenuUtilisateur");
			model.addAttribute("gestionMenuBanque", "gestionMenuBanque");
			model.addAttribute("gestionMenuGuichet", "gestionMenuGuichet");
			model.addAttribute("gestionMenuAgence", "gestionMenuAgence");
			model.addAttribute("gestionMenuAgent", "gestionMenuAgent");
			model.addAttribute("gestionMenuSociete", "gestionMenuSociete");
			model.addAttribute("accueilDeux", "accueilDeux");
			
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionSociete", "Gestion Sociéte >");
			model.addAttribute("titre", "Gestion des Sociétés");
			Boolean estSupprimer=false;
			model.addAttribute("identifiantSession", identifiantSession);
			List<Societe> societes=new ArrayList<Societe>();		
			societes=societeRepository.findAllSocietes(estSupprimer);
			model.addAttribute("societes", societes);
			model.addAttribute("listeSociete", "listeSociete");
			model.addAttribute("titre", " Gestion de Société");
			model.addAttribute("gestionSociete", "gestionSociete");
			model.addAttribute("menuNavigation", "menuNavigation");
	        return "espaceUtilisateur";			
	    }
	   
	 @Transactional
	 @RequestMapping(value = {"/ajoutSociete" }, method = RequestMethod.GET)
	    public String ajoutSociete(Model model, HttpSession session) { 
		 
		 String resultat=null;
			try {
				identifiantSession=session.getAttribute("identifiantSession").toString().trim();
			}
			catch(Exception e) {
				resultat="pageErreur";
				return resultat;
			}
			//	gestion Menu 
			model.addAttribute("gestionMenuUtilisateur", "gestionMenuUtilisateur");
			model.addAttribute("gestionMenuBanque", "gestionMenuBanque");
			model.addAttribute("gestionMenuGuichet", "gestionMenuGuichet");
			model.addAttribute("gestionMenuAgence", "gestionMenuAgence");
			model.addAttribute("gestionMenuAgent", "gestionMenuAgent");
			model.addAttribute("gestionMenuSociete", "gestionMenuSociete");
			model.addAttribute("accueilDeux", "accueilDeux");
			
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionSociete", "Gestion Sociéte >");
			model.addAttribute("cheminAjouterSociete", "Ajouter une Société >");
			model.addAttribute("titre", "Gestion des Sociétés");
			Boolean estSupprimer=false;
			List<Societe> societes=new ArrayList<Societe>();
			societes=societeRepository.findAllSocietes(estSupprimer);
			model.addAttribute("societes", societes);
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("formulaireGestionSociete", "formulaireGestionSociete");
			model.addAttribute("listeSociete", "listeSociete");
			model.addAttribute("titre", " Gestion de Société");
			model.addAttribute("gestionSociete", "gestionSociete");
			model.addAttribute("menuNavigation", "menuNavigation");
	        return "espaceUtilisateur";			
	    }
	 
	 @Transactional
	 @RequestMapping(value = {"/resultatAjoutSociete" }, method = RequestMethod.POST)
	    public String resultatAjoutBanque(Model model, @ModelAttribute("societe") Societe societe , HttpSession session) { 		 
		 String resultat=null;
			try {
				identifiantSession=session.getAttribute("identifiantSession").toString().trim();
			}
			catch(Exception e) {
				resultat="pageErreur";
				return resultat;
			}
//			gestion Menu 
			model.addAttribute("gestionMenuUtilisateur", "gestionMenuUtilisateur");
			model.addAttribute("gestionMenuBanque", "gestionMenuBanque");
			model.addAttribute("gestionMenuGuichet", "gestionMenuGuichet");
			model.addAttribute("gestionMenuAgence", "gestionMenuAgence");
			model.addAttribute("gestionMenuAgent", "gestionMenuAgent");
			model.addAttribute("gestionMenuSociete", "gestionMenuSociete");
			model.addAttribute("accueilDeux", "accueilDeux");
			
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionSociete", "Gestion Sociéte >");
			model.addAttribute("cheminAjouterSociete", "Ajouter une Société >");
			model.addAttribute("titre", "Gestion des Sociétés");
			String nomSociete=societe.getNomSociete().trim();
			String codeSociete=societe.getCodeSociete().trim();
			List<Societe> societes=new ArrayList<Societe>();
			Boolean estSupprimer=false;
			Societe societeSave=null;
			Societe societeNomSave=null;
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("menuNavigation", "menuNavigation");
			model.addAttribute("titre", " Gestion de Société");
			if( nomSociete != null &&nomSociete.length() > 0 && nomSociete.length()<=50 && codeSociete != null && codeSociete.length() > 0 && codeSociete.length()<=5 ) {						
				societeSave=societeRepository.findSocieteByCode(codeSociete);
				societeNomSave=societeRepository.findSocieteByCode(codeSociete);
					if(societeSave == null && societeNomSave==null ) {					
						societe.setCodeSociete(codeSociete);
						societe.setNomSociete(nomSociete);
						societe.setEstSupprimer(estSupprimer);
						
						model.addAttribute("listeSociete", "listeSociete");
						model.addAttribute("gestionSociete", "gestionSociete");
						DateFormat df = new SimpleDateFormat("dd/MM/yy");
//						Date date =new Date();						
//						DateFormat dateFormat= new SimpleDateFormat("dd-MM-yyyy");
//						String dateCreation=dateFormat.format(date);						
						societe.setDateCreation(new Date());
						societeRepository.save(societe);
						model.addAttribute("ajoutSuccesSociete", "Une société ajoutée avec succès");	
						societes=societeRepository.findAllSocietes(estSupprimer);
						model.addAttribute("societes", societes);					
							return "espaceUtilisateur";
					}	
					model.addAttribute("codeSocieteErreur", " Code Societe déjà existant");	
					model.addAttribute("nomSocieteErreur", " Nom Societe déjà existante");	
			}	 
			model.addAttribute("formErreurSociete", "formErreur");
			if(nomSociete==null || nomSociete.length()==0 || nomSociete.length()>50) {
				model.addAttribute("nomSocieteErreur", "Nom de la Societe invalide");
			}
			if(codeSociete==null || codeSociete.length()==0 || codeSociete.length()>50) {
				model.addAttribute("codeSocieteErreur", " Code Société invalide");
			}
			societes=societeRepository.findAllSocietes(estSupprimer);
			model.addAttribute("societes", societes);
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("resultatAjoutSociete", "resultatAjoutSociete");
			model.addAttribute("formulaireGestionSociete", "formulaireGestionSociete");
			model.addAttribute("listeSociete", "listeSociete");
			model.addAttribute("gestionSociete", "gestionSociete");
			model.addAttribute("menuNavigation", "menuNavigation");
	        return "espaceUtilisateur";			
	 }
	 
	 @Transactional
	 @RequestMapping(value = {"/modifierSociete" }, method = RequestMethod.GET)
	    public String modifierSociete(Model model, HttpSession session) { 
			String resultat=null;
			try {
				identifiantSession=session.getAttribute("identifiantSession").toString().trim();
			}
			catch(Exception e) {
				resultat="pageErreur";
				return resultat;
			}	
//			gestion Menu 
			model.addAttribute("gestionMenuUtilisateur", "gestionMenuUtilisateur");
			model.addAttribute("gestionMenuBanque", "gestionMenuBanque");
			model.addAttribute("gestionMenuGuichet", "gestionMenuGuichet");
			model.addAttribute("gestionMenuAgence", "gestionMenuAgence");
			model.addAttribute("gestionMenuAgent", "gestionMenuAgent");
			model.addAttribute("gestionMenuSociete", "gestionMenuSociete");
			model.addAttribute("accueilDeux", "accueilDeux");
			
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionSociete", "Gestion Sociéte >");
			model.addAttribute("cheminModifierSociete", "Modifier une Société >");
			model.addAttribute("titre", "Gestion des Sociétés");
			Boolean estSupprimer=false;
			List<Societe> societes=new ArrayList<Societe>();
			societes=societeRepository.findAllSocietes(estSupprimer);
			model.addAttribute("societes", societes);
			model.addAttribute("titre", " Gestion de Société");
			model.addAttribute("formulaireNumeroModifSociete", "formulaireNumeroModifSociete");
			model.addAttribute("actionQuatreBouton", "actionQuatreBouton");	
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("listeSociete", "listeSociete");
			model.addAttribute("gestionSociete", "gestionSociete");
			model.addAttribute("menuNavigation", "menuNavigation");
	        return "espaceUtilisateur";	
	    }
	 
	 @Transactional
	 @RequestMapping(value = {"/formulaireNumeroModifSociete" }, method = RequestMethod.POST)
	    public String formulaireNumeroModifSociete(Model model, @ModelAttribute("societe") Societe societe, HttpSession sessionUtilisateur, HttpSession session) {
			String resultat=null;
			try {
				identifiantSession=session.getAttribute("identifiantSession").toString().trim();
			}
			catch(Exception e) {
				resultat="pageErreur";
				return resultat;
			}
//			gestion Menu 
			model.addAttribute("gestionMenuUtilisateur", "gestionMenuUtilisateur");
			model.addAttribute("gestionMenuBanque", "gestionMenuBanque");
			model.addAttribute("gestionMenuGuichet", "gestionMenuGuichet");
			model.addAttribute("gestionMenuAgence", "gestionMenuAgence");
			model.addAttribute("gestionMenuAgent", "gestionMenuAgent");
			model.addAttribute("gestionMenuSociete", "gestionMenuSociete");
			model.addAttribute("accueilDeux", "accueilDeux");
			
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionSociete", "Gestion Sociéte >");
			model.addAttribute("cheminModifierSociete", "Modifier une Société >");
			model.addAttribute("titre", "Gestion des Sociétés");
			String codeSociete=societe.getCodeSociete().trim();
			Societe societeRecherche=societeRepository.findSocieteByCode(codeSociete);				
			
			if( societeRecherche == null) {
				 return "redirect:/messageSocieteNonExistante";  
			}							
			Boolean estSupprimer=false;
			List<Societe> societes=new ArrayList<Societe>();
			societes=societeRepository.findAllSocietes(estSupprimer);
			model.addAttribute("societes", societes);
			model.addAttribute("titre", " Gestion de Société");
			session.setAttribute("codeSocieteCache", codeSociete);	
			model.addAttribute("formulaireGestionModifSociete", "formulaireGestionModifSociete");
			model.addAttribute("actionQuatreBouton", "actionQuatreBouton");	
			model.addAttribute("societeRecherche", societeRecherche);
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("listeSociete", "listeSociete");
			model.addAttribute("gestionSociete", "gestionSociete");
			model.addAttribute("menuNavigation", "menuNavigation");			
	        return "espaceUtilisateur";	
	    }
	 
//	 Resultat de la modification des données d'un formulaire
	 @Transactional
	 @RequestMapping(value = {"/resultatModifSociete" }, method = RequestMethod.POST)
	    public String resultatModifSociete(Model model, @ModelAttribute("societe") Societe societe, HttpSession sessionUtilisateur, HttpSession session) {
			String resultat=null;
			try {
				identifiantSession=session.getAttribute("identifiantSession").toString().trim();
			}
			catch(Exception e) {
				resultat="pageErreur";
				return resultat;
			}
//			gestion Menu 
			model.addAttribute("gestionMenuUtilisateur", "gestionMenuUtilisateur");
			model.addAttribute("gestionMenuBanque", "gestionMenuBanque");
			model.addAttribute("gestionMenuGuichet", "gestionMenuGuichet");
			model.addAttribute("gestionMenuAgence", "gestionMenuAgence");
			model.addAttribute("gestionMenuAgent", "gestionMenuAgent");
			model.addAttribute("gestionMenuSociete", "gestionMenuSociete");
			model.addAttribute("accueilDeux", "accueilDeux");
			
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionSociete", "Gestion Sociéte >");
			model.addAttribute("cheminModifierSociete", "Modifier une Société >");
			model.addAttribute("titre", "Gestion des Sociétés");
			String nomSociete=societe.getNomSociete().trim();
			String codeSociete=societe.getCodeSociete().trim();		
			List<Societe> societes=new ArrayList<Societe>();
			Boolean estSupprimer=false;			
			String codeSocieteCache=session.getAttribute("codeSocieteCache").toString().trim();	
			
			Societe societeRecherche=societeRepository.findSocieteByCode(codeSocieteCache);	
			
			Societe societeCodeRecherche=societeRepository.findSocieteByCode(codeSociete);	
			Societe societeNomRecherche=societeRepository.findSocieteByNom(nomSociete);
			model.addAttribute("titre", " Gestion de Société");
			if( nomSociete==null || nomSociete.length()==0 || nomSociete.length()>50 || codeSociete==null || codeSociete.length()==0 || codeSociete.length()>5 ) {						
				
				model.addAttribute("societeRecherche", societeRecherche);
				model.addAttribute("formErreurSociete", "formErreur");
				model.addAttribute("identifiantSession", identifiantSession);
				model.addAttribute("actionQuatreBouton", "actionQuatreBouton");	
				model.addAttribute("formulaireGestionModifSociete", "formulaireGestionModifSociete");
				model.addAttribute("listeSociete", "listeSociete");
				model.addAttribute("gestionSociete", "gestionSociete");
				model.addAttribute("menuNavigation", "menuNavigation");
				
				if(nomSociete==null || nomSociete.length()==0 || nomSociete.length()>50) {
					model.addAttribute("nomSocieteErreur", "Nom de la Société invalide");
				}
				if(codeSociete==null || codeSociete.length()==0 || codeSociete.length()>5) {
					model.addAttribute("codeSocieteErreur", "Code de la Société invalide");
				}
		
				societes=societeRepository.findAllSocietes(estSupprimer);
				model.addAttribute("societes", societes);				
				return "espaceUtilisateur";	
			}	
			
			Integer idSocieteOmission=societeRecherche.getIdSocite();
			List<Societe> societesOmission=societeRepository.findAllSocietesOmission(idSocieteOmission);
			Boolean estEgal=false;
			for(Societe se:societesOmission) {
				
				if(se.getCodeSociete().equals(codeSociete)) {
					estEgal=true;
					model.addAttribute("codeSocieteExistantErreur", "Code Société déjà existant");
				}
				if(se.getNomSociete().equals(nomSociete)) {
					estEgal=true;
					model.addAttribute("nomSocieteExistantErreur", "Nom Société déjà existant");
				}
				if(estEgal) {
					model.addAttribute("formErreurSociete", "formErreur");
					societes=societeRepository.findAllSocietes(estSupprimer);
					model.addAttribute("societes", societes);	
					model.addAttribute("societeRecherche", societeRecherche);				
					model.addAttribute("identifiantSession", identifiantSession);
					model.addAttribute("actionTroisBouton", "actionTroisBouton");	
					model.addAttribute("formulaireGestionModifSociete", "formulaireGestionModifSociete");
					model.addAttribute("listeSociete", "listeSociete");
					model.addAttribute("gestionSociete", "gestionSociete");
					model.addAttribute("menuNavigation", "menuNavigation");	
					return "espaceUtilisateur";	
				}
			}
//			DateFormat df = new SimpleDateFormat("dd/MM/yy");
//			long date =System.currentTimeMillis();
//			
//			DateFormat dateFormat= new SimpleDateFormat("dd-MM-yy");
//			String dateCreation=df.format(date);
			
			societeRecherche.setDateCreation(new Date());
			societeRecherche.setNomSociete(nomSociete);
			societeRecherche.setCodeSociete(codeSociete);
			

			societeRecherche.setEstSupprimer(estSupprimer);
//			societeRepository.save(societeRecherche);
			
			societes=societeRepository.findAllSocietes(estSupprimer);
			model.addAttribute("societeRecherche", societeCodeRecherche);
			model.addAttribute("societes", societes);	
			model.addAttribute("listeSociete", "listeSociete");
			model.addAttribute("actionQuatreBouton", "actionQuatreBouton");	
			model.addAttribute("resultatModifSociete", "resultatModifSociete");
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("gestionSociete", "gestionSociete");
			model.addAttribute("menuNavigation", "menuNavigation");			
	        return "espaceUtilisateur";	
	    }
	 
//	  Action Recherche Banque
	 @Transactional
	 @RequestMapping(value = {"/rechercheSociete" }, method = RequestMethod.GET)
	    public String rechercherSociete(Model model, HttpSession session) { 
			String resultat=null;
			try {
				identifiantSession=session.getAttribute("identifiantSession").toString().trim();
			}
			catch(Exception e) {
				resultat="pageErreur";
				return resultat;
			}	
//			gestion Menu 
			model.addAttribute("gestionMenuUtilisateur", "gestionMenuUtilisateur");
			model.addAttribute("gestionMenuBanque", "gestionMenuBanque");
			model.addAttribute("gestionMenuGuichet", "gestionMenuGuichet");
			model.addAttribute("gestionMenuAgence", "gestionMenuAgence");
			model.addAttribute("gestionMenuAgent", "gestionMenuAgent");
			model.addAttribute("gestionMenuSociete", "gestionMenuSociete");
			model.addAttribute("accueilDeux", "accueilDeux");
			
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionSociete", "Gestion Sociéte >");
			model.addAttribute("cheminRechercherSociete", "Rechercher une Société >");
			model.addAttribute("titre", "Gestion des Sociétés");
			Boolean estSupprimer=false;
			List<Societe> societes=new ArrayList<Societe>();
			societes=societeRepository.findAllSocietes(estSupprimer);
			model.addAttribute("societes", societes);
			model.addAttribute("titre", " Gestion de Société");
			model.addAttribute("formulaireNumeroRechercheSociete", "formulaireNumeroRechercheSociete");
			model.addAttribute("actionQuatreBouton", "actionQuatreBouton");	
			model.addAttribute("identifiantSession", identifiantSession);  
			model.addAttribute("listeSociete", "listeSociete");
			model.addAttribute("gestionSociete", "gestionSociete");
			model.addAttribute("menuNavigation", "menuNavigation");
	        return "espaceUtilisateur";		        
	    }
	 
	 @Transactional
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
			model.addAttribute("gestionMenuUtilisateur", "gestionMenuUtilisateur");
			model.addAttribute("gestionMenuBanque", "gestionMenuBanque");
			model.addAttribute("gestionMenuGuichet", "gestionMenuGuichet");
			model.addAttribute("gestionMenuAgence", "gestionMenuAgence");
			model.addAttribute("gestionMenuAgent", "gestionMenuAgent");
			model.addAttribute("gestionMenuSociete", "gestionMenuSociete");
			model.addAttribute("accueilDeux", "accueilDeux");
			
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionSociete", "Gestion Sociéte >");
			model.addAttribute("cheminRechercherSociete", "Rechercher une Société >");
			model.addAttribute("titre", "Gestion des Sociétés");
			String codeSociete=societe.getCodeSociete().trim();
			session.setAttribute("codeSocieteCache", codeSociete);
			Societe societeRecherche=societeRepository.findSocieteByCode(codeSociete);
			if( societeRecherche == null) {				
				 return "redirect:/messageSocieteNonExistante";  
			}		
			model.addAttribute("titre", " Gestion de Société");
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
	    public String messageSocieteNonExistante(Model model, @ModelAttribute("banque") Banque banque, HttpSession sessionUtilisateur, HttpSession session) {
			String resultat=null;
			try {
				identifiantSession=session.getAttribute("identifiantSession").toString().trim();
			}
			catch(Exception e) {
				resultat="pageErreur";
				return resultat;
			}
//			gestion Menu 
			model.addAttribute("gestionMenuUtilisateur", "gestionMenuUtilisateur");
			model.addAttribute("gestionMenuBanque", "gestionMenuBanque");
			model.addAttribute("gestionMenuGuichet", "gestionMenuGuichet");
			model.addAttribute("gestionMenuAgence", "gestionMenuAgence");
			model.addAttribute("gestionMenuAgent", "gestionMenuAgent");
			model.addAttribute("gestionMenuSociete", "gestionMenuSociete");
			model.addAttribute("accueilDeux", "accueilDeux");
			
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionSociete", "Gestion Sociéte >");
			model.addAttribute("cheminRechercherSociete", "Société Non Existante >");
			model.addAttribute("titre", "Gestion des Sociétés");
			model.addAttribute("titre", " Gestion de Société");
			String codeSocieteCache=session.getAttribute("codeSocieteCache").toString().trim();
			model.addAttribute("codeSociete", codeSocieteCache);	
			model.addAttribute("messageSocieteNonExistante", "messageSocieteNonExistante");		
			model.addAttribute("actionQuatreBouton", "actionQuatreBouton");	
			Boolean estSupprimer=false;
			List<Societe> societes=new ArrayList<Societe>();
			societes=societeRepository.findAllSocietes(estSupprimer);
			model.addAttribute("societes", societes);
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("listeSociete", "listeSociete");
			model.addAttribute("gestionSociete", "gestionSociete");
			model.addAttribute("menuNavigation", "menuNavigation");		
	        return "espaceUtilisateur";	
	    } 
	 
	//	 Action suppression Societe 
		 @Transactional
		 @RequestMapping(value = {"/supprimerSociete" }, method = RequestMethod.GET)
	    public String supprimerSociete(Model model, HttpSession session) { 
			String resultat=null;
			try {
				identifiantSession=session.getAttribute("identifiantSession").toString().trim();
			}
			catch(Exception e) {
				resultat="pageErreur";
				return resultat;
			}	
//			gestion Menu 
			model.addAttribute("gestionMenuUtilisateur", "gestionMenuUtilisateur");
			model.addAttribute("gestionMenuBanque", "gestionMenuBanque");
			model.addAttribute("gestionMenuGuichet", "gestionMenuGuichet");
			model.addAttribute("gestionMenuAgence", "gestionMenuAgence");
			model.addAttribute("gestionMenuAgent", "gestionMenuAgent");
			model.addAttribute("gestionMenuSociete", "gestionMenuSociete");
			model.addAttribute("accueilDeux", "accueilDeux");
			
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionSociete", "Gestion Sociéte >");
			model.addAttribute("cheminSupprimerSociete", "Supprimer une Société >");
			model.addAttribute("titre", "Gestion des Sociétés");
			Boolean estSupprimer=false;
			List<Societe> societes=new ArrayList<Societe>();
			societes=societeRepository.findAllSocietes(estSupprimer);
			model.addAttribute("societes", societes);
			model.addAttribute("titre", " Gestion de Société");
			model.addAttribute("formulaireNumeroSupprimerSociete", "formulaireNumeroSupprimerSociete");
			model.addAttribute("actionQuatreBouton", "actionQuatreBouton");	
			model.addAttribute("identifiantSession", identifiantSession);  
			model.addAttribute("listeSociete", "listeSociete");
			model.addAttribute("gestionSociete", "gestionSociete");
			model.addAttribute("menuNavigation", "menuNavigation");
	        return "espaceUtilisateur";	        
	    }
	 
	 @Transactional
	 @RequestMapping(value = {"/resultatSupprimerSociete" }, method = RequestMethod.POST)
	    public String resultatSuppressionSociete(Model model, @ModelAttribute("societe") Societe societe, HttpSession sessionUtilisateur, HttpSession session) {
			String resultat=null;
			try {
				identifiantSession=session.getAttribute("identifiantSession").toString().trim();
			}
			catch(Exception e) {
				resultat="pageErreur";
				return resultat;
			}
//			gestion Menu 
			model.addAttribute("gestionMenuUtilisateur", "gestionMenuUtilisateur");
			model.addAttribute("gestionMenuBanque", "gestionMenuBanque");
			model.addAttribute("gestionMenuGuichet", "gestionMenuGuichet");
			model.addAttribute("gestionMenuAgence", "gestionMenuAgence");
			model.addAttribute("gestionMenuAgent", "gestionMenuAgent");
			model.addAttribute("gestionMenuSociete", "gestionMenuSociete");
			model.addAttribute("accueilDeux", "accueilDeux");
			
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionSociete", "Gestion Sociéte >");
			model.addAttribute("cheminSupprimerSociete", "Supprimer une Société >");
			model.addAttribute("titre", "Gestion des Sociétés");
			String codeSociete=societe.getCodeSociete().trim();
			session.setAttribute("codeSocieteCache", codeSociete);
			Societe societeRecherche=societeRepository.findSocieteByCode(codeSociete);
			if( societeRecherche == null) {				
				 return "redirect:/messageSocieteNonExistante";  
			}								
			Boolean estSupprimer=false;
			List<Societe> societes=new ArrayList<Societe>();
			societes=societeRepository.findAllSocietes(estSupprimer);
			model.addAttribute("societeRecherche", societeRecherche);			
			model.addAttribute("dialog_boxSociete", "dialog_boxSocietee");	
			model.addAttribute("titre", " Gestion de Société");
			model.addAttribute("dialog_backgroundSociete", "dialog_backgroundSociete");	
			model.addAttribute("formulaireNumeroSupprimerSociete", "formulaireNumeroSupprimerSociete");
			model.addAttribute("actionQuatreBouton", "actionQuatreBouton");	
			model.addAttribute("societes", societes);
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("codeSociete", codeSociete);
			model.addAttribute("listeSociete", "listeSociete");
			model.addAttribute("gestionSociete", "gestionSociete");
			model.addAttribute("menuNavigation", "menuNavigation");		
	        return "espaceUtilisateur";	
	    }
	 
	 @Transactional
	 @RequestMapping(value = {"/succesSuppressionSociete" }, method = RequestMethod.POST)
	    public String succesSuppressionSociete(Model model, @ModelAttribute("societe") Societe societe, HttpSession sessionUtilisateur, HttpSession session) {
			String resultat=null;
			try {
				identifiantSession=session.getAttribute("identifiantSession").toString().trim();
			}
			catch(Exception e) {
				resultat="pageErreur";
				return resultat;
			}
//			gestion Menu 
			model.addAttribute("gestionMenuUtilisateur", "gestionMenuUtilisateur");
			model.addAttribute("gestionMenuBanque", "gestionMenuBanque");
			model.addAttribute("gestionMenuGuichet", "gestionMenuGuichet");
			model.addAttribute("gestionMenuAgence", "gestionMenuAgence");
			model.addAttribute("gestionMenuAgent", "gestionMenuAgent");
			model.addAttribute("gestionMenuSociete", "gestionMenuSociete");
			model.addAttribute("accueilDeux", "accueilDeux");
			
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionSociete", "Gestion Sociéte >");
			model.addAttribute("cheminSupprimerSociete", "Supprimer une Société >");
			model.addAttribute("titre", "Gestion des Sociétés");
			String codeSocieteSuppression=session.getAttribute("codeSocieteCache").toString().trim();
			Societe societeSuppression=societeRepository.findSocieteByCode(codeSocieteSuppression);
			societeSuppression.setEstSupprimer(true);
			societeRepository.save(societeSuppression);
			Boolean estSupprimer=false;		
			model.addAttribute("titre", " Gestion de Société");
			model.addAttribute("codeSociete", codeSocieteSuppression);
			model.addAttribute("resultatSuppressionSociete", "resultatSuppressionSociete");	
			model.addAttribute("actionQuatreBouton", "actionQuatreBouton");
			List<Societe> societes=new ArrayList<Societe>();
			societes=societeRepository.findAllSocietes(estSupprimer);	
			model.addAttribute("societes", societes);
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("listeSociete", "listeSociete");
			model.addAttribute("gestionSociete", "gestionSociete");
			model.addAttribute("menuNavigation", "menuNavigation");			
	        return "espaceUtilisateur";	
	    }
	
	 @Transactional
	 @RequestMapping(value = {"/resultatModifDonneeSociete" }, method = RequestMethod.POST)
	    public String resultatModifDonneeSociete(Model model, @ModelAttribute("societe") Societe societe, HttpSession sessionUtilisateur, HttpSession session) {
			String resultat=null;
			try {
				identifiantSession=session.getAttribute("identifiantSession").toString().trim();
			}
			catch(Exception e) {
				resultat="pageErreur";
				return resultat;
			}	
//			gestion Menu 
			model.addAttribute("gestionMenuUtilisateur", "gestionMenuUtilisateur");
			model.addAttribute("gestionMenuBanque", "gestionMenuBanque");
			model.addAttribute("gestionMenuGuichet", "gestionMenuGuichet");
			model.addAttribute("gestionMenuAgence", "gestionMenuAgence");
			model.addAttribute("gestionMenuAgent", "gestionMenuAgent");
			model.addAttribute("gestionMenuSociete", "gestionMenuSociete");
			model.addAttribute("accueilDeux", "accueilDeux");
			
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionSociete", "Gestion Sociéte >");
			model.addAttribute("cheminSupprimerSociete", "Supprimer une Société >");
			model.addAttribute("titre", "Gestion des Sociétés");
			String codeSociete=societe.getCodeSociete().trim();
			session.setAttribute("codeSocieteCache",codeSociete);						
			model.addAttribute("dialog_boxSociete", "dialog_boxSociete");	
			model.addAttribute("dialog_backgroundSociete", "dialog_backgroundSociete");				
			Boolean estSupprimer=false;	
			model.addAttribute("titre", " Gestion de Société");
			model.addAttribute("codeSociete", codeSociete);	
			model.addAttribute("actionQuatreBouton", "actionQuatreBouton");
			List<Societe> societes=new ArrayList<Societe>();
			societes=societeRepository.findAllSocietes(estSupprimer);	
			model.addAttribute("societes", societes);
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("listeSociete", "listeSociete");
			model.addAttribute("gestionSociete", "gestionSociete");
			model.addAttribute("menuNavigation", "menuNavigation");			
	        return "espaceUtilisateur";	
	    }
	 
	 @Transactional
	 @RequestMapping(value = {"/envoiDonneeCacheeSociete" }, method = RequestMethod.POST)
	    public String envoiDonneeCacheeSociete(Model model, @ModelAttribute("societe") Societe societe, HttpSession sessionUtilisateur, HttpSession session) {
			String resultat=null;
			try {
				identifiantSession=session.getAttribute("identifiantSession").toString().trim();
			}
			catch(Exception e) {
				resultat="pageErreur";
				return resultat;
			}	
//			gestion Menu 
			model.addAttribute("gestionMenuUtilisateur", "gestionMenuUtilisateur");
			model.addAttribute("gestionMenuBanque", "gestionMenuBanque");
			model.addAttribute("gestionMenuGuichet", "gestionMenuGuichet");
			model.addAttribute("gestionMenuAgence", "gestionMenuAgence");
			model.addAttribute("gestionMenuAgent", "gestionMenuAgent");
			model.addAttribute("gestionMenuSociete", "gestionMenuSociete");
			model.addAttribute("accueilDeux", "accueilDeux");
			
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionSociete", "Gestion Sociéte >");
			model.addAttribute("cheminModifierSociete", "Modifier une Société >");
			model.addAttribute("titre", "Gestion des Sociétés");
			session.removeAttribute("codeSocieteCache");
			String codeSociete=societe.getCodeSociete().trim();
			Societe societeRecherche=societeRepository.findSocieteByCode(codeSociete);
			Boolean estSupprimer=false;
			List<Societe> societes=new ArrayList<Societe>();
			model.addAttribute("titre", " Gestion de Société");
			societes=societeRepository.findAllSocietes(estSupprimer);
			model.addAttribute("societes", societes);
			session.setAttribute("codeSocieteCache", codeSociete);	
			model.addAttribute("formulaireGestionModifSociete", "formulaireGestionModifSociete");
			model.addAttribute("actionQuatreBouton", "actionQuatreBouton");	
			model.addAttribute("societeRecherche", societeRecherche);
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("listeSociete", "listeSociete");
			model.addAttribute("gestionSociete", "gestionSociete");
			model.addAttribute("menuNavigation", "menuNavigation");			
	        return "espaceUtilisateur";	
	    }
	  

}
