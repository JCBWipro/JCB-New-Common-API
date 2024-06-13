package com.wipro.jcb.livelink.app.auth.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.jcb.livelink.app.auth.entity.UserCredential;


public interface UserCredentialrepository extends JpaRepository<UserCredential, Integer>{

	Optional<UserCredential> findByName(String username);

}
