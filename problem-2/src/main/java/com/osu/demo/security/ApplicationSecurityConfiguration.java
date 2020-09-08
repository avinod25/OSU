package com.osu.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * The Class ApplicationSecurityConfiguration.
 * 
 * @author Vinod Arroju
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {

	/** The access token authentication entry point. */
	@Autowired
	private AccessTokenAuthenticationEntryPoint accessTokenAuthenticationEntryPoint;

	/** The user details service. */
	@Autowired
	private UserDetailsService userDetailsService;

	/** The access token request filter. */
	@Autowired
	private AccessTokenRequestFilter accessTokenRequestFilter;

	/**
	 * Global AuthenticationManager configuration to know from where to load
	 *	user for matching credentials. It uses BCryptPasswordEncoder for password encoding
	 *
	 * @param auth the {@code AuthenticationManagerBuilder}
	 * @throws Exception the exception
	 */
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	/**
	 * Password encoder.
	 *
	 * @return the {@code PasswordEncoder}
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * Authentication manager bean.
	 *
	 * @return the {@code AuthenticationManager}
	 * @throws Exception the exception
	 */
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	/**
	 * Security configuration.
	 *
	 * @param http the {@code HttpSecurity}
	 * @throws Exception the exception
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable().authorizeRequests()
				.antMatchers("/api/users/authenticate", "/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**")
				.permitAll().antMatchers(HttpMethod.GET, "/api/lobsters", "/api/lobsters/**").permitAll().anyRequest()
				.authenticated().and().exceptionHandling().authenticationEntryPoint(accessTokenAuthenticationEntryPoint)
				.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		// Filter to validate the tokens with every request
		http.addFilterBefore(accessTokenRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
}
