package com.project.bookshop.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.bookshop.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

	
   public Optional<User> findByUsername(String username);
   
   public void deleteByUsername(String username);
   

   
   @Modifying
   @Query("SELECT a.firstName,a.lastName,a.dateOfBirth,a.address,a.username,a.password FROM User a")
   public List<Object[]> getAllUser();
   
  
   @Query("SELECT u FROM User u WHERE u.username=:username AND u.userId<>:id")
   public Optional<User> findByUsernameAndDiffId(String username,Integer id);
   @Query("SELECT u FROM User u WHERE u.email=:email AND u.userId<>:id")
   public Optional<User> findByEmailAndDiffId(String email,Integer id);
   
   
    public boolean existsByUsername(String username);
   
   
}
