package com.example.SpringReddit.service;

import com.example.SpringReddit.dto.SubredditDto;
import com.example.SpringReddit.exception.SubredditNotFoundException;
import com.example.SpringReddit.mapper.SubredditMapper;
import com.example.SpringReddit.model.Subreddit;
import com.example.SpringReddit.repository.SubredditRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class SubredditService {

	private final SubredditRepository subredditRepository;
	private final SubredditMapper subredditMapper;

	public SubredditDto create(SubredditDto subredditDto) {
		Subreddit subreddit = subredditRepository.save(subredditMapper.fromDto(subredditDto));
		subredditDto.setId(subreddit.getId());
		return subredditDto;
	}

	@Transactional(readOnly = true)
	public List<SubredditDto> getAll() {
		return subredditRepository.findAll()
				.stream()
				.map(subredditMapper::toDto)
				.collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public SubredditDto getSubreddit(Long id) {
		return subredditRepository.findById(id)
				.map(subredditMapper::toDto)
				.orElseThrow(() -> new SubredditNotFoundException("Subreddit with id " + id + "does not exist"));
	}
}
