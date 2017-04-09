package salary_payment;

import java.util.HashMap;

/**
 * Created by Polylanger on 2017/4/9.
 */
public class PayrollDatabase {

    public static final PayrollDatabase GPayroolDatabase = new PayrollDatabase();

    private HashMap<Integer, Employee> itsEmployees;

    public PayrollDatabase() {
        itsEmployees = new HashMap<Integer, Employee>();
    }

    public Employee getEmployee(int empId) {
        return itsEmployees.get(empId);
    }

    public void AddEmployee(int empId, Employee employee) {
        itsEmployees.put(empId, employee);
    }

    public void clear() {
        itsEmployees.clear();
    }

    public void deleteEmployee(int empId) {
        itsEmployees.remove(empId);
    }
}
