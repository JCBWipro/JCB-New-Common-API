package com.wipro.jcb.livelink.app.apigateway.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

import com.wipro.jcb.livelink.app.util.JwtUtils;
import com.google.common.net.HttpHeaders;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
	
	//We are Calling this AuthenticationFilter before redirecting request to corresponding MicroServices by defining filters in properties file

	@Autowired
	private RouteValidater routeValidater;
	
	@Autowired
	private JwtUtils jwtUtils;

	AuthenticationFilter() {
		super(Config.class);
	}

	@Override
	public GatewayFilter apply(Config config) {
		return ((exchange, chain) -> {
			if (routeValidater.isSecured.test(exchange.getRequest())) {
				// header contains token or not
				if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
					new RuntimeException("Missing Authorization Header");
				}

				// If Header contains the token, then get that header
				String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
				if(authHeader!=null && authHeader.startsWith("Bearer ")) {
					
					//Removing Extra 7 Spaces contains in Bearer Token
					authHeader=authHeader.substring(7);
				}
				try {
					//Rest Call to AuthService
					//But to Mask our token, will go with JwtUtils and will ignore this direct API call
					//webClientBuilder.build().get().uri("http://localhost:9898/validate?token"+authHeader).retrieve().bodyToMono(String.class).block();
					jwtUtils.validateToken(authHeader);
				}catch(Exception e) {
					System.out.println("Invalid Access");
					throw new RuntimeException("UnAuthorized Access to the Application");
				}
			}
			return chain.filter(exchange);
		});
	}

	public static class Config {

	}

}
