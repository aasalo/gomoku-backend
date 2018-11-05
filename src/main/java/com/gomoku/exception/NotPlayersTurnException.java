package com.gomoku.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import com.gomoku.game.Player;

@ResponseStatus
public class NotPlayersTurnException extends RuntimeException {
		
	public NotPlayersTurnException(Player player){
			super("It is not " + player + "'s turn");
		}

}
