package com.wipro.jcb.livelink.app.user.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.jcb.livelink.app.user.entity.Contact;



public interface ContactRepository extends JpaRepository<Contact, Integer>{

}
