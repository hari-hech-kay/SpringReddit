package com.example.SpringReddit.repository;

import com.example.SpringReddit.model.Comment;
import com.example.SpringReddit.model.Post;
import com.example.SpringReddit.model.RedditUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	List<Comment> findAllByPost(Post post);

	List<Comment> findAllByUser(RedditUser user);

	void deleteAllByPost(Post post);
}