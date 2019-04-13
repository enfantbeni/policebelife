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

import com.belife.policemanager.model.dto.BanqueDto;
import com.belife.policemanager.model.entity.Banque;
import com.belife.policemanager.model.entity.BanquePrincipale;
import com.belife.policemanager.model.repository.BanquePrincipaleRepository;
import com.belife.policemanager.model.repository.BanqueRepository;
import com.belife.policemanager.model.repository.RolesRepository;
import com.belife.policemanager.model.repository.SourcePoliceRepository;
import com.belife.policemanager.model.repository.UtilisateurRepository;

@Controller
public class BanqueController {
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
	 @RequestMapping(value = {"/gestionBanque" }, method = RequestMethod.GET)
	    public String gestionBanque(Model model, HttpSession session) { 
		 
		 String resultat=null;
			try {
				identifiantSession=session.getAttribute("identifiantSession").toString().trim();
			}
			catch(Exception e) {
				resultat="pageErreur";
				return resultat;
			}
			Boolean estSupprimer=false;
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionGuichet", "Gestion Guichet >");
			model.addAttribute("titre", "Gestion des Guichets de Banque");
			List<Banque> banques=new ArrayList<Banque>();		
			banques=banqueRepository.findAllBanques(estSupprimer);
			model.addAttribute("banques", banques);
			model.addAttribute("listeBanque", "listeBanque");
			model.addAttribute("gestionBanque", "gestionBanque");
			model.addAttribute("menuNavigation", "menuNavigation");
	        return "espaceUtilisateur";			
	    }
	 
	 @Transactional
	 @RequestMapping(value = {"/ajoutBanque" }, method = RequestMethod.GET)
	    public String ajoutBanque(Model model, HttpSession session) { 
		 
		 String resultat=null;
			try {
				identifiantSession=session.getAttribute("identifiantSession").toString().trim();
			}
			catch(Exception e) {
				resultat="pageErreur";
				return resultat;
			}
			Boolean estSupprimer=false;
			List<Banque> banques=new ArrayList<Banque>();
			List<BanquePrincipale> banquePrincipales=new ArrayList<BanquePrincipale>();
			List<String> nomBanquePrincipales=new ArrayList<String>();
			banques=banqueRepository.findAllBanques(estSupprimer);
			nomBanquePrincipales=banquePrincipaleRepository.findNomsBanquePrincipale(estSupprimer);
			model.addAttribute("nomBanquePrincipales", nomBanquePrincipales);
			model.addAttribute("banques", banques);
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionGuichet", "Gestion Guichet >");
			model.addAttribute("cheminAjouterGuichet", "Ajouter un Guichet >");
			model.addAttribute("titre", "Gestion des Guichets");
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("formulaireGestionBanque", "formulaireGestionBanque");
			model.addAttribute("listeBanque", "listeBanque");
			model.addAttribute("gestionBanque", "gestionBanque");
			model.addAttribute("menuNavigation", "menuNavigation");
	        return "espaceUtilisateur";			
	    }
	 
	 @Transactional
	 @RequestMapping(value = {"/resultatAjoutBanque" }, method = RequestMethod.POST)
	    public String resultatAjoutBanque(Model model, @ModelAttribute("banque") Banque banque, @ModelAttribute("banqueDto") BanqueDto banqueDto , HttpSession session) { 		 
		 String resultat=null;
			try {
				identifiantSession=session.getAttribute("identifiantSession").toString().trim();
			}
			catch(Exception e) {
				resultat="pageErreur";
				return resultat;
			}
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionGuichet", "Gestion Guichet >");
			model.addAttribute("cheminAjouterGuichet", "Ajout un Guichet >");
			model.addAttribute("titre", "Gestion des Guichets");
			String nomBanque=banqueDto.getNomBanque().trim();
			String codeGuichet=banqueDto.getCodeGuichet().trim();
			String nomGuichet=banqueDto.getNomGuichet().trim();
			BanquePrincipale idBanquePrincipale=new BanquePrincipale();
			List<Banque> banques=new ArrayList<Banque>();
			Banque nomGuichetVerification=new Banque();
			Banque codeGuichetVerification=new Banque();
			Boolean estSupprimer=false;
			Banque banqueSave=null;
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("gestionBanque", "gestionBanque");
			model.addAttribute("menuNavigation", "menuNavigation");
			nomGuichetVerification=banqueRepository.findBanqueByNomGuichet(nomGuichet);
			codeGuichetVerification=banqueRepository.findBanqueByCodeGuichet(codeGuichet);
			if( nomGuichet != null && nomGuichet.length() > 0 && nomGuichet.length()<=50 && codeGuichet != null && codeGuichet.length() > 0 && codeGuichet.length()<=5 && nomGuichetVerification ==null && codeGuichetVerification == null ) {						
								
						banque.setCodeGuichet(codeGuichet);
						banque.setNomGuichet(nomGuichet);
						idBanquePrincipale=banquePrincipaleRepository.findBanquePrincipaleByNom(nomBanque);
						banque.setIdBanquePrincipale(idBanquePrincipale);
						banque.setEstSupprimer(estSupprimer);
						model.addAttribute("listeBanque", "listeBanque");
						model.addAttribute("gestionBanque", "gestionBanque");
						Banque banqueReturn=banqueRepository.save(banque);
						model.addAttribute("ajoutSuccesBanque", "Un Guichet de code "+banqueReturn.getCodeGuichet()+" ajoutée avec succès");	
						model.addAttribute("ajoutSuccesBanque", banqueReturn.getCodeGuichet());
						banques=banqueRepository.findAllBanques(estSupprimer);
						model.addAttribute("banques", banques);					
						return "espaceUtilisateur";
				
			}	 
			model.addAttribute("formErreurBanque", "formErreur");
			
			if(codeGuichet==null || codeGuichet.length()==0 || codeGuichet.length()>5) {
				model.addAttribute("codeGuichetErreur", "Code Guichet invalide");
			}
			if(nomGuichet==null || nomGuichet.length()==0 || nomGuichet.length()>50) {
				model.addAttribute("nomGuichetErreur", " Nom guichet invalide");
			}
			if(codeGuichetVerification!=null) {
				model.addAttribute("codeGuichetErreur", "Code Guichet déjà existant");
			}
			if(nomGuichetVerification!=null ) {
				model.addAttribute("nomGuichetErreur", " Nom guichet déjà existant");
			}

			model.addAttribute("codeGuichetErreur", " Code guichet déjà existant");	
			banques=banqueRepository.findAllBanques(estSupprimer);
			model.addAttribute("banques", banques);
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("resultatAjoutBanque", "resultatAjoutBanque");
			model.addAttribute("formulaireGestionBanque", "formulaireGestionBanque");
			model.addAttribute("listeBanque", "listeBanque");
			model.addAttribute("gestionBanque", "gestionBanque");
			model.addAttribute("menuNavigation", "menuNavigation");
	        return "espaceUtilisateur";			
	 }
	 
	 @Transactional
	 @RequestMapping(value = {"/modifierBanque" }, method = RequestMethod.GET)
	    public String modifierUtilisateur(Model model, HttpSession session) { 
			String resultat=null;
			try {
				identifiantSession=session.getAttribute("identifiantSession").toString().trim();
			}
			catch(Exception e) {
				resultat="pageErreur";
				return resultat;
			}		
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionGuichet", "Gestion Guichet >");
			model.addAttribute("cheminModifierGuichet", "Modifier un Guichet >");
			model.addAttribute("titre", "Gestion des Guichets");
			Boolean estSupprimer=false;
			List<Banque> banques=new ArrayList<Banque>();
			banques=banqueRepository.findAllBanques(estSupprimer);
			model.addAttribute("banques", banques);
			model.addAttribute("formulaireNumeroModifBanque", "formulaireNumeroModifBanque");
			model.addAttribute("actionTroisBouton", "actionTroisBouton");	
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("listeBanque", "listeBanque");
			model.addAttribute("gestionBanque", "gestionBanque");
			model.addAttribute("menuNavigation", "menuNavigation");
	        return "espaceUtilisateur";	
	    }
	 
	 @Transactional
	 @RequestMapping(value = {"/formulaireNumeroModifBanque" }, method = RequestMethod.POST)
	    public String formulaireNumeroModifBanque(Model model, @ModelAttribute("banque") Banque banque, HttpSession sessionUtilisateur, HttpSession session) {
			String resultat=null;
			try {
				identifiantSession=session.getAttribute("identifiantSession").toString().trim();
			}
			catch(Exception e) {
				resultat="pageErreur";
				return resultat;
			}
			String codeGuichet=banque.getCodeGuichet().trim();
			Banque banqueRecherche=banqueRepository.findBanqueByCodeGuichet(codeGuichet);		
			session.setAttribute("codeGuichetCache",codeGuichet);	
			if( banqueRecherche == null) {
				 return "redirect:/messageBanqueNonExistante";  
			}	
			Boolean estSupprimer=false;
			List<String> nomBanquePrincipales=new ArrayList<String>();
			nomBanquePrincipales=banquePrincipaleRepository.findNomsBanquePrincipale(estSupprimer);
			model.addAttribute("nomBanquePrincipales", nomBanquePrincipales);
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionGuichet", "Gestion Guichet >");
			model.addAttribute("cheminModifierGuichet", "Modifier un Guichet >");
			model.addAttribute("titre", "Gestion des Guichets");
			List<Banque> banques=new ArrayList<Banque>();
			banques=banqueRepository.findAllBanques(estSupprimer);
			model.addAttribute("banques", banques);
			session.setAttribute("codeGuichetCache", codeGuichet);	
			model.addAttribute("formulaireGestionModifBanque", "formulaireGestionModifBanque");
			model.addAttribute("actionTroisBouton", "actionTroisBouton");	
			model.addAttribute("banqueRecherche", banqueRecherche);
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("listeBanque", "listeBanque");
			model.addAttribute("gestionBanque", "gestionBanque");
			model.addAttribute("menuNavigation", "menuNavigation");			
	        return "espaceUtilisateur";	
	    }
	 
	 @Transactional
	 @RequestMapping(value = {"/resultatModifBanque" }, method = RequestMethod.POST)
	    public String resultatModifBanque(Model model, @ModelAttribute("banque") Banque banque , @ModelAttribute("banqueDto") BanqueDto banqueDto, HttpSession sessionUtilisateur, HttpSession session) {
		 String resultat=null;
			try {
				identifiantSession=session.getAttribute("identifiantSession").toString().trim();
			}
			catch(Exception e) {
				resultat="pageErreur";
				return resultat;
			}
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionGuichet", "Gestion Guichet >");
			model.addAttribute("cheminModifierGuichet", "Modifier un Guichet >");
			model.addAttribute("titre", "Gestion des Guichets");
			String nomBanque=banqueDto.getNomBanque().trim();
			String codeGuichet=banqueDto.getCodeGuichet().trim();
			String nomGuichet=banqueDto.getNomGuichet().trim();
			
			BanquePrincipale idBanquePrincipale=new BanquePrincipale();
			List<Banque> banques=new ArrayList<Banque>();
			Banque nomGuichetVerification=new Banque();
			Banque codeGuichetVerification=new Banque();
			Boolean estSupprimer=false;
			Banque banqueSave=null;
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("gestionBanque", "gestionBanque");
			model.addAttribute("menuNavigation", "menuNavigation");
			nomGuichetVerification=banqueRepository.findBanqueByNomGuichet(nomGuichet);
			codeGuichetVerification=banqueRepository.findBanqueByCodeGuichet(codeGuichet);
			String codeGuichetCache=session.getAttribute("codeGuichetCache").toString().trim();
			Banque banqueRecherche=banqueRepository.findBanqueByCodeGuichet(codeGuichetCache);	
				
			model.addAttribute("banqueRecherche", banqueRecherche);
			List<String> nomBanquePrincipales=new ArrayList<String>();
			nomBanquePrincipales=banquePrincipaleRepository.findNomsBanquePrincipale(estSupprimer);
			model.addAttribute("nomBanquePrincipales", nomBanquePrincipales);
			
			if( nomGuichet == null || nomGuichet.length() == 0 || nomGuichet.length()>50 || codeGuichet == null || codeGuichet.length() == 0 || codeGuichet.length()>5 ) {						
							
						banque.setCodeGuichet(codeGuichet);
						banque.setNomGuichet(nomGuichet);
						idBanquePrincipale=banquePrincipaleRepository.findBanquePrincipaleByNom(nomBanque);
						banque.setIdBanquePrincipale(idBanquePrincipale);
						banque.setEstSupprimer(estSupprimer);
						model.addAttribute("listeBanque", "listeBanque");
						model.addAttribute("gestionBanque", "gestionBanque");
						model.addAttribute("formErreurBanque", "formErreur");
						model.addAttribute("formulaireGestionModifBanque", "formulaireGestionModifBanque");
						model.addAttribute("banqueRecherche", banqueRecherche);
						banques=banqueRepository.findAllBanques(estSupprimer);
						model.addAttribute("banques", banques);	
									
						if(codeGuichet==null || codeGuichet.length()==0 || codeGuichet.length()>5) {
							model.addAttribute("codeGuichetErreur", "Code Guichet invalide");
						}
						if(nomGuichet==null || nomGuichet.length()==0 || nomGuichet.length()>50) {
							model.addAttribute("nomGuichetErreur", " Nom guichet invalide");
						}
						
						banques=banqueRepository.findAllBanques(estSupprimer);
						model.addAttribute("banques", banques);					
						return "espaceUtilisateur";
							
			}	
//			Banque banqueRecherche=banqueRepository.findBanqueByCodeGuichet(codeGuichetCache);
			
			
		/////Recuperer l'Id de la Banque Recherchee
					Integer idBanqueRecherche=banqueRecherche.getIdBanque();
					
					/////// Premier cas Code Guichet
					if(codeGuichetVerification!=null) {
						Integer idCodeGuichetVerification=codeGuichetVerification.getIdBanque();
						if(idCodeGuichetVerification!=idBanqueRecherche) {
							model.addAttribute("codeGuichetErreur", "Code Guichet déjà existant");			
							if(nomGuichetVerification!=null) {
								Integer idNomGuichetVerification=nomGuichetVerification.getIdBanque();
								if(idNomGuichetVerification!=idBanqueRecherche) {
									model.addAttribute("nomGuichetErreur", "Nom Guichet déjà existant");
									System.out.println("    Code : "+idNomGuichetVerification);
								}
							}
							model.addAttribute("banqueRecherche", banqueRecherche);
							model.addAttribute("formErreurBanque", "formErreur");
							model.addAttribute("identifiantSession", identifiantSession);	
							model.addAttribute("formulaireGestionModifBanque", "formulaireGestionModifBanque");
							model.addAttribute("listeBanque", "listeBanque");
							model.addAttribute("gestionBanque", "gestionBanque");
							model.addAttribute("menuNavigation", "menuNavigation");	
							banques=banqueRepository.findAllBanques(estSupprimer);
							model.addAttribute("banques",banques);				
							return "espaceUtilisateur";				
						}
					}
				/////// Deuxieme cas
					if(codeGuichetVerification!=null) {
							Integer idCodeGuichetVerification=codeGuichetVerification.getIdBanque();
							if(idCodeGuichetVerification==idBanqueRecherche) {										
								if(nomGuichetVerification!=null) {
									Integer idNomGuichetVerification=nomGuichetVerification.getIdBanque();
									if(idNomGuichetVerification!=idBanqueRecherche) {
											model.addAttribute("nomGuichetErreur", "Nom Guichet déjà existant");
											
											}
										}
										model.addAttribute("banqueRecherche", banqueRecherche);
										model.addAttribute("formErreurBanque", "formErreur");
										model.addAttribute("identifiantSession", identifiantSession);	
										model.addAttribute("formulaireGestionModifBanque", "formulaireGestionModifBanque");
										model.addAttribute("listeBanque", "listeBanque");
										model.addAttribute("gestionBanque", "gestionBanque");
										model.addAttribute("menuNavigation", "menuNavigation");	
										banques=banqueRepository.findAllBanques(estSupprimer);
										model.addAttribute("banques",banques);				
										return "espaceUtilisateur";				
								}
						}
				/////// Troisième cas
								if(codeGuichetVerification==null) {															
										if(nomGuichetVerification!=null) {
													Integer idNomGuichetVerification=nomGuichetVerification.getIdBanque();
													if(idNomGuichetVerification!=idBanqueRecherche) {
															model.addAttribute("nomGuichetErreur", "Nom Guichet déjà existant");
															
															model.addAttribute("banqueRecherche", banqueRecherche);
															model.addAttribute("formErreurBanque", "formErreur");
															model.addAttribute("identifiantSession", identifiantSession);	
															model.addAttribute("formulaireGestionModifBanque", "formulaireGestionModifBanque");
															model.addAttribute("listeBanque", "listeBanque");
															model.addAttribute("gestionBanque", "gestionBanque");
															model.addAttribute("menuNavigation", "menuNavigation");	
															banques=banqueRepository.findAllBanques(estSupprimer);
															model.addAttribute("banques",banques);				
															return "espaceUtilisateur";		
															
															}
														}
																
												
										}
								
							/////// Deuxieme cas
								if(nomGuichetVerification!=null) {
										Integer idNomGuichetVerification=nomGuichetVerification.getIdBanque();
										if(idNomGuichetVerification==idBanqueRecherche) {										
											if(codeGuichetVerification!=null) {
												Integer idCodeGuichetVerification=codeGuichetVerification.getIdBanque();
												if(idCodeGuichetVerification!=idBanqueRecherche) {
														model.addAttribute("codeGuichetErreur", "Code Guichet déjà existant");
														
														}
													}
													model.addAttribute("banqueRecherche", banqueRecherche);
													model.addAttribute("formErreurBanque", "formErreur");
													model.addAttribute("identifiantSession", identifiantSession);	
													model.addAttribute("formulaireGestionModifBanque", "formulaireGestionModifBanque");
													model.addAttribute("listeBanque", "listeBanque");
													model.addAttribute("gestionBanque", "gestionBanque");
													model.addAttribute("menuNavigation", "menuNavigation");	
													banques=banqueRepository.findAllBanques(estSupprimer);
													model.addAttribute("banques",banques);				
													return "espaceUtilisateur";				
											}
									}
								if(nomGuichetVerification==null) {															
									if(codeGuichetVerification!=null) {
												Integer idCodeGuichetVerification=codeGuichetVerification.getIdBanque();
												if(idCodeGuichetVerification!=idBanqueRecherche) {
														model.addAttribute("codeGuichetErreur", "Code Guichet déjà existant");
														
														model.addAttribute("banqueRecherche", banqueRecherche);
														model.addAttribute("formErreurBanque", "formErreur");
														model.addAttribute("identifiantSession", identifiantSession);	
														model.addAttribute("formulaireGestionModifBanque", "formulaireGestionModifBanque");
														model.addAttribute("listeBanque", "listeBanque");
														model.addAttribute("gestionBanque", "gestionBanque");
														model.addAttribute("menuNavigation", "menuNavigation");	
														banques=banqueRepository.findAllBanques(estSupprimer);
														model.addAttribute("banques",banques);				
														return "espaceUtilisateur";		
														
														}
													}
															
											
									}
			
			model.addAttribute("formErreurBanque", "formErreur");
			
			if(codeGuichet==null || codeGuichet.length()==0 || codeGuichet.length()>5) {
				model.addAttribute("codeGuichetErreur", "Code Guichet invalide");
			}
			if(nomGuichet==null || nomGuichet.length()==0 || nomGuichet.length()>50) {
				model.addAttribute("nomGuichetErreur", " Nom guichet invalide");
			}
			if(codeGuichetVerification!=null) {
				model.addAttribute("codeGuichetErreur", "Code Guichet déjà existant");
			}
			if(nomGuichetVerification!=null ) {
				model.addAttribute("nomGuichetErreur", " Nom guichet déjà existant");
			}

			model.addAttribute("codeGuichetErreur", " Code guichet déjà existant");	
			banques=banqueRepository.findAllBanques(estSupprimer);
			model.addAttribute("banques", banques);
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("resultatAjoutBanque", "resultatAjoutBanque");
			model.addAttribute("formulaireGestionBanque", "formulaireGestionBanque");
			model.addAttribute("listeBanque", "listeBanque");
			model.addAttribute("gestionBanque", "gestionBanque");
			model.addAttribute("menuNavigation", "menuNavigation");
	        return "espaceUtilisateur";	
	    }
	 

	 
//	  Action Recherche Banque
	 @Transactional
	 @RequestMapping(value = {"/rechercheBanque" }, method = RequestMethod.GET)
	    public String rechercherBanque(Model model, HttpSession session) { 
			String resultat=null;
			try {
				identifiantSession=session.getAttribute("identifiantSession").toString().trim();
			}
			catch(Exception e) {
				resultat="pageErreur";
				return resultat;
			}		
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionGuichet", "Gestion Guichet >");
			model.addAttribute("cheminRechercherGuichet", "Rechercher un Guichet >");
			model.addAttribute("titre", "Gestion des Guichets");
			Boolean estSupprimer=false;
			List<Banque> banques=new ArrayList<Banque>();
			banques=banqueRepository.findAllBanques(estSupprimer);
			model.addAttribute("banques", banques);
			model.addAttribute("formulaireNumeroRechercheBanque", "formulaireNumeroRechercheBanque");
			model.addAttribute("actionTroisBouton", "actionTroisBouton");	
			model.addAttribute("identifiantSession", identifiantSession);  
			model.addAttribute("listeBanque", "listeBanque");
			model.addAttribute("gestionBanque", "gestionBanque");
			model.addAttribute("menuNavigation", "menuNavigation");
	        return "espaceUtilisateur";		        
	    }
	 
	 @Transactional
	 @RequestMapping(value = {"/resultatRechercheGuichet" }, method = RequestMethod.POST)
	    public String resultatRechercheGuichet(Model model, @ModelAttribute("banque") Banque banque, HttpSession sessionUtilisateur, HttpSession session) {
			String resultat=null;
			try {
				identifiantSession=session.getAttribute("identifiantSession").toString().trim();
			}
			catch(Exception e) {
				resultat="pageErreur";
				return resultat;
			}
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionGuichet", "Gestion Guichet >");
			model.addAttribute("cheminRechercherGuichet", "Rechercher un Guichet >");
			model.addAttribute("titre", "Gestion des Guichets");
			String codeGuichet=banque.getCodeGuichet().trim();
			session.setAttribute("codeGuichetCache", codeGuichet);
			Banque banqueRecherche=banqueRepository.findBanqueByCodeGuichet(codeGuichet);
			if( banqueRecherche == null) {				
				 return "redirect:/messageBanqueNonExistante";  
			}								
			Boolean estSupprimer=false;
			List<Banque> banques=new ArrayList<Banque>();
			banques=banqueRepository.findAllBanques(estSupprimer);
			model.addAttribute("banqueRecherche", banqueRecherche);			
			model.addAttribute("resultatRechercheGuichet", "resultatRechercheGuichet");	
			model.addAttribute("actionTroisBouton", "actionTroisBouton");	
			model.addAttribute("banqueRecherche", banqueRecherche);
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("gestionBanque", "gestionBanque");
			model.addAttribute("menuNavigation", "menuNavigation");		
	        return "espaceUtilisateur";	
	    }
	 @Transactional
	 @RequestMapping(value = {"/messageBanqueNonExistante" }, method = RequestMethod.GET)
	    public String messageBanqueNonExistante(Model model, @ModelAttribute("banque") Banque banque, HttpSession sessionUtilisateur, HttpSession session) {
			String resultat=null;
			try {
				identifiantSession=session.getAttribute("identifiantSession").toString().trim();
			}
			catch(Exception e) {
				resultat="pageErreur";
				return resultat;
			}
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionGuichet", "Gestion Guichet >");
			model.addAttribute("cheminRechercherGuichet", "Rechercher un Guichet >");
			model.addAttribute("titre", "Gestion des Guichets");
			String codeGuichetCache=session.getAttribute("codeGuichetCache").toString().trim();
			model.addAttribute("codeGuichet", codeGuichetCache);	
			model.addAttribute("messageBanqueNonExistante", "messageBanqueNonExistante");		
			model.addAttribute("actionTroisBouton", "actionTroisBouton");	
			Boolean estSupprimer=false;
			List<Banque> banques=new ArrayList<Banque>();
			banques=banqueRepository.findAllBanques(estSupprimer);
			model.addAttribute("banques", banques);
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("listeBanque", "listeBanque");
			model.addAttribute("gestionBanque", "gestionBanque");
			model.addAttribute("menuNavigation", "menuNavigation");		
	        return "espaceUtilisateur";	
	    }
	 	
//	 Action suppression Banque
	 @Transactional
	 	@RequestMapping(value = {"/supprimerBanque" }, method = RequestMethod.GET)
	    public String supprimerBanque(Model model, HttpSession session) { 
			String resultat=null;
			try {
				identifiantSession=session.getAttribute("identifiantSession").toString().trim();
			}
			catch(Exception e) {
				resultat="pageErreur";
				return resultat;
			}	
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionGuichet", "Gestion Guichet >");
			model.addAttribute("cheminSupprimerGuichet", "Supprimer un Guichet >");
			model.addAttribute("titre", "Gestion des Guichets");
			Boolean estSupprimer=false;
			List<Banque> banques=new ArrayList<Banque>();
			banques=banqueRepository.findAllBanques(estSupprimer);
			model.addAttribute("banques", banques);
			model.addAttribute("formulaireNumeroSupprimerBanque", "formulaireNumeroSupprimerBanque");
			model.addAttribute("actionTroisBouton", "actionTroisBouton");	
			model.addAttribute("identifiantSession", identifiantSession);  
			model.addAttribute("listeBanque", "listeBanque");
			model.addAttribute("gestionBanque", "gestionBanque");
			model.addAttribute("menuNavigation", "menuNavigation");
	        return "espaceUtilisateur";	        
	    }
	 
	 @Transactional
	 @RequestMapping(value = {"/resultatSupprimerGuichet" }, method = RequestMethod.POST)
	    public String resultatSuppressionGuichet(Model model, @ModelAttribute("banque") Banque banque, HttpSession sessionUtilisateur, HttpSession session) {
			String resultat=null;
			try {
				identifiantSession=session.getAttribute("identifiantSession").toString().trim();
			}
			catch(Exception e) {
				resultat="pageErreur";
				return resultat;
			}
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionGuichet", "Gestion Guichet >");
			model.addAttribute("cheminSupprimerGuichet", "Supprimer un Guichet >");
			model.addAttribute("titre", "Gestion des Guichets");
			String codeGuichet=banque.getCodeGuichet().trim();
			session.setAttribute("codeGuichetCache", codeGuichet);
			Banque banqueRecherche=banqueRepository.findBanqueByCodeGuichet(codeGuichet);
			if( banqueRecherche == null) {				
				 return "redirect:/messageBanqueNonExistante";  
			}								
			Boolean estSupprimer=false;
			List<Banque> banques=new ArrayList<Banque>();
			banques=banqueRepository.findAllBanques(estSupprimer);
			model.addAttribute("banqueRecherche", banqueRecherche);			
			model.addAttribute("dialog_boxBanque", "dialog_boxBanque");	
			model.addAttribute("dialog_backgroundBanque", "dialog_backgroundBanque");	
			model.addAttribute("formulaireNumeroSupprimerBanque", "formulaireNumeroSupprimerBanque");
			model.addAttribute("actionTroisBouton", "actionTroisBouton");	
			model.addAttribute("banques", banques);
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("codeGuichet", codeGuichet);
			model.addAttribute("listeBanque", "listeBanque");
			model.addAttribute("gestionBanque", "gestionBanque");
			model.addAttribute("menuNavigation", "menuNavigation");		
	        return "espaceUtilisateur";	
	    }
	 
	 @Transactional
	 @RequestMapping(value = {"/succesSuppressionBanque" }, method = RequestMethod.POST)
	    public String succesSuppressionBanque(Model model, @ModelAttribute("banque") Banque banque, HttpSession sessionUtilisateur, HttpSession session) {
			String resultat=null;
			try {
				identifiantSession=session.getAttribute("identifiantSession").toString().trim();
			}
			catch(Exception e) {
				resultat="pageErreur";
				return resultat;
			}
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionGuichet", "Gestion Guichet >");
			model.addAttribute("cheminSupprimerGuichet", "Supprimer un Guichet >");
			model.addAttribute("titre", "Gestion des Guichets");
			String codeGuichetSuppression=session.getAttribute("codeGuichetCache").toString().trim();
			Banque banqueSuppression=banqueRepository.findBanqueByCodeGuichet(codeGuichetSuppression);
			banqueSuppression.setEstSupprimer(true);
			banqueRepository.save(banqueSuppression);
			Boolean estSupprimer=false;				
			model.addAttribute("codeGuichet", codeGuichetSuppression);
			model.addAttribute("resultatSuppressionGuichet", "resultatSuppressionGuichet");	
			model.addAttribute("actionTroisBouton", "actionTroisBouton");
			List<Banque> banques=new ArrayList<Banque>();
			banques=banqueRepository.findAllBanques(estSupprimer);	
			model.addAttribute("banques", banques);
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("listeBanque", "listeBanque");
			model.addAttribute("gestionBanque", "gestionBanque");
			model.addAttribute("menuNavigation", "menuNavigation");			
	        return "espaceUtilisateur";	
	    }
	
	 @Transactional
	 @RequestMapping(value = {"/resultatModifDonneeBanque" }, method = RequestMethod.POST)
	    public String resultatModifDonneeBanque(Model model, @ModelAttribute("banque") Banque banque, HttpSession sessionUtilisateur, HttpSession session) {
			String resultat=null;
			try {
				identifiantSession=session.getAttribute("identifiantSession").toString().trim();
			}
			catch(Exception e) {
				resultat="pageErreur";
				return resultat;
			}		
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionGuichet", "Gestion Guichet >");
			model.addAttribute("cheminSupprimerGuichet", "Supprimer un Guichet >");
			model.addAttribute("titre", "Gestion des Guichets");
			String codeGuichet=banque.getCodeGuichet().trim();
			session.removeAttribute("codeGuichetCache");
			session.setAttribute("codeGuichetCache",codeGuichet);						
			model.addAttribute("dialog_boxBanque", "dialog_boxBanque");	
			model.addAttribute("dialog_backgroundBanque", "dialog_backgroundBanque");				
			Boolean estSupprimer=false;	
			model.addAttribute("codeGuichet", codeGuichet);	
			model.addAttribute("actionTroisBouton", "actionTroisBouton");
			List<Banque> banques=new ArrayList<Banque>();
			banques=banqueRepository.findAllBanques(estSupprimer);	
			model.addAttribute("banques", banques);
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("listeBanque", "listeBanque");
			model.addAttribute("gestionBanque", "gestionBanque");
			model.addAttribute("menuNavigation", "menuNavigation");			
	        return "espaceUtilisateur";	
	    }
	 
	 @Transactional
	 @RequestMapping(value = {"/envoiDonneeCacheeBanque" }, method = RequestMethod.POST)
	    public String envoiDonneeCacheeBanque(Model model, @ModelAttribute("banque") Banque banque, HttpSession sessionUtilisateur, HttpSession session) {
			String resultat=null;
			try {
				identifiantSession=session.getAttribute("identifiantSession").toString().trim();
			}
			catch(Exception e) {
				resultat="pageErreur";
				return resultat;
			}	
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionGuichet", "Gestion Guichet >");
			model.addAttribute("cheminModifierGuichet", "Modifier un Guichet >");
			model.addAttribute("titre", "Gestion des Guichets");
			session.removeAttribute("codeGuichetCache");
			String codeGuichet=banque.getCodeGuichet().trim();
			Banque banqueRecherche=banqueRepository.findBanqueByCodeGuichet(codeGuichet);
			Boolean estSupprimer=false;
			List<Banque> banques=new ArrayList<Banque>();
			banques=banqueRepository.findAllBanques(estSupprimer);
			model.addAttribute("banques", banques);
			session.setAttribute("codeGuichetCache", codeGuichet);	
			model.addAttribute("formulaireGestionModifBanque", "formulaireGestionModifBanque");
			model.addAttribute("actionTroisBouton", "actionTroisBouton");	
			model.addAttribute("banqueRecherche", banqueRecherche);
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("listeBanque", "listeBanque");
			model.addAttribute("gestionBanque", "gestionBanque");
			model.addAttribute("menuNavigation", "menuNavigation");			
	        return "espaceUtilisateur";	
	    }
	 
	 
	 
	 
	 

}
