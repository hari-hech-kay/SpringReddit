package com.example.SpringReddit.controller;

import com.example.SpringReddit.dto.CommentDto;
import com.example.SpringReddit.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@AllArgsConstructor
public class CommentController {

	CommentService commentService;

	@PostMapping
	public ResponseEntity<Long> createComment(@RequestBody CommentDto commentsDto) {
		Long commentId = commentService.create(commentsDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(commentId);
	}

	@GetMapping("/post/{postId}")
	public ResponseEntity<List<CommentDto>> getAllCommentsByPost(@PathVariable Long postId) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(commentService.getByPost(postId));
	}

	@GetMapping("/user/{username}")
	public ResponseEntity<List<CommentDto>> getAllCommentsByUser(@PathVariable String username) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(commentService.getByUser(username));
	}
}
