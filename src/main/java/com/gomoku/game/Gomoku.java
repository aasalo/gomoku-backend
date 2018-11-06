package com.gomoku.game;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gomoku.exception.CellNotEmptyException;
import com.gomoku.exception.NotPlayersTurnException;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Gomoku {

	private static final int BOARD_SIDE_SIZE = 15;
	private static final int BOARD_CELL_EMPTY = 0;
	private static final int BOARD_CELL_BLACK = 1;
	private static final int BOARD_CELL_WHITE = 2;
	private static final int MAX_ROUNDS = BOARD_SIDE_SIZE * BOARD_SIDE_SIZE;
	
	@Id
	@GeneratedValue
	private Long id;

	// Format: [ROW] [COLUMN]
	@Column(columnDefinition = "VARCHAR(4000)")
	int[][] board;
	
	// Status properties
	int round;
	Status status;
	Player whoseTurn;
	Player winner;

	String error;

	public Gomoku() {
		this.status = Status.ONGOING;
		this.whoseTurn = Player.BLACK;
		this.board = new int[BOARD_SIDE_SIZE][BOARD_SIDE_SIZE];
		this.round = 1;
	}
	
	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Player getWhoseTurn() {
		return whoseTurn;
	}

	public void setWhoseTurn(Player whoseTurn) {
		this.whoseTurn = whoseTurn;
	}

	public Player getWinner() {
		return winner;
	}

	public void setWinner(Player winner) {
		this.winner = winner;
	}	
	
	@JsonIgnore
	public void setBoard(int[][] board) {
		this.board = board;
	}

	public int[][] getBoard() {
		return board;
	}

	public Gomoku processTurn(Move move) {
		
		int column, row;
		
		row = move.getRow();
		column = move.getColumn();

		try {
			// Validate turn
			if (move.getPlayer() != whoseTurn) {
				throw new NotPlayersTurnException(move.getPlayer());
			}
			
			// Validate cell
			if (board[row][column] != BOARD_CELL_EMPTY) {				
				throw new CellNotEmptyException();
			}
			
			// All clear
			board[row][column] = cellTypeByPlayer(whoseTurn);
			
			//Check winner
			if (checkWinner(row, column)) {
				setWinner(whoseTurn);
				setStatus(Status.FINISHED);
			} else {
				nextTurn();
			}
			
			// Validate round
			if (round == MAX_ROUNDS) {
				setStatus(Status.FINISHED);
				setWinner(Player.DRAW);
			}
			
		}catch(Exception e) {			
			setError(e.getMessage());
		}
		
		return this;
		
	}
	
	private int cellTypeByPlayer(Player whoseTurn) {
		
		if (whoseTurn == Player.BLACK) {
			return BOARD_CELL_BLACK;
		}
		
		else {
			return BOARD_CELL_WHITE;
		}
		
	}
	
	private void nextTurn() {
		
		if (whoseTurn == Player.BLACK) {
			setWhoseTurn(Player.WHITE);
		} else {
			setWhoseTurn(Player.BLACK);
		}
		
		this.round++;
		
	}
	
	private boolean checkWinner(int row, int column) {
		
		BoardChecker checker = new BoardChecker();
		boolean win = false;
		
		if (checker.checkRow(board, row) || checker.checkColumn(board, column) || checker.checkDiagonalRight(board, row, column) || checker.checkDiagonalLeft(board, row, column)) {
			win = true;
		}
		
		
		return win;

	}

}
