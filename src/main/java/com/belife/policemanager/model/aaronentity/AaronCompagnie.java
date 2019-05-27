package com.belife.policemanager.model.aaronentity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "aaron_compagnie")
public class AaronCompagnie implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idCompagnie;
	@Column(name = "nomCompagnie")
	private String nomCompagnie;
	@Column(name = "ville")
	private String ville;
	@Column(name = "telephone")
	private String telephone;
	@Column(name = "adresse")
	private String adresse;
	public AaronCompagnie() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getIdCompagnie() {
		return idCompagnie;
	}
	public void setIdCompagnie(Integer idCompagnie) {
		this.idCompagnie = idCompagnie;
	}
	public String getNomCompagnie() {
		return nomCompagnie;
	}
	public void setNomCompagnie(String nomCompagnie) {
		this.nomCompagnie = nomCompagnie;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	
	

}
