package salary_payment;

/**
 * Created by Polylanger on 2017/4/9.
 */
public class ChangeSalariedTransaction extends ChangeClassificationTransaction {
    private double salary;

    public ChangeSalariedTransaction(int empId, double salary) {
        super(empId);
        this.salary = salary;
    }

    public PaymentClassification getClassification() {
        return new SalariedClassification(salary);
    }

    public PaymentSchedule getSchedule() {
        return new MonthlySchedule();
    }
}
