package com.project.bookshop.service;

import com.project.bookshop.model.ActiveUser;

public interface ActiveUserService {

	
	public boolean existsByUserIdUsername(String username);	
	public boolean existsByJwtToken(String jwtToken);	

	public void deleteByUsername(String username);
	public void deleteByJwtToken(String jwtToken);

	public void saveActiveUse(ActiveUser user);
	
}
