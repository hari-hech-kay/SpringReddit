package com.example.SpringReddit.service;

import com.example.SpringReddit.dto.PostRequest;
import com.example.SpringReddit.dto.PostResponse;
import com.example.SpringReddit.exception.PostNotFoundException;
import com.example.SpringReddit.exception.SubredditNotFoundException;
import com.example.SpringReddit.exception.UserNotFoundException;
import com.example.SpringReddit.mapper.PostMapper;
import com.example.SpringReddit.model.Post;
import com.example.SpringReddit.model.RedditUser;
import com.example.SpringReddit.model.Subreddit;
import com.example.SpringReddit.repository.CommentRepository;
import com.example.SpringReddit.repository.PostRepository;
import com.example.SpringReddit.repository.RedditUserRepository;
import com.example.SpringReddit.repository.SubredditRepository;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class PostService {
	private final AuthService authService;
	private final RedditUserRepository userRepository;
	private final SubredditRepository subredditRepository;
	private final PostRepository postRepository;
	private final CommentRepository commentRepository;
	private final PostMapper postMapper;

	public Long create(@NotNull PostRequest postRequest) {
		Subreddit subreddit = subredditRepository.findByName(postRequest.getSubredditName())
				.orElseThrow(() -> new SubredditNotFoundException(postRequest.getSubredditName()));
		RedditUser user = authService.getCurrentUser();
		return postRepository.save(postMapper.fromDto(postRequest, subreddit, user)).getPostId();
	}

	@Transactional(readOnly = true)
	public List<PostResponse> getAll() {
		return postRepository.findAll()
				.stream().map(postMapper::toDto)
				.collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public PostResponse getPost(Long id) {
		return postRepository.findById(id).map(postMapper::toDto)
				.orElseThrow(() -> new PostNotFoundException(id.toString()));
	}

	@Transactional(readOnly = true)
	public List<PostResponse> getPostsBySubreddit(Long subredditId) {
		Subreddit subreddit = subredditRepository.findById(subredditId)
				.orElseThrow(() -> new SubredditNotFoundException(subredditId));
		return postRepository.findAllBySubreddit(subreddit)
				.stream().map(postMapper::toDto)
				.collect(Collectors.toList());

	}

	@Transactional(readOnly = true)
	public List<PostResponse> getPostsByUser(String username) {
		RedditUser user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UserNotFoundException("User with id " + username + " does not exist"));
		return postRepository.findAllByUser(user)
				.stream().map(postMapper::toDto)
				.collect(Collectors.toList());

	}

	public void delete(Long postId) {
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new PostNotFoundException(postId.toString()));
		if (post.getUser().getUserId().equals(authService.getCurrentUser().getUserId())) {
			commentRepository.deleteAllByPost(post);
			postRepository.deleteById(postId);
		}
	}
}
