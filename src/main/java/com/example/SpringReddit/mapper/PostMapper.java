package com.example.SpringReddit.mapper;

import com.example.SpringReddit.dto.PostRequest;
import com.example.SpringReddit.dto.PostResponse;
import com.example.SpringReddit.model.*;
import com.example.SpringReddit.repository.CommentRepository;
import com.example.SpringReddit.repository.VoteRepository;
import com.example.SpringReddit.service.AuthService;
import com.github.marlonlom.utilities.timeago.TimeAgo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Mapper(componentModel = "spring")
public abstract class PostMapper {

	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	private VoteRepository voteRepository;
	@Autowired
	private AuthService authService;

	@Mapping(target = "description", source = "postRequest.description")
	@Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
	@Mapping(target = "voteCount", constant = "0")
	public abstract Post fromDto(PostRequest postRequest, Subreddit subreddit, RedditUser user);

	@Mapping(target = "username", source = "user.username")
	@Mapping(target = "subredditName", source = "subreddit.name")
	@Mapping(target = "commentCount", expression = "java(commentCount(post))")
	@Mapping(target = "duration", expression = "java(getDuration(post))")
	@Mapping(target = "voteType", expression = "java(getVoteType(post))")
	public abstract PostResponse toDto(Post post);

	Integer commentCount(Post post) {
		return commentRepository.findAllByPost(post).size();
	}

	String getDuration(Post post) {
		return TimeAgo.using(post.getCreatedDate().toEpochMilli());
	}

	VoteType getVoteType(Post post) {
		Optional<Vote> voteOptional = voteRepository.findByPostAndUser(post, authService.getCurrentUser());
		return voteOptional.map(Vote::getVoteType).orElse(null);
	}
}
