package salary_payment;

/**
 * Created by Polylanger on 2017/4/9.
 */
public abstract class ChangeClassificationTransaction extends ChangeEmployeeTransaction {
    public ChangeClassificationTransaction(int empId) {
        super(empId);
    }

    public void change(Employee e) {
        e.setClassification(getClassification());
        e.setSchedule(getSchedule());
    }

    public abstract PaymentClassification getClassification();

    public abstract PaymentSchedule getSchedule();
}
