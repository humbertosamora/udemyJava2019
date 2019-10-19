package aula118Composicao.model.entities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import aula118Composicao.model.enums.OrderStatus;

public class Order {
	// Variável SimpleDateFormat estático da classe para atender a todos os objetos que chamarem a função toString
	private static SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	
	private Date moment;
	private OrderStatus status;
	private Client client;
	
	List <OrderItem> itens = new ArrayList<OrderItem>();
	
	public Order() {
		moment = new Date(System.currentTimeMillis());
		this.status = OrderStatus.PENDING_PAYMENT;
	}
	
	public Order(OrderStatus status, Client client) {
		moment = new Date(System.currentTimeMillis()); // Cria o pedido com a data e hora atuais
		this.status = status;
		this.client = client;
	}
	
	public Date getMoment() {
		return moment;
	}
	
	// O método setMoment não foi implementado porque o modelo de negócio não permite alterar a data da ordem
//	public void setMoment(Date moment) {
//		this.moment = moment;
//	}
	
	public OrderStatus getStatus() {
		return status;
	}
	
	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	
	public Client getClient() {
		return client;
	}
	
	public void setClient(Client client) {
		this.client = client;
	}
	
	public boolean addItem(OrderItem item) {
		return itens.add(item);
	}
	
	public boolean removeItem(OrderItem item) {
		return itens.remove(item);
	}
	
	public Double total() {
		return itens.stream().mapToDouble(OrderItem::subTotal).sum();
	}
	
	@Override
	public String toString() {
		StringBuilder strb = new StringBuilder();
		
		strb.append("ORDER SUMARY:");
		strb.append("\n");
		strb.append("Order Moment: ");
		strb.append(sdf1.format(moment));
		strb.append("\n");
		strb.append("Order Status: ");
		strb.append(status);
		strb.append("\n");
		strb.append(client);
		strb.append("\n");
		strb.append("Order Itens:");
		strb.append("\n");
		itens.forEach(s -> strb.append(s + "\n"));
		strb.append("Total Price: ");
		strb.append(String.format("%.2f", total()));
		
		return strb.toString();
	}

	
}
