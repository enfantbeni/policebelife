package com.belife.policemanager.model.aaronentity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.belife.policemanager.model.entity.Agence;

@Entity
@Table(name = "aaron_sequence")
public class AaronSequence implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idSequence;
	
	@Column(name = "sequence")
	private String  seq;
	
	@Column(name = "dateCreation")
	private Date  dateCreation;
	
	@Column(name = "estSupprimer")
	private Boolean estSupprimer;
	
	@ManyToOne
    @JoinColumn(name = "idAgence")
    AaronAgence idAgence;

	public AaronSequence() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getIdSequence() {
		return idSequence;
	}

	public void setIdSequence(Integer idSequence) {
		this.idSequence = idSequence;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Boolean getEstSupprimer() {
		return estSupprimer;
	}

	public void setEstSupprimer(Boolean estSupprimer) {
		this.estSupprimer = estSupprimer;
	}

	public AaronAgence getIdAgence() {
		return idAgence;
	}

	public void setIdAgence(AaronAgence idAgence) {
		this.idAgence = idAgence;
	}
	
	

}
