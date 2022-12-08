package com.project.bookshop.service.impl;

import java.sql.Timestamp; 
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.bookshop.exception.NoSuchElementFoundException;
import com.project.bookshop.model.Book;
import com.project.bookshop.model.Order;
import com.project.bookshop.model.User;
import com.project.bookshop.repository.BookRepository;
import com.project.bookshop.repository.OrderRepository;
import com.project.bookshop.repository.UserRepository;
import com.project.bookshop.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{

@Autowired 
private OrderRepository orderRep;
	
	
@Autowired
private UserRepository userRep;

@Autowired 
BookRepository bookRep;

	
	@Override
	public void orderBook(String username, String title) {
	
   User user = userRep.findByUsername(username).orElseThrow();
   Book book= bookRep.findByTitle(title).orElseThrow(()->new NoSuchElementFoundException("Book does not exists"));
		
    Order or = new Order();
	or.setBookId(book);
	or.setUser(user);
    or.setOrderAt(Timestamp.valueOf(LocalDateTime.now()));
	
    orderRep.save(or);
	
	}


	@Override
	public List<Order> findOrderByUserUsername(String username) {
	return orderRep.findOrderByUserUsername(username);
	}


	@Override
	public List<Order> findByTitle(String username) {
		List<Order> or= orderRep.findOrderByTitle(username);
		return or;
	}


	@Override
	public List<Order> getAllOrders() {
	
	return	orderRep.findAll();
		
		
	}


	
	
	
}
