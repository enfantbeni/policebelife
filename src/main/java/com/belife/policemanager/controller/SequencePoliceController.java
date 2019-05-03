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
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.belife.policemanager.model.dto.AgentDto;
import com.belife.policemanager.model.dto.ClientDto;
import com.belife.policemanager.model.dto.SequenceDto;
import com.belife.policemanager.model.dto.SequencePoliceDto;
import com.belife.policemanager.model.entity.Agence;
import com.belife.policemanager.model.entity.Agent;
import com.belife.policemanager.model.entity.Client;
import com.belife.policemanager.model.entity.Sequence;
import com.belife.policemanager.model.entity.SequencePolice;
import com.belife.policemanager.model.entity.Societe;
import com.belife.policemanager.model.repository.AgenceRepository;
import com.belife.policemanager.model.repository.BanqueRepository;
import com.belife.policemanager.model.repository.ClientRepository;
import com.belife.policemanager.model.repository.RolesRepository;
import com.belife.policemanager.model.repository.SequencePoliceRepository;
import com.belife.policemanager.model.repository.SequenceRepository;
import com.belife.policemanager.model.repository.SourcePoliceRepository;
import com.belife.policemanager.model.repository.UtilisateurRepository;

@Controller
public class SequencePoliceController {
	
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
	 SequenceRepository sequenceRepository;
	 
	 @Autowired
	 SequencePoliceRepository sequencePoliceRepository;
	 
	 @Autowired
	 ClientRepository clientRepository;
	 
	 String identifiantSession=null;
	 
	 
	 
	 @Transactional
	 @RequestMapping(value = {"/gestionSequencePolice" }, method = RequestMethod.GET)
	    public String gestionSequencePolice(Model model, @ModelAttribute("sequenceDto") SequencePoliceDto sequenceDto , HttpServletRequest request , HttpSession session) { 
		 
		 String resultat=null;
			try {
				identifiantSession=session.getAttribute("identifiantSession").toString().trim();
			}
			catch(Exception e) {
				resultat="pageErreur";
				return resultat;
			}
			List<Sequence> sequences=new ArrayList<Sequence>();
			

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
			 Page<SequencePolice> seqPolicePage = sequencePoliceRepository.findAllSequencePolices(estSupprimer, pageable);
			 model.addAttribute("sequencePolices", seqPolicePage);	
			
			
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionSequencePolice", "Gestion Sequence >");
			model.addAttribute("titre", "Gestion de Sequence");
			model.addAttribute("identifiantSession", identifiantSession);			
			model.addAttribute("gestionSequencePolice", "gestionSequencePolice");
			model.addAttribute("quatreBoutonSequencePolice", "quatreBoutonSequencePolice");
			model.addAttribute("listeSequencePolice", "listeSequencePolice");
			model.addAttribute("menuNavigation", "menuNavigation");												
	        return "espaceUtilisateur";			
	    }
	 
	 @Transactional
	 @RequestMapping(value = {"/ajoutSequencePolice" }, method = RequestMethod.GET)
	    public String ajoutAgence(Model model, HttpSession session, HttpServletRequest request ) { 
		 
		 String resultat=null;
			try {
				identifiantSession=session.getAttribute("identifiantSession").toString().trim();
			}
			catch(Exception e) {
				resultat="pageErreur";
				return resultat;
			}
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
		     List<SequenceDto> sequencesDto=new ArrayList<SequenceDto>();
			 List<SequenceDto> sequencesAffiche = transformerSequenceToSequenceDto(sequencesDto);
			 model.addAttribute("sequencesAffiche", sequencesAffiche);
			List<String> nomAgences=agenceRepository.findAllNomDirects(estSupprimer);	
			model.addAttribute("nomAgences", nomAgences);
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("gestionMenuUniteTechnique", "gestionMenuUniteTechnique");
			model.addAttribute("accueilDeuxUniteTechnique", "accueilDeuxUniteTechnique");
			model.addAttribute("cheminAjouterSequencePolice", "Positionner une Sequence >");
			model.addAttribute("titre", " Gestion de Sequence ");										
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("formulaireGestionSequencePolice", "formulaireGestionSequencePolice");
			model.addAttribute("listeSequencePolice", "listeSequencePolice");
			model.addAttribute("gestionSequencePolice", "gestionSequencePolice");
			model.addAttribute("menuNavigation", "menuNavigation");
	        return "espaceUtilisateur";			
	    }
	 
	 @Transactional
	 private String renvoyerDerniereSequence(String sequenceSaisie) {
		 Boolean estSupprimer=false;
		 
		 Sequence sequenceObjet=sequenceRepository.findSequenceBySeq(sequenceSaisie);
		 	  
		 String sequencePoliceOrdreObjet=null;
		 if(sequenceObjet==null) {
			 String ordre="0001";
			 sequencePoliceOrdreObjet=ordre;			 
		 }
		 if(sequenceObjet!=null) {
			
				
			 List<String>  listeSeqPoliceParIdSequence=sequencePoliceRepository.listeSeqPoliceByIdSequence(sequenceObjet);
			 
			 String dernierSeqPolice=listeSeqPoliceParIdSequence.get(0);
			 
			 Integer beginIndex=3;
			 String ordre=dernierSeqPolice.substring(beginIndex);
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
		 return sequencePoliceOrdreObjet;
	 	}
	 
	 	@Transactional
		public List<SequenceDto> transformerSequenceToSequenceDto(List<SequenceDto> sequenceDtosAffiche) {
			Boolean estSupprimer=false;
			List<Sequence> sequences = sequenceRepository.findAllSequencesGouper(estSupprimer);
			for (Sequence sq : sequences) {
				SequenceDto objetSequenceDto = new SequenceDto();
				objetSequenceDto.setSeq(sq.getSeq());
				objetSequenceDto.setNomAgence(sq.getIdAgence().getNomDirect());
				objetSequenceDto.setDateCreation(sq.getDateCreation());
				sequenceDtosAffiche.add(objetSequenceDto);
			}
			return sequenceDtosAffiche;
			
		}
	 	 
		 @Transactional
		 @RequestMapping(value = {"/resultatAjoutSequencePolice" }, method = RequestMethod.POST)
		 public String resultatAjoutSequencePolice(Model model, @ModelAttribute("sequence") Sequence sequence, @ModelAttribute("sequenceDto") SequenceDto sequenceDto, @ModelAttribute("sequencePoliceDto") SequencePoliceDto sequencePoliceDto,  HttpServletRequest request, @ModelAttribute("sequencePolice") SequencePolice sequencePolice , HttpSession session) { 		 
		 String resultat=null;
			try {
				identifiantSession=session.getAttribute("identifiantSession").toString().trim();
			}
			catch(Exception e) {
				resultat="pageErreur";
				return resultat;
			}
			
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
			String seq=sequenceDto.getSeq().trim();	
			String nomAgence=sequenceDto.getNomAgence().trim();
			
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminAjouterSequencePolice", "Positionner une Sequence >");
			model.addAttribute("titre", " Gestion de Sequence ");
			model.addAttribute("gestionMenuUniteTechnique", "gestionMenuUniteTechnique");
			List<SequencePolice> sequencePolices=new ArrayList<SequencePolice>();
			List<Sequence> sequences=new ArrayList<Sequence>();
			Sequence sequenceSave=null;
			model.addAttribute("accueilDeuxUniteTechnique", "accueilDeuxUniteTechnique");
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("gestionSequencePolice", "gestionSequencePolice");
			model.addAttribute("menuNavigation", "menuNavigation");
			List<Sequence> sequencesGrouper =sequenceRepository.findAllSequencesGouper(estSupprimer);
			model.addAttribute("gestionSequencePolice", "gestionSequencePolice");
			
			if( seq != null && seq.length()>0 ) {
							Sequence seqExistant=null;
							sequenceSave=sequenceRepository.findSequenceBySeq(seq);
							
							session.setAttribute("seq", seq);
							session.setAttribute("nomAgence", nomAgence);
							model.addAttribute("seq", session.getAttribute("seq"));	
							model.addAttribute("nomAgence", session.getAttribute("nomAgence"));	
							
							
							if(sequenceSave==null) {	
								sequence.setSeq(seq);
								sequence.setDateCreation(new Date());
								Agence idAgence=agenceRepository.findAgenceByNomDirect(nomAgence);
								sequence.setIdAgence(idAgence);
								sequence.setEstSupprimer(estSupprimer);
								sequenceRepository.save(sequence);
								model.addAttribute("ajoutSuccesSequencePolice", "Une sequence de Police ajoutée avec succès");
								model.addAttribute("listeSequencePolice", "listeSequencePolice");
								List<SequenceDto> sequencesDto=new ArrayList<SequenceDto>();
								 List<SequenceDto> sequencesAffiche = transformerSequenceToSequenceDto(sequencesDto);
								 model.addAttribute("sequencesAffiche", sequencesAffiche);
								return "espaceUtilisateur";		
							}
													
			}
			List<SequenceDto> sequencesDto=new ArrayList<SequenceDto>();
			 List<SequenceDto> sequencesAffiche = transformerSequenceToSequenceDto(sequencesDto);
			 model.addAttribute("sequencesAffiche", sequencesAffiche);
			 model.addAttribute("listeSequencePolice", "listeSequencePolice");
			model.addAttribute("erreurSequence", " Sequence déjà attribuée");	
			model.addAttribute("formErreurSequencePolice", "formErreurSequencePolice");	
			List<String> nomAgences=agenceRepository.findAllNomDirects(estSupprimer);	
			model.addAttribute("nomAgences", nomAgences);
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("gestionMenuUniteTechnique", "gestionMenuUniteTechnique");
			model.addAttribute("accueilDeuxUniteTechnique", "accueilDeuxUniteTechnique");
			model.addAttribute("cheminAjouterSequencePolice", "Positionner une Sequence >");
			model.addAttribute("titre", " Gestion de Sequence de Police");										
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("formulaireGestionSequencePolice", "formulaireGestionSequencePolice");
			model.addAttribute("listeSequencePolice", "listeSequencePolice");
			model.addAttribute("gestionSequencePolice", "gestionSequencePolice");
			model.addAttribute("menuNavigation", "menuNavigation");
	        return "espaceUtilisateur";			
	 }
		 
		 @Transactional
		 @RequestMapping(value = {"/rechercheSequencePolice" }, method = RequestMethod.GET)
	    public String formulaireNumeroRechercheSequencePolice(Model model, @ModelAttribute("sequence") Sequence sequence ,HttpServletRequest request  , @ModelAttribute("sequencePolice") SequencePolice sequencePolice, @ModelAttribute("sequenceDto") SequencePoliceDto sequenceDto, HttpSession sessionUtilisateur, HttpSession session) {
			String resultat=null;
			try {
				identifiantSession=session.getAttribute("identifiantSession").toString().trim();
			}
			catch(Exception e) {
				resultat="pageErreur";
				return resultat;
			}
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
			 Page<SequencePolice> seqPolicePage = sequencePoliceRepository.findAllSequencePolices(estSupprimer, pageable);
			 List<String> sequences=sequenceRepository.findAllSeq();
			 model.addAttribute("sequences", sequences);
			 model.addAttribute("sequencePolices", seqPolicePage);
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionSequencePolice", "Gestion Sequence Police >");
			model.addAttribute("cheminModifierSequencePolice", "Rechercherr un numero Police >");
			model.addAttribute("titre", "Gestion de Sequence");
	
			model.addAttribute("formulaireNumeroRechercheSequencePolice", "formulaireNumeroRechercheSequencePolice");
			model.addAttribute("quatreBoutonSequencePolice", "quatreBoutonSequencePolice");
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("gestionSequencePolice", "gestionSequencePolice");
			model.addAttribute("menuNavigation", "menuNavigation");			
	        return "espaceUtilisateur";	
	    }
	 
		 
		 @Transactional
		 @RequestMapping(value = {"/resultatModifSequencePolice" }, method = RequestMethod.POST)
		 public String resultatModifSequencePolice(Model model,  HttpServletRequest request, @ModelAttribute("sequence") Sequence sequence , @ModelAttribute("sequenceDto") SequenceDto sequenceDto , @ModelAttribute("sequencePolice") SequencePolice sequencePolice , HttpSession session) { 		 
		 String resultat=null;
			try {
				identifiantSession=session.getAttribute("identifiantSession").toString().trim();
			}
			catch(Exception e) {
				resultat="pageErreur";
				return resultat;
			}
	
			String seq=sequenceDto.getSeq().trim();
			String nomAgence=sequenceDto.getNomAgence().trim();
			session.setAttribute("seq", seq);
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionSequencePolice", "Gestion Sequence Police >");
			model.addAttribute("cheminAjouterSequencePolice", "Ajouter une Sequence Police >");
			model.addAttribute("titre", " Gestion de Sequence ");
			boolean estSupprimer=false;
			List<SequencePolice> sequencePolices=new ArrayList<SequencePolice>();
			List<Sequence> sequences=new ArrayList<Sequence>();
		
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("gestionSequencePolice", "gestionSequencePolice");
			model.addAttribute("menuNavigation", "menuNavigation");
			String seqModif=session.getAttribute("seqARecuperer").toString().trim();
				if( seq != null && seq.length()>0 ) {
					
					Sequence sequenceAModifier=sequenceRepository.findSequenceModifBySeq(seq, seqModif, estSupprimer);
					Agence idAgence=agenceRepository.findAgenceByNomDirect(nomAgence);
								
					session.setAttribute("seq", seq);
					session.setAttribute("nomAgence", nomAgence);
					model.addAttribute("seq", session.getAttribute("seq"));	
					model.addAttribute("nomAgence", session.getAttribute("nomAgence"));	
					
					if(sequenceAModifier == null) {
						Sequence seqSave=sequenceRepository.findSequenceBySeq(seqModif);
						
						seqSave.setSeq(seq);
						seqSave.setDateCreation(new Date());
						seqSave.setIdAgence(idAgence);
						seqSave.setEstSupprimer(estSupprimer);
						sequenceRepository.save(seqSave);
						List<String> nomAgences=agenceRepository.findAllNomDirects(estSupprimer);	
						model.addAttribute("nomAgences", nomAgences);
						model.addAttribute("gestionSequencePolice", "gestionSequencePolice");
						model.addAttribute("cheminAccueil", "Accueil >");
						model.addAttribute("modifSequencePolice", "Sequence de Police modifiée avec succès");
						model.addAttribute("cheminGestionSequencePolice", "Gestion Sequence Police >");
						model.addAttribute("cheminModifierSequencePolice", "Modifier une Sequence >");
						model.addAttribute("titre", "Gestion de sequence de police");
						model.addAttribute("accueilDeuxUniteTechnique", "accueilDeuxUniteTechnique");
						model.addAttribute("gestionMenuUniteTechnique", "gestionMenuUniteTechnique");
						model.addAttribute("identifiantSession", identifiantSession);	
						model.addAttribute("actionQuatreBouton", "actionQuatreBouton");	
						model.addAttribute("menuNavigation", "menuNavigation");	
						model.addAttribute("listeSequencePolice", "listeSequencePolice");
						List<SequenceDto> sequencesDto=new ArrayList<SequenceDto>();
						List<SequenceDto> sequencesAffiche = transformerSequenceToSequenceDto(sequencesDto);
						model.addAttribute("sequencesAffiche", sequencesAffiche);
						
						
						return "espaceUtilisateur";	
						
					}
					
				}
							model.addAttribute("erreurSequence", " Sequence déjà attribuée");	
							model.addAttribute("formErreurSequencePolice", "formErreurSequencePolice");	
							List<String> nomAgences=agenceRepository.findAllNomDirects(estSupprimer);	
							model.addAttribute("nomAgences", nomAgences);
							model.addAttribute("gestionSequencePolice", "gestionSequencePolice");
							model.addAttribute("cheminAccueil", "Accueil >");
							model.addAttribute("cheminGestionSequencePolice", "Gestion Sequence Police >");
							model.addAttribute("cheminModifierSequencePolice", "Modifier une Sequence >");
							model.addAttribute("titre", "Gestion de sequence de police");
							model.addAttribute("accueilDeuxUniteTechnique", "accueilDeuxUniteTechnique");
							model.addAttribute("gestionMenuUniteTechnique", "gestionMenuUniteTechnique");
							model.addAttribute("identifiantSession", identifiantSession);	
							model.addAttribute("formulaireGestionModifSequencePolice", "formulaireGestionModifSequencePolice");
							model.addAttribute("actionQuatreBouton", "actionQuatreBouton");	
							model.addAttribute("menuNavigation", "menuNavigation");	
							model.addAttribute("listeSequencePolice", "listeSequencePolice");
							List<SequenceDto> sequencesDto=new ArrayList<SequenceDto>();
							List<SequenceDto> sequencesAffiche = transformerSequenceToSequenceDto(sequencesDto);
							model.addAttribute("sequencesAffiche", sequencesAffiche);
																
							return "espaceUtilisateur";		
		
	    		
	 }
	 
		 @Transactional
		 @RequestMapping(value = {"/envoiDonneeCacheeSequencePolice" }, method = RequestMethod.POST)
		    public String envoiDonneeCacheeSociete(Model model,  @ModelAttribute("sequence") Sequence sequence,  @ModelAttribute("sequenceDto") SequenceDto sequenceDto , @ModelAttribute("sequencePolice") SequencePolice sequencePolice, HttpSession session) {
				String resultat=null;
				try {
					identifiantSession=session.getAttribute("identifiantSession").toString().trim();
				}
				catch(Exception e) {
					resultat="pageErreur";
					return resultat;
				}	
				Boolean estSupprimer=false;
				String seq=sequence.getSeq().trim();
				session.setAttribute("seq", seq);
				session.setAttribute("seqARecuperer", seq);
				
				model.addAttribute("seq", session.getAttribute("seq"));
				List<String> nomAgences=agenceRepository.findAllNomDirects(estSupprimer);	
				model.addAttribute("nomAgences", nomAgences);
				model.addAttribute("gestionSequencePolice", "gestionSequencePolice");
				model.addAttribute("cheminAccueil", "Accueil >");
				model.addAttribute("cheminGestionSequencePolice", "Gestion Sequence Police >");
				model.addAttribute("cheminModifierSequencePolice", "Modifier une Sequence >");
				model.addAttribute("titre", "Gestion de sequence de police");
				model.addAttribute("accueilDeuxUniteTechnique", "accueilDeuxUniteTechnique");
				model.addAttribute("gestionMenuUniteTechnique", "gestionMenuUniteTechnique");
				model.addAttribute("identifiantSession", identifiantSession);	
				model.addAttribute("formulaireGestionModifSequencePolice", "formulaireGestionModifSequencePolice");
				model.addAttribute("actionQuatreBouton", "actionQuatreBouton");	
				model.addAttribute("menuNavigation", "menuNavigation");	
				model.addAttribute("listeSequencePolice", "listeSequencePolice");
				List<SequenceDto> sequencesDto=new ArrayList<SequenceDto>();
				List<SequenceDto> sequencesAffiche = transformerSequenceToSequenceDto(sequencesDto);
				model.addAttribute("sequencesAffiche", sequencesAffiche);
				
		        return "espaceUtilisateur";	
		    }
	 
		 @Transactional
		 @RequestMapping(value = {"/informationAgence" }, method = RequestMethod.GET)
		    public String informationAgence(Model model, HttpSession session, @ModelAttribute("clientDto") ClientDto clientDto,@ModelAttribute("agence") Agence agence,HttpServletRequest request ) { 
			 
			 String resultat=null;
				try {
					identifiantSession=session.getAttribute("identifiantSession").toString().trim();
				}
				catch(Exception e) {
					resultat="pageErreur";
					return resultat;
				}
				
				String nomAgence=agence.getNomDirect();
				
				
				 Boolean estSupprimer=false;
			     List<SequenceDto> sequencesDto=new ArrayList<SequenceDto>();
				 List<SequenceDto> sequencesAffiche = transformerSequenceToSequenceDto(sequencesDto);
				 model.addAttribute("sequencesAffiche", sequencesAffiche);
				List<String> nomAgences=agenceRepository.findAllNomDirects(estSupprimer);	
				model.addAttribute("nomAgences", nomAgences);
				model.addAttribute("cheminAccueil", "Accueil >");
				model.addAttribute("gestionMenuUniteTechnique", "gestionMenuUniteTechnique");
				model.addAttribute("accueilDeuxUniteTechnique", "accueilDeuxUniteTechnique");
				model.addAttribute("cheminAjouterSequencePolice", "S'informer sur une Agence >");
				model.addAttribute("titre", " Gestion de Sequence ");										
				model.addAttribute("identifiantSession", identifiantSession);
				model.addAttribute("formulaireInformationAgence", "formulaireInformationAgence");
				model.addAttribute("gestionSequencePolice", "gestionSequencePolice");
				model.addAttribute("menuNavigation", "menuNavigation");
		        return "espaceUtilisateur";			
		    }
		 
		 @Transactional
		 @RequestMapping(value = {"/sequenceEtDate" }, method = RequestMethod.POST)
		    public String sequenceEtDate(Model model, HttpSession session, @ModelAttribute("clientDto") ClientDto clientDto,@ModelAttribute("agence") Agence agence,HttpServletRequest request ) { 
			 
			 String resultat=null;
				try {
					identifiantSession=session.getAttribute("identifiantSession").toString().trim();
				}
				catch(Exception e) {
					resultat="pageErreur";
					return resultat;
				}
				
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
				
				
				String nomAgence=agence.getNomDirect();
				agence=agenceRepository.findAgenceByNomDirect(nomAgence);
				
				Page<Client> clientSoumis=clientRepository.findAllClientsPage(estSupprimer,agence, pageable);
				model.addAttribute("clientSoumis", clientSoumis);
				
			     List<SequenceDto> sequencesDto=new ArrayList<SequenceDto>();
				 List<SequenceDto> sequencesAffiche = transformerSequenceToSequenceDto(sequencesDto);
				 model.addAttribute("sequencesAffiche", sequencesAffiche);
				List<String> nomAgences=agenceRepository.findAllNomDirects(estSupprimer);	
				model.addAttribute("nomAgences", nomAgences);
				model.addAttribute("nomAgence", nomAgence);
				model.addAttribute("cheminAccueil", "Accueil >");
				model.addAttribute("listeNumeroPoliceParAgence", "listeNumeroPoliceParAgence");
				model.addAttribute("gestionMenuUniteTechnique", "gestionMenuUniteTechnique");
				model.addAttribute("accueilDeuxUniteTechnique", "accueilDeuxUniteTechnique");
				model.addAttribute("cheminAjouterSequencePolice", "S'informer sur une Agence >");
				model.addAttribute("titre", " Gestion de Sequence ");										
				model.addAttribute("identifiantSession", identifiantSession);
				model.addAttribute("formulaireInformationAgence", "formulaireInformationAgence");
				model.addAttribute("gestionSequencePolice", "gestionSequencePolice");
				model.addAttribute("menuNavigation", "menuNavigation");
		        return "espaceUtilisateur";			
		    }
		 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 

}
