package aula142ChessGame.chess.pieces;

import aula142ChessGame.boardgame.Board;
import aula142ChessGame.boardgame.Position;
import aula142ChessGame.chess.ChessPiece;
import aula142ChessGame.chess.Color;

public class Knight extends ChessPiece {
	
	public Knight(Board board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		return "N";
	}

	@Override
	public boolean[][] possibleMoves() {
		
		boolean[][] mat = new boolean[board.getRows()][board.getColumns()];

		// Knight possible moves are "L" moves
		int[][] moves = {	{ -2, -1 },
							{ -1, -2 },
							{ +1, -2 },
							{ +2, -1 },
							{ +2, +1 },
							{ +1, +2 },
							{ -1, +2 },
							{ -2, +1 } };
		
		Position p = new Position(0, 0);
		
		// King normal moves
		for (int i = 0; i < moves.length; i++) {
			
			p.setValues(position.getRow() + moves[i][0], position.getColumn() + moves[i][1]);
			
			if (board.positionExists(p) && (!board.thereIsAPiece(p) || isThereOponentPiece(p)) ) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			
		}
		
		return mat;
		
	}
	
}
