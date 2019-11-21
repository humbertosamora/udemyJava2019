package aula181ExercicioArquivos.entities;

import aula118Composicao.model.entities.OrderItem;

public class OrderItemCSV extends OrderItem {
	
	public OrderItemCSV() {
		super();
	}
	
	public OrderItemCSV(Integer quantity, Double price, String productName) {
		super(quantity, price, productName);
	}

	@Override
	public String toString() {
		return String.format("%s,%.2f", getProductName(), subTotal());
	}
	
}
