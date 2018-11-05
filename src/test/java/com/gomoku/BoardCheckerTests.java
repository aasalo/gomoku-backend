package com.gomoku;

import static org.junit.Assert.assertTrue;

import org.junit.*;

import com.gomoku.game.BoardChecker;

public class BoardCheckerTests {

	private static final int BOARD_SIDE_LENGTH = 15;
	
	BoardChecker bcTest = new BoardChecker();
	
	@Test
	public void testCheckColumnFindsPattern() {
		
		int[][] board = new int[BOARD_SIDE_LENGTH][BOARD_SIDE_LENGTH];
		
		board[6][11] = 1;
		board[7][11] = 1;
		board[8][11] = 1;
		board[9][11] = 1;
		board[10][11] = 1;

		
		assertTrue(bcTest.checkColumn(board, 11));
	}
	
	@Test
	public void testCheckRowFindsPattern() {
		
		int[][] board = new int[BOARD_SIDE_LENGTH][BOARD_SIDE_LENGTH];
		
		board[0][5] = 1;
		board[0][6] = 1;
		board[0][7] = 1;
		board[0][8] = 1;
		board[0][9] = 1;

		assertTrue(bcTest.checkRow(board, 0));
	}
	
	@Test
	public void testCheckDiagonalsFindsDiagonalRightPattern() {
		
		int[][] board = new int[BOARD_SIDE_LENGTH][BOARD_SIDE_LENGTH];
		
		board[1][4] = 1;
		board[2][5] = 1;
		board[3][6] = 1;
		board[4][7] = 1;
		board[5][8] = 1;
			
		assertTrue(bcTest.checkDiagonalRight(board, 4,7));
		
	}
	
	@Test
	public void testCheckDiagonalsFindsDiagonalLeftPattern() {
		
		int[][] board = new int[BOARD_SIDE_LENGTH][BOARD_SIDE_LENGTH];
		
		board[1][5] = 1;
		board[2][4] = 1;
		board[3][3] = 1;
		board[4][2] = 1;
		board[5][1] = 1;
	
		assertTrue(bcTest.checkDiagonalLeft(board, 3, 3));
		
	}

}