package aula142ChessGame.chess;

import aula142ChessGame.boardgame.Board;
import aula142ChessGame.boardgame.Piece;
import aula142ChessGame.boardgame.Position;

public abstract class ChessPiece extends Piece {
	
	private Color color;
	
	public ChessPiece(Board board, Color color) {
		super(board);
		this.color = color;
	}
	
	public Color getColor() {
		return color;
	}
	
	protected boolean isThereOponentPiece(Position position) {
		ChessPiece piece = (ChessPiece) board.piece(position);
		
		return piece!=null && piece.getColor()!=color;
	}
}
