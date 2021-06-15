package com.example.SpringReddit.dto;

import lombok.*;
import org.springframework.lang.Nullable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@NonNull
public class PostRequest {

	private String postName;
	private String description;

	@Nullable
	private String url;
	private String subredditName;
}
