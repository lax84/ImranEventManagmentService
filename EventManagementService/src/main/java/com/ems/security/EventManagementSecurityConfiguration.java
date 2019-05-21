package com.ems.security;







import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ems.exceptions.AccessException;

//This Class is used for configuring the security features
@Configuration
//Provides a convenient base class for creating a Web sucurityConfigurer instance
//The implementation allows customization by overriding methods
public class EventManagementSecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	//Step 1: We need to override the configure() methods (currently we will 2 methods)
	//Step 2: Setting Creating Roles and Usernameand Password using AuthenticationManagerBuilder
	//Step 3: Pattern based restriction for endpoint via mvcMatcher /root/**
	//Step 4: Mapping those endpoint to specific Roles
	//Step 5: Deciding whether you choose inmemory authentication or Database or JWT (Web Tokens)
	//Step 6: Password with and without Encryptions.
	//Step 7: Disabling CSRF(Cross Site Request Forgery)
	//Step 8: AccessDeniedException Handling
	
	@Autowired
	AccessException accessException;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
		
		
		.antMatchers("/audience/name/{firstName}").permitAll()
		.antMatchers("/audience/name/{firstName}.json").permitAll()
		.antMatchers("/audience/update/{audId}").permitAll()
		.antMatchers("/audience/all").permitAll()
		.antMatchers("/audience/all").permitAll()
		.antMatchers("/audience/all.json").permitAll()
		.antMatchers("/root/searchlocation/{countryName}").hasRole("USER")
		.antMatchers("/root/eventdetails/{eventId}").hasAnyRole("USER","ADMIN")
		.antMatchers("/root/updatelocation/{locationId}/{cityName}").hasRole("ADMIN")
		.antMatchers("/root/deletelocation/{locationId}").hasRole("ADMIN")
		.antMatchers("/root/insert").hasRole("ADMIN")
			.anyRequest()
			.authenticated()
			.and()
			.csrf().disable()
		.httpBasic()
		.and().exceptionHandling().accessDeniedHandler
		(accessException);
	}
	
	@Autowired
	DataSource dataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		
		auth.jdbcAuthentication()
		.passwordEncoder(bCryptPasswordEncoder)
		.dataSource(dataSource)
		.usersByUsernameQuery("select username,password,enabled from users where username=?")
		.authoritiesByUsernameQuery("select username,authority from authorities where username=?");
		/*auth.inMemoryAuthentication()
		.passwordEncoder(bCryptPasswordEncoder)
		.withUser("Rizwan")
		.password(bCryptPasswordEncoder.encode("Rizwan"))
		.roles("USER")
		.and()
		.withUser("Imran")
		.password(bCryptPasswordEncoder.encode("Imran"))
		.roles("USER","ADMIN")
		.and()
		.withUser("Sahi")
		.password(bCryptPasswordEncoder.encode("Sahi"))
		.roles("ADMIN")
		.and()
		.withUser("Hirah")
		.password(bCryptPasswordEncoder.encode("Hirah"))
		.roles("USER");
		
		//{noop} add "{noop}Sahi" before password without password encoder
		*/
	}
	


}
