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

import com.belife.policemanager.model.entity.Banque;
import com.belife.policemanager.model.entity.BanquePrincipale;
import com.belife.policemanager.model.repository.BanquePrincipaleRepository;
import com.belife.policemanager.model.repository.BanqueRepository;
import com.belife.policemanager.model.repository.RolesRepository;
import com.belife.policemanager.model.repository.SourcePoliceRepository;
import com.belife.policemanager.model.repository.UtilisateurRepository;

@Controller
public class BanquePrincipaleController {
	
	@Autowired
    UtilisateurRepository utilisateurRepository; 
	 
	 @Autowired
    RolesRepository rolesRepository;
	 
	 @Autowired
    SourcePoliceRepository sourcePoliceRepository;
	 
	 @Autowired
    BanqueRepository banqueRepository;
	 
	 @Autowired
	BanquePrincipaleRepository banquePrincipaleRepository;
	 
	 String identifiantSession=null;
	 
	 @Transactional
	 @RequestMapping(value = {"/gestionBanquePrincipale" }, method = RequestMethod.GET)
	    public String gestionBanquePrincipale(Model model, HttpSession session) { 		 
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
			
			Boolean estSupprimer=false;
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionBanque", "Gestion Banque >");
			model.addAttribute("titre", "Gestion des Banques");
			List<BanquePrincipale> banquePrincipales=new ArrayList<BanquePrincipale>();		
			banquePrincipales=banquePrincipaleRepository.findAllBanquePrincipales(estSupprimer);
			model.addAttribute("banquePrincipales", banquePrincipales);
			model.addAttribute("listeBanquePrincipale", "listeBanquePrincipale");
			model.addAttribute("gestionBanquePrincipale", "gestionBanquePrincipale");
			model.addAttribute("menuNavigation", "menuNavigation");
	        return "espaceUtilisateur";			
	    }
	 
	 @Transactional
	 @RequestMapping(value = {"/ajoutBanquePrincipale" }, method = RequestMethod.GET)
	    public String ajoutBanquePrincipale(Model model, HttpSession session) { 
		 
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
			
			Boolean estSupprimer=false;
			List<BanquePrincipale> banquePrincipales=new ArrayList<BanquePrincipale>();
			banquePrincipales=banquePrincipaleRepository.findAllBanquePrincipales(estSupprimer);
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionBanque", "Gestion Banque >");
			model.addAttribute("cheminAjouterBanque", "Ajout d'une Banque >");
			model.addAttribute("titre", "Gestion des Banques");
			model.addAttribute("banquePrincipales", banquePrincipales);
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("formulaireGestionBanquePrincipale", "formulaireGestionBanquePrincipale");
			model.addAttribute("listeBanquePrincipale", "listeBanquePrincipale");
			model.addAttribute("gestionBanquePrincipale", "gestionBanquePrincipale");
			model.addAttribute("menuNavigation", "menuNavigation");
	        return "espaceUtilisateur";			
	    }
	 
	 @Transactional
	 @RequestMapping(value = {"/resultatAjoutBanquePrincipale" }, method = RequestMethod.POST)
	    public String resultatAjoutBanquePrincipale(Model model, @ModelAttribute("banquePrincipale") BanquePrincipale banquePrincipale , HttpSession session) { 		 
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
			
			String nomBanque=banquePrincipale.getNomBanque().trim();
			String codeBanque=banquePrincipale.getCodeBanque().trim();
			List<BanquePrincipale> banquePrincipales=new ArrayList<BanquePrincipale>();
			Boolean estSupprimer=false;
			BanquePrincipale banquePrincipaleSave=null;
			BanquePrincipale banquePrincipaleNomSave=null;
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("gestionBanquePrincipale", "gestionBanquePrincipale");
			model.addAttribute("menuNavigation", "menuNavigation");
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionBanque", "Gestion Banque >");
			model.addAttribute("cheminAjouterBanque", "Ajout d'une Banque >");
			model.addAttribute("titre", "Gestion des Banques");
			if( nomBanque != null && nomBanque.length() > 0 && nomBanque.length()<=50 && codeBanque != null && codeBanque.length() > 0 && codeBanque.length()<=5 ) {						
				banquePrincipaleSave=banquePrincipaleRepository.findBanquePrincipaleByCode(codeBanque);
				banquePrincipaleNomSave=banquePrincipaleRepository.findBanquePrincipaleByNom(nomBanque);
				
					if(banquePrincipaleSave == null && banquePrincipaleNomSave == null) {		
						
						banquePrincipale.setNomBanque(nomBanque);
						banquePrincipale.setCodeBanque(codeBanque);
						banquePrincipale.setEstSupprimer(estSupprimer);
						
						model.addAttribute("listeBanquePrincipale", "listeBanquePrincipale");
						model.addAttribute("gestionBanquePrincipale", "gestionBanquePrincipale");
						BanquePrincipale bp=banquePrincipaleRepository.save(banquePrincipale);
						String codeBanqueReturn=bp.getCodeBanque();
						model.addAttribute("ajoutSuccesBanquePrincipale", "Une Banque de code  est ajoutée avec succès");	
						model.addAttribute("codeBanqueReturn", codeBanqueReturn);
						banquePrincipales=banquePrincipaleRepository.findAllBanquePrincipales(estSupprimer);
						model.addAttribute("banquePrincipales", banquePrincipales);		
						
							return "espaceUtilisateur";
					}	
					model.addAttribute("codeBanquePrincipaleErreur", " Code Banque déjà existant");	
					model.addAttribute("nomBanquePrincipaleErreur", " Nom Banque déjà existant");
			}	 
			model.addAttribute("formErreurBanquePrincipale", "formErreur");
			if(nomBanque==null || nomBanque.length()==0 || nomBanque.length()>50) {
				model.addAttribute("nomBanquePrincipaleErreur", "Nom de la Banque invalide");
			}
			if(codeBanque==null || codeBanque.length()==0 || codeBanque.length()>5) {
				model.addAttribute("codeBanquePrincipaleErreur", "Code de la Banque invalide");
			}
			Integer iterationBanque=0;
			banquePrincipales=banquePrincipaleRepository.findAllBanquePrincipales(estSupprimer);
			model.addAttribute("banquePrincipales", banquePrincipales);
			model.addAttribute("iterationBanque", iterationBanque);
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("resultatAjoutBanquePrincipale", "resultatAjoutBanquePrincipale");
			model.addAttribute("formulaireGestionBanquePrincipale", "formulaireGestionBanquePrincipale");
			model.addAttribute("listeBanquePrincipale", "listeBanquePrincipale");
			model.addAttribute("gestionBanquePrincipale", "gestionBanquePrincipale");
			model.addAttribute("menuNavigation", "menuNavigation");
	        return "espaceUtilisateur";			
	 }
	 
	 @Transactional
	 @RequestMapping(value = {"/modifierBanquePrincipale" }, method = RequestMethod.GET)
	    public String modifierBanquePrincipale(Model model, HttpSession session) { 
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
			
			Boolean estSupprimer=false;
			List<BanquePrincipale> banquePrincipales=new ArrayList<BanquePrincipale>();
			banquePrincipales=banquePrincipaleRepository.findAllBanquePrincipales(estSupprimer);
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionBanque", "Gestion Banque >");
			model.addAttribute("cheminModifierBanque", "Modifier une Banque >");
			model.addAttribute("titre", "Gestion des Banques");
			model.addAttribute("banquePrincipales", banquePrincipales);
			model.addAttribute("formulaireNumeroModifBanquePrincipale", "formulaireNumeroModifBanquePrincipale");
			model.addAttribute("actionTroisBouton", "actionTroisBouton");	
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("listeBanquePrincipale", "listeBanquePrincipale");
			model.addAttribute("gestionBanquePrincipale", "gestionBanquePrincipale");
			model.addAttribute("menuNavigation", "menuNavigation");
	        return "espaceUtilisateur";	
	    }
	 
	 @Transactional
	 	@RequestMapping(value = {"/formulaireNumeroModifBanquePrincipale"}, method = RequestMethod.POST)
	    public String formulaireNumeroModifBanquePrincipale(Model model, @ModelAttribute("banquePrincipale") BanquePrincipale banquePrincipale, HttpSession sessionUtilisateur, HttpSession session) {
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
			
			String codeBanque=banquePrincipale.getCodeBanque().trim();
			BanquePrincipale banqueRecherche=banquePrincipaleRepository.findBanquePrincipaleByCode(codeBanque);			
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionBanque", "Gestion Banque >");
			model.addAttribute("cheminModifierBanque", "Modifier une Banque >");
			model.addAttribute("titre", "Gestion des Banques");
			session.setAttribute("codeBanquePrincipaleCache", codeBanque);	
			if( banqueRecherche == null) {
				 return "redirect:/messageBanquePrincipaleNonExistante";  
			}							
			Boolean estSupprimer=false;
			List<BanquePrincipale> banquePrincipales=new ArrayList<BanquePrincipale>();
			banquePrincipales=banquePrincipaleRepository.findAllBanquePrincipales(estSupprimer);
			model.addAttribute("banquePrincipales", banquePrincipales);
			session.setAttribute("codeBanquePrincipaleCache", codeBanque);	
			model.addAttribute("formulaireGestionModifBanquePrincipale", "formulaireGestionModifBanquePrincipale");
			model.addAttribute("actionTroisBouton", "actionTroisBouton");	
			model.addAttribute("banquePrincipaleRecherche", banqueRecherche);
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("listeBanquePrincipale", "listeBanquePrincipale");
			model.addAttribute("gestionBanquePrincipale", "gestionBanquePrincipale");
			model.addAttribute("menuNavigation", "menuNavigation");			
	        return "espaceUtilisateur";	
	    }

	 	
//	 Resultat de la modification des données d'un formulaire
	 @Transactional
		@RequestMapping(value = {"/resultatModifBanquePrincipale" }, method = RequestMethod.POST)
	    public String resultatModifBanquePrincipale(Model model, @ModelAttribute("banquePrincipale") BanquePrincipale banquePrincipale, HttpSession sessionUtilisateur, HttpSession session) {
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
			
			String nomBanque=banquePrincipale.getNomBanque().trim();
			String codeBanque=banquePrincipale.getCodeBanque().trim();		
			List<BanquePrincipale> banquePrincipales=new ArrayList<BanquePrincipale>();
			Boolean estSupprimer=false;			
			String codeBanquePrincipaleCache=session.getAttribute("codeBanquePrincipaleCache").toString().trim();	
			BanquePrincipale banquePrincipaleRecherche=banquePrincipaleRepository.findBanquePrincipaleByCode(codeBanquePrincipaleCache);
			
			BanquePrincipale banquePrincipaleNom=banquePrincipaleRepository.findBanquePrincipaleByNom(nomBanque);
			BanquePrincipale banquePrincipaleCode=banquePrincipaleRepository.findBanquePrincipaleByCode(codeBanque);
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionBanque", "Gestion Banque >");
			model.addAttribute("cheminModifierBanque", "Modifier une Banque >");
			model.addAttribute("titre", "Gestion des Banques");
			
			
			if( nomBanque==null || nomBanque.length()==0 || nomBanque.length()>50 || codeBanque==null || codeBanque.length()==0 || codeBanque.length()>5 ) {						
				model.addAttribute("banquePrincipaleRecherche", banquePrincipaleRecherche);
				model.addAttribute("formErreurBanquePrincipale", "formErreur");
				model.addAttribute("identifiantSession", identifiantSession);
				model.addAttribute("actionTroisBouton", "actionTroisBouton");	
				model.addAttribute("formulaireGestionModifBanquePrincipale", "formulaireGestionModifBanquePrincipale");
				model.addAttribute("listeBanquePrincipale", "listeBanquePrincipale");
				model.addAttribute("gestionBanquePrincipale", "gestionBanquePrincipale");
				model.addAttribute("menuNavigation", "menuNavigation");	
				
				if(banquePrincipaleNom != null) {
					model.addAttribute("nomBanquePrincipaleErreur", " Nom Banque déjà existant");
				}
				if(banquePrincipaleCode != null) {
					model.addAttribute("codeBanquePrincipaleErreur", " Code Banque déjà existant");	
				}
				if(nomBanque==null || nomBanque.length()==0 || nomBanque.length()>50) {
					model.addAttribute("nomBanquePrincipaleErreur", "Nom de la Banque invalide");
				}
				if(codeBanque==null || codeBanque.length()==0 || codeBanque.length()>5) {
					model.addAttribute("codeBanquePrincipaleErreur", "Code de la Banque invalide");
				}					
				banquePrincipales=banquePrincipaleRepository.findAllBanquePrincipales(estSupprimer);
				model.addAttribute("banquePrincipales",banquePrincipales);				
				return "espaceUtilisateur";	
			}
			
			estSupprimer=false;
			banquePrincipales=banquePrincipaleRepository.findAllBanquePrincipales(estSupprimer);
			
			/////Recuperer l'Id de la Banque Recherchee
			Integer idBanquePrincipaleRecherche=banquePrincipaleRecherche.getIdBanque();
			
			///////
			if(banquePrincipaleCode!=null) {
				Integer idBanquePrincipaleCode=banquePrincipaleCode.getIdBanque();
				if(idBanquePrincipaleCode!=idBanquePrincipaleRecherche) {
					model.addAttribute("codeBanquePrincipaleErreur", "Code de la Banque déjà existant");			
					if(banquePrincipaleNom!=null) {
						Integer idBanquePrincipaleNom=banquePrincipaleNom.getIdBanque();
						if(idBanquePrincipaleNom!=idBanquePrincipaleRecherche) {
							model.addAttribute("nomBanquePrincipaleErreur", "Nom de la Banque déjà existant");
						}
					}
					model.addAttribute("banquePrincipaleRecherche", banquePrincipaleRecherche);
					model.addAttribute("formErreurBanquePrincipale", "formErreur");
					model.addAttribute("identifiantSession", identifiantSession);	
					model.addAttribute("formulaireGestionModifBanquePrincipale", "formulaireGestionModifBanquePrincipale");
					model.addAttribute("listeBanquePrincipale", "listeBanquePrincipale");
					model.addAttribute("gestionBanquePrincipale", "gestionBanquePrincipale");
					model.addAttribute("menuNavigation", "menuNavigation");	
					banquePrincipales=banquePrincipaleRepository.findAllBanquePrincipales(estSupprimer);
					model.addAttribute("banquePrincipales",banquePrincipales);				
					return "espaceUtilisateur";				
				}
			}
			
			if(banquePrincipaleCode!=null) {
				Integer idBanquePrincipaleCode=banquePrincipaleCode.getIdBanque();
				if(idBanquePrincipaleCode==idBanquePrincipaleRecherche) {
					
					if(banquePrincipaleNom!=null) {
						Integer idBanquePrincipaleNom=banquePrincipaleNom.getIdBanque();
						if(idBanquePrincipaleNom!=idBanquePrincipaleRecherche) {
							model.addAttribute("nomBanquePrincipaleErreur", "Nom de la Banque déjà existant");
							model.addAttribute("banquePrincipaleRecherche", banquePrincipaleRecherche);
							model.addAttribute("formErreurBanquePrincipale", "formErreur");
							model.addAttribute("identifiantSession", identifiantSession);	
							model.addAttribute("formulaireGestionModifBanquePrincipale", "formulaireGestionModifBanquePrincipale");
							model.addAttribute("listeBanquePrincipale", "listeBanquePrincipale");
							model.addAttribute("gestionBanquePrincipale", "gestionBanquePrincipale");
							model.addAttribute("menuNavigation", "menuNavigation");	
							banquePrincipales=banquePrincipaleRepository.findAllBanquePrincipales(estSupprimer);
							model.addAttribute("banquePrincipales",banquePrincipales);				
							return "espaceUtilisateur";	
						}
					}
								
				}
			}
			if(banquePrincipaleCode==null) {
				if(banquePrincipaleNom!=null) {
					Integer idBanquePrincipaleNom=banquePrincipaleNom.getIdBanque();
					if(idBanquePrincipaleNom!=idBanquePrincipaleRecherche) {
						model.addAttribute("nomBanquePrincipaleErreur", "Nom de la Banque déjà existant");
						model.addAttribute("banquePrincipaleRecherche", banquePrincipaleRecherche);
						model.addAttribute("formErreurBanquePrincipale", "formErreur");
						model.addAttribute("identifiantSession", identifiantSession);	
						model.addAttribute("formulaireGestionModifBanquePrincipale", "formulaireGestionModifBanquePrincipale");
						model.addAttribute("listeBanquePrincipale", "listeBanquePrincipale");
						model.addAttribute("gestionBanquePrincipale", "gestionBanquePrincipale");
						model.addAttribute("menuNavigation", "menuNavigation");	
						banquePrincipales=banquePrincipaleRepository.findAllBanquePrincipales(estSupprimer);
						model.addAttribute("banquePrincipales",banquePrincipales);				
						return "espaceUtilisateur";	
						
					}
				}
				
			}
					
			banquePrincipaleRecherche.setNomBanque(nomBanque);
			 banquePrincipaleRecherche.setCodeBanque(codeBanque);
			 banquePrincipaleRecherche.setEstSupprimer(estSupprimer);
			banquePrincipaleRepository.save(banquePrincipaleRecherche);
			banquePrincipales=banquePrincipaleRepository.findAllBanquePrincipales(estSupprimer);
			model.addAttribute("banquePrincipaleRecherche", banquePrincipaleRecherche);
			model.addAttribute("banquePrincipales", banquePrincipales);	
			model.addAttribute("listeBanquePrincipale", "listeBanquePrincipale");
			model.addAttribute("actionTroisBouton", "actionTroisBouton");	
			model.addAttribute("resultatModifBanquePrincipale", "resultatModifBanquePrincipale");
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("gestionBanquePrincipale", "gestionBanquePrincipale");
			model.addAttribute("menuNavigation", "menuNavigation");			
	        return "espaceUtilisateur";	
	    }
	 
	 
//	  Action Recherche Banque
	 @Transactional
	 @RequestMapping(value = {"/rechercheBanquePrincipale" }, method = RequestMethod.GET)
	    public String rechercherBanquePrincipale(Model model, HttpSession session) { 
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
			model.addAttribute("cheminGestionBanque", "Gestion Banque >");
			model.addAttribute("cheminRechercherBanque", "Rechercher une Banque >");
			model.addAttribute("titre", "Gestion des Banques");
			Boolean estSupprimer=false;
			List<BanquePrincipale> banquePrincipales=new ArrayList<BanquePrincipale>();
			banquePrincipales=banquePrincipaleRepository.findAllBanquePrincipales(estSupprimer);
			model.addAttribute("banquePrincipales", banquePrincipales);
			model.addAttribute("formulaireNumeroRechercheBanquePrincipale", "formulaireNumeroRechercheBanquePrincipale");
			model.addAttribute("actionTroisBouton", "actionTroisBouton");	
			model.addAttribute("identifiantSession", identifiantSession);  
			model.addAttribute("listeBanquePrincipale", "listeBanquePrincipale");
			model.addAttribute("gestionBanquePrincipale", "gestionBanquePrincipale");
			model.addAttribute("menuNavigation", "menuNavigation");
	        return "espaceUtilisateur";		        
	    }
	 
	 @Transactional
	 @RequestMapping(value = {"/resultatRechercheBanquePrincipale" }, method = RequestMethod.POST)
	    public String resultatRechercheBanquePrincipale(Model model, @ModelAttribute("banquePrincipale") BanquePrincipale banquePrincipale, HttpSession sessionUtilisateur, HttpSession session) {
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
			model.addAttribute("cheminGestionBanque", "Gestion Banque >");
			model.addAttribute("cheminRechercherBanque", "Rechercher une Banque >");
			model.addAttribute("titre", "Gestion des Banques");
			String codeBanque=banquePrincipale.getCodeBanque().trim();
			session.setAttribute("codeBanquePrincipaleCache", codeBanque);
			BanquePrincipale banquePrincipaleRecherche=banquePrincipaleRepository.findBanquePrincipaleByCode(codeBanque);
			if( banquePrincipaleRecherche == null) {				
				 return "redirect:/messageBanquePrincipaleNonExistante";  
			}								
			
			model.addAttribute("banquePrincipalesRecherche", banquePrincipaleRecherche);			
			model.addAttribute("resultatRechercheBanquePrincipale", "resultatRechercheBanquePrincipale");	
			model.addAttribute("actionTroisBouton", "actionTroisBouton");	
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("gestionBanquePrincipale", "gestionBanquePrincipale");	
			model.addAttribute("menuNavigation", "menuNavigation");		
	        return "espaceUtilisateur";	
	    }
	 
	 @Transactional
	 @RequestMapping(value = {"/messageBanquePrincipaleNonExistante" }, method = RequestMethod.GET)
	    public String messageBanqueNonExistante(Model model, @ModelAttribute("banquePrincipale") BanquePrincipale banquePrincipale, HttpSession sessionUtilisateur, HttpSession session) {
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
			model.addAttribute("cheminGestionBanque", "Gestion Banque >");
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionBanque", "Gestion Banque >");
			model.addAttribute("cheminBanqueNonExistante", "Banque non existante >");
			model.addAttribute("titre", "Gestion des Banques");
			String codeBanquePrincipaleCache=session.getAttribute("codeBanquePrincipaleCache").toString().trim();
			model.addAttribute("codeBanque", codeBanquePrincipaleCache);	
			model.addAttribute("messageBanqueNonExistante", "messageBanqueNonExistante");		
			model.addAttribute("actionTroisBouton", "actionTroisBouton");	
			Boolean estSupprimer=false;
			List<BanquePrincipale> banquePrincipales=new ArrayList<BanquePrincipale>();
			banquePrincipales=banquePrincipaleRepository.findAllBanquePrincipales(estSupprimer);
			model.addAttribute("banquePrincipales", banquePrincipales);
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("listeBanquePrincipale", "listeBanquePrincipale");
			model.addAttribute("gestionBanquePrincipale", "gestionBanquePrincipale");
			model.addAttribute("menuNavigation", "menuNavigation");		
	        return "espaceUtilisateur";	
	    }
	 	
//	 Action suppression Banque
	 @Transactional
	 	@RequestMapping(value = {"/supprimerBanquePrincipale" }, method = RequestMethod.GET)
	    public String supprimerBanquePrincipale(Model model, HttpSession session) { 
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
			model.addAttribute("cheminGestionBanque", "Gestion Banque >");
			model.addAttribute("cheminSupprimerBanque", "Supprimer une Banque >");
			model.addAttribute("titre", "Gestion des Banques");
			Boolean estSupprimer=false;
			List<BanquePrincipale> banquePrincipales=new ArrayList<BanquePrincipale>();
			banquePrincipales=banquePrincipaleRepository.findAllBanquePrincipales(estSupprimer);
			model.addAttribute("banquePrincipales", banquePrincipales);
			model.addAttribute("formulaireNumeroSupprimerBanquePrincipale", "formulaireNumeroSupprimerBanquePrincipale");
			model.addAttribute("actionTroisBouton", "actionTroisBouton");	
			model.addAttribute("identifiantSession", identifiantSession);  
			model.addAttribute("listeBanquePrincipale", "listeBanquePrincipale");
			model.addAttribute("gestionBanquePrincipale", "gestionBanquePrincipale");
			model.addAttribute("menuNavigation", "menuNavigation");
	        return "espaceUtilisateur";	        
	    }
	 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	 @Transactional
	 @RequestMapping(value = {"/resultatSupprimerBanquePrincipale" }, method = RequestMethod.POST)
	    public String resultatSupprimerBanquePrincipale(Model model, @ModelAttribute("banquePrincipale") BanquePrincipale banquePrincipale, HttpSession sessionUtilisateur, HttpSession session) {
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
			model.addAttribute("cheminGestionBanque", "Gestion Banque >");
			model.addAttribute("cheminSupprimerBanque", "Supprimer une Banque >");
			model.addAttribute("titre", "Gestion des Banques");
			String codeBanque=banquePrincipale.getCodeBanque().trim();
			session.setAttribute("codeBanquePrincipaleCache", codeBanque);
			BanquePrincipale banquePrincipaleRecherche=banquePrincipaleRepository.findBanquePrincipaleByCode(codeBanque);
			if( banquePrincipaleRecherche == null) {				
				 return "redirect:/messageBanquePrincipaleNonExistante";  
			}								
			Boolean estSupprimer=false;
			List<BanquePrincipale> banquePrincipales=new ArrayList<BanquePrincipale>();
			banquePrincipales=banquePrincipaleRepository.findAllBanquePrincipales(estSupprimer);
			model.addAttribute("banquePrincipaleRecherche", banquePrincipaleRecherche);			
			model.addAttribute("dialog_boxBanquePrincipale", "dialog_boxBanquePrincipale");	
			model.addAttribute("dialog_backgroundBanquePrincipale", "dialog_backgroundBanquePrincipale");	
			model.addAttribute("formulaireNumeroSupprimerBanquePrincipale", "formulaireNumeroSupprimerBanquePrincipale");
			model.addAttribute("actionTroisBouton", "actionTroisBouton");	
			model.addAttribute("banquePrincipales", banquePrincipales);
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("codeBanque", codeBanque);
			model.addAttribute("listeBanquePrincipale", "listeBanquePrincipale");
			model.addAttribute("gestionBanquePrincipale", "gestionBanquePrincipale");
			model.addAttribute("menuNavigation", "menuNavigation");		
	        return "espaceUtilisateur";	
	    }
	 
	 @Transactional
	 @RequestMapping(value = {"/succesSuppressionBanquePrincipale" }, method = RequestMethod.POST)
	    public String succesSuppressionBanquePrincipale(Model model, @ModelAttribute("banquePrincipale") BanquePrincipale banquePrincipale, HttpSession sessionUtilisateur, HttpSession session) {
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
			model.addAttribute("cheminGestionBanque", "Gestion Banque >");
			model.addAttribute("cheminSupprimerBanque", "Supprimer une Banque >");
			model.addAttribute("titre", "Gestion des Banques");
			String codeBanque=banquePrincipale.getCodeBanque().trim();
			String codeBanquePrincipaleSuppression=session.getAttribute("codeBanquePrincipaleCache").toString().trim();
			BanquePrincipale banquePrincipaleSuppression=banquePrincipaleRepository.findBanquePrincipaleByCode(codeBanquePrincipaleSuppression);
			banquePrincipaleSuppression.setEstSupprimer(true);
			banquePrincipale=banquePrincipaleRepository.save(banquePrincipaleSuppression);
			Boolean estSupprimer=false;				
			model.addAttribute("codeBanque", banquePrincipale.getCodeBanque() );
			model.addAttribute("resultatSuppressionBanquePrincipale", "resultatSuppressionBanquePrincipale");	
			model.addAttribute("actionTroisBouton", "actionTroisBouton");
			List<BanquePrincipale> banquePrincipales=new ArrayList<BanquePrincipale>();
			banquePrincipales=banquePrincipaleRepository.findAllBanquePrincipales(estSupprimer);
			model.addAttribute("banquePrincipales",banquePrincipales);
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("listeBanquePrincipale", "listeBanquePrincipale");
			model.addAttribute("gestionBanquePrincipale", "gestionBanquePrincipale");
			model.addAttribute("menuNavigation", "menuNavigation");			
	        return "espaceUtilisateur";	
	    }
	
	 @Transactional
	 @RequestMapping(value = {"/resultatModifDonneeBanquePrincipale" }, method = RequestMethod.POST)
	    public String resultatModifDonneeBanquePrincipale(Model model, @ModelAttribute("banquePrincipale") BanquePrincipale banquePrincipale, HttpSession sessionUtilisateur, HttpSession session) {
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
			model.addAttribute("cheminGestionBanque", "Gestion Banque >");
			model.addAttribute("cheminSupprimerBanque", "Supprimer une Banque >");
			String codeBanque=banquePrincipale.getCodeBanque().trim();
			session.removeAttribute("codeBanquePrincipaleCache");
			session.setAttribute("codeBanquePrincipaleCache",codeBanque);						
			model.addAttribute("dialog_boxBanquePrincipale", "dialog_boxBanquePrincipale");	
			model.addAttribute("dialog_backgroundBanquePrincipale", "dialog_backgroundBanquePrincipale");				
			Boolean estSupprimer=false;	
			model.addAttribute("codeBanque", codeBanque);	
			model.addAttribute("actionTroisBouton", "actionTroisBouton");
			List<BanquePrincipale> banquePrincipales=new ArrayList<BanquePrincipale>();
			banquePrincipales=banquePrincipaleRepository.findAllBanquePrincipales(estSupprimer);
			model.addAttribute("banquePrincipales", banquePrincipales);
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("listeBanquePrincipale", "listeBanquePrincipale");
			model.addAttribute("gestionBanquePrincipale", "gestionBanquePrincipale");
			model.addAttribute("menuNavigation", "menuNavigation");			
	        return "espaceUtilisateur";	
	    }
	 
	 @Transactional
	 @RequestMapping(value = {"/envoiDonneeCacheeBanquePrincipale" }, method = RequestMethod.POST)
	    public String envoiDonneeCacheeBanquePrincipale(Model model, @ModelAttribute("banquePrincipale") BanquePrincipale banquePrincipale, HttpSession sessionUtilisateur, HttpSession session) {
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
			model.addAttribute("cheminGestionBanque", "Gestion Banque >");
			model.addAttribute("cheminModifierBanque", "Modifier une Banque >");
			model.addAttribute("titre", "Gestion des Banques");
			session.removeAttribute("codeBanquePrincipaleCache");
			String codeBanque=banquePrincipale.getCodeBanque().trim();
			BanquePrincipale banquePrincipaleRecherche=banquePrincipaleRepository.findBanquePrincipaleByCode(codeBanque);
			Boolean estSupprimer=false;
			List<BanquePrincipale> banquePrincipales=new ArrayList<BanquePrincipale>();
			banquePrincipales=banquePrincipaleRepository.findAllBanquePrincipales(estSupprimer);
			session.setAttribute("codeBanquePrincipaleCache", codeBanque);
			model.addAttribute("banquePrincipales", banquePrincipales);
			session.setAttribute("codeBanque", codeBanque);	
			model.addAttribute("formulaireGestionModifBanquePrincipale", "formulaireGestionModifBanquePrincipale");
			model.addAttribute("actionTroisBouton", "actionTroisBouton");	
			model.addAttribute("banquePrincipaleRecherche", banquePrincipaleRecherche);
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("listeBanquePrincipale", "listeBanquePrincipale");
			model.addAttribute("gestionBanquePrincipale", "gestionBanquePrincipale");
			model.addAttribute("menuNavigation", "menuNavigation");			
	        return "espaceUtilisateur";	
	    }
	 
	 
	 

}
