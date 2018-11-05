package com.gomoku.exception;

public class CellNotEmptyException extends Exception{
	
	public CellNotEmptyException() {
		super("Cell is not empty");
	}

}
