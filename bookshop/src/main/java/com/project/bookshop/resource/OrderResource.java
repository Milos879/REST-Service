package com.project.bookshop.resource;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.bookshop.dto.OrderDTO;
import com.project.bookshop.model.Book;
import com.project.bookshop.model.Order;
import com.project.bookshop.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderResource {

	
@Autowired 
private OrderService ordService;
	


@PostMapping
@ResponseStatus(code = HttpStatus.NO_CONTENT)
public 	void  addOrder(@Validated @RequestBody  OrderDTO order){
	  
	
	String username= SecurityContextHolder.getContext().getAuthentication().getName();
	ordService.orderBook(username, order.getTitle());
}




@GetMapping("/findByUsername")
public ResponseEntity<List<Order>> getOrdersByUsername(@NotNull @RequestParam String username){
	List<Order> or= ordService.findOrderByUserUsername(username);
	return new ResponseEntity<List<Order>>(or,HttpStatus.OK);
}
	


@GetMapping("/findByTitle")
public ResponseEntity<List<Order>> getOrdersBytTitle(@NotNull @RequestParam String title){
	List<Order> or= ordService.findByTitle(title);
	return new ResponseEntity<List<Order>>(or,HttpStatus.OK);
}


@GetMapping("/getAll")
public ResponseEntity<List<Order>> getAllOrders(){
	List<Order> or= ordService.getAllOrders();
	return new ResponseEntity<List<Order>>(or,HttpStatus.OK);
}
}
