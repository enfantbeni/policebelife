package com.belife.policemanager.controller;


import java.util.ArrayList;
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

import com.belife.policemanager.model.dto.UtilisateurDto;
import com.belife.policemanager.model.entity.Agence;
import com.belife.policemanager.model.entity.Agent;
import com.belife.policemanager.model.entity.Client;
import com.belife.policemanager.model.entity.Roles;
import com.belife.policemanager.model.entity.SourcePolice;
import com.belife.policemanager.model.entity.Utilisateur;
import com.belife.policemanager.model.repository.AgenceRepository;
import com.belife.policemanager.model.repository.AgentRepository;
import com.belife.policemanager.model.repository.RolesRepository;
import com.belife.policemanager.model.repository.SourcePoliceRepository;
import com.belife.policemanager.model.repository.UtilisateurRepository;



@Controller
public class UtilisateurController {
	
	 @Autowired
     UtilisateurRepository utilisateurRepository; 
	 
	 @Autowired
     RolesRepository rolesRepository;
	 
	 @Autowired
     SourcePoliceRepository sourcePoliceRepository;
	 
	 @Autowired
     AgenceRepository agenceRepository;
	 
	 @Autowired
     AgentRepository agentRepository;
	 
	 private String fonctioInformaticien="Informaticien";
	 private String fonctionMedecin="Medecin";
	 private String fonctionSecretaire="Secretaire";
	 private String fonctionCommercial="Commercial";
	 private String fonctionUniteTechnique="Technicien";
	 private String fonctionChefAgence="Chef agence";
	 private String fonctionChefSousAgence="Chef sous agence";
	 private String fonctionEmploye="Employé";
	 private String fonctionAdministrateur="Administrateur";
	 
	 Roles r1=new Roles(" Gestion utilisateurs ","500",false);
	 Roles r2=new Roles(" Gestion modules ","501",false);
	 Roles r3=new Roles(" Gestion Banques ","502",false);
	 Roles r4=new Roles(" Gestion Agence ","503",false);
	 Roles r5=new Roles(" Soumission ","504",false);
	 Roles r6=new Roles(" Consultation LB ","505",false);
	 Roles r7=new Roles(" Consultation Alpha Inquery ","506",false);
	 Roles r8=new Roles(" Export Excel ","507",false);
	 
	 String identifiantSession=null;
	 
	 @Transactional
	@RequestMapping(value = {"/" }, method = RequestMethod.GET)
    public String index(Model model) {    
        return "redirect:/authentification";
    }
	
	@Transactional
	@RequestMapping(value = {"/deconnexion" }, method = RequestMethod.GET)
    public String deconnexion(Model model, HttpSession session) { 
	    session.removeAttribute("identifiantSession");
	    session.removeAttribute("identifiantCache");
	    session.removeAttribute("codeGuichetCache");
	    session.removeAttribute("codeSocieteCache");
	    session.removeAttribute("codeAgenceCache"); 
	    session.removeAttribute("nomSourceCache");
        return "redirect:/authentification";
    }
	
	@Transactional
	@RequestMapping(value = {"/authentification" }, method = RequestMethod.GET)
    public String authentification(Model model) {    
        return "authentification";
    }
	
	@Transactional
	@RequestMapping(value = {"/resultatModifDonnee" }, method = RequestMethod.POST)
    public String resultatModifDonnee(Model model, @ModelAttribute("utilisateur") Utilisateur utilisateur, HttpSession session) { 
		String resultat=null;
		try {
			identifiantSession=session.getAttribute("identifiantSession").toString().trim();
		}
		catch(Exception e) {
			resultat="pageErreur";
			return resultat;
		}
//		gestion Menu 
		model.addAttribute("gestionMenuUtilisateur", "gestionMenuUtilisateur");
		model.addAttribute("gestionMenuBanque", "gestionMenuBanque");
		model.addAttribute("gestionMenuGuichet", "gestionMenuGuichet");
		model.addAttribute("gestionMenuAgence", "gestionMenuAgence");
		model.addAttribute("gestionMenuAgent", "gestionMenuAgent");
		model.addAttribute("gestionMenuSociete", "gestionMenuSociete");
		model.addAttribute("accueilDeux", "accueilDeux");
		
		Utilisateur utilisateurRenvoi=new Utilisateur();
		List<Utilisateur> utilisateurs=new ArrayList<Utilisateur>();
		String identifiantAffiche=session.getAttribute("identifiantSession").toString().trim();
		model.addAttribute("identifiantSession", identifiantAffiche);
		String identifiant=utilisateur.getIdentifiant().trim();
		System.out.println(" Identifiant session utilisateur : "+identifiant);
		identifiantSession=identifiantSession.trim();
		
		
		Boolean estSupprimer=false;
		
		model.addAttribute("dialog_background", "dialog_background");
		model.addAttribute("identifiantSession",identifiantSession);
		model.addAttribute("dialog_box", "dialog_box");
		
		model.addAttribute("listeUtilisateur", "listeUtilisateur");
		
		List<Utilisateur> utilisateurA=new ArrayList<Utilisateur>();
		utilisateurA=utilisateurRepository.findAllUtilisateur(estSupprimer);
		model.addAttribute("utilisateurs", utilisateurA);
		model.addAttribute("afficheTableau", "afficheTableau");	
		utilisateurRenvoi=utilisateurRepository.findByIdentifiant(identifiant);	
		model.addAttribute("utilisateurRenvoi", utilisateurRenvoi);	
        return "espaceUtilisateur";
		
    }
	
	@Transactional
	@RequestMapping(value = {"/envoiDonneeCachee" }, method = RequestMethod.POST)
    public String envoiDonneeCachee(Model model,  @ModelAttribute("utilisateur") Utilisateur utilisateur, HttpSession session) { 		
		String resultat=null;
		try {
			identifiantSession=session.getAttribute("identifiantSession").toString().trim();
		}
		catch(Exception e) {
			resultat="pageErreur";
			return resultat;
		}				
		String identifiant=utilisateur.getIdentifiant().trim();	
		utilisateur=utilisateurRepository.findByIdentifiant(identifiant);
		session.setAttribute("identifiantCache",identifiant);
        return "redirect:/formModifDonneeUtilV2";
    }
	
	@Transactional
	@RequestMapping(value = {"/envoiDonneeCacheeModif" }, method = RequestMethod.POST)
    public String envoiDonneeCacheeModif(Model model,  @ModelAttribute("utilisateur") Utilisateur utilisateur, HttpSession session) { 		
		String resultat=null;
		try {
			identifiantSession=session.getAttribute("identifiantSession").toString().trim();
		}
		catch(Exception e) {
			resultat="pageErreur";
			return resultat;
		}				
		String identifiant=utilisateur.getIdentifiant().trim();	
		utilisateur=utilisateurRepository.findByIdentifiant(identifiant);
		session.setAttribute("identifiantCache",identifiant);
		model.addAttribute("utilisateur", utilisateur);	
        return "espaceUtilisateur";
    }
	

	
	@Transactional
	@RequestMapping(value = {"/sourcePolice" }, method = RequestMethod.GET)
    public String sourcePrelevement(Model model, HttpSession session) { 
		String resultat=null;
		try {
			identifiantSession=session.getAttribute("identifiantSession").toString().trim();
		}
		catch(Exception e) {
			resultat="pageErreur";
			return resultat;
		}
//		gestion Menu 
		model.addAttribute("gestionMenuUtilisateur", "gestionMenuUtilisateur");
		model.addAttribute("gestionMenuBanque", "gestionMenuBanque");
		model.addAttribute("gestionMenuGuichet", "gestionMenuGuichet");
		model.addAttribute("gestionMenuAgence", "gestionMenuAgence");
		model.addAttribute("gestionMenuAgent", "gestionMenuAgent");
		model.addAttribute("gestionMenuSociete", "gestionMenuSociete");
		model.addAttribute("accueilDeux", "accueilDeux");
		
		Boolean estSupprimer=false;
		List<SourcePolice> sourcePolices=new ArrayList<SourcePolice>();
		sourcePolices=sourcePoliceRepository.findAllSourcePrelevement(estSupprimer);
		model.addAttribute("sourcePolices", sourcePolices);
		model.addAttribute("identifiantSession", identifiantSession);
		model.addAttribute("formSourceGenre", "formSourceGenre");
		model.addAttribute("sourcePolice", "sourcePolice");
		model.addAttribute("menuNavigation", "menuNavigation");
        return "espaceUtilisateur";
    }
	
	@Transactional
	@RequestMapping(value = {"/contenuAccueilUtilisateur" }, method = RequestMethod.GET)
    public String contenuAccueilUtilisateur(Model model, HttpSession session) { 
		
		String resultat=null;
		try {
			identifiantSession=session.getAttribute("identifiantSession").toString().trim();
		}
		catch(Exception e) {
			resultat="pageErreur";
			return resultat;
		}
//		gestion Menu 
		model.addAttribute("gestionMenuUtilisateur", "gestionMenuUtilisateur");
		model.addAttribute("gestionMenuBanque", "gestionMenuBanque");
		model.addAttribute("gestionMenuGuichet", "gestionMenuGuichet");
		model.addAttribute("gestionMenuAgence", "gestionMenuAgence");
		model.addAttribute("gestionMenuAgent", "gestionMenuAgent");
		model.addAttribute("gestionMenuSociete", "gestionMenuSociete");
		model.addAttribute("accueilDeux", "accueilDeux");
		
		String identifiantConnecte=session.getAttribute("identifiantSession").toString().trim();
		Utilisateur utilisateur=utilisateurRepository.findByIdentifiant(identifiantConnecte);
		Agence agence=utilisateur.getIdAgence();
		String nomAgence=agence.getNomDirect();
		model.addAttribute("cheminAccueil", "Accueil >");
		model.addAttribute("nomAgence", nomAgence);
		model.addAttribute("accueilUtilisateurMessage", "accueilUtilisateurMessage");
		model.addAttribute("identifiantSession", identifiantSession);
		model.addAttribute("contenuAccueilUtilisateur", "contenuAccueilUtilisateur");
		model.addAttribute("menuNavigation", "menuNavigation");
        return "utilisateur/accueilUtilisateur";	
    }
	
	@Transactional
	@RequestMapping(value = {"/accueilUtilisateurLien" }, method = RequestMethod.GET)
    public String accueilUtilisateur(Model model, HttpSession session) {  
		String resultat=null;
		try {
			identifiantSession=session.getAttribute("identifiantSession").toString().trim();
		}
		catch(Exception e) {
			resultat="pageErreur";
			return resultat;
		}
//		gestion Menu 
		model.addAttribute("gestionMenuUtilisateur", "gestionMenuUtilisateur");
		model.addAttribute("gestionMenuBanque", "gestionMenuBanque");
		model.addAttribute("gestionMenuGuichet", "gestionMenuGuichet");
		model.addAttribute("gestionMenuAgence", "gestionMenuAgence");
		model.addAttribute("gestionMenuAgent", "gestionMenuAgent");
		model.addAttribute("gestionMenuSociete", "gestionMenuSociete");
		model.addAttribute("accueilDeux", "accueilDeux");

		
		String identifiantConnecte=session.getAttribute("identifiantSession").toString().trim();
		Utilisateur utilisateur=utilisateurRepository.findByIdentifiant(identifiantConnecte);
		Agence agence=utilisateur.getIdAgence();
		String nomAgence=agence.getNomDirect();
		model.addAttribute("nomAgence", nomAgence);
		session.setAttribute("nomAgenceActif", nomAgence);
		model.addAttribute("cheminAccueil", "Accueil >");
		model.addAttribute("contenuAccueilUtilisateur", "contenuAccueilUtilisateur");
		model.addAttribute("accueilUtilisateurMessage", "accueilUtilisateurMessage");
		model.addAttribute("identifiantSession", identifiantSession);
		model.addAttribute("menuNavigation", "menuNavigation");
        return "utilisateur/accueilUtilisateur";
    }
	
	@Transactional
	@RequestMapping(value = {"/listeModuleUtilisateur" }, method = RequestMethod.GET)
    public String listeModuleUtilisateur(Model model, HttpSession session) {   
		String resultat=null;
		try {
			identifiantSession=session.getAttribute("identifiantSession").toString().trim();
		}
		catch(Exception e) {
			resultat="pageErreur";
			return resultat;
		}
		model.addAttribute("listeModule", "listeModule");
		List<Roles> roles=new ArrayList<Roles>();
		roles.add(r1);
		roles.add(r2);
		roles.add(r3);roles.add(r4);roles.add(r5);roles.add(r6);roles.add(r7);roles.add(r8);
//		rolesRepository.saveAll(roles);
		List<Roles> moduleUtilisateur=rolesRepository.findAllRoles();
		model.addAttribute("roles", moduleUtilisateur);
		if(identifiantSession.length()>0) {
			String identifiantSession=session.getAttribute("identifiantSession").toString().trim();
			model.addAttribute("identifiantSession", identifiantSession);
			return "espaceUtilisateur";
			
		}
		else {
			return "pageErreur";
		}
			
    }
	
	@Transactional
	@RequestMapping(value = {"/modulesUtilisateur" }, method = RequestMethod.GET)
    public String modulesUtilisateur(Model model, @ModelAttribute("utilisateur") Utilisateur utilisateur, HttpSession session) { 
		String resultat=null;
		try {
			identifiantSession=session.getAttribute("identifiantSession").toString().trim();
		}
		catch(Exception e) {
			resultat="pageErreur";
			return resultat;
		}
		List<Utilisateur> utilisateurs=new ArrayList<Utilisateur>();
		model.addAttribute("modules", "modules");
		Boolean estSupprimer=false;
		utilisateurs=utilisateurRepository.findAllUtilisateur(estSupprimer);
		model.addAttribute("utilisateurs", utilisateurs);
		if(identifiantSession.length()>0){
			 String identifiantSession=session.getAttribute("identifiantSession").toString().trim();
			 model.addAttribute("identifiantSession", identifiantSession);
			 return "espaceUtilisateur";
			
		}
		else {
			return "pageErreur";
		}    
    }
	
	@Transactional
	@RequestMapping(value = {"/accueil" }, method = RequestMethod.POST)
    public String accueil(Model model,  @ModelAttribute("utilisateur") Utilisateur utilisateur, HttpSession session) { 
		model.addAttribute("accueilAdmin", "accueilAdmin");
		String identifiant=utilisateur.getIdentifiant().trim();
		String identifiantSession=null;
		String password=utilisateur.getPassword().trim();
		Utilisateur utilisateurParIdentifiant=null;
		String utilisateurPassword=null;
		

		if( identifiant != null && identifiant.length() > 0  && password != null && password.length() > 0 ) {
			utilisateurParIdentifiant=utilisateurRepository.findByIdentifiant(identifiant);			
			if(utilisateurParIdentifiant != null  ) {					
				utilisateurPassword=utilisateurParIdentifiant.getPassword();			
				String passNouveau=utilisateurPassword.trim();
				if( passNouveau.equals(password) ) {
					 session.setAttribute("identifiantSession", utilisateur.getIdentifiant().trim());	
					 identifiantSession=session.getAttribute("identifiantSession").toString().trim();
					 model.addAttribute("identifiantSession", identifiantSession);
					 String fonction=utilisateurRepository.fonctionUtilisateur(identifiantSession);
					 if(fonction.equals(fonctionAdministrateur)) {
						 model.addAttribute("cheminAccueil", "Accueil >");
						 model.addAttribute("accueilAdmin", "accueilAdmin");
//							gestion Menu 
							model.addAttribute("gestionMenuUtilisateur", "gestionMenuUtilisateur");
							model.addAttribute("gestionMenuBanque", "gestionMenuBanque");
							model.addAttribute("gestionMenuGuichet", "gestionMenuGuichet");
							model.addAttribute("gestionMenuAgence", "gestionMenuAgence");
							model.addAttribute("gestionMenuAgent", "gestionMenuAgent"); 
							model.addAttribute("gestionMenuSociete", "gestionMenuSociete");
							model.addAttribute("messageAccueilEspaceAdmin", "messageAccueilEspaceAdmin");
							model.addAttribute("accueilDeux", "accueilDeux");
						 
						 return "espaceUtilisateur";
					 }
					 else if(fonction.equals(fonctionUniteTechnique)) {
						 model.addAttribute("cheminAccueil", "Accueil >");
//							gestion Menu 			
							model.addAttribute("gestionMenuUniteTechnique", "gestionMenuUniteTechnique");
							model.addAttribute("accueilUniteTechnique", "accueilUniteTechnique");	
							model.addAttribute("accueilDeuxUniteTechnique", "accueilDeuxUniteTechnique");	
							model.addAttribute("accueilUniteTechniqueMessage", "accueilUniteTechniqueMessage");	
						 return "espaceUtilisateur";
						 
					 }
					 else {
						 return "redirect:/accueilUtilisateurLien";
					 }			 
					 	
				} 
			}
		}		 
		 model.addAttribute("hasError", " Identifiant ou mot de passe incorrect");
		 model.addAttribute("password", password);
		 model.addAttribute("identifiant", identifiant);
        return "authentification";
    }
	
	@Transactional
	@RequestMapping(value = {"/accueilDeux" }, method = RequestMethod.GET)
    public String accueilDeux(Model model, HttpSession session) { 
		String resultat=null;
		try {
			identifiantSession=session.getAttribute("identifiantSession").toString().trim();
		}
		catch(Exception e) {
			resultat="pageErreur";
			return resultat;
		}
//		gestion Menu 
		model.addAttribute("gestionMenuUtilisateur", "gestionMenuUtilisateur");
		model.addAttribute("gestionMenuBanque", "gestionMenuBanque");
		model.addAttribute("gestionMenuGuichet", "gestionMenuGuichet");
		model.addAttribute("gestionMenuAgence", "gestionMenuAgence");
		model.addAttribute("gestionMenuAgent", "gestionMenuAgent");
		model.addAttribute("gestionMenuSociete", "gestionMenuSociete");
		model.addAttribute("messageAccueilEspaceAdmin", "messageAccueilEspaceAdmin");
		model.addAttribute("accueilDeux", "accueilDeux");
		model.addAttribute("messageAccueilEspaceAdmin", "messageAccueilEspaceAdmin");
		model.addAttribute("accueilAdmin", "accueilAdmin");
		model.addAttribute("cheminAccueil", "Accueil >");
		
		if(identifiantSession.length()>0) {
			String identifiantSession=session.getAttribute("identifiantSession").toString().trim();
			model.addAttribute("identifiantSession", identifiantSession);
			return "espaceUtilisateur";
		}
		else {
			return "pageErreur";
		}
        
    }
	
	@Transactional
	@RequestMapping(value = {"/listeUtilisateur" }, method = RequestMethod.GET)
    public String listeUtilisateur(Model model, @ModelAttribute("utilisateur") Utilisateur utilisateur, HttpSession session) { 
		String resultat=null;
		try {
			identifiantSession=session.getAttribute("identifiantSession").toString().trim();
		}
		catch(Exception e) {
			resultat="pageErreur";
			return resultat;
		}
//		gestion Menu 
		model.addAttribute("gestionMenuUtilisateur", "gestionMenuUtilisateur");
		model.addAttribute("gestionMenuBanque", "gestionMenuBanque");
		model.addAttribute("gestionMenuGuichet", "gestionMenuGuichet");
		model.addAttribute("gestionMenuAgence", "gestionMenuAgence");
		model.addAttribute("gestionMenuAgent", "gestionMenuAgent");
		model.addAttribute("gestionMenuSociete", "gestionMenuSociete");
		model.addAttribute("cheminAccueil", "Accueil >");
		model.addAttribute("accueilDeux", "accueilDeux");
		model.addAttribute("titre", "Gestion des utilisateurs");
		model.addAttribute("cheminGestionUtilisateur", "Gestion des utilisateurs >");
		model.addAttribute("listeUtilisateur", "listeUtilisateur");
		List<Utilisateur> utilisateurs=new ArrayList<Utilisateur>();
		Boolean estSupprimer=false;
		utilisateurs=utilisateurRepository.findAllUtilisateur(estSupprimer);
		model.addAttribute("utilisateurs", utilisateurs);
		model.addAttribute("afficheTableau", "afficheTableau");
		if(identifiantSession.length()>0) {
			String identifiantSession=session.getAttribute("identifiantSession").toString().trim();
			model.addAttribute("identifiantSession", identifiantSession);
			return "espaceUtilisateur";
			
		}
		
			return resultat;
		
    }
	
	@Transactional
	@RequestMapping(value = {"/formulaireAjoutUtilisateur" }, method = RequestMethod.GET)
    public String formulaireAjoutUtilisateur(Model model, @ModelAttribute("utilisateur") Utilisateur utilisateur, HttpSession session) { 
		String resultat=null;
		try {
			identifiantSession=session.getAttribute("identifiantSession").toString().trim();
		}
		catch(Exception e) {
			resultat="pageErreur";
			return resultat;
		}
//		gestion Menu 
		model.addAttribute("gestionMenuUtilisateur", "gestionMenuUtilisateur");
		model.addAttribute("gestionMenuBanque", "gestionMenuBanque");
		model.addAttribute("gestionMenuGuichet", "gestionMenuGuichet");
		model.addAttribute("gestionMenuAgence", "gestionMenuAgence");
		model.addAttribute("gestionMenuAgent", "gestionMenuAgent");
		model.addAttribute("gestionMenuSociete", "gestionMenuSociete");
		model.addAttribute("accueilDeux", "accueilDeux");
		
		Boolean estSupprimer=false;
		List<String> agences=new ArrayList<String>();
		agences=agenceRepository.findAllNomDirects(estSupprimer);
		model.addAttribute("agences",agences);
		model.addAttribute("ajout", "ajout");
		
		model.addAttribute("cheminAccueil", "Accueil >");
		model.addAttribute("titre", "Gestion des utilisateurs");
		model.addAttribute("cheminGestionUtilisateur", "Gestion Utilisateur >");
		model.addAttribute("cheminAjouterUtilisateur", "Ajouter Utilisateur >");
		
		model.addAttribute("formulaireAjoutUtil", "formulaireAjoutUtil");
		model.addAttribute("listeUtilisateur", "listeUtilisateur");
		model.addAttribute("actionTroisBouton", "actionTroisBouton");
		List<Utilisateur> utilisateurs=new ArrayList<Utilisateur>();
		utilisateurs=utilisateurRepository.findAllUtilisateur(estSupprimer);
		model.addAttribute("afficheTableau", "afficheTableau");
		model.addAttribute("utilisateurs", utilisateurs);
		if(identifiantSession.length()>0) {
			String identifiantSession=session.getAttribute("identifiantSession").toString().trim();
			model.addAttribute("identifiantSession", identifiantSession);
			return "espaceUtilisateur";
		}
			return "pageErreur";	
    }
	
	@Transactional
	@RequestMapping(value = {"/ajoutUtilisateur" }, method = RequestMethod.POST)
    public String ajoutUtilisateur(Model model, @ModelAttribute("utilisateur") Utilisateur utilisateur, @ModelAttribute("utilisateurDto") UtilisateurDto utilisateurDto, HttpSession session) { 
		String resultat=null;
		try {
			identifiantSession=session.getAttribute("identifiantSession").toString().trim();
		}
		catch(Exception e) {
			resultat="pageErreur";
			return resultat;
		}
//		gestion Menu 
		model.addAttribute("gestionMenuUtilisateur", "gestionMenuUtilisateur");
		model.addAttribute("gestionMenuBanque", "gestionMenuBanque");
		model.addAttribute("gestionMenuGuichet", "gestionMenuGuichet");
		model.addAttribute("gestionMenuAgence", "gestionMenuAgence");
		model.addAttribute("gestionMenuAgent", "gestionMenuAgent");
		model.addAttribute("gestionMenuSociete", "gestionMenuSociete");
		model.addAttribute("accueilDeux", "accueilDeux");
		
		List<Utilisateur> utilisateurs=new ArrayList<Utilisateur>();
		Utilisateur utilisateurSave=null;
		String identifiant=utilisateurDto.getIdentifiant().trim();
		String password=utilisateurDto.getPassword().trim();
		String nomEtPrenom=utilisateurDto.getNomEtPrenom().trim();
		String fonction=utilisateurDto.getFonction().trim();
		String nomAgence=utilisateurDto.getNomAgence().trim();
		Boolean estSupprimer=false;
		model.addAttribute("listeUtilisateur", "listeUtilisateur");
		model.addAttribute("actionTroisBouton", "actionTroisBouton");	
		
		if( identifiant != null && identifiant.length() > 0 && identifiant.length()<=10 && password != null && password.length() > 0 && password.length()>4 && nomEtPrenom != null && nomEtPrenom.length() > 0 && fonction != null && fonction.length() > 0 ) {
			utilisateurSave=utilisateurRepository.findByIdentifiant(identifiant);
				if(utilisateurSave == null) {
					
					utilisateur.setEstSupprimer(estSupprimer);
					utilisateur.setFonction(fonction);
					utilisateur.setIdentifiant(identifiant);
					utilisateur.setNomEtPrenom(nomEtPrenom);
					utilisateur.setPassword(password);
					
					Agence agence=agenceRepository.findAgenceByNomDirect(nomAgence);
					utilisateur.setIdAgence(agence);
					utilisateurRepository.save(utilisateur);	
					utilisateurs=utilisateurRepository.findAllUtilisateur(estSupprimer);	
					model.addAttribute("ajout", "ajout");
					model.addAttribute("afficheTableau", "afficheTableau");
					model.addAttribute("utilisateurs", utilisateurs);					
					model.addAttribute("succes", "succes");
					model.addAttribute("choisirRole", "choisirRole");
					String identifiantSession=session.getAttribute("identifiantSession").toString().trim();
					 model.addAttribute("identifiantSession", identifiantSession);
					if(identifiantSession.length()>0)
						return "espaceUtilisateur";
				}				
		}
		
		model.addAttribute("formErreur", "formErreur");
		if(identifiant==null || identifiant.length()==0 || identifiant.length()>10) {
			model.addAttribute("identifiantErreur", "Identifiant invalide");
		}
		if(password==null || password.length()==0 || password.length()<=4) {
			model.addAttribute("passwordErreur", "Mot de passe invalide");
		}
		if(nomEtPrenom==null || nomEtPrenom.length()==0) {
			model.addAttribute("nomEtPrenomErreur", "Nom et prenom invalide");
		}
		if(fonction==null || fonction.length()==0) {
			model.addAttribute("fonctionErreur", "Fonction invalide");
		}
		
			String identifiantSession=session.getAttribute("identifiantSession").toString().trim();
			List<String> agences=new ArrayList<String>();
			agences=agenceRepository.findAllNomDirects(estSupprimer);
			model.addAttribute("agences",agences);
			model.addAttribute("identifiantSession", identifiantSession);
			utilisateurs=utilisateurRepository.findAllUtilisateur(estSupprimer);	
			model.addAttribute("ajout", "ajout");
			model.addAttribute("afficheTableau", "afficheTableau");
			model.addAttribute("utilisateurs", utilisateurs);
			model.addAttribute("formulaireAjoutUtil", "formulaireAjoutUtil");
			if(identifiantSession.length()>0) {
				return "espaceUtilisateur";
			}
			else {
				return "pageErreur";
			}
        
    }
	
	@Transactional
	@RequestMapping(value = {"/numeroModifUtilisateur" }, method = RequestMethod.GET)
    public String numeroModifUtilisateur(Model model, HttpSession session) { 
		String resultat=null;
		try {
			identifiantSession=session.getAttribute("identifiantSession").toString().trim();
		}
		catch(Exception e) {
			resultat="pageErreur";
			return resultat;
		}
//		gestion Menu 
		model.addAttribute("gestionMenuUtilisateur", "gestionMenuUtilisateur");
		model.addAttribute("gestionMenuBanque", "gestionMenuBanque");
		model.addAttribute("gestionMenuGuichet", "gestionMenuGuichet");
		model.addAttribute("gestionMenuAgence", "gestionMenuAgence");
		model.addAttribute("gestionMenuAgent", "gestionMenuAgent");
		model.addAttribute("gestionMenuSociete", "gestionMenuSociete");
		model.addAttribute("accueilDeux", "accueilDeux");
		
		model.addAttribute("ajout", "ajout");
		model.addAttribute("listeUtilisateur", "listeUtilisateur");
		model.addAttribute("formNumeroModifUtil", "formNumeroModifUtil");
		model.addAttribute("cheminAccueil", "Accueil >");
		model.addAttribute("titre", "Gestion des utilisateurs");
		model.addAttribute("cheminGestionUtilisateur", "Gestion Utilisateur >");
		model.addAttribute("cheminModifierUtilisateur", "Modifier Utilisateur >");
		model.addAttribute("actionTroisBouton", "actionTroisBouton");
		model.addAttribute("afficheTableau", "afficheTableau");
		List<Utilisateur> utilisateurs=new ArrayList<Utilisateur>();
		Boolean estSupprimer=false;
		utilisateurs=utilisateurRepository.findAllUtilisateur(estSupprimer);
		model.addAttribute("utilisateurs", utilisateurs);
		if(identifiantSession.length()>0) {
			String identifiantSession=session.getAttribute("identifiantSession").toString().trim();
			model.addAttribute("identifiantSession", identifiantSession);
			return "espaceUtilisateur";
		}
		else {
			 return "pageErreur";
		}     
    }
	
	@Transactional
	@RequestMapping(value = {"/formModifDonneeUtil" }, method = RequestMethod.POST)
    public String formModifDonneeUtil(Model model, @ModelAttribute("utilisateur") Utilisateur utilisateur, HttpSession sessionUtilisateur, HttpSession session) {
		String resultat=null;
		try {
			identifiantSession=session.getAttribute("identifiantSession").toString().trim();
		}
		catch(Exception e) {
			resultat="pageErreur";
			return resultat;
		}
//		gestion Menu 
		model.addAttribute("gestionMenuUtilisateur", "gestionMenuUtilisateur");
		model.addAttribute("gestionMenuBanque", "gestionMenuBanque");
		model.addAttribute("gestionMenuGuichet", "gestionMenuGuichet");
		model.addAttribute("gestionMenuAgence", "gestionMenuAgence");
		model.addAttribute("gestionMenuAgent", "gestionMenuAgent");
		model.addAttribute("gestionMenuSociete", "gestionMenuSociete");
		model.addAttribute("accueilDeux", "accueilDeux");
		
		String identifiant=utilisateur.getIdentifiant().trim();
		sessionUtilisateur.setAttribute("identifiantCache", utilisateur.getIdentifiant());
		
		Utilisateur utilisateurRecherche=utilisateurRepository.findByIdentifiant(identifiant);
			
		model.addAttribute("afficheTableau", "afficheTableau");
		if( utilisateurRecherche == null) {
			 String identifiantSession=session.getAttribute("identifiantSession").toString().trim();
			 model.addAttribute("identifiantSession", identifiantSession);
			 return "redirect:/messageUtilisateurNonExistant";  
		}
		
				
		model.addAttribute("ajout", "ajout");
		model.addAttribute("listeUtilisateur", "listeUtilisateur");
		model.addAttribute("formModifDonneeUtil", "formModifDonneeUtil");
		model.addAttribute("actionTroisBouton", "actionTroisBouton");
		model.addAttribute("utilisateurRecherche", utilisateurRecherche);
		model.addAttribute("afficheTableau", "afficheTableau");
		
		model.addAttribute("ajout", "ajout");
		model.addAttribute("listeUtilisateur", "listeUtilisateur");
//		model.addAttribute("formModifDonneeUtil", "formModifDonneeUtil");
		model.addAttribute("actionTroisBouton", "actionTroisBouton");
		model.addAttribute("utilisateurRecherche", utilisateurRecherche);
		model.addAttribute("afficheTableau", "afficheTableau");
		List<Utilisateur> utilisateurs=new ArrayList<Utilisateur>();
		Boolean estSupprimer=false;
		utilisateurs=utilisateurRepository.findAllUtilisateur(estSupprimer);
		model.addAttribute("utilisateurs", utilisateurs);
		String identifiantSession=session.getAttribute("identifiantSession").toString().trim();
		model.addAttribute("identifiantSession", identifiantSession);
		if(identifiantSession.length()>0) {
			return "espaceUtilisateur";
		}
		else {
			return "pageErreur";
		}			
    }
	
	@Transactional
	@RequestMapping(value = {"/formModifDonneeUtilV2" }, method = RequestMethod.GET)
    public String formModifDonneeUtilV2(Model model, @ModelAttribute("utilisateur") Utilisateur utilisateur, HttpSession sessionUtilisateur, HttpSession session) {
		String resultat=null;
		try {
			identifiantSession=session.getAttribute("identifiantSession").toString().trim();
		}
		catch(Exception e) {
			resultat="pageErreur";
			return resultat;
		}
//		gestion Menu 
		model.addAttribute("gestionMenuUtilisateur", "gestionMenuUtilisateur");
		model.addAttribute("gestionMenuBanque", "gestionMenuBanque");
		model.addAttribute("gestionMenuGuichet", "gestionMenuGuichet");
		model.addAttribute("gestionMenuAgence", "gestionMenuAgence");
		model.addAttribute("gestionMenuAgent", "gestionMenuAgent");
		model.addAttribute("gestionMenuSociete", "gestionMenuSociete");
		model.addAttribute("accueilDeux", "accueilDeux");
	
		String identifiant=session.getAttribute("identifiantCache").toString().trim();
		sessionUtilisateur.setAttribute("identifiant", utilisateur.getIdentifiant());
		
		System.out.println(" Identifiant " +identifiant);
		Utilisateur utilisateurRecherche=utilisateurRepository.findByIdentifiant(identifiant);
			
		model.addAttribute("afficheTableau", "afficheTableau");
		if( utilisateurRecherche == null) {
			 String identifiantSession=session.getAttribute("identifiantSession").toString().trim();
			 model.addAttribute("identifiantSession", identifiantSession);
			 return "redirect:/messageUtilisateurNonExistant";  
		}
		
				
		model.addAttribute("ajout", "ajout");
		model.addAttribute("listeUtilisateur", "listeUtilisateur");
		model.addAttribute("formModifDonneeUtil", "formModifDonneeUtil");
		model.addAttribute("actionTroisBouton", "actionTroisBouton");
		model.addAttribute("utilisateurRecherche", utilisateurRecherche);
		model.addAttribute("afficheTableau", "afficheTableau");
		
		model.addAttribute("ajout", "ajout");
		model.addAttribute("listeUtilisateur", "listeUtilisateur");
//		model.addAttribute("formModifDonneeUtil", "formModifDonneeUtil");
		model.addAttribute("actionTroisBouton", "actionTroisBouton");
		model.addAttribute("utilisateurRecherche", utilisateurRecherche);
		model.addAttribute("afficheTableau", "afficheTableau");
		List<Utilisateur> utilisateurs=new ArrayList<Utilisateur>();
		Boolean estSupprimer=false;
		utilisateurs=utilisateurRepository.findAllUtilisateur(estSupprimer);
		model.addAttribute("utilisateurs", utilisateurs);
		String identifiantSession=session.getAttribute("identifiantSession").toString().trim();
		model.addAttribute("identifiantSession", identifiantSession);
		if(identifiantSession.length()>0) {
			return "espaceUtilisateur";
		}
		else {
			return "pageErreur";
		}			
    }
	
	
	
	@Transactional
	@RequestMapping(value = {"/messageUtilisateurNonExistant" }, method = RequestMethod.GET)
    public String messageUtilisateurNonExistant(Model model, @ModelAttribute("utilisateur") Utilisateur utilisateur, HttpSession session) {  
		String resultat=null;
		try {
			identifiantSession=session.getAttribute("identifiantSession").toString().trim();
		}
		catch(Exception e) {
			resultat="pageErreur";
			return resultat;
		}
//		gestion Menu 
		model.addAttribute("gestionMenuUtilisateur", "gestionMenuUtilisateur");
		model.addAttribute("gestionMenuBanque", "gestionMenuBanque");
		model.addAttribute("gestionMenuGuichet", "gestionMenuGuichet");
		model.addAttribute("gestionMenuAgence", "gestionMenuAgence");
		model.addAttribute("gestionMenuAgent", "gestionMenuAgent");
		model.addAttribute("gestionMenuSociete", "gestionMenuSociete");
		model.addAttribute("accueilDeux", "accueilDeux");
		
		model.addAttribute("ajout", "ajout");
		model.addAttribute("listeUtilisateur", "listeUtilisateur");
		model.addAttribute("messageUtilisateurNonExistant", "messageUtilisateurNonExistant");
		model.addAttribute("actionTroisBouton", "actionTroisBouton");
		model.addAttribute("afficheTableau", "afficheTableau");
		List<Utilisateur> utilisateurs=new ArrayList<Utilisateur>();
		Boolean estSupprimer=false;
		utilisateurs=utilisateurRepository.findAllUtilisateur(estSupprimer);
		model.addAttribute("utilisateurs", utilisateurs);	
		if(identifiantSession.length()>0) {
			String identifiantSession=session.getAttribute("identifiantSession").toString().trim();
			model.addAttribute("identifiantSession", identifiantSession);
			return "espaceUtilisateur";
		}
		else {
			return "pageErreur";
		}
        
    }
	
	@Transactional
	@RequestMapping(value = {"/rechercheUtilisateur" }, method = RequestMethod.GET)
    public String rechercheUtilisateur(Model model, @ModelAttribute("utilisateur") Utilisateur utilisateur, HttpSession session) { 
		String resultat=null;
		try {
			identifiantSession=session.getAttribute("identifiantSession").toString().trim();
		}
		catch(Exception e) {
			resultat="pageErreur";
			return resultat;
		}
//		gestion Menu 
		model.addAttribute("gestionMenuUtilisateur", "gestionMenuUtilisateur");
		model.addAttribute("gestionMenuBanque", "gestionMenuBanque");
		model.addAttribute("gestionMenuGuichet", "gestionMenuGuichet");
		model.addAttribute("gestionMenuAgence", "gestionMenuAgence");
		model.addAttribute("gestionMenuAgent", "gestionMenuAgent");
		model.addAttribute("gestionMenuSociete", "gestionMenuSociete");
		model.addAttribute("accueilDeux", "accueilDeux");
		
		model.addAttribute("ajout", "ajout");
		model.addAttribute("listeUtilisateur", "listeUtilisateur");
		model.addAttribute("rechercheUtilisateur", "rechercheUtilisateur");
		model.addAttribute("actionTroisBouton", "actionTroisBouton");
		model.addAttribute("cheminAccueil", "Accueil >");
		model.addAttribute("titre", "Gestion des utilisateurs");
		model.addAttribute("cheminGestionUtilisateur", "Gestion Utilisateur >");
		model.addAttribute("cheminRechercherUtilisateur", "Rechercher Utilisateur >");
		List<Utilisateur> utilisateurs=new ArrayList<Utilisateur>();
//		model.addAttribute("afficheTableau", "afficheTableau");
		Boolean estSupprimer=false;
		String identifiantSession=session.getAttribute("identifiantSession").toString().trim();
		model.addAttribute("identifiantSession", identifiantSession);
		utilisateurs=utilisateurRepository.findAllUtilisateur(estSupprimer);
		if(identifiantSession.length()>0) {
			return "espaceUtilisateur";
		}
		else {
			  return "pageErreur";
		} 
    }
	
	@Transactional
	@RequestMapping(value = {"/succesRecherche" }, method = RequestMethod.POST)
    public String succesRecherche(Model model, @ModelAttribute("utilisateur") Utilisateur utilisateur, HttpSession session) {
		String resultat=null;
		try {
			identifiantSession=session.getAttribute("identifiantSession").toString().trim();
		}
		catch(Exception e) {
			resultat="pageErreur";
			return resultat;
		}
//		gestion Menu 
		model.addAttribute("gestionMenuUtilisateur", "gestionMenuUtilisateur");
		model.addAttribute("gestionMenuBanque", "gestionMenuBanque");
		model.addAttribute("gestionMenuGuichet", "gestionMenuGuichet");
		model.addAttribute("gestionMenuAgence", "gestionMenuAgence");
		model.addAttribute("gestionMenuAgent", "gestionMenuAgent");
		model.addAttribute("gestionMenuSociete", "gestionMenuSociete");
		model.addAttribute("accueilDeux", "accueilDeux");
		
		String identifiantAffiche=session.getAttribute("identifiantSession").toString().trim();
		model.addAttribute("identifiantSession", identifiantAffiche);
		String identifiant=utilisateur.getIdentifiant().trim();
		Utilisateur utilisateurRecherche=utilisateurRepository.findByIdentifiant(identifiant);
		System.out.println(" Utilisateur " + utilisateurRecherche);
		if( utilisateurRecherche == null) {
			if(identifiantSession.length()>0)
				return "redirect:/messageUtilisateurNonRetrouver";  
		}
		model.addAttribute("ajout", "ajout");
		model.addAttribute("listeUtilisateur", "listeUtilisateur");
		model.addAttribute("messageUtilisateurRetrouver", "messageUtilisateurRetrouver");
		model.addAttribute("actionTroisBouton", "actionTroisBouton");
		model.addAttribute("succesRecherche", "succesRecherche");
		model.addAttribute("utilisateurRechercher", utilisateurRecherche);
//		model.addAttribute("afficheTableau", "afficheTableau");
		List<Utilisateur> utilisateurs=new ArrayList<Utilisateur>();
		Boolean estSupprimer=false;
		utilisateurs=utilisateurRepository.findAllUtilisateur(estSupprimer);
		model.addAttribute("utilisateurs", utilisateurs);	
		if(identifiant.length()>0) {
			 return "espaceUtilisateur";
		}
		else {
			 return "pageErreur";
		}	
    }
	
	@Transactional
	@RequestMapping(value = {"/messageUtilisateurNonRetrouver" }, method = RequestMethod.GET)
    public String messageUtilisateurNonRetrouver(Model model, @ModelAttribute("utilisateur") Utilisateur utilisateur, HttpSession session) { 
		String resultat=null;
		try {
			identifiantSession=session.getAttribute("identifiantSession").toString().trim();
		}
		catch(Exception e) {
			resultat="pageErreur";
			return resultat;
		}
//		gestion Menu 
		model.addAttribute("gestionMenuUtilisateur", "gestionMenuUtilisateur");
		model.addAttribute("gestionMenuBanque", "gestionMenuBanque");
		model.addAttribute("gestionMenuGuichet", "gestionMenuGuichet");
		model.addAttribute("gestionMenuAgence", "gestionMenuAgence");
		model.addAttribute("gestionMenuAgent", "gestionMenuAgent");
		model.addAttribute("gestionMenuSociete", "gestionMenuSociete");
		model.addAttribute("accueilDeux", "accueilDeux");
		
		String identifiantAffiche=session.getAttribute("identifiantSession").toString().trim();
		model.addAttribute("identifiantSession", identifiantAffiche);
		model.addAttribute("ajout", "ajout");
		model.addAttribute("listeUtilisateur", "listeUtilisateur");
		model.addAttribute("messageUtilisateurNonRetrouver", "messageUtilisateurNonRetrouver");
		model.addAttribute("actionTroisBouton", "actionTroisBouton");
		model.addAttribute("afficheTableau", "afficheTableau");
		List<Utilisateur> utilisateurs=new ArrayList<Utilisateur>();
		Boolean estSupprimer=false;
		utilisateurs=utilisateurRepository.findAllUtilisateur(estSupprimer);
		model.addAttribute("utilisateurs", utilisateurs);
		if(identifiantSession.length()>0) {
			return "espaceUtilisateur";
		}
		else {
			return "espaceUtilisateur";
		}      
    }
	
	@Transactional
	@RequestMapping(value = {"/supprimerUtilisateur" }, method = RequestMethod.GET)
    public String supprimerUtilisateur(Model model, @ModelAttribute("utilisateur") Utilisateur utilisateur, HttpSession session) {
		String resultat=null;
		try {
			identifiantSession=session.getAttribute("identifiantSession").toString().trim();
		}
		catch(Exception e) {
			resultat="pageErreur";
			return resultat;
		}
//		gestion Menu 
		model.addAttribute("gestionMenuUtilisateur", "gestionMenuUtilisateur");
		model.addAttribute("gestionMenuBanque", "gestionMenuBanque");
		model.addAttribute("gestionMenuGuichet", "gestionMenuGuichet");
		model.addAttribute("gestionMenuAgence", "gestionMenuAgence");
		model.addAttribute("gestionMenuAgent", "gestionMenuAgent");
		model.addAttribute("gestionMenuSociete", "gestionMenuSociete");
		model.addAttribute("accueilDeux", "accueilDeux");
		
		String identifiantAffiche=session.getAttribute("identifiantSession").toString().trim();
		model.addAttribute("cheminAccueil", "Accueil >");
		model.addAttribute("titre", "Gestion des utilisateurs");
		model.addAttribute("cheminGestionUtilisateur", "Gestion Utilisateur >");
		model.addAttribute("cheminSupprimerUtilisateur", "Supprimer Utilisateur >");
		model.addAttribute("identifiantSession", identifiantAffiche);
		model.addAttribute("ajout", "ajout");
		model.addAttribute("listeUtilisateur", "listeUtilisateur");
		model.addAttribute("supprimerUtilisateur", "supprimerUtilisateur");
		model.addAttribute("actionTroisBouton", "actionTroisBouton");
		model.addAttribute("afficheTableau", "afficheTableau");
		List<Utilisateur> utilisateurs=new ArrayList<Utilisateur>();
		Boolean estSupprimer=false;
		utilisateurs=utilisateurRepository.findAllUtilisateur(estSupprimer);
		model.addAttribute("utilisateurs", utilisateurs);
		if(identifiantSession.length()>0) {
			return "espaceUtilisateur";
		}
		else {
			return "pageErreur";
		}     
    }
	
	@Transactional
	@RequestMapping(value = {"/succesSuppression" }, method = RequestMethod.POST)
    public String SuccesSuppression(Model model, @ModelAttribute("utilisateur") Utilisateur utilisateur, HttpSession session) {
		String resultat=null;
		try {
			identifiantSession=session.getAttribute("identifiantSession").toString().trim();
		}
		catch(Exception e) {
			resultat="pageErreur";
			return resultat;
		}
//		gestion Menu 
		model.addAttribute("gestionMenuUtilisateur", "gestionMenuUtilisateur");
		model.addAttribute("gestionMenuBanque", "gestionMenuBanque");
		model.addAttribute("gestionMenuGuichet", "gestionMenuGuichet");
		model.addAttribute("gestionMenuAgence", "gestionMenuAgence");
		model.addAttribute("gestionMenuAgent", "gestionMenuAgent");
		model.addAttribute("gestionMenuSociete", "gestionMenuSociete");
		model.addAttribute("accueilDeux", "accueilDeux");
		
		String identifiant=utilisateur.getIdentifiant().trim();
		Utilisateur utilisateurRecherche=utilisateurRepository.findByIdentifiant(identifiant);
		String identifiantAffiche=session.getAttribute("identifiantSession").toString().trim();
		model.addAttribute("identifiantSession", identifiantAffiche);
		
		if( utilisateurRecherche == null) {
			if(identifiantSession.length()>0) {
				 return "redirect:/messageUtilisateurNonRetrouver";  
			}	
		}
			
		model.addAttribute("ajout", "ajout");
		model.addAttribute("listeUtilisateur", "listeUtilisateur");
		model.addAttribute("messageUtilisateurRetrouver", "messageUtilisateurRetrouver");
		model.addAttribute("actionTroisBouton", "actionTroisBouton");
		model.addAttribute("succesSuppression", "succesSuppression");
		model.addAttribute("utilisateurRechercher", utilisateurRecherche);
		model.addAttribute("afficheTableau", "afficheTableau");
		
		
		Boolean supprimerUtil=true;
		utilisateurRecherche.setEstSupprimer(supprimerUtil);
		utilisateurRepository.save(utilisateurRecherche);
		Boolean estSupprimer=false;
		List<Utilisateur> utilisateurs=new ArrayList<Utilisateur>();
		utilisateurs=utilisateurRepository.findAllUtilisateur(estSupprimer);
		model.addAttribute("utilisateurs",utilisateurs);		
		if(identifiantSession.length()>0) {
			return "espaceUtilisateur";
		}
		else {
			return "pageErreur";
		}
			
    }
	
	
	
	@Transactional
	@RequestMapping(value = {"/resultatModif"}, method = RequestMethod.POST)
    public String resultatModif(Model model, @ModelAttribute("utilisateur") Utilisateur utilisateur, HttpSession sessionUtilisateur, HttpSession session) {  
		String resultat=null;
		try {
			identifiantSession=session.getAttribute("identifiantSession").toString().trim();
		}
		catch(Exception e) {
			resultat="pageErreur";
			return resultat;
		}
//		gestion Menu 
		model.addAttribute("gestionMenuUtilisateur", "gestionMenuUtilisateur");
		model.addAttribute("gestionMenuBanque", "gestionMenuBanque");
		model.addAttribute("gestionMenuGuichet", "gestionMenuGuichet");
		model.addAttribute("gestionMenuAgence", "gestionMenuAgence");
		model.addAttribute("gestionMenuAgent", "gestionMenuAgent");
		model.addAttribute("gestionMenuSociete", "gestionMenuSociete");
		model.addAttribute("accueilDeux", "accueilDeux");
		
		List<Utilisateur> utilisateurs=new ArrayList<Utilisateur>();
		model.addAttribute("identifiantSession", identifiantSession);
		String identifiant=utilisateur.getIdentifiant().trim();
		String fonction=utilisateur.getFonction().trim();
		String nomEtPrenom=utilisateur.getNomEtPrenom().trim();
		
		String identifiantSession=sessionUtilisateur.getAttribute("identifiantCache").toString();
		identifiantSession=identifiantSession.trim();
		
		Boolean estSupprimer=false;
		
		
		if( identifiant != null && identifiant.length() > 0 && identifiant.length()>=0 && fonction != null && fonction.length() > 0 && nomEtPrenom != null && nomEtPrenom.length() > 0 ) {
			     
			    Utilisateur utilisateurSave=utilisateurRepository.findByIdentifiant(identifiantSession);
			
				
				model.addAttribute("listeUtilisateur", "listeUtilisateur");
				model.addAttribute("actionTroisBouton", "actionTroisBouton");	
				
				utilisateurs=utilisateurRepository.findAllUtilisateur(estSupprimer);	
				model.addAttribute("ajout", "ajout");
				model.addAttribute("afficheTableau", "afficheTableau");
				model.addAttribute("utilisateurs", utilisateurs);
				
					
					/////Recuperer l'Id de l'utilisateur Recherchee
					Integer idUtilisateurRecherche=utilisateurRepository.findIdUtilisateur(identifiantSession);
					Integer idUtilisateurModif=utilisateurRepository.findIdUtilisateur(identifiant);
					Utilisateur utilisateurParIdentifiant=utilisateurRepository.findByIdentifiant(identifiant);
					
					///////
					if(utilisateurParIdentifiant!=null) {
						
						if(idUtilisateurRecherche!=idUtilisateurModif) {
							model.addAttribute("formErreur", "formErreur");
							Utilisateur utilisateurRecherche=utilisateurRepository.findByIdentifiant(identifiantSession);
							model.addAttribute("utilisateurRecherche", utilisateurRecherche);
							model.addAttribute("formModifDonneeUtil", "formModifDonneeUtil");
							model.addAttribute("identifiantErreur", "Identifiant utilisateur déjà existant");
							return "espaceUtilisateur";
							
							}
					}
					model.addAttribute("succes", "succes");   
					utilisateurSave.setFonction(fonction);
					utilisateurSave.setIdentifiant(identifiant);
					utilisateurSave.setNomEtPrenom(nomEtPrenom);
					utilisateurRepository.save(utilisateurSave);
					
		
				
				if(utilisateurSave != null) {
					if(identifiantSession.length()>0) {
						return "espaceUtilisateur";
					}					
				}
		}
		
		identifiant=utilisateur.getIdentifiant().trim();
		
		Utilisateur utilisateurRecherche=utilisateurRepository.findByIdentifiant(identifiantSession);
		System.out.println("  Utilisateur affiche "+ identifiantSession);
		model.addAttribute("formModifDonneeUtil", "formModifDonneeUtil");
		model.addAttribute("utilisateurRecherche", utilisateurRecherche);
		
		utilisateurs=utilisateurRepository.findAllUtilisateur(estSupprimer);	
		model.addAttribute("ajout", "ajout");
		model.addAttribute("afficheTableau", "afficheTableau");
		model.addAttribute("utilisateurs", utilisateurs);
		model.addAttribute("listeUtilisateur", "listeUtilisateur");
		model.addAttribute("actionTroisBouton", "actionTroisBouton");
		
		model.addAttribute("formErreur", "formErreur");
		if(identifiant==null || identifiant.length()==0) {
			model.addAttribute("identifiantErreur", "Identifiant invalide");
	
		}		
		if(nomEtPrenom==null || nomEtPrenom.length()==0) {
			model.addAttribute("nomEtPrenomErreur", "Nom et prenom invalide");
			
		}
		if(fonction==null || fonction.length()==0) {
			model.addAttribute("fonctionErreur", "Fonction invalide");
		}
		
		
			return "espaceUtilisateur";
		
    }
	
	
	@Transactional
	@RequestMapping(value = {"/resultatModifV2" }, method = RequestMethod.POST)
    public String resultatModifV2(Model model, @ModelAttribute("utilisateur") Utilisateur utilisateur, HttpSession sessionUtilisateur, HttpSession session) {  
		String resultat=null;
		try {
			identifiantSession=session.getAttribute("identifiantSession").toString().trim();
		}
		catch(Exception e) {
			resultat="pageErreur";
			return resultat;
		}
//		gestion Menu 
		model.addAttribute("gestionMenuUtilisateur", "gestionMenuUtilisateur");
		model.addAttribute("gestionMenuBanque", "gestionMenuBanque");
		model.addAttribute("gestionMenuGuichet", "gestionMenuGuichet");
		model.addAttribute("gestionMenuAgence", "gestionMenuAgence");
		model.addAttribute("gestionMenuAgent", "gestionMenuAgent");
		model.addAttribute("gestionMenuSociete", "gestionMenuSociete");
		model.addAttribute("accueilDeux", "accueilDeux");
		
		List<Utilisateur> utilisateurs=new ArrayList<Utilisateur>();

		if(identifiantSession.length()>0) {
			return "espaceUtilisateur";
		}
		else {
			return "pageErreur";
		}     
    }
	
	@Transactional
	@RequestMapping(value = {"/listeAgentsDansAgence" }, method = RequestMethod.GET)
    public String listeAgentsDansAgence(Model model, @ModelAttribute("utilisateur") Utilisateur utilisateur,@PageableDefault(size = 4) Pageable pageable , HttpSession session, HttpServletRequest request ) {  
		String resultat=null;
		try {
			identifiantSession=session.getAttribute("identifiantSession").toString().trim();
		}
		catch(Exception e) {
			resultat="pageErreur";
			return resultat;
		}
//		gestion Menu 
		model.addAttribute("gestionMenuUtilisateur", "gestionMenuUtilisateur");
		model.addAttribute("gestionMenuBanque", "gestionMenuBanque");
		model.addAttribute("gestionMenuGuichet", "gestionMenuGuichet");
		model.addAttribute("gestionMenuAgence", "gestionMenuAgence");
		model.addAttribute("gestionMenuAgent", "gestionMenuAgent");
		model.addAttribute("gestionMenuSociete", "gestionMenuSociete");
		model.addAttribute("accueilDeux", "accueilDeux");
		
		List<Agent> agents=new ArrayList<Agent>();		
		
		String identifiantConnecte=session.getAttribute("identifiantSession").toString().trim(); 
				
		utilisateur=utilisateurRepository.findByIdentifiant(identifiantConnecte);
		Agence agence=utilisateur.getIdAgence();
		agents=agentRepository.findAgentsByIdAgence(agence);
		model.addAttribute("cheminAccueil", "Accueil >");
		model.addAttribute("listeAgentsDansAgence", "Liste des Agents > ");
		model.addAttribute("agents", agents);
		model.addAttribute("identifiantSession", identifiantSession);
		model.addAttribute("contenuAccueilUtilisateur", "contenuAccueilUtilisateur");
		model.addAttribute("menuNavigation", "menuNavigation");
		
		
		int page = 0;
		int size = 4;
		 if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
	            page = Integer.parseInt(request.getParameter("page")) - 1;
	        }
		 if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
	            size = Integer.parseInt(request.getParameter("size"));
	        }
		 
		 Boolean estSupprimer=false;
		 pageable = PageRequest.of(page, size);
	     Page<Agent> agentPage = agentRepository.findAllAgentsPage(estSupprimer, pageable);
		 model.addAttribute("agentsPage", agentPage);
		
        return "utilisateur/accueilUtilisateur";		
    }
	
	
	@Transactional
	@RequestMapping(value = "/agentsPage")
    public ModelAndView listAgentsPageByPage(@RequestParam(name="page", defaultValue="0") int page, @ModelAttribute("utilisateur") Utilisateur utilisateur ,@RequestParam(name="size", defaultValue="10") int size,  @ModelAttribute("client") Client client, Model model, HttpSession session, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("utilisateur/accueilUtilisateur");
        Boolean estSupprimer=false;
        PageRequest pageable = PageRequest.of(0, 4);
        Page<Agent> agentPage = agentRepository.findAllAgentsPage(estSupprimer, pageable);
		 if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
	            page = Integer.parseInt(request.getParameter("page")) - 1;
	        }
		 if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
	            size = Integer.parseInt(request.getParameter("size"));
	        }
		model.addAttribute("agentsPage", agentPage);
				
		model.addAttribute("cheminAccueil",  "Accueil >");		
        model.addAttribute("identifiantSession", identifiantSession);
		model.addAttribute("identifiantSession", identifiantSession);
		List<Agent> agents=new ArrayList<Agent>();		
		
		String identifiantConnecte=session.getAttribute("identifiantSession").toString().trim(); 
				
		utilisateur=utilisateurRepository.findByIdentifiant(identifiantConnecte);
		Agence agence=utilisateur.getIdAgence();
		agents=agentRepository.findAgentsByIdAgence(agence);
		model.addAttribute("cheminAccueil", "Accueil >");
		model.addAttribute("listeAgentsDansAgence", "Liste des Agents > ");
		model.addAttribute("agents", agents);
		model.addAttribute("menuNavigation", "menuNavigation");
        return modelAndView;
    }
	
	

	
	
	
	
	
	
	

}
