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
@Table(name = "agence")
public class Agence implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idAgence;
	@Column(name = "nomDirect")
	private String nomDirect;
	@Column(name = "codeAgence")
	private String codeAgence;
	@Column(name = "codeDirect")
	private String codeDirect;
	@Column(name = "estSupprime", nullable=false)
	private Boolean estSupprimer;
	
	@OneToMany(targetEntity=com.belife.policemanager.model.entity.Agent.class, mappedBy = "idAgent")
	List<Agent> agents;
	@OneToMany(targetEntity=com.belife.policemanager.model.entity.Utilisateur.class, mappedBy = "idUtilisateur")
	List<Utilisateur> utilisateurs;
	
	public Agence() {
		super();
	}

	public Integer getIdAgence() {
		return idAgence;
	}

	public void setIdAgence(Integer idAgence) {
		this.idAgence = idAgence;
	}

	public String getNomDirect() {
		return nomDirect;
	}

	public void setNomDirect(String nomDirect) {
		this.nomDirect = nomDirect;
	}

	public String getCodeAgence() {
		return codeAgence;
	}

	public void setCodeAgence(String codeAgence) {
		this.codeAgence = codeAgence;
	}

	public String getCodeDirect() {
		return codeDirect;
	}

	public void setCodeDirect(String codeDirect) {
		this.codeDirect = codeDirect;
	}

	public Boolean getEstSupprimer() {
		return estSupprimer;
	}

	public void setEstSupprimer(Boolean estSupprimer) {
		this.estSupprimer = estSupprimer;
	}

	public List<Agent> getAgents() {
		return agents;
	}

	public void setAgents(List<Agent> agents) {
		this.agents = agents;
	}
	
}
