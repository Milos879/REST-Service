package com.project.bookshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.bookshop.model.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Integer>  {

	
public boolean existsByFirstNameAndLastName(String firstName,String lastName);	
	
}
