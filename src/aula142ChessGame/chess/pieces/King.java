package aula142ChessGame.chess.pieces;

import aula142ChessGame.boardgame.Board;
import aula142ChessGame.boardgame.Position;
import aula142ChessGame.chess.ChessPiece;
import aula142ChessGame.chess.Color;

public class King extends ChessPiece {

	public King(Board board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		return "K";
	}

	private boolean canMove(Position position) {
		ChessPiece piece = (ChessPiece) board.piece(position);

		return piece == null || piece.getColor() != getColor();
	}

	@Override
	public boolean[][] possibleMoves() {

		boolean[][] mat = new boolean[board.getRows()][board.getColumns()];
		
		Position p = new Position(0,0);
		
		// above
		p.setValues(position.getRow() - 1, position.getColumn());
		if (board.positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// below
		p.setValues(position.getRow() + 1, position.getColumn());
		if (board.positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// left
		p.setValues(position.getRow(), position.getColumn() - 1);
		if (board.positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// right
		p.setValues(position.getRow(), position.getColumn() + 1);
		if (board.positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// north-west
		p.setValues(position.getRow() - 1, position.getColumn() - 1);
		if (board.positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// south-west
		p.setValues(position.getRow() + 1, position.getColumn() - 1);
		if (board.positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// south-east
		p.setValues(position.getRow() + 1, position.getColumn() + 1);
		if (board.positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// north-east
		p.setValues(position.getRow() - 1, position.getColumn() + 1);
		if (board.positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		return mat;
	}
}
