package com.belife.policemanager.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name ="sequence")
public class Sequence implements Serializable{

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
    Agence idAgence;
	
	@OneToMany(targetEntity=com.belife.policemanager.model.entity.SequencePolice.class, mappedBy = "idSequencePolice")
	List<SequencePolice> sequencePolices;
	
			public Sequence() {
				super();
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
			public List<SequencePolice> getSequencePolices() {
				return sequencePolices;
			}
			public void setSequencePolices(List<SequencePolice> sequencePolices) {
				this.sequencePolices = sequencePolices;
			}
			public Agence getIdAgence() {
				return idAgence;
			}
			public void setIdAgence(Agence idAgence) {
				this.idAgence = idAgence;
			}
			
	
}
