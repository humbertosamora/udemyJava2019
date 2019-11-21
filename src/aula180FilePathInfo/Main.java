package aula180FilePathInfo;

import java.io.File;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter a file path: ");
		
		String strPath = sc.nextLine();
		
		File file = new File(strPath);
		
		System.out.println("File.getParent(): " + file.getParent());	// Parent directory
		System.out.println("File.getPath(): " + file.getPath());		// File name + path 
		System.out.println("File.getName(): " + file.getName());		// Just file name
		
		sc.close();
		
	}

}
