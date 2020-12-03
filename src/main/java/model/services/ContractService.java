package model.services;

import java.util.Calendar;
import java.util.Date;

import model.entities.Contract;
import model.entities.Installment;

public class ContractService {
    
    private OnlinePaymentService paymentService;


    public ContractService(OnlinePaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public void processContract(Contract contract, Integer months) {
        double baseQuota = contract.getTotalValue() / months;
        for (int i = 1; i <= months; i++) {
            double quotaValue = baseQuota + paymentService.interest(baseQuota, i);
            double finalQuotaValue = quotaValue + paymentService.paymentFee(quotaValue);
            Date dueDate = incrementMonth(contract.getDate(), i);
            contract.getInstallments().add(new Installment(dueDate, finalQuotaValue));
        }
    }

    public Date incrementMonth(Date date, int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, n);
        return calendar.getTime();
    }

}
