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
@Table(name = "utilisateurModule")
public class UtilisateurRoles implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne
    @JoinColumn(name = "idUtilisateur")
    Utilisateur idUtilisateur;
	
    @ManyToOne
    @JoinColumn(name = "idRoles")
    Roles idRoles;

	public UtilisateurRoles(Integer id, Utilisateur idUtilisateur, Roles idRoles) {
		super();
		this.id = id;
		this.idUtilisateur = idUtilisateur;
		this.idRoles = idRoles;
	}

	public UtilisateurRoles(Integer id) {
		super();
		this.id = id;
	}
	public UtilisateurRoles(Utilisateur idUtilisateur, Roles idRoles) {
		super();
		this.idUtilisateur = idUtilisateur;
		this.idRoles = idRoles;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Utilisateur getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(Utilisateur idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public Roles getIdModule() {
		return idRoles;
	}

	public void setIdModule(Roles idRoles) {
		this.idRoles = idRoles;
	}

	public Roles getIdRoles() {
		return idRoles;
	}

	public void setIdRoles(Roles idRoles) {
		this.idRoles = idRoles;
	}
	
    	
}
