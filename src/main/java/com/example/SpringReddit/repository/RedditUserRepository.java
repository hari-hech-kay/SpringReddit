package com.example.SpringReddit.repository;

import com.example.SpringReddit.model.RedditUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RedditUserRepository extends JpaRepository<RedditUser, Long> {
	Optional<RedditUser> findByUsername(String username);
}