package salary_payment;

/**
 * Created by Polylanger on 2017/4/9.
 */
public class AddCommissionEmployee extends AddEmployeeTransaction {
    private double salary;
    private double commissionRate;

    public AddCommissionEmployee(int empId, String name, String home, double salary, double commissionRate) {
        super(empId, name, home);
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
