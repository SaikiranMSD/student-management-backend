package com.cts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
public class StudentApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentApiGatewayApplication.class, args);
	}

	@Bean
	public CorsFilter corsFilter() {
		CorsConfiguration config = new CorsConfiguration();
		// Allow all origins for deployment flexibility (or set via CORS_ORIGIN env var)
		String corsOrigin = System.getenv("CORS_ORIGIN");
		if (corsOrigin != null && !corsOrigin.isEmpty()) {
			config.addAllowedOrigin(corsOrigin);
		} else {
			config.addAllowedOriginPattern("*");
		}
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		config.setAllowCredentials(true);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}

}
