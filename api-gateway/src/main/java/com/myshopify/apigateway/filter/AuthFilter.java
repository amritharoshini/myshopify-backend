package com.myshopify.apigateway.filter;

import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.myshopify.apigateway.model.TokenValidationResponse;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;




@Component
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {
	
	private static final String[] BYPASS_PATHS = {"/api/auth/**", "/eureka/**"};
	
	@Autowired
	private RouteValidator validator;
	
	@Autowired 
	private WebClient webClient;
	
	public AuthFilter() {
		super(Config.class);
	}
	
	public static class Config{
		
	}
	
	
	@Override
	public GatewayFilter apply(Config config) {
		return ((exchange, chain) -> {			
			ServerHttpRequest request = exchange.getRequest();
            String requestPath = request.getPath().toString();

            if(!validator.isSecured.test(request)) {
            	return chain.filter(exchange);
            }
			
			String token = extractTokenFromHeader(request);
	        if (StringUtils.isEmpty(token)) {
	            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
	            return exchange.getResponse().setComplete();
	        }
			
	        
	        return webClient
	                .get()
	                .uri("http://localhost:9400/api/auth/validate", uriBuilder -> uriBuilder.queryParam("token", token).build())
	                .retrieve()
	                .bodyToMono(TokenValidationResponse.class)
	                .flatMap(tokenResponse -> {
	                    if (tokenResponse != null && tokenResponse.isTokenValid()) {
	                        return chain.filter(exchange); // Proceed with the filter chain
	                    } else {
	                        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
	                        return exchange.getResponse().setComplete();
	                    }
	                });

		});
	}
	
	private String extractTokenFromHeader(ServerHttpRequest request) {
        String authHeader = request.getHeaders().getFirst("Authorization");
        if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7); // Extract the token after "Bearer "
        }
        return null;
    }

}
