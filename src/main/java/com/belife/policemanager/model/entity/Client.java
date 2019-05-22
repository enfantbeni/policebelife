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
@Table(name = "client")
public class Client implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idClient;
	@Column(name = "numeroPolice")
	private String numeroPolice;
	@Column(name = "sequencePolice")
	private String sequencePolice;
	@Column(name = "produit")
	private String produit;
	@Column(name = "genreAssure")
	private String genreAssure;
	@Column(name = "nomAssure")
	private String nomAssure;
	@Column(name = "nomClient")
	private String nomClient;	
	@Column(name = "matriculeClient")
	private String matriculeClient;
	@Column(name = "numeroCompte")
	private String numeroCompte;
	@Column(name = "nomSource")
	private String nomSource;
	@Column(name = "codeSource")
	private String codeSource;	
	@Column(name = "periodicite")
	private String periodicite ;
	@Column(name = "couverture")
	private Long couverture;
	@Column(name = "prime")
	private Long prime;
	@Column(name = "datePrelevement")
	private String datePrelevement;
	@Column(name = "dateSoumission")
	private String dateSoumission;
	@Column(name = "dateNaissance")
	private String dateNaissance;
	@Column(name = "dateCreation")
	private Date dateCreation;
	@Column(name = "profession")
	private String profession ;
	@Column(name = "employeur")
	private String employeur ;
	@Column(name = "ville")
	private String ville ;
	@Column(name = "adressePostale")
	private String adressePostale ;
	@Column(name = "telephone1")
	private String telephone1 ;
	@Column(name = "telephone2")
	private String telephone2 ;
	@Column(name = "nomComPreContrat")
	private String nomComPreContrat;
	@Column(name = "dateRealisation")
	private String dateRealisation;
	@Column(name = "codeAgent")
	private String codeAgent;
	@Column(name = "codeAgence")
	private String codeAgence;
	@Column(name = "status")
	private String status;
		
	@OneToMany(targetEntity=com.belife.policemanager.model.entity.ClientBanque.class, mappedBy = "idBanque")
	List<Banque> banques;
	
	@OneToMany(targetEntity=com.belife.policemanager.model.entity.ClientPlan.class, mappedBy = "idPlan")
	List<Plan> plans;
	
	@OneToMany(targetEntity=com.belife.policemanager.model.entity.ClientSociete.class, mappedBy = "idSociete")
	List<Societe> societes;
	
	public Client() {
		super();
	}
	
	public String getNumeroPolice() {
		return numeroPolice;
	}

	public void setNumeroPolice(String numeroPolice) {
		this.numeroPolice = numeroPolice;
	}

	public String getSequencePolice() {
		return sequencePolice;
	}

	public void setSequencePolice(String sequencePolice) {
		this.sequencePolice = sequencePolice;
	}

	public String getProduit() {
		return produit;
	}

	public void setProduit(String produit) {
		this.produit = produit;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public String getNumeroCompte() {
		return numeroCompte;
	}

	public void setNumeroCompte(String numeroCompte) {
		this.numeroCompte = numeroCompte;
	}
	
	public String getNomSource() {
		return nomSource;
	}

	public void setNomSource(String nomSource) {
		this.nomSource = nomSource;
	}

	public String getCodeSource() {
		return codeSource;
	}

	public void setCodeSource(String codeSource) {
		this.codeSource = codeSource;
	}

	public Integer getIdClient() {
		return idClient;
	}

	public void setIdClient(Integer idClient) {
		this.idClient = idClient;
	}

	public String getGenreAssure() {
		return genreAssure;
	}

	public void setGenreAssure(String genreAssure) {
		this.genreAssure = genreAssure;
	}

	public String getNomAssure() {
		return nomAssure;
	}

	public void setNomAssure(String nomAssure) {
		this.nomAssure = nomAssure;
	}

	public String getNomClient() {
		return nomClient;
	}

	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}

	public String getMatriculeClient() {
		return matriculeClient;
	}

	public void setMatriculeClient(String matriculeClient) {
		this.matriculeClient = matriculeClient;
	}

	public String getPeriodicite() {
		return periodicite;
	}

	public void setPeriodicite(String periodicite) {
		this.periodicite = periodicite;
	}

	public Long getCouverture() {
		return couverture;
	}

	public void setCouverture(Long couverture) {
		this.couverture = couverture;
	}

	public Long getPrime() {
		return prime;
	}

	public void setPrime(Long prime) {
		this.prime = prime;
	}


	public String getDateSoumission() {
		return dateSoumission;
	}

	public void setDateSoumission(String dateSoumission) {
		this.dateSoumission = dateSoumission;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getEmployeur() {
		return employeur;
	}

	public void setEmployeur(String employeur) {
		this.employeur = employeur;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getAdressePostale() {
		return adressePostale;
	}

	public void setAdressePostale(String adressePostale) {
		this.adressePostale = adressePostale;
	}

	

	public String getTelephone1() {
		return telephone1;
	}

	public void setTelephone1(String telephone1) {
		this.telephone1 = telephone1;
	}

	public String getTelephone2() {
		return telephone2;
	}

	public void setTelephone2(String telephone2) {
		this.telephone2 = telephone2;
	}

	public String getNomComPreContrat() {
		return nomComPreContrat;
	}

	public void setNomComPreContrat(String nomComPreContrat) {
		this.nomComPreContrat = nomComPreContrat;
	}

	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Societe> getSocietes() {
		return societes;
	}

	public void setSocietes(List<Societe> societes) {
		this.societes = societes;
	}


	public List<Banque> getBanques() {
		return banques;
	}

	public void setBanques(List<Banque> banques) {
		this.banques = banques;
	}

	public List<Plan> getPlans() {
		return plans;
	}

	public void setPlans(List<Plan> plans) {
		this.plans = plans;
	}	

	public String getDatePrelevement() {
		return datePrelevement;
	}

	public void setDatePrelevement(String datePrelevement) {
		this.datePrelevement = datePrelevement;
	}

	public String getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(String dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getDateRealisation() {
		return dateRealisation;
	}

	public void setDateRealisation(String dateRealisation) {
		this.dateRealisation = dateRealisation;
	}

	public String getCodeAgent() {
		return codeAgent;
	}

	public void setCodeAgent(String codeAgent) {
		this.codeAgent = codeAgent;
	}

	public String getCodeAgence() {
		return codeAgence;
	}

	public void setCodeAgence(String codeAgence) {
		this.codeAgence = codeAgence;
	}

	
	
}
