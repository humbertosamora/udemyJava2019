package aula142ChessGame.chess.pieces;

import aula142ChessGame.boardgame.Board;
import aula142ChessGame.chess.ChessPiece;
import aula142ChessGame.chess.Color;

public class King extends ChessPiece {

	public King(Board board, Color color) {
		super(board, color);
	}
	
	@Override
	public String toString () {
		return "K";
	}

	@Override
	public boolean[][] possibleMoves() {
		// TODO Auto-generated method stub
		boolean [][] mat = new boolean[board.getRows()][board.getColumns()];
		return mat;
	}
}
