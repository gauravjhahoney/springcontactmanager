package com.SmartContact.Myconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public UserDetailsService userDetailsService()
	
	{
		
		UserDetails normalUser= User.withUsername("gaurav").password(passwordEncoder().encode("password")).roles("USER").build();
		UserDetails adminUser= User.withUsername("gaurav1").password(passwordEncoder().encode("password1")).roles("ADMIN").build();
		InMemoryUserDetailsManager inMemoryUserDetailsManager=new InMemoryUserDetailsManager(normalUser);
		return inMemoryUserDetailsManager;
		
				
	}
	
	
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception
	{
		httpSecurity.csrf().disable()
		.authorizeHttpRequests()
		.requestMatchers("/admin/**").hasRole("ADMIN")
		.requestMatchers("/user/**").hasRole("USER")
		
		.requestMatchers("/user/index")
		.permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.formLogin();
		return httpSecurity.build();
	}
	
	
}
