package com.belife.policemanager.controller;


import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.belife.policemanager.model.dto.BanqueDto;
import com.belife.policemanager.model.dto.ClientDto;
import com.belife.policemanager.model.dto.SourcePrelevementDto;
import com.belife.policemanager.model.entity.Agence;
import com.belife.policemanager.model.entity.Agent;
import com.belife.policemanager.model.entity.Banque;
import com.belife.policemanager.model.entity.BanquePrincipale;
import com.belife.policemanager.model.entity.Client;
import com.belife.policemanager.model.entity.ClientBanque;
import com.belife.policemanager.model.entity.ClientPlan;
import com.belife.policemanager.model.entity.ClientSociete;
import com.belife.policemanager.model.entity.Plan;
import com.belife.policemanager.model.entity.Societe;
import com.belife.policemanager.model.entity.Utilisateur;
import com.belife.policemanager.model.repository.AgentRepository;
import com.belife.policemanager.model.repository.BanquePrincipaleRepository;
import com.belife.policemanager.model.repository.BanqueRepository;
import com.belife.policemanager.model.repository.ClientBanqueRepository;
import com.belife.policemanager.model.repository.ClientPlanRepository;
import com.belife.policemanager.model.repository.ClientRepository;
import com.belife.policemanager.model.repository.ClientSocieteRepository;
import com.belife.policemanager.model.repository.PlanRepository;
import com.belife.policemanager.model.repository.RolesRepository;
import com.belife.policemanager.model.repository.SocieteRepository;
import com.belife.policemanager.model.repository.SourcePoliceRepository;
import com.belife.policemanager.model.repository.UtilisateurRepository;






@Controller
public class SoumissionController {
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
	 
	 @Autowired
     SocieteRepository societeRepository;
	 
	 @Autowired
     PlanRepository planRepository;
	 
	 @Autowired
     ClientRepository clientRepository;
	 
	 @Autowired
     AgentRepository agentRepository;
	 
	 @Autowired
     ClientPlanRepository clientPlanRepository;
	 
	 @Autowired
     ClientBanqueRepository clientBanqueRepository;
	 
	 @Autowired
     ClientSocieteRepository clientSocieteRepository;
	 
	 
	 
	 
	public SoumissionController () {
		
	}
	 
	 
    private String identifiantSession=null;
    SourcePrelevementDto sourceDto=new  SourcePrelevementDto();
    
    
	@RequestMapping(value = {"/soumission" }, method = RequestMethod.GET)
    public String sourcePrelevement(Model model,  HttpSession session) { 		
		String resultat=null;
		try {
			identifiantSession=session.getAttribute("identifiantSession").toString().trim();
		}
		catch(Exception e) {
			resultat="pageErreur";
			return resultat;
		}
		
		

		model.addAttribute("cheminAccueil",  "Accueil >");
		model.addAttribute("cheminSoumission",  "Soumission >");
		model.addAttribute("identifiantSession", identifiantSession);
		model.addAttribute("choisirSourcePrelevement", "choisirSourcePrelevement");
		model.addAttribute("titre", " Soumission de Proposition");
		model.addAttribute("menuNavigation", "menuNavigation");
        return "utilisateur/accueilUtilisateur";
    }
	
	
	
//	@@ designe par
	@Transactional
	@RequestMapping(value = {"/soumissionProposition@@Individuel" }, method = RequestMethod.POST)
    public String soumissionPropositionDeux(Model model, @ModelAttribute("banqueDto") BanqueDto banqueDto, HttpSession session, @PageableDefault(size = 4) Pageable pageable, HttpServletRequest request) { 		
		String resultat=null;
		String genreConvention=null;
		String nomSource=null;
		try {
			identifiantSession=session.getAttribute("identifiantSession").toString().trim();
		}
		catch(Exception e) {
			resultat="pageErreur";
			return resultat;
		}
		String indiceSource="banque";
		session.setAttribute("indiceSource", indiceSource);
		 
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
	     Page<Client> clientPage = clientRepository.findAllClientsPage(estSupprimer, pageable);
		 model.addAttribute("clientsPage", clientPage);
		 
		 
		
		List<String> nomSources=societeRepository.findAllNomSociete(estSupprimer);
		model.addAttribute("nomSources",  nomSources);
		String nomGuichet=banqueDto.getNomGuichet().trim();
		nomSource=session.getAttribute("nomSourceCache").toString().trim();
		model.addAttribute("nomSource",  nomSource);
		
		model.addAttribute("testIndividuel", "testIndividuel");
		
		
		///////////////////////// 	//////////////////// Données à soumettre
		session.setAttribute("nomGuichetSoumis", nomGuichet);
		session.setAttribute("nomBanqueSoumis", nomSource);
		String nomGuichetSoumis=session.getAttribute("nomGuichetSoumis").toString().trim();
		String nomBanqueSoumis=session.getAttribute("nomBanqueSoumis").toString().trim();
		String codeGuichetSoumis=banqueRepository.findCodeGuichetByNomGuichet(nomGuichetSoumis);
		String codeBanqueSoumis=banquePrincipaleRepository.findCodeBanquePrincipale(nomBanqueSoumis);
		String police=codeBanqueSoumis.concat(codeGuichetSoumis);
		model.addAttribute("codeGuichetSoumis",codeGuichetSoumis);
		session.setAttribute("police", police);
		model.addAttribute("police",police);
		
				
		
		
	
//		Code Banque et code Guichet
		String codeBanquePrincipale=banquePrincipaleRepository.findCodeBanquePrincipale(nomSource);
		String codeGuichet=banqueRepository.findCodeGuichetByNomGuichet(nomGuichet);
		model.addAttribute("genreSource", " Numero Compte * : ");
		session.setAttribute("genreSource", " Numero Compte * : " );
		
		String identifiant=session.getAttribute("identifiantSession").toString().trim();
		Utilisateur utilisateurRep=utilisateurRepository.findByIdentifiant(identifiant);
		Agence agence=utilisateurRep.getIdAgence();
		Integer idAgence=agence.getIdAgence();
		List<String> codeAgents=agentRepository.findAllCodeAgents(agence);
		model.addAttribute("codeAgents", codeAgents);
		List<Client> soumissions=clientRepository.findAllClients(estSupprimer);
		model.addAttribute("soumissions", soumissions);
		model.addAttribute("listeSoumission", "listeSoumission");
		List<String> planDurees=planRepository.findPlanDurees();
		model.addAttribute("cheminAccueil",  "Accueil >");
		model.addAttribute("cheminSoumission",  "Soumission >");
		model.addAttribute("planDurees", planDurees);
		model.addAttribute("nomSource",  nomGuichetSoumis);
		model.addAttribute("genreConvention",  genreConvention);
		model.addAttribute("titre", " Soumission de Proposition");
		model.addAttribute("identifiantSession", identifiantSession);
		model.addAttribute("soumissionProposition", "soumissionProposition");
		model.addAttribute("menuNavigation", "menuNavigation");
		model.addAttribute("police",police);
        return "utilisateur/accueilUtilisateur";
    }
	
	@Transactional
	@RequestMapping(value = {"/soumissionProposition" }, method = RequestMethod.POST)
    public String soumissionProposition(Model model, @ModelAttribute("sourceDto") SourcePrelevementDto sourceDto, HttpSession session, @PageableDefault(size = 10) Pageable pageable, HttpServletRequest request) { 		
		String resultat=null;
		String genreConvention=null;
		String nomSource=null;
		try {
			identifiantSession=session.getAttribute("identifiantSession").toString().trim();
		}
		catch(Exception e) {
			resultat="pageErreur";
			return resultat;
		}
		 try {
			  genreConvention=session.getAttribute("genreConvention").toString().trim(); 
			  nomSource=sourceDto.getNomSource().trim();
			  session.setAttribute("nomsourceModif", nomSource);
		 }catch(Exception e) {
			 	List<String> planDurees=planRepository.findPlanDurees();
				model.addAttribute("planDurees", planDurees);
				
				model.addAttribute("cheminAccueil",  "Accueil >");
				model.addAttribute("cheminSoumission",  "Soumission >");
				model.addAttribute("erreurConvention",  "  Veillez choisir la nature de la convention ");
				model.addAttribute("genreConvention",  genreConvention);
				model.addAttribute("titre", " Soumission de Proposition");
				model.addAttribute("identifiantSession", identifiantSession);
				model.addAttribute("choisirSourcePrelevement", "choisirSourcePrelevement");
				model.addAttribute("menuNavigation", "menuNavigation");
		        return "utilisateur/accueilUtilisateur";			 
		 }
		 
		 int page = 0;
		 int size = 10;
		 if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
	            page = Integer.parseInt(request.getParameter("page")) - 1;
	        }
		 if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
	            size = Integer.parseInt(request.getParameter("size"));
	        }
		 
		 Boolean estSupprimer=false;
		 pageable = PageRequest.of(0, 4);
	     Page<Client> clientPage = clientRepository.findAllClientsPage(estSupprimer, pageable);
		 model.addAttribute("clientsPage", clientPage);
		 
		session.setAttribute("codeSocieteSoumis", nomSource);	
		String codeSocieteSoumis=session.getAttribute("codeSocieteSoumis").toString().trim();
		codeSocieteSoumis=societeRepository.findCodeSocieteByNomSociete(codeSocieteSoumis);
		model.addAttribute("police", codeSocieteSoumis);
		
		String genreGroupe="groupe";
		String genreIndividuel="individuel";
		List<String> nomSources=societeRepository.findAllNomSociete(estSupprimer);
		model.addAttribute("nomSources",  nomSources);
		
		String redirectionChoixGuichet=session.getAttribute("redirectionChoixGuichet").toString().trim();
		String choixGuichetBanque="choixGuichet";
		if(redirectionChoixGuichet.equals(choixGuichetBanque)) {
			session.setAttribute("nomSourceCache",nomSource);
			return "redirect:/redirectionChoixGuichet";
		}
			
		if(genreConvention.equals(genreGroupe)) {
			model.addAttribute("genreSource", " Matricule Client * : ");
			session.setAttribute("genreSource", " Matricule Client * : ");
			model.addAttribute("testGroupe", "testGroupe");
			session.setAttribute("police", codeSocieteSoumis);
		}	
		String identifiant=session.getAttribute("identifiantSession").toString().trim();
		Utilisateur utilisateurRep=utilisateurRepository.findByIdentifiant(identifiant);
		Agence agence=utilisateurRep.getIdAgence();
		Integer idAgence=agence.getIdAgence();
		List<String> codeAgents=agentRepository.findAllCodeAgents(agence);
		model.addAttribute("codeAgents", codeAgents);
		List<Client> soumissions=clientRepository.findAllClients(estSupprimer);
		model.addAttribute("soumissions", soumissions);
		model.addAttribute("listeSoumission", "listeSoumission");
		session.setAttribute("nomSourceCache",nomSource);
//		/////////////////// Code Société Soumis
		String codeSociete=societeRepository.findCodeSocieteByNomSociete(nomSource);
		session.setAttribute("codeSocieteSoumis",codeSociete);
			
		String indiceSource="societe";
		session.setAttribute("indiceSource", indiceSource);
		
		List<String> planDurees=planRepository.findPlanDurees();
		model.addAttribute("cheminAccueil",  "Accueil >");
		model.addAttribute("cheminSoumission",  "Soumission >");
		model.addAttribute("planDurees", planDurees);
		model.addAttribute("nomSource",  nomSource);
		model.addAttribute("genreConvention",  genreConvention);
		model.addAttribute("titre", " Soumission de Proposition");
		model.addAttribute("identifiantSession", identifiantSession);
		model.addAttribute("soumissionProposition", "soumissionProposition");
		model.addAttribute("menuNavigation", "menuNavigation");
        return "utilisateur/accueilUtilisateur";
    }
	
	@Transactional
	@RequestMapping(value = {"/redirectionChoixGuichet" }, method = RequestMethod.GET)
    public String redirectionChoixGuichet(Model model,  HttpSession session) { 		
		String resultat=null;
		try {
			identifiantSession=session.getAttribute("identifiantSession").toString().trim();
		}
		catch(Exception e) {
			resultat="pageErreur";
			return resultat;
		}
		String nomBanque=session.getAttribute("nomSourceCache").toString().trim();		
		BanquePrincipale banquePrincipale=banquePrincipaleRepository.findBanquePrincipaleByNom(nomBanque);
		List<String> nomGuichets=banqueRepository.findNomGuichets(banquePrincipale);
		
		model.addAttribute("nomGuichets", nomGuichets);
		model.addAttribute("cheminAccueil",  "Accueil >");
		model.addAttribute("cheminSoumission",  "Soumission >");
		model.addAttribute("cheminAccueil",  "Accueil >");
		model.addAttribute("cheminSoumission",  "Soumission >");
		model.addAttribute("identifiantSession", identifiantSession);
		model.addAttribute("choisirGuichetBanque", "choisirGuichetBanque");
		model.addAttribute("titre", " Soumission de Proposition");
		model.addAttribute("menuNavigation", "menuNavigation");
        return "utilisateur/accueilUtilisateur";
    }
	
	@Transactional
	@RequestMapping(value = {"/envoiCacheGenre" }, method = RequestMethod.POST)
    public String envoiCacheGenre(Model model, @ModelAttribute("sourceDto") SourcePrelevementDto sourceDto, HttpSession session) { 		
		String resultat=null;
		try {
			identifiantSession=session.getAttribute("identifiantSession").toString().trim();
		}
		catch(Exception e) {
			resultat="pageErreur";
			return resultat;
		}
		String genreGroupe="groupe";
		String genreIndividuel="individuel";
		String nomGenre=sourceDto.getNomSource().trim();
		String genreConvention=sourceDto.getGenreConvention().trim();
		Boolean checkedGroupe=false;
		Boolean checkedIndividuel=false;
		String choixGuichet=null;
		
		
		if(nomGenre.equals(genreGroupe)) {
			checkedGroupe=true;
			Boolean estSupprimer=false;
			choixGuichet="pasBon";
			List<String> nomSources=societeRepository.findAllNomSociete(estSupprimer);
			model.addAttribute("checkedGroupe", checkedGroupe);
			model.addAttribute("nomSociete","Nom Société : ");
			model.addAttribute("nomSources", nomSources);
			session.setAttribute("redirectionChoixGuichet", choixGuichet);
//			String indiceSource="societe";
//			session.setAttribute("indiceSource", indiceSource);
			
		}
		if(nomGenre.equals(genreIndividuel)) {
			checkedIndividuel=true;
			Boolean estSupprimer=false;
			choixGuichet="choixGuichet";
			List<String> nomBanques=banquePrincipaleRepository.findNomsBanquePrincipale(estSupprimer);
			model.addAttribute("nomBanquePrincipales", "nomBanquePrincipales");
			model.addAttribute("checkedIndividuel", checkedIndividuel);
			model.addAttribute("nomBanques", nomBanques);
			session.setAttribute("redirectionChoixGuichet", choixGuichet);		
		}	
		
		session.setAttribute("genreConvention",genreConvention);
		model.addAttribute("cheminAccueil",  "Accueil >");
		model.addAttribute("cheminSoumission",  "Soumission >");
		model.addAttribute("choisirSourcePrelevement", "choisirSourcePrelevement");
		model.addAttribute("titre", " Soumission de Proposition");
		model.addAttribute("identifiantSession", identifiantSession);
		model.addAttribute("menuNavigation", "menuNavigation");
        return "utilisateur/accueilUtilisateur";
    }
	
	@Transactional
	@RequestMapping(value = {"/modificationSoumission" } , method = RequestMethod.POST)
    public String confirmationSoumission(Model model , @ModelAttribute("client") Client client , @ModelAttribute("clientDto") ClientDto clientDto ,HttpSession session, HttpServletRequest request) { 
	 
	 String resultat=null;
		try {
			identifiantSession=session.getAttribute("identifiantSession").toString().trim();
		}
		catch(Exception e) {
			resultat="pageErreur";
			return resultat;
		}
		Integer idClient=client.getIdClient();
		Client clientModif=clientRepository.findClientById(idClient);
		session.setAttribute("idClientModif", idClient );
		////////////////////:  Retrouver Banque
		Banque findBanque=clientBanqueRepository.findIdBanqueByIdClient(clientModif);
		Societe findSociete=clientSocieteRepository.findSocieteByIdClient(clientModif);
		
				
		String nomSourceFind=null;
		Boolean estSupprimer=false;
		if(findBanque!=null) {
			model.addAttribute("testIndividuel", "testIndividuel");
			String nomGuichet=findBanque.getNomGuichet();
			System.out.println("   Nom guichet  : "+nomGuichet);
			BanquePrincipale idBanquePrincipale=banqueRepository.findBanquePrincipaleByNomGuichet(nomGuichet);
			nomSourceFind=findBanque.getNomGuichet();		
			List<String> nomSources=banqueRepository.findNomGuichets(idBanquePrincipale);
			model.addAttribute("nomSources",  nomSources);
			model.addAttribute("genreSource"," Numero Compte");
		}
		if(findSociete!=null) {
			model.addAttribute("testGroupe", "testGroupe");
			nomSourceFind=findSociete.getNomSociete();
			List<String> nomSources=societeRepository.findAllNomSociete(estSupprimer);
			model.addAttribute("nomSources",  nomSources);
			model.addAttribute("genreSource"," Matricule Client : ");
		}
		
		String identifiant=session.getAttribute("identifiantSession").toString().trim();
		Utilisateur utilisateurRep=utilisateurRepository.findByIdentifiant(identifiant);
		Agence agence=utilisateurRep.getIdAgence();
		List<String> codeAgents=agentRepository.findAllCodeAgents(agence);
		model.addAttribute("codeAgents", codeAgents);
		
		String genreSource=session.getAttribute("genreSource").toString().trim();
//		model.addAttribute("genreSource", genreSource);
		
		///////////////////////////////////::
		String nomSource=session.getAttribute("codeSocieteSoumis").toString().trim(); 
		nomSource=session.getAttribute("nomsourceModif").toString().trim();
		model.addAttribute("nomSource", nomSourceFind);
		///////////////////////////////////////////////
		
		model.addAttribute("client", clientModif);
		
		List<String> planDurees=planRepository.findPlanDurees();
		model.addAttribute("planDurees", planDurees);
		
		
		model.addAttribute("titre", " Soumission de Proposition");
		model.addAttribute("identifiantSession", identifiantSession);
		model.addAttribute("soumissionPropositionFormModif", "soumissionPropositionFormModif");
		model.addAttribute("menuNavigation", "menuNavigation");
		model.addAttribute("cheminAccueil",  "Accueil >");
		model.addAttribute("cheminSoumission",  "Soumission >");
		model.addAttribute("cheminAccueil",  "Accueil >");
		model.addAttribute("cheminSoumission",  "Soumission >");	
		
		
		 int page = 0;
		 int size = 4;
		 if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
	            page = Integer.parseInt(request.getParameter("page")) - 1;
	        }
		 if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
	            size = Integer.parseInt(request.getParameter("size"));
	        }
		 PageRequest pageable = PageRequest.of(page, size);
		 Page<Client> clientPage = clientRepository.findAllClientsPage(estSupprimer, pageable);
		 model.addAttribute("clientsPage", clientPage);
		 
		 
		List<Client> soumissions=clientRepository.findAllClients(estSupprimer);
		model.addAttribute("soumissions", soumissions);
		model.addAttribute("listeSoumission", "listeSoumission");
		model.addAttribute("identifiantSession", identifiantSession);
		model.addAttribute("titre", " Soumission de Proposition");
		model.addAttribute("identifiantSession", identifiantSession);
		model.addAttribute("menuNavigation", "menuNavigation");
        return "utilisateur/accueilUtilisateur";
    }
	
	@Transactional
	@RequestMapping(value = {"/resultatAjoutProposition" } , method = RequestMethod.POST)
    public String resultaAjoutProposition(Model model, @ModelAttribute("client") Client client, @ModelAttribute("clientDto") ClientDto clientDto , @ModelAttribute("clientPlan") ClientPlan clientPlan , @ModelAttribute("clientBanque") ClientBanque clientBanque , @ModelAttribute("clientSociete") ClientSociete clientSociete  ,HttpSession session, HttpServletRequest request) { 
	 
		String resultat=null;
		try {
			identifiantSession=session.getAttribute("identifiantSession").toString().trim();
		}
		catch(Exception e) {
			resultat="pageErreur";
			return resultat;
		}
				
		String planDuree=clientDto.getPlanDuree().trim();
		String nomAssure=clientDto.getNomAssure().trim();
		String nomClient=clientDto.getNomClient().trim();
		String numero=clientDto.getNumero().trim();
		String periodicite=clientDto.getPeriodicite().trim();
		Long couverture=clientDto.getCouverture();
		String genreAssure=clientDto.getGenreAssure().trim();
		Long prime=clientDto.getPrime();
		String datePrelevement=clientDto.getDatePrelevement();
		String dateSoumission=clientDto.getDateSoumission();	
		String dateNaissance=clientDto.getDateNaissance();
		String dateRealisation=clientDto.getDateRealisation();
		String matriculeClient=clientDto.getMatriculeClient();
		String banque=clientDto.getBanque().trim();
		String profession=clientDto.getProfession().trim();
		String employeur=clientDto.getEmployeur().trim();
		String ville=clientDto.getVille().trim();
		String adressePostal=clientDto.getAdressPostal().trim();
		String codeAgent=clientDto.getCodeAgent().trim();
		String telephone1=clientDto.getTelephone1().trim();
		String telephone2=clientDto.getTelephone2().trim();	
		String nomComPreContrat=clientDto.getNomComPreContrat().trim();
		
		//////////////////////////:Police
		String police=session.getAttribute("police").toString().trim();
		police=police.concat(numero);
		model.addAttribute("police", police);
		session.setAttribute("police", police);
			
		session.setAttribute("planDuree", planDuree);
		session.setAttribute("nomAssure", nomAssure);
		session.setAttribute("genreAssure", genreAssure);
		session.setAttribute("nomClient", nomClient);
		session.setAttribute("numero", numero);
		session.setAttribute("periodicite", periodicite);
		session.setAttribute("couverture", couverture);
		session.setAttribute("prime", prime);
		session.setAttribute("datePrelevement", datePrelevement);
		session.setAttribute("dateSoumission", dateSoumission);
		session.setAttribute("dateNaissance", dateNaissance);
		session.setAttribute("matriculeClient", matriculeClient);
		session.setAttribute("banque", banque);
		session.setAttribute("profession", profession);
		session.setAttribute("employeur", employeur);
		session.setAttribute("ville", ville);
		session.setAttribute("adressPostal", adressePostal);
		session.setAttribute("codeAgent", codeAgent);
		session.setAttribute("telephone1", telephone1);
		session.setAttribute("telephone2", telephone2);
		session.setAttribute("nomComPreContrat", nomComPreContrat);
		session.setAttribute("dateRealisation", dateRealisation);
		
		
		String nomSource=session.getAttribute("nomSourceCache").toString().trim();
		String indiceSource=session.getAttribute("indiceSource").toString().trim();
			
		
		Boolean estSupprimer=false;
		model.addAttribute("cheminAccueil",  "Accueil >");
		model.addAttribute("cheminSoumission",  "Soumission >");
		
		client.setNomAssure(nomAssure);
		client.setNomClient(nomClient);
		client.setNumero(numero);
		client.setPeriodicite(periodicite);
		client.setCouverture(couverture);
		client.setGenreAssure(genreAssure);
		client.setPrime(prime);
		client.setDatePrelevement(datePrelevement);
		client.setDateSoumission(dateSoumission);
		client.setDateNaissance(dateNaissance);
		client.setMatriculeClient(matriculeClient);
		client.setProfession(profession);
		client.setEmployeur(employeur);
		client.setVille(ville);
		client.setAdressePostale(adressePostal);
		client.setTellephone1(telephone1);
		client.setTellephone2(telephone2);
		client.setNomComPreContrat(nomComPreContrat);
		client.setEstSupprimer(estSupprimer);
		client.setDateCreation(new Date());
		client.setDateRealisation(dateRealisation);
		
		Agent agent=agentRepository.findAgentByCodeAgent(codeAgent);
		client.setIdAgent(agent); session.setAttribute("nomSourceCache",nomSource);
		
		Plan plan=planRepository.findPlanByPlanDuree(planDuree); 
		
		/////////////////// A voir
		String nomGuichetSoumis=null;
		String nomSocieteSoumis=null;
		try {
			nomGuichetSoumis=session.getAttribute("nomGuichetSoumis").toString().trim();
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
		////////////////// A voir
		try {
			nomSocieteSoumis=session.getAttribute("nomSourceCache").toString().trim();
		
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
		Banque banqueSoumise=banqueRepository.findBanqueByNomGuichet(nomGuichetSoumis);		
		
		Societe societeSoumise=societeRepository.findSocieteByNom(nomSocieteSoumis);
		
		Client clientReturn=clientRepository.save(client);
		
		clientPlan.setIdClient(clientReturn);
		clientPlan.setIdPlan(plan);
		clientPlanRepository.save(clientPlan);

		clientSociete.setIdClient(clientReturn);
		clientSociete.setIdSociete(societeSoumise);
		
		if(indiceSource.equals("societe")) {
			clientSocieteRepository.save(clientSociete);
		}
		
		clientBanque.setIdBanque(banqueSoumise);
		clientBanque.setIdClient(clientReturn);
		
		if(indiceSource.equals("banque")) {
			clientBanqueRepository.save(clientBanque);
		}
		
//		List<Client> soumissions=clientRepository.findAllClients(estSupprimer);
//		model.addAttribute("soumissions", soumissions);
		
		
		
		
		model.addAttribute("identifiantSession", identifiantSession);
		model.addAttribute("titre", " Soumission de Proposition");
		model.addAttribute("identifiantSession", identifiantSession);
		model.addAttribute("menuNavigation", "menuNavigation");

		model.addAttribute("resultatAjoutProposition", "resultatAjoutProposition");
		
		int page = 0;
		 int size = 4;
		 if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
	            page = Integer.parseInt(request.getParameter("page")) - 1;
	        }
		 if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
	            size = Integer.parseInt(request.getParameter("size"));
	        }
		 PageRequest pageable = PageRequest.of(page, 4);
		 model.addAttribute("clientsPage", clientRepository.findAllClientsPage(estSupprimer, pageable));
		 model.addAttribute("listeSoumission", "listeSoumission");
        return "utilisateur/accueilUtilisateur";
    }	
	
	
	@Transactional
	@RequestMapping(value = {"/resultatAjoutPropositionModif" } , method = RequestMethod.POST)
    public String resultaAjoutPropositionModif(Model model, @ModelAttribute("client") Client client, @ModelAttribute("clientDto") ClientDto clientDto , @ModelAttribute("clientPlan") ClientPlan clientPlan , @ModelAttribute("clientBanque") ClientBanque clientBanque , @ModelAttribute("clientSociete") ClientSociete clientSociete  ,HttpSession session, HttpServletRequest request) { 
	 
		String resultat=null;
		try {
			identifiantSession=session.getAttribute("identifiantSession").toString().trim();
		}
		catch(Exception e) {
			resultat="pageErreur";
			return resultat;
		}
		
		String idClient=session.getAttribute("idClientModif").toString().trim();
		Integer idClientModif=Integer.valueOf(idClient);
		
		Client clientModif=clientRepository.findClientById(idClientModif);
		String nomC=clientModif.getNomClient();
		Integer idC=clientModif.getIdClient();
		
		String planDuree=clientDto.getPlanDuree().trim();
		String nomAssure=clientDto.getNomAssure().trim();
		String nomClient=clientDto.getNomClient().trim();
		String numero=clientDto.getNumero().trim();
		String periodicite=clientDto.getPeriodicite().trim();
		Long couverture=clientDto.getCouverture();
		String genreAssure=clientDto.getGenreAssure().trim();
		Long prime=clientDto.getPrime();
		String datePrelevement=clientDto.getDatePrelevement();
		String dateSoumission=clientDto.getDateSoumission();	
		String dateNaissance=clientDto.getDateNaissance();
		String matriculeClient=clientDto.getMatriculeClient();
		String banque=clientDto.getBanque().trim();
		String profession=clientDto.getProfession().trim();
		String employeur=clientDto.getEmployeur().trim();
		String ville=clientDto.getVille().trim();
		String adressePostal=clientDto.getAdressPostal().trim();
		String codeAgent=clientDto.getCodeAgent().trim();
		String telephone1=clientDto.getTelephone1().trim();
		String telephone2=clientDto.getTelephone2().trim();	
		String nomComPreContrat=clientDto.getNomComPreContrat().trim();
		clientModif.setNomAssure(nomAssure);
		clientModif.setNomClient(nomClient);
		clientModif.setNumero(numero);
		clientModif.setPeriodicite(periodicite);
		clientModif.setCouverture(couverture);
		clientModif.setGenreAssure(genreAssure);
		clientModif.setPrime(prime);
		clientModif.setDatePrelevement(datePrelevement);
		clientModif.setDateSoumission(dateSoumission);
		clientModif.setDateNaissance(dateNaissance);
		clientModif.setMatriculeClient(matriculeClient);
		clientModif.setProfession(profession);
		clientModif.setEmployeur(employeur);
		clientModif.setVille(ville);
		clientModif.setAdressePostale(adressePostal);
		clientModif.setTellephone1(telephone1);
		clientModif.setTellephone2(telephone2);
		clientModif.setNomComPreContrat(nomComPreContrat);
		clientModif.setDateCreation(new Date());
		
		Client clientReturn=clientRepository.save(clientModif);
		
		
		//// ClientPlan
		clientPlan=clientPlanRepository.findClientPlanByIdClient(clientModif);
		clientPlan.setIdClient(clientReturn);
		Plan plan=planRepository.findPlanByPlanDuree(planDuree);
		clientPlan.setIdPlan(plan);
		System.out.println("   Id Client : "+plan.getIdPlan());
		ClientPlan cp=clientPlanRepository.save(clientPlan);	
		////	ClientBanque
		clientBanque=clientBanqueRepository.findBanqueByIdClient(clientModif);
		if(clientBanque!=null) {
					clientBanque.setIdClient(clientReturn);
					Banque banqueModif=banqueRepository.findBanqueByNomGuichet(banque);
					clientBanque.setIdBanque(banqueModif);			
					clientBanqueRepository.save(clientBanque);				
		}
					
		////// ClientSociete
		clientSociete=clientSocieteRepository.findSocieteByClient(clientModif);
		if(clientSociete!=null) {
			clientSociete.setIdClient(clientReturn);
			Societe societeModif=societeRepository.findSocieteByNom(banque);
			clientSociete.setIdSociete(societeModif);		
			clientSocieteRepository.save(clientSociete);
		}
		
		/////////	Agent
		Agent agent=agentRepository.findAgentByCodeAgent(codeAgent);
		clientModif.setIdAgent(agent);
		clientModif=clientRepository.save(clientModif);
		
			
		//////////////////////////:Police
		String police=session.getAttribute("police").toString().trim();
		police=police.concat(numero);
		model.addAttribute("police", police);
		session.setAttribute("police", police);
			
		
		String nomSource=session.getAttribute("nomSourceCache").toString().trim();
		String indiceSource=session.getAttribute("indiceSource").toString().trim();
		
		int page = 0;
		 int size = 4;
		 if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
	            page = Integer.parseInt(request.getParameter("page")) - 1;
	        }
		 if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
	            size = Integer.parseInt(request.getParameter("size"));
	        }
		 Boolean estSupprimer=false;
	     PageRequest pageable = PageRequest.of(page, 4);
		 Page<Client> clientPage = clientRepository.findAllClientsPage(estSupprimer, pageable);
//		 clientPage = clientRepository.findAllClientsPage(estSupprimer, pageable);
		 model.addAttribute("clientsPage", clientPage);
		
		
		 	 
		model.addAttribute("cheminAccueil",  "Accueil >");
		model.addAttribute("cheminSoumission",  "Soumission >");
		
		
//		Agent agent=agentRepository.findAgentByCodeAgent(codeAgent);
		client.setIdAgent(agent); session.setAttribute("nomSourceCache",nomSource);
		
//		Plan plan=planRepository.findPlanByPlanDuree(planDuree); 
		
		
		
//		List<Client> soumissions=clientRepository.findAllClients(estSupprimer);
//		model.addAttribute("soumissions", soumissions);
		
		
		model.addAttribute("listeSoumission", "listeSoumission");
		
		model.addAttribute("identifiantSession", identifiantSession);
		model.addAttribute("titre", " Soumission de Proposition");
		model.addAttribute("identifiantSession", identifiantSession);
		model.addAttribute("menuNavigation", "menuNavigation");

		model.addAttribute("resultatAjoutPropositionModif", "resultatAjoutPropositionModif");
        return "utilisateur/accueilUtilisateur";
    }	
	
		@Transactional
		@RequestMapping(value = "/clientsPage")
	    public ModelAndView listArticlesPageByPage(@RequestParam(name="page", defaultValue="0") int page,@RequestParam(name="size", defaultValue="10") int size,  @ModelAttribute("client") Client client, Model model, HttpSession session, HttpServletRequest request) {
	        ModelAndView modelAndView = new ModelAndView("utilisateur/accueilUtilisateur");
	        Boolean estSupprimer=false;
	        PageRequest pageable = PageRequest.of(page-1, 4);
	        Page<Client> clientPage = clientRepository.findAllClientsPage(estSupprimer, pageable);
 
			 if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
		            page = Integer.parseInt(request.getParameter("page")) - 1;
		        }
			 if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
		            size = Integer.parseInt(request.getParameter("size"));
		        }
		    clientPage = clientRepository.findAllClientsPage(estSupprimer, pageable);
			model.addAttribute("clientsPage", clientPage);
					
			model.addAttribute("cheminAccueil",  "Accueil >");
			model.addAttribute("cheminSoumission",  "Soumission >");		
			model.addAttribute("listeSoumission", "listeSoumission");
	        model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("titre", " Soumission de Proposition");
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("menuNavigation", "menuNavigation");
	        return modelAndView;
	    }
	
	
}
