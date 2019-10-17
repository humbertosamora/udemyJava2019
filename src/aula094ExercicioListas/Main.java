package aula094ExercicioListas;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.LinkedList;

public class Main {
	
	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		
		List <Employee> employees = new LinkedList<Employee>();
		Scanner sc = new Scanner (System.in);
		
		
		System.out.print("How many employees will be registered? ");
		
		int n = sc.nextInt();
		
		for (int i=1; i<= n; i++) {
			System.out.println();
			System.out.println("Emplyoee #" + i);
			
			System.out.print("Id: ");
			int id = sc.nextInt();
			sc.nextLine();	// Remove o enter digitado após o inteiro
			
			System.out.print("Name: ");
			String name = sc.nextLine();
			
			System.out.print("Salary: ");
			double salary = Double.parseDouble(sc.nextLine().trim().replace(',','.'));
			
			employees.add(new Employee(id, name, salary));
		}
		
		System.out.println();
		System.out.print("Enter the employee id that will have salary increase :");
		int id = sc.nextInt();
		sc.nextLine();	// Retira o enter digitado
		
		Employee employee =	employees.
							stream().
							filter(s -> s.getId() == id).
							findAny().
							orElse(null); 
		
		if ( employee != null ) {
			System.out.print("Enter the percentage: ");
			double percentage = Double.parseDouble(sc.nextLine().trim().replace(',', '.'));
			employee.setSalary(employee.getSalary() * (1.0 + percentage/100.0) );
			
		} else {
			System.out.println("This id does not exist!");
		}
		
		System.out.println();
		System.out.println("List of employees:");
		employees.forEach(System.out::println);		
		
		sc.close();
	}
}
