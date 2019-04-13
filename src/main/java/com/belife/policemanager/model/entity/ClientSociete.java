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
@Table(name = "clientSociete")
public class ClientSociete implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idClientSociete;
	
	@ManyToOne
    @JoinColumn(name = "idClient")
    Client idClient;
	
    @ManyToOne
    @JoinColumn(name = "idSociete")
    Societe idSociete;

	public ClientSociete() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getIdClientSociete() {
		return idClientSociete;
	}

	public void setIdClientSociete(Integer idClientSociete) {
		this.idClientSociete = idClientSociete;
	}

	public Client getIdClient() {
		return idClient;
	}

	public void setIdClient(Client idClient) {
		this.idClient = idClient;
	}

	public Societe getIdSociete() {
		return idSociete;
	}

	public void setIdSociete(Societe idSociete) {
		this.idSociete = idSociete;
	}
    
    
    

}
