package com.example.SpringReddit.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
	private String username;
	private String password;
	private String email;
}

