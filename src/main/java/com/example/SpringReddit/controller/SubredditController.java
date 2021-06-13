package com.example.SpringReddit.controller;

import com.example.SpringReddit.dto.SubredditDto;
import com.example.SpringReddit.service.SubredditService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subreddit")
@AllArgsConstructor
public class SubredditController {

	SubredditService subredditService;

	@PostMapping
	public SubredditDto createSubreddit(@RequestBody SubredditDto subredditDto) {
		return subredditService.create(subredditDto);
	}

	@GetMapping
	public List<SubredditDto> getAllSubreddits() {
		return subredditService.getAll();
	}

	@GetMapping("/{id}")
	public SubredditDto getSubreddit(@PathVariable Long id) {
		return subredditService.getSubreddit(id);
	}

}
