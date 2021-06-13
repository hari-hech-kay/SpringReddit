package com.example.SpringReddit.service;

import com.example.SpringReddit.dto.VoteDto;
import com.example.SpringReddit.exception.PostNotFoundException;
import com.example.SpringReddit.model.Post;
import com.example.SpringReddit.model.Vote;
import com.example.SpringReddit.repository.PostRepository;
import com.example.SpringReddit.repository.VoteRepository;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class VoteService {

	private final PostRepository postRepository;
	private final VoteRepository voteRepository;
	private final AuthService authService;

	public void vote(@NotNull VoteDto voteDto) {
		Post post = postRepository.findById(voteDto.getPostId())
				.orElseThrow(() -> new PostNotFoundException("Post not found with id " + voteDto.getPostId() + " does not exist"));
		Optional<Vote> voteOptional = voteRepository.findByPostAndUser(post, authService.getCurrentUser());
		if (voteOptional.isPresent()) {
			Vote vote = voteOptional.get();
			if (vote.getVoteType().equals(voteDto.getVoteType())) {
				voteRepository.delete(vote);
				post.setVoteCount(voteDto.getVoteType().getDirection() * -1);
			} else {
				vote.setVoteType(voteDto.getVoteType());
				voteRepository.save(vote);
				post.setVoteCount(voteDto.getVoteType().getDirection() * 2);
			}
		} else saveVote(voteDto, post);
	}

	private void saveVote(VoteDto voteDto, Post post) {
		voteRepository.save(fromDto(voteDto, post));
		post.setVoteCount(voteDto.getVoteType().getDirection());
	}

	private Vote fromDto(VoteDto voteDto, Post post) {
		return Vote.builder()
				.voteType(voteDto.getVoteType())
				.post(post)
				.user(authService.getCurrentUser())
				.build();
	}

}
