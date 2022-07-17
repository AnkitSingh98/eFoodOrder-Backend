package com.hibernate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hibernate.entitiy.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	
	//CustomFinderMethods  -- just write in Standard format ie camel case,...  -- jpa will create query automatically
	
	public User findByEmail(String email);  
	
	public  List<User> findByName(String name);
	
	public User findByEmailAndPassword(String email, String password);
	
	public List<User> findByActiveTrue();
	
	public List<User> findByAboutIsNull();
	
	public List<User> findByNameStartingWith(String prefix);
	
	public List<User> findByNameContaining(String infix);
	
	public List<User> findByNameLike(String likePattern);
	
	public List<User> findByNameOrderByNameDesc(String name);
	
	
	// Creating Query Methods
	
	@Query("Select u from User u")   // this is JPQL -- JPA converts it to native query based on dialect we are using --- in jdbc we were directly firing native query
	public List<User> getAllUsers();
	
	
	@Query("select u from User u where u.userId = :userId and u.email = :email")  //u.userId is the class variable keep in mind
	public User getUserByEmail( @Param("userId") int userId, String email);  //userId this name should be same as above parameter :userId
	
  // 	@Query(value=" ", native =TRUE)
	

}
