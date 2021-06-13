package com.example.SpringReddit.mapper;

import com.example.SpringReddit.dto.CommentDto;
import com.example.SpringReddit.model.Comment;
import com.example.SpringReddit.model.Post;
import com.example.SpringReddit.model.RedditUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class CommentMapper {

	@Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
	public abstract Comment fromDto(CommentDto commentDto, Post post, RedditUser user);

	@Mapping(target = "postId", source = "post.postId")
	@Mapping(target = "userName", source = "user.userId")
	public abstract CommentDto toDto(Comment comment);

}
