package com.example.SpringReddit.repository;

import com.example.SpringReddit.model.Post;
import com.example.SpringReddit.model.RedditUser;
import com.example.SpringReddit.model.Subreddit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
	List<Post> findAllBySubreddit(Subreddit subreddit);

	List<Post> findAllByUser(RedditUser user);
}