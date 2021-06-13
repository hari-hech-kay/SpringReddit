package com.example.SpringReddit.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

import static java.time.temporal.ChronoUnit.HOURS;

@Service
public class JwtProvider {

	@Value("${jwt.expirationTime}")
	private Long expirationTimeInHours;

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.issuer}")
	private String issuer;

	public String generateJwt(@NotNull Authentication auth) {
		Algorithm algorithm = Algorithm.HMAC512(secret);
		User user = (User) auth.getPrincipal();

		//try{
		return JWT.create()
				.withIssuer(issuer)
				.withIssuedAt(Date.from(Instant.now()))
				.withExpiresAt(Date.from(Instant.now().plus(expirationTimeInHours, HOURS)))
				.withSubject(user.getUsername())
				.sign(algorithm);
//        }
//        catch (JWTCreationException exception){
//            throw new RedditException(HttpStatus.INTERNAL_SERVER_ERROR, "Exception while generating JWT", exception.);
//        }
	}

	private DecodedJWT validateJwt(String jwt) {
		Algorithm algorithm = Algorithm.HMAC512(secret);
		//try {
		JWTVerifier verifier = JWT.require(algorithm)
				.withIssuer(issuer)
				.build();
		return verifier.verify(jwt);
//        }
//        catch (JWTVerificationException exception){
//            throw new RedditException(HttpStatus.UNAUTHORIZED,"Invalid or expired token. JWT verification failed", exception);
//        }
	}

	public String getUsernameFromJwt(String jwt) {
		DecodedJWT decodedJWT = validateJwt(jwt);
		return decodedJWT.getSubject();
	}
}
