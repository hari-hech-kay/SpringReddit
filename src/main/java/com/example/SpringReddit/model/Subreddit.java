package com.example.SpringReddit.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Subreddit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Community is required")
	private String name;

	@NotBlank(message = "Description is required")
	private String description;

	@OneToMany(fetch = FetchType.LAZY)
	private List<Post> posts;
	private Instant createdDate;

	@ManyToOne(fetch = FetchType.LAZY)
	private RedditUser user;

}