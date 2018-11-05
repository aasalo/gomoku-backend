package com.gomoku.game;

public class Move {

	Player player;
	int column;
	int row;
	
	public Move(Player player, int column, int row) {
		this.column = column;
		this.row = row;
		this.player = player;
	}

	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public int getColumn() {
		return column;
	}
	public void setColumn(int column) {
		this.column = column;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	
}
