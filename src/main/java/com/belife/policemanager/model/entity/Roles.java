package com.belife.policemanager.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Roles implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column(name = "titre_module")
	private String titreModule;
	@Column(name = "numero_module")
	private String numeroModule;
	@Column(name = "estSupprime")
	private Boolean estSupprimer;
	@OneToMany(targetEntity=com.belife.policemanager.model.entity.UtilisateurRoles.class, mappedBy = "idUtilisateur")
	List<Utilisateur> utilisateurs;

	public Roles() {
		super();
	}
	
	public Roles(String titreModule, String numeroModule, Boolean estSupprimer, Utilisateur idUtilisateur,
			List<Utilisateur> utilisateurs) {
		super();
		this.titreModule = titreModule;
		this.numeroModule = numeroModule;
		this.estSupprimer = estSupprimer;
		this.utilisateurs = utilisateurs;
	}
	
	

	public Roles(Integer id, String titreModule, String numeroModule, Boolean estSupprimer) {
		super();
		this.id = id;
		this.titreModule = titreModule;
		this.numeroModule = numeroModule;
		this.estSupprimer = estSupprimer;
	}

	public Roles(String titreModule, String numeroModule, Boolean estSupprimer) {
		super();
		this.titreModule = titreModule;
		this.numeroModule = numeroModule;
		this.estSupprimer = estSupprimer;
	}

	public Roles(String titreModule, String numeroModule) {
		super();
		this.titreModule = titreModule;
		this.numeroModule = numeroModule;
	}

	public Roles(Integer id, String titreModule, Boolean estSupprimer) {
		super();
		this.id = id;
		this.titreModule = titreModule;
		this.estSupprimer = estSupprimer;
	}
	public Roles(Integer id, String titreModule) {
		super();
		this.id = id;
		this.titreModule = titreModule;
	}
	public Roles(Integer id, Boolean estSupprimer) {
		super();
		this.id = id;
		this.estSupprimer = estSupprimer;
	}
	public Roles(String titreModule, Boolean estSupprimer) {
		super();
		this.titreModule = titreModule;
		this.estSupprimer = estSupprimer;
	}
	public Roles(Integer id) {
		super();
		this.id = id;
	}
	
	public String getNumeroModule() {
		return numeroModule;
	}

	public void setNumeroModule(String numeroModule) {
		this.numeroModule = numeroModule;
	}

	public Roles(String titreModule) {
		super();
		this.titreModule = titreModule;
	}
	public Roles(Boolean estSupprimer) {
		super();
		this.estSupprimer = estSupprimer;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitreModule() {
		return titreModule;
	}
	public void setTitreModule(String titreModule) {
		this.titreModule = titreModule;
	}
	public Boolean getEstSupprimer() {
		return estSupprimer;
	}
	public void setEstSupprimer(Boolean estSupprimer) {
		this.estSupprimer = estSupprimer;
	}

	public List<Utilisateur> getUtilisateurs() {
		return utilisateurs;
	}

	public void setUtilisateurs(List<Utilisateur> utilisateurs) {
		this.utilisateurs = utilisateurs;
	}
	
}
