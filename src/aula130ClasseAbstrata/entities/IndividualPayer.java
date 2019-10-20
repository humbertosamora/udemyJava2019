package aula130ClasseAbstrata.entities;

public class IndividualPayer extends TaxPayer {
	private Double healthexpendituries;
	
	public IndividualPayer() {
		super();
	}
	
	public IndividualPayer(String name, Double anualIncome, Double healthexpendituries) {
		super(name, anualIncome);
		this.healthexpendituries = healthexpendituries;
	}
	
	@Override
	public Double taxesPaid() {
		if (getAnualIncome() < 20000.00) {
			return (getAnualIncome() * 0.15 - healthexpendituries * 0.50); 
		} else {
			return (getAnualIncome() * 0.25 - healthexpendituries * 0.50);
		}
	}

}
