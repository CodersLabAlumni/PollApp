package com.pollapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@GetMapping("")
	public List<GameScore> getAllGameScore() {
		return gameScoreRepository.findTop50ByOrderByScoreDesc();
	}
	
	@PostMapping("")
	public GameScore createGameScore (@RequestBody GameScore gameScore) {
		return gameScoreRepository.save(gameScore);
	}
}
