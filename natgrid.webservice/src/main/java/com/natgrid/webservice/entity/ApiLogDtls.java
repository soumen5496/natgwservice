package com.natgrid.webservice.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="NATGAPILOG")
public class ApiLogDtls {
	
	 @Column
	 private String USECASEID;
	 @Column
	 private String USERID ;
	 @Column
	 private String CLIENTID;
	 @Id
	 @Column
	 private Timestamp REQUESTTIME;
	 @Column
	 private Timestamp RESPONSETIME;
	 @Column
	 private Integer RECORDCOUNT;
	 @Column
	 private Integer MONTHYEAR;
	 @Column
	 private String CRISGROUP;
	 
	 
	public String getUSECASEID() {
		return USECASEID;
	}
	public void setUSECASEID(String uSECASEID) {
		USECASEID = uSECASEID;
	}
	public String getUSERID() {
		return USERID;
	}
	public void setUSERID(String uSERID) {
		USERID = uSERID;
	}
	public String getCLIENTID() {
		return CLIENTID;
	}
	public void setCLIENTID(String cLIENTID) {
		CLIENTID = cLIENTID;
	}
	public Timestamp getREQUESTTIME() {
		return REQUESTTIME;
	}
	public void setREQUESTTIME(Timestamp rEQUESTTIME) {
		REQUESTTIME = rEQUESTTIME;
	}
	public Timestamp getRESPONSETIME() {
		return RESPONSETIME;
	}
	public void setRESPONSETIME(Timestamp rESPONSETIME) {
		RESPONSETIME = rESPONSETIME;
	}
	public Integer getRECORDCOUNT() {
		return RECORDCOUNT;
	}
	public void setRECORDCOUNT(Integer rECORDCOUNT) {
		RECORDCOUNT = rECORDCOUNT;
	}
	public Integer getMONTHYEAR() {
		return MONTHYEAR;
	}
	public void setMONTHYEAR(Integer mONTHYEAR) {
		MONTHYEAR = mONTHYEAR;
	}
	public String getCRISGROUP() {
		return CRISGROUP;
	}
	public void setCRISGROUP(String cRISGROUP) {
		CRISGROUP = cRISGROUP;
	}
	 
	 
}
