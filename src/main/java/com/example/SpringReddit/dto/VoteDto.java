package com.example.SpringReddit.dto;

import com.example.SpringReddit.model.VoteType;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class VoteDto {
	private VoteType voteType;
	private Long postId;
}
