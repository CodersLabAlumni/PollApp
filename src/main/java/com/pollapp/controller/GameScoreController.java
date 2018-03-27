package com.pollapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pollapp.entity.GameScore;
import com.pollapp.repository.GameScoreRepository;

@RestController
@RequestMapping("/gameScores")
public class GameScoreController {

	@Autowired
	private GameScoreRepository gameScoreRepository;
	
	@PostMapping("")
	public GameScore createGameScore (@RequestBody GameScore gameScore) {
		return gameScoreRepository.save(gameScore);
	}
}
