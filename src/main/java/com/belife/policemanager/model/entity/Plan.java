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
@Table(name = "plan")
public class Plan implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idPlan;
	@Column(name = "codePlan")
	private String codePlan;
	@Column(name = "planDuree")
	private String planDuree;
	@Column(name = "typeContrat")
	private String typeContrat;
	@Column(name = "pngrid")
	private String pngrid;
	
	@OneToMany(targetEntity=com.belife.policemanager.model.entity.ClientPlan.class, mappedBy = "idClient")
	List<Client> clients;
	
	public Plan() {
		super();
	}
	public Integer getIdPlan() {
		return idPlan;
	}
	public void setIdPlan(Integer idPlan) {
		this.idPlan = idPlan;
	}
	public String getCodePlan() {
		return codePlan;
	}
	public void setCodePlan(String codePlan) {
		this.codePlan = codePlan;
	}
	public String getPlanDuree() {
		return planDuree;
	}
	public void setPlanDuree(String planDuree) {
		this.planDuree = planDuree;
	}
	public String getTypeContrat() {
		return typeContrat;
	}
	public void setTypeContrat(String typeContrat) {
		this.typeContrat = typeContrat;
	}
	public String getPngrid() {
		return pngrid;
	}
	public void setPngrid(String pngrid) {
		this.pngrid = pngrid;
	}
	public List<Client> getClients() {
		return clients;
	}
	public void setClients(List<Client> clients) {
		this.clients = clients;
	}
	
	

}
