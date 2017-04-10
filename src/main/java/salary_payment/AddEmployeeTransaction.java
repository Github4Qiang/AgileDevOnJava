package salary_payment;

/**
 * Created by Polylanger on 2017/4/9.
 */
public abstract class AddEmployeeTransaction implements Transaction {

    private int empId;
    private String name;
    private String address;

    public AddEmployeeTransaction(int empId, String name, String address) {
        this.empId = empId;
        this.name = name;
        this.address = address;
    }

    public void execute() {
        PaymentMethod pm = new HoldMethod();
        PaymentSchedule ps = getSchedule();
        PaymentClassification pc = getClassification();
        Employee e = new Employee(empId, name, address);
        e.setClassification(pc);
        e.setSchedule(ps);
        e.setMethod(pm);
        e.setAffiliation(getAffiliation());
        PayrollDatabase.GPayroolDatabase.addEmployee(empId, e);
    }

    public Affiliation getAffiliation() {
        return new NoAffiliation();
    }

    public abstract PaymentClassification getClassification();

    public abstract PaymentSchedule getSchedule();

}
