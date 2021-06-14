package com.example.SpringReddit.controller;

import com.example.SpringReddit.dto.VoteDto;
import com.example.SpringReddit.service.VoteService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/api/vote")
public class VoteController {

	private final VoteService voteService;

	@PostMapping
	public void vote(@Valid @RequestBody VoteDto voteDto) {
		voteService.vote(voteDto);
	}
}
