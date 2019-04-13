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
@Table(name = "clientBanque")
public class ClientBanque implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idClientBanque;
	
	@ManyToOne
    @JoinColumn(name = "idClient")
    Client idClient;
	
    @ManyToOne
    @JoinColumn(name = "idBanque")
    Banque idBanque;

	public ClientBanque() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getIdClientBanque() {
		return idClientBanque;
	}

	public void setIdClientBanque(Integer idClientBanque) {
		this.idClientBanque = idClientBanque;
	}

	public Client getIdClient() {
		return idClient;
	}

	public void setIdClient(Client idClient) {
		this.idClient = idClient;
	}

	public Banque getIdBanque() {
		return idBanque;
	}

	public void setIdBanque(Banque idBanque) {
		this.idBanque = idBanque;
	}
	
    
    

}
