package com.belife.policemanager.model.entity;


import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "utilisateur")
public class Utilisateur implements Serializable {
private static final long serialVersionUID = -2343243243242432341L;
@Id
@Column(name = "id")
@GeneratedValue(strategy = GenerationType.AUTO)
private Integer idUtilisateur;
@Column(name = "nomEtPrenom")
private String nomEtPrenom;
@Column(name = "identifiant")
private String identifiant;
@Column(name = "fontion")
private String fonction;
@Column(name = "password")
private String password;
@Column(name = "estSupprime", nullable=false)
private Boolean estSupprimer;
@OneToMany(targetEntity=com.belife.policemanager.model.entity.UtilisateurRoles.class, mappedBy = "idRoles")
List<Roles> roles;
@ManyToOne
@JoinColumn(name = "idAgence")
Agence idAgence;

public Utilisateur(Integer id, String nomEtPrenom, String identifiant, String fonction,
		String password, Boolean estSupprimer) {
	super();
	this.idUtilisateur = id;
	this.nomEtPrenom = nomEtPrenom;
	this.identifiant = identifiant;
	this.fonction = fonction;
	this.password = password;
	this.estSupprimer = estSupprimer;
}
public Utilisateur(String nomEtPrenom, String identifiant, String fonction, String password,
		Boolean estSupprimer) {
	super();
	this.nomEtPrenom = nomEtPrenom;
	this.identifiant = identifiant;
	this.fonction = fonction;
	this.password = password;
	this.estSupprimer = estSupprimer;
}

//Setters, getters and constructors
public Utilisateur() {
	super();
}

public Utilisateur(Integer id) {
    this.idUtilisateur = id;
}

public Utilisateur(String nomEtPrenom, String identifiant, String password, Boolean estSupprimer) {
	super();
	this.nomEtPrenom = nomEtPrenom;
	this.identifiant = identifiant;
	this.password = password;
	this.estSupprimer = estSupprimer;
}
public Utilisateur(String nomEtPrenom, String password) {
	super();
	this.nomEtPrenom = nomEtPrenom;
	this.password = password;
}

public Integer getIdUtilisateur() {
    return idUtilisateur;
}
public void setIdUtilisateur(Integer id) {
    this.idUtilisateur = id;
}

public String getNomEtPrenom() {
    return nomEtPrenom;
}

public void setNomEtPrenom(String nomEtPrenom) {
    this.nomEtPrenom=nomEtPrenom;
}
public String getIdentifiant() {
	return identifiant;
}
public void setIdentifiant(String identifiant) {
	this.identifiant = identifiant;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public Boolean getEstSupprimer() {
	return estSupprimer;
}
public void setEstSupprimer(Boolean estSupprimer) {
	this.estSupprimer = estSupprimer;
}
public String getFonction() {
	return fonction;
}

public void setFonction(String fonction) {
	this.fonction = fonction;
}
public List<Roles> getRoles() {
	return roles;
}
public void setRoles(List<Roles> roles) {
	this.roles = roles;
}
public Agence getIdAgence() {
	return idAgence;
}
public void setIdAgence(Agence idAgence) {
	this.idAgence = idAgence;
}



}
