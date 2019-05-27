package com.belife.policemanager.model.aaronentity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "societe")
public class AaronSociete implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "BGGRPID")
	private String codeSociete;
	
	@Column(name = "BGZIP")
	private String telephone;
	
	@Column(name = "BGNAME")
	private String nomSociete;
	
	@Column(name = "BGSTATUS")
	private String status;

	public AaronSociete() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getCodeSociete() {
		return codeSociete;
	}

	public void setCodeSociete(String codeSociete) {
		this.codeSociete = codeSociete;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getNomSociete() {
		return nomSociete;
	}

	public void setNomSociete(String nomSociete) {
		this.nomSociete = nomSociete;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
