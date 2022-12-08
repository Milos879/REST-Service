package com.project.bookshop.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.bookshop.dto.LoginDTO;
import com.project.bookshop.dto.UserUpdateDTO;
import com.project.bookshop.exception.NoSuchElementFoundException;
import com.project.bookshop.exception.UniqueConstarintViolationException;
import com.project.bookshop.model.ActiveUser;
import com.project.bookshop.model.Order;
import com.project.bookshop.model.User;
import com.project.bookshop.repository.OrderRepository;
import com.project.bookshop.repository.UserRepository;
import com.project.bookshop.security.JWTProvider;
import com.project.bookshop.service.ActiveUserService;
import com.project.bookshop.service.UserService;
import com.project.bookshop.type.UserRole;

@Service
public class UserServiceImpl implements UserService {

	
	
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private JWTProvider jwtUtil;
	
	@Autowired
	private UserRepository userRep;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired 
	private ActiveUserService activeUserService;
	
	
	
	
	
	@Override
	public void saveUser(User user ,String role) {

		if(userRep.existsByUsername(user.getUsername()))
		  throw new UniqueConstarintViolationException("Username already exists!!!");
		
		
		if(role.equalsIgnoreCase("customer"))
		  user.setRole(UserRole.customer);
		else if(role.equalsIgnoreCase("employee"))
		   user.setRole(UserRole.employee);
		else 
		  throw new IllegalArgumentException("Invalid role! (customer/employee)");
			
		user.setPassword(encoder.encode(user.getPassword()));
		userRep.save(user);
	}

	
	
	
	
	
	@Override
	@Transactional
	public Map<String,String> login(LoginDTO login) {
		     
	Authentication auth=	authManager.authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));
	
		
	String token= jwtUtil.generateToken(auth.getPrincipal().toString()); 	
	User user= userRep.findByUsername(login.getUsername()).orElseThrow();
	
	activeUserService.deleteByUsername(login.getUsername());
	
	
	
    activeUserService.saveActiveUse(new ActiveUser(token,user));
	SecurityContextHolder.getContext().setAuthentication(auth);	
	
	return Map.of(JWTProvider.JWT_HEADER, token);
	}

	
	
	
	
	
	
	@Override
	public User findByUsername(String username) {
		return userRep.findByUsername(username)
				.orElseThrow(() -> new NoSuchElementFoundException("Username do not exists"));

	}

	
	
	@Override
	@Transactional
	public void deleteByUsername() {
    String username= SecurityContextHolder.getContext().getAuthentication().getName();
        
        logout();
		userRep.deleteByUsername(username);

	}

	@Override
	@Transactional
	public void updateUser(UserUpdateDTO u) {

		
		String username= SecurityContextHolder.getContext().getAuthentication().getName();
	    User ur = userRep.findByUsername(username).get();
        
		 
	 boolean existsUsername = userRep.findByUsernameAndDiffId(u.getUsername(),ur.getUserId()).isPresent();
     boolean existsEmail = userRep.findByEmailAndDiffId(u.getEmail(), ur.getUserId()).isPresent();
	 if(existsUsername) 
       throw new UniqueConstarintViolationException("Username already exists!");
	 else if(existsEmail)
	   throw new UniqueConstarintViolationException("Email already exists!");
		
		
	    logout();
		
		
		ur.setPassword(encoder.encode(u.getPassword()));
		ur.setUsername(u.getUsername());
		ur.setEmail(u.getEmail());

		
		
		
		
		
		
		
	}

	@Override
	@Transactional
	public List<Object[]> getAllUser() {

		return userRep.getAllUser();
	}

	
	
	
	
	
	
	
	@Override
	@Transactional
	public void logout() {
		
	String username=SecurityContextHolder.getContext().getAuthentication().getName();
		
	activeUserService.deleteByUsername(username);	
		
	}

	
	
	
	
	

}
