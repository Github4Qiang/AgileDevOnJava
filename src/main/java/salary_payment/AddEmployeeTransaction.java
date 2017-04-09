package salary_payment;

/**
 * Created by Polylanger on 2017/4/9.
 */
public abstract class AddEmployeeTransaction implements Transaction {

    private int empId;
    private String name;
    private String home;

    public AddEmployeeTransaction(int empId, String name, String home) {
        this.empId = empId;
        this.name = name;
        this.home = home;
    }

    public void execute() {
        PaymentMethod pm = new HoldMethod();
        PaymentSchedule ps = getSchedule();
        PaymentClassification pc = getClassification();
        Employee e = new Employee(empId, name, home);
        e.setClassification(pc);
        e.setSchedule(ps);
        e.setMethod(pm);
        PayrollDatabase.GPayroolDatabase.AddEmployee(empId, e);
    }

    public abstract PaymentClassification getClassification();

    public abstract PaymentSchedule getSchedule();

}
