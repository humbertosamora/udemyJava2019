package aula197Generics;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.Locale;

import aula118Composicao.model.entities.Product;

public class Main {
	public static void main(String[] args) throws IOException {

		// Set "." as decimal separator
		Locale.setDefault(Locale.US);

		String filePath = "D:\\Users\\Samurai\\Documents\\JAVA\\eclipse-jee\\udemyJava2019\\aula197products.csv";

		List<Product> products = Product.fromCSV(filePath, StandardCharsets.UTF_8);

		Product p = max(products);

		System.out.print("Most expensive product: " + p);

	}
	
	/**
	 * Didactic parametric function to find the maximum value of {@literal List<T>}.
	 * It could be replaced by invoking "Collection.max(T)" into a commercial software. 
	 * 
	 * @param list A List of generic type {@literal <T>}.
	 * 
	 * @return The max object {@literal <T>} contained in {@literal List<T>}.
	 * 
	 * @throws InvalidParameterException
	 *         If the list is empty our null.
	 * 
	 * */
	public static <T extends Comparable<? super T>> T max(List<T> list) {

		if (list == null || list.size() == 0) {
			throw new InvalidParameterException("The list cannot be empty.");
		}

		T maxt = list.get(0);

		for (T t : list) {
			if (t.compareTo(maxt) > 0) {
				maxt = t;
			}
		}

		return maxt;
	}

}
