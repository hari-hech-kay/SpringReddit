package com.example.SpringReddit.dto;

import lombok.*;
import org.springframework.lang.Nullable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@NonNull
public class SubredditDto {
	@Nullable
	private Long id;
	private String name;
	private String description;
	private Integer postCount;

}
