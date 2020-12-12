package com.natgrid.webservice.report;

public class ReportDtls {
	
	
	String usecaseid;
	String crisgroup;
	Integer requestcount;
	Integer norecordfoundcount;
	Integer recordcount;
	
	public ReportDtls(String usecaseid, String crisgroup, Integer requestcount, Integer norecordfoundcount,
			Integer recordcount) {
		super();
		this.usecaseid = usecaseid;
		this.crisgroup = crisgroup;
		this.requestcount = requestcount;
		this.norecordfoundcount = norecordfoundcount;
		this.recordcount = recordcount;
	}

	public String getUsecaseid() {
		return usecaseid;
	}

	public void setUsecaseid(String usecaseid) {
		this.usecaseid = usecaseid;
	}

	public String getCrisgroup() {
		return crisgroup;
	}

	public void setCrisgroup(String crisgroup) {
		this.crisgroup = crisgroup;
	}

	public Integer getRequestcount() {
		return requestcount;
	}

	public void setRequestcount(Integer requestcount) {
		this.requestcount = requestcount;
	}

	public Integer getNorecordfoundcount() {
		return norecordfoundcount;
	}

	public void setNorecordfoundcount(Integer norecordfoundcount) {
		this.norecordfoundcount = norecordfoundcount;
	}

	public Integer getRecordcount() {
		return recordcount;
	}

	public void setRecordcount(Integer recordcount) {
		this.recordcount = recordcount;
	}
	
	

}
