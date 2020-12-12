package com.natgrid.webservice.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PRSDTLS")
public class PrsDtls {
	
	  @Column
	  @Id
	  private String PNR;
	  @Column
	  private String TRAINNO;   
	  @Column
	  private Date NORMDT;
	  @Column
	  private Date BOARDINGDT; 
	  @Column
	  private Date ALIGHTDT ;
	  @Column
	  private String BOARDSTN;
	  @Column
	  private String DESTSTN;
	  @Column
	  private String PASSENGERNAME;
	  @Column
	  private Long CONTACTNO;
	  @Column
	  private String EMAILID;
	  
	  
	public String getPNR() {
		return PNR;
	}
	public void setPNR(String pNR) {
		PNR = pNR;
	}
	public String getTRAINNO() {
		return TRAINNO;
	}
	public void setTRAINNO(String tRAINNO) {
		TRAINNO = tRAINNO;
	}
	public Date getNORMDT() {
		return NORMDT;
	}
	public void setNORMDT(Date nORMDT) {
		NORMDT = nORMDT;
	}
	public Date getBOARDINGDT() {
		return BOARDINGDT;
	}
	public void setBOARDINGDT(Date bOARDINGDT) {
		BOARDINGDT = bOARDINGDT;
	}
	public Date getALIGHTDT() {
		return ALIGHTDT;
	}
	public void setALIGHTDT(Date aLIGHTDT) {
		ALIGHTDT = aLIGHTDT;
	}
	public String getBOARDSTN() {
		return BOARDSTN;
	}
	public void setBOARDSTN(String bOARDSTN) {
		BOARDSTN = bOARDSTN;
	}
	public String getDESTSTN() {
		return DESTSTN;
	}
	public void setDESTSTN(String dESTSTN) {
		DESTSTN = dESTSTN;
	}
	public String getPASSENGERNAME() {
		return PASSENGERNAME;
	}
	public void setPASSENGERNAME(String pASSENGERNAME) {
		PASSENGERNAME = pASSENGERNAME;
	}
	public Long getCONTACTNO() {
		return CONTACTNO;
	}
	public void setCONTACTNO(Long cONTACTNO) {
		CONTACTNO = cONTACTNO;
	}
	public String getEMAILID() {
		return EMAILID;
	}
	public void setEMAILID(String eMAILID) {
		EMAILID = eMAILID;
	}
}
