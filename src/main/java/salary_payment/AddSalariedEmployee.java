package salary_payment;

/**
 * Created by Polylanger on 2017/4/9.
 */
public class AddSalariedEmployee extends AddEmployeeTransaction {

    private double salary;

    public AddSalariedEmployee(int empId, String name, String home, double salary) {
        super(empId, name, home);
        this.salary = salary;
    }

    @Override
    public PaymentClassification getClassification() {
        return new SalariedClassification(salary);
    }

    @Override
    public PaymentSchedule getSchedule() {
        return new MonthlySchedule();
    }
}
