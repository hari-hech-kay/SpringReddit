package com.example.SpringReddit.mapper;

import com.example.SpringReddit.dto.SubredditDto;
import com.example.SpringReddit.model.Post;
import com.example.SpringReddit.model.Subreddit;
import org.jetbrains.annotations.NotNull;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubredditMapper {

	@Mapping(target = "postCount", expression = "java(mapPosts(subreddit.getPosts()))")
	SubredditDto toDto(Subreddit subreddit);

	default Integer mapPosts(@NotNull List<Post> posts) {
		return posts.size();
	}

	@InheritInverseConfiguration
	@Mapping(target = "posts", ignore = true)
	Subreddit fromDto(SubredditDto subredditDto);
}
