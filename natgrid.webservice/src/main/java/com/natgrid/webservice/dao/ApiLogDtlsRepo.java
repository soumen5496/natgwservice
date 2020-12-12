package com.natgrid.webservice.dao;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.natgrid.webservice.entity.ApiLogDtls;
import com.natgrid.webservice.report.ReportDtls;
import com.natgrid.webservice.report.ReportDtlsInterfc;

public interface ApiLogDtlsRepo extends JpaRepository<ApiLogDtls, Timestamp> {

	/*@Query("select  new com.natgrid.webservice.report.ReportDtls( p.usecaseid,p.crisgroup, count(p), SUM(CASE WHEN p.RECORDCOUNT=0 THEN 1 ELSE 0 END)," + 
			"sum(p.RECORDCOUNT))  from ApiLogDtls p" + 
			"where p.USERID<>'CRISTEST' and p.monthyear='202011'  group by p.usecaseid,p.crisgroup order by p.crisgroup,p.usecaseid")
	List<ReportDtls> genReport();	*/
	
	@Query(nativeQuery = true, value ="select  usecaseid, crisgroup, count(*) AS requestcount, SUM(CASE WHEN RECORDCOUNT=0 THEN 1 ELSE 0 END) AS norecordfoundcount," + 
			"sum(RECORDCOUNT) AS recordcount from NATGAPILOG " + 
			"where UPPER(USERID)<>'CRISTEST' and monthyear=?1 group by usecaseid, crisgroup order by crisgroup,usecaseid")
	List<ReportDtlsInterfc> genReport2(String monthyr);
}
