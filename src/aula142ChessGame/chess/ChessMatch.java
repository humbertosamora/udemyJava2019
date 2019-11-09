package aula142ChessGame.chess;

import aula142ChessGame.boardgame.Board;
import aula142ChessGame.boardgame.Piece;
import aula142ChessGame.boardgame.Position;
import aula142ChessGame.chess.pieces.King;
import aula142ChessGame.chess.pieces.Rook;

public class ChessMatch {
	
	private Board board;
	
	public ChessMatch() {
		board = new Board(8, 8);
		initialSetup();
	}
	
	public ChessPiece[][] getPieces() {
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[i].length; j++) {
				mat[i][j] = (ChessPiece) board.piece(i, j);
			}
		}
		
		return mat;
	}
	
	public boolean [][] possibleMoves(ChessPosition source){
		validateSourcePosition(source.toPosition());
		return board.piece(source.toPosition()).possibleMoves();
	}
	
	public ChessPiece performChessMove(ChessPosition sourceposition, ChessPosition targetposition) {
		Position source = sourceposition.toPosition();
		Position target = targetposition.toPosition();
		
		Piece capturedPiece = makeMove(source, target);
		
		return (ChessPiece) capturedPiece;
	}
	
	private void validateSourcePosition(Position position) {
		if (!board.thereIsAPiece(position)) {
			throw new ChessException("There is no piece on source position.");
		}
		
		if(!board.piece(position).isThereAnyPossibleMove()) {
			throw new ChessException("There is no possible moves for the chosen piece.");
		}
	}
	
	public void validateSourcePosition(ChessPosition position) {
		validateSourcePosition(position.toPosition());
	}
	
	private void validateTargetPosition(Position source, Position target) {
		if (!board.piece(source).possibleMove(target)) {
			throw new ChessException("The chosen piece can't move to target position.");
		}
	}
	
	public void validateTargetPosition(ChessPosition source, ChessPosition target) {
		validateTargetPosition(source.toPosition(), target.toPosition());
	}
	
	private Piece makeMove(Position source, Position target) {
		Piece movedPiece = board.removePiece(source);
		Piece capturedPiece = board.removePiece(target);
		
		board.placePiece(movedPiece, target);		
		return capturedPiece;
	}
	
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
	}
	
	private void initialSetup() {
		placeNewPiece('c', 2, new Rook(board, Color.WHITE));
		placeNewPiece('d', 2, new Rook(board, Color.WHITE));
		placeNewPiece('e', 2, new Rook(board, Color.WHITE));
		placeNewPiece('e', 1, new Rook(board, Color.WHITE));
		placeNewPiece('d', 1, new King(board, Color.WHITE));
		
		placeNewPiece('c', 7, new Rook(board, Color.BLACK));
		placeNewPiece('c', 8, new Rook(board, Color.BLACK));
		placeNewPiece('d', 7, new Rook(board, Color.BLACK));
		placeNewPiece('e', 7, new Rook(board, Color.BLACK));
		placeNewPiece('e', 8, new Rook(board, Color.BLACK));
		placeNewPiece('d', 8, new King(board, Color.BLACK));
	}
	
}
