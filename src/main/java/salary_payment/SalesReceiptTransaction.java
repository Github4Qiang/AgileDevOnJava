package salary_payment;

import java.util.Date;

/**
 * Created by Polylanger on 2017/4/9.
 */
public class SalesReceiptTransaction implements Transaction{
    private Date date;
    private double amount;
    private int empId;

    public SalesReceiptTransaction(Date date, double amount, int empId) {
        this.date = date;
        this.amount = amount;
        this.empId = empId;
    }

    public void execute() {
        Employee e = PayrollDatabase.GPayroolDatabase.getEmployee(empId);
        if (e != null) {
            PaymentClassification pc = e.getClassification();
            if (pc instanceof CommissionedClassification) {
                ((CommissionedClassification) pc).addSalesReceipt(new SalesReceipt(date, amount));
            } else {
                throw new RuntimeException("Tried to add SalesReceipt to non-commissioned employee");
            }
        } else {
            throw new RuntimeException("No such employee: empId=" + empId);
        }
    }
}
