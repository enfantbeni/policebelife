package com.belife.policemanager.model.dto;

public class ClientDto {
	private String planDuree;
	private String genreAssure;
	private String nomAssure;
	private String nomClient;
	private String numero;
	private String banque;
	private String periodicite;
	private Long couverture;
	private Long prime;
	private String datePrelevement;
	private String dateSoumission;
	private String dateNaissance;
	private String profession;
	private String employeur;
	private String ville;
	private String adressPostal;
	private String telephone1;
	private String telephone2;
	private String codeAgent;
	private String nomComPreContrat;
	private String matriculeClient;
	private Boolean estSupprimer;
	public ClientDto() {
		super();
	}
	
	public String getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(String dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getPlanDuree() {
		return planDuree;
	}
	public void setPlanDuree(String planDuree) {
		this.planDuree = planDuree;
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
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getBanque() {
		return banque;
	}
	public void setBanque(String banque) {
		this.banque = banque;
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
	public String getDatePrelevement() {
		return datePrelevement;
	}
	public void setDatePrelevement(String datePrelevement) {
		this.datePrelevement = datePrelevement;
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
	
	public String getMatriculeClient() {
		return matriculeClient;
	}
	public void setMatriculeClient(String matriculeClient) {
		this.matriculeClient = matriculeClient;
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
	public String getAdressPostal() {
		return adressPostal;
	}
	public void setAdressPostal(String adressPostal) {
		this.adressPostal = adressPostal;
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
	public String getCodeAgent() {
		return codeAgent;
	}
	public void setCodeAgent(String codeAgent) {
		this.codeAgent = codeAgent;
	}
	public String getNomComPreContrat() {
		return nomComPreContrat;
	}
	public void setNomComPreContrat(String nomComPreContrat) {
		this.nomComPreContrat = nomComPreContrat;
	}
	public Boolean getEstSupprimer() {
		return estSupprimer;
	}
	public void setEstSupprimer(Boolean estSupprimer) {
		this.estSupprimer = estSupprimer;
	}
}
