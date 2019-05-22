package com.belife.policemanager.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.belife.policemanager.model.dto.AgenceBanqueDto;
import com.belife.policemanager.model.dto.BanqueDto;
import com.belife.policemanager.model.dto.ClientDto;
import com.belife.policemanager.model.dto.SourcePrelevementDto;
import com.belife.policemanager.model.entity.Agence;
import com.belife.policemanager.model.entity.AgenceBanque;
import com.belife.policemanager.model.entity.Agent;
import com.belife.policemanager.model.entity.Banque;
import com.belife.policemanager.model.entity.Client;
import com.belife.policemanager.model.entity.ClientBanque;
import com.belife.policemanager.model.entity.ClientPlan;
import com.belife.policemanager.model.entity.ClientSociete;
import com.belife.policemanager.model.entity.Commercial;
import com.belife.policemanager.model.entity.Plan;
import com.belife.policemanager.model.entity.Sequence;
import com.belife.policemanager.model.entity.Societe;
import com.belife.policemanager.model.entity.Utilisateur;
import com.belife.policemanager.model.repository.AgenceBanqueRepository;
import com.belife.policemanager.model.repository.AgenceRepository;
import com.belife.policemanager.model.repository.AgentRepository;
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
     SocieteRepository societeRepository;
	 
	 @Autowired
     PlanRepository planRepository;
	 
	 @Autowired
     ClientRepository clientRepository;
	 
	 @Autowired
     AgentRepository agentRepository;
	 
	 @Autowired
     AgenceBanqueRepository agenceBanqueRepository;
	 
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
	
	
	
////	@@ designe par
//	@Transactional
	@RequestMapping(value = {"/soumissionProposition@@Individuel" }, method = RequestMethod.POST)
    public String soumissionPropositionDeux(Model model, @ModelAttribute("agenceBanqueDto") AgenceBanqueDto agenceBanqueDto, HttpSession session, @PageableDefault(size = 20) Pageable pageable, HttpServletRequest request) { 		
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
		String codeAgenceActive=agenceActive.getCodeAgence();
		int page = 0;
		int size = 20;
		 if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
	            page = Integer.parseInt(request.getParameter("page")) - 1;
	        }
		 if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
	            size = Integer.parseInt(request.getParameter("size"));
	        }
		 
		 String status="A";
		 pageable = PageRequest.of(page, size);
	     Page<Client> clientPage = clientRepository.findAllClientsPage(status,codeAgenceActive , pageable);
		 model.addAttribute("soumissions", clientPage);		 
		 session.removeAttribute("testGroupe");
		 	
		List<String> nomSources=societeRepository.findAllNomSociete(status);
		model.addAttribute("nomSources",  nomSources);
			
		String nomGuichet=agenceBanqueDto.getLibelleAgence().trim();
		
		String[] codeGuichets=nomGuichet.split("-");
		System.out.println(" Code guichets : "+codeGuichets[0]);
		String codeGuichet=codeGuichets[0].trim();
		
		String codeAgenceBanque=agenceBanqueRepository.findCodeAgenceBycodeBanque(codeGuichet);
		
		model.addAttribute("nomGuichetSoumis", nomGuichet);
				
		nomSource=session.getAttribute("nomSourceCache").toString().trim();
		nomGuichet=codeGuichets[1];	
		model.addAttribute("nomSource",  nomSource);		
		model.addAttribute("testIndividuel", "testIndividuel");
		session.setAttribute("testIndividuel", "testIndividuel");
		
		String pattern = "dd/MM/yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String dateSoumission = simpleDateFormat.format(new Date());	

		///////////////////////// 	//////////////////// Données à soumettre
		model.addAttribute("banque",nomGuichet);	
		model.addAttribute("dateSoumission",dateSoumission);
		model.addAttribute("codeGuichetSoumis", codeGuichet);
		model.addAttribute("genreSource", " Numero Compte * : ");
		session.setAttribute("genreSource", " Numero Compte * : " );
		
		String identifiant=session.getAttribute("identifiantSession").toString().trim();
		Utilisateur utilisateurRep=utilisateurRepository.findByIdentifiant(identifiant);
		Agence agence=utilisateurRep.getIdAgence();
		Integer idAgence=agence.getIdAgence();
		String codeAgence=agence.getCodeAgence();
		List<String> nomCommmerciaux=agentRepository.findAllNomAgentByCodeAgence(codeAgence);
//		List<String> codeCommerciaux=agentRepository.findAllCodeAgentByCodeAgence(codeAgence);
		List<String> codeAgents=agentRepository.findAllCodeAgent();
		
		model.addAttribute("codeAgents", codeAgents);

		model.addAttribute("listeSoumission", "listeSoumission");
		List<String> planDurees=planRepository.findPlanDurees();
		model.addAttribute("cheminAccueil",  "Accueil >");
		model.addAttribute("cheminSoumission",  "Soumission >");
		model.addAttribute("planDurees", planDurees);
		model.addAttribute("nomSource",  nomGuichet);
		model.addAttribute("genreConvention",  genreConvention);
		model.addAttribute("titre", " Soumission de Proposition");
		model.addAttribute("identifiantSession", identifiantSession);
		model.addAttribute("soumissionProposition", "soumissionProposition");
		model.addAttribute("menuNavigation", "menuNavigation");
        return "utilisateur/accueilUtilisateur";
    }
	
	
//	@Transactional
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
		 
		 String nomAgenceBelifeActive=session.getAttribute("nomAgenceActif").toString().trim();
		 Agence idAgence=agenceRepository.findAgenceByNomDirect(nomAgenceBelifeActive);
		 String codeAgence=idAgence.getCodeAgence();
	 
		 int page = 0;
		 int size = 20;
		 if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
	            page = Integer.parseInt(request.getParameter("page")) - 1;
	        }
		 
		 if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
	            size = Integer.parseInt(request.getParameter("size"));
	        }
		 
		 String status="A";
		 pageable = PageRequest.of(0, size);
		 
		session.setAttribute("codeSocieteSoumis", nomSource);	
		String codeSocieteSoumis=session.getAttribute("codeSocieteSoumis").toString().trim();
		
		String genreGroupe="groupe";
		String genreIndividuel="individuel";
		
		String redirectionChoixGuichet=session.getAttribute("redirectionChoixGuichet").toString().trim();
		String choixGuichetBanque="choixGuichet";
		if(redirectionChoixGuichet.equals(choixGuichetBanque)) {
			session.setAttribute("nomSourceCache",nomSource);
			String codeBanque=banqueRepository.findCodeBanquePrincipale(nomSource);
			session.setAttribute("codeBanqueCache", codeBanque);
			return "redirect:/redirectionChoixGuichet";
		}
			
		if(genreConvention.equals(genreGroupe)) {
			model.addAttribute("genreSource", " Numero Société * : ");
			session.setAttribute("genreSource", " Numero Société * : ");
			model.addAttribute("testGroupe", "testGroupe");		
			String[] nomSources=nomSource.split("-");
			String codeSource=nomSources[0];
			nomSource=nomSources[1];
		    session.setAttribute("police", codeSource);		
		}	
		
		List<String> nomSources=societeRepository.findAllNomSociete(status);
			
		String pattern = "dd/MM/yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String dateSoumi = simpleDateFormat.format(new Date());
		
		model.addAttribute("banque", nomSource);	
		model.addAttribute("dateSoumission", dateSoumi);		
		session.setAttribute("testGroupe", "testGroupe");
		session.removeAttribute("testIndividuel");
		
		String identifiant=session.getAttribute("identifiantSession").toString().trim();
		Utilisateur utilisateurRep=utilisateurRepository.findByIdentifiant(identifiant);
		Agence agence=utilisateurRep.getIdAgence();
		 codeAgence=agence.getCodeAgence();
		Integer idAgenceUtilisateur=agence.getIdAgence();
		
		List<String> codeAgents=agentRepository.findAllCodeAgent();
		
		
		List<String> nomAgents=agentRepository.findAllNomAgentByCodeAgence(codeAgence);
		List<String> codeAndNomAgents=new ArrayList<String>();
		
		List<String> nomCommmerciaux=agentRepository.findAllNomAgentByCodeAgence(codeAgence);
		List<String> codeCommerciaux=agentRepository.findAllCodeAgentByCodeAgence(codeAgence);
		
		model.addAttribute("codeAgents", codeCommerciaux);
		
		
		model.addAttribute("codeAndNomAgents", codeAndNomAgents);
		Page<Client> clientPage = clientRepository.findAllClientsPage(status,codeAgence, pageable); 
		
//		List<Client> soumissions=clientRepository.findAllClientsPage(status, idAgence, pageable);
		model.addAttribute("soumissions", clientPage);
		model.addAttribute("listeSoumission", "listeSoumission");
		session.setAttribute("nomSourceCache",nomSource);
		
//		/////////////////// Code Société Soumis
		List<String> codesSociete=societeRepository.findCodesSocieteByNomSociete(nomSource, status);
		String codeSociete=codesSociete.get(0);
		session.setAttribute("codeSociete",codeSociete);
		model.addAttribute("codeSociete", codeSociete);
		
	
		String indiceSource="societe";
		session.setAttribute("indiceSource", indiceSource);
		
		List<String> planDurees=planRepository.findPlanDurees();
		model.addAttribute("cheminAccueil",  "Accueil >");
		model.addAttribute("cheminSoumission",  "Soumission >");
		model.addAttribute("planDurees", planDurees);
		model.addAttribute("genreConvention",  genreConvention);
		model.addAttribute("titre", " Soumission de Proposition");
		model.addAttribute("identifiantSession", identifiantSession);
		model.addAttribute("soumissionProposition", "soumissionProposition");
		model.addAttribute("menuNavigation", "menuNavigation");
			
        return "utilisateur/accueilUtilisateur";
    }
	
	
//	@Transactional
	@RequestMapping(value = {"/redirectionChoixGuichet" }, method = RequestMethod.GET)
    public String redirectionChoixGuichet(Model model,  HttpSession session, @ModelAttribute("banqueDto") BanqueDto banqueDto ) { 		
		String resultat=null;
		try {
			identifiantSession=session.getAttribute("identifiantSession").toString().trim();
		}
		catch(Exception e) {
			resultat="pageErreur";
			return resultat;
		}
		String status="A";
		String nomBanque=session.getAttribute("nomSourceCache").toString();	
		String codeBanqueCache=session.getAttribute("codeBanqueCache").toString().trim();
		String codeBanque=banqueRepository.findCodeBanquePrincipale(nomBanque);
		
		List<String> codeAgenceBanques=agenceBanqueRepository.findAllCodeAgenceBycodeBanque(codeBanque);
		List<String> nomAgenceBanques=agenceBanqueRepository.findAllLibelleAgenceBycodeBanque(codeBanque);
		List<String> codeNomAgenceBanque=new ArrayList<String>();
				
		for(int i=0;i<codeAgenceBanques.size();i++) {
			String codeNom=null;
			codeNom=codeAgenceBanques.get(i).concat("-").concat(nomAgenceBanques.get(i));
			codeNomAgenceBanque.add(codeNom);
		}	
		
		model.addAttribute("nomGuichets",  codeNomAgenceBanque);
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
		String status="A";
		
		if(nomGenre.equals(genreGroupe)) {
			checkedGroupe=true;
			choixGuichet="pasBon";
			
			List<String> codeSources=societeRepository.findAllCodeSociete(status);
			List<String> nomSources=societeRepository.findAllNomSociete(status);
			List<String> codeNomSources=new ArrayList<String>();
			
			for(int i=0;i<codeSources.size();i++) {
				if(codeSources.get(i).length()!=0 && nomSources.get(i).length()!=0) {
				    String codeNom=codeSources.get(i).concat("-").concat(nomSources.get(i));
				    codeNomSources.add(codeNom);
				}			
			}
			
			model.addAttribute("checkedGroupe", checkedGroupe);
			model.addAttribute("nomSociete","Nom Société : ");		
			session.setAttribute("redirectionChoixGuichet", choixGuichet);	
			List<String> nomAgenceBanque=agenceBanqueRepository.findAllLibelleAgence();	
			List<String> nomSocietes=societeRepository.findAllNomSociete(status);
			model.addAttribute("nomSources", codeNomSources );
		}
		
		if(nomGenre.equals(genreIndividuel)) {
			checkedIndividuel=true;
			choixGuichet="choixGuichet";
			
			List<String> nomBanques=banqueRepository.findAllNomBanque(status);		
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
	
	
	
	
	////	@Transactional
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
		String status="A";
		Integer idClient=client.getIdClient();
		Client clientModif=clientRepository.findClientById(idClient, status);
		session.setAttribute("idClientModif", idClient );
		////////////////////:  Retrouver Banque
		Banque findBanque=clientBanqueRepository.findIdBanqueByIdClient(clientModif);
		
		String numeroCompte=clientModif.getNumeroCompte();
		String pattern = "dd/MM/yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		
		Societe findSociete=clientSocieteRepository.findSocieteByIdClient(clientModif);
		String nomAgenceActif=session.getAttribute("nomAgenceActif").toString().trim();
		 Agence agenceActive=agenceRepository.findAgenceByNomDirect(nomAgenceActif);
		 
		 String datePrelevement=clientModif.getDatePrelevement();
		 String dateNaissance=clientModif.getDateNaissance();
		 String dateRealisation=clientModif.getDateRealisation();
		 Date datePrelevementEnDate=new Date();
		 
		 try {
			datePrelevementEnDate=simpleDateFormat.parse(datePrelevement);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date dateNaissanceEnDate=new Date();
		try {
			dateNaissanceEnDate=simpleDateFormat.parse(dateNaissance);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date dateRealisationEnDate=new Date();
		try {
			dateRealisationEnDate=simpleDateFormat.parse(dateRealisation);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("datePrelevement",datePrelevementEnDate);
		model.addAttribute("dateNaissance", dateNaissanceEnDate);
		model.addAttribute("dateRealisation", dateRealisationEnDate);		
				
		String nomSourceFind=null;
		if(numeroCompte!=null) {
			model.addAttribute("testIndividuel", "testIndividuel");
			List<String> nomAgenceBanque=agenceBanqueRepository.findAllLibelleAgence();
			model.addAttribute("nomSources", nomAgenceBanque);
		}
		
		if(numeroCompte==null) {
			model.addAttribute("testGroupe", "testGroupe");
			List<String> nomSources=societeRepository.findAllNomSociete(status);
			model.addAttribute("nomSources", nomSources);
			
		}
		
		String identifiant=session.getAttribute("identifiantSession").toString().trim();
		Utilisateur utilisateurRep=utilisateurRepository.findByIdentifiant(identifiant);
		Agence agence=utilisateurRep.getIdAgence();
		String codeAgence=agence.getCodeAgence();
		List<String> codeAgents=agentRepository.findAllCodeAgentByCodeAgence(codeAgence);
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
		 Page<Client> clientPage = clientRepository.findAllClientsPage(status, codeAgence, pageable);
		 model.addAttribute("soumissions", clientPage);
		 
		model.addAttribute("listeSoumission", "listeSoumission");
		model.addAttribute("identifiantSession", identifiantSession);
		model.addAttribute("titre", " Soumission de Proposition");
		model.addAttribute("identifiantSession", identifiantSession);
		model.addAttribute("menuNavigation", "menuNavigation");
        return "utilisateur/accueilUtilisateur";
    }
	
	
	
//	 @Transactional
	 private String renvoyerDernierNumeroPoliceSequence(String nomAgenceActif) {
		 
		 Sequence objetSequence=renvoyerDerniereSequence( nomAgenceActif );
		 String numeroSequence=objetSequence.getSeq();
		 String sequence=objetSequence.getSeq();
		 String status="A";
		 List<String> numeroPoliceListSoumis=clientRepository.findAllNumeroPolicesByIdSequence(numeroSequence,status);
		 	  
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
	 
	
////	Renvoyer la derniere sesquence
//	@Transactional
	 private Sequence renvoyerDerniereSequence(String nomAgenceActif ) {
		Agence agenceActive=agenceRepository.findAgenceByNomDirect(nomAgenceActif);
		List<Sequence> sequencesAgence=sequenceRepository.findSequenceListeByIdSequence(agenceActive);
		Sequence sequenceAgence=sequencesAgence.get(0);	
		return sequenceAgence;
	}
	
	
////	@Transactional
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
		String status="A";
		Integer idClient=client.getIdClient();
		Client clientAfficher=clientRepository.findClientById(idClient,status);
		session.setAttribute("idClientModif", idClient );
		////////////////////:  Retrouver Banque
		
		String testNumeroCompte=clientAfficher.getNumeroCompte();
		
		if(testNumeroCompte!=null) {
			model.addAttribute("testIndividuel",  "testIndividuel");			
			
		}
		if(testNumeroCompte==null) {
			model.addAttribute("testGroupe",  "testGroupe");
			
		}
	
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
		 String codeAgenceActive=agenceActive.getCodeAgence();
		
		 PageRequest pageable = PageRequest.of(page, size);
		 Page<Client> clientPage = clientRepository.findAllClientsPage(status, codeAgenceActive, pageable);
		 model.addAttribute("soumissions", clientPage);
	
		model.addAttribute("clientAfficher", clientAfficher);
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
	
//	@Transactional
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
		String pattern = "dd/MM/yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
				
		String planDuree=clientDto.getPlanDuree().trim();
		String nomAssure=clientDto.getNomAssure().trim();
		String nomClient=clientDto.getNomClient().trim();	
		String numero=null;
		try {
			numero=clientDto.getNumeroCompte().trim();
			
		}catch(Exception e) {
			e.printStackTrace();
		}		
		String periodicite=clientDto.getPeriodicite().trim();
		Long couverture=clientDto.getCouverture();
		String genreAssure=clientDto.getGenreAssure().trim();
		Long prime=clientDto.getPrime();
		
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
//		Date date = simpleDateFormat.parse("25/12/2010"); 
		
		String datePrelevement=clientDto.getDatePrelevement();
		Date datePr=new Date();
		try {
		datePr=simpleDateFormat.parse(datePrelevement);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		datePrelevement=simpleDateFormat.format(datePr);
	
		
		String dateSoumission=clientDto.getDateSoumission();
		String dateNaissance=clientDto.getDateNaissance();
		
		Date dateNais=new Date();
		try {
			dateNais=simpleDateFormat.parse(dateNaissance);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		dateNaissance=simpleDateFormat.format(dateNais);
		
		String dateRealisation=clientDto.getDateRealisation();
		Date dateRea=new Date();
		try {
			dateRea=simpleDateFormat.parse(dateRealisation);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		dateRealisation=simpleDateFormat.format(dateRea);
		
		
		String matriculeClient=null;
		try {
		matriculeClient=clientDto.getMatriculeClient().trim();
			
		}catch(Exception e) {
			e.printStackTrace();
		}	
		String banque=clientDto.getBanque().trim();
		
		String codeSource=clientDto.getCodeSource().trim();
		
		String profession=clientDto.getProfession().trim();
		String employeur=clientDto.getEmployeur().trim();
		String ville=clientDto.getVille().trim();
		String adressePostal=clientDto.getAdressPostal().trim();
		String codeAgent=clientDto.getCodeAgent().trim();
		String telephone1=clientDto.getTelephone1().trim();
		String telephone2=clientDto.getTelephone2().trim();	
		String nomComPreContrat=clientDto.getNomComPreContrat().trim();
		
		String status="A";
		String nomAgenceActif=session.getAttribute("nomAgenceActif").toString().trim();
		Agence agenceActive=agenceRepository.findAgenceByNomDirect(nomAgenceActif);
		List<Sequence> sequencesAgence=sequenceRepository.findSequenceListeByIdSequence(agenceActive);
	
		if(sequencesAgence.isEmpty()) {
			model.addAttribute("MessageSequenceNonPositionne", "MessageSequenceNonPositionne");
			session.setAttribute("MessageSequenceNonPositionne", "MessageSequenceNonPositionne");
			
			String nomSource=session.getAttribute("nomSourceCache").toString().trim();		
			
			model.addAttribute("cheminAccueil",  "Accueil >");
			model.addAttribute("cheminSoumission",  "Soumission >");
			
			session.setAttribute("nomSourceCache",nomSource);
			
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
			 
			session.setAttribute("nomSourceCache",nomSource);
			
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("titre", " Soumission de Proposition");
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("menuNavigation", "menuNavigation");

			return "utilisateur/accueilUtilisateur";
		}
		
		model.addAttribute("numeroPolice", numeroPolice);	
		
		String codeGuichetSoumis=banqueRepository.findCodeBanquePrincipale(banque);
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
		model.addAttribute("codeBanque", codeSource);
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
			session.setAttribute("testEnvoye", testGroupe);
		}
		if(choixIndividuelNon) {
			String testIndividuel=session.getAttribute("testIndividuel").toString().trim();
			model.addAttribute("testIndividuel", testIndividuel);
			model.addAttribute("testEnvoye", testIndividuel);
			session.setAttribute("testEnvoye", testIndividuel);
		}					
		
		model.addAttribute("cheminAccueil",  "Accueil >");
		model.addAttribute("cheminSoumission",  "Soumission >");
		
		model.addAttribute("identifiantSession", identifiantSession);
		model.addAttribute("titre", " Soumission de Proposition");
		model.addAttribute("identifiantSession", identifiantSession);
		model.addAttribute("menuNavigation", "menuNavigation");

		model.addAttribute("confirmationDonneeSoumission", "confirmationDonneeSoumission");
        return "utilisateur/accueilUtilisateur";
    }	
	

//	@Transactional
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
		
		String numeroPolice=client.getNumeroPolice().trim();
		String produit=client.getProduit().trim();
		String nomAssure=client.getNomAssure().trim();
		String nomClient=client.getNomClient().trim();
		String numeroCompte=null;		
		try {
		 numeroCompte=client.getNumeroCompte().trim();
		}catch(Exception e) {			
			e.printStackTrace();
		}
		
		String periodicite=client.getPeriodicite().trim();
		Long couverture=client.getCouverture();
		String genreAssure=client.getGenreAssure().trim();
		Long prime=client.getPrime();
	    String datePrelevement=client.getDatePrelevement();

		String dateSoumission=client.getDateSoumission();	
		String dateNaissance=client.getDateNaissance();
		String dateRealisation=client.getDateRealisation();
		
		String matriculeClient=null;
		try {
			 matriculeClient=client.getMatriculeClient();
			}catch(Exception e) {			
				e.printStackTrace();
			}			
		String nomSource=client.getNomSource().trim();
    	String codeSource=client.getCodeSource().trim();
		String profession=client.getProfession().trim();
		String employeur=client.getEmployeur().trim();
		String ville=client.getVille().trim();
		String adressePostal=client.getAdressePostale().trim();
		String codeAgent=client.getCodeAgent().trim();
		String telephone1=client.getTelephone1().trim();
		String telephone2=client.getTelephone2().trim();	
		String nomComPreContrat=client.getNomComPreContrat().trim();
		String nomAgenceActif=session.getAttribute("nomAgenceActif").toString().trim();
		
//		String nomSource=session.getAttribute("nomSourceCache").toString().trim();
		String indiceSource=session.getAttribute("indiceSource").toString().trim();
			
		String status="A";
		
		model.addAttribute("cheminAccueil",  "Accueil >");
		model.addAttribute("cheminSoumission",  "Soumission >");
		Agence agenceActive=agenceRepository.findAgenceByNomDirect(nomAgenceActif);
		String codeAgenceActive=agenceActive.getCodeAgence();
		List<String> listeSequenceAgence=sequenceRepository.findSequenceByIdAgence( agenceActive);
		
		String sequencePolice=listeSequenceAgence.get(0);
		client.setSequencePolice(sequencePolice);
		client.setCodeSource(codeSource);
		client.setNomSource(nomSource);
		client.setProduit(produit);
		client.setCodeAgence(codeAgenceActive);
		client.setCodeAgent(codeAgent);
		client.setNomAssure(nomAssure);
		client.setNomClient(nomClient);
		client.setNumeroCompte(numeroCompte);
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
		client.setTelephone1(telephone1);
		client.setTelephone2(telephone2);
		client.setNomComPreContrat(nomComPreContrat);
		client.setStatus(status);
		client.setDateCreation(new Date());
		client.setDateRealisation(dateRealisation);
		
		client.setDateCreation(new Date());	
		
		client.setStatus(status);
		
		Commercial agent=agentRepository.findAgentByCodeAgent(codeAgent);	
		
		String testEnvoye=session.getAttribute("testEnvoye").toString();
		
	    
		Client clientExiste=null;
		String nouveauNumeroPolice=null;
		String numeroPoliceVerification=null;
		
     	Client clientDejaSauvegarder=clientRepository.findClientByAllParameters(status, numeroPolice, produit, genreAssure, nomAssure, nomClient, matriculeClient, numeroCompte, nomSource, codeSource, periodicite, couverture, prime, datePrelevement, dateSoumission, dateNaissance, profession, employeur, ville, adressePostal, telephone1, telephone2, nomComPreContrat, dateRealisation, codeAgent, codeAgenceActive)    ;
	    if(clientDejaSauvegarder!=null) {
	    	model.addAttribute("propositionDejaSoumise", " Proposition déjà soumise");
	    	model.addAttribute("resultatAjoutProposition", "resultatAjoutProposition");
	    	
	    }
	    else {
		
		do {
	    		clientExiste=clientRepository.findClientByNumeroPolice(numeroPolice, status);
			    if( clientExiste==null ) {
			    	client.setNumeroPolice(numeroPolice);
			    	Client clientSave=clientRepository.save(client); 
			    	numeroPoliceVerification=clientSave.getNumeroPolice();
			    }
			    else {			    
				    String numeroPoliceNouveau=renvoyerDernierNumeroPoliceSequence( nomAgenceActif);		    
				    client.setNumeroPolice(numeroPoliceNouveau);
				    numeroPolice=numeroPoliceNouveau;	
				    
			    }
			    model.addAttribute("AjoutProposition", "AjoutProposition");
			    model.addAttribute("resultatAjoutProposition", "resultatAjoutProposition");
	    }while(clientExiste!=null);	    
	    }	
		session.setAttribute("nomSourceCache",nomSource);
		model.addAttribute("identifiantSession", identifiantSession);	
		model.addAttribute("numeroPoliceVerification", numeroPoliceVerification);
		model.addAttribute("titre", " Soumission de Proposition");
		model.addAttribute("identifiantSession", identifiantSession);
		model.addAttribute("menuNavigation", "menuNavigation");
		
		 int page = 0;
		 int size = 20;
		 if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
	            page = Integer.parseInt(request.getParameter("page")) - 1;
	        }
		 if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
	            size = Integer.parseInt(request.getParameter("size"));
	        }
		 String nomAgenceActive=session.getAttribute("nomAgenceActif").toString().trim();
		  agenceActive=agenceRepository.findAgenceByNomDirect(nomAgenceActive);
		 String codeAgencActive=agenceActive.getCodeAgence();
		 
		
		 PageRequest pageable = PageRequest.of(page, size);
		 model.addAttribute("soumissions", clientRepository.findAllClientsPage(status,codeAgencActive, pageable));
		 
		 model.addAttribute("listeSoumission", "listeSoumission");
        return "utilisateur/accueilUtilisateur";
    }	
	
	
//	@Transactional
	@RequestMapping(value = {"/resultatAjoutPropositionModif" } , method = RequestMethod.POST)
    public String resultaAjoutPropositionModif(Model model, @ModelAttribute("client") Client client , @ModelAttribute("clientPlan") ClientPlan clientPlan , @ModelAttribute("clientBanque") ClientBanque clientBanque , @ModelAttribute("clientSociete") ClientSociete clientSociete  ,HttpSession session, HttpServletRequest request) { 	 
		String resultat=null;
		try {
			identifiantSession=session.getAttribute("identifiantSession").toString().trim();
		}
		catch(Exception e) {
			resultat="pageErreur";
			return resultat;
		}
		String status="A";
		String pattern = "dd/MM/yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String idClient=session.getAttribute("idClientModif").toString().trim();
		Integer idClientModif=Integer.valueOf(idClient);
		
		Client clientModif=clientRepository.findClientById(idClientModif,status);
		Date dc=clientModif.getDateCreation();
		String nomC=clientModif.getNomClient();
		Integer idC=clientModif.getIdClient();
		
		String numeroPolice=client.getNumeroPolice().trim();
		String produit=client.getProduit().trim();
		String nomAssure=client.getNomAssure().trim();
		String nomClient=client.getNomClient().trim();
		String numeroCompte=null;
		try {
		numeroCompte=client.getNumeroCompte().trim();
		}catch(Exception e) {
			e.printStackTrace();
		}
				
		String periodicite=client.getPeriodicite().trim();
		Long couverture=client.getCouverture();
		String genreAssure=client.getGenreAssure().trim();
		Long prime=client.getPrime();
		
		String datePrelevement=client.getDatePrelevement();
		Date datePr=new Date();
		try {
		datePr=simpleDateFormat.parse(datePrelevement);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		datePrelevement=simpleDateFormat.format(datePr);
		
		String dateSoumission=client.getDateSoumission();	
		
		String dateNaissance=client.getDateNaissance();
		Date dateNai=new Date();
		try {
			dateNai=simpleDateFormat.parse(dateNaissance);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		dateNaissance=simpleDateFormat.format(dateNai);
		
		String dateRealisation=client.getDateRealisation();
		Date dateRe=new Date();
		try {
			dateRe=simpleDateFormat.parse(dateRealisation);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		dateRealisation=simpleDateFormat.format(dateRe);
			
		String matriculeClient=null;
		try {
			matriculeClient=client.getMatriculeClient();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		String nomSource=client.getNomSource().trim();
		
		List<String> codesSourceSociete=societeRepository.findCodesSocieteByNomSociete(nomSource, status);
		String codeSourceSociete=codesSourceSociete.get(0);
		
		List<String> codesSourceBanque=agenceBanqueRepository.findCodesAgenceBanqueByLibelle(nomSource);
		String codeSourceBanque=codesSourceBanque.get(0);
		if(codeSourceSociete!=null) {
			clientModif.setCodeSource(codeSourceSociete);
		}
		if(codeSourceBanque!=null) {
			clientModif.setCodeSource(codeSourceBanque);
		}
		
		
//		String codeSource=client.getCodeSource().trim();
		String profession=client.getProfession().trim();
		String employeur=client.getEmployeur().trim();
		String ville=client.getVille().trim();
		String adressePostal=client.getAdressePostale().trim();
		String codeAgent=client.getCodeAgent().trim();
		String telephone1=client.getTelephone1().trim();
		String telephone2=client.getTelephone2().trim();	
		String nomComPreContrat=client.getNomComPreContrat().trim();
		
		
		
		clientModif.setNumeroPolice(numeroPolice);
		
		clientModif.setNomSource(nomSource);
		
		
		
		clientModif.setProduit(produit);
		clientModif.setNomAssure(nomAssure);
		clientModif.setNomClient(nomClient);
		clientModif.setNumeroCompte(numeroCompte);
		clientModif.setPeriodicite(periodicite);
		clientModif.setCouverture(couverture);
		clientModif.setGenreAssure(genreAssure);
		clientModif.setPrime(prime);
		clientModif.setDatePrelevement(datePrelevement);
		clientModif.setDateRealisation(dateRealisation);
		clientModif.setDateSoumission(dateSoumission);
		clientModif.setDateNaissance(dateNaissance);
		clientModif.setMatriculeClient(matriculeClient);
		clientModif.setProfession(profession);
		clientModif.setEmployeur(employeur);
		clientModif.setVille(ville);
		clientModif.setAdressePostale(adressePostal);
		clientModif.setTelephone1(telephone1);
		clientModif.setTelephone2(telephone2);
		clientModif.setNomComPreContrat(nomComPreContrat);
		clientModif.setDateCreation(dc);
		String nomAgenceActif=session.getAttribute("nomAgenceActif").toString().trim();
		Agence agence=agenceRepository.findAgenceByNomDirect(nomAgenceActif);
		Sequence sequenceAgence=renvoyerDerniereSequence(nomAgenceActif);
		String codeAgence=agence.getCodeAgence();
		clientModif.setCodeAgence(codeAgence);
		String sequence=sequenceAgence.getSeq();
		clientModif.setSequencePolice(sequence);
		clientModif.setStatus(status);
		Client clientReturn=clientRepository.save(clientModif);
		String numeroPoliceVerification=clientReturn.getNumeroPolice();
		 model.addAttribute("numeroPoliceVerification", numeroPoliceVerification);
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
		
		 Page<Client> clientPage = clientRepository.findAllClientsPage(status, codeAgence, pageable);
		 model.addAttribute("soumissions", clientPage);
			 	 
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
	
//		@Transactional
		@RequestMapping(value = "/clientsPage")
	    public ModelAndView listArticlesPageByPage(@RequestParam(name="page", defaultValue="0") int page,@RequestParam(name="size", defaultValue="100") int size,  @ModelAttribute("client") Client client, Model model, HttpSession session, HttpServletRequest request) {
	        ModelAndView modelAndView = new ModelAndView("utilisateur/accueilUtilisateur");
	        Boolean estSupprimer=false;
	        String nomAgenceActive=session.getAttribute("nomAgenceActif").toString().trim();
			Agence agenceActive=agenceRepository.findAgenceByNomDirect(nomAgenceActive);
			String codeAgence=agenceActive.getCodeAgence();
			String status="A";
	        PageRequest pageable = PageRequest.of(page-1, size);
	        Page<Client> clientPage = clientRepository.findAllClientsPage(status, codeAgence, pageable);
 
			 if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
		            page = Integer.parseInt(request.getParameter("page")) - 1;
		        }
			 if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
		            size = Integer.parseInt(request.getParameter("size"));
		        }
			model.addAttribute("soumissions", clientPage);
					
			model.addAttribute("cheminAccueil",  "Accueil >");
			model.addAttribute("cheminSoumission",  "Soumission >");		
			model.addAttribute("listeSoumission", "listeSoumission");
	        model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("titre", " Soumission de Proposition");
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("menuNavigation", "menuNavigation");
	        return modelAndView;
	    }
//		@Transactional
//		@RequestMapping(value = "/clientsPageDeux")
//	    public ModelAndView listArticlesPageByPageDeux(@RequestParam(name="page", defaultValue="0") int page,@RequestParam(name="size", defaultValue="20") int size,  @ModelAttribute("client") Client client, Model model, HttpSession session, HttpServletRequest request) {
//	        ModelAndView modelAndView = new ModelAndView("utilisateur/accueilUtilisateur");
//	        Boolean estSupprimer=false;
//	        String nomAgenceActive=session.getAttribute("nomAgenceActif").toString().trim();
//			 Agence agenceActive=agenceRepository.findAgenceByNomDirect(nomAgenceActive);
//			
//	        PageRequest pageable = PageRequest.of(page-1, size);
//	        Page<Client> clientPage = clientRepository.findAllClientsPage(estSupprimer,agenceActive, pageable);
// 
//			 if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
//		            page = Integer.parseInt(request.getParameter("page")) - 1;
//		        }
//			 if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
//		            size = Integer.parseInt(request.getParameter("size"));
//		        }
//		    clientPage = clientRepository.findAllClientsPage(estSupprimer,agenceActive, pageable);
//			model.addAttribute("clientsPage", clientPage);
//					
//			model.addAttribute("cheminAccueil",  "Accueil >");
//			model.addAttribute("cheminSoumission",  "Soumission >");		
//			model.addAttribute("listeSoumissionDeux", "listeSoumissionDeux");
//	        model.addAttribute("identifiantSession", identifiantSession);
//			model.addAttribute("titre", " Soumission de Proposition");
//			model.addAttribute("identifiantSession", identifiantSession);
//			model.addAttribute("menuNavigation", "menuNavigation");
//	        return modelAndView;
//	    }
//		
//		@Transactional
//		@RequestMapping(value = {"/listeSoumission" } , method = RequestMethod.GET)
//	    public String listeSoumission(Model model, @ModelAttribute("client") Client client, @ModelAttribute("clientDto") ClientDto clientDto , @ModelAttribute("clientPlan") ClientPlan clientPlan , @ModelAttribute("clientBanque") ClientBanque clientBanque , @ModelAttribute("clientSociete") ClientSociete clientSociete  ,HttpSession session, HttpServletRequest request) { 
//		 
//			String resultat=null;
//			try {
//				identifiantSession=session.getAttribute("identifiantSession").toString().trim();
//			}
//			catch(Exception e) {
//				resultat="pageErreur";
//				return resultat;
//			}
//			Boolean estSupprimer=false;
//			int page = 0;
//			 int size = 20;
//			 if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
//		            page = Integer.parseInt(request.getParameter("page")) - 1;
//		        }
//			 if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
//		            size = Integer.parseInt(request.getParameter("size"));
//		        }
//			 PageRequest pageable = PageRequest.of(page, size);
//			 String nomAgenceActive=session.getAttribute("nomAgenceActif").toString().trim();
//			 Agence agenceActive=agenceRepository.findAgenceByNomDirect(nomAgenceActive);
//			 Page<Client> clientPage = clientRepository.findAllClientsPage(estSupprimer, agenceActive, pageable);
//			 model.addAttribute("clientsPage", clientPage);
//							
//			model.addAttribute("cheminAccueil",  "Accueil >");
//			model.addAttribute("cheminSoumission",  " Liste Soumission >");
//			model.addAttribute("listeSoumission", "listeSoumission");
//			model.addAttribute("identifiantSession", identifiantSession);
//			model.addAttribute("titre", " Soumission de Proposition");
//			model.addAttribute("identifiantSession", identifiantSession);
//			model.addAttribute("menuNavigation", "menuNavigation");
//
//	        return "utilisateur/accueilUtilisateur";
//	    }	
//		@Transactional
//		@RequestMapping(value = {"/listeSoumissionDeux" } , method = RequestMethod.GET)
//	    public String listeSoumissionDeux(Model model, @ModelAttribute("client") Client client, @ModelAttribute("clientDto") ClientDto clientDto , @ModelAttribute("clientPlan") ClientPlan clientPlan , @ModelAttribute("clientBanque") ClientBanque clientBanque , @ModelAttribute("clientSociete") ClientSociete clientSociete  ,HttpSession session, HttpServletRequest request) { 
//		 
//			String resultat=null;
//			try {
//				identifiantSession=session.getAttribute("identifiantSession").toString().trim();
//			}
//			catch(Exception e) {
//				resultat="pageErreur";
//				return resultat;
//			}
//			Boolean estSupprimer=false;
//			int page = 0;
//			 int size = 20;
//			 if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
//		            page = Integer.parseInt(request.getParameter("page")) - 1;
//		        }
//			 if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
//		            size = Integer.parseInt(request.getParameter("size"));
//		        }
//			 PageRequest pageable = PageRequest.of(page, size);
//			 String nomAgenceActive=session.getAttribute("nomAgenceActif").toString().trim();
//			 Agence agenceActive=agenceRepository.findAgenceByNomDirect(nomAgenceActive);
//			 Page<Client> clientPage = clientRepository.findAllClientsPage(estSupprimer, agenceActive, pageable);
//			 model.addAttribute("clientsPage", clientPage);
//							
//			model.addAttribute("cheminAccueil",  "Accueil >");
//			model.addAttribute("cheminSoumission",  " Liste Soumission >");
//			model.addAttribute("listeSoumissionDeux", "listeSoumissionDeux");
//			model.addAttribute("identifiantSession", identifiantSession);
//			model.addAttribute("titre", " Soumission de Proposition");
//			model.addAttribute("identifiantSession", identifiantSession);
//			model.addAttribute("menuNavigation", "menuNavigation");
//
//	        return "utilisateur/accueilUtilisateur";
//	    }	
//	
	
}
