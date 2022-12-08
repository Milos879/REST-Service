package com.project.bookshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.bookshop.model.Book;
import com.project.bookshop.repository.AuthorRepository;
import com.project.bookshop.repository.BookRepository;
import com.project.bookshop.service.BookService;
@Service
public class BookServiceImpl implements BookService {

	
@Autowired
private BookRepository bookRep;
private AuthorRepository authorRep;	
	
	



@Override
public void addBook() {
	// TODO Auto-generated method stub
	
}

}
