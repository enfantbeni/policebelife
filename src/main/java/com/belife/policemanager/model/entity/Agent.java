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
@Table(name = "agent")
public class Agent implements Serializable {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idAgent;
	@Column(name = "nomAgent")
	private String nomAgent;
	@Column(name = "codeAgent")
	private String codeAgent;
	@Column(name = "estSupprime", nullable=false)
	private Boolean estSupprimer;
	
	@ManyToOne
    @JoinColumn(name = "idAgence")
    Agence idAgence;
	
	@OneToMany(targetEntity=com.belife.policemanager.model.entity.Client.class, mappedBy = "idClient")
	List<Client> clients;
	
	public Agent() {
		super();
	}
	public Integer getIdAgent() {
		return idAgent;
	}
	public void setIdAgent(Integer idAgent) {
		this.idAgent = idAgent;
	}
	public String getNomAgent() {
		return nomAgent;
	}
	public void setNomAgent(String nomAgent) {
		this.nomAgent = nomAgent;
	}
	public String getCodeAgent() {
		return codeAgent;
	}
	public void setCodeAgent(String codeAgent) {
		this.codeAgent = codeAgent;
	}
	public Boolean getEstSupprimer() {
		return estSupprimer;
	}
	public void setEstSupprimer(Boolean estSupprimer) {
		this.estSupprimer = estSupprimer;
	}
	public Agence getIdAgence() {
		return idAgence;
	}
	public void setIdAgence(Agence idAgence) {
		this.idAgence = idAgence;
	}
	public List<Client> getClients() {
		return clients;
	}
	public void setClients(List<Client> clients) {
		this.clients = clients;
	}
	
	

}
