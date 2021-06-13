package com.example.SpringReddit.dto;

import com.example.SpringReddit.model.VoteType;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostResponse {
	private Long postId;
	private String postName;
	private String description;
	private String url;
	private String duration;
	private String username;
	private String subredditName;
	private Integer voteCount;
	private Integer commentCount;
	private VoteType voteType;
}
