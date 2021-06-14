package com.example.SpringReddit.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long postId;

	@NotBlank(message = "Post name cannot be empty")
	private String postName;

	@Nullable
	private String url;

	@Nullable
	@Lob
	private String description;
	private Integer voteCount = 0;

	@ManyToOne(fetch = FetchType.LAZY)
	private RedditUser user;
	private Instant createdDate;

	@ManyToOne(fetch = FetchType.LAZY)
	private Subreddit subreddit;

}