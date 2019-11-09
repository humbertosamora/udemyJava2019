package aula142ChessGame.boardgame;

public abstract class Piece {
	
	protected Position position;
	protected Board board;
	
	public Piece(Board board) {
		this.board = board;
	}

	public Board getBoard() {
		return board;
	}
	
	public abstract boolean [][] possibleMoves();
	
	public boolean possibleMove(Position position) {
		return possibleMoves()[position.getRow()][position.getColumn()];
	}
	
	public boolean isThereAnyPossibleMove() {
		boolean [][] mat = possibleMoves();
		
		for (boolean moves[] : mat) {
			for (boolean move : moves) {
				if (move == true) {
					return true;
				}
			}
		}
		
		return false;
	}
}
