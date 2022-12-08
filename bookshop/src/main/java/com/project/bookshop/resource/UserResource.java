package com.project.bookshop.resource;

import java.util.List; 
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.bookshop.dto.LoginDTO;
import com.project.bookshop.dto.UserUpdateDTO;
import com.project.bookshop.exception.NoSuchElementFoundException;
import com.project.bookshop.model.User;
import com.project.bookshop.repository.UserRepository;
import com.project.bookshop.service.UserService;

@RestController
@RequestMapping("/user")
public class UserResource {

	@Autowired
	private UserService userSer;
    
	
	
	
	
	@PostMapping("/registration")
	public ResponseEntity<String> saveUser(@Validated @RequestBody User user,@NotNull(message="ROLE must not be null") @RequestParam("role") String role) {

		userSer.saveUser(user,role);
		return new ResponseEntity<String>("Registration is successful", HttpStatus.CREATED);
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<Map<String,String>> login(@Validated @RequestBody LoginDTO login){
		
		
		
		return new ResponseEntity<Map<String,String>>(userSer.login(login),HttpStatus.OK);
	}
	

	@PutMapping("/update")
	public ResponseEntity<String> updateUser(@Validated @RequestBody UserUpdateDTO newUser) {

		userSer.updateUser(newUser);
		return new ResponseEntity<String>("User is updated ! Log in again!", HttpStatus.ACCEPTED);
	}

	
	
	
	
	
	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteUser() {
		userSer.deleteByUsername();
		return new ResponseEntity<String>(HttpStatus.NO_CONTENT);

	}

   @DeleteMapping("/logout")
   public ResponseEntity<String> logout(){
	userSer.logout();
	
	return new ResponseEntity<String>("the user is successfully logged out",HttpStatus.OK);
   }
	
	
	
	
	
   
   
	@GetMapping
	public ResponseEntity<List<Object[]>> getAllUsers(){
		
		List<Object[]> users= userSer.getAllUser();
		return new ResponseEntity<List<Object[]>>(users,HttpStatus.OK);
	}
	
	@GetMapping("/get")
	public ResponseEntity<User>getUser(){
		
		
		String username=SecurityContextHolder.getContext().getAuthentication().getName();
		return new ResponseEntity<User>(userSer.findByUsername(username),HttpStatus.OK);
		
	}
}
