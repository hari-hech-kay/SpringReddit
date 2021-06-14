package com.example.SpringReddit.controller;

import com.example.SpringReddit.dto.PostRequest;
import com.example.SpringReddit.dto.PostResponse;
import com.example.SpringReddit.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
@AllArgsConstructor
public class PostController {

	private final PostService postService;

	@PostMapping("/create")
	public ResponseEntity<Long> createPost(@Valid @RequestBody PostRequest postRequest) {
		Long postId = postService.create(postRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(postId);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PostResponse> getPost(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(postService.getPost(id));
	}

	@GetMapping()
	public ResponseEntity<List<PostResponse>> getAllPosts() {
		return ResponseEntity.status(HttpStatus.OK).body(postService.getAll());
	}

	@GetMapping("/subreddit/{id}")
	public ResponseEntity<List<PostResponse>> getPostsBySubreddit(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(postService.getPostsBySubreddit(id));
	}

	@GetMapping("/user/{username}")
	public ResponseEntity<List<PostResponse>> getPostByUser(@PathVariable String username) {
		return ResponseEntity.status(HttpStatus.OK).body(postService.getPostsByUser(username));
	}

	@DeleteMapping("/{id}")
	public void deletePost(@PathVariable Long id) {
		postService.delete(id);
	}
}
