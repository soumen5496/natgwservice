package com.natgrid.webservice.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.natgrid.webservice.dao.ApiLogDtlsRepo;
import com.natgrid.webservice.dao.PrsApiLogRepo;
import com.natgrid.webservice.dao.PrsDtlsRepo;
import com.natgrid.webservice.entity.ApiLogDtls;
import com.natgrid.webservice.entity.PrsApiLog;
import com.natgrid.webservice.entity.PrsDtls;
import com.natgrid.webservice.report.ReportDtls;
import com.natgrid.webservice.report.ReportDtlsInterfc;
import com.natgrid.webservice.report.ReportUtil;

@RestController
public class NatgridWsController {
	
	@Autowired
	private PrsDtlsRepo prsdtlsrepo;
	
	//@Autowired
	//private PrsApiLogRepo prsapilogrepo;
	
	@Autowired
	private ApiLogDtlsRepo apilogdtlsrepo;

	
	/*
	 @GetMapping("UC01/PRS/{pnr}")
	 public PrsDtls Onlypnr(@PathVariable("pnr") String ippnr)
	 {
		//System.out.println("Given PNR:"+ippnr);
		PrsDtls tmpob=prsdtlsrepo.findByPNR(ippnr);
		
		if(tmpob == null) {
			//throw new RuntimeException("Employee not found for the Id:"+myid);
			System.out.println("Record not found for PNR:"+ippnr);
		}
		
		return tmpob;
	  }
	
	
	 @GetMapping("UC02/PRS/{pnr}/{frmdt}/{todt}")
	 public PrsDtls pnrnormdtrng(@PathVariable("pnr") String mypnr,@PathVariable("frmdt") String frmdt,@PathVariable("todt") String todt)
	 {
		//System.out.println("Given PNR:"+ippnr);
		PrsDtls tmpob=null;
		Date date1,date2;
		try 
		{
			date1 = new SimpleDateFormat("dd-MM-yyyy").parse(frmdt);
			date2=new SimpleDateFormat("dd-MM-yyyy").parse(todt);	
			//System.out.println(date1+"***"+date2);
			
		    tmpob=prsdtlsrepo.findPnrNormDtRng(mypnr, date1, date2);
			if(tmpob == null) 
			{
				System.out.println("Record not found for PNR:"+mypnr);
				
			}
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		
		
		return tmpob;
	  }
	*/

	
	@GetMapping("UC01/PRS")
	public List<PrsDtls> Onlypnr2(@RequestParam(value="PNR") String ippnr)
	{
		Integer rcnt=1;
		Timestamp inst=Timestamp.from(Instant.now());
		List<PrsDtls> tmpob=prsdtlsrepo.findByPNR(ippnr);
		rcnt=tmpob.size();
		
		if(tmpob.isEmpty()) {
		
			System.out.println("Record not found for PNR:"+ippnr);
			rcnt=0;
		}
		
		// Stores Log of Api call
		ApiLogDtls logob=getlog("UC01/PRS",rcnt,202011,"PRS",inst);
		apilogdtlsrepo.save(logob);
		
		
		return tmpob;
	}
	
	
	@GetMapping("UC02/PRS")
	public PrsDtls pnrnormdtrng2(@RequestParam(value="PNR") String mypnr,@RequestParam(value="FROMDT") String frmdt,@RequestParam(value="TODT") String todt)
	{
		//System.out.println("Given PNR:"+ippnr);
		PrsDtls tmpob=null;
		Date date1,date2;
		Timestamp inst=Timestamp.from(Instant.now());
		try 
		{   Integer rcnt=1;
			date1 = new SimpleDateFormat("dd-MM-yyyy").parse(frmdt);
			date2=new SimpleDateFormat("dd-MM-yyyy").parse(todt);	
			//System.out.println(date1+"***"+date2);
			
		    tmpob=prsdtlsrepo.findPnrNormDtRng(mypnr, date1, date2);
			if(tmpob == null) 
			{
				System.out.println("Record not found for PNR:"+mypnr);
				rcnt=0;
			}
			
			// Stores Log of Api call
			ApiLogDtls logob=getlog("UC02/PRS",rcnt,202011,"PRS",inst);
			apilogdtlsrepo.save(logob);
			
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		
		
		return tmpob;
	}
	
	
	@GetMapping("/GENREPORT")
	public List<ReportDtlsInterfc> getreport(@RequestParam(value="monthyr") String monthyear)
	{
		
		List<ReportDtlsInterfc> reportob=apilogdtlsrepo.genReport2(monthyear.replaceAll("-", ""));
		//System.out.println("GENREPORT::"+reportob.get(0).getRequestcount());
		//System.out.println("GENREPORT::"+reportob.get(0).getUsecaseid());
		return reportob;
	}
	
	public ApiLogDtls getlog(String apiname,Integer count,Integer monthyr,String crisgrp,Timestamp inst1)
	{
		
		Timestamp inst2= Timestamp.from(Instant.now()); 
		Authentication auth =SecurityContextHolder.getContext().getAuthentication(); 
		String uname=auth.getName(); 
		
		ApiLogDtls tempob=new ApiLogDtls();
		tempob.setCLIENTID(uname);
		tempob.setUSERID(uname);
		tempob.setUSECASEID(apiname);
		tempob.setCRISGROUP(crisgrp);
		tempob.setMONTHYEAR(monthyr);
		tempob.setREQUESTTIME(inst1);
		tempob.setRESPONSETIME(inst2);
		tempob.setRECORDCOUNT(count);
		
		System.out.println(uname+" has used"+apiname);
		return tempob;
	}

}
