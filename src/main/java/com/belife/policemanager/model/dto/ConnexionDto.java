package com.belife.policemanager.model.dto;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.belife.policemanager.model.entity.Utilisateur;

public class ConnexionDto {

	private String tempsConnexion;
	

	private String adresseMachine;
		
	private String nomUtilisateur;

	public ConnexionDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getTempsConnexion() {
		return tempsConnexion;
	}

	public void setTempsConnexion(String tempsConnexion) {
		this.tempsConnexion = tempsConnexion;
	}

	public String getAdresseMachine() {
		return adresseMachine;
	}

	public void setAdresseMachine(String adresseMachine) {
		this.adresseMachine = adresseMachine;
	}

	public String getNomUtilisateur() {
		return nomUtilisateur;
	}

	public void setNomUtilisateur(String nomUtilisateur) {
		this.nomUtilisateur = nomUtilisateur;
	}
	
	

}
