package com.belife.policemanager.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "connexion")
public class Connexion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idConnexion;
	
	@Column(name = "temps_connexion")
	private String tempsConnexion;
	
	@Column(name = "adresse_machine")
	private String adresseMachine;
		
	@ManyToOne
    @JoinColumn(name = "id_utilisateur")
    Utilisateur idUtilisateur;

	public Connexion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Connexion(String tempsConnexion, String adresseMachine, Utilisateur idUtilisateur) {
		super();
		this.tempsConnexion = tempsConnexion;
		this.adresseMachine = adresseMachine;
		this.idUtilisateur = idUtilisateur;
	}

	public Integer getIdConnexion() {
		return idConnexion;
	}

	public void setIdConnexion(Integer idConnexion) {
		this.idConnexion = idConnexion;
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

	public Utilisateur getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(Utilisateur idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}


}
