package salary_payment;

/**
 * Created by Polylanger on 2017/4/9.
 */
public class ChangeCommissionedTransaction extends ChangeClassificationTransaction {
    private double salary;
    private double commissionRate;

    public ChangeCommissionedTransaction(int empId, double salary, double commissionRate) {
        super(empId);
        this.salary = salary;
        this.commissionRate = commissionRate;
    }

    public PaymentClassification getClassification() {
        return new CommissionedClassification(salary, commissionRate);
    }

    public PaymentSchedule getSchedule() {
        return new BiweeklySchedule();
    }
}
