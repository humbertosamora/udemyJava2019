package aula093Listas;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) {
	
		List <String> list = new ArrayList<String>();
		
		list.add("Maria");
		list.add("Alex");
		list.add("Bob");
		list.add("Anna");
		list.add("Márcia");
		list.add("Maria");
		list.add("João");
		list.add("Ana Cecília");
		list.add(2,"Joana");
		
		System.out.println("List size: " + list.size());
		
		System.out.println("---------------------");
		for (String s : list) {
			System.out.println(s);
		}
		
		System.out.println("---------------------");
		System.out.println("Index of Bob: " + list.indexOf("Bob"));
		System.out.println("Index of Joana: " + list.indexOf("Joana"));
		System.out.println("Index of Marco: " + list.indexOf("Marco")); // Retorna -1 se não encontrar
		
		System.out.println("---------------------");
		list.removeIf(x -> x.charAt(0) == 'M');
		for (String s : list) {
			System.out.println(s);
		}
	
		System.out.println("---------------------");
		List<String> result =	list.
								stream().
								filter(x -> x.charAt(0) == 'A').
								collect(Collectors.toList());
		for (String s : result) {
			System.out.println(s);
		}
		
		System.out.println("---------------------");
		String name =	list.
						stream().
						filter(x -> x.charAt(0) == 'J').
						findFirst().
						orElse(null);
		System.out.println(name);
	}
}