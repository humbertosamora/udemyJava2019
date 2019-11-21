package aula176BufferedReader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
	public static void main(String[] args) {
		
		String path = "D:\\Users\\Samurai\\Documents\\JAVA\\eclipse-jee\\udemyJava2019\\in.txt";
		
		try (BufferedReader br = Files.newBufferedReader(Paths.get(path), StandardCharsets.UTF_8)) {
			
			String line = br.readLine();
			
			while (line!=null) {
				if (line!=null) System.out.println(line);
				line = br.readLine();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
