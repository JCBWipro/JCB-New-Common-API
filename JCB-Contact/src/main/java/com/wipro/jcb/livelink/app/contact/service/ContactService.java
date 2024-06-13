package com.wipro.jcb.livelink.app.contact.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.wipro.jcb.livelink.app.contact.entity.Contact;
import com.wipro.jcb.livelink.app.contact.model.ContactResponse;
import com.wipro.jcb.livelink.app.contact.repo.ContactRepository;

@Service
public class ContactService {
	
	@Autowired
	private ContactRepository contactRepository;
	
	public List<ContactResponse> getContactDetailsByUserId(int id){
		List<Object[]> listOfContacts = contactRepository.findContactDetailsByUserId(id);
		List<ContactResponse> listOfContactResponse = new ArrayList<>();
		for(Object[] data : listOfContacts) {
			ContactResponse contactResponse = new ContactResponse();
			contactResponse.setId((int) data[0]);
			contactResponse.setEmail(data[1].toString());
			contactResponse.setMobileNumber(data[2].toString());
			listOfContactResponse.add(contactResponse);
		}
		return listOfContactResponse;
	}

}
