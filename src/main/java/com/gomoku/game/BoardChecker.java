package com.gomoku.game;

import java.util.ArrayList;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BoardChecker {
	
	private static final int BOARD_SIDE_LENGTH = 15;
	
	public BoardChecker() {	}
	
	public boolean checkRow(int[][] board, int row) {
		
		ArrayList<Integer> lookup = new ArrayList<Integer>();
		int i;
		
		// Check column
		for(i = 0; i < BOARD_SIDE_LENGTH; i++) {
			lookup.add(board[row][i]);
		}
		
		return this.checkLookup(listToString(lookup));
	}
	
	public boolean checkColumn(int[][] board, int column) {
		
		ArrayList<Integer> lookup = new ArrayList<Integer>();
		int i;
		
		// Check row
		for(i = 0; i < BOARD_SIDE_LENGTH; i++) {
			lookup.add(board[i][column]);
		}
		
		return this.checkLookup(listToString(lookup));
	}
	
	public boolean checkDiagonalRight(int[][] board, int row, int column) {
				
		boolean matched = false;
		ArrayList<Integer> lookup = new ArrayList<Integer>();
		int i;
		int j = column;
		
		// Add cell values to a list
		for(i = row; i < BOARD_SIDE_LENGTH; i++) {
			// Don't allow going out of the board, the size of lookup will vary
			if (i == BOARD_SIDE_LENGTH || j == BOARD_SIDE_LENGTH) {
				break;
			}
			
			lookup.add(board[i][j]);
			j++;

		}
		
		// Now add everything from the opposite direction
		// Clicked cell was already added, so subtract 1
		j = column - 1;
		for(i = row - 1; i >= 0; i--) {
			// Don't allow going out of the board, the size of lookup will vary
			if (i < 0 || j < 0) {
				break;
			}
			
			lookup.add(0, board[i][j]);
			
			j--;

		}
		
		if (checkLookup(listToString(lookup))) {
			matched = true;
		}
		
		return matched;
	}
	
	public boolean checkDiagonalLeft(int[][] board, int row, int column) {
		
		boolean matched = false;
		ArrayList<Integer> lookup = new ArrayList<Integer>();
		int i;
		int j = column;
		
		// Ascend through the board first
		for(i = row; i >= 0; i--) {
			
			// Don't allow going out of the board
			if (i < 0 || j >= BOARD_SIDE_LENGTH) {
				break;
			}
			
			lookup.add(board[i][j]);
			
			j++;
			
		}
		
		j = column - 1;
		// Add everything from the opposite direction
		// Starting cell was already added so subtract 1
		for(i = row + 1; i < BOARD_SIDE_LENGTH; i++) {
			// Don't allow going out of the board, the size of lookup will vary
			if (i > BOARD_SIDE_LENGTH || j < 0) {
				break;
			}

			lookup.add(0, board[i][j]);
			
			j--;

		}
		
		if (checkLookup(listToString(lookup))) {
			matched = true;
		}
		
		return matched;
	}
	
	private Boolean checkLookup(String lookup) {
		/* 
		 * Receives a string in a format of [{0-2}...{0-2}]
		 * and checks if it contains a win condition
		 */
		
		final String WIN_CONDITION_WHITE = "11111";
		final String WIN_CONDITION_BLACK = "22222";
		
		System.out.println("Lookup string: " + lookup);
		
		if (lookup.contains(WIN_CONDITION_WHITE) || lookup.contains(WIN_CONDITION_BLACK)) {
			return true;
		}else{
			return false;
		}
		
	}
	
	private String listToString(ArrayList<Integer> lookup) {
		return Arrays.toString(lookup.toArray()).replace(", ", "");
	}
		
}
