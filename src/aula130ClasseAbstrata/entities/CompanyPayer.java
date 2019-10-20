package aula130ClasseAbstrata.entities;

public class CompanyPayer extends TaxPayer {
	private Integer numberOfEmployees;

	public CompanyPayer() {
		super();
	}
	
	public CompanyPayer(String name, Double anualIncome, Integer numberOfEmployees) {
		super(name, anualIncome);
		this.numberOfEmployees = numberOfEmployees;
	}
	
	@Override
	public Double taxesPaid() {
		if (numberOfEmployees > 10) {
			return getAnualIncome() * 0.14;
		} else {
			return getAnualIncome() * 0.16;
		}
	}
	
	
}
