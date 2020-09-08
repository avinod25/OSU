package com.osu.demo.service;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.osu.demo.common.DemoException;
import com.osu.demo.common.ErrorCode;
import com.osu.demo.model.UserInfo;
import com.osu.demo.repository.UserInfoRepository;

/**
 * The Class UserSigninService.
 * 
 * @author Vinod Arroju
 */
@Service
public class UserSigninService implements UserDetailsService {

	/** The log. */
	private Logger log = LogManager.getLogger(UserSigninService.class);
	
	/** The authentication manager. */
	@Autowired
	private AuthenticationManager authenticationManager;
	
	/** The user inforepository. */
	@Resource
	UserInfoRepository userInforepository;
	
	/**
	 * Authenticate.
	 *
	 * @param username the {@code String}
	 * @param password the {@code String}
	 * @throws DemoException the demo exception
	 */
	public void authenticate(String username, String password) throws DemoException {
		try {
			Authentication auth= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			log.debug("auth::isAuthenticated::"+auth.isAuthenticated());
		} catch (DisabledException e) {
			throw new DemoException(ErrorCode.OtherError, e);
		} catch (BadCredentialsException e) {
			throw new DemoException(ErrorCode.InputError, e);
		}
	}
	
	/**
	 * Load user by username.
	 *
	 * @param username the {@code String}
	 * @return the {@code UserDetails}
	 * @throws UsernameNotFoundException the username not found exception
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserInfo userInfo=userInforepository.findByUserName(username);
		if(userInfo!=null) {
			return User.builder().username(username).password(userInfo.getUserPassword())
					.roles(userInfo.getRole())
					.accountLocked(false)
					.accountExpired(false)
					.build();
		}
		else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}
}