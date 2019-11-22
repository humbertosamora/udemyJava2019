package aula189ExercicioInterface.application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Scanner;
import java.util.TimeZone;

import aula189ExercicioInterface.model.entities.Contract;
import aula189ExercicioInterface.model.services.ContractService;
import aula189ExercicioInterface.model.services.PaypalService;

public class Main {

	public static void main(String[] args) {

		try (Scanner sc = new Scanner(System.in)) {
			
			Locale.setDefault(Locale.US);
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			sdf.setTimeZone(TimeZone.getDefault());
			
			Contract contract = new Contract();
			
			System.out.println("Enter contract data");
			System.out.print("Number: ");
			contract.setNumber(Integer.parseInt(sc.nextLine()));
			System.out.print("Date (dd/MM/yyyy): ");
			contract.setDate(sdf.parse(sc.nextLine()));
			System.out.print("Contract value: ");
			contract.setTotalValue(Double.parseDouble(sc.nextLine()));
			System.out.print("Enter the number of installments: ");
			int months = Integer.parseInt(sc.nextLine()); 
			
			ContractService service = new ContractService(new PaypalService());
			service.processContract(contract, months);
			
			System.out.println("Installments:");
			contract.getInstallments().stream().forEach(i -> System.out.println(i));
			
		}
		catch (ParseException e) {
			System.out.println("Error: " + e.getMessage());
		}
		catch (NullPointerException e) {
			System.out.println("Error: " + e.getMessage());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
