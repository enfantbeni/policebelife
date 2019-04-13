package com.belife.policemanager.model.dto;

public class UtilisateurDto {
		
	private Integer idUtilisateur;

	private String nomEtPrenom;
	
	private String identifiant;
	
	private String fonction;
	
	private String password;
	
	private String nomAgence;

	private Boolean estSupprimer;

	public UtilisateurDto() {
		super();
	}

	public Integer getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(Integer idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public String getNomEtPrenom() {
		return nomEtPrenom;
	}

	public void setNomEtPrenom(String nomEtPrenom) {
		this.nomEtPrenom = nomEtPrenom;
	}

	public String getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}

	public String getFonction() {
		return fonction;
	}

	public void setFonction(String fonction) {
		this.fonction = fonction;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNomAgence() {
		return nomAgence;
	}

	public void setNomAgence(String nomAgence) {
		this.nomAgence = nomAgence;
	}

	public Boolean getEstSupprimer() {
		return estSupprimer;
	}

	public void setEstSupprimer(Boolean estSupprimer) {
		this.estSupprimer = estSupprimer;
	}
	
}
