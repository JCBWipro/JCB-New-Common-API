package com.wipro.jcb.livelink.app.contact.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.jcb.livelink.app.contact.entity.Contact;
import com.wipro.jcb.livelink.app.contact.model.ContactResponse;
import com.wipro.jcb.livelink.app.contact.model.ContactSMSEmailResponse;
import com.wipro.jcb.livelink.app.contact.repo.ContactRepository;

@Service
public class ContactService {
	
	@Autowired
	private ContactRepository contactRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public List<ContactSMSEmailResponse> getContactSmsEmailByUserId(int id){
		List<Object[]> listOfContacts = contactRepository.findContactSmsEmailByUserId(id);
		List<ContactSMSEmailResponse> listOfContactResponse = new ArrayList<>();
		for(Object[] data : listOfContacts) {
			ContactSMSEmailResponse contactResponse = new ContactSMSEmailResponse();
			contactResponse.setId((int) data[0]);
			contactResponse.setEmail(data[1].toString());
			contactResponse.setMobileNumber(data[2].toString());
			listOfContactResponse.add(contactResponse);
		}
		return listOfContactResponse;
	}

	public List<ContactResponse> getContactDetailsByUserID(int id) {
		List<Contact> listOfContactDetails = contactRepository.findContactDetailsByUserID(id);
		List<ContactResponse> listOfContactResponse = new ArrayList<>();
		for(Contact contactResponse : listOfContactDetails) {
			ContactResponse response = new ContactResponse();
			response = modelMapper.map(contactResponse, ContactResponse.class);
			listOfContactResponse.add(response);
		}
		
		return listOfContactResponse;
	}

}
