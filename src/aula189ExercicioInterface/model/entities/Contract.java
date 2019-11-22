package aula189ExercicioInterface.model.entities;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Contract {
	
	private Integer number;
	private Date date;
	private Double totalValue;
	private List<Installment> installments = new ArrayList<Installment>();
	
	public Contract() {
		
	}
	
	public Contract(Integer number, Date date) {
		if (number<=0 ) {
			throw new InvalidParameterException("Contract: The contract number must be greater than zero.");
		}
		
		this.number = number;
		this.date = date;
	}
	
	public Integer getNumber() {
		return number;
	}
	
	public void setNumber(Integer number) {
		if (number<=0 ) {
			throw new InvalidParameterException("Contract number must be greater than zero.");
		}
		
		this.number = number;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public Double getTotalValue() {
		return totalValue;
	}
	
	public void setTotalValue(Double totalValue) {
		this.totalValue = totalValue;
	}

	public List<Installment> getInstallments() {
		return installments;
	}

	public void setInstallments(List<Installment> installments) {
		this.installments = installments;
	}
	
}
