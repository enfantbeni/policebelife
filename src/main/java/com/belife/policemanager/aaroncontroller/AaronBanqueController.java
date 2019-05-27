package com.belife.policemanager.aaroncontroller;

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

import com.belife.policemanager.model.aaronentity.AaronBanque;
import com.belife.policemanager.model.aaronrepository.AaronBanqueRepository;
import com.belife.policemanager.model.entity.Agence;
import com.belife.policemanager.model.entity.Banque;
import com.belife.policemanager.model.repository.AgenceBanqueRepository;
import com.belife.policemanager.model.repository.BanqueRepository;
import com.belife.policemanager.model.repository.RolesRepository;
import com.belife.policemanager.model.repository.SocieteRepository;
import com.belife.policemanager.model.repository.SourcePoliceRepository;
import com.belife.policemanager.model.repository.UtilisateurRepository;

@Controller
public class AaronBanqueController {

	@Autowired
    UtilisateurRepository utilisateurRepository; 
	 
	 @Autowired
    RolesRepository rolesRepository;
	 
	 @Autowired
    SourcePoliceRepository sourcePoliceRepository;
	 
	 @Autowired
    BanqueRepository banqueRepository;
	 
	 @Autowired
	 AaronBanqueRepository aaronBanqueRepository;
	 
	 @Autowired
	 SocieteRepository societeRepository;
	 
	 @Autowired
	 AgenceBanqueRepository agenceRepositoryRepository;
	 
	 String identifiantSession=null;
	 
	 
	 @Transactional
	 @RequestMapping(value = {"/aaronGestionBanquePrincipale" }, method = RequestMethod.GET)
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
			Page<AaronBanque> banquesPage =aaronBanqueRepository.findAllBanquePage(pageable);
			model.addAttribute("banquePrincipales", banquesPage);
			model.addAttribute("listeBanquePrincipale", "listeBanquePrincipale");
			model.addAttribute("gestionBanquePrincipale", "gestionBanquePrincipale");
			model.addAttribute("menuNavigation", "menuNavigation");
	        return "aaron/espaceUtilisateur";			
	    }
	 
	 
	 @Transactional
	 @RequestMapping(value = {"/aaronAjoutBanquePrincipale" }, method = RequestMethod.GET)
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

		                	String chaineExtraite=null;
							 chaineExtraite=code.substring(0,j);
							codeAgenceBanque.add(chaineExtraite);
							break ;
//						}
		            }
		             
		        }
		             k++;
		       }
			 
				
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
					
			Page<AaronBanque> banquesPage =aaronBanqueRepository.findAllBanquePage(pageable);
							
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
	        return "aaron/espaceUtilisateur";			
	    }
	 
	 
	 @Transactional
	@RequestMapping(value = {"/aaronResultatAjoutBanquePrincipale" }, method = RequestMethod.POST)	         
	    public String resultatAjoutBanquePrincipale(Model model, @ModelAttribute("aaronBanque") AaronBanque aaronBanque , HttpSession session, @PageableDefault(size = 50) Pageable pageable, HttpServletRequest request) { 		 
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
			
			String nomBanque=aaronBanque.getNomBanque().trim();
			String codeBanque=aaronBanque.getCodeBanque().trim();
			String status="A";
			
			List <String> codeBanqueExistant=aaronBanqueRepository.findAllCodeBanque();
			
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
								
			AaronBanque banqueCodeSave=null;
			AaronBanque banqueNomSave=null;
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
				
				banqueCodeSave=aaronBanqueRepository.findBanquePrincipaleByCodeBanque(codeBanque);
				
				banqueNomSave=aaronBanqueRepository.findBanquePrincipaleByNomBanque(nomBanque);
				
					if(banqueCodeSave == null && banqueNomSave == null) {	
						
						aaronBanque.setNomBanque(nomBanque);
						aaronBanque.setCodeBanque(codeBanque);
						aaronBanque.setStatus(status);
						
						model.addAttribute("listeBanquePrincipale", "listeBanquePrincipale");
						model.addAttribute("gestionBanquePrincipale", "gestionBanquePrincipale");
						AaronBanque bp=aaronBanqueRepository.save(aaronBanque);
						String codeBanqueReturn=bp.getCodeBanque();
						model.addAttribute("ajoutSuccesBanquePrincipale", "Une Banque de code  est ajoutée avec succès");	
						model.addAttribute("codeBanqueReturn", codeBanqueReturn);
						Page<AaronBanque> banquesPage =aaronBanqueRepository.findAllBanquePage(pageable);
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
			
			Page<AaronBanque> banquesPage =aaronBanqueRepository.findAllBanquePage(pageable);
			model.addAttribute("banquePrincipales", banquesPage);
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("resultatAjoutBanquePrincipale", "resultatAjoutBanquePrincipale");
			model.addAttribute("formulaireGestionBanquePrincipale", "formulaireGestionBanquePrincipale");
			model.addAttribute("listeBanquePrincipale", "listeBanquePrincipale");
			model.addAttribute("gestionBanquePrincipale", "gestionBanquePrincipale");
			model.addAttribute("menuNavigation", "menuNavigation");
	        return "aaron/espaceUtilisateur";			
	 }
	 
	 
	 	@Transactional
	 	@RequestMapping(value = {"/aaronModifierBanquePrincipale" }, method = RequestMethod.GET)
	    public String modifierBanquePrincipale(Model model, HttpSession session,@PageableDefault(size = 50) Pageable pageable, HttpServletRequest request) { 
			String resultat=null;
			try {
				identifiantSession=session.getAttribute("identifiantSession").toString().trim();
			}
			catch(Exception e) {
				resultat="pageErreur";
				return resultat;
			}	
			
			List <String> codeBanqueExistant=aaronBanqueRepository.findAllCodeBanque();
			
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
			Page<AaronBanque> banquesPage =aaronBanqueRepository.findAllBanquePage(pageable);
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
	        return "aaron/espaceUtilisateur";	
	    }
	 
	 
	 @Transactional
	 	@RequestMapping(value = {"/aaronFormulaireNumeroModifBanquePrincipale"}, method = RequestMethod.POST)
	    public String formulaireNumeroModifBanquePrincipale(Model model, @ModelAttribute("aaronBanque") AaronBanque aaronBanque, HttpSession session, @PageableDefault(size = 50) Pageable pageable, HttpServletRequest request) {
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
			
			String codeBanque=aaronBanque.getCodeBanque().trim();
			AaronBanque banqueRecherche=aaronBanqueRepository.findBanquePrincipaleByCodeBanque(codeBanque);			
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
				 Page<AaronBanque> banquesPage =aaronBanqueRepository.findAllBanquePage(pageable);
				 model.addAttribute("banquePrincipales", banquesPage);
				 return "aaron/espaceUtilisateur";  
			}	
						
			List <String> codeBanqueExistant=aaronBanqueRepository.findAllCodeBanque();
			
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
			Page<AaronBanque> banquesPage =aaronBanqueRepository.findAllBanquePage(pageable);
			model.addAttribute("banquePrincipales", banquesPage);
			session.setAttribute("codeBanqueCache", codeBanque);	
			model.addAttribute("formulaireGestionModifBanquePrincipale", "formulaireGestionModifBanquePrincipale");		
			model.addAttribute("nomBanqueRecherche", banqueRecherche.getNomBanque());	
			model.addAttribute("codeBanqueRecherche", banqueRecherche.getCodeBanque());	
			model.addAttribute("menuNavigation", "menuNavigation");			
	        return "aaron/espaceUtilisateur";	
	    }

	 	
	 	/// Resultat de la modification des données d'un formulaire
	 	@Transactional
		@RequestMapping(value = {"/aaronResultatModifBanquePrincipale" }, method = RequestMethod.POST)
	    public String resultatModifBanquePrincipale(Model model, @ModelAttribute("aaronBanque") AaronBanque aaronBanque, HttpSession session,@PageableDefault(size = 50) Pageable pageable, HttpServletRequest request) {
			String resultat=null;
			try {
				identifiantSession=session.getAttribute("identifiantSession").toString().trim();
			}
			catch(Exception e) {
				resultat="pageErreur";
				return resultat;
			}
			List <String> codeBanqueExistant=aaronBanqueRepository.findAllCodeBanque();
			
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
			Page<AaronBanque> banquesPage =aaronBanqueRepository.findAllBanquePage(pageable);
//			gestion Menu 
			model.addAttribute("gestionMenuUtilisateur", "gestionMenuUtilisateur");
			model.addAttribute("gestionMenuBanque", "gestionMenuBanque");
			model.addAttribute("gestionMenuGuichet", "gestionMenuGuichet");
			model.addAttribute("gestionMenuAgence", "gestionMenuAgence");
			model.addAttribute("gestionMenuAgent", "gestionMenuAgent");
			model.addAttribute("gestionMenuSociete", "gestionMenuSociete");
			model.addAttribute("accueilDeux", "accueilDeux");
			model.addAttribute("gestionMenuAgenceBanque", "gestionMenuAgenceBanque");
			String nomBanque=aaronBanque.getNomBanque().trim();
			String codeBanque=aaronBanque.getCodeBanque().trim();				
			String codeBanqueCache=session.getAttribute("codeBanqueCache").toString().trim();	
			
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionBanque", "Gestion Banque >");
			model.addAttribute("cheminModifierBanque", "Modifier une Banque >");
			model.addAttribute("titre", "Gestion des Banques");
			model.addAttribute("nomBanque", nomBanque);
			model.addAttribute("codeBanque", codeBanque);
			Banque banqueRecherche=banqueRepository.findBanquePrincipaleByCodeBanque(codeBanqueCache);
			if( nomBanque != null && nomBanque.length() > 0 && nomBanque.length()<=40 && codeBanque != null && codeBanque.length() > 0 && codeBanque.length()<=20) {						
				
				AaronBanque banquePrincipaleRecherche=aaronBanqueRepository.findBanquePrincipaleByCodeBanque(codeBanqueCache);
				Integer idBanque=banquePrincipaleRecherche.getIdBanque();
				
				AaronBanque banquePrincipaleNom=aaronBanqueRepository.findBanquePrincipaleByNom(nomBanque,idBanque);
				AaronBanque banquePrincipaleCode=aaronBanqueRepository.findBanquePrincipaleByCode(codeBanque,idBanque);
				
				if(banquePrincipaleNom==null && banquePrincipaleCode==null) {
				
					banquePrincipaleRecherche.setCodeBanque(codeBanque);
					banquePrincipaleRecherche.setNomBanque(nomBanque);
					AaronBanque bp=aaronBanqueRepository.save(banquePrincipaleRecherche);				
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
	        return "aaron/espaceUtilisateur";	
	    }
	 
	 
////	  Action Recherche Banque
	 @Transactional
	 @RequestMapping(value = {"/aaronRechercheBanquePrincipale" }, method = RequestMethod.GET)
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
	        return "aaron/espaceUtilisateur";		        
	    }
	 
	 //	@Transactional
	 @RequestMapping(value = {"/aaronResultatRechercheBanquePrincipale" }, method = RequestMethod.POST)
	    public String resultatRechercheBanquePrincipale(Model model, @ModelAttribute("aaronBanque") AaronBanque aaronBanque, HttpSession session) {
			String resultat=null;
			try {
				identifiantSession=session.getAttribute("identifiantSession").toString().trim();
			}
			catch(Exception e) {
				resultat="pageErreur";
				return resultat;
			}
			// gestion Menu 
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
			String codeBanque=aaronBanque.getCodeBanque().trim();
			AaronBanque banqueRecherche=aaronBanqueRepository.findBanquePrincipaleByCodeBanque(codeBanque);
			model.addAttribute("codeBanque", codeBanque);
			
			if( banqueRecherche == null) {
				model.addAttribute("formErreurBanquePrincipale", "formErreur");					
				model.addAttribute("codeBanquePrincipaleErreur", " La Banque de code "+codeBanque+" non introuvable dans le système. ");			
				model.addAttribute("formulaireNumeroRechercheBanquePrincipale", "formulaireNumeroRechercheBanquePrincipale");	
				model.addAttribute("actionTroisBouton", "actionTroisBouton");
				return "aaron/espaceUtilisateur";	
			}
				
			model.addAttribute("banqueRecherche", banqueRecherche);			
			model.addAttribute("resultatRechercheBanquePrincipale", "resultatRechercheBanquePrincipale");	
			model.addAttribute("actionTroisBouton", "actionTroisBouton");		
	        return "aaron/espaceUtilisateur";	
	    }
	 
	 
	 ////	 Action suppression Banque
	 @Transactional
	 	@RequestMapping(value = {"/aaronSupprimerBanquePrincipale" }, method = RequestMethod.GET)
	    public String supprimerBanquePrincipale(Model model, HttpSession session,@PageableDefault(size = 50) Pageable pageable, HttpServletRequest request) { 
			String resultat=null;
			try {
				identifiantSession=session.getAttribute("identifiantSession").toString().trim();
			}
			catch(Exception e) {
				resultat="pageErreur";
				return resultat;
			}	
			// gestion Menu 
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
			Page<AaronBanque> banquesPage =aaronBanqueRepository.findAllBanquePage(pageable);
			
			model.addAttribute("banquePrincipales", banquesPage);
			model.addAttribute("formulaireNumeroSupprimerBanquePrincipale", "formulaireNumeroSupprimerBanquePrincipale");
			model.addAttribute("actionTroisBouton", "actionTroisBouton");	
			model.addAttribute("identifiantSession", identifiantSession);  
			model.addAttribute("listeBanquePrincipale", "listeBanquePrincipale");
			model.addAttribute("gestionBanquePrincipale", "gestionBanquePrincipale");
			model.addAttribute("menuNavigation", "menuNavigation");
	        return "aaron/espaceUtilisateur";	        
	    }
	 
	 
//	 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//	 @Transactional
	 @RequestMapping(value = {"/aaronResultatSupprimerBanquePrincipale" }, method = RequestMethod.POST)
	    public String resultatSupprimerBanquePrincipale(Model model, @ModelAttribute("aaronBanque") AaronBanque aaronBanque, HttpSession sessionUtilisateur, HttpSession session, @PageableDefault(size = 50) Pageable pageable, HttpServletRequest request) {
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
			Page<AaronBanque> banquesPage =aaronBanqueRepository.findAllBanquePage(pageable);
			
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
			
			String codeBanque=aaronBanque.getCodeBanque().trim();
			session.setAttribute("codeBanquePrincipaleCache", codeBanque);
			AaronBanque banqueRecherche=aaronBanqueRepository.findBanquePrincipaleByCodeBanque(codeBanque);
			model.addAttribute("banquePrincipales",  banquesPage);
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("codeBanque", codeBanque);
			
			if( banqueRecherche == null) {		
							
				model.addAttribute("formErreurBanquePrincipale", "formErreur");					
				model.addAttribute("codeBanquePrincipaleErreur", " La Banque de code "+codeBanque+" non introuvable dans le système. ");			
				model.addAttribute("formulaireNumeroSupprimerBanquePrincipale", "formulaireNumeroSupprimerBanquePrincipale");
				model.addAttribute("actionTroisBouton", "actionTroisBouton");
				return "aaron/espaceUtilisateur";	
				 
			}								
			
			model.addAttribute("banquePrincipaleRecherche", banqueRecherche);			
			model.addAttribute("dialog_boxBanquePrincipale", "dialog_boxBanquePrincipale");	
			model.addAttribute("dialog_backgroundBanquePrincipale", "dialog_backgroundBanquePrincipale");	
			model.addAttribute("formulaireNumeroSupprimerBanquePrincipale", "formulaireNumeroSupprimerBanquePrincipale");
							
	        return "aaron/espaceUtilisateur";	
	    }
	 
//	 @Transactional
	 @RequestMapping(value = {"/aaronSuccesSuppressionBanquePrincipale" }, method = RequestMethod.POST)
	    public String succesSuppressionBanquePrincipale(Model model, @ModelAttribute("aaronBanque") AaronBanque aaronBanque, HttpSession session, @PageableDefault(size = 50) Pageable pageable, HttpServletRequest request) {
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
			String codeBanque=aaronBanque.getCodeBanque().trim();
			String codeBanquePrincipaleSuppression=session.getAttribute("codeBanquePrincipaleCache").toString().trim();
			AaronBanque banqueSuppression=aaronBanqueRepository.findBanquePrincipaleByCodeBanque(codeBanquePrincipaleSuppression);
			String status="Inactif";
			banqueSuppression.setStatus(status);
			aaronBanque=aaronBanqueRepository.save(banqueSuppression);			
			model.addAttribute("codeBanque", aaronBanque.getCodeBanque() );
			model.addAttribute("resultatSuppressionBanquePrincipale", "resultatSuppressionBanquePrincipale");	
			model.addAttribute("actionTroisBouton", "actionTroisBouton");
			
			int page = 0;
			int size = 50;			 
			pageable = PageRequest.of(page, size);
			Page<AaronBanque> banquesPage =aaronBanqueRepository.findAllBanquePage(pageable);
			model.addAttribute("banquePrincipales", banquesPage);
			
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("listeBanquePrincipale", "listeBanquePrincipale");
			model.addAttribute("gestionBanquePrincipale", "gestionBanquePrincipale");
			model.addAttribute("menuNavigation", "menuNavigation");			
	        return "aaron/espaceUtilisateur";	
	    }
	
//	 @Transactional
	 @RequestMapping(value = {"/aaronResultatModifDonneeBanquePrincipale" }, method = RequestMethod.POST)
	    public String resultatModifDonneeBanquePrincipale(Model model, @ModelAttribute("aaronBanque") AaronBanque aaronBanque, HttpSession session, @PageableDefault(size = 50) Pageable pageable, HttpServletRequest request) {
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
			String codeBanque=aaronBanque.getCodeBanque().trim();
			session.removeAttribute("codeBanquePrincipaleCache");
			session.setAttribute("codeBanquePrincipaleCache",codeBanque);						
			model.addAttribute("dialog_boxBanquePrincipale", "dialog_boxBanquePrincipale");	
			model.addAttribute("dialog_backgroundBanquePrincipale", "dialog_backgroundBanquePrincipale");				
			model.addAttribute("codeBanque", codeBanque);	
			model.addAttribute("actionTroisBouton", "actionTroisBouton");
			
			int page = 0;
			int size = 50;			 
			pageable = PageRequest.of(page, size);
			Page<AaronBanque> banquesPage =aaronBanqueRepository.findAllBanquePage(pageable);
			model.addAttribute("banquePrincipales", banquesPage);
			
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("listeBanquePrincipale", "listeBanquePrincipale");
			model.addAttribute("gestionBanquePrincipale", "gestionBanquePrincipale");
			model.addAttribute("menuNavigation", "menuNavigation");			
	        return "aaron/espaceUtilisateur";	
	    }
	 
        //	@Transactional
	 	@RequestMapping(value = {"/aaronEnvoiDonneeCacheeBanquePrincipale" }, method = RequestMethod.POST)
	    public String envoiDonneeCacheeBanquePrincipale(Model model, @ModelAttribute("aaronBanque") AaronBanque aaronBanque, HttpSession session, @PageableDefault(size = 50) Pageable pageable, HttpServletRequest request) {
			String resultat=null;
			try {
				identifiantSession=session.getAttribute("identifiantSession").toString().trim();
			}
			catch(Exception e) {
				resultat="pageErreur";
				return resultat;
			}
			List <String> codeBanqueExistant=aaronBanqueRepository.findAllCodeBanque();
			
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
			String codeBanque=aaronBanque.getCodeBanque().trim();
			AaronBanque banqueRecherche=aaronBanqueRepository.findBanquePrincipaleByCodeBanque(codeBanque);
			
			int page = 0;
			int size = 50;			 
			pageable = PageRequest.of(page, size);
			Page<AaronBanque> banquesPage =aaronBanqueRepository.findAllBanquePage(pageable);
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
	        return "aaron/espaceUtilisateur";	
	    }
	 	
	 	 @Transactional
			@RequestMapping(value = "/aaronlisteBanquePrincipales")
		    public ModelAndView listeUtilisateursPageByPage(@RequestParam(name="page", defaultValue="0") int page,@RequestParam(name="size", defaultValue="100") int size,  @ModelAttribute("aaronBanque")  AaronBanque aaronBanque, Model model, HttpSession session, HttpServletRequest request) {
		        ModelAndView modelAndView = new ModelAndView("aaron/espaceUtilisateur");
		       
		        PageRequest pageable = PageRequest.of(page-1, size);
		        Page<AaronBanque> banquesPage =aaronBanqueRepository.findAllBanquePage(pageable);

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
