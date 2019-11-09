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
		
		Position p = new Position(0,0);
		
		// above
		p.setValues(position.getRow() - 1, position.getColumn());
		while(board.positionExists(p) && !board.thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setRow(p.getRow() - 1);
		}
		if (board.positionExists(p) && isThereOponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// below
		p.setValues(position.getRow() + 1, position.getColumn());
		while(board.positionExists(p) && !board.thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setRow(p.getRow() + 1);
		}
		if (board.positionExists(p) && isThereOponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// left
		p.setValues(position.getRow() , position.getColumn() - 1 );
		while(board.positionExists(p) && !board.thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setColumn(p.getColumn() - 1);
		}
		if (board.positionExists(p) && isThereOponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// right
		p.setValues(position.getRow() , position.getColumn() + 1 );
		while(board.positionExists(p) && !board.thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setColumn(p.getColumn() + 1);
		}
		if (board.positionExists(p) && isThereOponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		return mat;
	}
}
