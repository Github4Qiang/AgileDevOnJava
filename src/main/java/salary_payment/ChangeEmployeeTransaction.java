package salary_payment;

/**
 * Created by Polylanger on 2017/4/9.
 */
public abstract class ChangeEmployeeTransaction implements Transaction {
    private int empId;

    public ChangeEmployeeTransaction(int empId) {
        this.empId = empId;
    }

    public void execute() {
        Employee e = PayrollDatabase.GPayroolDatabase.getEmployee(empId);
        change(e);
    }

    public abstract void change(Employee e);

}
