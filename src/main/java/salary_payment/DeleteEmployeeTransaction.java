package salary_payment;

/**
 * Created by Polylanger on 2017/4/9.
 */
public class DeleteEmployeeTransaction implements Transaction {

    private int empId;

    public DeleteEmployeeTransaction(int empId) {
        this.empId = empId;
    }

    public void execute() {
        PayrollDatabase.GPayroolDatabase.deleteEmployee(empId);
    }
}
