package com.natgrid.webservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.natgrid.webservice.dao.ApiLogDtlsRepo;
import com.natgrid.webservice.report.ReportDtlsInterfc;

/*import com.natgrid.webservice.login.RoleDtlsRepo;
import com.natgrid.webservice.login.RoleMapDtlsRepo;
import com.natgrid.webservice.login.UserDtlsRepo;*/



@Controller
public class NatgridHomeController {

	
	//@Autowired
	//private UserDtlsRepo usrdtls;
	
	/*@Autowired
	private RoleDtlsRepo rldtls;
	
	@Autowired
	private RoleMapDtlsRepo rlmpdtls;*/
	

	@RequestMapping("/")
	public String myhome(ModelMap model)
	{
		Authentication auth =SecurityContextHolder.getContext().getAuthentication(); 
		String name=auth.getName(); 
		model.addAttribute("UNAME", name);
		return "home.jsp";
	}
	

	@RequestMapping("/login")
	public String login()
	{
		return "login.jsp";
		//return "login2.html";
		
	}
	
	@RequestMapping("/login-error")
	public String loginError(Model model) {
	    //model.addAttribute("loginError", true);
	    //return "login2.html";
		return "login.jsp";
	  }
	
	@RequestMapping("/logout-sucess")
	public String logout()
	{
		//return "logout.html";
		return "logout.jsp";
	}

	
}
