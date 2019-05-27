package com.belife.policemanager.model.aaronentity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "aaron_agence")
public class AaronAgence implements Serializable{

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
	
	@Column(name = "status")
	private String status;

	public AaronAgence() {
		super();
		// TODO Auto-generated constructor stub
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
