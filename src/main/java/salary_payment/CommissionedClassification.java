package salary_payment;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by Polylanger on 2017/4/9.
 */
public class CommissionedClassification implements PaymentClassification {
    private double salary;
    private double commissionRate;
    private HashMap<Long, SalesReceipt> itsSalesReceipts;

    public CommissionedClassification(double salary, double commissionRate) {
        super();
        itsSalesReceipts = new HashMap<Long, SalesReceipt>();
    }

    public void addSalesReceipt(SalesReceipt salesReceipt) {
        itsSalesReceipts.put(salesReceipt.getDate().getTime(), salesReceipt);
    }


    public SalesReceipt getSalesReceipt(Date date) {
        return itsSalesReceipts.get(date.getTime());
    }

    public double calculatePay(Paycheck pc) {
        return 0;
    }
}
