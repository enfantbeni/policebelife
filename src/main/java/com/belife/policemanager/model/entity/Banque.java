package com.belife.policemanager.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	
	@Column(name = "nomBanque")
	private String nomBanque;
	
	@Column(name = "codeBanque")
	private String codeBanque;
	
	@Column(name = "status")
	private String status;
	
	
	public Banque() {
		super();
	}
	public Integer getIdBanque() {
		return idBanque;
	}
	public void setIdBanque(Integer idBanque) {
		this.idBanque = idBanque;
	}
	public String getNomBanque() {
		return nomBanque;
	}
	public void setNomBanque(String nomBanque) {
		this.nomBanque = nomBanque;
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
