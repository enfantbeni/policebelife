package com.belife.policemanager.model.entity;

import java.io.Serializable;
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
@Table(name = "banque")
public class Banque implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idBanque;
	@Column(name = "nomGuichet")
	private String nomGuichet;
	@Column(name = "codeGuichet")
	private String codeGuichet;
	@Column(name = "estSupprime", nullable=false)
	private Boolean estSupprimer;
	
	
	
	@ManyToOne
    @JoinColumn(name = "idBanquePrincipale")
    BanquePrincipale idBanquePrincipale;
	
	@OneToMany(targetEntity=com.belife.policemanager.model.entity.ClientBanque.class, mappedBy = "idClient")
	List<Client> clients;
	
	
	
	public Banque() {
		super();
	}
	
	public Banque(Integer idBanque, String nomBanque, String codeBanque, String codeGuichet) {
		super();
		this.idBanque = idBanque;
		this.codeGuichet = codeGuichet;
	}
	
	
	
	public Banque(Boolean estSupprimer) {
		super();
		this.estSupprimer = estSupprimer;
	}

	
	public Banque(Integer idBanque) {
		super();
		this.idBanque = idBanque;
	}
	
	
	
	public String getNomGuichet() {
		return nomGuichet;
	}

	public void setNomGuichet(String nomGuichet) {
		this.nomGuichet = nomGuichet;
	}

	public Integer getIdBanque() {
		return idBanque;
	}
	public void setIdBanque(Integer idBanque) {
		this.idBanque = idBanque;
	}
	
	public String getCodeGuichet() {
		return codeGuichet;
	}

	public void setCodeGuichet(String codeGuichet) {
		this.codeGuichet = codeGuichet;
	}

	public Boolean getEstSupprimer() {
		return estSupprimer;
	}
   
	public void setEstSupprimer(Boolean estSupprimer) {
		this.estSupprimer = estSupprimer;
	}


	public BanquePrincipale getIdBanquePrincipale() {
		return idBanquePrincipale;
	}

	public void setIdBanquePrincipale(BanquePrincipale idBanquePrincipale) {
		this.idBanquePrincipale = idBanquePrincipale;
	}

	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}
	
	

	
	

}
