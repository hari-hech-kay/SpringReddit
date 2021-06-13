package com.example.SpringReddit.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.Instant;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VerificationToken {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;
	private String token;

	@OneToOne(fetch = LAZY)
	private RedditUser user;
	private Instant expiryDate;
}