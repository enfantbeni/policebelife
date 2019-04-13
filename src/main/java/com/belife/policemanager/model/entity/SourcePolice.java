package com.belife.policemanager.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "source_police")
public class SourcePolice implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column(name = "genre_police")
	private String genrePolice;
	@Column(name = "source")
	private String source;
	@Column(name = "estSupprime", nullable=false)
	private Boolean estSupprimer;
	
	
	public SourcePolice(String source, Boolean estSupprimer) {
		super();
		this.source = source;
		this.estSupprimer = estSupprimer;
	}
	public SourcePolice(String genrePolice, String source, Boolean estSupprimer) {
		super();
		this.genrePolice = genrePolice;
		this.source = source;
		this.estSupprimer = estSupprimer;
	}
	public SourcePolice(Boolean estSupprimer) {
		super();
		this.estSupprimer = estSupprimer;
	}
	public SourcePolice() {
		super();
	}
	public SourcePolice(Integer id, String genrePolice, String source) {
		super();
		this.id = id;
		this.genrePolice = genrePolice;
		this.source = source;
	}
	public SourcePolice(String genrePolice, String source) {
		super();
		this.genrePolice = genrePolice;
		this.source = source;
	}
	public SourcePolice(String genrePolice) {
		super();
		this.genrePolice = genrePolice;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getGenrePolice() {
		return genrePolice;
	}
	public void setGenrePolice(String genrePolice) {
		this.genrePolice = genrePolice;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public Boolean getEstSupprimer() {
		return estSupprimer;
	}
	public void setEstSupprimer(Boolean estSupprimer) {
		this.estSupprimer = estSupprimer;
	}
	
	
	

}
