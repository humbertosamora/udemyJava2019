package aula142ChessGame.chess.pieces;

import aula142ChessGame.boardgame.Board;
import aula142ChessGame.boardgame.Position;
import aula142ChessGame.chess.ChessMatch;
import aula142ChessGame.chess.ChessPiece;
import aula142ChessGame.chess.Color;

public class Pawn extends ChessPiece {
	
	private ChessMatch chessMatch;
	
	public Pawn(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
	}
	
	@Override
	public String toString() {
		return "P";
	}
	
	@Override
	public boolean[][] possibleMoves() {

		boolean[][] mat = new boolean[board.getRows()][board.getColumns()];
		
		Position p = new Position(0,0);
		int move = (getColor() == Color.WHITE ? -1 : + 1);
		
		// Check the square in front of pawn
		p.setValues(position.getRow() + move, position.getColumn());
		if (board.positionExists(p) && !board.thereIsAPiece(p) ) {
			mat[p.getRow()][p.getColumn()] = true;
			
			// Check the second square if it is the first move
			// The pawn can only move two squares if it can move one
			p.setValues(position.getRow() + 2*move, position.getColumn());
			if (board.positionExists(p) && !board.thereIsAPiece(p) && getMoveCount()==0) {
				mat[p.getRow()][p.getColumn()] = true;
			}
		}
		
		// Check capture left opponent piece
		p.setValues(position.getRow() + move, position.getColumn() - 1);
		if (board.positionExists(p) && isThereOponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// Check capture right opponent piece
		p.setValues(position.getRow() + move, position.getColumn() + 1);
		if (board.positionExists(p) && isThereOponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// Check capture left opponent enpassant pawn
		p.setValues(position.getRow(), position.getColumn() - 1);
		if (testEnPassantPawn(p)) {
			mat[position.getRow() + move][position.getColumn() - 1] = true;
		}
		
		// Check capture right opponent enpassant pawn
		p.setValues(position.getRow(), position.getColumn() + 1);
		if (testEnPassantPawn(p)) {
			mat[position.getRow() + move][position.getColumn() + 1] = true;
		}
		
		return mat;
	}
	
	private boolean testEnPassantPawn(Position enPassantPosition) {
		
		ChessPiece enPassantPawn = chessMatch.getEnPassantVulnerable();
		
		if ( enPassantPawn != null && board.positionExists(enPassantPosition) &&
			 board.thereIsAPiece(enPassantPosition) && enPassantPawn == board.piece(enPassantPosition) ) {
			
			return true;
		}
		
		return false;
	}
	
}
