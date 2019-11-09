package aula142ChessGame.application;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.sun.corba.se.spi.ior.MakeImmutable;

import aula142ChessGame.boardgame.BoardException;
import aula142ChessGame.chess.ChessMatch;
import aula142ChessGame.chess.ChessPiece;
import aula142ChessGame.chess.ChessPosition;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		ChessMatch chessMatch = new ChessMatch();

		while (true) {
			try {
				UI.clearScreen();
				UI.printBoard(chessMatch.getPieces());
				System.out.println();
				
				System.out.print("Source: ");
				ChessPosition source = UI.readChessPosition(sc);
				chessMatch.validateSourcePosition(source);
				
				boolean[][] possibleMoves = chessMatch.possibleMoves(source);
				UI.clearScreen();
				UI.printBoard(chessMatch.getPieces(),possibleMoves);
				
				System.out.print("Target: ");
				ChessPosition target = UI.readChessPosition(sc);
				chessMatch.validateTargetPosition(source, target);
				
				ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
				
			} catch (BoardException e) {
				System.out.println(e.getMessage());
				System.out.print("Press Enter to continue...");
				sc.nextLine();
			} catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				System.out.print("Press Enter to continue...");
				sc.nextLine();
			}
			catch (RuntimeException e) {
				System.out.println(e.getMessage());
				System.out.print("Press Enter to continue...");
				sc.nextLine();
			}
		}

		// sc.close();

		/* Próxima aula: 153 */
	}

}
