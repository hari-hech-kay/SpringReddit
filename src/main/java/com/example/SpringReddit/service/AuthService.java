package com.example.SpringReddit.service;

import com.example.SpringReddit.dto.AuthenticationResponse;
import com.example.SpringReddit.dto.LoginRequest;
import com.example.SpringReddit.dto.RegisterRequest;
import com.example.SpringReddit.exception.RedditException;
import com.example.SpringReddit.exception.UserNotFoundException;
import com.example.SpringReddit.model.Email;
import com.example.SpringReddit.model.RedditUser;
import com.example.SpringReddit.model.VerificationToken;
import com.example.SpringReddit.repository.RedditUserRepository;
import com.example.SpringReddit.repository.VerificationTokenRepository;
import com.example.SpringReddit.security.JwtProvider;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class AuthService {

	private final PasswordEncoder passwordEncoder;
	private final RedditUserRepository userRepository;
	private final VerificationTokenRepository tokenRepository;
	private final MailService mailService;
	private final AuthenticationManager authenticationManager;
	private final JwtProvider jwtProvider;

	public void signup(@NotNull RegisterRequest registerRequest) {
		RedditUser user = new RedditUser();
		user.setUsername(registerRequest.getUsername());
		user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
		user.setEmail(registerRequest.getEmail());
		user.setCreatedDate(Instant.now());
		user.setEnabled(false);

		userRepository.save(user);

		String token = generateVerificationToken(user);
		final String ACTIVATION_LINK = "http://localhost:8080/api/auth/accountVerification?token=" + token;
		mailService.sendMail(new Email(user.getEmail(),
				"SpringReddit Please verify your email address",
				"Click the below link to verify your email and activate your account" + ACTIVATION_LINK));
	}

	private String generateVerificationToken(RedditUser user) {
		String token = UUID.randomUUID().toString();

		VerificationToken verificationToken = new VerificationToken();
		verificationToken.setToken(token);
		verificationToken.setUser(user);
		tokenRepository.save(verificationToken);

		return token;
	}

	public void verifyAccount(String token) {
		VerificationToken verificationToken = tokenRepository.findByToken(token)
				.orElseThrow(() -> new RedditException(HttpStatus.BAD_REQUEST, "Invalid account verification token"));
		RedditUser user = verificationToken.getUser();
		user.setEnabled(true);
		userRepository.save(user);
	}

	public AuthenticationResponse login(LoginRequest loginRequest) {

		Authentication auth = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(auth);
		String jwt = jwtProvider.generateJwt(auth);
		return new AuthenticationResponse(jwt, loginRequest.getUsername());
	}

	public RedditUser getCurrentUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth == null) return null;
		User principal = (User) auth.getPrincipal();
		return userRepository.findByUsername(principal.getUsername())
				.orElseThrow(() -> new UserNotFoundException("User not found with username " + principal.getUsername()));
	}
}
