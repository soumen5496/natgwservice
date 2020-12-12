package com.natgrid.webservice.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.natgrid.webservice.entity.PrsDtls;

public interface PrsDtlsRepo extends JpaRepository<PrsDtls, String> {
	
	List<PrsDtls> findByPNR(String pnr);
	
	@Query("select p from PrsDtls p where p.PNR=?1 and p.NORMDT>=?2 and p.NORMDT<=?3") 
	PrsDtls findPnrNormDtRng( String mypnr,Date frmdt,Date todt);  
	
	
	/*@Query(value = "from PrsDtls t where t.normdt BETWEEN :startDate AND :endDate")
	public PrsDtls getAllBetweenDates(@Param("startDate")Date startDate,@Param("endDate")Date endDate);*/

}
