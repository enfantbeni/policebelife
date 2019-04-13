package com.belife.policemanager.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.belife.policemanager.model.entity.SourcePolice;
import com.belife.policemanager.model.repository.RolesRepository;
import com.belife.policemanager.model.repository.SourcePoliceRepository;
import com.belife.policemanager.model.repository.UtilisateurRepository;

@Controller
public class SourcePrelevementController {
	
	 @Autowired
     UtilisateurRepository utilisateurRepository; 
	 
	 @Autowired
     RolesRepository rolesRepository;
	 
	 @Autowired
     SourcePoliceRepository sourcePoliceRepository;
	 
	 String identifiantSession=null;
	 
	 @Transactional
	 @RequestMapping(value = {"/ajoutSource" }, method = RequestMethod.GET)
	    public String authentification(Model model, HttpSession session) { 
		 
		 String resultat=null;
			try {
				identifiantSession=session.getAttribute("identifiantSession").toString().trim();
			}
			catch(Exception e) {
				resultat="pageErreur";
				return resultat;
			}
			Boolean estSupprimer=false;
			List<SourcePolice> sourcePolices=new ArrayList<SourcePolice>();
			sourcePolices=sourcePoliceRepository.findAllSourcePrelevement(estSupprimer);
			model.addAttribute("sourcePolices", sourcePolices);
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("formulaireGestionSource", "formulaireGestionSource");
			model.addAttribute("sourcePolice", "sourcePolice");
			model.addAttribute("menuNavigation", "menuNavigation");
	        return "espaceUtilisateur";
			
	    }
	 
	 @Transactional
	 @RequestMapping(value = {"/resultatAjoutSource" }, method = RequestMethod.POST)
	    public String resultatAjoutSource(Model model, @ModelAttribute("sourcePolice") SourcePolice sourcePolice , HttpSession session) { 
		 
		 String resultat=null;
			try {
				identifiantSession=session.getAttribute("identifiantSession").toString().trim();
			}
			catch(Exception e) {
				resultat="pageErreur";
				return resultat;
			}
			String source=sourcePolice.getSource().trim();
			String nature=sourcePolice.getGenrePolice().trim();
			List<SourcePolice> sourcePolices=new ArrayList<SourcePolice>();
			SourcePolice sourcePoliceSave=null;
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("sourcePolice", "sourcePolice");
			model.addAttribute("menuNavigation", "menuNavigation");
			Boolean estSupprimer=false;
			if( source != null && source.length() > 0 && source.length()<=40 && nature != null && nature.length() > 0 ) {
							
				sourcePoliceSave=sourcePoliceRepository.findBySource(source);
					if(sourcePoliceSave == null) {
						
						sourcePolice.setSource(source);
						sourcePolice.setGenrePolice(nature);
						sourcePolice.setEstSupprimer(estSupprimer);
						sourcePoliceRepository.save(sourcePolice);
						model.addAttribute("ajoutSuccesSource", "Source de prélèvement ajoutée avec succès");
						sourcePolices=sourcePoliceRepository.findAllSourcePrelevement(estSupprimer);
						model.addAttribute("sourcePolices", sourcePolices);
						
							return "espaceUtilisateur";
					}				
			}
			
			model.addAttribute("formErreurSource", "formErreur");
			if(source==null || source.length()==0 || source.length()>40) {
				model.addAttribute("sourceErreur", "Source invalide ou déjà existante");
			}
			sourcePolices=sourcePoliceRepository.findAllSourcePrelevement(estSupprimer);
			model.addAttribute("sourcePolices", sourcePolices);
			model.addAttribute("identifiantSession", identifiantSession);
			model.addAttribute("resultatAjoutSource", "resultatAjoutSource");
			model.addAttribute("formulaireGestionSource", "formulaireGestionSource");
			model.addAttribute("sourcePolice", "sourcePolice");
			model.addAttribute("menuNavigation", "menuNavigation");
	        return "espaceUtilisateur";
			
	    }

}
