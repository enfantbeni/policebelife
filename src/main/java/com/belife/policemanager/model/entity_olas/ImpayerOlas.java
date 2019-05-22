package com.belife.policemanager.model.entity_olas;

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
@Table(name = "impayer_olas")
public class ImpayerOlas implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "police")
	private String police;
		
	@Column(name = "payeur")
	private String payeur;
	
	@Column(name = "libelleAgence")
    private String libelleAgence;
	
	@Column(name = "prime")
	private Long prime;
	
	@Column(name = "codeRejet")
	private String codeRejet;
	
	@Column(name = "libelleRejet")
	private String libelleRejet;
	
	@Column(name = "telephone")
	private String telephone;
	
	@Column(name = "payToDate")
	private String payToDate;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "dateSusp")
	private String dateSusp;
	
	public String getPolice() {
		return police;
	}

	public void setPolice(String police) {
		this.police = police;
	}

	public String getPayeur() {
		return payeur;
	}

	public void setPayeur(String payeur) {
		this.payeur = payeur;
	}

	
	public String getLibelleAgence() {
		return libelleAgence;
	}

	public void setLibelleAgence(String libelleAgence) {
		this.libelleAgence = libelleAgence;
	}

	public Long getPrime() {
		return prime;
	}

	public void setPrime(Long prime) {
		this.prime = prime;
	}

	public String getCodeRejet() {
		return codeRejet;
	}

	public void setCodeRejet(String codeRejet) {
		this.codeRejet = codeRejet;
	}

	public String getLibelleRejet() {
		return libelleRejet;
	}

	public void setLibelleRejet(String libelleRejet) {
		this.libelleRejet = libelleRejet;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getPayToDate() {
		return payToDate;
	}

	public void setPayToDate(String payToDate) {
		this.payToDate = payToDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDateSusp() {
		return dateSusp;
	}

	public void setDateSusp(String dateSusp) {
		this.dateSusp = dateSusp;
	}
	
	
}
