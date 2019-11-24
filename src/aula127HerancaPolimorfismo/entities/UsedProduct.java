package aula127HerancaPolimorfismo.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

import aula118Composicao.model.entities.Product;

public class UsedProduct extends Product {
	private Date manufactureDate;
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public UsedProduct() {
		super();
	}
	
	public UsedProduct(String name, Double price, Date manufactureDate) {
		super(name, price);
		this.manufactureDate = manufactureDate;
	}
	
	public Date getManufactureDate() {
		return manufactureDate;
	}
	
	public void setManufactureDate(Date manufactureDate) {
		this.manufactureDate = manufactureDate;
	}
	
	@Override
	public String toString() {
		return	getName() + " (used) $ " +
				String.format("%.2f", getPrice()) +
				" (Manufacture date: " + sdf.format(manufactureDate) + ")";
	}
}
