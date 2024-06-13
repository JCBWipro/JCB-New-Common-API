package com.wipro.jcb.livelink.app.user.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wipro.jcb.livelink.app.user.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	@Query(nativeQuery = true, value = "select id, age, first_name, last_name from microservices_db.user where id=:id")
	Optional<User> findUserDetailsById(@Param("id") int id);
	
	@Query(nativeQuery = true, value = "select id, age, first_name, last_name from microservices_db.user")
	List<User> findAllUserDetails();
}
