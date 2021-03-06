package com.example.SpringReddit.repository;

import com.example.SpringReddit.model.Subreddit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubredditRepository extends JpaRepository<Subreddit, Long> {
	Optional<Subreddit> findByName(String name);
}