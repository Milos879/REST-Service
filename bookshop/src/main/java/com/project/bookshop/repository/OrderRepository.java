package com.project.bookshop.repository;

import java.util.List; 
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.bookshop.model.Book;
import com.project.bookshop.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {

	
public List<Order> findOrderByUserUsername(String username);


@Query("SELECT s FROM Order s JOIN s.bookId b WHERE b.title=:title")
public List<Order> findOrderByTitle(@Param("title") String title);
	



   
          
           
}
