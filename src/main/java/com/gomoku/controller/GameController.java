package com.gomoku.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gomoku.game.Gomoku;
import com.gomoku.game.Move;
import com.gomoku.service.GameService;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/game")
public class GameController {
	
	private static final Logger logger = LoggerFactory.getLogger(GameController.class);
	
	@Autowired
	private GameService gameService;

	@PostMapping
	@ResponseBody Long newGame() {
		
		logger.info("Creating new game.. ");
		
		return gameService.create();
	}
	
	@GetMapping(value = "/{id}")
	@ResponseBody Gomoku getGame(@PathVariable Long id) {

		return gameService.getGame(id);
	}
	
	@PostMapping(value = "/update/{id}")
	Gomoku updateGame(@PathVariable Long id, @RequestBody Move move) {
	
		logger.info("Updating game " + id);
		
		return gameService.updateGame(id, move);
		
	}
	

}
