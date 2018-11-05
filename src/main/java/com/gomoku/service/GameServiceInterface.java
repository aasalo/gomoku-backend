package com.gomoku.service;

import com.gomoku.game.Gomoku;
import com.gomoku.game.Move;

public interface GameServiceInterface {

	Long create();
	Gomoku getGame(Long id);
	Gomoku updateGame(Long id, Move move);
	
}
