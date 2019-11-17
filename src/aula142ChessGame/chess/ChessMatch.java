package aula142ChessGame.chess;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import aula142ChessGame.boardgame.Board;
import aula142ChessGame.boardgame.Piece;
import aula142ChessGame.boardgame.Position;
import aula142ChessGame.chess.pieces.Bishop;
import aula142ChessGame.chess.pieces.King;
import aula142ChessGame.chess.pieces.Knight;
import aula142ChessGame.chess.pieces.Pawn;
import aula142ChessGame.chess.pieces.Queen;
import aula142ChessGame.chess.pieces.Rook;

public class ChessMatch {

	private Board board;
	private int turn;
	private Color currentPlayer;
	private boolean check;
	private boolean checkMate;
	private ChessPiece enPassantVulnerable;
	private ChessPiece promotedPawn;
	
	private List<ChessPiece> capturedPieces = new ArrayList<ChessPiece>();
	private List<ChessPiece> piecesOnTheBoard = new ArrayList<ChessPiece>();
	
	public ChessMatch() {
		board = new Board(8, 8);
		turn = 1;
		currentPlayer = Color.WHITE;
		initialSetup();
	}

	public int getTurn() {
		return turn;
	}

	public Color getCurrentPlayer() {
		return currentPlayer;
	}

	public boolean getCheck() {
		return check;
	}

	public boolean getCheckMate() {
		return checkMate;
	}

	public List<ChessPiece> getCapturedPieces() {
		return capturedPieces;
	}
	
	public ChessPiece getEnPassantVulnerable() {
		return enPassantVulnerable;
	}

	public ChessPiece getPromotedPawn() {
		return promotedPawn;
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

	public boolean[][] possibleMoves(ChessPosition source) {
		validateSourcePosition(source);
		return board.piece(source.toPosition()).possibleMoves();
	}
	
	public void performChessMove(ChessPosition sourceposition, ChessPosition targetposition, Supplier <Character> readPromotionType) {
		Position source = sourceposition.toPosition();
		Position target = targetposition.toPosition();

		Piece capturedPiece = makeMove(source, target);
		
		// Undo move if it puts the current player in check
		if (testCheck(currentPlayer)) {
			undoMove(source, target, capturedPiece);
			throw new ChessException("You can't put yourself in check.");
		}
		
		ChessPiece movedPiece = (ChessPiece) board.piece(target);
		
		// Check promotion here because promoted piece can put opponent in check
		if ( movedPiece instanceof Pawn &&
			( movedPiece.getColor()==Color.WHITE && target.getRow() == 0 ||
			  movedPiece.getColor()==Color.BLACK && target.getRow() == 7 ) ) {
			
			// Use the supplier function to read the promotion type
			Character promotionType = readPromotionType.get();
			
			// Enclose with try statement to undoMove if something goes wrong
			try {
				replacePromotedPiece(target, promotionType);
			}
			catch (Exception e) {
				undoMove(source, target, capturedPiece);
				throw new ChessException(e.getMessage());
			}
		}
		
		// Test check condition of opponent
		check = testCheck(opponent(currentPlayer));
		
		// Test check mate
		if (testCheckMate(opponent(currentPlayer))) {
			checkMate = true;
		} else {
			nextTurn();
		}
		
		// When Pawn move two squares it becomes En Passant vulnerable		
		if (movedPiece instanceof Pawn && Math.abs(target.getRow() - source.getRow()) == 2) {
			enPassantVulnerable = movedPiece;
		}
		else {
			enPassantVulnerable = null;
		}
		
	}
	
	private Piece makeMove(Position source, Position target) {
		Piece movedPiece = (ChessPiece) board.removePiece(source);
		Piece capturedPiece = (ChessPiece) board.removePiece(target);
		
		board.placePiece(movedPiece, target);
		
		if (capturedPiece != null) {
			piecesOnTheBoard.remove(capturedPiece);
			capturedPieces.add((ChessPiece) capturedPiece);
		}
		
		((ChessPiece) movedPiece).increaseMoveCount();
		
		// Move Rook in special move King side castling
		if (movedPiece instanceof King && target.getColumn() == source.getColumn() + 2) {
			Position rookSource = new Position(source.getRow(), source.getColumn() + 3);
			Position rookTarget = new Position(source.getRow(), source.getColumn() + 1);
			
			Piece rook = board.removePiece(rookSource);
			board.placePiece(rook, rookTarget);
			((ChessPiece) rook).increaseMoveCount();
		}
		
		// Move Took in special move Queen side castling
		if (movedPiece instanceof King && target.getColumn() == source.getColumn() - 2) {
			Position rookSource = new Position(source.getRow(), source.getColumn() - 4);
			Position rookTarget = new Position(source.getRow(), source.getColumn() - 1);
			
			Piece rook = board.removePiece(rookSource);
			board.placePiece(rook, rookTarget);
			((ChessPiece) rook).increaseMoveCount();
		}
		
		// Remove opponent Pawn in special move En Passant.
		if (movedPiece instanceof Pawn && capturedPiece == null && target.getColumn() != source.getColumn()) {
			
			Position pawnPosition = new Position(0, 0);
			
			if (((Pawn) movedPiece).getColor() == Color.WHITE) {
				pawnPosition.setValues(target.getRow() + 1, target.getColumn());
			}
			else {
				pawnPosition.setValues(target.getRow() - 1, target.getColumn());
			}
			
			capturedPiece = board.removePiece(pawnPosition);
			piecesOnTheBoard.remove(capturedPiece);
			capturedPieces.add((ChessPiece) capturedPiece);
		}
		
		return capturedPiece;
	}
	
	private void undoMove(Position source, Position target, Piece capturedPiece) {
		Piece movedPiece = board.removePiece(target);
		board.placePiece(movedPiece, source);
		
		if (capturedPiece != null) {
			board.placePiece(capturedPiece, target);
			capturedPieces.remove(capturedPiece);
			piecesOnTheBoard.add((ChessPiece) capturedPiece);
		}
		
		((ChessPiece) movedPiece).decreaseMoveCount();
		
		// Move Rook in special move King side castling
		if (movedPiece instanceof King && target.getColumn() == source.getColumn() + 2) {
			Position rookSource = new Position(source.getRow(), source.getColumn() + 3);
			Position rookTarget = new Position(source.getRow(), source.getColumn() + 1);
			
			Piece rook = board.removePiece(rookTarget);
			board.placePiece(rook, rookSource);
			((ChessPiece) rook).decreaseMoveCount();
		}
		
		// Move Rook in special move Queen side castling
		if (movedPiece instanceof King && target.getColumn() == source.getColumn() - 2) {
			Position rookSource = new Position(source.getRow(), source.getColumn() - 4);
			Position rookTarget = new Position(source.getRow(), source.getColumn() - 1);
			
			Piece rook = board.removePiece(rookTarget);
			board.placePiece(rook, rookSource);
			((ChessPiece) rook).decreaseMoveCount();
		}
		
		// Undo remove opponent Pawn in special move En Passant.
		if (movedPiece instanceof Pawn &&
			capturedPiece != null &&
			capturedPiece == enPassantVulnerable &&
			target.getColumn() != source.getColumn() ) {
			
			Position pawnPosition = new Position(0, 0);
			
			if (((Pawn) movedPiece).getColor() == Color.WHITE) {
				pawnPosition.setValues(target.getRow() + 1, target.getColumn());
			}
			else {
				pawnPosition.setValues(target.getRow() - 1, target.getColumn());
			}
			
			ChessPiece pawn = (ChessPiece) board.removePiece(target);
			board.placePiece(pawn, pawnPosition);
		}
		
	}
		
	public void replacePromotedPiece(Position target, char pieceType) {
		
		pieceType = Character.toUpperCase(pieceType);
		ChessPiece newPiece;
		
		switch (pieceType) {
		case 'B':
			newPiece = new Bishop(board, currentPlayer);
			break;
		
		case 'N':
			newPiece = new Knight(board, currentPlayer);
			break;
		
		case 'Q':
			newPiece = new Queen(board, currentPlayer);
			break;
		
		case 'R':
			newPiece = new Rook(board, currentPlayer);
			break;
		
		default:
			throw new InvalidParameterException("Invalid type for promotion.");
		}
		
		Piece pawn = board.removePiece(target);
		piecesOnTheBoard.remove(pawn);
		board.placePiece(newPiece, target);
		piecesOnTheBoard.add(newPiece);
		
	}
	
	public void validateSourcePosition(ChessPosition chessPosition) {
		Position position = chessPosition.toPosition();

		if (!board.thereIsAPiece(position)) {
			throw new ChessException("There is no piece on source position.");
		}

		ChessPiece piece = (ChessPiece) board.piece(position);
		if (currentPlayer != piece.getColor()) {
			throw new ChessException("The chosen piece is nor yours.");
		}

		if (!board.piece(position).isThereAnyPossibleMove()) {
			throw new ChessException("There is no possible moves for the chosen piece.");
		}
	}

	public void validateTargetPosition(ChessPosition source, ChessPosition target) {

		Piece piece = board.piece(source.toPosition());

		if (!piece.possibleMove(target.toPosition())) {
			throw new ChessException("The chosen piece can't move to target position.");
		}
	}

	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
		piecesOnTheBoard.add(piece);
	}

	private void nextTurn() {
		turn++;
		currentPlayer = (currentPlayer == Color.WHITE ? Color.BLACK : Color.WHITE);
	}

	private Color opponent(Color color) {
		return (color == Color.WHITE ? Color.BLACK : Color.WHITE);
	}

	private ChessPiece king(Color color) {
		ChessPiece king = piecesOnTheBoard.stream().filter(p -> (p.getColor() == color && p instanceof King))
				.findFirst().get();

		if (king == null) {
			throw new IllegalStateException("There is no " + color + " king on the board.");
		}

		return king;
	}

	private boolean testCheck(Color color) {
		Position kingPosition = king(color).getChessPosition().toPosition();
		List<ChessPiece> opponentPieces =	piecesOnTheBoard.
											stream().
											filter(p -> p.getColor() == opponent(color))
											.collect(Collectors.toList());
		
		for (ChessPiece p : opponentPieces) {
			
			// A King can't put his opponent in check mate
			if (p instanceof King == true) {
				continue;
			}
			
			boolean[][] mat = p.possibleMoves();
			
			if (mat[kingPosition.getRow()][kingPosition.getColumn()]) {
				return true;
			}
		}

		return false;
	}

	private boolean testCheckMate(Color color) {

		// If opponent is not under check, it can't be check mate.
		if (check == false) {
			return false;
		}
		
		List<ChessPiece> opponentPieces =	piecesOnTheBoard.
											stream().
											filter(p -> p.getColor() == color).
											collect(Collectors.toList());
		
		for (ChessPiece piece : opponentPieces) {
			
			boolean[][] mat = piece.possibleMoves();
			
			for (int i = 0; i < board.getRows(); i++) {
				for (int j = 0; j < board.getColumns(); j++) {
					if (mat[i][j]) {
						Position source = piece.getChessPosition().toPosition();
						Position target = new Position(i, j);

						Piece capturedPiece = makeMove(source, target);

						boolean testCheck = testCheck(color);

						undoMove(source, target, capturedPiece);

						if (!testCheck) {
							return false;
						}
					}
				}
			}
		}
		
		return true;
	}

	private void initialSetup() {
		
		placeNewPiece('a', 1, new Rook(board, Color.WHITE));
		placeNewPiece('b', 1, new Knight(board, Color.WHITE));
		placeNewPiece('c', 1, new Bishop(board, Color.WHITE));
		placeNewPiece('d', 1, new Queen(board, Color.WHITE));
		placeNewPiece('e', 1, new King(board, Color.WHITE, this));
		placeNewPiece('f', 1, new Bishop(board, Color.WHITE));
		placeNewPiece('g', 1, new Knight(board, Color.WHITE));
		placeNewPiece('h', 1, new Rook(board, Color.WHITE));
		placeNewPiece('a', 2, new Pawn(board, Color.WHITE, this));
		placeNewPiece('b', 2, new Pawn(board, Color.WHITE, this));
		placeNewPiece('c', 2, new Pawn(board, Color.WHITE, this));
		placeNewPiece('d', 2, new Pawn(board, Color.WHITE, this));
		placeNewPiece('e', 2, new Pawn(board, Color.WHITE, this));
		placeNewPiece('f', 2, new Pawn(board, Color.WHITE, this));
		placeNewPiece('g', 2, new Pawn(board, Color.WHITE, this));
		placeNewPiece('h', 2, new Pawn(board, Color.WHITE, this));
		
		placeNewPiece('a', 8, new Rook(board, Color.BLACK));
		placeNewPiece('b', 8, new Knight(board, Color.BLACK));
		placeNewPiece('c', 8, new Bishop(board, Color.BLACK));
		placeNewPiece('d', 8, new Queen(board, Color.BLACK));
		placeNewPiece('e', 8, new King(board, Color.BLACK, this));
		placeNewPiece('f', 8, new Bishop(board, Color.BLACK));
		placeNewPiece('g', 8, new Knight(board, Color.BLACK));
		placeNewPiece('h', 8, new Rook(board, Color.BLACK));
		placeNewPiece('a', 7, new Pawn(board, Color.BLACK, this));
		placeNewPiece('b', 7, new Pawn(board, Color.BLACK, this));
		placeNewPiece('c', 7, new Pawn(board, Color.BLACK, this));
		placeNewPiece('d', 7, new Pawn(board, Color.BLACK, this));
		placeNewPiece('e', 7, new Pawn(board, Color.BLACK, this));
		placeNewPiece('f', 7, new Pawn(board, Color.BLACK, this));
		placeNewPiece('g', 7, new Pawn(board, Color.BLACK, this));
		placeNewPiece('h', 7, new Pawn(board, Color.BLACK, this));
		
	}

}
