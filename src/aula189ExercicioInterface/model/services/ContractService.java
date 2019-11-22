package aula189ExercicioInterface.model.services;

import java.security.InvalidParameterException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import aula189ExercicioInterface.model.entities.Contract;
import aula189ExercicioInterface.model.entities.Installment;

public class ContractService {
	
	private OnlinePaymentService paymentService;
	
	public ContractService(OnlinePaymentService paymentService) {
		this.paymentService = paymentService;
	}
	
	public void processContract(Contract contract, int months) {
		
		if (months < 1) {
			throw new InvalidParameterException("processContract: The number o months must be at least 1.");
		}
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(contract.getDate());
		
		List<Installment> installments = contract.getInstallments();
		
		double [] amounts = paymentService.interest(contract.getTotalValue(), months);
		
		for (int i=0; i<months; i++ ) {
			// Add "i" monhts to contract date and
			cal.add(Calendar.MONTH, i+1);
			Date dueDate = cal.getTime();
			cal.add(Calendar.MONTH, -(i+1));
			
			installments.add(new Installment(dueDate, amounts[i] + paymentService.paymentFee(amounts[i])));
		}
	}
	
}
