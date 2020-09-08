package com.osu.demo.controller;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.osu.demo.api.model.BaseResponse;
import com.osu.demo.api.model.SigninRequest;
import com.osu.demo.api.model.SigninResponse;
import com.osu.demo.api.model.Status;
import com.osu.demo.common.DemoException;
import com.osu.demo.security.JwtTokenUtil;
import com.osu.demo.service.UserSigninService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * The Class SigninController.
 * 
 * @author Vinod Arroju
 */
@RestController
@CrossOrigin
@RequestMapping("/api/users")
@Tag(name = "Authenticate", description = "Access token API")
public class SigninController {

	/** The log. */
	private Logger log = LogManager.getLogger(SigninController.class);

	/** The jwt token util. */
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	/** The user signin service. */
	@Autowired
	private UserSigninService userSigninService;
	
	/** The Constant CONTENT_TYPE_JSON. */
	private final static String CONTENT_TYPE_JSON = "application/json";

	/**
	 * Creates the authentication token.
	 *
	 * @param authenticationRequest the {@code SigninRequest}
	 * @return the {@code SigninResponse}
	 * @throws Exception the exception
	 */
	@Operation(summary = "Get access token. Default username: 'osutest', password: 'Welcome@01'  ", responses = {
			@ApiResponse(responseCode = "200", description = "Successful Operation", content = @Content(mediaType = CONTENT_TYPE_JSON, schema = @Schema(implementation = SigninResponse.class))),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = CONTENT_TYPE_JSON, schema = @Schema(implementation = BaseResponse.class))) })
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody(required = true) @Valid SigninRequest authenticationRequest)
			throws Exception {
		SigninResponse response = new SigninResponse();
		HttpStatus status = HttpStatus.OK;
		try {
			userSigninService.authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

			final UserDetails userDetails = userSigninService.loadUserByUsername(authenticationRequest.getUsername());

			final String token = jwtTokenUtil.generateToken(userDetails);

			response.setAccessToken(token);
			response.setStatus(Status.SUCCESS);

			return ResponseEntity.ok(response);
		} catch (DemoException e) {
			log.error("ERROR::", e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			response.setStatus(Status.ERROR);
			response.setMessage(e.getMessage());
		}
		return ResponseEntity.status(status).body(response);
	}

	
}
