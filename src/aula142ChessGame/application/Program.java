package aula142ChessGame.application;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.function.Supplier;

import aula142ChessGame.boardgame.BoardException;
import aula142ChessGame.chess.ChessMatch;
import aula142ChessGame.chess.ChessPosition;

public class Program {
	
	public static Scanner sc = new Scanner(System.in);
		
	public static void main(String[] args) {
		
		ChessMatch chessMatch = new ChessMatch();
		
		while (!chessMatch.getCheckMate()) {
			try {
				
				UI.clearScreen();
				UI.printMatch(chessMatch);
				
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
				
				chessMatch.performChessMove(source, target, readPromotionType);
				
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
		
		UI.clearScreen();
		UI.printMatch(chessMatch);
		sc.close();
		
	}
	
	// Supplier function used to read the promotion piece type needed in method performChessMove.
	// Inside that method (which is in a inner application layer) the chessMatch must know the
	// promoted type to testCheck and testCheckMat against opponent after promotion a Pawn.
	public static Supplier <Character> readPromotionType = () -> {
		
		while (true) {
			Character type;
			
			try {
				System.out.print("Choose a piece to promotion (B/K/Q/R): ");
				type = sc.nextLine().trim().charAt(0);
				
				if (type!='B' && type!='N' && type!='Q' && type!='R') {
					System.out.println("Type not allowed!");
					continue;
				}
				
				return type;
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
		}
	};
	
}
