package aula142ChessGame.chess;

import aula142ChessGame.boardgame.Board;
import aula142ChessGame.boardgame.Piece;
import aula142ChessGame.boardgame.Position;

public abstract class ChessPiece extends Piece {
	
	private Color color;
	private int moveCount;
	
	public ChessPiece(Board board, Color color) {
		super(board);
		this.color = color;
	}
	
	public Color getColor() {
		return color;
	}
	
	public int getMoveCount() {
		return moveCount;
	}
	
	public void increaseMoveCount() {
		moveCount++;
	}
	
	public void decreaseMoveCount() {
		moveCount--;
	}
	
	public ChessPosition getChessPosition() {
		return ChessPosition.fromPosition(position);
	}
	
	protected boolean isThereOponentPiece(Position position) {
		ChessPiece piece = (ChessPiece) board.piece(position);
		
		return piece!=null && piece.getColor()!=color;
	}
	
}
