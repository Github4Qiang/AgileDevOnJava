package salary_payment;

/**
 * Created by Polylanger on 2017/4/9.
 */
public class AddHourlyEmployee extends AddEmployeeTransaction {
    private double hourlyRate;

    public AddHourlyEmployee(int empId, String name, String home, double hourlyRate) {
        super(empId, name, home);
        this.hourlyRate = hourlyRate;
    }

    public PaymentClassification getClassification() {
        return new HourlyClassification(hourlyRate);
    }

    public PaymentSchedule getSchedule() {
        return new WeeklySchedule();
    }
}
