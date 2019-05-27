package com.belife.policemanager.aaroncontroller;

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
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.belife.policemanager.model.aarondto.AaronClientDto;
import com.belife.policemanager.model.aaronentity.AaronAgence;
import com.belife.policemanager.model.aaronentity.AaronClient;
import com.belife.policemanager.model.aaronentity.AaronSequence;
import com.belife.policemanager.model.aaronentity.AaronUtilisateur;
import com.belife.policemanager.model.aaronrepository.AaronAgenceRepository;
import com.belife.policemanager.model.aaronrepository.AaronBanqueRepository;
import com.belife.policemanager.model.aaronrepository.AaronClientRepository;
import com.belife.policemanager.model.aaronrepository.AaronSequenceRepository;
import com.belife.policemanager.model.aaronrepository.AaronUtilisateurRepository;
import com.belife.policemanager.model.dto.AgenceBanqueDto;
import com.belife.policemanager.model.dto.BanqueDto;
import com.belife.policemanager.model.dto.ClientDto;
import com.belife.policemanager.model.dto.SourcePrelevementDto;
import com.belife.policemanager.model.entity.Agence;
import com.belife.policemanager.model.entity.Banque;
import com.belife.policemanager.model.entity.Client;
import com.belife.policemanager.model.entity.ClientBanque;
import com.belife.policemanager.model.entity.ClientPlan;
import com.belife.policemanager.model.entity.ClientSociete;
import com.belife.policemanager.model.entity.Commercial;
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
public class AaronClientController {

	 @Autowired
     UtilisateurRepository utilisateurRepository; 
	 
	 @Autowired
     AaronUtilisateurRepository aaronUtilisateurRepository; 
	 
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
     AaronAgenceRepository aaronAgenceRepository;
	 
	 @Autowired
     ClientPlanRepository clientPlanRepository;
	 
	 @Autowired
     ClientBanqueRepository clientBanqueRepository;
	 
	 @Autowired
     ClientSocieteRepository clientSocieteRepository;
	 
	 @Autowired
     AaronClientRepository aaronClientRepository;
	 
	 @Autowired
     SequenceRepository sequenceRepository;
	 
	 @Autowired
     SequencePoliceRepository sequencePoliceRepository;
	 
	 @Autowired
     AaronSequenceRepository aaronSequenceRepository;
	 
	 
    private String identifiantSession=null;
    SourcePrelevementDto sourceDto=new  SourcePrelevementDto();
    
    
	@RequestMapping(value = {"/aaronSoumission" }, method = RequestMethod.GET)
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
        return "aaron/utilisateur/accueilUtilisateur";
    }
	
	
	
////	@@ designe par
//	@Transactional
	@RequestMapping(value = {"/aaronSoumissionProposition@@Individuel" }, method = RequestMethod.POST)
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
		AaronAgence agenceActive=aaronAgenceRepository.findAgenceByNomDirect(nomAgenceActif);
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
	     Page<AaronClient> clientPage = aaronClientRepository.findAllClientsPage(status,codeAgenceActive , pageable);
		 model.addAttribute("soumissions", clientPage);		 
		 session.removeAttribute("testGroupe");
		 	
		List<String> nomSources=societeRepository.findAllNomSociete(status);
		model.addAttribute("nomSources",  nomSources);
			
		String nomGuichet=agenceBanqueDto.getLibelleAgence().trim();
		
		String[] codeGuichets=nomGuichet.split("-");
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
		AaronUtilisateur utilisateurRep=aaronUtilisateurRepository.findByIdentifiant(identifiant);
		AaronAgence agence=utilisateurRep.getIdAgence();
		
		Integer idAgence=agence.getIdAgence();
		String codeAgence=agence.getCodeAgence();
		System.out.println(" Code agence : "+codeAgence);
		List<String> nomCommmerciaux=agentRepository.findAllNomAgentByCodeAgence(codeAgence);

		AaronAgence aarongence=aaronAgenceRepository.findAgenceByCodeDirect(codeAgence);
	
		
		model.addAttribute("codeAgent", codeAgence);

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
        return "aaron/utilisateur/accueilUtilisateur";
    }
	
	
//	@Transactional
	@RequestMapping(value = {"/aaronSoumissionProposition" }, method = RequestMethod.POST)
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
		        return "aaron/utilisateur/accueilUtilisateur";			 
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
			return "redirect:/aaronRedirectionChoixGuichet";
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
		AaronUtilisateur utilisateurRep=aaronUtilisateurRepository.findByIdentifiant(identifiant);
		
		AaronAgence agence=utilisateurRep.getIdAgence();
		
		 codeAgence=agence.getCodeAgence();
		Integer idAgenceUtilisateur=agence.getIdAgence();
		
		
		AaronAgence aaronAgence=aaronAgenceRepository.findAgenceByNomDirect(agence.getNomDirect());
		
		model.addAttribute("codeAgent", aaronAgence.getCodeAgence());
		
		
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
			
        return "aaron/utilisateur/accueilUtilisateur";
    }
	
	
//	@Transactional
	@RequestMapping(value = {"/aaronRedirectionChoixGuichet" }, method = RequestMethod.GET)
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
		return "aaron/utilisateur/accueilUtilisateur";
    }
	
	
	@Transactional
	@RequestMapping(value = {"/aaronEnvoiCacheGenre" }, method = RequestMethod.POST)
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
        return "aaron/utilisateur/accueilUtilisateur";
    }
	
	
	
	
	////	@Transactional
	@RequestMapping(value = {"/aaronModificationSoumission" } , method = RequestMethod.POST)
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
		AaronClient clientModif=aaronClientRepository.findClientById(idClient, status);
		session.setAttribute("idClientModif", idClient );
		////////////////////:  Retrouver Banque
		
		
		String numeroCompte=clientModif.getNumeroCompte();
		String pattern = "dd/MM/yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		
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
		AaronUtilisateur utilisateurRep=aaronUtilisateurRepository.findByIdentifiant(identifiant);
		AaronAgence agence=utilisateurRep.getIdAgence();
		String codeAgence=agence.getCodeAgence();
//		List<String> codeAgents=agentRepository.findAllCodeAgentByCodeAgence(codeAgence);
//		model.addAttribute("codeAgents", codeAgents);
		
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
		 Page<AaronClient> clientPage = aaronClientRepository.findAllClientsPage(status, codeAgence, pageable);
		 model.addAttribute("soumissions", clientPage);
		 
		model.addAttribute("listeSoumission", "listeSoumission");
		model.addAttribute("identifiantSession", identifiantSession);
		model.addAttribute("titre", " Soumission de Proposition");
		model.addAttribute("identifiantSession", identifiantSession);
		model.addAttribute("menuNavigation", "menuNavigation");
        return "aaron/utilisateur/accueilUtilisateur";
    }
	
	
	
//	 @Transactional
	 private String renvoyerDernierNumeroPoliceSequence(String nomAgenceActif) {
		 
		 AaronSequence objetSequence=renvoyerDerniereSequence( nomAgenceActif );
		 String numeroSequence=objetSequence.getSeq();
		 String sequence=objetSequence.getSeq();
		 String status="A";
		 List<String> numeroPoliceListSoumis=aaronClientRepository.findAllNumeroPolicesByIdSequence(numeroSequence,status);
		 	  
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
	 private AaronSequence renvoyerDerniereSequence(String nomAgenceActif ) {
		AaronAgence agenceActive=aaronAgenceRepository.findAgenceByNomDirect(nomAgenceActif);
		List<AaronSequence> sequencesAgence=aaronSequenceRepository.findSequenceListeByIdSequence(agenceActive);
//		List<AaronSequence> sequencesAgence=aaronSequenceRepository.findSequenceListeByIdSequence(agenceActive);
		AaronSequence sequenceAgence=sequencesAgence.get(0);	
		return sequenceAgence;
	}
	
	
////	@Transactional
	@RequestMapping(value = {"/aaronVoirSoumission" } , method = RequestMethod.POST)
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
		AaronClient clientAfficher=aaronClientRepository.findClientById(idClient,status);
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
		 AaronAgence agenceActive=aaronAgenceRepository.findAgenceByNomDirect(nomAgenceActif);
		 String codeAgenceActive=agenceActive.getCodeAgence();
		
		 PageRequest pageable = PageRequest.of(page, size);
		 Page<AaronClient> clientPage = aaronClientRepository.findAllClientsPage(status, codeAgenceActive, pageable);
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
        return "aaron/utilisateur/accueilUtilisateur";
    }	
	
//	@Transactional
	@RequestMapping(value = {"/aaronConfirmationAjoutProposition" } , method = RequestMethod.POST)
    public String confirmationAjoutProposition(Model model, @ModelAttribute("aaronClient") AaronClient aaronClient, @ModelAttribute("aaronClientDto") AaronClientDto aaronClientDto , @ModelAttribute("clientPlan") ClientPlan clientPlan , @ModelAttribute("clientBanque") ClientBanque clientBanque , @ModelAttribute("clientSociete") ClientSociete clientSociete  ,HttpSession session, HttpServletRequest request) { 
	 
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
				
		String planDuree=aaronClientDto.getPlanDuree().trim();
		String nomAssure=aaronClientDto.getNomAssure().trim();
		String nomClient=aaronClientDto.getNomClient().trim();	
		String numero=null;
		try {
			numero=aaronClientDto.getNumeroCompte().trim();
			
		}catch(Exception e) {
			e.printStackTrace();
		}		
		String periodicite=aaronClientDto.getPeriodicite().trim();
		Long couverture=aaronClientDto.getCouverture();
		String genreAssure=aaronClientDto.getGenreAssure().trim();
		Long prime=aaronClientDto.getPrime();
		
		String datePrelevement=aaronClientDto.getDatePrelevement();
		Date datePr=new Date();
		try {
		datePr=simpleDateFormat.parse(datePrelevement);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		datePrelevement=simpleDateFormat.format(datePr);
		
		String dateSoumission=aaronClientDto.getDateSoumission();
		String dateNaissance=aaronClientDto.getDateNaissance();
		
		Date dateNais=new Date();
		try {
			dateNais=simpleDateFormat.parse(dateNaissance);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		dateNaissance=simpleDateFormat.format(dateNais);
		
		String dateRealisation=aaronClientDto.getDateRealisation();
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
		matriculeClient=aaronClientDto.getMatriculeClient().trim();
			
		}catch(Exception e) {
			e.printStackTrace();
		}	
		String banque=aaronClientDto.getBanque().trim();
		
		String codeSource=aaronClientDto.getCodeSource().trim();
		
		String profession=aaronClientDto.getProfession().trim();
		String employeur=aaronClientDto.getEmployeur().trim();
		String ville=aaronClientDto.getVille().trim();
		String adressePostal=aaronClientDto.getAdressPostal().trim();
		String codeAgent=aaronClientDto.getCodeAgent().trim();
		String telephone1=aaronClientDto.getTelephone1().trim();
		String telephone2=aaronClientDto.getTelephone2().trim();	
		String nomComPreContrat=aaronClientDto.getNomComPreContrat().trim();
		
		String status="A";
		String nomAgenceActif=session.getAttribute("nomAgenceActif").toString().trim();
		AaronAgence agenceActive=aaronAgenceRepository.findAgenceByNomDirect(nomAgenceActif);
		List<AaronSequence> sequencesAgence=aaronSequenceRepository.findSequenceListeByIdSequence(agenceActive);
	
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

			return "aaron/utilisateur/accueilUtilisateur";
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

			return "aaron/utilisateur/accueilUtilisateur";
		}
		
		model.addAttribute("numeroPolice", numeroPolice);	
		
		String codeGuichetSoumis=aaronBanqueRepository.findCodeBanquePrincipale(banque);
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
        return "aaron/utilisateur/accueilUtilisateur";
    }	
	

//	@Transactional
	@RequestMapping(value = {"/aaronResultatAjoutProposition" } , method = RequestMethod.POST)
    public String resultaAjoutProposition(Model model, @ModelAttribute("aaronClient") AaronClient aaronClient, @ModelAttribute("clientDto") ClientDto clientDto , @ModelAttribute("clientPlan") ClientPlan clientPlan , @ModelAttribute("clientBanque") ClientBanque clientBanque , @ModelAttribute("clientSociete") ClientSociete clientSociete  ,HttpSession session, HttpServletRequest request) { 
	 
		String resultat=null;
		try {
			identifiantSession=session.getAttribute("identifiantSession").toString().trim();
		}
		catch(Exception e) {
			resultat="pageErreur";
			return resultat;
		}
		
		String numeroPolice=aaronClient.getNumeroPolice().trim();
		String produit=aaronClient.getProduit().trim();
		String nomAssure=aaronClient.getNomAssure().trim();
		String nomClient=aaronClient.getNomClient().trim();
		String numeroCompte=null;		
		try {
		 numeroCompte=aaronClient.getNumeroCompte().trim();
		}catch(Exception e) {			
			e.printStackTrace();
		}
		
		String periodicite=aaronClient.getPeriodicite().trim();
		Long couverture=aaronClient.getCouverture();
		String genreAssure=aaronClient.getGenreAssure().trim();
		Long prime=aaronClient.getPrime();
	    String datePrelevement=aaronClient.getDatePrelevement();

		String dateSoumission=aaronClient.getDateSoumission();	
		String dateNaissance=aaronClient.getDateNaissance();
		String dateRealisation=aaronClient.getDateRealisation();
		
		String matriculeClient=null;
		try {
			 matriculeClient=aaronClient.getMatriculeClient();
			}catch(Exception e) {			
				e.printStackTrace();
			}			
		String nomSource=aaronClient.getNomSource().trim();
    	String codeSource=aaronClient.getCodeSource().trim();
		String profession=aaronClient.getProfession().trim();
		String employeur=aaronClient.getEmployeur().trim();
		String ville=aaronClient.getVille().trim();
		String adressePostal=aaronClient.getAdressePostale().trim();
		String codeAgent=aaronClient.getCodeAgent().trim();
		String telephone1=aaronClient.getTelephone1().trim();
		String telephone2=aaronClient.getTelephone2().trim();	
		String nomComPreContrat=aaronClient.getNomComPreContrat().trim();
		String nomAgenceActif=session.getAttribute("nomAgenceActif").toString().trim();
		
		String indiceSource=session.getAttribute("indiceSource").toString().trim();
			
		String status="A";
		
		model.addAttribute("cheminAccueil",  "Accueil >");
		model.addAttribute("cheminSoumission",  "Soumission >");
		AaronAgence agenceActive=aaronAgenceRepository.findAgenceByNomDirect(nomAgenceActif);
		String codeAgenceActive=agenceActive.getCodeAgence();
		List<String> listeSequenceAgence=aaronSequenceRepository.findSequenceByIdAgence(agenceActive);
		
		String sequencePolice=listeSequenceAgence.get(0);
		aaronClient.setSequencePolice(sequencePolice);
		aaronClient.setCodeSource(codeSource);
		aaronClient.setNomSource(nomSource);
		aaronClient.setProduit(produit);
		aaronClient.setCodeAgence(codeAgenceActive);
		aaronClient.setCodeAgent(codeAgent);
		aaronClient.setNomAssure(nomAssure);
		aaronClient.setNomClient(nomClient);
		aaronClient.setNumeroCompte(numeroCompte);
		aaronClient.setPeriodicite(periodicite);
		aaronClient.setCouverture(couverture);
		aaronClient.setGenreAssure(genreAssure);
		aaronClient.setPrime(prime);
		aaronClient.setDatePrelevement(datePrelevement);
		aaronClient.setDateSoumission(dateSoumission);
		aaronClient.setDateNaissance(dateNaissance);
		aaronClient.setMatriculeClient(matriculeClient);
		aaronClient.setProfession(profession);
		aaronClient.setEmployeur(employeur);
		aaronClient.setVille(ville);
		aaronClient.setAdressePostale(adressePostal);
		aaronClient.setTelephone1(telephone1);
		aaronClient.setTelephone2(telephone2);
		aaronClient.setNomComPreContrat(nomComPreContrat);
		aaronClient.setStatus(status);
		aaronClient.setDateCreation(new Date());
		aaronClient.setDateRealisation(dateRealisation);				
		
		Commercial agent=agentRepository.findAgentByCodeAgent(codeAgent);	
		
		String testEnvoye=session.getAttribute("testEnvoye").toString();
		    
		AaronClient clientExiste=null;
		String nouveauNumeroPolice=null;
		String numeroPoliceVerification=null;
		
		AaronClient clientDejaSauvegarder=aaronClientRepository.findClientByAllParameters(status, numeroPolice, produit, genreAssure, nomAssure, nomClient, matriculeClient, numeroCompte, nomSource, codeSource, periodicite, couverture, prime, datePrelevement, dateSoumission, dateNaissance, profession, employeur, ville, adressePostal, telephone1, telephone2, nomComPreContrat, dateRealisation, codeAgent, codeAgenceActive)    ;
	    if(clientDejaSauvegarder!=null) {
	    	model.addAttribute("propositionDejaSoumise", " Proposition déjà soumise");
	    	model.addAttribute("resultatAjoutProposition", "resultatAjoutProposition");
	    	
	    }
	    else {
		
		do {
	    		clientExiste=aaronClientRepository.findClientByNumeroPolice(numeroPolice, status);
			    if( clientExiste==null ) {
			    	aaronClient.setNumeroPolice(numeroPolice);
			    	AaronClient clientSave=aaronClientRepository.save(aaronClient); 
			    	numeroPoliceVerification=clientSave.getNumeroPolice();
			    }
			    else {			    
				    String numeroPoliceNouveau=renvoyerDernierNumeroPoliceSequence( nomAgenceActif);		    
				    aaronClient.setNumeroPolice(numeroPoliceNouveau);
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
		  agenceActive=aaronAgenceRepository.findAgenceByNomDirect(nomAgenceActive);
		 String codeAgencActive=agenceActive.getCodeAgence();
		 
		
		 PageRequest pageable = PageRequest.of(page, size);
		 model.addAttribute("soumissions", aaronClientRepository.findAllClientsPage(status,codeAgencActive, pageable));
		 
		 model.addAttribute("listeSoumission", "listeSoumission");
        return "aaron/utilisateur/accueilUtilisateur";
    }	
	
	
//	@Transactional
	@RequestMapping(value = {"/aaronResultatAjoutPropositionModif" } , method = RequestMethod.POST)
    public String resultaAjoutPropositionModif(Model model, @ModelAttribute("aaronClient") AaronClient aaronClient , HttpSession session, HttpServletRequest request) { 	 
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
		
		AaronClient clientModif=aaronClientRepository.findClientById(idClientModif,status);
		Date dc=clientModif.getDateCreation();
		String nomC=clientModif.getNomClient();
		Integer idC=clientModif.getIdClient();
		
		String numeroPolice=aaronClient.getNumeroPolice().trim();
		String produit=aaronClient.getProduit().trim();
		String nomAssure=aaronClient.getNomAssure().trim();
		String nomClient=aaronClient.getNomClient().trim();
		String numeroCompte=null;
		try {
		numeroCompte=aaronClient.getNumeroCompte().trim();
		}catch(Exception e) {
			e.printStackTrace();
		}
				
		String periodicite=aaronClient.getPeriodicite().trim();
		Long couverture=aaronClient.getCouverture();
		String genreAssure=aaronClient.getGenreAssure().trim();
		Long prime=aaronClient.getPrime();
		
		String datePrelevement=aaronClient.getDatePrelevement();
		Date datePr=new Date();
		try {
		datePr=simpleDateFormat.parse(datePrelevement);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		datePrelevement=simpleDateFormat.format(datePr);
		
		String dateSoumission=aaronClient.getDateSoumission();	
		
		String dateNaissance=aaronClient.getDateNaissance();
		Date dateNai=new Date();
		try {
			dateNai=simpleDateFormat.parse(dateNaissance);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		dateNaissance=simpleDateFormat.format(dateNai);
		
		String dateRealisation=aaronClient.getDateRealisation();
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
			matriculeClient=aaronClient.getMatriculeClient();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		String nomSource=aaronClient.getNomSource().trim();
		
		List<String> codesSourceSociete=societeRepository.findCodesSocieteByNomSociete(nomSource, status);
		String codeSourceSociete=null;
		
		List<String> codesSourceBanque=agenceBanqueRepository.findCodesAgenceBanqueByLibelle(nomSource);
		String codeSourceBanque=null;
		if(!codesSourceSociete.isEmpty() ) {
			codeSourceSociete=codesSourceSociete.get(0);
			model.addAttribute("testGroupe", "test");
			clientModif.setCodeSource(codeSourceSociete);
		}
		if(!codesSourceBanque.isEmpty()) {
			codeSourceBanque=codesSourceBanque.get(0);
			model.addAttribute("testIndividuel", "testIndividuel");
			clientModif.setCodeSource(codeSourceBanque);
		}
		
		
//		String codeSource=client.getCodeSource().trim();
		String profession=aaronClient.getProfession().trim();
		String employeur=aaronClient.getEmployeur().trim();
		String ville=aaronClient.getVille().trim();
		String adressePostal=aaronClient.getAdressePostale().trim();
		String codeAgent=aaronClient.getCodeAgent().trim();
		String telephone1=aaronClient.getTelephone1().trim();
		String telephone2=aaronClient.getTelephone2().trim();	
		String nomComPreContrat=aaronClient.getNomComPreContrat().trim();
			
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
		AaronAgence agence=aaronAgenceRepository.findAgenceByNomDirect(nomAgenceActif);
		AaronSequence sequenceAgence=renvoyerDerniereSequence(nomAgenceActif);
		String codeAgence=agence.getCodeAgence();
		clientModif.setCodeAgence(codeAgence);
		String sequence=sequenceAgence.getSeq();
		clientModif.setSequencePolice(sequence);
		clientModif.setStatus(status);
		clientModif.setNumeroCompte(clientModif.getNumeroCompte());
		AaronClient clientReturn=aaronClientRepository.save(clientModif);
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
		 
	     PageRequest pageable = PageRequest.of(page, size);
	     String nomAgenceActive=session.getAttribute("nomAgenceActif").toString().trim();
		 AaronAgence agenceActive=aaronAgenceRepository.findAgenceByNomDirect(nomAgenceActive);
		
		 Page<AaronClient> clientPage = aaronClientRepository.findAllClientsPage(status, codeAgence, pageable);
		 model.addAttribute("soumissions", clientPage);
			 	 
		model.addAttribute("cheminAccueil",  "Accueil >");
		model.addAttribute("cheminSoumission",  "Soumission >");
		
		model.addAttribute("listeSoumission", "listeSoumission");
		
		model.addAttribute("identifiantSession", identifiantSession);
		model.addAttribute("titre", " Soumission de Proposition");
		model.addAttribute("identifiantSession", identifiantSession);
		model.addAttribute("menuNavigation", "menuNavigation");

		model.addAttribute("resultatAjoutPropositionModif", "resultatAjoutPropositionModif");
        return "aaron/utilisateur/accueilUtilisateur";
    }	
	
//		@Transactional
		@RequestMapping(value = "/aaronClientsPage")
	    public ModelAndView listArticlesPageByPage(@RequestParam(name="page", defaultValue="0") int page,@RequestParam(name="size", defaultValue="100") int size,  @ModelAttribute("aaronclient") AaronClient aaronClient, Model model, HttpSession session, HttpServletRequest request) {
	        ModelAndView modelAndView = new ModelAndView("aaron/utilisateur/accueilUtilisateur");
	        String nomAgenceActive=session.getAttribute("nomAgenceActif").toString().trim();
			AaronAgence agenceActive=aaronAgenceRepository.findAgenceByNomDirect(nomAgenceActive);
			String codeAgence=agenceActive.getCodeAgence();
			String status="A";
	        PageRequest pageable = PageRequest.of(page-1, size);
	        Page<AaronClient> clientPage = aaronClientRepository.findAllClientsPage(status, codeAgence, pageable);
 
			 if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
		            page = Integer.parseInt(request.getParameter("page")) - 1;
		        }
			 if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
		            size = Integer.parseInt(request.getParameter("size"));
		        }
			model.addAttribute("soumissions",clientPage);
					
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
