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
import com.belife.policemanager.model.entity.Client;
import com.belife.policemanager.model.entity.Lbb;
import com.belife.policemanager.model.repository.AgenceRepository;
import com.belife.policemanager.model.repository.BanqueRepository;
import com.belife.policemanager.model.repository.ConsultationLBRepository;
import com.belife.policemanager.model.repository.RolesRepository;
import com.belife.policemanager.model.repository.SourcePoliceRepository;
import com.belife.policemanager.model.repository.UtilisateurRepository;

@Controller
public class ConsultationLBController {
	
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
	 ConsultationLBRepository consultationLBRepository;
	 
	 String identifiantSession=null;
	
	@RequestMapping(value = {"/gestionConsultationLB" }, method = RequestMethod.GET)
    public String gestionAgence(Model model, HttpSession session,@PageableDefault(size = 200) Pageable pageable,HttpServletRequest request) { 
	 
	 String resultat=null;
		try {
			identifiantSession=session.getAttribute("identifiantSession").toString().trim();
		}
		catch(Exception e) {
			resultat="pageErreur";
			return resultat;
		}
		
		model.addAttribute("cheminAccueil", "Accueil >");
		model.addAttribute("cheminGestionLB", "Consultation LB >");
		model.addAttribute("titre", "Consultation LB");

		model.addAttribute("identifiantSession", identifiantSession);
		model.addAttribute("listeLB", "listeLB");
		model.addAttribute("gestionLB", "gestionLB");
		model.addAttribute("unBoutonLB", "unBoutonLB");		
		
		//	gestion Menu 	
//		model.addAttribute("gestionConsultationLB", "gestionConsultationLB");
		model.addAttribute("menuNavigation", "menuNavigation");
		
		int page = 0;
		int size = 200;
		
		pageable = PageRequest.of(page, size);
		
		Page<Lbb> consultationPage=consultationLBRepository.findAllConsultation(pageable);
		model.addAttribute("consultationPage", consultationPage);
		 return "utilisateur/accueilUtilisateur";		
    }
	
	@Transactional
	@RequestMapping(value = "/consultationPage")
    public ModelAndView listArticlesPageByPage(@RequestParam(name="page", defaultValue="0") int page,@RequestParam(name="size", defaultValue="200") int size,  @ModelAttribute("client") Client client, Model model, HttpSession session, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("utilisateur/accueilUtilisateur");
        Boolean estSupprimer=false;
        String nomAgenceActive=session.getAttribute("nomAgenceActif").toString().trim();
		 Agence agenceActive=agenceRepository.findAgenceByNomDirect(nomAgenceActive);
		
        PageRequest pageable = PageRequest.of(page-1, size);
        Page<Lbb> consultationPage=consultationLBRepository.findAllConsultation(pageable);

		 if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
	            page = Integer.parseInt(request.getParameter("page")) - 1;
	        }
		 if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
	            size = Integer.parseInt(request.getParameter("size"));
	        }
		 consultationPage=consultationLBRepository.findAllConsultation(pageable);
		model.addAttribute("consultationPage", consultationPage);
		model.addAttribute("listeLB", "listeLB");
		model.addAttribute("gestionLB", "gestionLB");
		model.addAttribute("cheminAccueil", "Accueil >");
		model.addAttribute("cheminGestionLB", "Consultation LB >");
		model.addAttribute("titre", "Consultation LB");

		model.addAttribute("identifiantSession", identifiantSession);
		model.addAttribute("listeLB", "listeLB");
		
		//	gestion Menu 	
		model.addAttribute("menuNavigation", "menuNavigation");
        return modelAndView;
    }
	
	@RequestMapping(value = {"/rechercheLB" }, method = RequestMethod.GET)
    public String rechercheLB(Model model, HttpSession session,@PageableDefault(size = 100) Pageable pageable,HttpServletRequest request) { 
	 
	 String resultat=null;
		try {
			identifiantSession=session.getAttribute("identifiantSession").toString().trim();
		}
		catch(Exception e) {
			resultat="pageErreur";
			return resultat;
		}
		
		model.addAttribute("cheminAccueil", "Accueil >");
		model.addAttribute("cheminGestionLB", "Consultation LB >");
		model.addAttribute("titre", "Consultation LB");

		model.addAttribute("identifiantSession", identifiantSession);
		model.addAttribute("gestionLB", "gestionLB");
		model.addAttribute("unBoutonLB", "unBoutonLB");		
		
		//	gestion Menu 	
//		model.addAttribute("gestionConsultationLB", "gestionConsultationLB");
		model.addAttribute("menuNavigation", "menuNavigation");
		
		model.addAttribute("formulaireNumeroPoliceRechercheLbb","formulaireNumeroPoliceRechercheLbb");
		 return "utilisateur/accueilUtilisateur";		
    }
	
	@Transactional
	 @RequestMapping(value = {"/resultatRechercheLB" }, method = RequestMethod.POST)
	    public String resultatRechercheLB(Model model, @ModelAttribute("lbb") Lbb lbb, HttpSession session) {
			String resultat=null;
			try {
				identifiantSession=session.getAttribute("identifiantSession").toString().trim();
			}
			catch(Exception e) {
				resultat="pageErreur";
				return resultat;
			}
			
//			gestion Menu 
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionLB", "Consultation LB >");
			model.addAttribute("titre", "Consultation LB");
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("gestionLB", "gestionLB");
			model.addAttribute("unBoutonLB", "unBoutonLB");	
			model.addAttribute("menuNavigation", "menuNavigation");
			model.addAttribute("resultatRechercheLB", "resultatRechercheLB");

			String numeroPolice=lbb.getLBPOLN().trim();
			System.out.println(" Nuemro de police : "+numeroPolice);
			Lbb lbbRecherche=consultationLBRepository.findConsultationByLBPOLN(numeroPolice);
			if( lbbRecherche == null) {				
				 return "redirect:/messageLBNonExistante";  
			}	
			
			model.addAttribute("lbbRecherche", lbbRecherche);	
	        return "utilisateur/accueilUtilisateur";	
	    }
	
	@RequestMapping(value = {"/messageLBNonExistante" }, method = RequestMethod.GET)
    public String messageAgenceNonExistante(Model model, HttpSession session,@PageableDefault(size = 200) Pageable pageable,HttpServletRequest request) { 
	 
	 String resultat=null;
		try {
			identifiantSession=session.getAttribute("identifiantSession").toString().trim();
		}
		catch(Exception e) {
			resultat="pageErreur";
			return resultat;
		}
		
		model.addAttribute("cheminAccueil", "Accueil >");
		model.addAttribute("cheminGestionLB", "Consultation LB >");
		model.addAttribute("titre", "Consultation LB");

		model.addAttribute("identifiantSession", identifiantSession);
		model.addAttribute("gestionLB", "gestionLB");
		model.addAttribute("unBoutonLB", "unBoutonLB");		
		
		//	gestion Menu 	
//		model.addAttribute("gestionConsultationLB", "gestionConsultationLB");
		model.addAttribute("menuNavigation", "menuNavigation");
		model.addAttribute("messageLBNonExistante", "messageLBNonExistante");
		int page = 0;
		int size = 200;
		
		pageable = PageRequest.of(page, size);
		
		Page<Lbb> consultationPage=consultationLBRepository.findAllConsultation(pageable);
		model.addAttribute("consultationPage", consultationPage);
		 return "utilisateur/accueilUtilisateur";		
    }

}
