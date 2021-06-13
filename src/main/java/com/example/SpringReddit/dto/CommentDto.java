package com.example.SpringReddit.dto;

import lombok.*;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CommentDto {
	private Long id;
	private Long postId;
	private Instant createdDate;
	private String text;
	private String userName;
}
