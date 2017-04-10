package salary_payment;

/**
 * Created by Polylanger on 2017/4/9.
 */
public class SalariedClassification implements PaymentClassification {

    private double salary;

    public SalariedClassification(double salary) {
        this.salary = salary;
    }

    public double calculatePay(Paycheck pc) {
        return salary;
    }
}
