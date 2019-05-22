package com.belife.policemanager.controller;

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

import com.belife.policemanager.model.dto.AgentDto;
import com.belife.policemanager.model.entity.Agence;
import com.belife.policemanager.model.entity.AgenceBanque;
import com.belife.policemanager.model.entity.Agent;
import com.belife.policemanager.model.entity.Commercial;
import com.belife.policemanager.model.entity.Societe;
import com.belife.policemanager.model.repository.AgenceRepository;
import com.belife.policemanager.model.repository.AgentRepository;
import com.belife.policemanager.model.repository.BanqueRepository;
import com.belife.policemanager.model.repository.RolesRepository;
import com.belife.policemanager.model.repository.SourcePoliceRepository;
import com.belife.policemanager.model.repository.UtilisateurRepository;

@Controller
public class AgentController {

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
	AgentRepository agentRepository;

	String identifiantSession = null;

	@Transactional
	@RequestMapping(value = { "/gestionAgent" }, method = RequestMethod.GET)
	public String gestionAgent(Model model, HttpSession session, @PageableDefault(size = 400) Pageable pageable, HttpServletRequest request) {
		String resultat = null;
		try {
			identifiantSession = session.getAttribute("identifiantSession").toString().trim();
		} catch (Exception e) {
			resultat = "pageErreur";
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
		model.addAttribute("cheminAccueil", "Accueil >");
		model.addAttribute("cheminGestionAgent", " Agent >");
		model.addAttribute("titre", " Agents ");
		String tatus="actif";		
		model.addAttribute("identifiantSession", identifiantSession);
		model.addAttribute("listeAgent", "listeAgent");
		model.addAttribute("gestionAgent", "gestionAgent");
		model.addAttribute("menuNavigation", "menuNavigation");
		int page = 0;
		int size = 400;			 
		pageable = PageRequest.of(page, size);
		Page<Commercial> agents = agentRepository.findAllAgentsPage(pageable);		
		model.addAttribute("agents", agents);
		return "espaceUtilisateur";
	}
	
	 @Transactional
		@RequestMapping(value = "/listeAgents")
	    public ModelAndView listeAgentsPageByPage(@RequestParam(name="page", defaultValue="0") int page,@RequestParam(name="size", defaultValue="400") int size,  @ModelAttribute("agent") Agent agent, Model model, HttpSession session, HttpServletRequest request) {
	        ModelAndView modelAndView = new ModelAndView("espaceUtilisateur");
	       
	        PageRequest pageable = PageRequest.of(page-1, size);
	        Page<Commercial> agents = agentRepository.findAllAgentsPage(pageable);
			model.addAttribute("agents", agents);
					
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
			model.addAttribute("cheminGestionAgent", " Agent >");
			model.addAttribute("titre", " Agents ");
			String tatus="actif";		
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("listeAgent", "listeAgent");
			model.addAttribute("gestionAgent", "gestionAgent");
			model.addAttribute("menuNavigation", "menuNavigation");
			
	        return modelAndView;
		} 

	 	 
//	 @Transactional
	 @RequestMapping(value = {"/resultatRechercheAgent" }, method = RequestMethod.POST)
	    public String resultatRechercheSociete(Model model, @ModelAttribute("commercial") Commercial commercial, HttpSession session) {
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
			model.addAttribute("gestionMenuAgenceBanque", "gestionMenuAgenceBanque");
			String status="actif";
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionAgent", " Agent >");
			model.addAttribute("cheminRechercherAgent", "Recherche Agent >");
			String codeAgent=commercial.getCodeAgent().trim();
			session.setAttribute("codeAgentCache", codeAgent);
			Commercial agentRecherche=agentRepository.findAgentByCodeAgent(codeAgent);
			if( agentRecherche == null) {				
				 return "redirect:/messageAgentNonExistant";  
			}		
			model.addAttribute("titre", " Agent ");
			model.addAttribute("agentRecherche", agentRecherche);
			model.addAttribute("codeAgent", codeAgent);
			model.addAttribute("resultatRechercheAgent", "resultatRechercheAgent");	
			model.addAttribute("actionQuatreBouton", "actionQuatreBouton");
			
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("gestionAgent", "gestionAgent");
			model.addAttribute("menuNavigation", "menuNavigation");
			
			model.addAttribute("menuNavigation", "menuNavigation");		
	        return "espaceUtilisateur";	
	    }	 
	 
	 
	 @Transactional
	 @RequestMapping(value = {"/messageAgentNonExistant" }, method = RequestMethod.GET)
	    public String messageSocieteNonExistante(Model model, @ModelAttribute("societe") Societe societe, HttpSession session, @PageableDefault(size = 100) Pageable pageable, HttpServletRequest request) {
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
			
			String codeAgent=session.getAttribute("codeAgentCache").toString().trim();
			model.addAttribute("codeAgent", codeAgent);
			
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionAgence", " Société >");
			model.addAttribute("titre", " Société ");			
			model.addAttribute("actionQuatreBouton", "actionQuatreBouton");	
			model.addAttribute("messageAgentNonExistant", "messageAgentNonExistant");	
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("gestionAgent", "gestionAgent");
			model.addAttribute("menuNavigation", "menuNavigation");	
			
	        return "espaceUtilisateur";	
	    }



}
