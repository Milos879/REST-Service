package com.project.bookshop.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.bookshop.model.User;

@Service
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Autowired
	private PasswordEncoder passworedEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		
		System.out.println("custom provider");
		
		
		UserDetails r = userDetailsService.loadUserByUsername(authentication.getName());

		if (!passworedEncoder.matches(authentication.getCredentials().toString(), r.getPassword())) {
			throw new BadCredentialsException("Invalid password!!!");

		}
		
		
		return new UsernamePasswordAuthenticationToken(r.getUsername(), r.getPassword(), r.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
