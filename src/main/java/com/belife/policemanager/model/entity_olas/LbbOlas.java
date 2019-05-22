package com.belife.policemanager.model.entity_olas;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "lb_olas")
public class LbbOlas implements Serializable{
    // La table LB
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public LbbOlas() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Id
	@Column(name = "LBPOLN")
	private String LBPOLN;
	
	
	@Column(name = "LBCOMP")
	private String LBCOMP;
	
	
	@Column(name = "LBACNT")
	private String LBACNT;
	
	@Column(name = "LBNAME")
	private String LBNAME;
	
	@Column(name = "LBPREM")
	private String LBPREM;
	
	@Column(name = "LBLOAN")
	private String LBLOAN;
	
	@Column(name = "LBSTPR")
	private String LBSTPR;
	
	@Column(name = "LBGRID")
	private String LBGRID;
	@Column(name = "LBCDPR")
	private String LBCDPR;
	
	@Column(name = "LBPROC")
	private String LBPROC;
	@Column(name = "LBAGNT")
	private String LBAGNT;
	@Column(name = "LBSBDT")
	private String LBSBDT;
	
	@Column(name = "LBEFDT")
	private String LBEFDT;
	@Column(name = "LBDTPL")
	private String LBDTPL;
	
	@Column(name = "LBDMAJ")
	private String LBDMAJ;
	@Column(name = "LBUSER")
	private String LBUSER;
	
	public String getLBCOMP() {
		return LBCOMP;
	}
	public void setLBCOMP(String lBCOMP) {
		LBCOMP = lBCOMP;
	}
	public String getLBPOLN() {
		return LBPOLN;
	}
	public void setLBPOLN(String lBPOLN) {
		LBPOLN = lBPOLN;
	}
	public String getLBACNT() {
		return LBACNT;
	}
	public void setLBACNT(String lBACNT) {
		LBACNT = lBACNT;
	}
	public String getLBNAME() {
		return LBNAME;
	}
	public void setLBNAME(String lBNAME) {
		LBNAME = lBNAME;
	}
	public String getLBPREM() {
		return LBPREM;
	}
	public void setLBPREM(String lBPREM) {
		LBPREM = lBPREM;
	}
	public String getLBLOAN() {
		return LBLOAN;
	}
	public void setLBLOAN(String lBLOAN) {
		LBLOAN = lBLOAN;
	}
	public String getLBSTPR() {
		return LBSTPR;
	}
	public void setLBSTPR(String lBSTPR) {
		LBSTPR = lBSTPR;
	}
	public String getLBGRID() {
		return LBGRID;
	}
	public void setLBGRID(String lBGRID) {
		LBGRID = lBGRID;
	}
	public String getLBCDPR() {
		return LBCDPR;
	}
	public void setLBCDPR(String lBCDPR) {
		LBCDPR = lBCDPR;
	}
	public String getLBPROC() {
		return LBPROC;
	}
	public void setLBPROC(String lBPROC) {
		LBPROC = lBPROC;
	}
	public String getLBAGNT() {
		return LBAGNT;
	}
	public void setLBAGNT(String lBAGNT) {
		LBAGNT = lBAGNT;
	}
	public String getLBSBDT() {
		return LBSBDT;
	}
	public void setLBSBDT(String lBSBDT) {
		LBSBDT = lBSBDT;
	}
	public String getLBEFDT() {
		return LBEFDT;
	}
	public void setLBEFDT(String lBEFDT) {
		LBEFDT = lBEFDT;
	}
	public String getLBDTPL() {
		return LBDTPL;
	}
	public void setLBDTPL(String lBDTPL) {
		LBDTPL = lBDTPL;
	}
	public String getLBDMAJ() {
		return LBDMAJ;
	}
	public void setLBDMAJ(String lBDMAJ) {
		LBDMAJ = lBDMAJ;
	}
	public String getLBUSER() {
		return LBUSER;
	}
	public void setLBUSER(String lBUSER) {
		LBUSER = lBUSER;
	}
	

}
