package com.natgrid.webservice.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private MyUserRepo repo;
	//private UserDtlsRepo repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{
		MyUser user=repo.findByusername(username);
		//UserDtls user=repo.findByusername(username);
		
		if(user==null)
			throw new UsernameNotFoundException("User 404");
		
		return new MyUserDetails(user);
	}

}
