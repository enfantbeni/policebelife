package com.belife.policemanager.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "commercial")
public class Commercial implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "EAAGNT")
	private String codeAgent;
	
	@Column(name ="EACOMP")
	private String codeCompagnie;
	
	@Column(name ="EANAME")
	private String nomAgent;
		
	@Column(name ="EAADR1")
	private String adresse1;
	
	@Column(name ="EAADR2")
	private String adresse2;
	
	@Column(name ="EACITY")
	private String ville;
	
	@Column(name ="EASTAT")
	private String statAgent;
	
	@Column(name ="EAZIP")
	private String telephone;
	
	@Column(name ="EAAGEN")
	private String codeAgence;
	
	@Column(name ="EAFCR")
	private String financialCode;
	
	@Column(name ="EASTUS")
	private String status;
	
	
	public Commercial() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getCodeAgent() {
		return codeAgent;
	}

	public void setCodeAgent(String codeAgent) {
		this.codeAgent = codeAgent;
	}

	public String getCodeCompagnie() {
		return codeCompagnie;
	}

	public void setCodeCompagnie(String codeCompagnie) {
		this.codeCompagnie = codeCompagnie;
	}

	public String getNomAgent() {
		return nomAgent;
	}

	public void setNomAgent(String nomAgent) {
		this.nomAgent = nomAgent;
	}

	public String getAdresse1() {
		return adresse1;
	}

	public void setAdresse1(String adresse1) {
		this.adresse1 = adresse1;
	}

	public String getAdresse2() {
		return adresse2;
	}

	public void setAdresse2(String adresse2) {
		this.adresse2 = adresse2;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getCodeAgence() {
		return codeAgence;
	}

	public void setCodeAgence(String codeAgence) {
		this.codeAgence = codeAgence;
	}

	public String getFinancialCode() {
		return financialCode;
	}

	public void setFinancialCode(String financialCode) {
		this.financialCode = financialCode;
	}

	public String getStatAgent() {
		return statAgent;
	}

	public void setStatAgent(String statAgent) {
		this.statAgent = statAgent;
	}
	

}
