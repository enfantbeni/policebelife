package com.belife.policemanager.model.aaronentity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "aaron_utilisateur")
public class AaronUtilisateur implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
	
	@Column(name = "nomAgence")
	private String nomAgence;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "dateCreation")
	private Date dateCreation;	
	
//	@OneToMany(targetEntity=com.belife.policemanager.model.entity.UtilisateurRoles.class, mappedBy = "idRoles")
//	List<Roles> roles;
	@ManyToOne
	@JoinColumn(name = "idAgence")
	AaronAgence idAgence;
	
	
	public AaronUtilisateur() {
		super();
		// TODO Auto-generated constructor stub
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


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public Date getDateCreation() {
		return dateCreation;
	}


	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}


	public AaronAgence getIdAgence() {
		return idAgence;
	}


	public void setIdAgence(AaronAgence idAgence) {
		this.idAgence = idAgence;
	}
	
	
	

}
