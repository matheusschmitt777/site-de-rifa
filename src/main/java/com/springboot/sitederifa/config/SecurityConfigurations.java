package com.springboot.sitederifa.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {
	
	@Autowired
    SecurityFilter securityFilter;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
	    return httpSecurity
	    		.cors(cors -> cors.configurationSource(request -> {
	    		    CorsConfiguration configuration = new CorsConfiguration();
	    		    configuration.applyPermitDefaultValues();
	    		    configuration.addAllowedOrigin("http://localhost:3000");
	    		    configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE"));
	    		    return configuration;
	    		}))
	            .csrf(csrf -> csrf.disable())
	            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	            .authorizeHttpRequests(authorize -> authorize
	            		.requestMatchers(new AntPathRequestMatcher("/auth/login")).permitAll()
	            		.requestMatchers(new AntPathRequestMatcher("/auth/register")).permitAll()
	                    .requestMatchers(new AntPathRequestMatcher("/**", "GET")).permitAll()
	                    .requestMatchers(new AntPathRequestMatcher("/**", "POST")).permitAll()
	                    .requestMatchers(new AntPathRequestMatcher("/**", "PUT")).hasRole("ADMIN")
	                    .requestMatchers(new AntPathRequestMatcher("/**", "DELETE")).hasRole("ADMIN")
	                    .anyRequest().authenticated()
	            )
	            .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
	            .build();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
	
	@Bean
	public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
