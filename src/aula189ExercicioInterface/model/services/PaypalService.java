package aula189ExercicioInterface.model.services;

public class PaypalService implements OnlinePaymentService {
	
	@Override
	public double paymentFee(double amount) {
		return amount * 0.02;
	}
	
	@Override
	public double [] interest(double amount, int months) {
		
		double [] interests = new double[months];
		
		for (int i=0; i<months; i++) {
			interests[i] = (double) (1.00 + 0.01 * (i + 1)) * amount / months; 
		}
		
		return interests;
	}
	
}
