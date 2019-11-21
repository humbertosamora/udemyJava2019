package aula118Composicao.model.entities;

public class OrderItem {
	private Integer quantity;
	private Double price;
	private Product product;
	
	public OrderItem() {
	}
	
	public OrderItem(Integer quantity, Double price, String productName) {
		this.quantity = quantity;
		
		// A princípio o preço da ordem e do produto são iguais, mas pode haver taxas no futuro.
		this.price = price;
		
		this.product = new Product(productName, price);
	}
	
	public Integer getQuantity() {
		return quantity;
	}
	
	public void setQuantity(Integer quantity) {
		if (quantity >= 0) this.quantity = quantity;
	}
	
	public Double getPrice() {
		return price;
	}
	
	public void setPrice(Double price) {
		if (price >= 0) this.price = price;
	}
	
	public String getProductName() {
		return product.getName();
	}
	
	public Double subTotal() {
		return quantity * price;
	}

	@Override
	public String toString() {
		
		return	product +
				", Quantity: " + quantity +
				", Subtotal: $" + String.format("%.2f", subTotal());
	}
	
}
