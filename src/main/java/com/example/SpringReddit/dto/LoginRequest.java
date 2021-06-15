package com.example.SpringReddit.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@NonNull
public class LoginRequest {
	private String username;
	private String password;
}
