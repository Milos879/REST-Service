package com.project.bookshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.bookshop.model.ActiveUser;
import com.project.bookshop.repository.ActiveUserRepository;
import com.project.bookshop.service.ActiveUserService;
@Service
public class ActiveUserServiceImpl implements ActiveUserService  {

	
@Autowired
private ActiveUserRepository activeUserRepo;
	

	
	
	
	@Override
	public boolean existsByUserIdUsername(String username) {
		// TODO Auto-generated method stub
		return activeUserRepo.existsByUserIdUsername(username);
	}

	@Override
	public boolean existsByJwtToken(String jwtToken) {
		// TODO Auto-generated method stub
		return activeUserRepo.existsByJwtToken(jwtToken);
	}

	@Override
	
	public void deleteByUsername(String username) {
		activeUserRepo.deleteByUsername(username);
		
	}

	@Override
	@Transactional
	public void deleteByJwtToken(String jwtToken) {
		activeUserRepo.deleteByJwtToken(jwtToken);
		
	}

	@Override
	public void saveActiveUse(ActiveUser user) {
		activeUserRepo.save(user);
		
	}

}
