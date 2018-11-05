package com.gomoku.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gomoku.db.GameRepository;
import com.gomoku.game.Gomoku;
import com.gomoku.game.Move;

@Service
public class GameService implements GameServiceInterface {

	private static final Logger logger = LoggerFactory.getLogger(GameService.class);
	@Autowired
	GameRepository gameRepository;

	@Override
	public Long create() {
		
		Gomoku gomoku = new Gomoku();
		
		gameRepository.save(gomoku);
		
		return gomoku.getId();
	}

	@Override
	public Gomoku getGame(Long id) {
		
		return gameRepository.getOne(id);
	}

	@Override
	public Gomoku updateGame(Long id, Move move) {
		
		Gomoku gomoku = gameRepository.getOne(id);
		
		gomoku.processTurn(move);
		
		if (gomoku.getError() == null) {			
			gameRepository.save(gomoku);
		}else {
			logger.info("Error detected when updating game status! " + gomoku.getError() + " Will not save.");
		}
		
		return gomoku;
	}

}
