package com.project.bookshop.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.bookshop.model.User;
import com.project.bookshop.repository.UserRepository;
@Service
public class CustomUserDetailsService implements UserDetailsService{

	
	@Autowired
	private UserRepository userRep;
	
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRep.findByUsername(username).orElseThrow(()-> new BadCredentialsException("Invalid username c"));
		
		
		
	  return   new org.springframework.security.core.userdetails.User (user.getUsername(),user.getPassword(),Arrays.asList(new SimpleGrantedAuthority("ROLE_"+user.getRole().toString())));
	

}
}