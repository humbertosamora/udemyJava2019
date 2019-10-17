package aula082Vetores;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Rent[] rents = new Rent[10];
		Scanner sc = new Scanner(System.in);
		
		System.out.print("How many rooms will be rented? ");
		int n = sc.nextInt();
		
		for (int i=1; i<=n; i++ ) {
			// Retira o enter após o "nextInt()"
			sc.nextLine();
			System.out.println();
			
			System.out.println("Rent #" + i);
			
			System.out.print("Name: ");
			String name = sc.nextLine();
			
			System.out.print("E-mail: ");
			String email = sc.nextLine();
			
			System.out.print("Room: ");
			int room = sc.nextInt();
			
			if (room > 0 && room < 10) {
				rents[room] =  new Rent(name,email);
			}
		}
		
		System.out.println();		
		System.out.println("Busy rooms:");
		
		for (int i=0; i<10; i++) {
			if (rents[i] != null) {
				System.out.println(i + ": " + rents[i].getName() + ", " + rents[i].getEmail());
			}
		}
		
		sc.close();
	}

}
