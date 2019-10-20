package aula130ClasseAbstrata;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import aula130ClasseAbstrata.entities.CompanyPayer;
import aula130ClasseAbstrata.entities.IndividualPayer;
import aula130ClasseAbstrata.entities.TaxPayer;

public class Main {

	public static void main(String[] args) {
		
		List<TaxPayer> payers = new ArrayList<TaxPayer>();
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter the number of tax payers: ");
		int numberOfPayers = Integer.parseInt(sc.nextLine().trim());
		
		for (int i=1; i<=numberOfPayers; i++) {
			
			System.out.printf("Tax payer %d data:\n",i);
			
			System.out.print("Individual or company (i/c)? ");
			char option = sc.nextLine().toLowerCase().charAt(0);
			
			System.out.print("Name: ");
			String name = sc.nextLine().trim();
			
			System.out.print("Anual income: ");
			Double anualIncome = Double.parseDouble(sc.nextLine().trim().replace(',','.'));
			
			switch (option) {
				case 'i':
					System.out.print("Health expenditures: ");
					Double healthexpendituries = Double.parseDouble(sc.nextLine().trim().replace(',','.'));
					payers.add(new IndividualPayer(name, anualIncome, healthexpendituries));
					break;
				case 'c':
					System.out.print("Number of employees: ");
					Integer numberOfEmployees = Integer.parseInt(sc.nextLine().trim());
					payers.add(new CompanyPayer(name, anualIncome, numberOfEmployees));
					break;
				default:
			}
		}
		
		System.out.println();
		System.out.println("TAXES PAID:");
		payers.forEach(s -> System.out.println(s));
		
		System.out.printf("TOTAL TAXES: $%.2f\n", payers.stream().mapToDouble(TaxPayer::taxesPaid).sum());
		
		sc.close();

	}

}
