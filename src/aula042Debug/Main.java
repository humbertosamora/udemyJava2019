package aula042Debug;

import java.util.Locale;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		String str;
		
		Locale.setDefault(Locale.US);
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Largura do terreno: ");
		//double largura = sc.nextDouble();
		str = sc.nextLine().trim();
		double largura = Double.parseDouble(str.split(" ")[0].replace(",", "."));
		
		System.out.print("Comprimento do terreno: ");
		//double comprimento = sc.nextDouble();
		str = sc.nextLine().trim();
		double comprimento = Double.parseDouble(str.split(" ")[0].replace(",", "."));
		
		System.out.print("Preço do metro quadrado: ");
		//double metroQuadrado = sc.nextDouble();
		str = sc.nextLine().trim();
		double metroQuadrado = Double.parseDouble(str.split(" ")[0].replace(",", "."));
		
		double area = largura * comprimento;
		double preco = area * metroQuadrado;
		
		System.out.printf("AREA = %.2f%n", area);
		System.out.printf("PRECO = %.2f%n", preco);
		
		sc.close();
	}
}
