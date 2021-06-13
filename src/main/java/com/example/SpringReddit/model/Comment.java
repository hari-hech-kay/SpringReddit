package com.example.SpringReddit.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message = "Comment text is required")
	private String text;
	private Instant createdDate;

	@ManyToOne
	@JoinColumn(name = "postId", referencedColumnName = "postId")
	private Post post;

	@ManyToOne
	@JoinColumn(name = "userId", referencedColumnName = "userId")
	private RedditUser user;

}