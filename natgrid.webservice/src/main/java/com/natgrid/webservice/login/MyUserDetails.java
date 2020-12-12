package com.natgrid.webservice.login;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;




public class MyUserDetails implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	
	//private UserDtls user;
	private MyUser user;

	//MyUser user
	public MyUserDetails(MyUser user) {
		super();
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		/*Set<RoleDtls> roles = user.getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
         
        for (RoleDtls role : roles) {
        	String rln=role.getRolename().toUpperCase();
            authorities.add(new SimpleGrantedAuthority(rln));
        }*/
		 
        
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
	    if(user.getRole().equalsIgnoreCase("admin"))
			   authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN")); 
	    if(user.getRole().equalsIgnoreCase("user"))
			   authorities.add(new SimpleGrantedAuthority("ROLE_USER")); 
	    return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
