package aula130ClasseAbstrata.entities;

public abstract class TaxPayer {
	private String name;
	private Double anualIncome;
	
	public abstract Double taxesPaid();
	
	public TaxPayer() {
		
	}
	
	public TaxPayer(String name, Double anualIncome) {
		this.name = name;
		this.anualIncome = anualIncome;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getAnualIncome() {
		return anualIncome;
	}

	public void setAnualIncome(Double anualIncome) {
		this.anualIncome = anualIncome;
	}

	@Override
	public String toString() {
		return String.format("%s: $ %.2f", name , taxesPaid());
	}
	
}
