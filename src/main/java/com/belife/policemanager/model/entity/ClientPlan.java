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
@Table(name = "clientPlan")
public class ClientPlan implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idClienPlan;
	
	
	@ManyToOne
    @JoinColumn(name = "idClient")
    Client idClient;
	
    @ManyToOne
    @JoinColumn(name = "idPlan")
    Plan idPlan;

	public ClientPlan() {
		super();
	}

	public Integer getIdClienPlan() {
		return idClienPlan;
	}

	public void setIdClienPlan(Integer idClienPlan) {
		this.idClienPlan = idClienPlan;
	}

	public Client getIdClient() {
		return idClient;
	}

	public void setIdClient(Client idClient) {
		this.idClient = idClient;
	}

	public Plan getIdPlan() {
		return idPlan;
	}

	public void setIdPlan(Plan idPlan) {
		this.idPlan = idPlan;
	}
    
    
    


}
