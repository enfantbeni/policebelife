package com.belife.policemanager.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.belife.policemanager.model.dto.ConnexionDto;
import com.belife.policemanager.model.entity.Agence;
import com.belife.policemanager.model.entity.Connexion;
import com.belife.policemanager.model.entity.Utilisateur;
import com.belife.policemanager.model.repository.ConnexionRepository;
import com.belife.policemanager.model.repository.UtilisateurRepository;

@Controller
public class ConnexionController {
	
	@Autowired
	UtilisateurRepository utilisateurRepository;
	
	@Autowired
	ConnexionRepository connexionRepository;

	public ConnexionController() {
		super();
		// TODO Auto-generated constructor stub
	}
	private String identifiantSession=null;

	@RequestMapping(value = {"/connexionUtilisateur" }, method = RequestMethod.GET)
    public String gestionEditionCompte(Model model,  HttpSession session) { 		
		String resultat=null;
		try {
			identifiantSession=session.getAttribute("identifiantSession").toString().trim();
		}
		catch(Exception e) {
			resultat="pageErreur";
			return resultat;
		}
		model.addAttribute("gestionMenuUtilisateur", "gestionMenuUtilisateur");
		model.addAttribute("gestionMenuBanque", "gestionMenuBanque");
		model.addAttribute("gestionMenuGuichet", "gestionMenuGuichet");
		model.addAttribute("gestionMenuAgence", "gestionMenuAgence");
		model.addAttribute("gestionMenuAgent", "gestionMenuAgent");
		model.addAttribute("gestionMenuSociete", "gestionMenuSociete");
		model.addAttribute("accueilDeux", "accueilDeux");
		model.addAttribute("gestionConnexion", "gestionConnexion");
		model.addAttribute("gestionMenuAgenceBanque", "gestionMenuAgenceBanque");
		
		List<Utilisateur> nomUtilisateurs=utilisateurRepository.findAllUtilisateur();
		String codeNom=new String();
		List<String> codeNomUtilisateurs=new ArrayList<String>();
		for(Utilisateur u: nomUtilisateurs) {
			codeNom=u.getIdentifiant().concat("-").concat(u.getNomEtPrenom());
			codeNomUtilisateurs.add(codeNom);
		}
		model.addAttribute("codeNomUtilisateurs", codeNomUtilisateurs);
		model.addAttribute("cheminAccueil",  "Accueil >");
		model.addAttribute("cheminConnexion",  "Connexion des utilisateurs >");
		model.addAttribute("connexionUtilisateur", "connexionUtilisateur");
		model.addAttribute("identifiantSession", identifiantSession);
		
		model.addAttribute("titre", " Connexion des utilisateur");
		model.addAttribute("menuNavigation", "menuNavigation");
        return "espaceUtilisateur";

	}
	
	@RequestMapping(value = {"/infosConnection" }, method = RequestMethod.POST)
    public String infosConnection(Model model,  HttpSession session,  @ModelAttribute("connexion") Connexion connexion,  @ModelAttribute("connexionDto") ConnexionDto connexionDto, @PageableDefault(size = 100) Pageable pageable, HttpServletRequest request) { 		
		String resultat=null;  
		try {
			identifiantSession=session.getAttribute("identifiantSession").toString().trim();
		}
		catch(Exception e) {
			resultat="pageErreur";
			return resultat;
		}
		model.addAttribute("gestionMenuUtilisateur", "gestionMenuUtilisateur");
		model.addAttribute("gestionMenuBanque", "gestionMenuBanque");
		model.addAttribute("gestionMenuGuichet", "gestionMenuGuichet");
		model.addAttribute("gestionMenuAgence", "gestionMenuAgence");
		model.addAttribute("gestionMenuAgent", "gestionMenuAgent");
		model.addAttribute("gestionMenuSociete", "gestionMenuSociete");
		model.addAttribute("accueilDeux", "accueilDeux");
		model.addAttribute("gestionConnexion", "gestionConnexion");
		model.addAttribute("gestionMenuAgenceBanque", "gestionMenuAgenceBanque");
		
		List<Utilisateur> nomUtilisateurs=utilisateurRepository.findAllUtilisateur();
		
		String codeNom=connexionDto.getNomUtilisateur();
		String[] codeNoms=codeNom.split("-");
		String identifiant=codeNoms[0];
		String nom=codeNoms[1];
		Utilisateur utilisateurConnexion=utilisateurRepository.findByIdentifiant(identifiant);
		List<Connexion> connexionUtilisateurs=connexionRepository.findByIdentifiant(utilisateurConnexion);
		System.out.println(" Connexion :"+utilisateurConnexion.getIdentifiant());
		
		model.addAttribute("connUtilisateurs", connexionUtilisateurs);
		model.addAttribute("cheminAccueil",  "Accueil >");
		model.addAttribute("cheminConnexion",  "Connexion des utilisateurs >");
		model.addAttribute("listeConnexion", "listeConnexion");
		model.addAttribute("identifiantSession", identifiantSession);
		
		model.addAttribute("titre", " Connexion des utilisateur");
		model.addAttribute("menuNavigation", "menuNavigation");
        return "espaceUtilisateur";

	}

}
