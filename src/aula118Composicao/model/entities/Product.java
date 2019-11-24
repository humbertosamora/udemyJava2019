package aula118Composicao.model.entities;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Product implements Comparable <Product> {
	
	private String name;
	private Double price;
	
	public Product() {
		
	}
	
	public Product(String name, Double price) {
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return name + ", $ " + String.format("%.2f", price); 
	}
	
	/**
     * Returns a list of products read from a CSV file formatted as: "product name,product price"
     *
     * @param  filePath
     *         A string representing the full file path pointing to a CSV file.
     *
     * @param  charSet
     *         The character set used to save the CSV file. The valid values are:<BR>
     *         Charset.US_ASCII<BR>
	 *		   Charset.ISO_8859_1<BR>
     *         Charset.UTF_8<BR>
     *         Charset.UTF_16BE<BR>
     *         Charset.UTF_16LE<BR>
     *         Charset.UTF_16
     *
     * @return Returns a list of products.
	 * 
	 * @throws IOException
     *
     * @author Humberto Samora <a href="https://github.com/humbertosamora">https://github.com/humbertosamora</a> 
     *
     * @see  java.nio.charset.Charset
     * 
     */
	public static List<Product> fromCSV(String filePath, Charset charSet) throws IOException{
		
		try (BufferedReader br = Files.newBufferedReader(Paths.get(filePath), charSet)) {

			List<Product> products = new ArrayList<Product>();

			String line = br.readLine();

			while (line != null) {

				String[] fields = line.trim().split(",");
				String productName = fields[0];
				double price = Double.parseDouble(line.trim().split(",")[1]);

				products.add(new Product(productName, price));
				line = br.readLine();
			}
			
			return products;
		}
		
	}
	
	@Override
	public int compareTo(Product other) {
		return price.compareTo(other.price);
	}
	
}
