package aula178BufferedWriter;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Main {

	public static void main(String[] args) {
		
		String path = "D:\\Users\\Samurai\\Documents\\JAVA\\eclipse-jee\\udemyJava2019\\out.txt";

		try (BufferedWriter br = Files.newBufferedWriter(Paths.get(path), StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
			
			String [] lines = {	"As marias � tudo froxo!",
								"�xo, �oxo, as maria � tudo froxo!",
								"Somos sinistros!",
								"�h, �h, �h, somos sinistros!" };
			
			for (String line : lines) {
				br.write(line);
				br.newLine();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
