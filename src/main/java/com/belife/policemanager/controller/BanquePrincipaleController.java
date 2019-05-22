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

import com.belife.policemanager.model.entity.Agence;
import com.belife.policemanager.model.entity.Banque;
import com.belife.policemanager.model.repository.AgenceBanqueRepository;
import com.belife.policemanager.model.repository.BanqueRepository;
import com.belife.policemanager.model.repository.RolesRepository;
import com.belife.policemanager.model.repository.SocieteRepository;
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
	 SocieteRepository societeRepository;
	 
	 @Autowired
	 AgenceBanqueRepository agenceRepositoryRepository;
	 
	 String identifiantSession=null;
	 
	 
	 @Transactional
	 @RequestMapping(value = {"/gestionBanquePrincipale" }, method = RequestMethod.GET)
	    public String gestionBanquePrincipale(Model model,HttpSession session, @PageableDefault(size = 50) Pageable pageable, HttpServletRequest request) { 		 
		 String resultat=null;
			try {
				identifiantSession=session.getAttribute("identifiantSession").toString().trim();
			}
			catch(Exception e) {
				resultat="pageErreur";
				return resultat;
			}
			int page = 0;
			int size = 50;			 
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
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionBanque", "Gestion Banque >");
			model.addAttribute("titre", "Gestion des Banques");		
			Page<Banque> banquesPage =banqueRepository.findAllBanquePage(pageable);
			model.addAttribute("banquePrincipales", banquesPage);
			model.addAttribute("listeBanquePrincipale", "listeBanquePrincipale");
			model.addAttribute("gestionBanquePrincipale", "gestionBanquePrincipale");
			model.addAttribute("menuNavigation", "menuNavigation");
	        return "espaceUtilisateur";			
	    }
	 
	 
	 @Transactional
	 @RequestMapping(value = {"/ajoutBanquePrincipale" }, method = RequestMethod.GET)
	    public String ajoutBanquePrincipale(Model model, HttpSession session, @PageableDefault(size = 50) Pageable pageable, HttpServletRequest request) { 
		 
		 String resultat=null;
			try {
				identifiantSession=session.getAttribute("identifiantSession").toString().trim();
			}
			catch(Exception e) {
				resultat="pageErreur";
				return resultat;
			}
			int page = 0;
			int size = 50;			 
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
			
			
			String status="A";
			List <String> nomBanques=societeRepository.findAllNomSociete(status);
			
			List <String> codeBanqueExistant=banqueRepository.findAllCodeBanque();
			
			
			
			
			List<String> codeAgenceBanque=agenceRepositoryRepository.findAllDistinctCodeAgenceBanque();
			
//			 int k = 0;
//			 
//		        label1: while (k < 5) {
//		 
//		            System.out.println("----------------------\n");
//		            System.out.println("i = " + k);
//		            i++;
//		 
//		            label2: for (int j = 0; j < 3; j++) {
//		 
//		                System.out.println("  --> " + j);
//		                if (j > 0) {
//		                    // Quittez la boucle avec label1.
//		                    break label1;
//		                }
//		            }
//		 
//		        }
			 
			 
			 int k = 0;
			 
		         while (k < codeAgenceBanque.size()) {
		        	 String code=codeAgenceBanque.get(k);
//		            System.out.println("----------------------\n");
//		            System.out.println(" valeur i = " + k);
//		           
		 
		             for (int j=0; j<codeAgenceBanque.get(k).length();j++) {
		 
//		                System.out.println(" valeur j  --> " + j);
//		                if (j > 4) {
//		                    // Quittez la boucle avec label1.
//		                    break ;
//		                }
//		                
		                
		                char c=code.charAt(j);
		                Integer entier=Integer.valueOf(c);
		                
		                if(entier!=0) {
//							nombreZero++;
//							estTrouver=true;
		               System.out.println(" Nombre :"+ entier);
		                	String chaineExtraite=null;
							 chaineExtraite=code.substring(0,j);
							codeAgenceBanque.add(chaineExtraite);
							break ;
//						}
		            }
		             
		        }
		             k++;
		       }
		             System.out.println(" Chaine extraite : " + codeAgenceBanque);
			 
			
//			for(int i=0; i<codeAgenceBanque.size();i++) {
//				String code=codeAgenceBanque.get(i);
//				Integer nombreZero=0;
//				Boolean estTrouver=false;
//				for(int j=0; j<codeAgenceBanque.get(i).length();j++) {
//					char c=code.charAt(j);
//					
//					if(c!='0') {
//						nombreZero++;
//						estTrouver=true;
//						String chaineExtraite=code.substring(j+1);
//						codeAgenceBanque.add(chaineExtraite);
//						
//					}
//					if(nombreZero==0) {
//						
//					}
//					
//				}
//				System.out.print(" Code Banque : "+codeAgenceBanque);
//				System.out.println("Ok");
//			}
			
			System.out.println(" Code Banque : "+codeAgenceBanque);
			
			List<String> codeAgenceBanqueSansDoublon=new ArrayList<String>();
			Integer nombreTrouver=0;
			
			for(int i=0;i<codeAgenceBanque.size();i++) {
				nombreTrouver=0;
				for(int j=i+1; j<codeAgenceBanque.size();j++) {
					if(  (codeAgenceBanque.get(i)).equals(codeAgenceBanque.get(j))  ) {
						nombreTrouver=nombreTrouver+1;
						
					}
				}
				if(i==codeAgenceBanque.size()) {
					nombreTrouver=0;
					for(int j=i+1; j<codeAgenceBanque.size();j++) {
						if(  (codeAgenceBanque.get(i)).equals(codeAgenceBanque.get(j))  ) {
							nombreTrouver=nombreTrouver+1;
							
						}
					}
					
				}
				
				if(nombreTrouver==0) {					
					codeAgenceBanqueSansDoublon.add(codeAgenceBanque.get(i));	
					
				}
			}
			
			List<String> codeBanqueAfficher=new ArrayList<String>();
			
			if(!codeBanqueExistant.isEmpty()) {
				
				for(String codeB:codeAgenceBanqueSansDoublon) { 
					nombreTrouver=0;
					for( String codeBE : codeBanqueExistant) {
						
						if(codeBE.equals(codeB)) {
							nombreTrouver=nombreTrouver+1;										
						}	
											
					}
					
					if(nombreTrouver==0) {
						codeBanqueAfficher.add(codeB);
					}
					
				   }
					model.addAttribute("codeBanques",  codeBanqueAfficher);
				}
			
			if(codeBanqueExistant.isEmpty()) {
				model.addAttribute("codeBanques",codeAgenceBanqueSansDoublon);
			}
					
			Page<Banque> banquesPage =banqueRepository.findAllBanquePage(pageable);
							
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionBanque", "Gestion Banque >");
			model.addAttribute("cheminAjouterBanque", "Ajout d'une Banque >");
			model.addAttribute("titre", "Gestion des Banques");
			model.addAttribute("banquePrincipales", banquesPage);
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("formulaireGestionBanquePrincipale", "formulaireGestionBanquePrincipale");
			model.addAttribute("listeBanquePrincipale", "listeBanquePrincipale");
			model.addAttribute("gestionBanquePrincipale", "gestionBanquePrincipale");
			model.addAttribute("menuNavigation", "menuNavigation");
	        return "espaceUtilisateur";			
	    }
	 
	 
	 @Transactional
//	 @RequestMapping(value = {"/resultatAjoutBanquePrincipale"} , method = RequestMethod.POST)
	@RequestMapping(value = {"/resultatAjoutBanquePrincipale" }, method = RequestMethod.POST)	         
	    public String resultatAjoutBanquePrincipale(Model model, @ModelAttribute("banquePrincipale") Banque banque , HttpSession session, @PageableDefault(size = 50) Pageable pageable, HttpServletRequest request) { 		 
		 String resultat=null;
			try {
				identifiantSession=session.getAttribute("identifiantSession").toString().trim();
			}
			catch(Exception e) {
				resultat="pageErreur";
				return resultat;
			}
			int page = 0;
			int size = 50;			 
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
			
			String nomBanque=banque.getNomBanque().trim();
			String codeBanque=banque.getCodeBanque().trim();
			String status="A";
			
			List <String> codeBanqueExistant=banqueRepository.findAllCodeBanque();
			
			List<String> codeAgenceBanque=agenceRepositoryRepository.findAllDistinctCodeAgenceBanque();
			
			List<String> codeAgenceBanqueSansDoublon=new ArrayList<String>();
			Integer nombreTrouver=0;
			
			for(int i=0;i<codeAgenceBanque.size();i++) {
				nombreTrouver=0;
				for(int j=i+1; j<codeAgenceBanque.size();j++) {
					if(  (codeAgenceBanque.get(i)).equals(codeAgenceBanque.get(j))  ) {
						nombreTrouver=nombreTrouver+1;
						
					}
				}
				if(i==codeAgenceBanque.size()) {
					nombreTrouver=0;
					for(int j=i+1; j<codeAgenceBanque.size();j++) {
						if(  (codeAgenceBanque.get(i)).equals(codeAgenceBanque.get(j))  ) {
							nombreTrouver=nombreTrouver+1;
							
						}
					}
					
				}
				
				if(nombreTrouver==0) {
					
					codeAgenceBanqueSansDoublon.add(codeAgenceBanque.get(i));	
					
				}
			}
			
			List<String> codeBanqueAfficher=new ArrayList<String>();
			
			if(!codeBanqueExistant.isEmpty()) {
				
				for(String codeB:codeAgenceBanqueSansDoublon) { 
					nombreTrouver=0;
					for( String codeBE : codeBanqueExistant) {
						
						if(codeBE.equals(codeB)) {
							nombreTrouver=nombreTrouver+1;										
						}	
											
					}
					
					if(nombreTrouver==0) {
						codeBanqueAfficher.add(codeB);
					}
					
				   }
					model.addAttribute("codeBanques",  codeBanqueAfficher);
				}
			
			if(codeBanqueExistant.isEmpty()) {
				model.addAttribute("codeBanques",codeAgenceBanqueSansDoublon);
			}
								
			Banque banqueCodeSave=null;
			Banque banqueNomSave=null;
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("gestionBanquePrincipale", "gestionBanquePrincipale");
			model.addAttribute("menuNavigation", "menuNavigation");
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionBanque", "Gestion Banque >");
			model.addAttribute("cheminAjouterBanque", "Ajout d'une Banque >");
			model.addAttribute("titre", "Gestion des Banques");
			model.addAttribute("nomBanque", nomBanque);
			model.addAttribute("codeBanque", codeBanque);
			if( nomBanque != null && nomBanque.length() > 0 && nomBanque.length()<=40 && codeBanque != null && codeBanque.length() > 0 && codeBanque.length()<=20 ) {						
				
				banqueCodeSave=banqueRepository.findBanquePrincipaleByCodeBanque(codeBanque);
				
				banqueNomSave=banqueRepository.findBanquePrincipaleByNomBanque(nomBanque);
				
					if(banqueCodeSave == null && banqueNomSave == null) {	
						
						banque.setNomBanque(nomBanque);
						banque.setCodeBanque(codeBanque);
						banque.setStatus(status);
						
						model.addAttribute("listeBanquePrincipale", "listeBanquePrincipale");
						model.addAttribute("gestionBanquePrincipale", "gestionBanquePrincipale");
						Banque bp=banqueRepository.save(banque);
						String codeBanqueReturn=bp.getCodeBanque();
						model.addAttribute("ajoutSuccesBanquePrincipale", "Une Banque de code  est ajoutée avec succès");	
						model.addAttribute("codeBanqueReturn", codeBanqueReturn);
						Page<Banque> banquesPage =banqueRepository.findAllBanquePage(pageable);
						model.addAttribute("banquePrincipales", banquesPage);		
						
						return "espaceUtilisateur";
					}	
					
										
					if(banqueNomSave!=null)
						model.addAttribute("nomBanquePrincipaleErreur", " Nom Banque déjà existant");
			}	
			
			model.addAttribute("formErreurBanquePrincipale", "formErreur");
			if(nomBanque==null || nomBanque.length()==0 || nomBanque.length()>40) {
				model.addAttribute("nomBanquePrincipaleErreur", "Nom de la Banque invalide");
			}
			
			Page<Banque> banquesPage =banqueRepository.findAllBanquePage(pageable);
			model.addAttribute("banquePrincipales", banquesPage);
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
	    public String modifierBanquePrincipale(Model model, HttpSession session,@PageableDefault(size = 50) Pageable pageable, HttpServletRequest request) { 
			String resultat=null;
			try {
				identifiantSession=session.getAttribute("identifiantSession").toString().trim();
			}
			catch(Exception e) {
				resultat="pageErreur";
				return resultat;
			}	
			
			List <String> codeBanqueExistant=banqueRepository.findAllCodeBanque();
			
			List<String> codeAgenceBanque=agenceRepositoryRepository.findAllDistinctCodeAgenceBanque();
			
			List<String> codeAgenceBanqueSansDoublon=new ArrayList<String>();
			Integer nombreTrouver=0;
			
			for(int i=0;i<codeAgenceBanque.size();i++) {
				nombreTrouver=0;
				for(int j=i+1; j<codeAgenceBanque.size();j++) {
					if(  (codeAgenceBanque.get(i)).equals(codeAgenceBanque.get(j))  ) {
						nombreTrouver=nombreTrouver+1;
						
					}
				}
				if(i==codeAgenceBanque.size()) {
					nombreTrouver=0;
					for(int j=i+1; j<codeAgenceBanque.size();j++) {
						if(  (codeAgenceBanque.get(i)).equals(codeAgenceBanque.get(j))  ) {
							nombreTrouver=nombreTrouver+1;
							
						}
					}
					
				}
				
				if(nombreTrouver==0) {
					
					codeAgenceBanqueSansDoublon.add(codeAgenceBanque.get(i));	
					
				}
			}
			
			List<String> codeBanqueAfficher=new ArrayList<String>();
			
			if(!codeBanqueExistant.isEmpty()) {
				
				for(String codeB:codeAgenceBanqueSansDoublon) { 
					nombreTrouver=0;
					for( String codeBE : codeBanqueExistant) {
						
						if(codeBE.equals(codeB)) {
							nombreTrouver=nombreTrouver+1;										
						}	
											
					}
					
					if(nombreTrouver==0) {
						codeBanqueAfficher.add(codeB);
					}
					
				   }
					model.addAttribute("codeBanques",  codeBanqueAfficher);
				}
			
			if(codeBanqueExistant.isEmpty()) {
				model.addAttribute("codeBanques",codeAgenceBanqueSansDoublon);
			}
						
			
//			gestion Menu 
			model.addAttribute("gestionMenuUtilisateur", "gestionMenuUtilisateur");
			model.addAttribute("gestionMenuBanque", "gestionMenuBanque");
			model.addAttribute("gestionMenuGuichet", "gestionMenuGuichet");
			model.addAttribute("gestionMenuAgence", "gestionMenuAgence");
			model.addAttribute("gestionMenuAgent", "gestionMenuAgent");
			model.addAttribute("gestionMenuSociete", "gestionMenuSociete");
			model.addAttribute("accueilDeux", "accueilDeux");
			model.addAttribute("gestionMenuAgenceBanque", "gestionMenuAgenceBanque");
			int page = 0;
			int size = 50;			 
			pageable = PageRequest.of(page, size);
			Page<Banque> banquesPage =banqueRepository.findAllBanquePage(pageable);
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionBanque", "Gestion Banque >");
			model.addAttribute("cheminModifierBanque", "Modifier une Banque >");
			model.addAttribute("titre", "Gestion des Banques");
			model.addAttribute("banquePrincipales", banquesPage);
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
	    public String formulaireNumeroModifBanquePrincipale(Model model, @ModelAttribute("banque") Banque banque, HttpSession session, @PageableDefault(size = 50) Pageable pageable, HttpServletRequest request) {
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
			model.addAttribute("actionTroisBouton", "actionTroisBouton");
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("listeBanquePrincipale", "listeBanquePrincipale");
			model.addAttribute("gestionBanquePrincipale", "gestionBanquePrincipale");
			model.addAttribute("gestionMenuAgenceBanque", "gestionMenuAgenceBanque");
			
			String codeBanque=banque.getCodeBanque().trim();
			Banque banqueRecherche=banqueRepository.findBanquePrincipaleByCodeBanque(codeBanque);			
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionBanque", "Gestion Banque >");
			model.addAttribute("cheminModifierBanque", "Modifier Banque >");
			model.addAttribute("titre", "Gestion des Banques");
			session.setAttribute("codeBanqueCache", codeBanque);	
			if( banqueRecherche == null) {
				 model.addAttribute("formErreurBanquePrincipale", "formErreur");
				 model.addAttribute("formulaireNumeroModifBanquePrincipale", "formulaireNumeroModifBanquePrincipale");
				 model.addAttribute("codeBanquePrincipaleErreur"," La Banque de code "+ codeBanque +" introuvable dans le système ." );	
				 model.addAttribute("codeBanque", codeBanque);
				 Page<Banque> banquesPage =banqueRepository.findAllBanquePage(pageable);
				 model.addAttribute("banquePrincipales", banquesPage);
				 return "espaceUtilisateur";  
			}	

			int page = 0;
			int size = 50;			 
			pageable = PageRequest.of(page, size);
			Page<Banque> banquesPage =banqueRepository.findAllBanquePage(pageable);
			model.addAttribute("banquePrincipales", banquesPage);
			session.setAttribute("codeBanqueCache", codeBanque);	
			model.addAttribute("formulaireGestionModifBanquePrincipale", "formulaireGestionModifBanquePrincipale");		
			model.addAttribute("nomBanqueRecherche", banqueRecherche.getNomBanque());	
			model.addAttribute("codeBanqueRecherche", banqueRecherche.getCodeBanque());	
			model.addAttribute("menuNavigation", "menuNavigation");			
	        return "espaceUtilisateur";	
	    }

	 	
	 	/// Resultat de la modification des données d'un formulaire
	 	@Transactional
		@RequestMapping(value = {"/resultatModifBanquePrincipale" }, method = RequestMethod.POST)
	    public String resultatModifBanquePrincipale(Model model, @ModelAttribute("banque") Banque banque, HttpSession session,@PageableDefault(size = 50) Pageable pageable, HttpServletRequest request) {
			String resultat=null;
			try {
				identifiantSession=session.getAttribute("identifiantSession").toString().trim();
			}
			catch(Exception e) {
				resultat="pageErreur";
				return resultat;
			}
			List <String> codeBanqueExistant=banqueRepository.findAllCodeBanque();
			
			List<String> codeAgenceBanque=agenceRepositoryRepository.findAllDistinctCodeAgenceBanque();
			
			List<String> codeAgenceBanqueSansDoublon=new ArrayList<String>();
			Integer nombreTrouver=0;
			
			for(int i=0;i<codeAgenceBanque.size();i++) {
				nombreTrouver=0;
				for(int j=i+1; j<codeAgenceBanque.size();j++) {
					if(  (codeAgenceBanque.get(i)).equals(codeAgenceBanque.get(j))  ) {
						nombreTrouver=nombreTrouver+1;
						
					}
				}
				if(i==codeAgenceBanque.size()) {
					nombreTrouver=0;
					for(int j=i+1; j<codeAgenceBanque.size();j++) {
						if(  (codeAgenceBanque.get(i)).equals(codeAgenceBanque.get(j))  ) {
							nombreTrouver=nombreTrouver+1;
							
						}
					}
					
				}
				
				if(nombreTrouver==0) {
					
					codeAgenceBanqueSansDoublon.add(codeAgenceBanque.get(i));	
					
				}
			}
			
			List<String> codeBanqueAfficher=new ArrayList<String>();
			
			if(!codeBanqueExistant.isEmpty()) {
				
				for(String codeB:codeAgenceBanqueSansDoublon) { 
					nombreTrouver=0;
					for( String codeBE : codeBanqueExistant) {
						
						if(codeBE.equals(codeB)) {
							nombreTrouver=nombreTrouver+1;										
						}	
											
					}
					
					if(nombreTrouver==0) {
						codeBanqueAfficher.add(codeB);
					}
					
				   }
					model.addAttribute("codeBanques",  codeBanqueAfficher);
				}
			
			if(codeBanqueExistant.isEmpty()) {
				model.addAttribute("codeBanques",codeAgenceBanqueSansDoublon);
			}
			int page = 0;
			int size = 50;			 
			pageable = PageRequest.of(page, size);
			Page<Banque> banquesPage =banqueRepository.findAllBanquePage(pageable);
//			gestion Menu 
			model.addAttribute("gestionMenuUtilisateur", "gestionMenuUtilisateur");
			model.addAttribute("gestionMenuBanque", "gestionMenuBanque");
			model.addAttribute("gestionMenuGuichet", "gestionMenuGuichet");
			model.addAttribute("gestionMenuAgence", "gestionMenuAgence");
			model.addAttribute("gestionMenuAgent", "gestionMenuAgent");
			model.addAttribute("gestionMenuSociete", "gestionMenuSociete");
			model.addAttribute("accueilDeux", "accueilDeux");
			model.addAttribute("gestionMenuAgenceBanque", "gestionMenuAgenceBanque");
			String nomBanque=banque.getNomBanque().trim();
			String codeBanque=banque.getCodeBanque().trim();				
			String codeBanqueCache=session.getAttribute("codeBanqueCache").toString().trim();	
			
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionBanque", "Gestion Banque >");
			model.addAttribute("cheminModifierBanque", "Modifier une Banque >");
			model.addAttribute("titre", "Gestion des Banques");
			model.addAttribute("nomBanque", nomBanque);
			model.addAttribute("codeBanque", codeBanque);
			Banque banqueRecherche=banqueRepository.findBanquePrincipaleByCodeBanque(codeBanqueCache);
			if( nomBanque != null && nomBanque.length() > 0 && nomBanque.length()<=40 && codeBanque != null && codeBanque.length() > 0 && codeBanque.length()<=20) {						
				
				Banque banquePrincipaleRecherche=banqueRepository.findBanquePrincipaleByCodeBanque(codeBanqueCache);
				Integer idBanque=banquePrincipaleRecherche.getIdBanque();
				
				Banque banquePrincipaleNom=banqueRepository.findBanquePrincipaleByNom(nomBanque,idBanque);
				Banque banquePrincipaleCode=banqueRepository.findBanquePrincipaleByCode(codeBanque,idBanque);
				
				if(banquePrincipaleNom==null && banquePrincipaleCode==null) {
				
					banquePrincipaleRecherche.setCodeBanque(codeBanque);
					banquePrincipaleRecherche.setNomBanque(nomBanque);
					Banque bp=banqueRepository.save(banquePrincipaleRecherche);				
					model.addAttribute("resultatModifBanquePrincipale","resultatModifBanquePrincipale");
					model.addAttribute("codeBanqueRecherche", bp.getCodeBanque());
					model.addAttribute("identifiantSession", identifiantSession);
					model.addAttribute("actionTroisBouton", "actionTroisBouton");	
					model.addAttribute("listeBanquePrincipale", "listeBanquePrincipale");
					model.addAttribute("gestionBanquePrincipale", "gestionBanquePrincipale");
					model.addAttribute("menuNavigation", "menuNavigation");	 		
					model.addAttribute("banquePrincipales", banquesPage);
					return "espaceUtilisateur";	
				}
				    if(banquePrincipaleCode!=null)
				    	model.addAttribute("codeBanquePrincipaleErreur", " Code Banque déjà existant");	
					
					if(banquePrincipaleNom!=null)
						model.addAttribute("nomBanquePrincipaleErreur", " Nom Banque déjà existant");
				
				}
			
				model.addAttribute("formErreurBanquePrincipale", "formErreur");				
				if(nomBanque==null || nomBanque.length()==0 || nomBanque.length()>40) {
					model.addAttribute("nomBanquePrincipaleErreur", "Nom de la Banque invalide");
				}
				
					
			model.addAttribute("nomBanqueRecherche", nomBanque);	
			model.addAttribute("codeBanqueRecherche", codeBanque);	
			model.addAttribute("banquePrincipales", banquesPage);	
			model.addAttribute("listeBanquePrincipale", "listeBanquePrincipale");
			model.addAttribute("actionTroisBouton", "actionTroisBouton");	
			model.addAttribute("formulaireGestionModifBanquePrincipale", "formulaireGestionModifBanquePrincipale");
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("gestionBanquePrincipale", "gestionBanquePrincipale");
			model.addAttribute("menuNavigation", "menuNavigation");			
	        return "espaceUtilisateur";	
	    }
	 
	 
////	  Action Recherche Banque
	 @Transactional
	 @RequestMapping(value = {"/rechercheBanquePrincipale" }, method = RequestMethod.GET)
	    public String rechercherBanquePrincipale(Model model, HttpSession session,@PageableDefault(size = 50) Pageable pageable, HttpServletRequest request) { 
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
			model.addAttribute("gestionMenuAgenceBanque", "gestionMenuAgenceBanque");
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionBanque", "Gestion Banque >");
			model.addAttribute("cheminRechercherBanque", "Rechercher une Banque >");
			model.addAttribute("titre", "Gestion des Banques");

			model.addAttribute("formulaireNumeroRechercheBanquePrincipale", "formulaireNumeroRechercheBanquePrincipale");
			model.addAttribute("actionTroisBouton", "actionTroisBouton");	
			model.addAttribute("identifiantSession", identifiantSession);  
			model.addAttribute("gestionBanquePrincipale", "gestionBanquePrincipale");
			model.addAttribute("menuNavigation", "menuNavigation");
	        return "espaceUtilisateur";		        
	    }
	 
//	 @Transactional
	 @RequestMapping(value = {"/resultatRechercheBanquePrincipale" }, method = RequestMethod.POST)
	    public String resultatRechercheBanquePrincipale(Model model, @ModelAttribute("banque") Banque banque, HttpSession session) {
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
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("gestionBanquePrincipale", "gestionBanquePrincipale");	
			model.addAttribute("menuNavigation", "menuNavigation");	
			model.addAttribute("gestionMenuAgenceBanque", "gestionMenuAgenceBanque");
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionBanque", "Gestion Banque >");
			model.addAttribute("cheminRechercherBanque", "Rechercher une Banque >");
			model.addAttribute("titre", "Gestion des Banques");
			String codeBanque=banque.getCodeBanque().trim();
			Banque banqueRecherche=banqueRepository.findBanquePrincipaleByCodeBanque(codeBanque);
			model.addAttribute("codeBanque", codeBanque);
			
			if( banqueRecherche == null) {
				model.addAttribute("formErreurBanquePrincipale", "formErreur");					
				model.addAttribute("codeBanquePrincipaleErreur", " La Banque de code "+codeBanque+" non introuvable dans le système. ");			
				model.addAttribute("formulaireNumeroRechercheBanquePrincipale", "formulaireNumeroRechercheBanquePrincipale");	
				model.addAttribute("actionTroisBouton", "actionTroisBouton");
				return "espaceUtilisateur";	
			}
			
			model.addAttribute("banqueRecherche", banqueRecherche);			
			model.addAttribute("resultatRechercheBanquePrincipale", "resultatRechercheBanquePrincipale");	
			model.addAttribute("actionTroisBouton", "actionTroisBouton");		
	        return "espaceUtilisateur";	
	    }
	 
	 
////	 Action suppression Banque
	 @Transactional
	 	@RequestMapping(value = {"/supprimerBanquePrincipale" }, method = RequestMethod.GET)
	    public String supprimerBanquePrincipale(Model model, HttpSession session,@PageableDefault(size = 50) Pageable pageable, HttpServletRequest request) { 
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
			model.addAttribute("gestionMenuAgenceBanque", "gestionMenuAgenceBanque");
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionBanque", "Gestion Banque >");
			model.addAttribute("cheminSupprimerBanque", "Supprimer une Banque >");
			model.addAttribute("titre", "Gestion des Banques");
			
			int page = 0;
			int size = 50;			 
			pageable = PageRequest.of(page, size);
			Page<Banque> banquesPage =banqueRepository.findAllBanquePage(pageable);
			
			model.addAttribute("banquePrincipales", banquesPage);
			model.addAttribute("formulaireNumeroSupprimerBanquePrincipale", "formulaireNumeroSupprimerBanquePrincipale");
			model.addAttribute("actionTroisBouton", "actionTroisBouton");	
			model.addAttribute("identifiantSession", identifiantSession);  
			model.addAttribute("listeBanquePrincipale", "listeBanquePrincipale");
			model.addAttribute("gestionBanquePrincipale", "gestionBanquePrincipale");
			model.addAttribute("menuNavigation", "menuNavigation");
	        return "espaceUtilisateur";	        
	    }
	 
	 
//	 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//	 @Transactional
	 @RequestMapping(value = {"/resultatSupprimerBanquePrincipale" }, method = RequestMethod.POST)
	    public String resultatSupprimerBanquePrincipale(Model model, @ModelAttribute("banque") Banque banque, HttpSession sessionUtilisateur, HttpSession session, @PageableDefault(size = 50) Pageable pageable, HttpServletRequest request) {
			String resultat=null;
			try {
				identifiantSession=session.getAttribute("identifiantSession").toString().trim();
			}
			catch(Exception e) {
				resultat="pageErreur";
				return resultat;
			}
			int page = 0;
			int size = 50;			 
			pageable = PageRequest.of(page, size);
			Page<Banque> banquesPage =banqueRepository.findAllBanquePage(pageable);
			
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
			model.addAttribute("cheminGestionBanque", "Gestion Banque >");
			model.addAttribute("cheminSupprimerBanque", "Supprimer une Banque >");
			model.addAttribute("titre", "Gestion des Banques");
			model.addAttribute("gestionBanquePrincipale", "gestionBanquePrincipale");
			model.addAttribute("menuNavigation", "menuNavigation");
			model.addAttribute("actionTroisBouton", "actionTroisBouton");
			model.addAttribute("listeBanquePrincipale", "listeBanquePrincipale");
			
			String codeBanque=banque.getCodeBanque().trim();
			session.setAttribute("codeBanquePrincipaleCache", codeBanque);
			Banque banqueRecherche=banqueRepository.findBanquePrincipaleByCodeBanque(codeBanque);
			model.addAttribute("banquePrincipales",  banquesPage);
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("codeBanque", codeBanque);
			
			if( banqueRecherche == null) {		
							
				model.addAttribute("formErreurBanquePrincipale", "formErreur");					
				model.addAttribute("codeBanquePrincipaleErreur", " La Banque de code "+codeBanque+" non introuvable dans le système. ");			
				model.addAttribute("formulaireNumeroSupprimerBanquePrincipale", "formulaireNumeroSupprimerBanquePrincipale");
				model.addAttribute("actionTroisBouton", "actionTroisBouton");
				return "espaceUtilisateur";	
				 
			}								
			
			model.addAttribute("banquePrincipaleRecherche", banqueRecherche);			
			model.addAttribute("dialog_boxBanquePrincipale", "dialog_boxBanquePrincipale");	
			model.addAttribute("dialog_backgroundBanquePrincipale", "dialog_backgroundBanquePrincipale");	
			model.addAttribute("formulaireNumeroSupprimerBanquePrincipale", "formulaireNumeroSupprimerBanquePrincipale");
							
	        return "espaceUtilisateur";	
	    }
	 
//	 @Transactional
	 @RequestMapping(value = {"/succesSuppressionBanquePrincipale" }, method = RequestMethod.POST)
	    public String succesSuppressionBanquePrincipale(Model model, @ModelAttribute("banque") Banque banque, HttpSession session, @PageableDefault(size = 50) Pageable pageable, HttpServletRequest request) {
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
			model.addAttribute("gestionMenuAgenceBanque", "gestionMenuAgenceBanque");
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionBanque", "Gestion Banque >");
			model.addAttribute("cheminSupprimerBanque", "Supprimer une Banque >");
			model.addAttribute("titre", "Gestion des Banques");
			String codeBanque=banque.getCodeBanque().trim();
			String codeBanquePrincipaleSuppression=session.getAttribute("codeBanquePrincipaleCache").toString().trim();
			Banque banqueSuppression=banqueRepository.findBanquePrincipaleByCodeBanque(codeBanquePrincipaleSuppression);
			String status="Inactif";
			banqueSuppression.setStatus(status);
			banque=banqueRepository.save(banqueSuppression);			
			model.addAttribute("codeBanque", banque.getCodeBanque() );
			model.addAttribute("resultatSuppressionBanquePrincipale", "resultatSuppressionBanquePrincipale");	
			model.addAttribute("actionTroisBouton", "actionTroisBouton");
			List<Banque> banques=new ArrayList<Banque>();
			
			int page = 0;
			int size = 50;			 
			pageable = PageRequest.of(page, size);
			Page<Banque> banquesPage =banqueRepository.findAllBanquePage(pageable);
			model.addAttribute("banquePrincipales", banquesPage);
			
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("listeBanquePrincipale", "listeBanquePrincipale");
			model.addAttribute("gestionBanquePrincipale", "gestionBanquePrincipale");
			model.addAttribute("menuNavigation", "menuNavigation");			
	        return "espaceUtilisateur";	
	    }
	
//	 @Transactional
	 @RequestMapping(value = {"/resultatModifDonneeBanquePrincipale" }, method = RequestMethod.POST)
	    public String resultatModifDonneeBanquePrincipale(Model model, @ModelAttribute("banque") Banque banque, HttpSession session, @PageableDefault(size = 50) Pageable pageable, HttpServletRequest request) {
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
			model.addAttribute("gestionMenuAgenceBanque", "gestionMenuAgenceBanque");
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionBanque", "Gestion Banque >");
			model.addAttribute("cheminSupprimerBanque", "Supprimer une Banque >");
			String codeBanque=banque.getCodeBanque().trim();
			session.removeAttribute("codeBanquePrincipaleCache");
			session.setAttribute("codeBanquePrincipaleCache",codeBanque);						
			model.addAttribute("dialog_boxBanquePrincipale", "dialog_boxBanquePrincipale");	
			model.addAttribute("dialog_backgroundBanquePrincipale", "dialog_backgroundBanquePrincipale");				
			Boolean estSupprimer=false;	
			model.addAttribute("codeBanque", codeBanque);	
			model.addAttribute("actionTroisBouton", "actionTroisBouton");
			
			int page = 0;
			int size = 50;			 
			pageable = PageRequest.of(page, size);
			Page<Banque> banquesPage =banqueRepository.findAllBanquePage(pageable);
			model.addAttribute("banquePrincipales", banquesPage);
			
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("listeBanquePrincipale", "listeBanquePrincipale");
			model.addAttribute("gestionBanquePrincipale", "gestionBanquePrincipale");
			model.addAttribute("menuNavigation", "menuNavigation");			
	        return "espaceUtilisateur";	
	    }
	 
        //	@Transactional
	 	@RequestMapping(value = {"/envoiDonneeCacheeBanquePrincipale" }, method = RequestMethod.POST)
	    public String envoiDonneeCacheeBanquePrincipale(Model model, @ModelAttribute("banque") Banque banque, HttpSession session, @PageableDefault(size = 50) Pageable pageable, HttpServletRequest request) {
			String resultat=null;
			try {
				identifiantSession=session.getAttribute("identifiantSession").toString().trim();
			}
			catch(Exception e) {
				resultat="pageErreur";
				return resultat;
			}
			List <String> codeBanqueExistant=banqueRepository.findAllCodeBanque();
			
			List<String> codeAgenceBanque=agenceRepositoryRepository.findAllDistinctCodeAgenceBanque();
			
			List<String> codeAgenceBanqueSansDoublon=new ArrayList<String>();
			Integer nombreTrouver=0;
			
			for(int i=0;i<codeAgenceBanque.size();i++) {
				nombreTrouver=0;
				for(int j=i+1; j<codeAgenceBanque.size();j++) {
					if(  (codeAgenceBanque.get(i)).equals(codeAgenceBanque.get(j))  ) {
						nombreTrouver=nombreTrouver+1;
						
					}
				}
				if(i==codeAgenceBanque.size()) {
					nombreTrouver=0;
					for(int j=i+1; j<codeAgenceBanque.size();j++) {
						if(  (codeAgenceBanque.get(i)).equals(codeAgenceBanque.get(j))  ) {
							nombreTrouver=nombreTrouver+1;
							
						}
					}
					
				}
				
				if(nombreTrouver==0) {
					
					codeAgenceBanqueSansDoublon.add(codeAgenceBanque.get(i));	
					
				}
			}
			
			List<String> codeBanqueAfficher=new ArrayList<String>();
			
			if(!codeBanqueExistant.isEmpty()) {
				
				for(String codeB:codeAgenceBanqueSansDoublon) { 
					nombreTrouver=0;
					for( String codeBE : codeBanqueExistant) {
						
						if(codeBE.equals(codeB)) {
							nombreTrouver=nombreTrouver+1;										
						}	
											
					}
					
					if(nombreTrouver==0) {
						codeBanqueAfficher.add(codeB);
					}
					
				   }
					model.addAttribute("codeBanques",  codeBanqueAfficher);
				}
			
			if(codeBanqueExistant.isEmpty()) {
				model.addAttribute("codeBanques",codeAgenceBanqueSansDoublon);
			}
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
			model.addAttribute("cheminGestionBanque", "Gestion Banque >");
			model.addAttribute("cheminModifierBanque", "Modifier une Banque >");
			model.addAttribute("titre", "Gestion des Banques");
			session.removeAttribute("codeBanquePrincipaleCache");
			String codeBanque=banque.getCodeBanque().trim();
			Banque banqueRecherche=banqueRepository.findBanquePrincipaleByCodeBanque(codeBanque);
			
			int page = 0;
			int size = 50;			 
			pageable = PageRequest.of(page, size);
			Page<Banque> banquesPage =banqueRepository.findAllBanquePage(pageable);
			model.addAttribute("banquePrincipales", banquesPage);
					
			session.setAttribute("codeBanqueCache", codeBanque);
			session.setAttribute("codeBanque", codeBanque);	
			model.addAttribute("formulaireGestionModifBanquePrincipale", "formulaireGestionModifBanquePrincipale");
			model.addAttribute("actionTroisBouton", "actionTroisBouton");	
			model.addAttribute("codeBanqueRecherche", banqueRecherche.getCodeBanque());
			model.addAttribute("nomBanqueRecherche", banqueRecherche.getNomBanque());
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("listeBanquePrincipale", "listeBanquePrincipale");
			model.addAttribute("gestionBanquePrincipale", "gestionBanquePrincipale");
			model.addAttribute("menuNavigation", "menuNavigation");			
	        return "espaceUtilisateur";	
	    }
	 	
	 	 @Transactional
			@RequestMapping(value = "/listeBanquePrincipales")
		    public ModelAndView listeUtilisateursPageByPage(@RequestParam(name="page", defaultValue="0") int page,@RequestParam(name="size", defaultValue="100") int size,  @ModelAttribute("agence")  Agence agence, Model model, HttpSession session, HttpServletRequest request) {
		        ModelAndView modelAndView = new ModelAndView("espaceUtilisateur");
		       
		        PageRequest pageable = PageRequest.of(page-1, size);
		        Page<Banque> banquesPage =banqueRepository.findAllBanquePage(pageable);

				model.addAttribute("banquePrincipales", banquesPage);
						
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
				model.addAttribute("cheminGestionBanque", "Gestion Banque >");
				model.addAttribute("cheminModifierBanque", "Modifier une Banque >");
				model.addAttribute("listeBanquePrincipale", "listeBanquePrincipale");
				model.addAttribute("gestionBanquePrincipale", "gestionBanquePrincipale");
				model.addAttribute("menuNavigation", "menuNavigation");		
				model.addAttribute("titre", "Gestion des Banques");						
		        return modelAndView;
			}	
	 
}
	 

