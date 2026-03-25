package com.cognizant.greengov.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AnonymousAuthenticationProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

	private final JwtAuthenticationFilter jwtFilter;

	public SecurityConfig(JwtAuthenticationFilter jwtFilter) {
		this.jwtFilter = jwtFilter;
	}

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())
				.sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(auth -> auth.requestMatchers("/api/admin/auth/login").permitAll()

						// Public auth endpoints
						.requestMatchers("/api/auth/register", "/api/auth/login").permitAll()

						// Admin console
						.requestMatchers("/api/admin/**").hasRole("ADMIN")

						// Programs (GET public for now)
						.requestMatchers(HttpMethod.GET, "/api/programs").permitAll()

						// Applications (citizen/business)
//						.requestMatchers(HttpMethod.POST, "/api/applications").hasAnyRole("CITIZEN", "BUSINESS_OWNER")
						.requestMatchers(HttpMethod.POST, "/api/applications").permitAll()

						// Incentives → Disbursement Officers only
						.requestMatchers(HttpMethod.POST, "/api/incentives/**").hasAuthority("DISBURSEMENT_OFFICER")

						.requestMatchers(HttpMethod.POST, "/api/disbursements/**").hasAuthority("DISBURSEMENT_OFFICER")

						// Program creation → Program Manager
						.requestMatchers(HttpMethod.POST, "/api/programs/**").permitAll()
						
						//Sustainable program creation -> Prog manager
						.requestMatchers(HttpMethod.POST, "/api/projects").hasAuthority("PROGRAM_MANAGER")

						// Compliance → Compliance Officer
						.requestMatchers("/api/compliance/**").hasAuthority("COMPLIANCE_OFFICER")

						// Audits → Audit Manager
						.requestMatchers("/api/audits/**").hasAuthority("AUDIT_MANAGER")

						.requestMatchers(HttpMethod.GET, "/api/projects/**").permitAll()

						// Anything else requires auth
						.anyRequest().authenticated())
				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	AuthenticationManager authenticationManager() {
		return new ProviderManager(new AnonymousAuthenticationProvider("greengov-anon"));
	}
}