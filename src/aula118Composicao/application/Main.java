package aula118Composicao.application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import aula118Composicao.model.entities.Client;
import aula118Composicao.model.entities.Order;
import aula118Composicao.model.entities.OrderItem;
import aula118Composicao.model.enums.OrderStatus;

public class Main {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter cliente data:");
		System.out.print("Name: ");
		String name = sc.nextLine().trim();
		
		System.out.print("E-mail: ");
		String email = sc.nextLine().trim();
		
		System.out.print("Birth date (DD/MM/YYYY): ");
		String birthdate = sc.nextLine().trim();
		
		System.out.println("Enter order data: ");
		System.out.print("Status (PENDING_PAYMENT, PROCESSING, SHIPPED, DELIVERD): ");
		String status = sc.nextLine();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			Order order = new Order(OrderStatus.valueOf(status), new Client(name, email, sdf.parse(birthdate)));
			System.out.print("How many items to this order? ");
			int numbersOfItens = Integer.parseInt(sc.nextLine().trim());
			
			for (int i=1; i<=numbersOfItens; i++) {
				System.out.println("Enter #" + i + " item data:");
				System.out.print("Product name: ");
				String productName = sc.nextLine();
				
				System.out.print("Product price: ");
				double productPrice = Double.parseDouble(sc.nextLine().trim().replace(',', '.'));
				
				System.out.print("Quantity: ");
				int productQuantity = Integer.parseInt(sc.nextLine().trim());
				
				order.addItem(new OrderItem(productQuantity, productPrice, productName));
			}
			
			System.out.println();
			System.out.println(order);
			
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sc.close();	
		}
		
	}
}
