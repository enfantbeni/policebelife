package com.belife.policemanager.model.entity;

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

@Entity
@Table(name = "sequencePolice")
public class SequencePolice implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idSequencePolice;
	@Column(name = "seqPolice")
	private String  seqPolice;
	@Column(name = "dateCreation")
	private Date  dateCreation;
	@Column(name = "estSupprimer")
	private Boolean estSupprimer;
	@ManyToOne
    @JoinColumn(name = "idSequence")
    Sequence idSequence;
	
	public SequencePolice() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getIdSequencePolice() {
		return idSequencePolice;
	}
	public void setIdSequencePolice(Integer idSequencePolice) {
		this.idSequencePolice = idSequencePolice;
	}
	public String getSeqPolice() {
		return seqPolice;
	}
	public void setSeqPolice(String seqPolice) {
		this.seqPolice = seqPolice;
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
	public Sequence getIdSequence() {
		return idSequence;
	}
	public void setIdSequence(Sequence idSequence) {
		this.idSequence = idSequence;
	}
	
	
}
