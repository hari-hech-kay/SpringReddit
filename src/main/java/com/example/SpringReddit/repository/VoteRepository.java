package com.example.SpringReddit.repository;

import com.example.SpringReddit.model.Post;
import com.example.SpringReddit.model.RedditUser;
import com.example.SpringReddit.model.Vote;
import com.example.SpringReddit.model.VoteType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Long> {
	Vote findByPostAndUserAndVoteType(Post post, RedditUser currentUser, VoteType voteType);

	Optional<Vote> findByPostAndUser(Post post, RedditUser currentUser);
}