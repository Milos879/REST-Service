package com.project.bookshop.service;

import java.util.List;

import com.project.bookshop.model.Book;
import com.project.bookshop.model.Order;

public interface OrderService {

	
public void orderBook(String username,String title);	
public List<Order> findOrderByUserUsername(String username);
public List<Order> findByTitle(String username);
public List<Order> getAllOrders();
}
