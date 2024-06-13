package com.wipro.jcb.livelink.app.auth.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.wipro.jcb.livelink.app.auth.entity.UserCredential;
import com.wipro.jcb.livelink.app.auth.repo.UserCredentialrepository;


@Component
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserCredentialrepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<UserCredential> credential = repository.findByName(username);
		return credential.map(CustomUserDetails::new)
				 .orElseThrow(() -> new UsernameNotFoundException("user not found " + username));
	}

}
