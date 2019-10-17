package aula025EntradaDados;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		int x;
		String s1;
		String s2;
		String s3;
		
		Scanner sc = new Scanner(System.in);
		
		x = sc.nextInt();
		// Retira a quebra de linha adicional devido ao "enter" após digitar o número
		sc.nextLine();
		s1 = sc.nextLine();
		s2 = sc.nextLine();
		s3 = sc.nextLine();
		
		System.out.printf("Você digitou:\n%d\n%s\n%s\n%s", x, s1, s2, s3);
		
		sc.close();
	}

}
