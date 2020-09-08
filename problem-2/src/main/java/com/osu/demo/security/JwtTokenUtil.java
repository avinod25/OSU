package com.osu.demo.security;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * The Class JwtTokenUtil.
 * 
 * @author Vinod Arroju
 */
@Component
public class JwtTokenUtil implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2737855553315006968L;

	/** The secret. */
	@Value("${jwt.secret}")
	private String secret;
	
	/** The jwt token validity. */
	@Value("${jwt.token_validity}")
	private Long jwtTokenValidity;

	/**
	 * Gets the username from token.
	 *
	 * @param token the {@code String}
	 * @return the username from token
	 */
	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	/**
	 * Gets the expiration date from token.
	 *
	 * @param token the {@code String}
	 * @return the expiration {@code Date} from token
	 */
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	/**
	 * Gets the claim from token.
	 *
	 * @param <T> the generic type
	 * @param token the {@code String}
	 * @param claimsResolver the {@code Function<Claims, T>}
	 * @return the claim from token
	 */
	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}
    
    /**
     * Gets the all claims from token.
     *
     * @param token the {@code String}
     * @return the all {@code Claims} from token
     */
    //for retrieveing any information from token we will need the secret key
	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

	/**
	 * Checks if the token has expired.
	 *
	 * @param token the {@code String}
	 * @return the boolean
	 */
	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	/**
	 * Generate token for user.
	 *
	 * @param userDetails the {@code UserDetails}
	 * @return the string
	 */
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return doGenerateToken(claims, userDetails.getUsername());
	}

	//while creating the token -
	//1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
	//2. Sign the JWT using the HS512 algorithm and secret key.
	//3. According to JWS Compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
	/**
	 * Do generate token.
	 *
	 * @param claims the {@code Map<String, Object>}
	 * @param subject the {@code String}
	 * @return the string
	 */
	private String doGenerateToken(Map<String, Object> claims, String subject) {

		return Jwts.builder().setClaims(claims)
				.setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + jwtTokenValidity * 1000))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	/**
	 * Validate token.
	 *
	 * @param token the {@code String}
	 * @param userDetails the {@code UserDetails}
	 * @return the boolean
	 */
	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

}
