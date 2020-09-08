package com.osu.demo.security;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * The Class AccessTokenAuthenticationEntryPoint.
 */
@Component
public class AccessTokenAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4605957042481722292L;

	/**
	 * Commence.
	 *
	 * @param request the {@code HttpServletRequest}
	 * @param response the {@code HttpServletResponse}
	 * @param authException the {@code AuthenticationException}
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException {
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
	}
}
