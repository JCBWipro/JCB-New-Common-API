package com.wipro.jcb.livelink.app.user.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.wipro.jcb.livelink.app.user.model.UserRequest;
import com.wipro.jcb.livelink.app.user.model.UserResponse;
import com.wipro.jcb.livelink.app.user.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	@Value("${contactUri}")
	public String contactUri;
	
	@Autowired
	private UserService userService;
	
	//Just For Testing
	@GetMapping 
	public String getString() {
		return "User Authenticated Successfuly : " + environment.getProperty("local.server.port");
	}
	
	@GetMapping("/getresponseFromContactService")
	public String getresponseFromContactService() {
		String response = webClientBuilder.build().get().uri(contactUri).retrieve().bodyToMono(String.class).block();
		return response;
	}
	
	@PostMapping("/saveUserWithContact")
	public void saveUserWithContact(@RequestBody UserRequest userRequest) {
		userService.saveUser(userRequest);
	}
	
	@Cacheable(value="usersById")
	@GetMapping("/getUserById/{id}")
	public UserResponse getUserById(@PathVariable int id) throws InterruptedException {
		return userService.findUserDetailsById(id);
	}
	
	@Cacheable(value="users")
	@GetMapping("/getAllUserDetails")
	public List<UserResponse> getAllUserDetails() throws InterruptedException {
		return userService.findAllUserDetails();
	}
	
}
