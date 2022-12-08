package com.project.bookshop.service;

import java.util.List;
import java.util.Map;

import com.project.bookshop.dto.LoginDTO;
import com.project.bookshop.dto.UserUpdateDTO;
import com.project.bookshop.model.Order;
import com.project.bookshop.model.User;

public interface UserService {

public void saveUser(User user,String role);	

public User findByUsername(String username);

public void deleteByUsername();

public void updateUser(UserUpdateDTO u);

public List<Object[]> getAllUser();

public Map<String,String> login(LoginDTO login);

public void logout();


}
