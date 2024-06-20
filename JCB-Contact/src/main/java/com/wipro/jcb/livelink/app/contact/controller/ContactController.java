package com.wipro.jcb.livelink.app.contact.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.jcb.livelink.app.contact.model.ContactResponse;
import com.wipro.jcb.livelink.app.contact.model.ContactSMSEmailResponse;
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
	
	@GetMapping("/getContactSmsEmailByUserId/{id}")
	public List<ContactSMSEmailResponse> getContactSmsEmailByUserId(@PathVariable int id) {
		return contactService.getContactSmsEmailByUserId(id);
	}
	
	@GetMapping("/getContactDetailsByUserID/{id}")
	public List<ContactResponse> getContactDetailsByUserID(@PathVariable int id) {
		System.out.println("In ContactService: Call Received from User Service to fetch ContactDetailsByUserID "+id);
		return contactService.getContactDetailsByUserID(id);
	}
}