package com.belife.policemanager.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import com.belife.policemanager.model.aaronentity.AaronUtilisateur;
import com.belife.policemanager.model.aaronrepository.AaronUtilisateurRepository;
import com.belife.policemanager.model.dto.UtilisateurDto;
import com.belife.policemanager.model.entity.Agence;
import com.belife.policemanager.model.entity.Connexion;
import com.belife.policemanager.model.entity.Roles;
import com.belife.policemanager.model.entity.SourcePolice;
import com.belife.policemanager.model.entity.Utilisateur;
import com.belife.policemanager.model.repository.AgenceRepository;
import com.belife.policemanager.model.repository.AgentRepository;
import com.belife.policemanager.model.repository.ConnexionRepository;
import com.belife.policemanager.model.repository.RolesRepository;
import com.belife.policemanager.model.repository.SocieteRepository;
import com.belife.policemanager.model.repository.SourcePoliceRepository;
import com.belife.policemanager.model.repository.UtilisateurRepository;

@Controller
public class UtilisateurController {

	@Autowired
    UtilisateurRepository utilisateurRepository; 
	
	@Autowired
    AaronUtilisateurRepository aaronUtilisateurRepository; 
	 
	 @Autowired
    RolesRepository rolesRepository;
	 
	 @Autowired
    SourcePoliceRepository sourcePoliceRepository;
	 
	 @Autowired
    AgenceRepository agenceRepository;
	 
	 @Autowired
    AgentRepository agentRepository;
	 
	 @Autowired
    SocieteRepository societeRepository;
	 
	 @Autowired
	 ConnexionRepository connexionRepository;
		 
	 
	 private String fonctioInformaticien="Informaticien";
	 private String fonctionMedecin="Medecin";
	 private String fonctionSecretaire="Secretaire";
	 private String fonctionCommercial="Commercial";
	 private String fonctionUniteTechnique="Technicien";
	 private String fonctionChefAgence="Chef agence";
	 private String fonctionChefSousAgence="Chef sous agence";
	 private String fonctionEmploye="Employé";
	 private String fonctionAdministrateur="Administrateur";
	 
	 private String aaronInformaticien="aaron-Informaticien";
	 private String aaronMedecin="aaron-Medecin";
	 private String aaronSecretaire="aaron-Secretaire";
	 private String aaronCommercial="aaron-Commercial";
	 private String aaronUniteTechnique="aaron-Technicien";
	 private String aaronChefAgence="aaron-Chef agence";
	 private String aaronChefSousAgence="aaron-Chef sous agence";
	 private String aaronEmploye="aaron-Employé";
	 private String aaronAdministrateur="aaron-Administrateur";
	 
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
		String resultat=null;
		try {
			identifiantSession=session.getAttribute("identifiantSession").toString().trim();
		}
		catch(Exception e) {
			resultat="pageErreur";
			return resultat;
		}
		Utilisateur utili=utilisateurRepository.findByIdentifiant(identifiantSession);
		Boolean connecter=false;
	
	    session.removeAttribute("identifiantSession");
	    session.removeAttribute("identifiantCache");
	    session.removeAttribute("codeGuichetCache");
	    session.removeAttribute("codeSocieteCache");
	    session.removeAttribute("codeAgenceCache"); 
	    session.removeAttribute("nomSourceCache");
//	    utilisateur.setConnecter(false);
       return "redirect:/authentification";
   }
	
	@Transactional
	@RequestMapping(value = {"/authentification" }, method = RequestMethod.GET)
   public String authentification(Model model) {    
       return "authentification";
   }
	
	@Transactional
	@RequestMapping(value = {"/resultatModifDonnee" }, method = RequestMethod.POST)
   public String resultatModifDonnee(Model model, @ModelAttribute("utilisateur") Utilisateur utilisateur, HttpSession session, @PageableDefault(size = 100) Pageable pageable, HttpServletRequest request) { 
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
//		gestion Menu 
		model.addAttribute("gestionMenuUtilisateur", "gestionMenuUtilisateur");
		model.addAttribute("gestionMenuBanque", "gestionMenuBanque");
		model.addAttribute("gestionMenuGuichet", "gestionMenuGuichet");
		model.addAttribute("gestionMenuAgence", "gestionMenuAgence");
		model.addAttribute("gestionMenuAgent", "gestionMenuAgent");
		model.addAttribute("gestionMenuSociete", "gestionMenuSociete");
		model.addAttribute("accueilDeux", "accueilDeux");
		model.addAttribute("cheminAccueil", "Accueil >");
		model.addAttribute("titre", "Gestion des utilisateurs");
		model.addAttribute("cheminGestionUtilisateur", "Gestion Utilisateur >");
		model.addAttribute("cheminModifierUtilisateur", "Desactiver Utilisateur >");
		
		Utilisateur utilisateurRenvoi=new Utilisateur();
		List<Utilisateur> utilisateurs=new ArrayList<Utilisateur>();
		String identifiantAffiche=session.getAttribute("identifiantSession").toString().trim();
		model.addAttribute("identifiantSession", identifiantAffiche);
		String identifiant=utilisateur.getIdentifiant().trim();
		System.out.println(" Identifiant session utilisateur : "+identifiant);
		identifiantSession=identifiantSession.trim();
		
		model.addAttribute("dialog_background", "dialog_background");
		model.addAttribute("identifiantSession",identifiantSession);
		model.addAttribute("dialog_box", "dialog_box");
		
		model.addAttribute("listeUtilisateur", "listeUtilisateur");
		
		
		Page<Utilisateur> utilisateursPage =utilisateurRepository.findAllUtilisateurPage(pageable);	
		model.addAttribute("utilisateurs",utilisateursPage);
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
   public String sourcePrelevement(Model model, HttpSession session, @PageableDefault(size = 100) Pageable pageable, HttpServletRequest request) { 
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
//		gestion Menu 
		model.addAttribute("gestionMenuUtilisateur", "gestionMenuUtilisateur");
		model.addAttribute("gestionMenuBanque", "gestionMenuBanque");
		model.addAttribute("gestionMenuGuichet", "gestionMenuGuichet");
		model.addAttribute("gestionMenuAgence", "gestionMenuAgence");
		model.addAttribute("gestionMenuAgent", "gestionMenuAgent");
		model.addAttribute("gestionMenuSociete", "gestionMenuSociete");
		model.addAttribute("accueilDeux", "accueilDeux");
		model.addAttribute("gestionConnexion", "gestionConnexion");
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
		model.addAttribute("gestionMenuAgenceBanque", "gestionMenuAgenceBanque");
		model.addAttribute("gestionConnexion", "gestionConnexion");
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
		model.addAttribute("gestionMenuAgenceBanque", "gestionMenuAgenceBanque");
		model.addAttribute("gestionConnexion", "gestionConnexion");
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
	@RequestMapping(value = {"/accueil" }, method = RequestMethod.POST)
   public String accueil(Model model,  @ModelAttribute("utilisateur") Utilisateur utilisateur, HttpSession session) { 
		model.addAttribute("accueilAdmin", "accueilAdmin");
		String identifiant=utilisateur.getIdentifiant().trim();
		String identifiantSession=null;
		String password=utilisateur.getPassword().trim();
		Utilisateur utilisateurParIdentifiant=null;
		Utilisateur utilisateurParPassword=null;
		String utilisateurPassword=null;
		
		AaronUtilisateur aaronUtilisateurIdentifiant=null;
		AaronUtilisateur aaronUtilisateurPassword=null;
		
		try {
//			String connecter=session.getAttribute("connecter").toString();
			Integer connect=session.getMaxInactiveInterval();
			 Date accessed = new Date(session.getMaxInactiveInterval());
			System.out.println("  Temps : "+connect );
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		

		if( identifiant != null && identifiant.length() > 0  && password != null && password.length() > 0 ) {
			
			utilisateurParIdentifiant=utilisateurRepository.findByIdentifiant(identifiant);	
			utilisateurParPassword=utilisateurRepository.findByPassword(password);
			
			aaronUtilisateurIdentifiant=aaronUtilisateurRepository.findByIdentifiant(identifiant);
			aaronUtilisateurPassword=aaronUtilisateurRepository.findByPassword(password);	
			
			if(aaronUtilisateurIdentifiant!=null && aaronUtilisateurPassword!=null ) {
				 session.setAttribute("identifiantSession", utilisateur.getIdentifiant().trim());
			     String aaronFonction=aaronUtilisateurIdentifiant.getFonction();
			     identifiantSession=session.getAttribute("identifiantSession").toString().trim();
				 model.addAttribute("identifiantSession", identifiantSession);
			     if(aaronFonction.equals(aaronAdministrateur)) {
			    	    session.setAttribute("connecter", "connecter");
			    	 	model.addAttribute("cheminAccueil", "Accueil >");
						model.addAttribute("accueilAdmin", "accueilAdmin");
						model.addAttribute("gestionMenuUtilisateur", "gestionMenuUtilisateur");
						model.addAttribute("gestionMenuBanque", "gestionMenuBanque");
						model.addAttribute("gestionMenuGuichet", "gestionMenuGuichet");
						model.addAttribute("gestionMenuAgence", "gestionMenuAgence");
						model.addAttribute("gestionMenuAgent", "gestionMenuAgent"); 
						model.addAttribute("gestionMenuSociete", "gestionMenuSociete");
						model.addAttribute("gestionConnexion", "gestionConnexion");
						model.addAttribute("messageAccueilEspaceAdmin", "messageAccueilEspaceAdmin");
						model.addAttribute("accueilDeux", "accueilDeux");
						session.setAttribute("identifiantSession", utilisateur.getIdentifiant().trim());
						identifiantSession=session.getAttribute("identifiantSession").toString().trim();
						model.addAttribute("identifiantSession", identifiantSession);					
						return "aaron/espaceUtilisateur";
			    	 
			     }else if(aaronFonction.equals(aaronUniteTechnique)) {
			    	    session.setAttribute("connecter", "connecter");
			    	 	model.addAttribute("cheminAccueil", "Accueil >");
//						gestion Menu 			
						model.addAttribute("gestionMenuUniteTechnique", "gestionMenuUniteTechnique");
						model.addAttribute("accueilUniteTechnique", "accueilUniteTechnique");	
						model.addAttribute("accueilDeuxUniteTechnique", "accueilDeuxUniteTechnique");	
						model.addAttribute("accueilUniteTechniqueMessage", "accueilUniteTechniqueMessage");	
						return "aaron/espaceUtilisateur";
			    	 		    	 
			     }else {
			    	   return "redirect:/aaronAccueilUtilisateurLien";
			    	 
			     }		
			}
			
			
			if(utilisateurParIdentifiant != null && utilisateurParPassword !=null ) {					
				utilisateurPassword=utilisateurParIdentifiant.getPassword();			
				String passNouveau=utilisateurPassword.trim();
							
				if( passNouveau.equals(password) ) {
					 session.setAttribute("identifiantSession", utilisateur.getIdentifiant().trim());	
					 identifiantSession=session.getAttribute("identifiantSession").toString().trim();
					 model.addAttribute("identifiantSession", identifiantSession);
					 String fonction=utilisateurRepository.fonctionUtilisateur(identifiantSession);
//					
					 if(fonction.equals(fonctionAdministrateur)   ) {
						 session.setAttribute("connecter", "connecter");
						 model.addAttribute("cheminAccueil", "Accueil >");
						 model.addAttribute("accueilAdmin", "accueilAdmin");
//						 gestion Menu 
						 model.addAttribute("gestionConnexion", "gestionConnexion");
						 model.addAttribute("gestionMenuUtilisateur", "gestionMenuUtilisateur");
						 model.addAttribute("gestionMenuAgenceBanque", "gestionMenuAgenceBanque");
						 model.addAttribute("gestionMenuBanque", "gestionMenuBanque");
						 model.addAttribute("gestionMenuGuichet", "gestionMenuGuichet");
						 model.addAttribute("gestionMenuAgence", "gestionMenuAgence");
						 model.addAttribute("gestionMenuAgent", "gestionMenuAgent"); 
						 model.addAttribute("gestionMenuSociete", "gestionMenuSociete");
						 model.addAttribute("messageAccueilEspaceAdmin", "messageAccueilEspaceAdmin");
						 model.addAttribute("accueilDeux", "accueilDeux");
						 
						 Date dateConnexion=new Date();
						 SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy 'à' HH:mm:ss ");					
						 String tempsConnexion=sdf.format( dateConnexion);
						 InetAddress address=null;
						 String adresseIP=null;
						 try {
							 address = InetAddress.getLocalHost();
							 adresseIP= address.getHostAddress();
						} catch (UnknownHostException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}						 
						Utilisateur idUtilisateur=utilisateurRepository.findByIdentifiant(identifiantSession); 
						Connexion conn=new Connexion(tempsConnexion,adresseIP,idUtilisateur);
						connexionRepository.save(conn);
						
						 return "espaceUtilisateur";
					 }
					 else if(fonction.equals(fonctionUniteTechnique)  ) {
						
						 model.addAttribute("cheminAccueil", "Accueil >");
//							gestion Menu 			
							model.addAttribute("gestionMenuUniteTechnique", "gestionMenuUniteTechnique");
							model.addAttribute("accueilUniteTechnique", "accueilUniteTechnique");	
							model.addAttribute("accueilDeuxUniteTechnique", "accueilDeuxUniteTechnique");	
							model.addAttribute("accueilUniteTechniqueMessage", "accueilUniteTechniqueMessage");	
						
							return "espaceUtilisateur";
						 
					 }
					 else if( fonction.equals(fonctionSecretaire)  ) {
						 session.setAttribute("connecter", "connecter");
						
						 return "redirect:/accueilUtilisateurLien";
					 }	
					 else if( fonction.equals(fonctionChefAgence) ) {
						 session.setAttribute("connecter", "connecter");
					
						 return "redirect:/accueilUtilisateurLien";
					 }	
//					 else if( fonction.equals(aaronChefAgence)  && utilisateurParPassword.getConnecter()==false) {
//						 utilisateurParPassword.setConnecter(true);
//						 return "redirect:/accueilUtilisateurLien";
//					 }	
//					 	
				} 
				
				
				
			}
		}		 
		 model.addAttribute("hasError", " Identifiant ou mot de passe incorrect");
		 model.addAttribute("password", password);
		 model.addAttribute("identifiant", identifiant);
       return "authentification";
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
		
		
//		Boolean estSupprimer=false;
//		utilisateurs=utilisateurRepository.findAllUtilisateur(estSupprimer);
//		model.addAttribute("utilisateurs", utilisateurs);
//		
		
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
		model.addAttribute("gestionMenuAgenceBanque", "gestionMenuAgenceBanque");
		model.addAttribute("gestionMenuUtilisateur", "gestionMenuUtilisateur");
		model.addAttribute("gestionMenuBanque", "gestionMenuBanque");
		model.addAttribute("gestionMenuGuichet", "gestionMenuGuichet");
		model.addAttribute("gestionMenuAgence", "gestionMenuAgence");
		model.addAttribute("gestionMenuAgent", "gestionMenuAgent");
		model.addAttribute("gestionMenuSociete", "gestionMenuSociete");
		model.addAttribute("messageAccueilEspaceAdmin", "messageAccueilEspaceAdmin");
		model.addAttribute("gestionConnexion", "gestionConnexion");
		model.addAttribute("accueilDeux", "accueilDeux");
		model.addAttribute("gestionMenuAgenceBanque", "gestionMenuAgenceBanque");	
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
   public String listeUtilisateur(Model model, @ModelAttribute("utilisateur") Utilisateur utilisateur, HttpSession sessionn, HttpSession session, @PageableDefault(size = 100) Pageable pageable, HttpServletRequest request) { 
		String resultat=null;
		try {
			identifiantSession=session.getAttribute("identifiantSession").toString().trim();
		}
		catch(Exception e) {
			resultat="pageErreur";
			return resultat;
		}
//		gestion Menu
		model.addAttribute("gestionMenuAgenceBanque", "gestionMenuAgenceBanque");
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
		model.addAttribute("gestionConnexion", "gestionConnexion");
		int page = 0;
		int size = 100;			 
		pageable = PageRequest.of(page, size);
		Page<Utilisateur> utilisateurs=utilisateurRepository.findAllUtilisateurPage(pageable);
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
   public String formulaireAjoutUtilisateur(Model model, @ModelAttribute("utilisateur") Utilisateur utilisateur, HttpSession session, @PageableDefault(size = 100) Pageable pageable, HttpServletRequest request) { 
		String resultat=null;
		try {
			identifiantSession=session.getAttribute("identifiantSession").toString().trim();
		}
		catch(Exception e) {
			resultat="pageErreur";
			return resultat;
		}
//		gestion Menu 
		model.addAttribute("gestionMenuAgenceBanque", "gestionMenuAgenceBanque");
		model.addAttribute("gestionMenuUtilisateur", "gestionMenuUtilisateur");
		model.addAttribute("gestionMenuBanque", "gestionMenuBanque");
		model.addAttribute("gestionMenuGuichet", "gestionMenuGuichet");
		model.addAttribute("gestionMenuAgence", "gestionMenuAgence");
		model.addAttribute("gestionMenuAgent", "gestionMenuAgent");
		model.addAttribute("gestionMenuSociete", "gestionMenuSociete");
		model.addAttribute("accueilDeux", "accueilDeux");
		model.addAttribute("gestionConnexion", "gestionConnexion");
		Boolean estSupprimer=false;
		List<String> agences=new ArrayList<String>();
		agences=agenceRepository.findAllNomDirects();
		model.addAttribute("agences",agences);
		model.addAttribute("ajout", "ajout");
		
		model.addAttribute("cheminAccueil", "Accueil >");
		model.addAttribute("titre", "Gestion des utilisateurs");
		model.addAttribute("cheminGestionUtilisateur", "Gestion Utilisateur >");
		model.addAttribute("cheminAjouterUtilisateur", "Ajouter Utilisateur >");
		
		model.addAttribute("formulaireAjoutUtil", "formulaireAjoutUtil");
		model.addAttribute("listeUtilisateur", "listeUtilisateur");
		model.addAttribute("actionTroisBouton", "actionTroisBouton");
		int page = 0;
		int size = 100;			 
		pageable = PageRequest.of(page, size);
		Page<Utilisateur> utilisateurs=utilisateurRepository.findAllUtilisateurPage(pageable);
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
   public String ajoutUtilisateur(Model model, @ModelAttribute("utilisateur") Utilisateur utilisateur, @ModelAttribute("utilisateurDto") UtilisateurDto utilisateurDto, HttpSession session, @PageableDefault(size = 100) Pageable pageable, HttpServletRequest request) { 
		String resultat=null;
		try {
			identifiantSession=session.getAttribute("identifiantSession").toString().trim();
		}
		catch(Exception e) {
			resultat="pageErreur";
			return resultat;
		}
//		gestion Menu 
		model.addAttribute("gestionMenuAgenceBanque", "gestionMenuAgenceBanque");
		model.addAttribute("gestionMenuUtilisateur", "gestionMenuUtilisateur");
		model.addAttribute("gestionMenuBanque", "gestionMenuBanque");
		model.addAttribute("gestionMenuGuichet", "gestionMenuGuichet");
		model.addAttribute("gestionMenuAgence", "gestionMenuAgence");
		model.addAttribute("gestionMenuAgent", "gestionMenuAgent");
		model.addAttribute("gestionMenuSociete", "gestionMenuSociete");
		model.addAttribute("accueilDeux", "accueilDeux");
		model.addAttribute("cheminAccueil", "Accueil >");
		model.addAttribute("cheminGestionUtilisateur", "Gestion Utilisateur >");
		model.addAttribute("cheminAjouterUtilisateur", "Ajouter Utilisateur >");
		model.addAttribute("gestionConnexion", "gestionConnexion");
		Utilisateur utilisateurSave=null;
		Utilisateur utilisateurPassword=null;
		AaronUtilisateur utilisateurAron=null;
		String identifiant=utilisateurDto.getIdentifiant().trim();
		String password=utilisateurDto.getPassword().trim();
		String nomEtPrenom=utilisateurDto.getNomEtPrenom().trim();
		String fonction=utilisateurDto.getFonction().trim();
		String nomAgence=utilisateurDto.getNomAgence().trim();
		
		Boolean estSupprimer=false;
		model.addAttribute("listeUtilisateur", "listeUtilisateur");
		model.addAttribute("actionTroisBouton", "actionTroisBouton");
		int page = 0;
		int size = 100;			 
		pageable = PageRequest.of(page, size);
		Page<Utilisateur> utilisateurs=utilisateurRepository.findAllUtilisateurPage(pageable);
		
		if( identifiant != null && identifiant.length() > 0 && identifiant.length()<=10 && password != null && password.length() > 0 && password.length()>4 && nomEtPrenom != null && nomEtPrenom.length() > 0 && fonction != null && fonction.length() > 0 ) {
			
			utilisateurSave=utilisateurRepository.findByIdentifiant(identifiant);
			utilisateurPassword=utilisateurRepository.findByPassword(password);
			utilisateurAron=aaronUtilisateurRepository.findByPassword(password);
				if(utilisateurSave == null  && utilisateurPassword==null && utilisateurAron==null) {
					String status="A";
					utilisateur.setStatus(status);
					utilisateur.setFonction(fonction);
					utilisateur.setIdentifiant(identifiant);
					utilisateur.setNomEtPrenom(nomEtPrenom);
					utilisateur.setPassword(password);
					utilisateur.setDateCreation(new Date());
					
					Agence agence=agenceRepository.findAgenceByNomDirect(nomAgence);
					utilisateur.setIdAgence(agence);
					utilisateurRepository.save(utilisateur);	
					model.addAttribute("ajout", "ajout");
					model.addAttribute("afficheTableau", "afficheTableau");
					model.addAttribute("utilisateurs", utilisateurs);					
					model.addAttribute("succes", "succes");
//					model.addAttribute("choisirRole", "choisirRole");
					String identifiantSession=session.getAttribute("identifiantSession").toString().trim();
					 model.addAttribute("identifiantSession", identifiantSession);
					if(identifiantSession.length()>0)
						return "espaceUtilisateur";
				}				
		}
		if(utilisateurSave!=null ) {
			model.addAttribute("identifiantErreur", "Identifiant déjà existant.");
		}
		if(utilisateurPassword!=null || utilisateurAron!=null ) {
			model.addAttribute("passwordErreur", " Mot de passe déjà existant.");
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
			agences=agenceRepository.findAllNomDirects();
			model.addAttribute("agences",agences);
			model.addAttribute("identifiantSession", identifiantSession);	
			model.addAttribute("ajout", "ajout");
			model.addAttribute("afficheTableau", "afficheTableau");
			model.addAttribute("utilisateurs", utilisateurs);
			model.addAttribute("formulaireAjoutUtil", "formulaireAjoutUtil");
		
				return "espaceUtilisateur";    
   }
	
	
	@Transactional
	@RequestMapping(value = {"/numeroModifUtilisateur" }, method = RequestMethod.GET)
   public String numeroModifUtilisateur(Model model, HttpSession session, @PageableDefault(size = 100) Pageable pageable, HttpServletRequest request) { 
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
		
//		gestion Menu 
		model.addAttribute("gestionMenuAgenceBanque", "gestionMenuAgenceBanque");
		model.addAttribute("gestionMenuUtilisateur", "gestionMenuUtilisateur");
		model.addAttribute("gestionMenuBanque", "gestionMenuBanque");
		model.addAttribute("gestionMenuGuichet", "gestionMenuGuichet");
		model.addAttribute("gestionMenuAgence", "gestionMenuAgence");
		model.addAttribute("gestionMenuAgent", "gestionMenuAgent");
		model.addAttribute("gestionMenuSociete", "gestionMenuSociete");
		model.addAttribute("accueilDeux", "accueilDeux");
		model.addAttribute("gestionConnexion", "gestionConnexion");
		model.addAttribute("ajout", "ajout");
		model.addAttribute("listeUtilisateur", "listeUtilisateur");
		model.addAttribute("formNumeroModifUtil", "formNumeroModifUtil");
		model.addAttribute("cheminAccueil", "Accueil >");
		model.addAttribute("titre", "Gestion des utilisateurs");
		model.addAttribute("cheminGestionUtilisateur", "Gestion Utilisateur >");
		model.addAttribute("cheminModifierUtilisateur", "Modifier Utilisateur >");
		model.addAttribute("actionTroisBouton", "actionTroisBouton");
		model.addAttribute("afficheTableau", "afficheTableau");
		Boolean estSupprimer=false;
		Page<Utilisateur> utilisateurs=utilisateurRepository.findAllUtilisateurPage(pageable);
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
   public String formModifDonneeUtil(Model model, @ModelAttribute("utilisateur") Utilisateur utilisateur, HttpSession session, @PageableDefault(size = 100) Pageable pageable, HttpServletRequest request) {
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
//		gestion Menu 
		model.addAttribute("gestionMenuAgenceBanque", "gestionMenuAgenceBanque");
		model.addAttribute("gestionMenuUtilisateur", "gestionMenuUtilisateur");
		model.addAttribute("gestionMenuBanque", "gestionMenuBanque");
		model.addAttribute("gestionMenuGuichet", "gestionMenuGuichet");
		model.addAttribute("gestionMenuAgence", "gestionMenuAgence");
		model.addAttribute("gestionMenuAgent", "gestionMenuAgent");
		model.addAttribute("gestionMenuSociete", "gestionMenuSociete");
		model.addAttribute("accueilDeux", "accueilDeux");
		model.addAttribute("cheminAccueil", "Accueil >");
		model.addAttribute("titre", "Gestion des utilisateurs");
		model.addAttribute("cheminGestionUtilisateur", "Gestion Utilisateur >");
		model.addAttribute("cheminModifierUtilisateur", "Modifier Utilisateur >");
		Page<Utilisateur> utilisateurs=utilisateurRepository.findAllUtilisateurPage(pageable);
		String identifiant=utilisateur.getIdentifiant().trim();
		session.setAttribute("identifiantCache", utilisateur.getIdentifiant());
		model.addAttribute("gestionConnexion", "gestionConnexion");
		Utilisateur utilisateurRecherche=utilisateurRepository.findByIdentifiant(identifiant);
			
		model.addAttribute("afficheTableau", "afficheTableau");
		if( utilisateurRecherche == null) {
			 String identifiantSession=session.getAttribute("identifiantSession").toString().trim();
			 model.addAttribute("identifiantSession", identifiantSession);
			 return "redirect:/messageUtilisateurNonExistant";  
		}
		
		List<String> agences=new ArrayList<String>();
		agences=agenceRepository.findAllNomDirects();
		model.addAttribute("agences",agences);		
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
		Boolean estSupprimer=false;
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
   public String formModifDonneeUtilV2(Model model, @ModelAttribute("utilisateur") Utilisateur utilisateur, HttpSession sessionUtilisateur, HttpSession session, @PageableDefault(size = 100) Pageable pageable, HttpServletRequest request) {
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
//		gestion Menu 
		model.addAttribute("gestionMenuAgenceBanque", "gestionMenuAgenceBanque");
		model.addAttribute("gestionMenuUtilisateur", "gestionMenuUtilisateur");
		model.addAttribute("gestionMenuBanque", "gestionMenuBanque");
		model.addAttribute("gestionMenuGuichet", "gestionMenuGuichet");
		model.addAttribute("gestionMenuAgence", "gestionMenuAgence");
		model.addAttribute("gestionMenuAgent", "gestionMenuAgent");
		model.addAttribute("gestionMenuSociete", "gestionMenuSociete");
		model.addAttribute("accueilDeux", "accueilDeux");
		model.addAttribute("cheminAccueil", "Accueil >");
		model.addAttribute("titre", "Gestion des utilisateurs");
		model.addAttribute("cheminGestionUtilisateur", "Gestion Utilisateur >");
		model.addAttribute("cheminModifierUtilisateur", "Modifier Utilisateur >");
		model.addAttribute("gestionConnexion", "gestionConnexion");
		String identifiant=session.getAttribute("identifiantCache").toString().trim();
		sessionUtilisateur.setAttribute("identifiant", utilisateur.getIdentifiant());
		
		Utilisateur utilisateurRecherche=utilisateurRepository.findByIdentifiant(identifiant);
			
		model.addAttribute("afficheTableau", "afficheTableau");
		if( utilisateurRecherche == null) {
			 String identifiantSession=session.getAttribute("identifiantSession").toString().trim();
			 model.addAttribute("identifiantSession", identifiantSession);
			 return "redirect:/messageUtilisateurNonExistant";  
		}
		
		List<String> agences=new ArrayList<String>();
		agences=agenceRepository.findAllNomDirects();
		model.addAttribute("agences",agences);	
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
		
		Page<Utilisateur> utilisateurs=utilisateurRepository.findAllUtilisateurPage(pageable);
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
   public String messageUtilisateurNonExistant(Model model, @ModelAttribute("utilisateur") Utilisateur utilisateur, HttpSession session, @PageableDefault(size = 100) Pageable pageable, HttpServletRequest request) {  
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
//		gestion Menu 
		model.addAttribute("gestionMenuAgenceBanque", "gestionMenuAgenceBanque");
		model.addAttribute("gestionMenuUtilisateur", "gestionMenuUtilisateur");
		model.addAttribute("gestionMenuBanque", "gestionMenuBanque");
		model.addAttribute("gestionMenuGuichet", "gestionMenuGuichet");
		model.addAttribute("gestionMenuAgence", "gestionMenuAgence");
		model.addAttribute("gestionMenuAgent", "gestionMenuAgent");
		model.addAttribute("gestionMenuSociete", "gestionMenuSociete");
		model.addAttribute("accueilDeux", "accueilDeux");
		model.addAttribute("gestionConnexion", "gestionConnexion");
		model.addAttribute("ajout", "ajout");
		model.addAttribute("listeUtilisateur", "listeUtilisateur");
		model.addAttribute("messageUtilisateurNonExistant", "messageUtilisateurNonExistant");
		model.addAttribute("actionTroisBouton", "actionTroisBouton");
		model.addAttribute("afficheTableau", "afficheTableau");
		
		Page<Utilisateur> utilisateurs=utilisateurRepository.findAllUtilisateurPage(pageable);
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
   public String rechercheUtilisateur(Model model, @ModelAttribute("utilisateur") Utilisateur utilisateur, HttpSession session, @PageableDefault(size = 100) Pageable pageable, HttpServletRequest request) { 
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
//		gestion Menu 
		model.addAttribute("gestionMenuAgenceBanque", "gestionMenuAgenceBanque");
		model.addAttribute("gestionMenuUtilisateur", "gestionMenuUtilisateur");
		model.addAttribute("gestionMenuBanque", "gestionMenuBanque");
		model.addAttribute("gestionMenuGuichet", "gestionMenuGuichet");
		model.addAttribute("gestionMenuAgence", "gestionMenuAgence");
		model.addAttribute("gestionMenuAgent", "gestionMenuAgent");
		model.addAttribute("gestionMenuSociete", "gestionMenuSociete");
		model.addAttribute("accueilDeux", "accueilDeux");
		model.addAttribute("gestionConnexion", "gestionConnexion");
		model.addAttribute("ajout", "ajout");
		model.addAttribute("listeUtilisateur", "listeUtilisateur");
		model.addAttribute("rechercheUtilisateur", "rechercheUtilisateur");
		model.addAttribute("actionTroisBouton", "actionTroisBouton");
		model.addAttribute("cheminAccueil", "Accueil >");
		model.addAttribute("titre", "Gestion des utilisateurs");
		model.addAttribute("cheminGestionUtilisateur", "Gestion Utilisateur >");
		model.addAttribute("cheminRechercherUtilisateur", "Rechercher Utilisateur >");
		
//		model.addAttribute("afficheTableau", "afficheTableau");
		String identifiantSession=session.getAttribute("identifiantSession").toString().trim();
		model.addAttribute("identifiantSession", identifiantSession);
		Page<Utilisateur> utilisateurs=utilisateurRepository.findAllUtilisateurPage(pageable);

			return "espaceUtilisateur";
   }
	
	@Transactional
	@RequestMapping(value = {"/succesRecherche" }, method = RequestMethod.POST)
   public String succesRecherche(Model model, @ModelAttribute("utilisateur") Utilisateur utilisateur, HttpSession session, @PageableDefault(size = 100) Pageable pageable, HttpServletRequest request) {
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
//		gestion Menu 
		model.addAttribute("gestionMenuAgenceBanque", "gestionMenuAgenceBanque");
		model.addAttribute("gestionMenuUtilisateur", "gestionMenuUtilisateur");
		model.addAttribute("gestionMenuBanque", "gestionMenuBanque");
		model.addAttribute("gestionMenuGuichet", "gestionMenuGuichet");
		model.addAttribute("gestionMenuAgence", "gestionMenuAgence");
		model.addAttribute("gestionMenuAgent", "gestionMenuAgent");
		model.addAttribute("gestionMenuSociete", "gestionMenuSociete");
		model.addAttribute("accueilDeux", "accueilDeux");
		model.addAttribute("gestionConnexion", "gestionConnexion");
		String identifiantAffiche=session.getAttribute("identifiantSession").toString().trim();
		model.addAttribute("identifiantSession", identifiantAffiche);
		String identifiant=utilisateur.getIdentifiant().trim();
		Utilisateur utilisateurRecherche=utilisateurRepository.findByIdentifiant(identifiant);
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
		
		//model.addAttribute("afficheTableau", "afficheTableau");
		Page<Utilisateur> utilisateurs=utilisateurRepository.findAllUtilisateurPage(pageable);
		model.addAttribute("utilisateurs", utilisateurs);	
	
	    return "espaceUtilisateur";		
   }
	
	@Transactional
	@RequestMapping(value = {"/messageUtilisateurNonRetrouver" }, method = RequestMethod.GET)
   public String messageUtilisateurNonRetrouver(Model model, @ModelAttribute("utilisateur") Utilisateur utilisateur, HttpSession session, @PageableDefault(size = 100) Pageable pageable, HttpServletRequest request) { 
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
//		gestion Menu 
		model.addAttribute("gestionMenuAgenceBanque", "gestionMenuAgenceBanque");
		model.addAttribute("gestionMenuUtilisateur", "gestionMenuUtilisateur");
		model.addAttribute("gestionMenuBanque", "gestionMenuBanque");
		model.addAttribute("gestionMenuGuichet", "gestionMenuGuichet");
		model.addAttribute("gestionMenuAgence", "gestionMenuAgence");
		model.addAttribute("gestionMenuAgent", "gestionMenuAgent");
		model.addAttribute("gestionMenuSociete", "gestionMenuSociete");
		model.addAttribute("accueilDeux", "accueilDeux");
		model.addAttribute("gestionConnexion", "gestionConnexion");
		String identifiantAffiche=session.getAttribute("identifiantSession").toString().trim();
		model.addAttribute("identifiantSession", identifiantAffiche);
		model.addAttribute("ajout", "ajout");
		model.addAttribute("listeUtilisateur", "listeUtilisateur");
		model.addAttribute("messageUtilisateurNonRetrouver", "messageUtilisateurNonRetrouver");
		model.addAttribute("actionTroisBouton", "actionTroisBouton");
		model.addAttribute("afficheTableau", "afficheTableau");

		Page<Utilisateur> utilisateurs=utilisateurRepository.findAllUtilisateurPage(pageable);
		model.addAttribute("utilisateurs", utilisateurs);
	
			return "espaceUtilisateur";	     
   }
	
	@Transactional
	@RequestMapping(value = {"/supprimerUtilisateur" }, method = RequestMethod.GET)
   public String supprimerUtilisateur(Model model, @ModelAttribute("utilisateur") Utilisateur utilisateur, HttpSession session, @PageableDefault(size = 100) Pageable pageable, HttpServletRequest request) {
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
//		gestion Menu 
		model.addAttribute("gestionMenuAgenceBanque", "gestionMenuAgenceBanque");
		model.addAttribute("gestionMenuUtilisateur", "gestionMenuUtilisateur");
		model.addAttribute("gestionMenuBanque", "gestionMenuBanque");
		model.addAttribute("gestionMenuGuichet", "gestionMenuGuichet");
		model.addAttribute("gestionMenuAgence", "gestionMenuAgence");
		model.addAttribute("gestionMenuAgent", "gestionMenuAgent");
		model.addAttribute("gestionMenuSociete", "gestionMenuSociete");
		model.addAttribute("accueilDeux", "accueilDeux");
		model.addAttribute("gestionConnexion", "gestionConnexion");
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
		
		Page<Utilisateur> utilisateurs=utilisateurRepository.findAllUtilisateurPage(pageable);
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
   public String SuccesSuppression(Model model, @ModelAttribute("utilisateur") Utilisateur utilisateur, HttpSession session, @PageableDefault(size = 100) Pageable pageable, HttpServletRequest request) {
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
//		gestion Menu 
		model.addAttribute("gestionMenuAgenceBanque", "gestionMenuAgenceBanque");
		model.addAttribute("gestionMenuUtilisateur", "gestionMenuUtilisateur");
		model.addAttribute("gestionMenuBanque", "gestionMenuBanque");
		model.addAttribute("gestionMenuGuichet", "gestionMenuGuichet");
		model.addAttribute("gestionMenuAgence", "gestionMenuAgence");
		model.addAttribute("gestionMenuAgent", "gestionMenuAgent");
		model.addAttribute("gestionMenuSociete", "gestionMenuSociete");
		model.addAttribute("accueilDeux", "accueilDeux");
		model.addAttribute("cheminAccueil", "Accueil >");
		model.addAttribute("titre", "Gestion des utilisateurs");
		model.addAttribute("cheminGestionUtilisateur", "Gestion Utilisateur >");
		model.addAttribute("cheminSupprimerUtilisateur", "Supprimer Utilisateur >");
		model.addAttribute("gestionConnexion", "gestionConnexion");
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
		
		String status="Inactif";
		utilisateurRecherche.setStatus(status);
		utilisateurRepository.save(utilisateurRecherche);
		
		Page<Utilisateur> utilisateurs=utilisateurRepository.findAllUtilisateurPage(pageable);
		model.addAttribute("utilisateurs",utilisateurs);		
		
			return "espaceUtilisateur";		
   }
	
	
	
	@Transactional
	@RequestMapping(value = {"/resultatModif"}, method = RequestMethod.POST)
   public String resultatModif(Model model, @ModelAttribute("utilisateur") Utilisateur utilisateur,  @ModelAttribute("utilisateurDto") UtilisateurDto utilisateurDto,  HttpSession session, @PageableDefault(size = 100) Pageable pageable, HttpServletRequest request) {  
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
//		gestion Menu 
		model.addAttribute("gestionMenuAgenceBanque", "gestionMenuAgenceBanque");
		model.addAttribute("gestionMenuUtilisateur", "gestionMenuUtilisateur");
		model.addAttribute("gestionMenuBanque", "gestionMenuBanque");
		model.addAttribute("gestionMenuGuichet", "gestionMenuGuichet");
		model.addAttribute("gestionMenuAgence", "gestionMenuAgence");
		model.addAttribute("gestionMenuAgent", "gestionMenuAgent");
		model.addAttribute("gestionMenuSociete", "gestionMenuSociete");
		model.addAttribute("accueilDeux", "accueilDeux");
		model.addAttribute("gestionConnexion", "gestionConnexion");
		model.addAttribute("identifiantSession", identifiantSession);
		String identifiant=utilisateurDto.getIdentifiant().trim();
		String fonction=utilisateurDto.getFonction().trim();
		String nomEtPrenom=utilisateurDto.getNomEtPrenom().trim();
		String nomAgence=utilisateurDto.getNomAgence().trim();
		
		
		String identifiantSession=session.getAttribute("identifiantCache").toString();
		identifiantSession=identifiantSession.trim();
		Page<Utilisateur> utilisateurs=utilisateurRepository.findAllUtilisateurPage(pageable);
		Boolean estSupprimer=false;
		
		
		if( identifiant != null && identifiant.length() > 0 && identifiant.length()<=40 && fonction != null && fonction.length() > 0 && nomEtPrenom != null && nomEtPrenom.length() > 0 && nomEtPrenom.length()<=40 ) {
			     
			    Utilisateur utilisateurSave=utilisateurRepository.findByIdentifiant(identifiantSession);
			
			    Integer utilisateurId=utilisateurSave.getIdUtilisateur();
			    		
				model.addAttribute("listeUtilisateur", "listeUtilisateur");
				model.addAttribute("actionTroisBouton", "actionTroisBouton");				
				model.addAttribute("ajout", "ajout");
				model.addAttribute("afficheTableau", "afficheTableau");
				model.addAttribute("utilisateurs", utilisateurs);
				model.addAttribute("actionTroisBouton", "actionTroisBouton");
				List<String> agences=new ArrayList<String>();
				agences=agenceRepository.findAllNomDirects();
				model.addAttribute("agences",agences);
					
					/////Recuperer l'Id de l'utilisateur Recherchee
					Integer idUtilisateurRecherche=utilisateurRepository.findIdUtilisateur(identifiantSession);
					Integer idUtilisateurModif=utilisateurRepository.findIdUtilisateur(identifiant);
					Utilisateur utilisateurParNomPrenom=utilisateurRepository.findUtilisateurByNomPrenomModif(nomEtPrenom, utilisateurId);
					Utilisateur utilisateurParIdentifiant=utilisateurRepository.findUtilisateurByIdentifiantModif(identifiant, utilisateurId);
					
					///////
					if(utilisateurParNomPrenom==null && utilisateurParIdentifiant==null ) {
						
							model.addAttribute("formErreur", "formErreur");
							Utilisateur utilisateurRecherche=utilisateurRepository.findByIdentifiant(identifiantSession);
							model.addAttribute("utilisateurRecherche", utilisateurRecherche);
							model.addAttribute("formModifDonneeUtil", "formModifDonneeUtil");
							model.addAttribute("identifiantErreur", "Identifiant utilisateur déjà existant");
							Agence agence=agenceRepository.findAgenceByCodeDirect(nomAgence);	
							model.addAttribute("succes", "succes"); 
							utilisateurSave.setIdAgence(agence);
							utilisateurSave.setFonction(fonction);
							utilisateurSave.setIdentifiant(identifiant);
							utilisateurSave.setNomEtPrenom(nomEtPrenom);
							utilisateurRepository.save(utilisateurSave);
							return "espaceUtilisateur";
					
					}
					if(utilisateurParNomPrenom!=null ) {
						model.addAttribute("nomEtPrenomErreur", "Nom et prenom déjà existant");
					}
					
					if(utilisateurParIdentifiant!=null ) {
						model.addAttribute("identifiantErreur", "Identifiant déjà existant");
					}
				
							
		}
		
		identifiant=utilisateur.getIdentifiant().trim();
		
		Utilisateur utilisateurRecherche=utilisateurRepository.findByIdentifiant(identifiantSession);

		model.addAttribute("formModifDonneeUtil", "formModifDonneeUtil");
		model.addAttribute("utilisateurRecherche", utilisateurRecherche);
			
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
		model.addAttribute("gestionMenuAgenceBanque", "gestionMenuAgenceBanque");
		model.addAttribute("gestionMenuUtilisateur", "gestionMenuUtilisateur");
		model.addAttribute("gestionMenuBanque", "gestionMenuBanque");
		model.addAttribute("gestionMenuGuichet", "gestionMenuGuichet");
		model.addAttribute("gestionMenuAgence", "gestionMenuAgence");
		model.addAttribute("gestionMenuAgent", "gestionMenuAgent");
		model.addAttribute("gestionMenuSociete", "gestionMenuSociete");
		model.addAttribute("accueilDeux", "accueilDeux");
		model.addAttribute("gestionConnexion", "gestionConnexion");
		return "espaceUtilisateur";
	}
	
	
	@Transactional
	@RequestMapping(value = "/listeUtilisateurs")
   public ModelAndView listeUtilisateursPageByPage(@RequestParam(name="page", defaultValue="0") int page,@RequestParam(name="size", defaultValue="100") int size,  @ModelAttribute("utilisateur") Utilisateur utilisateur, Model model, HttpSession session, HttpServletRequest request) {
       ModelAndView modelAndView = new ModelAndView("espaceUtilisateur");
      
       PageRequest pageable = PageRequest.of(page-1, size);
       Page<Utilisateur> utilisateurPage = utilisateurRepository.findAllUtilisateurPage(pageable);

		model.addAttribute("utilisateurs", utilisateurPage);
				
//		gestion Menu 
		model.addAttribute("gestionMenuAgenceBanque", "gestionMenuAgenceBanque");
		model.addAttribute("gestionMenuUtilisateur", "gestionMenuUtilisateur");
		model.addAttribute("gestionMenuBanque", "gestionMenuBanque");
		model.addAttribute("gestionMenuGuichet", "gestionMenuGuichet");
		model.addAttribute("gestionMenuAgence", "gestionMenuAgence");
		model.addAttribute("gestionMenuAgent", "gestionMenuAgent");
		model.addAttribute("gestionMenuSociete", "gestionMenuSociete");
		model.addAttribute("accueilDeux", "accueilDeux");
		
		model.addAttribute("accueilDeux", "accueilDeux");
		model.addAttribute("cheminAccueil", "Accueil >");
		model.addAttribute("titre", "Gestion des utilisateurs");
		model.addAttribute("cheminGestionUtilisateur", "Gestion Utilisateur >");
		model.addAttribute("identifiantSession", identifiantSession);
		model.addAttribute("listeUtilisateur", "listeUtilisateur");
		model.addAttribute("gestionUtilisateur", "gestionUtilisateur");
		model.addAttribute("menuNavigation", "menuNavigation");
		model.addAttribute("afficheTableau", "afficheTableau");
		
       return modelAndView;
	}	
	

}
