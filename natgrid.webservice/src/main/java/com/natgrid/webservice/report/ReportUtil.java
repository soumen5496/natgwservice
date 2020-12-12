package com.natgrid.webservice.report;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.util.List;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.attach.ITagWorker;
import com.itextpdf.html2pdf.attach.ProcessorContext;
import com.itextpdf.html2pdf.attach.impl.DefaultTagWorkerFactory;
import com.itextpdf.html2pdf.attach.impl.tags.HtmlTagWorker;
import com.itextpdf.html2pdf.html.TagConstants;
import com.itextpdf.layout.Document;
import com.itextpdf.styledxmlparser.node.IElementNode;


public class ReportUtil{

	public String genhtmltable(String ipmonth,String ipyr,List<ReportDtlsInterfc> res)
	{
	
	
	 //ReportDAO myob=new ReportDAO();
	 String htmlstr=null;
	    
		try 
		{
			htmlstr="<h3 style='text-align:center;padding:0;margin:0'>Summary of data shared with NATGRID for "+ ipmonth+" "+ipyr+"</h3>"
					+ "<h4 style='text-align:center'> (Confidential) </h4>";
		    //System.out.println(htmlstr);style=""white-space:nowrap;
			htmlstr+="<table id='myTable' border='1' style='border-collapse:collapse;'>"+
		      		 "<thead><tr style='background-color:#384354;color:white;font-family: Arial, Helvetica, sans-serif;font-size:16px;'>"+
		      		 "<th style='word-wrap:break-word;padding:7px;'>System</th>"+
		      		 "<th style='word-wrap:break-word;padding:7px;'>Usecase</th>"+
		      		 "<th style='word-wrap:break-word;padding:7px;'>No. of Requests Received</th>"+
		      		 "<th style='word-wrap:break-word;padding:7px;'>No. of Responses Served</th>"+
		      		 "<th style='word-wrap:break-word;padding:7px;'>Total No of Requests for which NO RECORD FOUND</th>"+
  				     "<th style='word-wrap:break-word;padding:7px;'>Total No of Records Returned</th></tr></thead><tbody>";
		
			int	sum_requestcount=0;
			int sum_norecordfoundcount=0;
			int sum_recordcount=0;
			
			String rowgroup="";
			
			for(int k=0;k<res.size();k++)
			{
				
				ReportDtlsInterfc rb = res.get(k);
				//System.out.println(res.get(k).getRequestcount());
				//System.out.println(rb.getCrisgroup()+"@"+rb.getUsecaseid()+"@"+rb.getRequestcount()
				
				sum_requestcount+=rb.getRequestcount();
				sum_norecordfoundcount+=rb.getNorecordfoundcount();
				sum_recordcount+=+rb.getRecordcount();
				
				/*if(!rowgroup.equalsIgnoreCase(rb.getCrisgroup()))
				{
					htmlstr+="<tr><td style='text-align:center;font-size:15px;background-color:#d9dbdb;font-weight:bold' colspan='6'>"+rb.getCrisgroup()+"</td></tr>";
					rowgroup=rb.getCrisgroup();
				}*/
				if(k%2==0)	
				  htmlstr+="<tr style='background-color:#e6e8eb'>";
				else
				  htmlstr+="<tr>";	
				
				htmlstr+="<td style='text-align:left;padding:7px;'>"+rb.getCrisgroup()+"</td>";
				htmlstr+="<td style='text-align:left;padding:7px;'>"+rb.getUsecaseid()+"</td>";
				htmlstr+="<td style='text-align:left;padding:7px;'>"+rb.getRequestcount()+"</td>";
				htmlstr+="<td style='text-align:left;padding:7px;'>"+rb.getRequestcount()+"</td>";
				htmlstr+="<td style='text-align:left;padding:7px;'>"+rb.getNorecordfoundcount()+"</td>";
				htmlstr+="<td style='text-align:left;padding:7px;'>"+rb.getRecordcount()+"</td>";
				htmlstr+="</tr>";
						
			}
		
			htmlstr+="</tbody><tfoot><tr style='background-color:#384354;color:white;font-size:19px;'>";
			htmlstr+="<th style='text-align:left;padding:7px;' colspan='2'>TOTAL</td>";
			htmlstr+="<th style='text-align:left;padding:7px;'>"+sum_requestcount+"</td>";
			htmlstr+="<th style='text-align:left;padding:7px;'>"+sum_requestcount+"</td>";
			htmlstr+="<th style='text-align:left;padding:7px;'>"+sum_norecordfoundcount+"</td>";
			htmlstr+="<th style='text-align:left;padding:7px;'>"+sum_recordcount+"</td>";
			htmlstr+="</tr></tfoot></table>";
				      						
			if(res.size()==0)
			{
			  	htmlstr="<h2> NO DATA FOUND </h2>";
			}								
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	  return htmlstr;
	}
	
	public String genpdf(String ipmn,String ipyr,List<ReportDtlsInterfc> ipres)
	{
		String filename="NATGRID_"+ipmn.substring(0, 3)+ipyr+".pdf";
		//String filename="NATGRID_test_nov.pdf";
		String pdfbody=genhtmltable(ipmn,ipyr,ipres);
   	   try 
   	   {
   		 ConverterProperties props = new ConverterProperties();
   		 props.setTagWorkerFactory(new CustomTagWorkerFactory());
			HtmlConverter.convertToPdf(pdfbody, new FileOutputStream(filename),props);
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} 
   	 
        System.out.println( "PDF Created! at " +LocalDateTime.now());
		return filename;
	}
	
	
	public class ZeroMarginHtmlTagWorker extends HtmlTagWorker {
	     public ZeroMarginHtmlTagWorker(IElementNode element, ProcessorContext context) {
	         super(element, context);
	         Document doc = (Document) getElementResult();
	         doc.setMargins(15,15,0,15);
	     }
	}
	
	public class CustomTagWorkerFactory extends DefaultTagWorkerFactory {
	     public ITagWorker getCustomTagWorker(IElementNode tag, ProcessorContext context) {
	         if (TagConstants.HTML.equals(tag.name())) {
	             return new ZeroMarginHtmlTagWorker(tag, context);
	         }
	         return null;
	     }
	}
}
