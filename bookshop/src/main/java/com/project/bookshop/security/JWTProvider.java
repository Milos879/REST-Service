package com.project.bookshop.security;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;

import com.project.bookshop.repository.ActiveUserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException; 



@Component
public class JWTProvider {


	
	
public static final SecretKey SECRET_KEY=Keys.secretKeyFor(SignatureAlgorithm.HS256);	
public static final String JWT_HEADER="X-ACCESS-TOKEN";
	




public String generateToken(String username){
return	
		Jwts.builder()
	.setHeader(Map.of("typ","JWT","alg","HS256"))
	.setSubject(username)
	.setIssuer("me")
	.setIssuedAt(
			 Date.from(
			 LocalDateTime.now()
			.atZone(ZoneId.of("Europe/Belgrade")).toInstant()))
	.setExpiration(
			 Date.from(
			 LocalDateTime.now().plusMinutes(5)
			.atZone(ZoneId.of("Europe/Belgrade")).toInstant()))
	.signWith(SECRET_KEY, SignatureAlgorithm.HS256)
	.compact();
}	




public String getClaimFromToken(String token){
 
try {
	
String token2	=	 Jwts.parserBuilder().setSigningKey(SECRET_KEY).build()
	     .parseClaimsJws(token)
	     .getBody().getSubject();
return token2;
}
catch(UnsupportedJwtException e){
	throw new BadCredentialsException("Invalid token! unsupported");
}
catch(MalformedJwtException e){
	throw new BadCredentialsException("Invalid token! malformed");
}
catch(SignatureException e){
	
	throw new BadCredentialsException("Invalid token! signature");
}

catch(IllegalArgumentException e) {
	throw new BadCredentialsException("Invalid token! illegal argment");
}



}	






}








