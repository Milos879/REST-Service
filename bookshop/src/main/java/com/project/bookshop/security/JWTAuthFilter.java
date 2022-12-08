package com.project.bookshop.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.project.bookshop.service.ActiveUserService;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JWTAuthFilter extends OncePerRequestFilter {

	
@Autowired
private JWTProvider jwtProvider;
@Autowired
private CustomUserDetailsService userDetailsService;	
@Autowired
@Qualifier("handlerExceptionResolver")
private HandlerExceptionResolver handlerExceptionReslover;
@Autowired
private ActiveUserService activeUserService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		
        String url= request.getServletPath();

		if(url.equals("/user/login") || url.equals("/user/registration")){
    	filterChain.doFilter(request, response);
    	return;  
    	}
    
	
		
		
		
		String token=request.getHeader(JWTProvider.JWT_HEADER);
		
		if(StringUtils.hasText(token)){
			try {
			 
			 authenticate(token);
			 filterChain.doFilter(request, response);
			
			}catch( ExpiredJwtException |  BadCredentialsException ex){
				 
				if(ex instanceof ExpiredJwtException ){
					activeUserService.deleteByJwtToken(token);
					 handlerExceptionReslover
					.resolveException(request, response, null, new BadCredentialsException("Token is expired!"));
				
				}else
				
				 handlerExceptionReslover
				.resolveException(request, response, null, ex);
			
			}
		}else {
			handlerExceptionReslover
			.resolveException(request, response, null, new BadCredentialsException("X-ACCESS-TOKEN IS REQUIRED!"));
		}
		
		 
    
		
		
		

}
	
	
	
private void authenticate(String token){
	
	
	
	String user=jwtProvider.getClaimFromToken(token);
	
	
	
	if(activeUserService.existsByJwtToken(token)) {

	
	
	
	UserDetails ud= userDetailsService.loadUserByUsername(user);	
	 
	
	SecurityContextHolder.getContext()
	.setAuthentication(new UsernamePasswordAuthenticationToken(ud.getUsername(), ud.getPassword(), ud.getAuthorities()));
	
	new UsernamePasswordAuthenticationToken(ud.getUsername(), ud.getPassword(), ud.getAuthorities());
	
	}else 
		throw new BadCredentialsException("Token is invalid! Log in again");
	
}


}





