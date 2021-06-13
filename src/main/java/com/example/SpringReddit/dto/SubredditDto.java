package com.example.SpringReddit.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class SubredditDto {
	private Long id;
	private String name;
	private String description;
	private Integer postCount;

}
