package com.belife.policemanager.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.belife.policemanager.model.dto.AgentDto;
import com.belife.policemanager.model.dto.SequencePoliceDto;
import com.belife.policemanager.model.entity.Agence;
import com.belife.policemanager.model.entity.Agent;
import com.belife.policemanager.model.entity.Client;
import com.belife.policemanager.model.entity.Sequence;
import com.belife.policemanager.model.entity.SequencePolice;
import com.belife.policemanager.model.entity.Societe;
import com.belife.policemanager.model.repository.AgenceRepository;
import com.belife.policemanager.model.repository.BanqueRepository;
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
			 Page<SequencePolice> seqPolicePage = sequencePoliceRepository.findAllSequencePolices(estSupprimer, pageable);
//			 clientPage = clientRepository.findAllClientsPage(estSupprimer, pageable);
			 model.addAttribute("sequencePolices", seqPolicePage);	
			List<Sequence> sequences=new ArrayList<Sequence>();
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionSequencePolice", "Gestion Sequence Police >");
			model.addAttribute("cheminAjouterSequencePolice", "Ajouter une Sequence Police >");
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
	 
	 
//	 	@Transactional
//		public Page<SequencePoliceDto> transformerSequencePoliceToSequencePoliceDto(Page<SequencePoliceDto> sequencePoliceDtosAffiche, HttpServletRequest request) {
//			Boolean estSupprimer=false;
//			
//			int page = 0;
//			 int size = 4;
//			 if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
//		            page = Integer.parseInt(request.getParameter("page")) - 1;
//		        }
//			 if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
//		            size = Integer.parseInt(request.getParameter("size"));
//		        }
//		     PageRequest pageable = PageRequest.of(page, 4);
//			 Page<SequencePolice> seqPolicePage = sequencePoliceRepository.findAllSequencePolices(estSupprimer, pageable);
//			
//
//			for (SequencePolice sp : seqPolicePage) {
//				SequencePoliceDto spDto = new SequencePoliceDto();
//				spDto.setSeq(sp.getIdSequence().getSeq());
//				spDto.setSeqPolice(sp.getSeqPolice());				
//				sequencePoliceDtosAffiche.and(spDto);
//				sequencePoliceDtosAffiche.
//			}
//			return sequencePoliceDtosAffiche;		
//		}
	 
	 
		 @Transactional
		 @RequestMapping(value = {"/resultatAjoutSequencePolice" }, method = RequestMethod.POST)
		 public String resultatAjoutSequencePolice(Model model, @ModelAttribute("sequence") Sequence sequence , @ModelAttribute("sequenceDto") SequencePoliceDto sequenceDto,  HttpServletRequest request, @ModelAttribute("sequencePolice") SequencePolice sequencePolice , HttpSession session) { 		 
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
			 model.addAttribute("sequencePolices", seqPolicePage);
		    
			String seq=sequence.getSeq().trim();			
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionSequencePolice", "Gestion Sequence Police >");
			model.addAttribute("cheminAjouterSequencePolice", "Ajouter une Sequence Police >");
			model.addAttribute("titre", " Gestion de Sequence ");
			
			List<SequencePolice> sequencePolices=new ArrayList<SequencePolice>();
			List<Sequence> sequences=new ArrayList<Sequence>();
			Sequence sequenceSave=null;
		
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("gestionSequencePolice", "gestionSequencePolice");
			model.addAttribute("menuNavigation", "menuNavigation");
			if( seq != null && seq.length()>0 ) {
							Sequence seqExistant=null;
							sequenceSave=sequenceRepository.findSequenceBySeq(seq);
							
							String seqOrdreChaine=renvoyerDerniereSequence(seq);
							
							sequence.setSeq(seq);
							sequence.setDateCreation(new Date());
							sequence.setEstSupprimer(estSupprimer);
							if(sequenceSave==null) {				
							sequenceRepository.save(sequence);
							seqExistant=sequenceRepository.findSequenceBySeq(seq);
							}
							if(sequenceSave!=null) {
							  seqExistant=sequenceRepository.findSequenceBySeq(seq);
							}
							
							String seqPoliceOrdreChaine=null;
							
							seqPoliceOrdreChaine=seq.concat(seqOrdreChaine);
							sequencePolice.setDateCreation(new Date());
							sequencePolice.setEstSupprimer(estSupprimer);
							sequencePolice.setIdSequence(seqExistant);
							sequencePolice.setSeqPolice(seqPoliceOrdreChaine);
							sequencePoliceRepository.save(sequencePolice);
							
							model.addAttribute("listeSequencePolice", "listeSequencePolice");
							model.addAttribute("gestionSequencePolice", "gestionSequencePolice");
						
							model.addAttribute("ajoutSuccesSequencePolice", "Une sequence de Police ajoutée avec succès");							
							seqPolicePage = sequencePoliceRepository.findAllSequencePolices(estSupprimer, pageable);
							model.addAttribute("sequencePolices", seqPolicePage);											
							return "espaceUtilisateur";		
			}
			
			sequencePolices=sequencePoliceRepository.findAllSequencePolices(estSupprimer);
			model.addAttribute("sequencePolices", sequencePolices);
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("resultatAjoutSequencePolice", "resultatAjoutSequencePolice");
			model.addAttribute("formulaireGestionSequencePolice", "formulaireGestionSequencePolice");
			model.addAttribute("listeSequencePolice", "listeSequencePolice");
			model.addAttribute("gestionSequencePolice", "gestionSequencePolice");
			model.addAttribute("menuNavigation", "menuNavigation");
	        return "espaceUtilisateur";			
	 }
	 
	 @Transactional
	 @RequestMapping(value = {"/modifierSequencePolice" }, method = RequestMethod.GET)
	    public String modifierSequencePolice(Model model, HttpSession session, HttpServletRequest request ) { 
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
//			 clientPage = clientRepository.findAllClientsPage(estSupprimer, pageable);
			 model.addAttribute("sequencePolices", seqPolicePage);
			 
			List<SequencePolice> sequencePolices=new ArrayList<SequencePolice>();
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionSequencePolice", "Gestion Sequence Police >");
			model.addAttribute("cheminModifierSequencePolice", "Modifier une Sequence Police >");
			model.addAttribute("titre", "Gestion de Sequence");
			model.addAttribute("formulaireNumeroModifSequencePolice", "formulaireNumeroModifSequencePolice");
			model.addAttribute("quatreBoutonSequencePolice", "quatreBoutonSequencePolice");
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("listeSequencePolice", "listeSequencePolice");
			model.addAttribute("gestionSequencePolice", "gestionSequencePolice");
			model.addAttribute("menuNavigation", "menuNavigation");
	        return "espaceUtilisateur";	
	    }
	 
	 
		 @Transactional
		 @RequestMapping(value = {"/formulaireNumeroModifSequencePolice" }, method = RequestMethod.POST)
	    public String formulaireNumeroModifSequencePolice(Model model, @ModelAttribute("sequence") Sequence sequence ,HttpServletRequest request  , @ModelAttribute("sequencePolice") SequencePolice sequencePolice, @ModelAttribute("sequenceDto") SequencePoliceDto sequenceDto, HttpSession sessionUtilisateur, HttpSession session) {
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
//			 clientPage = clientRepository.findAllClientsPage(estSupprimer, pageable);
			 model.addAttribute("sequencePolices", seqPolicePage);
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionSequencePolice", "Gestion Sequence Police >");
			model.addAttribute("cheminModifierSequencePolice", "Modifier une Sequence Police >");
			model.addAttribute("titre", "Gestion de Sequence");
			String seq=sequence.getSeq().trim();
			Sequence sequenceRecherche=sequenceRepository.findSequenceBySeq(seq);
			session.setAttribute("seqCache", seq);
			if( sequenceRecherche == null) {
				 return "redirect:/messageSequencePoliceNonExistante";  
			}
			
			model.addAttribute("titre", " Gestion d'Agence");
			session.setAttribute("seqCache", seq);	
			model.addAttribute("formulaireGestionModifSequencePolice", "formulaireGestionModifSequencePolice");
			model.addAttribute("quatreBoutonSequencePolice", "quatreBoutonSequencePolice");
			model.addAttribute("sequencePoliceRecherche", sequenceRecherche);
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("listeSequencePolice", "listeSequencePolice");
			model.addAttribute("gestionSequencePolice", "gestionSequencePolice");
			model.addAttribute("menuNavigation", "menuNavigation");			
	        return "espaceUtilisateur";	
	    }
		 
		 @Transactional
			@RequestMapping(value = "/sequencePolices")
		    public ModelAndView listArticlesPageByPage(@RequestParam(name="page", defaultValue="0") int page,@RequestParam(name="size", defaultValue="20") int size,  @ModelAttribute("sequencePolice") SequencePolice sequencePolice, Model model, HttpSession session, HttpServletRequest request) {
		        ModelAndView modelAndView = new ModelAndView("espaceUtilisateur");
		        Boolean estSupprimer=false;
		         size=20;
		        PageRequest pageable = PageRequest.of(page-1, size);
		        Page<SequencePolice> sequencePolices = sequencePoliceRepository.findAllSequencePolices(estSupprimer, pageable);
	 
				 if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
			            page = Integer.parseInt(request.getParameter("page")) - 1;
			        }
				 if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
			            size = Integer.parseInt(request.getParameter("size"));
			        }
				model.addAttribute("sequencePolices",  sequencePolices);
						
				model.addAttribute("cheminAccueil", "Accueil >");
				model.addAttribute("cheminGestionSequencePolice", "Gestion Sequence Police >");
				model.addAttribute("identifiantSession", identifiantSession);
				model.addAttribute("titre", "Liste de numeros de Police");
				model.addAttribute("quatreBoutonSequencePolice", "quatreBoutonSequencePolice");
				model.addAttribute("identifiantSession", identifiantSession);
				model.addAttribute("listeSequencePolice", "listeSequencePolice");
				model.addAttribute("gestionSequencePolice", "gestionSequencePolice");
				model.addAttribute("listeSequencePolice", "listeSequencePolice");
				model.addAttribute("menuNavigation", "menuNavigation");
		        return modelAndView;
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
		 @RequestMapping(value = {"/resultatRechercheSequencePolice" }, method = RequestMethod.POST)
		 public String resultatRechercheSequencePolice(Model model,  HttpServletRequest request, @ModelAttribute("sequence") Sequence sequence , @ModelAttribute("sequencePolice") SequencePolice sequencePolice , HttpSession session) { 		 
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
			 Page<SequencePolice> seqPolicePage = null;
	
			String seq=sequence.getSeq().trim();	
			session.setAttribute("seq", seq);
			model.addAttribute("cheminAccueil", "Accueil >");
			model.addAttribute("cheminGestionSequencePolice", "Gestion Sequence Police >");
			model.addAttribute("cheminAjouterSequencePolice", "Ajouter une Sequence Police >");
			model.addAttribute("titre", " Gestion de Sequence ");
			
			List<SequencePolice> sequencePolices=new ArrayList<SequencePolice>();
			List<Sequence> sequences=new ArrayList<Sequence>();
		
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("gestionSequencePolice", "gestionSequencePolice");
			model.addAttribute("menuNavigation", "menuNavigation");
							
						
							model.addAttribute("gestionSequencePolice", "gestionSequencePolice");
							model.addAttribute("resultatRechercheSequencePolice", "resultatRechercheSequencePolice");
							sequence=sequenceRepository.findSequenceBySeq(seq);
							seqPolicePage=sequencePoliceRepository.findAllSequencePolicesBySeq(sequence, estSupprimer, pageable );
							model.addAttribute("sequencePolices", seqPolicePage);											
							return "espaceUtilisateur";		
		
	    		
	 }
		 
		 @Transactional
			@RequestMapping(value = "/numeroPolices")
		    public ModelAndView numeroPolicesBySeq(@RequestParam(name="page", defaultValue="0") int page,@RequestParam(name="size", defaultValue="20") int size, @ModelAttribute("sequence") Sequence sequence, @ModelAttribute("sequencePolice") SequencePolice sequencePolice, Model model, HttpSession session, HttpServletRequest request) {
		        ModelAndView modelAndView = new ModelAndView("espaceUtilisateur");
		        Boolean estSupprimer=false;
		         size=20;
		        PageRequest pageable = PageRequest.of(page-1, size);
		        Page<SequencePolice> sequencePolices = sequencePoliceRepository.findAllSequencePolices(estSupprimer, pageable);
	 
				 if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
			            page = Integer.parseInt(request.getParameter("page")) - 1;
			        }
				 if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
			            size = Integer.parseInt(request.getParameter("size"));
			        }
				 String seq=session.getAttribute("seq").toString().trim();
				 sequence=sequenceRepository.findSequenceBySeq(seq);
				 Page<SequencePolice> seqPolicePage=sequencePoliceRepository.findAllSequencePolicesBySeq(sequence, estSupprimer, pageable );
				 model.addAttribute("sequencePolices", seqPolicePage);	
						
				model.addAttribute("cheminAccueil", "Accueil >");
				model.addAttribute("cheminGestionSequencePolice", "Gestion Sequence Police >");
				model.addAttribute("identifiantSession", identifiantSession);
				model.addAttribute("titre", "Liste de numeros de Police");
				model.addAttribute("quatreBoutonSequencePolice", "quatreBoutonSequencePolice");
				model.addAttribute("identifiantSession", identifiantSession);
				model.addAttribute("resultatRechercheSequencePolice", "resultatRechercheSequencePolice");
				model.addAttribute("gestionSequencePolice", "gestionSequencePolice");
				model.addAttribute("menuNavigation", "menuNavigation");
		        return modelAndView;
		    }
//		 @Transactional
//		 @RequestMapping(value = {"/envoiCacheModifSequencePolice" }, method = RequestMethod.POST)
//		    public String envoiDonneeCacheeSociete(Model model,  @ModelAttribute("sequence") Sequence sequence , @ModelAttribute("sequencePolice") SequencePolice sequencePolice, HttpSession session) {
//				String resultat=null;
//				try {
//					identifiantSession=session.getAttribute("identifiantSession").toString().trim();
//				}
//				catch(Exception e) {
//					resultat="pageErreur";
//					return resultat;
//				}		
//				model.addAttribute("cheminAccueil", "Accueil >");
//				model.addAttribute("cheminGestionSequencePolice", "Gestion Sequence Police >");
//				model.addAttribute("cheminModifierSequencePolice", "Modifier une Sequence Police >");
//				model.addAttribute("titre", "Gestion des numeros de police");
//
//				String codeSociete=societe.getCodeSociete().trim();
//				Societe societeRecherche=societeRepository.findSocieteByCode(codeSociete);
//				Boolean estSupprimer=false;
//				List<Societe> societes=new ArrayList<Societe>();
//				model.addAttribute("titre", " Gestion de Société");
//				societes=societeRepository.findAllSocietes(estSupprimer);
//				model.addAttribute("societes", societes);
//				session.setAttribute("codeSocieteCache", codeSociete);	
//				model.addAttribute("formulaireGestionModifSociete", "formulaireGestionModifSociete");
//				model.addAttribute("actionQuatreBouton", "actionQuatreBouton");	
//				model.addAttribute("societeRecherche", societeRecherche);
//				model.addAttribute("identifiantSession", identifiantSession);
//				model.addAttribute("listeSociete", "listeSociete");
//				model.addAttribute("gestionSociete", "gestionSociete");
//				model.addAttribute("menuNavigation", "menuNavigation");			
//		        return "espaceUtilisateur";	
//		    }
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 

}
