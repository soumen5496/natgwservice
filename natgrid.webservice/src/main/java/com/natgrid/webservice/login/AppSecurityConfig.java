package com.natgrid.webservice.login;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter
{
  
	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public AuthenticationProvider AuthProvider()
	{
		
		
		DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		
		//provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
		
		return provider;
	}

	// login using custom user form & data from user database. Comment this to get auto generated user form.
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		 
		http
		    .httpBasic()
            .and()
		    .csrf().disable()
		    .authorizeRequests()
		    .antMatchers("/webjars/**").permitAll()
		    .antMatchers("/login").permitAll()
		    .antMatchers("/GENREPORT").permitAll()
		    .antMatchers("/sendemail").permitAll()
		    .antMatchers("/UC02/PRS/**").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
		    .antMatchers("/UC01/PRS/**").hasAnyAuthority("ROLE_ADMIN","ROLE_USER")
		    .anyRequest().authenticated()
		    .and()
		    .formLogin()
		    .loginPage("/login").permitAll()
		   // .failureUrl("/login-error") 
		    .and()
		    .logout()
		    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		    .logoutSuccessUrl("/logout-sucess").permitAll()
		    .deleteCookies("JSESSIONID")
		    .invalidateHttpSession(true)
		    .clearAuthentication(true)
		    .and()
	        .exceptionHandling().accessDeniedPage("/"); 
		    
	}
	
	//login using HardCoded User Data
	/*@Bean
	@Override
	protected UserDetailsService userDetailsService()
	{
		List<UserDetails> users= new ArrayList<>();
		users.add(User.withDefaultPasswordEncoder().username("admin").password("admin").roles("ADMIN").build());
		
		return new InMemoryUserDetailsManager(users);
		
	}*/
	
}
