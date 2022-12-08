package com.project.bookshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.bookshop.model.ActiveUser;



@Repository
public interface ActiveUserRepository extends JpaRepository<ActiveUser,Integer>  {

	
public boolean existsByUserIdUsername(String username);	
public boolean existsByJwtToken(String jwtToken);	

@Modifying
@Query("DELETE FROM ActiveUser a WHERE  a.userId IN (SELECT u FROM User u WHERE u.username=:username)")
public void deleteByUsername( @Param("username")String username);

public void deleteByJwtToken(String jwtToken);



}
