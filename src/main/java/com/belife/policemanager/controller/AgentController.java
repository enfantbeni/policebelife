package com.belife.policemanager.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import com.belife.policemanager.model.dto.AgentDto;
import com.belife.policemanager.model.entity.Agence;
import com.belife.policemanager.model.entity.Agent;
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
	public String gestionAgent(Model model, HttpSession session) {
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
		
		model.addAttribute("cheminAccueil", "Accueil >");
		model.addAttribute("cheminGestionAgent", "Gestion Agent >");
		model.addAttribute("titre", "Gestion des Agents");
		Boolean estSupprimer = false;
		List<AgentDto> agentDtosAff=new ArrayList<AgentDto>();  	
		model.addAttribute("agentDtos", transformerAgentToAgentDto(agentDtosAff));
		
		model.addAttribute("identifiantSession", identifiantSession);
		model.addAttribute("listeAgent", "listeAgent");
		model.addAttribute("gestionAgent", "gestionAgent");
		model.addAttribute("menuNavigation", "menuNavigation");
		List<Agent> agents = new ArrayList<Agent>();
		agents = agentRepository.findAllAgents(estSupprimer);
		model.addAttribute("agents", agents);
		return "espaceUtilisateur";
	}

	@Transactional
	@RequestMapping(value = { "/ajoutAgent" }, method = RequestMethod.GET)
	public String ajoutAgence(Model model, HttpSession session) {

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
		
		model.addAttribute("cheminAccueil", "Accueil >");
		model.addAttribute("cheminGestionAgent", "Gestion Agent >");
		model.addAttribute("cheminAjouterAgent", "Ajouter un Agent >");
		model.addAttribute("titre", "Gestion des Agents");
		Boolean estSupprimer = false;
		List<Agent> agents = new ArrayList<Agent>();
		agents = agentRepository.findAllAgents(estSupprimer);
		
		List<AgentDto> agentDtosAff=new ArrayList<AgentDto>();  	
		model.addAttribute("agentDtos", transformerAgentToAgentDto(agentDtosAff));
		
		List<String> nomDirects = new ArrayList<String>();
		nomDirects = agenceRepository.findAllNomDirects(estSupprimer);
		model.addAttribute("nomDirects", nomDirects);
		
		model.addAttribute("agences", agents);
		model.addAttribute("identifiantSession", identifiantSession);
		model.addAttribute("formulaireGestionAgent", "formulaireGestionAgent");
		model.addAttribute("listeAgent", "listeAgent");
		model.addAttribute("gestionAgent", "gestionAgent");
		model.addAttribute("menuNavigation", "menuNavigation");
		return "espaceUtilisateur";
	}

	@Transactional
	@RequestMapping(value = { "/resultatAjoutAgent" }, method = RequestMethod.POST)
	public String resultatAjoutAgent(Model model, @ModelAttribute("agentDto") AgentDto agentDto,
			@ModelAttribute("agent") Agent agent, HttpSession session) {
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
		
		model.addAttribute("cheminAccueil", "Accueil >");
		model.addAttribute("cheminGestionAgent", "Gestion Agent >");
		model.addAttribute("cheminAjouterAgent", "Ajouter un Agent >");
		model.addAttribute("titre", "Gestion des Agents");
		String codeAgentDto = agentDto.getCodeAgent().trim();
		String nomAgentDto = agentDto.getNomAgent().trim();
		String nomAgenceDto = agentDto.getNomAgence().trim();
		String telephoneAgentDto=agentDto.getTelephone().trim();
		
		Agence agence = new Agence();

		List<Agent> agents = new ArrayList<Agent>();
		List<AgentDto> agentDtos = new ArrayList<AgentDto>();
		Boolean estSupprimer = false;
		Agent agentSave = null;
		Agent nomDirectSave = null;
		model.addAttribute("identifiantSession", identifiantSession);
		model.addAttribute("menuNavigation", "menuNavigation");
	
		if (codeAgentDto != null && codeAgentDto.length() > 0 && codeAgentDto.length() <= 5 && nomAgenceDto != null
				&& nomAgenceDto.length() > 0 && nomAgenceDto.length() <= 50 && nomAgentDto != null
				&& nomAgentDto.length() > 0 && nomAgentDto.length() <= 50) {
			agentSave = agentRepository.findAgentByCodeAgent(codeAgentDto);
			if (agentSave == null) {
				agence = agenceRepository.findAgenceByNomDirect(nomAgenceDto);
				agent.setIdAgence(agence);
				agent.setCodeAgent(codeAgentDto);
				agent.setNomAgent(nomAgentDto);
				agent.setTelephone(telephoneAgentDto);
				agent.setEstSupprimer(estSupprimer);
				agent.setDateCreation(new Date());

				model.addAttribute("listeAgent", "listeAgent");
				model.addAttribute("gestionAgent", "gestionAgent");
				agentRepository.save(agent);
				
				model.addAttribute("ajoutSuccesAgent", "Un Agent ajouté avec succès");
				
				agents = agentRepository.findAllAgents(estSupprimer);
				List<AgentDto> agentDtosAff=new ArrayList<AgentDto>();  	
				model.addAttribute("agentDtos", transformerAgentToAgentDto(agentDtosAff));
							
				model.addAttribute("agents", agents);
				return "espaceUtilisateur";
			}
			model.addAttribute("codeAgentErreur", " Code Agent déjà existant");

		}
		List<String> nomDirects = new ArrayList<String>();
		nomDirects = agenceRepository.findAllNomDirects(estSupprimer);
		model.addAttribute("nomDirects", nomDirects);

		model.addAttribute("formErreurAgent", "formErreur");
		if (codeAgentDto == null || codeAgentDto.length() == 0 || codeAgentDto.length() > 5) {
			model.addAttribute("codeAgentErreur", "Le code Agent doit être non null et supérieur à 5 caractères");
		}
		if (nomAgentDto == null || nomAgentDto.length() == 0 || nomAgentDto.length() > 50) {
			model.addAttribute("nomAgentErreur", "Le nom de l'Agent doit être non null");
		}
		if (nomAgenceDto == null || nomAgenceDto.length() == 0 || nomAgenceDto.length() > 50) {
			model.addAttribute("nomAgenceErreur", "Le nom de l'Agence doit être non null");
		}
		
		agents = agentRepository.findAllAgents(estSupprimer);
		List<AgentDto> agentDtosAff=new ArrayList<AgentDto>();  	
		model.addAttribute("agentDtos", transformerAgentToAgentDto(agentDtosAff));
		
		model.addAttribute("identifiantSession", identifiantSession);
		model.addAttribute("resultatAjoutAgent", "resultatAjoutAgent");
		model.addAttribute("formulaireGestionAgent", "formulaireGestionAgent");
		model.addAttribute("listeAgent", "listeAgent");
		model.addAttribute("gestionAgent", "gestionAgent");
		model.addAttribute("menuNavigation", "menuNavigation");
		return "espaceUtilisateur";
	}
	
	@Transactional
	public List<AgentDto> transformerAgentToAgentDto(List<AgentDto> agentDtosAffiche) {
		Boolean estSupprimer=false;
		List<Agent> agents = agentRepository.findAllAgents(estSupprimer);
		for (Agent ag : agents) {
			AgentDto objetAgentDto = new AgentDto();
			objetAgentDto.setIdAgent(ag.getIdAgent());
			objetAgentDto.setCodeAgent(ag.getCodeAgent());
			objetAgentDto.setNomAgent(ag.getNomAgent());
			objetAgentDto.setTelephone(ag.getTelephone());
			objetAgentDto.setEstSupprimer(ag.getEstSupprimer());
			Integer IdAgence=ag.getIdAgence().getIdAgence();
			String nomDirect = agenceRepository.findNomDirect(IdAgence);
			objetAgentDto.setNomAgence(nomDirect);
			agentDtosAffiche.add(objetAgentDto);
		}
		return agentDtosAffiche;
		
	}
	
	@Transactional
	@RequestMapping(value = {"/modifierAgent" }, method = RequestMethod.GET)
    public String modifierAgent(Model model, HttpSession session) { 
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
		
		model.addAttribute("cheminAccueil", "Accueil >");
		model.addAttribute("cheminGestionAgent", "Gestion Agent >");
		model.addAttribute("cheminModifierAgent", "Modifier un Agent >");
		model.addAttribute("titre", "Gestion des Agents");
		List<AgentDto> agentDtosAff=new ArrayList<AgentDto>();  	
		model.addAttribute("agentDtos", transformerAgentToAgentDto(agentDtosAff));		
		model.addAttribute("formulaireNumeroModifAgent", "formulaireNumeroModifAgent");
		model.addAttribute("quatreTroisBouton", "actionQuatreBouton");	
		model.addAttribute("identifiantSession", identifiantSession);
		model.addAttribute("listeAgent", "listeAgent");
		model.addAttribute("gestionAgent", "gestionAgent");
		model.addAttribute("menuNavigation", "menuNavigation");
        return "espaceUtilisateur";	
    }
	
	@Transactional
	public AgentDto donneeAgentDto(AgentDto agentDto, String codeAgent) {
			Agent agentModifie = agentRepository.findAgentByCodeAgent(codeAgent);
//			String pattern = "dd-MM-yyyy";
//			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
//			String date = simpleDateFormat.format(new Date());
			AgentDto objetAgentDto = new AgentDto();
			objetAgentDto.setIdAgent(agentModifie.getIdAgent());
			objetAgentDto.setCodeAgent(agentModifie.getCodeAgent());
			objetAgentDto.setNomAgent(agentModifie.getNomAgent());
			objetAgentDto.setEstSupprimer(agentModifie.getEstSupprimer());
			objetAgentDto.setTelephone(agentModifie.getTelephone());
			Integer IdAgence=agentModifie.getIdAgence().getIdAgence();
			String nomDirect = agenceRepository.findNomDirect(IdAgence);
			objetAgentDto.setNomAgence(nomDirect);			
		return objetAgentDto;	
	}
 
 
 @RequestMapping(value = {"/formulaireNumeroModifAgent" }, method = RequestMethod.POST)
    public String formulaireNumeroModifAgent(Model model, @ModelAttribute("agent") Agent agent, HttpSession sessionUtilisateur, HttpSession session) {
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
		
		String codeAgent=agent.getCodeAgent().trim();
		Agent agentRecherche=agentRepository.findAgentByCodeAgent(codeAgent);				
		
		if( agentRecherche == null) {
			 return "redirect:/messageAgentNonExistant";  
		}							
		Boolean estSupprimer=false;
		List<Agence> agences=new ArrayList<Agence>();
		model.addAttribute("cheminAccueil", "Accueil >");
		model.addAttribute("cheminGestionAgent", "Gestion Agent >");
		model.addAttribute("cheminModifierAgent", "Modifier un Agent >");
		model.addAttribute("titre", "Gestion des Agents");
		List<AgentDto> agentDtosAff=new ArrayList<AgentDto>();  	
		model.addAttribute("agentDtos", transformerAgentToAgentDto(agentDtosAff));
		
		List<String> nomDirects = new ArrayList<String>();
		nomDirects = agenceRepository.findAllNomDirects(estSupprimer);
		
		model.addAttribute("nomDirects", nomDirects);
		AgentDto agentDtoRecherche=new AgentDto();
		model.addAttribute("agentRecherche", donneeAgentDto(agentDtoRecherche,codeAgent));	
		
		session.setAttribute("codeAgenceCache", codeAgent);	
		model.addAttribute("formulaireGestionModifAgent", "formulaireGestionModifAgent");
		model.addAttribute("actionQuatreBouton", "actionQuatreBouton");	
		model.addAttribute("identifiantSession", identifiantSession);
		model.addAttribute("listeAgent", "listeAgent");
		model.addAttribute("gestionAgent", "gestionAgent");
		model.addAttribute("menuNavigation", "menuNavigation");			
        return "espaceUtilisateur";	
    }

 
// Resultat de la modification des données d'un formulaire
 @RequestMapping(value = {"/resultatModifAgent" }, method = RequestMethod.POST)
    public String resultatModifAgent(Model model,@ModelAttribute("agentDto") AgentDto agentDto, @ModelAttribute("agent") Agent agent, HttpSession sessionUtilisateur, HttpSession session) {
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
		
		model.addAttribute("cheminAccueil", "Accueil >");
		model.addAttribute("cheminGestionAgent", "Gestion Agent >");
		model.addAttribute("cheminModifierAgent", "Modifier un Agent >");
		model.addAttribute("titre", "Gestion des Agents");
		String codeAgentDto = agentDto.getCodeAgent().trim();
		String nomAgentDto = agentDto.getNomAgent().trim();
		String nomAgenceDto = agentDto.getNomAgence().trim();
		String telephoneAgenceDto=agentDto.getTelephone().trim();
		Agence agenceSave=new Agence();		
		List<Agent> agents=new ArrayList<Agent>();
		Boolean estSupprimer=false;			
		String codeAgentCache=session.getAttribute("codeAgenceCache").toString().trim();	
		
		Agent agentRecherche=agentRepository.findAgentByCodeAgent(codeAgentCache);
		
		Agent codeAgentRecherche=agentRepository.findAgentByCodeAgent(codeAgentDto);	
		
		if( codeAgentDto==null || codeAgentDto.length()==0 || codeAgentDto.length()>5 || nomAgentDto==null || nomAgentDto.length()==0 || nomAgentDto.length()>50 || nomAgenceDto==null || nomAgenceDto.length()==0 || nomAgenceDto.length()>50 || codeAgentRecherche!=null  ) {						
			
			
			AgentDto agentDtoRecherche=new AgentDto();
			model.addAttribute("agentRecherche", donneeAgentDto(agentDtoRecherche,agentRecherche.getCodeAgent()));
			
			
			model.addAttribute("formErreurAgent", "formErreur");
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("actionQuatreBouton", "actionQuatreBouton");	
			model.addAttribute("formulaireGestionModifAgent", "formulaireGestionModifAgent");
			model.addAttribute("listeAgent", "listeAgent");
			model.addAttribute("gestionAgent", "gestionAgent");
			model.addAttribute("menuNavigation", "menuNavigation");	
			List<String> nomDirects = new ArrayList<String>();
			nomDirects = agenceRepository.findAllNomDirects(estSupprimer);
			model.addAttribute("nomDirects", nomDirects);
			
			if(codeAgentDto==null || codeAgentDto.length()==0 || codeAgentDto.length()>5) {
				model.addAttribute("codeAgentErreur", " Le code Agent doit être supérieur à 5 caractères ");
			}
			if(nomAgentDto==null || nomAgentDto.length()==0 || nomAgentDto.length()>50) {
				model.addAttribute("nomAgentErreur", "Le nom de l'Agent  est invalide");
			}
			
			if(codeAgentRecherche !=null) {
				model.addAttribute("codeAgentExistantErreur", "Cet code Agent déjà existant");
			}
			
			List<AgentDto> agentDtosAff=new ArrayList<AgentDto>();  	
			model.addAttribute("agentDtos", transformerAgentToAgentDto(agentDtosAff));
			
			return "espaceUtilisateur";	
		}
		Integer idAgenceRecherche=agentRecherche.getIdAgent(); 
		Integer idCodeAgenceRecherche=codeAgentRecherche.getIdAgent();
		if(codeAgentRecherche!=null) {
			if(idCodeAgenceRecherche!=idAgenceRecherche) {
				model.addAttribute("codeAgentExistantErreur", "Code Agent déjà existant");
				model.addAttribute("formErreurAgent", "formErreur");
				model.addAttribute("identifiantSession", identifiantSession);
				model.addAttribute("actionQuatreBouton", "actionQuatreBouton");	
				model.addAttribute("formulaireGestionModifAgent", "formulaireGestionModifAgent");
				model.addAttribute("listeAgent", "listeAgent");
				model.addAttribute("gestionAgent", "gestionAgent");
				model.addAttribute("menuNavigation", "menuNavigation");	
				List<String> nomDirects = new ArrayList<String>();
				nomDirects = agenceRepository.findAllNomDirects(estSupprimer);
				model.addAttribute("nomDirects", nomDirects);
				return "espaceUtilisateur";	
			}
		}
		
		
		 agentRecherche.setNomAgent(nomAgentDto);
		 agentRecherche.setCodeAgent(codeAgentDto);
		 agenceSave = agenceRepository.findAgenceByNomDirect(nomAgenceDto);
		 agentRecherche.setIdAgence(agenceSave);	 
		 agentRecherche.setEstSupprimer(estSupprimer);	
		 agentRecherche.setTelephone(telephoneAgenceDto);
		agentRepository.save(agentRecherche);
		
		List<AgentDto> agentDtosAff=new ArrayList<AgentDto>();  	
		model.addAttribute("agentDtos", transformerAgentToAgentDto(agentDtosAff));
		model.addAttribute("agentRecherche", agentRecherche);	
		model.addAttribute("listeAgent", "listeAgent");
		model.addAttribute("actionQuatreBouton", "actionQUatreBouton");	
		model.addAttribute("resultatModifAgent", "resultatModifAgent");
		model.addAttribute("identifiantSession", identifiantSession);
		model.addAttribute("gestionAgent", "gestionAgent");
		model.addAttribute("menuNavigation", "menuNavigation");	
		
        return "espaceUtilisateur";	
    }
 
    // Recheche Agent
	 @RequestMapping(value = {"/rechercheAgent" }, method = RequestMethod.GET)
	 public String rechercherAgence(Model model, HttpSession session) { 
			String resultat=null;
			try {
				identifiantSession=session.getAttribute("identifiantSession").toString().trim();
			}
			catch(Exception e) {
				resultat="pageErreur";
				return resultat;
			}
			//gestion Menu 
			model.addAttribute("gestionMenuUtilisateur", "gestionMenuUtilisateur");
			model.addAttribute("gestionMenuBanque", "gestionMenuBanque");
			model.addAttribute("gestionMenuGuichet", "gestionMenuGuichet");
			model.addAttribute("gestionMenuAgence", "gestionMenuAgence");
			model.addAttribute("gestionMenuAgent", "gestionMenuAgent");
			model.addAttribute("gestionMenuSociete", "gestionMenuSociete");
			model.addAttribute("accueilDeux", "accueilDeux");
			
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionAgent", "Gestion Agent >");
			model.addAttribute("cheminRechercherAgent", "Rechercher un Agent >");
			model.addAttribute("titre", "Gestion des Agents");
			Boolean estSupprimer=false;
			List<Agent> agents=new ArrayList<Agent>();
			agents=agentRepository.findAllAgents(estSupprimer);
			model.addAttribute("agents", agents);
			List<AgentDto> agentDtosAff=new ArrayList<AgentDto>();  	
			model.addAttribute("agentDtos", transformerAgentToAgentDto(agentDtosAff));
			model.addAttribute("formulaireNumeroRechercheAgent", "formulaireNumeroRechercheAgent");
			model.addAttribute("actionQuatreBouton", "actionQuatreBouton");	
			model.addAttribute("identifiantSession", identifiantSession);  
			model.addAttribute("listeAgent", "listeAgent");
			model.addAttribute("gestionAgent", "gestionAgent");
			model.addAttribute("menuNavigation", "menuNavigation");
	     return "espaceUtilisateur";		        
	 }
	
	
	@RequestMapping(value = {"/resultatRechercheAgent" }, method = RequestMethod.POST)
	 public String resultatRechercheAgent(Model model, @ModelAttribute("agent") Agent agent, HttpSession sessionUtilisateur, HttpSession session) {
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
			model.addAttribute("cheminGestionAgent", "Gestion Agent >");
			model.addAttribute("cheminRechercherAgent", "Rechercher un Agent >");
			model.addAttribute("titre", "Gestion des Agents");
			String codeAgent=agent.getCodeAgent().trim();
			
			session.setAttribute("codeAgenceCache", codeAgent);
			Agent agentRecherche=agentRepository.findAgentByCodeAgent(codeAgent);
			if( agentRecherche == null) {				
				 return "redirect:/messageAgentNonExistant";  
			}								
			Boolean estSupprimer=false;
			List<Agence> agences=new ArrayList<Agence>();
			List<AgentDto> agentDtosAff=new ArrayList<AgentDto>();  	
			model.addAttribute("agentDtos", transformerAgentToAgentDto(agentDtosAff));
			
			AgentDto agentDtoRecherche=new AgentDto();
			model.addAttribute("agentRecherche", donneeAgentDto(agentDtoRecherche,agentRecherche.getCodeAgent()));	
			
			model.addAttribute("resultatRechercheAgent", "resultatRechercheAgent");	
			model.addAttribute("actionQuatreBouton", "actionQuatreBouton");	
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("gestionAgent", "gestionAgent");
			model.addAttribute("menuNavigation", "menuNavigation");		
	     return "espaceUtilisateur";	
	 }
	
	 @RequestMapping(value = {"/messageAgentNonExistant" }, method = RequestMethod.GET)
	    public String messageAgentNonExistant(Model model, @ModelAttribute("agent") Agent agent, HttpSession sessionUtilisateur, HttpSession session) {
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
			model.addAttribute("cheminGestionAgent", "Gestion Agent >");
			model.addAttribute("cheminRechercherAgent", " Agent non existant >");
			model.addAttribute("titre", "Gestion des Agents");
			String codeAgentCache=session.getAttribute("codeAgenceCache").toString().trim();
			model.addAttribute("codeAgent", codeAgentCache);	
			model.addAttribute("messageAgentNonExistant", "messageAgentNonExistant");		
			model.addAttribute("actionQuatreBouton", "actionQuatreBouton");	
			Boolean estSupprimer=false;
			List<Agent> agents=new ArrayList<Agent>();		
			
			List<AgentDto> agentDtosAff=new ArrayList<AgentDto>();  	
			model.addAttribute("agentDtos", transformerAgentToAgentDto(agentDtosAff));	
			
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("listeAgent", "listeAgent");
			model.addAttribute("gestionAgent", "gestionAgent");
			model.addAttribute("menuNavigation", "menuNavigation");		
	        return "espaceUtilisateur";	
	    }
	
	 	//	 Supprimer un Agent
	 	@RequestMapping(value = {"/supprimerAgent" }, method = RequestMethod.GET)
	    public String supprimerAgent(Model model, HttpSession session) { 
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
			model.addAttribute("cheminGestionAgent", "Gestion Agent >");
			model.addAttribute("cheminSupprimerAgent", "Supprimer un Agent >");
			model.addAttribute("titre", "Gestion des Agents");
			List<AgentDto> agentDtosAff=new ArrayList<AgentDto>();  	
			model.addAttribute("agentDtos", transformerAgentToAgentDto(agentDtosAff));
			model.addAttribute("formulaireNumeroSupprimerAgent", "formulaireNumeroSupprimerAgent");
			model.addAttribute("actionQuatreBouton", "actionQuatreBouton");	
			model.addAttribute("identifiantSession", identifiantSession);  
			model.addAttribute("listeAgent", "listeAgent");
			model.addAttribute("gestionAgent", "gestionAgent");
			model.addAttribute("menuNavigation", "menuNavigation");
	        return "espaceUtilisateur";	        
	    }
	 
	 	@RequestMapping(value = {"/resultatSupprimerAgent" }, method = RequestMethod.POST)
	    public String resultatSuppressionAgent(Model model, @ModelAttribute("agent") Agent agent, HttpSession sessionUtilisateur, HttpSession session) {
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
			model.addAttribute("cheminGestionAgent", "Gestion Agent >");
			model.addAttribute("cheminSupprimerAgent", "Supprimer un Agent >");
			model.addAttribute("titre", "Gestion des Agents");
			String codeAgent=agent.getCodeAgent().trim();
			session.setAttribute("codeAgenceCache", codeAgent);
			Agent agentRecherche=agentRepository.findAgentByCodeAgent(codeAgent);
			if( agentRecherche == null) {				
				 return "redirect:/messageAgentNonExistant";  
			}										
			model.addAttribute("dialog_boxAgent", "dialog_boxAgent");	
			model.addAttribute("dialog_backgroundAgent", "dialog_backgroundAgent");	
			model.addAttribute("actionQuatreBouton", "actionQuatreBouton");	
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("codeAgent", codeAgent);
			model.addAttribute("listeAgent", "listeAgent");
			model.addAttribute("gestionAgent", "gestionAgent");
			model.addAttribute("menuNavigation", "menuNavigation");	
			List<AgentDto> agentDtosAff=new ArrayList<AgentDto>();  	
			model.addAttribute("agentDtos", transformerAgentToAgentDto(agentDtosAff));
	        return "espaceUtilisateur";	
	    }
	 
	 @RequestMapping(value = {"/succesSuppressionAgent" }, method = RequestMethod.POST)
	    public String succesSuppressionAgent(Model model, @ModelAttribute("agent") Agent agent, HttpSession sessionUtilisateur, HttpSession session) {
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
			model.addAttribute("cheminGestionAgent", "Gestion Agent >");
			model.addAttribute("cheminSupprimerAgent", "Supprimer un Agent >");
			model.addAttribute("titre", "Gestion des Agents");
			String codeAgentSuppression=session.getAttribute("codeAgenceCache").toString().trim();
			Agent agentSuppression=agentRepository.findAgentByCodeAgent(codeAgentSuppression);
			agentSuppression.setEstSupprimer(true);
			agentRepository.save(agentSuppression);			
			model.addAttribute("codeAgent", codeAgentSuppression);
			model.addAttribute("resultatSuppressionAgent", "resultatSuppressionAgent");	
			model.addAttribute("actionQuatreBouton", "actionQUatreBouton");
			
			List<AgentDto> agentDtosAff=new ArrayList<AgentDto>();  	
			model.addAttribute("agentDtos", transformerAgentToAgentDto(agentDtosAff));
			
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("listeAgent", "listeAgent");
			model.addAttribute("gestionAgent", "gestionAgent");
			model.addAttribute("menuNavigation", "menuNavigation");			
	        return "espaceUtilisateur";	
	    }
	 
	 @RequestMapping(value = {"/resultatModifDonneeAgent" }, method = RequestMethod.POST)
	    public String resultatModifDonneeAgence(Model model, @ModelAttribute("agent") Agent agent, HttpSession sessionUtilisateur, HttpSession session) {
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
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionAgent", "Gestion Agent >");
			model.addAttribute("cheminSupprimerAgent", "Supprimer un Agent >");
			model.addAttribute("titre", "Gestion des Agents");
			
			String codeAgent=agent.getCodeAgent().trim();
			session.removeAttribute("codeAgenceCache");
			session.setAttribute("codeAgenceCache",codeAgent);						
			model.addAttribute("dialog_boxAgent", "dialog_boxAgent");	
			model.addAttribute("dialog_backgroundAgent", "dialog_backgroundAgent");
			List<AgentDto> agentDtosAff=new ArrayList<AgentDto>();  	
			model.addAttribute("agentDtos", transformerAgentToAgentDto(agentDtosAff));
			model.addAttribute("codeAgent", codeAgent);	
			model.addAttribute("actionQuatreBouton", "actionQuatreBouton");
			List<Agent> agents=new ArrayList<Agent>();
			agents=agentRepository.findAllAgents(estSupprimer);	
			model.addAttribute("agents", agents);
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("listeAgent", "listeAgent");
			model.addAttribute("gestionAgent", "gestionAgent");
			model.addAttribute("menuNavigation", "menuNavigation");			
	        return "espaceUtilisateur";	
	    }
	 
	 	@RequestMapping(value = {"/envoiDonneeCacheeAgent" }, method = RequestMethod.POST)
	    public String envoiDonneeCacheeAgent(Model model, @ModelAttribute("agent") Agent agent, HttpSession sessionUtilisateur, HttpSession session) {
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
			model.addAttribute("cheminGestionAgent", "Gestion Agent >");
			model.addAttribute("cheminModifierAgent", "Modifier un Agent >");
			model.addAttribute("titre", "Gestion des Agents");
			session.removeAttribute("codeAgenceCache");
			String codeAgent=agent.getCodeAgent();
			Agent agentRecherche=agentRepository.findAgentByCodeAgent(codeAgent);
			Boolean estSupprimer=false;
			List<String> nomDirects = new ArrayList<String>();
			nomDirects = agenceRepository.findAllNomDirects(estSupprimer);
			model.addAttribute("nomDirects", nomDirects);
			List<Agent> agents=new ArrayList<Agent>();
			agents=agentRepository.findAllAgents(estSupprimer);
			model.addAttribute("agents", agents);
			session.setAttribute("codeAgenceCache", codeAgent);	
			List<AgentDto> agentDtosAff=new ArrayList<AgentDto>();  	
			model.addAttribute("agentDtos", transformerAgentToAgentDto(agentDtosAff));
			model.addAttribute("formulaireGestionModifAgent", "formulaireGestionModifAgent");
			model.addAttribute("actionQuatreBouton", "actionQuatreBouton");	
			model.addAttribute("agentRecherche", agentRecherche);
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("listeAgent", "listeAgent");
			model.addAttribute("gestionAgent", "gestionAgent");
			model.addAttribute("menuNavigation", "menuNavigation");			
	        return "espaceUtilisateur";	
	    }	

}
