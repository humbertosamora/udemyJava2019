package aula141Execoes;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

import aula141Execoes.entities.Account;

public class Main {
	
	public static void main(String[] args) {
		Account account;
		Scanner sc = null;

		try {
			Locale.setDefault(Locale.US);
			sc = new Scanner(System.in);
			
			System.out.println("Enter account data");
			System.out.print("Number: ");
			int number = sc.nextInt();
			sc.nextLine();

			System.out.print("Holder: ");
			String holder = sc.nextLine();

			System.out.print("Initial balance: ");
			double balance = sc.nextDouble();

			System.out.print("Withdraw limit: ");
			double withdrawLimit = sc.nextDouble();

			System.out.println();
			System.out.print("Enter amount for withdraw: ");
			double amount = sc.nextDouble();
			account = new Account(number, holder, balance, withdrawLimit);
			account.withdraw(amount);
			System.out.printf("New balance: %.2f\n", account.getBalance());
			
		} catch (InputMismatchException e) {
			System.out.println("Input error.");
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

		if (sc != null) {
			sc.close();
		}
	}

}
