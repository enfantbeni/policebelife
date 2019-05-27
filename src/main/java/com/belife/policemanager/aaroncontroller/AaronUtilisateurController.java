package com.belife.policemanager.aaroncontroller;

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

import com.belife.policemanager.model.aarondto.AaronUtilisateurDto;
import com.belife.policemanager.model.aaronentity.AaronAgence;
import com.belife.policemanager.model.aaronentity.AaronUtilisateur;
import com.belife.policemanager.model.aaronrepository.AaronAgenceRepository;
import com.belife.policemanager.model.aaronrepository.AaronUtilisateurRepository;
import com.belife.policemanager.model.dto.UtilisateurDto;
import com.belife.policemanager.model.entity.Agence;
import com.belife.policemanager.model.entity.Roles;
import com.belife.policemanager.model.entity.SourcePolice;
import com.belife.policemanager.model.entity.Utilisateur;
import com.belife.policemanager.model.repository.AgenceRepository;
import com.belife.policemanager.model.repository.AgentRepository;
import com.belife.policemanager.model.repository.RolesRepository;
import com.belife.policemanager.model.repository.SocieteRepository;
import com.belife.policemanager.model.repository.SourcePoliceRepository;
import com.belife.policemanager.model.repository.UtilisateurRepository;

@Controller
public class AaronUtilisateurController {

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
	 AaronAgenceRepository aaronAgenceRepository;
	 
	 @Autowired
    AgentRepository agentRepository;
	 
	 @Autowired
    SocieteRepository societeRepository;
	 
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
	 
//	 @Transactional
//	@RequestMapping(value = {"/" }, method = RequestMethod.GET)
//   public String index(Model model) {    
//       return "redirect:/authentification";
//   }
//	
	@Transactional
	@RequestMapping(value = {"aaronDeconnexion" }, method = RequestMethod.GET)
   public String deconnexion(Model model, HttpSession session) { 
	    session.removeAttribute("identifiantSession");
	    session.removeAttribute("identifiantCache");
	    session.removeAttribute("codeGuichetCache");
	    session.removeAttribute("codeSocieteCache");
	    session.removeAttribute("codeAgenceCache"); 
	    session.removeAttribute("nomSourceCache");
       return "redirect:/authentification";
   }
	
//	@Transactional
//	@RequestMapping(value = {"/authentification" }, method = RequestMethod.GET)
//   public String authentification(Model model) {    
//       return "authentification";
//   }
	
	@Transactional
	@RequestMapping(value = {"/aaronResultatModifDonnee" }, method = RequestMethod.POST)
   public String resultatModifDonnee(Model model, @ModelAttribute("aaronUtilisateur") AaronUtilisateur aaronUtilisateur, HttpSession session, @PageableDefault(size = 100) Pageable pageable, HttpServletRequest request) { 
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
		model.addAttribute("gestionMenuAgenceBanque", "gestionMenuAgenceBanque");
		AaronUtilisateur aaronUtilisateurRenvoi=new AaronUtilisateur();
		List<AaronUtilisateur> utilisateurs=new ArrayList<AaronUtilisateur>();
		String identifiantAffiche=session.getAttribute("identifiantSession").toString().trim();
		model.addAttribute("identifiantSession", identifiantAffiche);
		String identifiant=aaronUtilisateur.getIdentifiant().trim();
		identifiantSession=identifiantSession.trim();
		
		model.addAttribute("dialog_background", "dialog_background");
		model.addAttribute("identifiantSession",identifiantSession);
		model.addAttribute("dialog_box", "dialog_box");
		
		model.addAttribute("listeUtilisateur", "listeUtilisateur");
				
		Page<AaronUtilisateur> utilisateursPage =aaronUtilisateurRepository.findAllUtilisateurPage(pageable);	
		model.addAttribute("utilisateurs",utilisateursPage);
		model.addAttribute("afficheTableau", "afficheTableau");	
		aaronUtilisateurRenvoi=aaronUtilisateurRepository.findByIdentifiant(identifiant);	
		model.addAttribute("utilisateurRenvoi", aaronUtilisateurRenvoi);	
       return "aaron/espaceUtilisateur";
		
   }
	
	@Transactional
	@RequestMapping(value = {"/aaronEnvoiDonneeCachee" }, method = RequestMethod.POST)
   public String envoiDonneeCachee(Model model,  @ModelAttribute("aaronUtilisateur") AaronUtilisateur aaronUtilisateur, HttpSession session) { 		
		String resultat=null;
		try {
			identifiantSession=session.getAttribute("identifiantSession").toString().trim();
		}
		catch(Exception e) {
			resultat="pageErreur";
			return resultat;
		}				
		String identifiant=aaronUtilisateur.getIdentifiant().trim();	
		aaronUtilisateur=aaronUtilisateurRepository.findByIdentifiant(identifiant);
		session.setAttribute("identifiantCache",identifiant);
       return "redirect:/aaronFormModifDonneeUtilV2";
   }
	
	@Transactional
	@RequestMapping(value = {"/aaronEnvoiDonneeCacheeModif" }, method = RequestMethod.POST)
   public String envoiDonneeCacheeModif(Model model,  @ModelAttribute("aaronUtilisateur") AaronUtilisateur aaronUtilisateur, HttpSession session) { 		
		String resultat=null;
		try {
			identifiantSession=session.getAttribute("identifiantSession").toString().trim();
		}
		catch(Exception e) {
			resultat="pageErreur";
			return resultat;
		}				
		String identifiant=aaronUtilisateur.getIdentifiant().trim();	
		aaronUtilisateur=aaronUtilisateurRepository.findByIdentifiant(identifiant);
		session.setAttribute("identifiantCache",identifiant);
		model.addAttribute("utilisateur", aaronUtilisateur);	
       return "aaron/espaceUtilisateur";
   }
	

	
	@Transactional
	@RequestMapping(value = {"/aaronSourcePolice" }, method = RequestMethod.GET)
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
		model.addAttribute("gestionMenuAgenceBanque", "gestionMenuAgenceBanque");
		Boolean estSupprimer=false;
		List<SourcePolice> sourcePolices=new ArrayList<SourcePolice>();
		sourcePolices=sourcePoliceRepository.findAllSourcePrelevement(estSupprimer);
		

		model.addAttribute("sourcePolices", sourcePolices);
		model.addAttribute("identifiantSession", identifiantSession);
		model.addAttribute("formSourceGenre", "formSourceGenre");
		model.addAttribute("sourcePolice", "sourcePolice");
		model.addAttribute("menuNavigation", "menuNavigation");
       return "aaron/espaceUtilisateur";
   }
	
	@Transactional
	@RequestMapping(value = {"/aaronContenuAccueilUtilisateur" }, method = RequestMethod.GET)
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
	
		String identifiantConnecte=session.getAttribute("identifiantSession").toString().trim();
		AaronUtilisateur utilisateur=aaronUtilisateurRepository.findByIdentifiant(identifiantConnecte);
		AaronAgence agence=utilisateur.getIdAgence();
		String nomAgence=agence.getNomDirect();
		model.addAttribute("cheminAccueil", "Accueil >");
		model.addAttribute("nomAgence", nomAgence);
		model.addAttribute("accueilUtilisateurMessage", "accueilUtilisateurMessage");
		model.addAttribute("identifiantSession", identifiantSession);
		model.addAttribute("contenuAccueilUtilisateur", "contenuAccueilUtilisateur");
		model.addAttribute("menuNavigation", "menuNavigation");
       return "aaron/utilisateur/accueilUtilisateur";	
   }
	
	
	@Transactional
	@RequestMapping(value = {"/aaronAccueilUtilisateurLien" }, method = RequestMethod.GET)
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
		
		String identifiantConnecte=session.getAttribute("identifiantSession").toString().trim();
		AaronUtilisateur utilisateur=aaronUtilisateurRepository.findByIdentifiant(identifiantConnecte);
		AaronAgence agence=utilisateur.getIdAgence();
		String nomAgence=agence.getNomDirect();
		
		model.addAttribute("nomAgence", nomAgence);
		session.setAttribute("nomAgenceActif", nomAgence);
		model.addAttribute("cheminAccueil", "Accueil >");
		model.addAttribute("contenuAccueilUtilisateur", "contenuAccueilUtilisateur");
		model.addAttribute("accueilUtilisateurMessage", "accueilUtilisateurMessage");
		model.addAttribute("identifiantSession", identifiantSession);
		model.addAttribute("menuNavigation", "menuNavigation");
       return "aaron/utilisateur/accueilUtilisateur";
   }
	
	@Transactional
	@RequestMapping(value = {"/aaronlisteModuleUtilisateur" }, method = RequestMethod.GET)
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
			return "aaron/espaceUtilisateur";
			
		}
		else {
			return "pageErreur";
		}
			
   }
	
	@Transactional
	@RequestMapping(value = {"/aaronModulesUtilisateur" }, method = RequestMethod.GET)
   public String modulesUtilisateur(Model model, @ModelAttribute("aaronUtilisateur") AaronUtilisateur aaronUtilisateur, HttpSession session) { 
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
	@RequestMapping(value = {"/aaronAccueilDeux" }, method = RequestMethod.GET)
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
		model.addAttribute("accueilDeux", "accueilDeux");
		model.addAttribute("gestionMenuAgenceBanque", "gestionMenuAgenceBanque");	
		model.addAttribute("messageAccueilEspaceAdmin", "messageAccueilEspaceAdmin");
		model.addAttribute("accueilAdmin", "accueilAdmin");
		model.addAttribute("cheminAccueil", "Accueil >");
		
		if(identifiantSession.length()>0) {
			String identifiantSession=session.getAttribute("identifiantSession").toString().trim();
			model.addAttribute("identifiantSession", identifiantSession);
			return "aaron/espaceUtilisateur";
		}
		else {
			return "pageErreur";
		}
       
   }
	
	@Transactional
	@RequestMapping(value = {"/aaronListeUtilisateur" }, method = RequestMethod.GET)
   public String listeUtilisateur(Model model, @ModelAttribute("aaronUtilisateur") AaronUtilisateur aaronUtilisateur, HttpSession sessionn, HttpSession session, @PageableDefault(size = 100) Pageable pageable, HttpServletRequest request) { 
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
		model.addAttribute("afficheTableau", "afficheTableau");
		int page = 0;
		int size = 100;			 
		pageable = PageRequest.of(page, size);
		
		Page<AaronUtilisateur> utilisateurs=aaronUtilisateurRepository.findAllUtilisateurPage(pageable);
		
		model.addAttribute("utilisateurs", utilisateurs);
			
		String identifiantSession=session.getAttribute("identifiantSession").toString().trim();
		model.addAttribute("identifiantSession", identifiantSession);
		return "aaron/espaceUtilisateur";	
   }
	
	@Transactional
	@RequestMapping(value = {"/aaronFormulaireAjoutUtilisateur" }, method = RequestMethod.GET)
   public String formulaireAjoutUtilisateur(Model model, @ModelAttribute("aaronUtilisateur") AaronUtilisateur aaronUtilisateur, HttpSession session, @PageableDefault(size = 100) Pageable pageable, HttpServletRequest request) { 
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
		Page<AaronUtilisateur> utilisateurs=aaronUtilisateurRepository.findAllUtilisateurPage(pageable);
		model.addAttribute("afficheTableau", "afficheTableau");
		model.addAttribute("utilisateurs", utilisateurs);
		
		String identifiantSession=session.getAttribute("identifiantSession").toString().trim();
		model.addAttribute("identifiantSession", identifiantSession);
		return "aaron/espaceUtilisateur";
		
   }
	
	@Transactional
	@RequestMapping(value = {"/aaronAjoutUtilisateur" }, method = RequestMethod.POST)
   public String ajoutUtilisateur(Model model, @ModelAttribute("aaronUtilisateur") AaronUtilisateur aaronUtilisateur, @ModelAttribute("aaronUtilisateurDto") AaronUtilisateurDto aaronUtilisateurDto, HttpSession session, @PageableDefault(size = 100) Pageable pageable, HttpServletRequest request) { 
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
		
		AaronUtilisateur utilisateurSave=null;
		String identifiant=aaronUtilisateurDto.getIdentifiant().trim();
		String password=aaronUtilisateurDto.getPassword().trim();
		String nomEtPrenom=aaronUtilisateurDto.getNomEtPrenom().trim();
		String fonction=aaronUtilisateurDto.getFonction().trim();
		String nomAgence=aaronUtilisateurDto.getNomAgence().trim();
		
		model.addAttribute("listeUtilisateur", "listeUtilisateur");
		model.addAttribute("actionTroisBouton", "actionTroisBouton");
		int page = 0;
		int size = 100;			 
		pageable = PageRequest.of(page, size);
		Page<AaronUtilisateur> utilisateurs=aaronUtilisateurRepository.findAllUtilisateurPage(pageable);
		
		if( identifiant != null && identifiant.length() > 0 && identifiant.length()<=10 && password != null && password.length() > 0 && password.length()>4 && nomEtPrenom != null && nomEtPrenom.length() > 0 && fonction != null && fonction.length() > 0 ) {
			utilisateurSave=aaronUtilisateurRepository.findByIdentifiant(identifiant);
				if(utilisateurSave == null) {
					String status="A";
					String prefixe="aaron";
					fonction=prefixe.concat("-").concat(fonction);
					aaronUtilisateur.setStatus(status);
					aaronUtilisateur.setFonction(fonction);
					aaronUtilisateur.setIdentifiant(identifiant);
					aaronUtilisateur.setNomEtPrenom(nomEtPrenom);
					aaronUtilisateur.setPassword(password);
					aaronUtilisateur.setDateCreation(new Date());
					 utilisateurs=aaronUtilisateurRepository.findAllUtilisateurPage(pageable);
					AaronAgence agence=aaronAgenceRepository.findAgenceByNomDirect(nomAgence);
					aaronUtilisateur.setIdAgence(agence);
					aaronUtilisateurRepository.save(aaronUtilisateur);	
					model.addAttribute("ajout", "ajout");
					model.addAttribute("afficheTableau", "afficheTableau");
					model.addAttribute("utilisateurs", utilisateurs);					
					model.addAttribute("succes", "succes");
					String identifiantSession=session.getAttribute("identifiantSession").toString().trim();
					 model.addAttribute("identifiantSession", identifiantSession);
					if(identifiantSession.length()>0)
						return "aaron/espaceUtilisateur";
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
			agences=agenceRepository.findAllNomDirects();
			model.addAttribute("agences",agences);
			model.addAttribute("identifiantSession", identifiantSession);	
			model.addAttribute("ajout", "ajout");
			model.addAttribute("afficheTableau", "afficheTableau");
			model.addAttribute("utilisateurs", utilisateurs);
			model.addAttribute("formulaireAjoutUtil", "formulaireAjoutUtil");
			if(identifiantSession.length()>0) {
				return "aaron/espaceUtilisateur";
			}
			else {
				return "pageErreur";
			}
       
   }
	
	@Transactional
	@RequestMapping(value = {"/aaronNumeroModifUtilisateur" }, method = RequestMethod.GET)
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
		
		model.addAttribute("ajout", "ajout");
		model.addAttribute("listeUtilisateur", "listeUtilisateur");
		model.addAttribute("formNumeroModifUtil", "formNumeroModifUtil");
		model.addAttribute("cheminAccueil", "Accueil >");
		model.addAttribute("titre", "Gestion des utilisateurs");
		model.addAttribute("cheminGestionUtilisateur", "Gestion Utilisateur >");
		model.addAttribute("cheminModifierUtilisateur", "Modifier Utilisateur >");
		model.addAttribute("actionTroisBouton", "actionTroisBouton");
		model.addAttribute("afficheTableau", "afficheTableau");

		Page<AaronUtilisateur> utilisateurs=aaronUtilisateurRepository.findAllUtilisateurPage(pageable);
		model.addAttribute("utilisateurs", utilisateurs);
		if(identifiantSession.length()>0) {
			String identifiantSession=session.getAttribute("identifiantSession").toString().trim();
			model.addAttribute("identifiantSession", identifiantSession);
			return "aaron/espaceUtilisateur";
		}
		else {
			 return "pageErreur";
		}     
   }
	
	@Transactional
	@RequestMapping(value = {"/aaronFormModifDonneeUtil" }, method = RequestMethod.POST)
   public String formModifDonneeUtil(Model model, @ModelAttribute("aaronUtilisateur") AaronUtilisateur aaronUtilisateur, HttpSession session, @PageableDefault(size = 100) Pageable pageable, HttpServletRequest request) {
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
		Page<AaronUtilisateur> utilisateurs=aaronUtilisateurRepository.findAllUtilisateurPage(pageable);
		String identifiant=aaronUtilisateur.getIdentifiant().trim();
		session.setAttribute("identifiantCache", aaronUtilisateur.getIdentifiant());
		
		AaronUtilisateur utilisateurRecherche=aaronUtilisateurRepository.findByIdentifiant(identifiant);
			
		model.addAttribute("afficheTableau", "afficheTableau");
		if( utilisateurRecherche == null) {
			 String identifiantSession=session.getAttribute("identifiantSession").toString().trim();
			 model.addAttribute("identifiantSession", identifiantSession);
			 return "redirect:/aaronMessageUtilisateurNonExistant";  
		}
		
		List<String> agences=new ArrayList<String>();
		agences=aaronAgenceRepository.findAllNomDirects();
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
	
		model.addAttribute("utilisateurs", utilisateurs);
		String identifiantSession=session.getAttribute("identifiantSession").toString().trim();
		model.addAttribute("identifiantSession", identifiantSession);
		if(identifiantSession.length()>0) {
			return "aaron/espaceUtilisateur";
		}
		else {
			return "pageErreur";
		}			
   }
	
	@Transactional
	@RequestMapping(value = {"/aaronFormModifDonneeUtilV2" }, method = RequestMethod.GET)
   public String formModifDonneeUtilV2(Model model, @ModelAttribute("aaronUtilisateur") AaronUtilisateur aaronUtilisateur, HttpSession sessionUtilisateur, HttpSession session, @PageableDefault(size = 100) Pageable pageable, HttpServletRequest request) {
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
	
		String identifiant=session.getAttribute("identifiantCache").toString().trim();
		sessionUtilisateur.setAttribute("identifiant", aaronUtilisateur.getIdentifiant());
		
		AaronUtilisateur utilisateurRecherche=aaronUtilisateurRepository.findByIdentifiant(identifiant);
			
		model.addAttribute("afficheTableau", "afficheTableau");
		if( utilisateurRecherche == null) {
			 String identifiantSession=session.getAttribute("identifiantSession").toString().trim();
			 model.addAttribute("identifiantSession", identifiantSession);
			 return "redirect:/aaronMessageUtilisateurNonExistant";  
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
		
		Page<AaronUtilisateur> utilisateurs=aaronUtilisateurRepository.findAllUtilisateurPage(pageable);
		model.addAttribute("utilisateurs", utilisateurs);
		String identifiantSession=session.getAttribute("identifiantSession").toString().trim();
		model.addAttribute("identifiantSession", identifiantSession);
		if(identifiantSession.length()>0) {
			return "aaron/espaceUtilisateur";
		}
		else {
			return "pageErreur";
		}			
   }
	
	
	
	@Transactional
	@RequestMapping(value = {"/aaronMessageUtilisateurNonExistant" }, method = RequestMethod.GET)
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
		
		model.addAttribute("ajout", "ajout");
		model.addAttribute("listeUtilisateur", "listeUtilisateur");
		model.addAttribute("messageUtilisateurNonExistant", "messageUtilisateurNonExistant");
		model.addAttribute("actionTroisBouton", "actionTroisBouton");
		model.addAttribute("afficheTableau", "afficheTableau");
		
		Page<AaronUtilisateur> utilisateurs=aaronUtilisateurRepository.findAllUtilisateurPage(pageable);
		model.addAttribute("utilisateurs", utilisateurs);	
		if(identifiantSession.length()>0) {
			String identifiantSession=session.getAttribute("identifiantSession").toString().trim();
			model.addAttribute("identifiantSession", identifiantSession);
			return "aaron/espaceUtilisateur";
		}
		else {
			return "pageErreur";
		}
       
   }
	
	@Transactional
	@RequestMapping(value = {"/aaronRechercheUtilisateur" }, method = RequestMethod.GET)
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
		Page<AaronUtilisateur> utilisateurs=aaronUtilisateurRepository.findAllUtilisateurPage(pageable);

			return "aaron/espaceUtilisateur";
   }
	
	@Transactional
	@RequestMapping(value = {"/aaronSuccesRecherche" }, method = RequestMethod.POST)
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
		
		String identifiantAffiche=session.getAttribute("identifiantSession").toString().trim();
		model.addAttribute("identifiantSession", identifiantAffiche);
		String identifiant=utilisateur.getIdentifiant().trim();
		AaronUtilisateur utilisateurRecherche=aaronUtilisateurRepository.findByIdentifiant(identifiant);
		if( utilisateurRecherche == null) {
			if(identifiantSession.length()>0)
				return "redirect:/aaronMessageUtilisateurNonRetrouver";  
		}
		model.addAttribute("ajout", "ajout");
		model.addAttribute("listeUtilisateur", "listeUtilisateur");
		model.addAttribute("messageUtilisateurRetrouver", "messageUtilisateurRetrouver");
		model.addAttribute("actionTroisBouton", "actionTroisBouton");
		model.addAttribute("succesRecherche", "succesRecherche");
		model.addAttribute("utilisateurRechercher", utilisateurRecherche);
		
		//model.addAttribute("afficheTableau", "afficheTableau");
		Page<AaronUtilisateur> utilisateurs=aaronUtilisateurRepository.findAllUtilisateurPage(pageable);
		model.addAttribute("utilisateurs", utilisateurs);	
	
	    return "aaron/espaceUtilisateur";		
   }
	
	@Transactional
	@RequestMapping(value = {"/aaronMessageUtilisateurNonRetrouver" }, method = RequestMethod.GET)
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
		model.addAttribute("cheminAccueil", "Accueil >");
		model.addAttribute("titre", "Gestion des utilisateurs");
		model.addAttribute("cheminGestionUtilisateur", "Gestion Utilisateur >");
		String identifiantAffiche=session.getAttribute("identifiantSession").toString().trim();
		model.addAttribute("identifiantSession", identifiantAffiche);
		model.addAttribute("ajout", "ajout");
		model.addAttribute("listeUtilisateur", "listeUtilisateur");
		model.addAttribute("messageUtilisateurNonRetrouver", "messageUtilisateurNonRetrouver");
		model.addAttribute("actionTroisBouton", "actionTroisBouton");
		model.addAttribute("afficheTableau", "afficheTableau");

		Page<AaronUtilisateur> utilisateurs=aaronUtilisateurRepository.findAllUtilisateurPage(pageable);
		model.addAttribute("utilisateurs", utilisateurs);
	
			return "aaron/espaceUtilisateur";	     
   }
	
	@Transactional
	@RequestMapping(value = {"/aaronSupprimerUtilisateur" }, method = RequestMethod.GET)
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
		
		Page<AaronUtilisateur> utilisateurs=aaronUtilisateurRepository.findAllUtilisateurPage(pageable);
		model.addAttribute("utilisateurs", utilisateurs);
		if(identifiantSession.length()>0) {
			return "aaron/espaceUtilisateur";
		}
		else {
			return "pageErreur";
		}     
   }
	
	@Transactional
	@RequestMapping(value = {"/aaronSuccesSuppression" }, method = RequestMethod.POST)
   public String SuccesSuppression(Model model, @ModelAttribute("aaronUtilisateur") AaronUtilisateur aaronUtilisateur, HttpSession session, @PageableDefault(size = 100) Pageable pageable, HttpServletRequest request) {
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
		
		String identifiant=aaronUtilisateur.getIdentifiant().trim();
		AaronUtilisateur utilisateurRecherche=aaronUtilisateurRepository.findByIdentifiant(identifiant);
		String identifiantAffiche=session.getAttribute("identifiantSession").toString().trim();
		model.addAttribute("identifiantSession", identifiantAffiche);
		
		if( utilisateurRecherche == null) {
			if(identifiantSession.length()>0) {
				 return "redirect:/aaronMessageUtilisateurNonRetrouver";  
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
		aaronUtilisateurRepository.save(utilisateurRecherche);
		
		Page<AaronUtilisateur> utilisateurs=aaronUtilisateurRepository.findAllUtilisateurPage(pageable);
		model.addAttribute("utilisateurs",utilisateurs);		
		
		return "aaron/espaceUtilisateur";		
   }	
	
	@Transactional
	@RequestMapping(value = {"/aaronResultatModif"}, method = RequestMethod.POST)
    public String resultatModif(Model model, @ModelAttribute("aaronUtilisateur") AaronUtilisateur aaronUtilisateur,  @ModelAttribute("aaronUtilisateurDto") AaronUtilisateurDto aaronUtilisateurDto,  HttpSession session, @PageableDefault(size = 100) Pageable pageable, HttpServletRequest request) {  
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
		
		model.addAttribute("identifiantSession", identifiantSession);
		String identifiant=aaronUtilisateurDto.getIdentifiant().trim();
		String fonction=aaronUtilisateurDto.getFonction().trim();
		String nomEtPrenom=aaronUtilisateurDto.getNomEtPrenom().trim();
		String nomAgence=aaronUtilisateurDto.getNomAgence().trim();
				
		String identifiantSession=session.getAttribute("identifiantCache").toString();
		identifiantSession=identifiantSession.trim();
		Page<AaronUtilisateur> utilisateurs=aaronUtilisateurRepository.findAllUtilisateurPage(pageable);
		
		if( identifiant != null && identifiant.length() > 0 && identifiant.length()<=40 && fonction != null && fonction.length() > 0 && nomEtPrenom != null && nomEtPrenom.length() > 0 && nomEtPrenom.length()<=40 ) {
			     
			    AaronUtilisateur utilisateurSave=aaronUtilisateurRepository.findByIdentifiant(identifiantSession);
			
			    Integer utilisateurId=utilisateurSave.getIdUtilisateur();
			    		
				model.addAttribute("listeUtilisateur", "listeUtilisateur");
				model.addAttribute("actionTroisBouton", "actionTroisBouton");				
				model.addAttribute("ajout", "ajout");
				model.addAttribute("afficheTableau", "afficheTableau");
				model.addAttribute("utilisateurs", utilisateurs);
				model.addAttribute("actionTroisBouton", "actionTroisBouton");
				List<String> agences=new ArrayList<String>();
				agences=aaronAgenceRepository.findAllNomDirects();
				model.addAttribute("agences",agences);
					
					/////Recuperer l'Id de l'utilisateur Recherchee
					Integer idUtilisateurRecherche=aaronUtilisateurRepository.findIdUtilisateur(identifiantSession);
					Integer idUtilisateurModif=aaronUtilisateurRepository.findIdUtilisateur(identifiant);
					AaronUtilisateur utilisateurParNomPrenom=aaronUtilisateurRepository.findUtilisateurByNomPrenomModif(nomEtPrenom, utilisateurId);
					AaronUtilisateur utilisateurParIdentifiant=aaronUtilisateurRepository.findUtilisateurByIdentifiantModif(identifiant, utilisateurId);
					
					///////
					if(utilisateurParNomPrenom==null && utilisateurParIdentifiant==null ) {
						
//							model.addAttribute("formErreur", "formErreur");
//							Utilisateur utilisateurRecherche=utilisateurRepository.findByIdentifiant(identifiantSession);
//							model.addAttribute("utilisateurRecherche", utilisateurRecherche);
//							model.addAttribute("formModifDonneeUtil", "formModifDonneeUtil");
//							model.addAttribute("identifiantErreur", "Identifiant utilisateur déjà existant");
							AaronAgence agence=aaronAgenceRepository.findAgenceByCodeDirect(nomAgence);	
							model.addAttribute("succesModif", "succesModif"); 
							utilisateurSave.setIdAgence(agence);
							utilisateurSave.setFonction(fonction);
							utilisateurSave.setIdentifiant(identifiant);
							utilisateurSave.setNomEtPrenom(nomEtPrenom);
							aaronUtilisateurRepository.save(utilisateurSave);
							return "aaron/espaceUtilisateur";
					
					}
					if(utilisateurParNomPrenom!=null ) {
						model.addAttribute("nomEtPrenomErreur", "Nom et prenom déjà existant");
					}
					
					if(utilisateurParIdentifiant!=null ) {
						model.addAttribute("identifiantErreur", "Identifiant déjà existant");
					}							
		}
		
		identifiant=aaronUtilisateur.getIdentifiant().trim();
		
		AaronUtilisateur utilisateurRecherche=aaronUtilisateurRepository.findByIdentifiant(identifiantSession);

		model.addAttribute("formModifDonneeUtil", "formModifDonneeUtil");
		model.addAttribute("utilisateurRecherche", utilisateurRecherche);
			
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
			return "aaron/espaceUtilisateur";
		
   }
	
	
	@Transactional
	@RequestMapping(value = {"/aaronResultatModifV2" }, method = RequestMethod.POST)
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
	
		return "aaron/espaceUtilisateur";
	}
	
	
	@Transactional
	@RequestMapping(value = "/aaronListeUtilisateurs")
   public ModelAndView listeUtilisateursPageByPage(@RequestParam(name="page", defaultValue="0") int page,@RequestParam(name="size", defaultValue="100") int size,  @ModelAttribute("utilisateur") Utilisateur utilisateur, Model model, HttpSession session, HttpServletRequest request) {
       ModelAndView modelAndView = new ModelAndView("aaron/espaceUtilisateur");
      
       PageRequest pageable = PageRequest.of(page-1, size);
       Page<AaronUtilisateur> utilisateurs=aaronUtilisateurRepository.findAllUtilisateurPage(pageable);

		model.addAttribute("utilisateurs", utilisateurs);
				
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
