package aula127HerancaPolimorfismo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import aula127HerancaPolimorfismo.entities.ImportedProduct;
import aula127HerancaPolimorfismo.entities.Product;
import aula127HerancaPolimorfismo.entities.UsedProduct;

public class Main {

	public static void main(String[] args) throws ParseException {
		
		List<Product> products = new ArrayList<Product>();
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		System.out.print("Enter the number of products: ");
		int numberOfProducts = Integer.parseInt(sc.nextLine().trim());
		
		for (int i=1; i<= numberOfProducts; i++) {
			System.out.println("Product #" + i + " data:");
			
			System.out.print("Common, used or imported (c/u/i)? ");
			char c = sc.nextLine().trim().toLowerCase().charAt(0);
			
			System.out.print("Name: ");
			String name = sc.nextLine();
			
			System.out.print("Price: ");
			double price = Double.parseDouble(sc.nextLine().trim().replace(',', '.'));			
			
			Product p;
			
			switch (c) {
				case 'c':
					p = new Product(name, price);
					products.add(p);
					break;
				case 'u':
					System.out.print("Manufacture date (DD/MM/YYYY): ");
					Date manufactureDate = sdf.parse(sc.nextLine().trim());
					p = new UsedProduct(name, price, manufactureDate);
					products.add(p);
					break;
				case 'i':
					System.out.print("Customs fee: ");
					double customsFee = Double.parseDouble(sc.nextLine().trim().replace(',', '.'));
					p = new ImportedProduct(name, price, customsFee);
					products.add(p);
					break;
				default:
			}
			
		}
		
		System.out.println();
		System.out.println("PRICE TAGS:");
		products.forEach(s -> System.out.println(s.priceTag()));
		
		sc.close();
	}

}
