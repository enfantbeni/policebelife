package com.belife.policemanager.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "agence_banque")
public class AgenceBanque implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@Column(name = "cde_Guichet")
	private String codeGuichet;
	
	@Column(name = "cde_Apb")
	private String codeApb;
	
	@Column(name = "SS_grid")
	private String codeBanque;
	
	@Column(name = "libelle_agence")
	private String libelleAgence;
	
	@Column(name = "status")
	private String status;

//	@OneToMany(targetEntity=com.belife.policemanager.model.entity.ClientBanque.class, mappedBy = "idClient")
//	List<Client> clients;
		
	public AgenceBanque() {
		super();
	}

	public String getCodeGuichet() {
		return codeGuichet;
	}

	public void setCodeGuichet(String codeGuichet) {
		this.codeGuichet = codeGuichet;
	}	

	public String getLibelleAgence() {
		return libelleAgence;
	}

	public void setLibelleAgence(String libelleAgence) {
		this.libelleAgence = libelleAgence;
	}

	public String getCodeApb() {
		return codeApb;
	}



	public void setCodeApb(String codeApb) {
		this.codeApb = codeApb;
	}



	public String getCodeBanque() {
		return codeBanque;
	}



	public void setCodeBanque(String codeBanque) {
		this.codeBanque = codeBanque;
	}



	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}

}
