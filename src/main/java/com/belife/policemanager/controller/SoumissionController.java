package com.belife.policemanager.controller;


import java.text.SimpleDateFormat;
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
import com.belife.policemanager.model.entity.Sequence;
import com.belife.policemanager.model.entity.Societe;
import com.belife.policemanager.model.entity.Utilisateur;
import com.belife.policemanager.model.repository.AgenceRepository;
import com.belife.policemanager.model.repository.AgentRepository;
import com.belife.policemanager.model.repository.BanquePrincipaleRepository;
import com.belife.policemanager.model.repository.BanqueRepository;
import com.belife.policemanager.model.repository.ClientBanqueRepository;
import com.belife.policemanager.model.repository.ClientPlanRepository;
import com.belife.policemanager.model.repository.ClientRepository;
import com.belife.policemanager.model.repository.ClientSocieteRepository;
import com.belife.policemanager.model.repository.PlanRepository;
import com.belife.policemanager.model.repository.RolesRepository;
import com.belife.policemanager.model.repository.SequencePoliceRepository;
import com.belife.policemanager.model.repository.SequenceRepository;
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
     AgenceRepository agenceRepository;
	 
	 @Autowired
     ClientPlanRepository clientPlanRepository;
	 
	 @Autowired
     ClientBanqueRepository clientBanqueRepository;
	 
	 @Autowired
     ClientSocieteRepository clientSocieteRepository;
	 
	 @Autowired
     SequenceRepository sequenceRepository;
	 
	 @Autowired
     SequencePoliceRepository sequencePoliceRepository;
	 
	 
	 
	 
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
	@RequestMapping(value = {"/soumissionProposition@@Individuel" }, method = RequestMethod.GET)
    public String soumissionPropositionDeux(Model model, @ModelAttribute("banqueDto") BanqueDto banqueDto, HttpSession session, @PageableDefault(size = 20) Pageable pageable, HttpServletRequest request) { 		
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
		String nomAgenceActif=session.getAttribute("nomAgenceActif").toString().trim();
		Agence agenceActive=agenceRepository.findAgenceByNomDirect(nomAgenceActif);
				
		int page = 0;
		int size = 20;
		 if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
	            page = Integer.parseInt(request.getParameter("page")) - 1;
	        }
		 if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
	            size = Integer.parseInt(request.getParameter("size"));
	        }
		 
		 Boolean estSupprimer=false;
		 pageable = PageRequest.of(page, size);
	     Page<Client> clientPage = clientRepository.findAllClientsPage(estSupprimer,agenceActive , pageable);
		 model.addAttribute("clientsPage", clientPage);
		 
		 session.removeAttribute("testGroupe");
		
		List<String> nomSources=societeRepository.findAllNomSociete(estSupprimer);
		model.addAttribute("nomSources",  nomSources);
		String nomGuichet=session.getAttribute("nomGuichet").toString().trim();
		nomSource=session.getAttribute("nomSourceCache").toString().trim();
		model.addAttribute("nomSource",  nomSource);		
		model.addAttribute("testIndividuel", "testIndividuel");
		session.setAttribute("testIndividuel", "testIndividuel");
		
		Date aujourdhui = new Date();
		String pattern = "dd/MM/yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String dateSoumi = simpleDateFormat.format(new Date());
		
		String planDuree=null;
		String nomAssure=null;
		String genreAssure=null;
		String nomClient=null;
		String numero=null;
		String periodicite=null;
		String couverture=null;
		String prime=null;
		String datePrelevement=null;
		String dateSoumission=null;
		String dateNaissance=null;
		String matriculeClient=null;
		String banque=null;
		String profession=null;
		String employeur=null;
		String ville=null;
		String adressPostal=null;
		String codeAgent=null;
		String telephone1=null;
		String telephone2=null;
		String nomComPreContrat=null;
		String dateRealisation=null;
		
		
		 planDuree=session.getAttribute("planDuree").toString().trim();
		 nomAssure=session.getAttribute("nomAssure").toString().trim();
		 genreAssure=session.getAttribute("genreAssure").toString().trim();
		 nomClient=session.getAttribute("nomClient").toString().trim();
		 numero=session.getAttribute("numero").toString().trim();
		 periodicite=session.getAttribute("periodicite").toString().trim();
		 couverture=session.getAttribute("couverture").toString().trim();
		 prime=session.getAttribute("prime").toString().trim();
		 datePrelevement=session.getAttribute("datePrelevement").toString().trim();
		 dateSoumission=session.getAttribute("dateSoumission").toString().trim();
		 dateNaissance=session.getAttribute("dateNaissance").toString().trim();
		 matriculeClient=session.getAttribute("matriculeClient").toString().trim();
		 banque=session.getAttribute("banque").toString().trim();
		 profession=session.getAttribute("profession").toString().trim();
	     employeur=session.getAttribute("employeur").toString().trim();
		 ville=session.getAttribute("ville").toString().trim();
		 adressPostal=session.getAttribute("adressPostal").toString().trim();
		 codeAgent=session.getAttribute("codeAgent").toString().trim();
		 telephone1=session.getAttribute("telephone1").toString().trim();
		 telephone2=session.getAttribute("telephone2").toString().trim();
		 nomComPreContrat=session.getAttribute("nomComPreContrat").toString().trim();
		 dateRealisation=session.getAttribute("dateRealisation").toString().trim();
			
			model.addAttribute("planDuree", planDuree);
			model.addAttribute("nomAssure", nomAssure);
			model.addAttribute("genreAssure", genreAssure);
			model.addAttribute("nomClient", nomClient);
			model.addAttribute("numero", numero);
			model.addAttribute("periodicite", periodicite);
			model.addAttribute("couverture", couverture);
			model.addAttribute("prime", prime);
			model.addAttribute("datePrelevement", datePrelevement);
			model.addAttribute("dateNaissance", dateNaissance);
			model.addAttribute("matriculeClient", matriculeClient);
			model.addAttribute("banque",banque);
			model.addAttribute("profession", profession);
			model.addAttribute("employeur", employeur);
			model.addAttribute("ville", ville);
			model.addAttribute("adressPostal", adressPostal);
			model.addAttribute("codeAgent", codeAgent);
			model.addAttribute("telephone1",telephone1);
			model.addAttribute("telephone2", telephone2);
			model.addAttribute("nomComPreContrat", nomComPreContrat);
			model.addAttribute("dateRealisation", dateRealisation);
			model.addAttribute("dateSoumission",dateSoumi);
		
		///////////////////////// 	//////////////////// Données à soumettre
		session.setAttribute("nomGuichetSoumis", nomGuichet);
		session.setAttribute("nomBanqueSoumis", nomSource);
		String nomGuichetSoumis=session.getAttribute("nomGuichetSoumis").toString().trim();
		String nomBanqueSoumis=session.getAttribute("nomBanqueSoumis").toString().trim();
		String codeGuichetSoumis=banqueRepository.findCodeGuichetByNomGuichet(nomGuichetSoumis);
		String codeBanqueSoumis=banquePrincipaleRepository.findCodeBanquePrincipale(nomBanqueSoumis);
		String police=codeBanqueSoumis.concat(codeGuichetSoumis);
		
		model.addAttribute("codeGuichetSoumis",codeGuichetSoumis);
		session.setAttribute("codeGuichet", codeGuichetSoumis);
		
		model.addAttribute("banque",nomGuichetSoumis);
		
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
    public String soumissionProposition(Model model, @ModelAttribute("sourceDto") SourcePrelevementDto sourceDto, HttpSession session, @PageableDefault(size = 20) Pageable pageable, HttpServletRequest request) { 		
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
		 String nomAgenceActif=session.getAttribute("nomAgenceActif").toString().trim();
		 Agence agenceActive=agenceRepository.findAgenceByNomDirect(nomAgenceActif);
		
		 
	 
		 int page = 0;
		 int size = 20;
		 if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
	            page = Integer.parseInt(request.getParameter("page")) - 1;
	        }
		 if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
	            size = Integer.parseInt(request.getParameter("size"));
	        }
		 
		 Boolean estSupprimer=false;
		 pageable = PageRequest.of(0, size);
	     Page<Client> clientPage = clientRepository.findAllClientsPage(estSupprimer,agenceActive , pageable);
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
			model.addAttribute("genreSource", " Numero Société * : ");
			session.setAttribute("genreSource", " Numero Société * : ");
			model.addAttribute("testGroupe", "testGroupe");
		    session.setAttribute("police", codeSocieteSoumis);
			
		}	
		
		String pattern = "dd/MM/yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String dateSoumi = simpleDateFormat.format(new Date());
		model.addAttribute("dateSoumission", dateSoumi);	
			
		session.setAttribute("testGroupe", "testGroupe");
		session.removeAttribute("testIndividuel");
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
		session.setAttribute("codeSociete",codeSociete);
		model.addAttribute("codeSociete", codeSociete);
		
	
		String indiceSource="societe";
		session.setAttribute("indiceSource", indiceSource);
		
		List<String> planDurees=planRepository.findPlanDurees();
		model.addAttribute("cheminAccueil",  "Accueil >");
		model.addAttribute("cheminSoumission",  "Soumission >");
		model.addAttribute("planDurees", planDurees);
		model.addAttribute("banque",  nomSource);
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
		
		
		model.addAttribute("nomGuichets",  nomGuichets);
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
	@RequestMapping(value = {"/redirectionSuppressionSessionChoixGuichet" }, method = RequestMethod.POST )
    public String redirectionSuppressionSessionChoixGuichet(Model model,  @ModelAttribute("banqueDto") BanqueDto banqueDto ,HttpSession session) { 		
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
		session.setAttribute("nomGuichets", nomGuichets);
		String nomGuichet=banqueDto.getNomGuichet().trim();
		
		session.setAttribute("nomGuichet",nomGuichet);	
		session.setAttribute("planDuree"," ");
		session.setAttribute("nomAssure"," ");
		session.setAttribute("genreAssure"," ");
		session.setAttribute("nomClient"," ");
		session.setAttribute("numero"," ");
		session.setAttribute("periodicite"," ");
		session.setAttribute("couverture"," ");
		session.setAttribute("prime"," ");
		session.setAttribute("datePrelevement"," ");
		session.setAttribute("dateSoumission"," ");
		session.setAttribute("dateNaissance"," ");
		session.setAttribute("matriculeClient"," ");
		session.setAttribute("banque"," ");
		session.setAttribute("profession"," ");
		session.setAttribute("employeur"," ");
		session.setAttribute("ville"," ");
		session.setAttribute("adressPostal"," ");
		session.setAttribute("codeAgent"," ");
		session.setAttribute("telephone1"," ");
		session.setAttribute("telephone2"," ");
		session.setAttribute("nomComPreContrat"," ");
		session.setAttribute("dateRealisation"," ");
		
		model.addAttribute("cheminAccueil",  "Accueil >");
		model.addAttribute("cheminSoumission",  "Soumission >");
		model.addAttribute("cheminAccueil",  "Accueil >");
		model.addAttribute("cheminSoumission",  "Soumission >");
		model.addAttribute("identifiantSession", identifiantSession);
		model.addAttribute("choisirGuichetBanque", "choisirGuichetBanque");
		model.addAttribute("titre", " Soumission de Proposition");
		model.addAttribute("menuNavigation", "menuNavigation");	
		
		return "redirect:/soumissionProposition@@Individuel";
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
		String nomAgenceActif=session.getAttribute("nomAgenceActif").toString().trim();
		 Agence agenceActive=agenceRepository.findAgenceByNomDirect(nomAgenceActif);
			
				
		String nomSourceFind=null;
		Boolean estSupprimer=false;
		if(findBanque!=null) {
			model.addAttribute("testIndividuel", "testIndividuel");
			String nomGuichet=findBanque.getNomGuichet();
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
			model.addAttribute("genreSource"," Code Société : ");
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
		model.addAttribute("cheminModificationSoumission",  "Modification informations >");
		
		 int page = 0;
		 int size = 20;
		 if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
	            page = Integer.parseInt(request.getParameter("page")) - 1;
	        }
		 if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
	            size = Integer.parseInt(request.getParameter("size"));
	        }
		 PageRequest pageable = PageRequest.of(page, size);
		 Page<Client> clientPage = clientRepository.findAllClientsPage(estSupprimer,agenceActive, pageable);
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
	 private String renvoyerDernierNumeroPoliceSequence(String nomAgenceActif) {
		 
		 Sequence objetSequence=renvoyerDerniereSequence( nomAgenceActif );
		 String sequence=objetSequence.getSeq();
		 
		 List<String> numeroPoliceListSoumis=clientRepository.findAllNumeroPolicesByIdSequence(objetSequence);
		 	  
		 String sequencePoliceOrdreObjet=null;
		 if(numeroPoliceListSoumis.isEmpty()) {
			 String ordre="0000";
			 sequencePoliceOrdreObjet=ordre;			 
		 }
		 
		 if(!numeroPoliceListSoumis.isEmpty()) {
			
			 String dernierNumeroPolice=numeroPoliceListSoumis.get(0);	
			 	 
			 
			 Integer beginIndex=3;
			 String ordre=dernierNumeroPolice.substring(beginIndex);
			 Integer ordreEntier=Integer.valueOf(ordre);
			 ordreEntier++;	 
			
			 if(ordreEntier>0 && ordreEntier<10) {
				 String ordreZero="000";		 
				 ordre=Integer.toString(ordreEntier);
				 ordre=ordreZero.concat(ordre);	
				 sequencePoliceOrdreObjet=ordre;			
			 }
			 if(ordreEntier>=10 && ordreEntier<100) {
				 String ordreZero="00";
				 ordre=Integer.toString(ordreEntier);
				 ordre=ordreZero.concat(ordre);	
				 sequencePoliceOrdreObjet=ordre;			 
			 }
			 if(ordreEntier>=100 && ordreEntier<999) {
				 String ordreZero="0";
				 ordre=Integer.toString(ordreEntier);
				 ordre=ordreZero.concat(ordre);	
				 sequencePoliceOrdreObjet=ordre;		 
			 }
			 if(ordreEntier>1000 && ordreEntier<9999) {
				 ordreEntier++;
				 ordre=Integer.toString(ordreEntier);	
				 sequencePoliceOrdreObjet=ordre;			 
			 }
			 
		 }	
		 sequencePoliceOrdreObjet=sequence.concat(sequencePoliceOrdreObjet);
		 return sequencePoliceOrdreObjet;
	 	}
	 
	 @Transactional
	 private String positionnerDernierNumeroPolice(String nomAgenceActif, String dernierNumeroPolice) {
		 
		 Sequence objetSequence=renvoyerDerniereSequence( nomAgenceActif );
		 String sequence=objetSequence.getSeq();
		 	 	  
		 String sequencePoliceOrdreObjet=null;	 
			 
			 Integer beginIndex=3;
			 String ordre=dernierNumeroPolice.substring(beginIndex);
			 Integer ordreEntier=Integer.valueOf(ordre);
			 ordreEntier++;	 
			
			 if(ordreEntier>0 && ordreEntier<10) {
				 String ordreZero="000";		 
				 ordre=Integer.toString(ordreEntier);
				 ordre=ordreZero.concat(ordre);	
				 sequencePoliceOrdreObjet=ordre;			
			 }
			 if(ordreEntier>=10 && ordreEntier<100) {
				 String ordreZero="00";
				 ordre=Integer.toString(ordreEntier);
				 ordre=ordreZero.concat(ordre);	
				 sequencePoliceOrdreObjet=ordre;			 
			 }
			 if(ordreEntier>=100 && ordreEntier<999) {
				 String ordreZero="0";
				 ordre=Integer.toString(ordreEntier);
				 ordre=ordreZero.concat(ordre);	
				 sequencePoliceOrdreObjet=ordre;		 
			 }
			 if(ordreEntier>1000 && ordreEntier<9999) {
				 ordreEntier++;
				 ordre=Integer.toString(ordreEntier);	
				 sequencePoliceOrdreObjet=ordre;			 
			 }
			 
		 sequencePoliceOrdreObjet=sequence.concat(sequencePoliceOrdreObjet);
		 return sequencePoliceOrdreObjet;
	 	}
	
	
//	Renvoyer la derniere sesquence
	@Transactional
	 private Sequence renvoyerDerniereSequence(String nomAgenceActif ) {
		Agence agenceActive=agenceRepository.findAgenceByNomDirect(nomAgenceActif);
		List<Sequence> sequencesAgence=sequenceRepository.findSequenceListeByIdSequence(agenceActive);
		Sequence sequenceAgence=sequencesAgence.get(0);	
		return sequenceAgence;
	}
	
	
	@Transactional
	@RequestMapping(value = {"/voirSoumission" } , method = RequestMethod.POST)
    public String voirSoumission(Model model, @ModelAttribute("client") Client client, @ModelAttribute("clientDto") ClientDto clientDto , @ModelAttribute("clientPlan") ClientPlan clientPlan , @ModelAttribute("clientBanque") ClientBanque clientBanque , @ModelAttribute("clientSociete") ClientSociete clientSociete  ,HttpSession session, HttpServletRequest request) { 
	 
		String resultat=null;
		try {
			identifiantSession=session.getAttribute("identifiantSession").toString().trim();
		}
		catch(Exception e) {
			resultat="pageErreur";
			return resultat;
		}
		Integer idClient=client.getIdClient();
		Client clientAfficher=clientRepository.findClientById(idClient);
		session.setAttribute("idClientModif", idClient );
		////////////////////:  Retrouver Banque
		Banque findBanque=clientBanqueRepository.findIdBanqueByIdClient(clientAfficher);
		Societe findSociete=clientSocieteRepository.findSocieteByIdClient(clientAfficher);
		String codeAgent=clientAfficher.getIdAgent().getCodeAgent();
		String nomAgence=clientAfficher.getIdAgence().getNomDirect();
		model.addAttribute("codeAgent", codeAgent);
		model.addAttribute("nomAgence", nomAgence);
		
		String nomBanque=null;
		String nomSociete=null;
		String codeGuichet=null;
		String codeSociete=null;
		if(findBanque!=null) {
			model.addAttribute("testIndividuel",  "testIndividuel");
			nomBanque=findBanque.getNomGuichet();
			codeGuichet=findBanque.getCodeGuichet();
			model.addAttribute("codeGuichet",  codeGuichet);
			model.addAttribute("nomGuichet",  nomBanque);
		}
		if(findSociete!=null) {
			model.addAttribute("testGroupe",  "testGroupe");
			nomSociete=findSociete.getNomSociete();
			codeSociete=findSociete.getCodeSociete();
			model.addAttribute("nomSociete", nomSociete);
		}
		 boolean estSupprimer=false;
		 int page = 0;
		 int size = 20;
		 if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
	            page = Integer.parseInt(request.getParameter("page")) - 1;
	        }
		 if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
	            size = Integer.parseInt(request.getParameter("size"));
	        }
		 String nomAgenceActif=session.getAttribute("nomAgenceActif").toString().trim();
		 Agence agenceActive=agenceRepository.findAgenceByNomDirect(nomAgenceActif);
		
		 PageRequest pageable = PageRequest.of(page, size);
		 Page<Client> clientPage = clientRepository.findAllClientsPage(estSupprimer,agenceActive, pageable);
		 model.addAttribute("clientsPage", clientPage);
		
		ClientPlan cp=clientPlanRepository.findClientPlanByIdClient(clientAfficher);
		String produit=cp.getIdPlan().getPlanDuree();
		model.addAttribute("clientAfficher", clientAfficher);
		model.addAttribute("nomBanque", nomBanque);
		model.addAttribute("nomSociete", nomSociete);
		model.addAttribute("produit",produit);
				
		model.addAttribute("cheminAccueil",  "Accueil >");
		model.addAttribute("cheminSoumission",  "Soumission >");
		model.addAttribute("cheminVoirSoumission",  "Voir Soumission >");
		model.addAttribute("listeSoumission", "listeSoumission");
		model.addAttribute("identifiantSession", identifiantSession);
		model.addAttribute("titre", " Soumission de Proposition");
		model.addAttribute("identifiantSession", identifiantSession);
		model.addAttribute("menuNavigation", "menuNavigation");

		model.addAttribute("voirDonneeSoumission", "voirDonneeSoumission");
        return "utilisateur/accueilUtilisateur";
    }	
	
	@Transactional
	@RequestMapping(value = {"/confirmationAjoutProposition" } , method = RequestMethod.POST)
    public String confirmationAjoutProposition(Model model, @ModelAttribute("client") Client client, @ModelAttribute("clientDto") ClientDto clientDto , @ModelAttribute("clientPlan") ClientPlan clientPlan , @ModelAttribute("clientBanque") ClientBanque clientBanque , @ModelAttribute("clientSociete") ClientSociete clientSociete  ,HttpSession session, HttpServletRequest request) { 
	 
		String resultat=null;
		try {
			identifiantSession=session.getAttribute("identifiantSession").toString().trim();
		}
		catch(Exception e) {
			resultat="pageErreur";
			return resultat;
		}
		String propositionSoumise=clientDto.getPropositionSoumise().trim();		
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
		System.out.println("    Banque Premiere "+banque);
		String profession=clientDto.getProfession().trim();
		String employeur=clientDto.getEmployeur().trim();
		String ville=clientDto.getVille().trim();
		String adressePostal=clientDto.getAdressPostal().trim();
		String codeAgent=clientDto.getCodeAgent().trim();
		String telephone1=clientDto.getTelephone1().trim();
		String telephone2=clientDto.getTelephone2().trim();	
		String nomComPreContrat=clientDto.getNomComPreContrat().trim();
		
//		String codeGuichet=session.getAttribute("codeGuichet").toString().trim();
		
		
		
		//////////////////////////:Police
		
		String nomAgenceActif=session.getAttribute("nomAgenceActif").toString().trim();
//		Sequence sequenceAgence=renvoyerDerniereSequence(nomAgenceActif)
		Agence agenceActive=agenceRepository.findAgenceByNomDirect(nomAgenceActif);
		List<Sequence> sequencesAgence=sequenceRepository.findSequenceListeByIdSequence(agenceActive);
	
		if(sequencesAgence.isEmpty()) {
			model.addAttribute("MessageSequenceNonPositionne", "MessageSequenceNonPositionne");
			session.setAttribute("MessageSequenceNonPositionne", "MessageSequenceNonPositionne");
			
			String nomSource=session.getAttribute("nomSourceCache").toString().trim();		
			
			model.addAttribute("cheminAccueil",  "Accueil >");
			model.addAttribute("cheminSoumission",  "Soumission >");
			
			Agent agent=agentRepository.findAgentByCodeAgent(codeAgent);
			client.setIdAgent(agent); session.setAttribute("nomSourceCache",nomSource);
			
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("titre", " Soumission de Proposition");
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("menuNavigation", "menuNavigation");

			return "utilisateur/accueilUtilisateur";
		}
		model.addAttribute("cheminVerificationSoumission",  "Vérification des informations >");
		String numeroPolice=renvoyerDernierNumeroPoliceSequence( nomAgenceActif);
		
		 Integer beginIndex=3;
		 String ordre=numeroPolice.substring(beginIndex);
		 String ordreFinal="10000";
		 Integer ordreEntier=Integer.valueOf(ordre);
		 String sequence=numeroPolice.substring(0,beginIndex);
		if(ordre.equals(ordreFinal)) {
			model.addAttribute("MessageSequenceEpuise", "MessageSequenceEpuise");
			session.setAttribute("MessageSequenceEpuise", "MessageSequenceEpuise");
			model.addAttribute("sequence", sequence);
			String nomSource=session.getAttribute("nomSourceCache").toString().trim();		
			
			model.addAttribute("cheminAccueil",  "Accueil >");
			model.addAttribute("cheminSoumission",  "Soumission >");
			
			Agent agent=agentRepository.findAgentByCodeAgent(codeAgent);
			client.setIdAgent(agent); session.setAttribute("nomSourceCache",nomSource);
			
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("titre", " Soumission de Proposition");
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("menuNavigation", "menuNavigation");

			return "utilisateur/accueilUtilisateur";
		}
		
		model.addAttribute("numeroPolice", numeroPolice);	
		
		if(propositionSoumise.equals("non")) {
//		model.addAttribute("codeGuichet", codeGuichet);
		String nomGuichetSoumis=session.getAttribute("nomGuichetSoumis").toString().trim();
		String codeGuichetSoumis=banqueRepository.findCodeGuichetByNomGuichet(nomGuichetSoumis);
		model.addAttribute("codeGuichet", codeGuichetSoumis);
		model.addAttribute("planDuree", planDuree);
		model.addAttribute("nomAssure", nomAssure);
		model.addAttribute("genreAssure", genreAssure);
		model.addAttribute("nomClient", nomClient);
		model.addAttribute("numero", numero);
		model.addAttribute("periodicite", periodicite);
		model.addAttribute("couverture", couverture);
		model.addAttribute("prime", prime);
		model.addAttribute("datePrelevement", datePrelevement);
		model.addAttribute("dateSoumission", dateSoumission);
		model.addAttribute("dateNaissance", dateNaissance);
		model.addAttribute("matriculeClient", matriculeClient);
		model.addAttribute("banque", banque);
		session.setAttribute("banque", banque);
		model.addAttribute("profession", profession);
		model.addAttribute("employeur",employeur);
		model.addAttribute("ville", ville);
		model.addAttribute("adressPostal", adressePostal);
		model.addAttribute("codeAgent", codeAgent);
		model.addAttribute("telephone1", telephone1);
		model.addAttribute("telephone2", telephone2);
		model.addAttribute("nomComPreContrat", nomComPreContrat);
		model.addAttribute("dateRealisation", dateRealisation);
		}
				
		Boolean choixIndividuelNon=true;
		Boolean choixGroupeNon=true;	
		try {
			String testGroupe=session.getAttribute("testGroupe").toString().trim();
			
		}catch(Exception e) {
			choixGroupeNon=false;
			e.printStackTrace();
		}
		try {
			String testIndividuel=session.getAttribute("testIndividuel").toString().trim();
		}catch(Exception e) {
			choixIndividuelNon=false;
			e.printStackTrace();
		}
		if(choixGroupeNon) {
			String testGroupe=session.getAttribute("testGroupe").toString().trim();
			model.addAttribute("testGroupe", testGroupe);
			model.addAttribute("testEnvoye", testGroupe);
		}
		if(choixIndividuelNon) {
			String testIndividuel=session.getAttribute("testIndividuel").toString().trim();
			model.addAttribute("testIndividuel", testIndividuel);
			model.addAttribute("testEnvoye", testIndividuel);
		}		
		
//		String nomSource=session.getAttribute("nomSourceCache").toString().trim();		
		
		model.addAttribute("cheminAccueil",  "Accueil >");
		model.addAttribute("cheminSoumission",  "Soumission >");
		
		Agent agent=agentRepository.findAgentByCodeAgent(codeAgent);
		client.setIdAgent(agent); 
//		session.setAttribute("nomSourceCache",nomSource);
		
		model.addAttribute("identifiantSession", identifiantSession);
		model.addAttribute("titre", " Soumission de Proposition");
		model.addAttribute("identifiantSession", identifiantSession);
		model.addAttribute("menuNavigation", "menuNavigation");

		model.addAttribute("confirmationDonneeSoumission", "confirmationDonneeSoumission");
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
		String testEnvoye=clientDto.getTestEnvoye().trim();
		String numeroPolice=clientDto.getNumeroPolice().trim();
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
//		String banque=clientDto.getBanque();
		String banque=session.getAttribute("banque").toString().trim();
		String profession=clientDto.getProfession().trim();
		String employeur=clientDto.getEmployeur().trim();
		String ville=clientDto.getVille().trim();
		String adressePostal=clientDto.getAdressPostal().trim();
		String codeAgent=clientDto.getCodeAgent().trim();
		String telephone1=clientDto.getTelephone1().trim();
		String telephone2=clientDto.getTelephone2().trim();	
		String nomComPreContrat=clientDto.getNomComPreContrat().trim();
		String nomAgenceActif=session.getAttribute("nomAgenceActif").toString().trim();
		
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
		client.setDateCreation(new Date());	
		Agent agent=agentRepository.findAgentByCodeAgent(codeAgent);
		client.setIdAgent(agent); 
		Agence agence=agent.getIdAgence();
		client.setIdAgence(agence);
		Sequence sequenceAgence=renvoyerDerniereSequence(nomAgenceActif);
		client.setIdSequence(sequenceAgence);
	    //// ClientPlan
		Plan plan=planRepository.findPlanByPlanDuree(planDuree);
		clientPlan.setIdPlan(plan);
		
		    
	    Client clientExiste=clientRepository.findClientByNumeroPolice(numeroPolice);
	    
	    if( clientExiste==null ) {
	    	client.setNumeroPolice(numeroPolice);
	    	Client clientSave=clientRepository.save(client);
	    	clientPlan.setIdClient(clientSave);
	    	clientPlanRepository.save(clientPlan);
	    	if(testEnvoye.equals("testGroupe")) {
	    		clientSociete.setIdClient(clientSave);
				Societe societeModif=societeRepository.findSocieteByNom(banque);
				clientSociete.setIdSociete(societeModif);		
				clientSocieteRepository.save(clientSociete);
	    	}
	    	
	    	if(testEnvoye.equals("testIndividuel")) {
	    		clientBanque.setIdClient(clientSave);
				Banque banqueModif=banqueRepository.findBanqueByNomGuichet(banque);
				clientBanque.setIdBanque(banqueModif);			
				clientBanqueRepository.save(clientBanque);	
	    	}
	    	
	    }
	    else {		
		    List<String> numeroPoliceListSoumis=clientRepository.findAllNumeroPolicesByIdSequence(sequenceAgence);
		    String dernierNumeroPolice= numeroPoliceListSoumis.get(0);    
		    String numeroPoliceNouveau=positionnerDernierNumeroPolice( nomAgenceActif, dernierNumeroPolice);
		    client.setNumeroPolice(numeroPoliceNouveau);
		   Client clientSave=clientRepository.save(client);	
		    clientPlan.setIdClient(clientSave);
	    	clientPlanRepository.save(clientPlan);
	    }
	    
	    String propositionSoumise="oui";
	    session.setAttribute("propositionSoumise",propositionSoumise);
	    session.setAttribute("planDuree", " ");
		session.setAttribute("nomAssure", " ");
		session.setAttribute("genreAssure", " ");
		session.setAttribute("nomClient", " ");
		session.setAttribute("numero", " ");
		session.setAttribute("periodicite", " ");
		session.setAttribute("couverture", " ");
		session.setAttribute("prime", " ");
		session.setAttribute("datePrelevement", " ");
		session.setAttribute("dateSoumission", " ");
		session.setAttribute("dateNaissance", " ");
		session.setAttribute("matriculeClient", " ");
		session.setAttribute("banque", " ");
		session.setAttribute("profession", " ");
		session.setAttribute("employeur", " ");
		session.setAttribute("ville", " ");
		session.setAttribute("adressPostal", " ");
		session.setAttribute("codeAgent", " ");
		session.setAttribute("telephone1", " ");
		session.setAttribute("telephone2", " ");
		session.setAttribute("nomComPreContrat", " ");
		session.setAttribute("dateRealisation", " ");
		session.setAttribute("numeroPolice", " ");
			
		session.setAttribute("nomSourceCache",nomSource);
			
		model.addAttribute("identifiantSession", identifiantSession);
		model.addAttribute("titre", " Soumission de Proposition");
		model.addAttribute("identifiantSession", identifiantSession);
		model.addAttribute("menuNavigation", "menuNavigation");

		model.addAttribute("resultatAjoutProposition", "resultatAjoutProposition");
		
		int page = 0;
		 int size = 20;
		 if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
	            page = Integer.parseInt(request.getParameter("page")) - 1;
	        }
		 if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
	            size = Integer.parseInt(request.getParameter("size"));
	        }
		 String nomAgenceActive=session.getAttribute("nomAgenceActif").toString().trim();
		 Agence agenceActive=agenceRepository.findAgenceByNomDirect(nomAgenceActive);
		
		 PageRequest pageable = PageRequest.of(page, size);
		 model.addAttribute("clientsPage", clientRepository.findAllClientsPage(estSupprimer,agenceActive, pageable));
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
		Date dc=clientModif.getDateCreation();
		String nomC=clientModif.getNomClient();
		Integer idC=clientModif.getIdClient();
		
		String numeroPolice=clientDto.getNumeroPolice().trim();
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
		
		clientModif.setNumeroPolice(numeroPolice);
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
		clientModif.setDateCreation(dc);
		String nomAgenceActif=session.getAttribute("nomAgenceActif").toString().trim();
		Agent agent=agentRepository.findAgentByCodeAgent(codeAgent);
		client.setIdAgent(agent); 
		Agence agence=agent.getIdAgence();
		client.setIdAgent(agent);
		Sequence sequenceAgence=renvoyerDerniereSequence(nomAgenceActif);
		client.setIdSequence(sequenceAgence);
				
		clientBanque=clientBanqueRepository.findBanqueByIdClient(clientModif);
		Client clientReturn=null;
////	ClientBanque
		if(clientBanque!=null) {
			
			 clientReturn=clientRepository.save(clientModif);	
			
			clientBanque.setIdClient(clientReturn);
			Banque banqueModif=banqueRepository.findBanqueByNomGuichet(banque);
			clientBanque.setIdBanque(banqueModif);			
			ClientBanque cb=clientBanqueRepository.save(clientBanque);		
		}
		
	////// ClientSociete
			clientSociete=clientSocieteRepository.findSocieteByClient(clientModif);
		if(clientSociete!=null) {
		
			numero=societeRepository.findCodeSocieteByNomSociete(banque);
			clientModif.setNumero(numero);
			 clientReturn=clientRepository.save(clientModif);	
			clientSociete.setIdClient(clientReturn);
			Societe societeModif=societeRepository.findSocieteByNom(banque);
			clientSociete.setIdSociete(societeModif);		
			clientSocieteRepository.save(clientSociete);
			}
		
		//// ClientPlan
		clientPlan=clientPlanRepository.findClientPlanByIdClient(clientModif);
		clientPlan.setIdClient(clientReturn);
		Plan plan=planRepository.findPlanByPlanDuree(planDuree);
		clientPlan.setIdPlan(plan);
		ClientPlan cp=clientPlanRepository.save(clientPlan);		
		
		String nomSource=session.getAttribute("nomSourceCache").toString().trim();
		String indiceSource=session.getAttribute("indiceSource").toString().trim();
		
		int page = 0;
		 int size = 20;
		 if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
	            page = Integer.parseInt(request.getParameter("page")) - 1;
	        }
		 if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
	            size = Integer.parseInt(request.getParameter("size"));
	        }
		 Boolean estSupprimer=false;
	     PageRequest pageable = PageRequest.of(page, size);
	     String nomAgenceActive=session.getAttribute("nomAgenceActif").toString().trim();
		 Agence agenceActive=agenceRepository.findAgenceByNomDirect(nomAgenceActive);
		
		 Page<Client> clientPage = clientRepository.findAllClientsPage(estSupprimer, agenceActive, pageable);
		 model.addAttribute("clientsPage", clientPage);
			 	 
		model.addAttribute("cheminAccueil",  "Accueil >");
		model.addAttribute("cheminSoumission",  "Soumission >");
		
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
	    public ModelAndView listArticlesPageByPage(@RequestParam(name="page", defaultValue="0") int page,@RequestParam(name="size", defaultValue="20") int size,  @ModelAttribute("client") Client client, Model model, HttpSession session, HttpServletRequest request) {
	        ModelAndView modelAndView = new ModelAndView("utilisateur/accueilUtilisateur");
	        Boolean estSupprimer=false;
	        String nomAgenceActive=session.getAttribute("nomAgenceActif").toString().trim();
			 Agence agenceActive=agenceRepository.findAgenceByNomDirect(nomAgenceActive);
			
	        PageRequest pageable = PageRequest.of(page-1, size);
	        Page<Client> clientPage = clientRepository.findAllClientsPage(estSupprimer,agenceActive, pageable);
 
			 if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
		            page = Integer.parseInt(request.getParameter("page")) - 1;
		        }
			 if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
		            size = Integer.parseInt(request.getParameter("size"));
		        }
		    clientPage = clientRepository.findAllClientsPage(estSupprimer,agenceActive, pageable);
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
