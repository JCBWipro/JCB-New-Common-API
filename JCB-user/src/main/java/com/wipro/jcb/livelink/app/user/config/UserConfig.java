package com.wipro.jcb.livelink.app.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class UserConfig {
	
	@Bean
	public WebClient.Builder webClientBuilder() {
		return WebClient.builder();
	}
}
