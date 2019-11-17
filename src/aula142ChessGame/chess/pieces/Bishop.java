package aula142ChessGame.chess.pieces;

import aula142ChessGame.boardgame.Board;
import aula142ChessGame.boardgame.Position;
import aula142ChessGame.chess.ChessPiece;
import aula142ChessGame.chess.Color;

public class Bishop extends ChessPiece {

	public Bishop(Board board, Color color) {
		super(board, color);
	}
	
	@Override
	public String toString() {
		return "B";
	}
	
	@Override
	public boolean[][] possibleMoves() {
		
		boolean [][] mat = new boolean[board.getRows()][board.getColumns()];
			
		// Bishop moves along diagonals
		int[][] moves = {	{ -1, -1 },		// diagonal north west
							{ +1, -1 },		// diagonal south west
							{ +1, +1 },		// diagonal south east
							{ -1, +1 } };	// diagonal north east					
		
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
