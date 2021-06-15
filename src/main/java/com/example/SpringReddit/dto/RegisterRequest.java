package com.example.SpringReddit.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@NonNull
public class RegisterRequest {

	private String username;
	private String password;
	private String email;
}

