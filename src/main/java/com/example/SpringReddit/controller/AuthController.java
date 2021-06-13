package com.example.SpringReddit.controller;

import com.example.SpringReddit.dto.AuthenticationResponse;
import com.example.SpringReddit.dto.LoginRequest;
import com.example.SpringReddit.dto.RegisterRequest;
import com.example.SpringReddit.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

	AuthService authService;

	@PostMapping("/signup")
	public String signup(@RequestBody RegisterRequest registerRequest) {
		authService.signup(registerRequest);
		return "User registration successful";
	}

	@GetMapping("/accountVerification")
	public String verifyAccount(@RequestParam(name = "token") String token) {
		authService.verifyAccount(token);
		return "User account activated successfully";
	}

	@PostMapping("/login")
	public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
		return authService.login(loginRequest);
	}

}
