package com.wipro.jcb.livelink.app.contact.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.wipro.jcb.livelink.app.contact.entity.Contact;
import com.wipro.jcb.livelink.app.contact.model.ContactResponse;
import com.wipro.jcb.livelink.app.contact.service.ContactService;


@RestController
@RequestMapping("/api/contact")
public class ContactController {
	
	@Autowired
	private ContactService contactService;
	
	@GetMapping
	public String getString() {
		System.out.println("Called from Contact Service");
		return "Contact Service";
	}
	
	@GetMapping("/getContactDetailsByUserId/{id}")
	public List<ContactResponse> getContactDetailsByUserId(@PathVariable int id) {
		return contactService.getContactDetailsByUserId(id);
	}

}
