package aula097Matrizes;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter the number of rows and columns in the matrix: ");
		int n = sc.nextInt();
		int m = sc.nextInt();
		
		System.out.println();
		System.out.println("Enter the array:");
		
		int[][] matrix = new int[n][m];
		
		for(int i=0; i<matrix.length; i++) {
			for (int j=0; j<matrix[i].length; j++) {
				matrix[i][j] = sc.nextInt();
			}
		}
		
		System.out.println();
		System.out.print("Enter a number in the array: ");
		int number = sc.nextInt();
		
		for(int i=0; i<matrix.length; i++) {
			for (int j=0; j<matrix[i].length; j++) {
				if (matrix[i][j] == number) {
					System.out.println("Position: [" + i + "," + j +"]");
					// Imprime a célula da esquerda
					if (j-1 >= 0) System.out.println("Left: " + matrix[i][j-1]);
					// Imprime a célula da linha superior
					if (i-1 >= 0) System.out.println("Up: " + matrix[i-1][j]);
					// Imprime a célula da direita
					if (j+1 < matrix[i].length) System.out.println("Right: " + matrix[i][j+1]);
					// Imprime a célula da linha inferior
					if (i+1 < matrix.length) System.out.println("Down: " + matrix[i+1][j]);
				}
			}
		}
		
		sc.close();
	}

}
