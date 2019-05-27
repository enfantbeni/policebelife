package com.belife.policemanager.aaroncontroller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.belife.policemanager.model.aarondto.AaronSequenceDto;
import com.belife.policemanager.model.aaronentity.AaronAgence;
import com.belife.policemanager.model.aaronentity.AaronSequence;
import com.belife.policemanager.model.aaronrepository.AaronAgenceRepository;
import com.belife.policemanager.model.aaronrepository.AaronSequenceRepository;
import com.belife.policemanager.model.dto.ClientDto;
import com.belife.policemanager.model.dto.SequenceDto;
import com.belife.policemanager.model.dto.SequencePoliceDto;
import com.belife.policemanager.model.entity.Agence;
import com.belife.policemanager.model.entity.Client;
import com.belife.policemanager.model.entity.Sequence;
import com.belife.policemanager.model.entity.SequencePolice;
import com.belife.policemanager.model.repository.AgenceRepository;
import com.belife.policemanager.model.repository.BanqueRepository;
import com.belife.policemanager.model.repository.ClientRepository;
import com.belife.policemanager.model.repository.RolesRepository;
import com.belife.policemanager.model.repository.SequencePoliceRepository;
import com.belife.policemanager.model.repository.SequenceRepository;
import com.belife.policemanager.model.repository.SourcePoliceRepository;
import com.belife.policemanager.model.repository.UtilisateurRepository;


@Controller
public class AaronSequenceController {

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
	 AaronAgenceRepository aaronAgenceRepository;
	 
	 @Autowired
	 AaronSequenceRepository aaronSequenceRepository;
	 
	 @Autowired
	 SequenceRepository sequenceRepository;
	 
	 @Autowired
	 SequencePoliceRepository sequencePoliceRepository;
	 
	 @Autowired
	 ClientRepository clientRepository;
	 
	 String identifiantSession=null;
	 
	 
	 
	 @Transactional
	 @RequestMapping(value = {"/aaronGestionSequencePolice" }, method = RequestMethod.GET)
	    public String gestionSequencePolice(Model model, @ModelAttribute("aaronSequenceDto") AaronSequenceDto aaronSequenceDto , HttpServletRequest request , HttpSession session) { 
		 
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
		     PageRequest pageable = PageRequest.of(page, size);
		     List<AaronSequenceDto> sequencesDto=new ArrayList<AaronSequenceDto>();
			 List<AaronSequenceDto> sequencesAffiche = transformerSequenceToSequenceDto(sequencesDto);
			 model.addAttribute("sequencesAffiche", sequencesAffiche);
			
			model.addAttribute("accueilDeuxUniteTechnique", "accueilDeuxUniteTechnique");
			model.addAttribute("gestionMenuUniteTechnique", "gestionMenuUniteTechnique");
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionSequencePolice", "Gestion Sequence >");
			model.addAttribute("titre", "Gestion de Sequence");
			model.addAttribute("identifiantSession", identifiantSession);			
			model.addAttribute("gestionSequencePolice", "gestionSequencePolice");
			model.addAttribute("quatreBoutonSequencePolice", "quatreBoutonSequencePolice");
			model.addAttribute("listeSequencePolice", "listeSequencePolice");
			model.addAttribute("menuNavigation", "menuNavigation");												
	        return "aaron/espaceUtilisateur";			
	    }
	 
	 
	 @Transactional
	 @RequestMapping(value = {"/aaronAjoutSequencePolice" }, method = RequestMethod.GET)
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
			 PageRequest pageable = PageRequest.of(page, size);
		     List<AaronSequenceDto> sequencesDto=new ArrayList<AaronSequenceDto>();
			 List<AaronSequenceDto> sequencesAffiche = transformerSequenceToSequenceDto(sequencesDto);
			 model.addAttribute("sequencesAffiche", sequencesAffiche);
		     
			List<String> nomAgences=aaronAgenceRepository.findAllNomDirects();	
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
	        return "aaron/espaceUtilisateur";			
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
		public List<AaronSequenceDto> transformerSequenceToSequenceDto(List<AaronSequenceDto> sequenceDtosAffiche) {
			Boolean estSupprimer=false;
			List<AaronSequence> sequences = aaronSequenceRepository.findAllSequencesGouper(estSupprimer);
			for (AaronSequence sq : sequences) {
				AaronSequenceDto objetSequenceDto = new AaronSequenceDto();
				objetSequenceDto.setSeq(sq.getSeq());
				
				objetSequenceDto.setNomAgence(sq.getIdAgence().getNomDirect());
				
				objetSequenceDto.setDateCreation(sq.getDateCreation());
				sequenceDtosAffiche.add(objetSequenceDto);
			}
			return sequenceDtosAffiche;
			
		}
	 	 
		 @Transactional
		 @RequestMapping(value = {"/aaronResultatAjoutSequencePolice" }, method = RequestMethod.POST)
		 public String resultatAjoutSequencePolice(Model model, @ModelAttribute("aaronSequence") AaronSequence aaronSequence, @ModelAttribute("aaronSequenceDto") AaronSequenceDto aaronSequenceDto, @ModelAttribute("sequencePoliceDto") SequencePoliceDto sequencePoliceDto,  HttpServletRequest request, @ModelAttribute("sequencePolice") SequencePolice sequencePolice , HttpSession session) { 		 
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
			String seq=aaronSequenceDto.getSeq().trim();	
			String nomAgence=aaronSequenceDto.getNomAgence().trim();
			
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminAjouterSequencePolice", "Positionner une Sequence >");
			model.addAttribute("titre", " Gestion de Sequence ");
			model.addAttribute("gestionMenuUniteTechnique", "gestionMenuUniteTechnique");
			List<SequencePolice> sequencePolices=new ArrayList<SequencePolice>();
			List<Sequence> sequences=new ArrayList<Sequence>();
			AaronSequence sequenceSave=null;
			model.addAttribute("accueilDeuxUniteTechnique", "accueilDeuxUniteTechnique");
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("gestionSequencePolice", "gestionSequencePolice");
			model.addAttribute("menuNavigation", "menuNavigation");
			List<Sequence> sequencesGrouper =sequenceRepository.findAllSequencesGouper(estSupprimer);
			model.addAttribute("gestionSequencePolice", "gestionSequencePolice");
			
			if( seq != null && seq.length()>0 ) {
							Sequence seqExistant=null;
							sequenceSave=aaronSequenceRepository.findSequenceBySeq(seq);
							
							session.setAttribute("seq", seq);
							session.setAttribute("nomAgence", nomAgence);
							model.addAttribute("seq", session.getAttribute("seq"));	
							model.addAttribute("nomAgence", session.getAttribute("nomAgence"));	
							
							
							if(sequenceSave==null) {	
								aaronSequence.setSeq(seq);
								aaronSequence.setDateCreation(new Date());
								AaronAgence idAgence=aaronAgenceRepository.findAgenceByNomDirect(nomAgence);
								aaronSequence.setIdAgence(idAgence);
								aaronSequence.setEstSupprimer(estSupprimer);
								aaronSequenceRepository.save(aaronSequence);
								model.addAttribute("ajoutSuccesSequencePolice", "Une sequence de Police ajoutée avec succès");
								model.addAttribute("listeSequencePolice", "listeSequencePolice");
								List<AaronSequenceDto> sequencesDto=new ArrayList<AaronSequenceDto>();
								 List<AaronSequenceDto> sequencesAffiche = transformerSequenceToSequenceDto(sequencesDto);
								 model.addAttribute("sequencesAffiche", sequencesAffiche);
								return "aaron/espaceUtilisateur";		
							}
													
			}
			List<AaronSequenceDto> sequencesDto=new ArrayList<AaronSequenceDto>();
			 List<AaronSequenceDto> sequencesAffiche = transformerSequenceToSequenceDto(sequencesDto);
			 model.addAttribute("sequencesAffiche", sequencesAffiche);
			 model.addAttribute("listeSequencePolice", "listeSequencePolice");
			model.addAttribute("erreurSequence", " Sequence déjà attribuée");	
			model.addAttribute("formErreurSequencePolice", "formErreurSequencePolice");	
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
	        return "aaron/espaceUtilisateur";			
	 }
		 
		 @Transactional
		 @RequestMapping(value = {"/aaronRechercheSequencePolice" }, method = RequestMethod.GET)
	    public String formulaireNumeroRechercheSequencePolice(Model model, @ModelAttribute("aaronSequence") AaronSequence aaronSequence ,HttpServletRequest request  ,  HttpSession sessionUtilisateur, HttpSession session) {
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
			 Page<AaronSequence> seqPolicePage = aaronSequenceRepository.findAllAaronSequencesPage(pageable);
			 List<String> sequences=aaronSequenceRepository.findAllSeq();
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
	        return "aaron/espaceUtilisateur";	
	    }
	 
		 
		 @Transactional
		 @RequestMapping(value = {"/aaronResultatModifSequencePolice" }, method = RequestMethod.POST)
		 public String resultatModifSequencePolice(Model model,  HttpServletRequest request, @ModelAttribute("aaronSequence") AaronSequence aaronSequence , @ModelAttribute("aaronSequenceDto") AaronSequenceDto aaronSequenceDto , @ModelAttribute("sequencePolice") SequencePolice sequencePolice , HttpSession session) { 		 
		 String resultat=null;
			try {
				identifiantSession=session.getAttribute("identifiantSession").toString().trim();
			}
			catch(Exception e) {
				resultat="pageErreur";
				return resultat;
			}
	
			String seq=aaronSequenceDto.getSeq().trim();
			String nomAgence=aaronSequenceDto.getNomAgence().trim();
			session.setAttribute("seq", seq);
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionSequencePolice", "Gestion Sequence Police >");
			model.addAttribute("cheminAjouterSequencePolice", "Ajouter une Sequence Police >");
			model.addAttribute("titre", " Gestion de Sequence ");
			boolean estSupprimer=false;
			List<SequencePolice> sequencePolices=new ArrayList<SequencePolice>();
			List<AaronSequence> sequences=new ArrayList<AaronSequence>();
		
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("gestionSequencePolice", "gestionSequencePolice");
			model.addAttribute("menuNavigation", "menuNavigation");
			String seqModif=session.getAttribute("seqARecuperer").toString().trim();
				if( seq != null && seq.length()>0 ) {
					
					AaronSequence sequenceAModifier=aaronSequenceRepository.findSequenceModifBySeq(seq, seqModif, estSupprimer);
					AaronAgence idAgence=aaronAgenceRepository.findAgenceByNomDirect(nomAgence);
								
					session.setAttribute("seq", seq);
					session.setAttribute("nomAgence", nomAgence);
					model.addAttribute("seq", session.getAttribute("seq"));	
					model.addAttribute("nomAgence", session.getAttribute("nomAgence"));	
					
					if(sequenceAModifier == null) {
						AaronSequence seqSave=aaronSequenceRepository.findSequenceBySeq(seqModif);
						
						seqSave.setSeq(seq);
						seqSave.setDateCreation(new Date());
						seqSave.setIdAgence(idAgence);
						seqSave.setEstSupprimer(estSupprimer);
						aaronSequenceRepository.save(seqSave);
						List<String> nomAgences=aaronAgenceRepository.findAllNomDirects();	
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
						List<AaronSequenceDto> sequencesDto=new ArrayList<AaronSequenceDto>();
						List<AaronSequenceDto> sequencesAffiche = transformerSequenceToSequenceDto(sequencesDto);
						model.addAttribute("sequencesAffiche", sequencesAffiche);
						
						
						return "aaron/espaceUtilisateur";	
						
					}
					
				}
							model.addAttribute("erreurSequence", " Sequence déjà attribuée");	
							model.addAttribute("formErreurSequencePolice", "formErreurSequencePolice");	
							List<String> nomAgences=aaronAgenceRepository.findAllNomDirects();	
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
							List<AaronSequenceDto> sequencesDto=new ArrayList<AaronSequenceDto>();
							List<AaronSequenceDto> sequencesAffiche = transformerSequenceToSequenceDto(sequencesDto);
							model.addAttribute("sequencesAffiche", sequencesAffiche);
																
							return "aaron/espaceUtilisateur";		
		
	    		
	 }
	 
		 @Transactional
		 @RequestMapping(value = {"/aaronEnvoiDonneeCacheeSequencePolice" }, method = RequestMethod.POST)
		    public String envoiDonneeCacheeSociete(Model model,  @ModelAttribute("aaronSequence") AaronSequence aaronSequence,  @ModelAttribute("aaronSequenceDto") AaronSequenceDto aaronSequenceDto , @ModelAttribute("sequencePolice") SequencePolice sequencePolice, HttpSession session) {
				String resultat=null;
				try {
					identifiantSession=session.getAttribute("identifiantSession").toString().trim();
				}
				catch(Exception e) {
					resultat="pageErreur";
					return resultat;
				}	
				Boolean estSupprimer=false;
				String seq=aaronSequence.getSeq().trim();
				session.setAttribute("seq", seq);
				session.setAttribute("seqARecuperer", seq);
				
				model.addAttribute("seq", session.getAttribute("seq"));
				List<String> nomAgences=aaronAgenceRepository.findAllNomDirects();	
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
				List<AaronSequenceDto> sequencesDto=new ArrayList<AaronSequenceDto>();
				List<AaronSequenceDto> sequencesAffiche = transformerSequenceToSequenceDto(sequencesDto);
				model.addAttribute("sequencesAffiche", sequencesAffiche);
				
		        return "aaron/espaceUtilisateur";	
		    }
	 
		 @Transactional
		 @RequestMapping(value = {"/aaronInformationAgence" }, method = RequestMethod.GET)
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
			     List<AaronSequenceDto> sequencesDto=new ArrayList<AaronSequenceDto>();
				 List<AaronSequenceDto> sequencesAffiche = transformerSequenceToSequenceDto(sequencesDto);
				 model.addAttribute("sequencesAffiche", sequencesAffiche);
				List<String> nomAgences=aaronAgenceRepository.findAllNomDirects();	
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
		        return "aaron/espaceUtilisateur";			
		    }
		 
		 @Transactional
		 @RequestMapping(value = {"/aaronSequenceEtDate" }, method = RequestMethod.POST)
		    public String sequenceEtDate(Model model, HttpSession session, @ModelAttribute("clientDto") ClientDto clientDto,@ModelAttribute("aaronAgence") AaronAgence aaronAgence,HttpServletRequest request ) { 
			 
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
				
				String status="A";
				String nomAgence=aaronAgence.getNomDirect();
				aaronAgence=aaronAgenceRepository.findAgenceByNomDirect(nomAgence);
				String codeAgence=aaronAgence.getCodeAgence();
				
				List<AaronSequence> aaronceSequenceAgence=aaronSequenceRepository.findAllSequenceByIdAgence(aaronAgence);
				
				model.addAttribute("aaronceSequenceAgence", aaronceSequenceAgence);
				
			    List<AaronSequenceDto> sequencesDto=new ArrayList<AaronSequenceDto>();
				List<AaronSequenceDto> sequencesAffiche = transformerSequenceToSequenceDto(sequencesDto);
				model.addAttribute("sequencesAffiche", sequencesAffiche);
				List<String> nomAgences=aaronAgenceRepository.findAllNomDirects();	
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
		        return "aaron/espaceUtilisateur";			
		    }
		 
		 
		 
		  @Transactional
			@RequestMapping(value = {"/aaronAccueilDeuxUniteTechnique" }, method = RequestMethod.GET)
		    public String accueilDeux(Model model, HttpSession session) { 
				String resultat=null;
				try {
					identifiantSession=session.getAttribute("identifiantSession").toString().trim();
				}
				catch(Exception e) {
					resultat="pageErreur";
					return resultat;
				}		
//				gestion Menu 			
				model.addAttribute("gestionMenuUniteTechnique", "gestionMenuUniteTechnique");
				model.addAttribute("accueilUniteTechnique", "accueilUniteTechnique");	
				model.addAttribute("accueilDeuxUniteTechnique", "accueilDeuxUniteTechnique");		
				model.addAttribute("accueilUniteTechniqueMessage", "accueilUniteTechniqueMessage");	
				model.addAttribute("identifiantSession", identifiantSession);
				model.addAttribute("cheminAccueil", "Accueil >");
				
				
			    return "aaron/espaceUtilisateur";
				
		        
		    }
		 
	 
	 

}
