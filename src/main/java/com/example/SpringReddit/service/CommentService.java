package com.example.SpringReddit.service;

import com.example.SpringReddit.dto.CommentDto;
import com.example.SpringReddit.exception.CommentNotFoundException;
import com.example.SpringReddit.exception.PostNotFoundException;
import com.example.SpringReddit.mapper.CommentMapper;
import com.example.SpringReddit.model.Comment;
import com.example.SpringReddit.model.Post;
import com.example.SpringReddit.model.RedditUser;
import com.example.SpringReddit.repository.CommentRepository;
import com.example.SpringReddit.repository.PostRepository;
import com.example.SpringReddit.repository.RedditUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
@Transactional
public class CommentService {

	private final CommentRepository commentRepository;
	private final RedditUserRepository redditUserRepository;
	private final PostRepository postRepository;
	private final CommentMapper commentMapper;
	private final AuthService authService;

	public Long create(CommentDto commentDto) {
		Post post = postRepository.findById(commentDto.getPostId())
				.orElseThrow(() -> new PostNotFoundException("Post not found with id " + commentDto.getPostId()));

		return commentRepository.save(commentMapper.fromDto(commentDto, post, authService.getCurrentUser())).getId();
	}

	@Transactional(readOnly = true)
	public List<CommentDto> getByPost(Long postId) {
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new PostNotFoundException("Post not found with id " + postId));
		return commentRepository.findAllByPost(post)
				.stream().map(commentMapper::toDto)
				.collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public List<CommentDto> getByUser(String username) {
		RedditUser user = redditUserRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with username u/" + username));
		return commentRepository.findAllByUser(user)
				.stream().map(commentMapper::toDto)
				.collect(Collectors.toList());
	}

	public void delete(Long commentId) {
		Comment comment = commentRepository.findById(commentId)
				.orElseThrow(() -> new CommentNotFoundException("Comment not found with id " + commentId));
		;
		if (comment.getUser().getUserId().equals(authService.getCurrentUser().getUserId()))
			commentRepository.deleteById(commentId);
	}


}
