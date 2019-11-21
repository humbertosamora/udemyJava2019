package aula181ExercicioArquivos.application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import aula181ExercicioArquivos.entities.OrderItemCSV;

public class Main {

	public static void main(String[] args) {
		
		// Set "." as decimal separator 
		Locale.setDefault(Locale.US);
		
		Scanner sc = new Scanner (System.in);
		
		System.out.print("Enter file name: ");
		
		Path inPath = Paths.get(sc.nextLine());
		Path outPath = Paths.get(inPath.getParent() + "\\out\\summary.csv");
		
		File directory = new File(inPath.getParent() + "\\out");
		if (!directory.exists()) {
			if (!directory.mkdir()) System.out.println("Cannot create " + directory.getPath() + " directory.");
			else System.out.println("Directory " + directory.getPath() + " created.");
		}
		
		try (BufferedReader br = Files.newBufferedReader(inPath, StandardCharsets.UTF_8);
			 BufferedWriter bw = Files.newBufferedWriter(outPath, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);) {
			
			List<OrderItemCSV> orders = new ArrayList<OrderItemCSV>();
			
			System.out.println("Reading file.");
			String line = br.readLine();
			
			while (line != null) {
				
				String [] fields = line.trim().split(","); 
				String productName = fields[0];
				double price = Double.parseDouble(line.trim().split(",")[1]);
				int quantity = Integer.parseInt(line.trim().split(",")[2]);				
				
				orders.add(new OrderItemCSV(quantity, price, productName));
				line = br.readLine();
			}
			
			for (OrderItemCSV order : orders) {
				bw.write("" + order);
				bw.newLine();
			}
			
			System.out.println("Writing file.");
			
		}
		catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		catch (NumberFormatException  e) {
			System.out.println("Error: " + e.getMessage());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Done!");
		
		sc.close();
		
	}

}
