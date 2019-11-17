package aula142ChessGame.chess.pieces;

import aula142ChessGame.boardgame.Board;
import aula142ChessGame.boardgame.Position;
import aula142ChessGame.chess.ChessMatch;
import aula142ChessGame.chess.ChessPiece;
import aula142ChessGame.chess.Color;

public class King extends ChessPiece {
	
	private ChessMatch chessMatch;
	
	public King(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
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
		boolean [][] mat = new boolean[board.getRows()][board.getColumns()];
		
		// King can move one square in any direction
		int[][] moves = {	{ -1,  0 },		// above
							{  0, -1 },		// left
							{ +1,  0 },		// below
							{  0, +1 },		// right
							{ -1, -1 },		// north west
							{ +1, -1 },		// south west
							{ +1, +1 },		// south east
							{ -1, +1 } };	// north east
		
		Position p = new Position(0,0);
		
		for (int i = 0; i < moves.length; i++) {
			
			p.setValues(position.getRow() + moves[i][0], position.getColumn() + moves[i][1]);
			
			if (board.positionExists(p) && canMove(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			
		}
		
		// Special move castling
		if (getMoveCount()==0 && !chessMatch.getCheck()) {
			// king side rook
			Position kingRookPosition = new Position(position.getRow(), position.getColumn() + 3);
			if (testRookCastling(kingRookPosition) && testCastling(kingRookPosition)) {
				mat[position.getRow()][position.getColumn() + 2] = true;
			}
			
			// Queen side rook
			Position queenRookPosition = new Position(position.getRow(), position.getColumn() - 4);
			if (testRookCastling(queenRookPosition) && testCastling(queenRookPosition)) {
				mat[position.getRow()][position.getColumn() - 2] = true;
			}
		}
		
		return mat;
	}
	
	private boolean testRookCastling(Position rookPosition) {
		ChessPiece piece = (ChessPiece) board.piece(rookPosition);
		
		return piece!=null && piece instanceof Rook && piece.getMoveCount()==0 && piece.getColor()==this.getColor();		
	}
	
	private boolean testCastling(Position rookPosition) {
		
		// increment = +1 for King side castling (right)
		// increment = -1 for Queen side castling (left)
		int increment = (rookPosition.getColumn() > position.getColumn() ? +1 : -1); 
		
		// The two squares passed by King in castling must be empty 
		if ( board.piece(position.getRow(), position.getColumn() + increment) != null ||
			 board.piece(position.getRow(), position.getColumn() + 2*increment) != null ) {
			return false;
		}
		
		// The third square must also be empty in Queen side castling
		if (increment == -1 && board.piece(position.getRow(), position.getColumn() + 3*increment) != null) {
			return false;
		}
		
		Color opponetColor = (getColor() == Color.WHITE ? Color.BLACK : Color.WHITE);
		
		ChessPiece [][] pieces = chessMatch.getPieces();
		
		// The squares passed by King in castling must not be under attack of any opponent piece
		for (int i=0; i < pieces.length; i++) {
			for (int j=0; j < pieces[i].length; j++) {
				
				if (pieces[i][j]!=null && pieces[i][j].getColor()==opponetColor) {
					
					// Prevent recursive method call caused by king's possibleMoves():
					// thisKing.testCastling() -> opponentking.possibleMoves() -> opponentKing.testCastling() -> thisKing.possibleMoves() -> thisKing.testCastling() -> opponentking.possibleMoves() -> ... -> Stack overflow!!!
					if(pieces[i][j] instanceof King) {
						if (Math.abs(pieces[i][j].getChessPosition().getRow() - this.getChessPosition().getRow()) == 1 ) {
							// If the opponent King is on any possible square of next line, it prevents current player King castling.
							return false;
						}
						else {
							// If there are more lines between the two Kings, one does not prevent the other to castle.
							// So check the next opponent piece.
							continue;
						}
					}
					
					boolean [][] mat = pieces[i][j].possibleMoves();
					
					if ( mat[position.getRow()][position.getColumn() + increment] ||
						mat[position.getRow()][position.getColumn() + 2*increment] ) {
						// If it is Queen side castling, it doesn't matter the third square is / or is not
						// under attack of some opponent piece because the King will not pass by that square.
						
						return false;
					}
				}
			}
		}
		
		return true;
	}
	
}
