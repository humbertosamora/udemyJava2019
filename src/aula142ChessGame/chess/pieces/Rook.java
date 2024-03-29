package aula142ChessGame.chess.pieces;

import aula142ChessGame.boardgame.Board;
import aula142ChessGame.boardgame.Position;
import aula142ChessGame.chess.ChessPiece;
import aula142ChessGame.chess.Color;

public class Rook extends ChessPiece {

	public Rook(Board board, Color color) {
		super(board, color);
	}
	
	@Override
	public String toString () {
		return "R";
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean [][] mat = new boolean[board.getRows()][board.getColumns()];
		
		// Rook moves along lines or columns
		int[][] moves = {	{ -1,  0 },		// above
							{  0, -1 },		// left
							{ +1,  0 },		// below
							{  0, +1 } };	// right
		
		Position p = new Position(0,0);
		
		for (int i = 0; i < moves.length; i++) {
			
			p.setValues(position.getRow() + moves[i][0], position.getColumn() + moves[i][1]);
			
			while(board.positionExists(p) && !board.thereIsAPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
				p.setValues(p.getRow() + moves[i][0], p.getColumn() + moves[i][1]);
			}
			
			if (board.positionExists(p) && isThereOponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			
		}
		
		return mat;
	}
}
