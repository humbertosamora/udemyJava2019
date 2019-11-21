package aula179Diretorios;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter a folder path: ");
		String strPath = sc.nextLine();
		
		File path = new File(strPath);
		
		List<File> folders  = Arrays.asList(path.listFiles());
		System.out.println();
		System.out.println("FOLDERS:");
		folders.stream().filter(f -> f.isDirectory()).forEach(f -> System.out.println(f));
		
		File [] files = path.listFiles(f -> f.isFile());
		System.out.println();
		System.out.println("FILES:");
		for (File file : files) {
			System.out.println(file);
		}
		
		sc.close();
		
	}

}
