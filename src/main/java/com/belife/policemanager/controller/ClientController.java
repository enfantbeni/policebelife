package com.belife.policemanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.belife.policemanager.model.repository.BanqueRepository;
import com.belife.policemanager.model.repository.RolesRepository;
import com.belife.policemanager.model.repository.SourcePoliceRepository;
import com.belife.policemanager.model.repository.UtilisateurRepository;

@Controller
public class ClientController {
	 @Autowired
     UtilisateurRepository utilisateurRepository; 
	 
	 @Autowired
     RolesRepository rolesRepository;
	 
	 @Autowired
     SourcePoliceRepository sourcePoliceRepository;
	 
	 @Autowired
     BanqueRepository banqueRepository;
	 
	 
	 
	 String identifiantSession=null;
	 
	 
	 

}
