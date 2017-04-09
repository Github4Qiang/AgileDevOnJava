package salary_payment;

/**
 * Created by Polylanger on 2017/4/9.
 */
public class ChangeHourlyTransaction extends ChangeClassificationTransaction {
    private double hourlyRate;

    public ChangeHourlyTransaction(int empId, double hourlyRate) {
        super(empId);
        this.hourlyRate = hourlyRate;
    }

    public PaymentClassification getClassification() {
        return new HourlyClassification(hourlyRate);
    }

    public PaymentSchedule getSchedule() {
        return new WeeklySchedule();
    }
}
