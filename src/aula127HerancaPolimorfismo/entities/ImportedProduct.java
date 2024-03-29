package aula127HerancaPolimorfismo.entities;

import aula118Composicao.model.entities.Product;

public class ImportedProduct extends Product {
	private Double customsFee;
	
	public ImportedProduct() {
		super();
	}
	
	public ImportedProduct(String name, Double price, Double customsFee) {
		super(name, price);
		this.customsFee = customsFee;
	}
	
	public Double getCustomsFee() {
		return customsFee;
	}

	public void setCustomsFee(Double customsFee) {
		this.customsFee = customsFee;
	}
	
	@Override
	public String toString() {
		return	getName() + " $ " +
				String.format("%.2f", getPrice() + customsFee) +
				String.format(" (Customs fee: $ %.2f)", customsFee);
	}
	
	public Double totalPrice() {
		return super.getPrice() + customsFee;
	}
	
}
