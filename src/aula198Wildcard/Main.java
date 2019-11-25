package aula198Wildcard;

import java.util.Arrays;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		List <Integer> ints = Arrays.asList(1, 15, 18, 20, 39);
		printlist(ints);
		
		List <String> strs = Arrays.asList("Aline", "Joyce", "Cristina");
		printlist(strs);
	}
	
	
	/**
	 * Didactic parametric function to print a generic {@literal List<T>}.
	 *
	 * @param list A List of generic type {@literal <T>}.
	 * 
	 * */
	public static void printlist(List<?> list) {
		
		if (list == null) {
			System.out.println("null");
		}
		else if (list.size()==0) {
			System.out.println("[ ]");
		}
		else {

			System.out.print("[");

			for (int i = 0; i < list.size(); i++) {
				if (i > 0) {
					System.out.print(", ");
				}

				System.out.print(list.get(i));
			}

			System.out.println("]");
		}
	}

}
