package com.hibernate.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.hibernate.security.JwtAuthenticationEntryPoint;
import com.hibernate.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	//@Autowired
	//private PasswordEncoder passwordEncoder;
	// bcos we cannot define bean in same class 
	// either define bean in some other class
	// or remove this dependency injection and directly call the bean method  <------  we are doing this
	
	
	@Autowired
	private JwtAuthenticationFilter filter;
	
	@Autowired
	private JwtAuthenticationEntryPoint entryPoint;
	
	
	public static String[] PUBLIC_URLS = { "/user" , 
											"/auth/login" ,
											"/v3/api-docs",
											"/v2/api-docs",
											"/swagger-resources/**",
											"/swagger-ui/**",
											"/webjars/**"
			};
	
	
	
	// Configuring Basic Authorization
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		
		http
		.csrf()
		.disable()
		.authorizeRequests()
	//  .antMatchers(HttpMethod.POST).permitAll()
		.antMatchers(PUBLIC_URLS).permitAll()
        .antMatchers(HttpMethod.GET).permitAll()   // Anyone can access GET methods ie without authentication
//      .antMatchers(HttpMethod.POST,"/users/**").hasRole("ADMIN")   
		.anyRequest()
		.authenticated()
		.and()
		//.httpBasic()
		.exceptionHandling().authenticationEntryPoint(entryPoint)  // specifies which class to fire when unauthorized exception is thrown
		.and()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		
		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
		
	}

	
	// Configuring database Authorization
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception{
		return super.authenticationManagerBean();
	}
	
	
	

}
