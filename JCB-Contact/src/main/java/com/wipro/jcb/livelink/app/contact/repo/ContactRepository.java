package com.wipro.jcb.livelink.app.contact.repo;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wipro.jcb.livelink.app.contact.entity.Contact;



public interface ContactRepository extends JpaRepository<Contact, Integer>{
	
	@Query(nativeQuery = true, value = "select id,email,mobile_number from microservices_db.contact_details where user_id = (select id from microservices_db.user where id=:id)")
	List<Object[]> findContactDetailsByUserId(@Param("id") int id);
}

