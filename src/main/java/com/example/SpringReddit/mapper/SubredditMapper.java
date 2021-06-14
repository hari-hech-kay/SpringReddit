package com.example.SpringReddit.mapper;

import com.example.SpringReddit.dto.SubredditDto;
import com.example.SpringReddit.model.RedditUser;
import com.example.SpringReddit.model.Subreddit;
import com.example.SpringReddit.repository.PostRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class SubredditMapper {

	@Autowired
	private PostRepository postRepository;

	@Mapping(target = "postCount", expression = "java(postCount(subreddit))")
	public abstract SubredditDto toDto(Subreddit subreddit);

	Integer postCount(Subreddit subreddit) {
		return postRepository.findAllBySubreddit(subreddit).size();
	}

	@Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
	public abstract Subreddit fromDto(SubredditDto subredditDto, RedditUser user);
}
