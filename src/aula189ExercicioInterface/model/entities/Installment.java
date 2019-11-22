package aula189ExercicioInterface.model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Installment {
	
	Date dueDate;
	Double amount;
	private static SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
	
	public Installment() {
		
	}
	
	public Installment(Date dueDate, Double amount) {
		super();
		this.dueDate = dueDate;
		this.amount = amount;
	}
	
	public Date getDueDate() {
		return dueDate;
	}
	
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	
	public Double getAmount() {
		return amount;
	}
	
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	@Override
	public String toString() {
		return sf.format(dueDate) + " - " + String.format("%.2f", amount);
	}
	
}
