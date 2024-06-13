package com.wipro.jcb.livelink.app.user.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import com.wipro.jcb.livelink.app.user.entity.Contact;
import com.wipro.jcb.livelink.app.user.entity.User;
import com.wipro.jcb.livelink.app.user.model.ContactRequest;
import com.wipro.jcb.livelink.app.user.model.UserRequest;
import com.wipro.jcb.livelink.app.user.model.UserResponse;
import com.wipro.jcb.livelink.app.user.repo.ContactRepository;
import com.wipro.jcb.livelink.app.user.repo.UserRepository;

@Service
@CacheConfig(cacheNames="{User}")
public class UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ContactRepository contactRepository;
	// you can use the @CacheEvict annotation, because it doesn't add anything to the cache unlike the @Cacheable annotation.
	//Use CacheEvict when you have to remove stale or unused data from the cache
	
	@CacheEvict(value="users", allEntries=true)
	public void saveUser(UserRequest userRequest) {
		User user = new User();
		user.setFirstName(userRequest.getFirstName());
		user.setLastName(userRequest.getLastName());
		user.setAge(userRequest.getAge());
		User newUser = userRepo.save(user);
		List<Contact> listOfContact = new ArrayList<>();
		
		for(ContactRequest contactRequest : userRequest.getContactRequest()){
			Contact contact = new Contact();
			contact.setUser(newUser);
			contact.setAddressLine1(contactRequest.getAddressLine1());
			contact.setAddressLine2(contactRequest.getAddressLine2());
			contact.setCity(contactRequest.getCity());
			contact.setMobileNumber("+91"+contactRequest.getMobileNumber());
			contact.setEmail(contactRequest.getEmail());
			contact.setPincode(contactRequest.getPincode());
			contact.setZip(contactRequest.getZip());
			listOfContact.add(contact);
		}
		//Save list of contacts
		contactRepository.saveAll(listOfContact);
	}
	
	public UserResponse findUserDetailsById(int id) throws InterruptedException {
		Thread.sleep(2000);
		System.out.println("findById() called from DB");
		User user = userRepo.findUserDetailsById(id).get();
		UserResponse userResponse = new UserResponse();
		userResponse.setId(user.getId());
		userResponse.setFirstName(user.getFirstName());
		userResponse.setLastName(user.getLastName());
		userResponse.setAge(user.getAge());
		return userResponse;
	}
	
	// if REDIS is having data then this method does not execute
	public List<UserResponse> findAllUserDetails() throws InterruptedException {
		Thread.sleep(2000);
		System.out.println("findAllUserDetails() called from DB");
		List<User> listOfUser = userRepo.findAllUserDetails();
		List<UserResponse> listOfUsers = new ArrayList<>();
		for(User user : listOfUser) {
			UserResponse userResponse = new UserResponse();
			userResponse.setId(user.getId());
			userResponse.setFirstName(user.getFirstName());
			userResponse.setLastName(user.getLastName());
			userResponse.setAge(user.getAge());
			listOfUsers.add(userResponse);
		}
		return listOfUsers;
	}
	
}
