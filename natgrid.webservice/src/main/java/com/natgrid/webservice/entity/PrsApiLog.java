package com.natgrid.webservice.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="NATGPRSAPILOG")
public class PrsApiLog {
     
	
	// @Column
	// @GeneratedValue(strategy = GenerationType.AUTO)
	 //private Integer RECORDID;
	 @Column
	 private String USERNAME;
	 @Column
	 private String APINO;
	 @Id
	 @Column
	 private Timestamp TRNSDT;
	 @Column
	 private Integer RECORDCNT;
	 
	 
	 /*public Integer getRECORDID() {
			return RECORDID;
		}
	public void setRECORDID(Integer rECORDID) {
			RECORDID = rECORDID;
		}*/
	 
	public String getUSERNAME() {
		return USERNAME;
	}
	public void setUSERNAME(String uSERNAME) {
		USERNAME = uSERNAME;
	}
	public String getAPINO() {
		return APINO;
	}
	public void setAPINO(String aPINO) {
		APINO = aPINO;
	}
	public Timestamp getTRNSDT() {
		return TRNSDT;
	}
	public void setTRNSDT(Timestamp tRNSDT) {
		TRNSDT = tRNSDT;
	}
	public Integer getRECORDCNT() {
		return RECORDCNT;
	}
	public void setRECORDCNT(Integer rECORDCNT) {
		RECORDCNT = rECORDCNT;
	}
}
