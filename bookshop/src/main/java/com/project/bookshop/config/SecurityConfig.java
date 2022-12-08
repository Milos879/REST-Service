package com.project.bookshop.config;

import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.project.bookshop.security.JWTAuthFilter;



@Configuration
@EnableWebSecurity(debug=true)
public class SecurityConfig {


@Autowired
private   AuthenticationConfiguration authConfig ;
@Autowired
private JWTAuthFilter jwtFilter;



@Bean
public AuthenticationManager authManager() throws Exception{
	return authConfig.getAuthenticationManager();
}





	
@Bean 
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
	http.csrf().disable();
    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    
    
    
    
	http.authorizeHttpRequests().
	antMatchers("/user/registration","/user/login").permitAll()
   .antMatchers(HttpMethod.GET, "/user").hasRole("employee")
   .antMatchers("/user/**").hasAnyRole("customer","employee")
   .antMatchers(HttpMethod.POST,"/order").hasAnyRole("customer","employee")
   .antMatchers("/order/**").hasRole("employee");
	
	http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
	
	
	return http.build();
}
	
	
@Bean
public PasswordEncoder passwordEncoder() {
	return new BCryptPasswordEncoder();
	}
	
	

	
}
