package com.example.SpringReddit.dto;

import lombok.*;
import org.springframework.lang.Nullable;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CommentDto {
	@Nullable
	private Long id;
	private Long postId;
	private Instant createdDate;
	private String text;
	private String userName;
}
