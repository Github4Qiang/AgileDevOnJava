package salary_payment;

import java.util.HashMap;
import java.util.Set;

/**
 * Created by Polylanger on 2017/4/9.
 */
public class PayrollDatabase {

    public static final PayrollDatabase GPayroolDatabase = new PayrollDatabase();

    private HashMap<Integer, Employee> itsEmployees;
    private HashMap<Integer, Integer> itsAffilication;

    public PayrollDatabase() {
        itsEmployees = new HashMap<Integer, Employee>();
        itsAffilication = new HashMap<Integer, Integer>();
    }

    public Set<Integer> getAllEmployeesId() {
        return itsEmployees.keySet();
    }

    public Employee getEmployee(int empId) {
        return itsEmployees.get(empId);
    }

    public void addEmployee(int empId, Employee employee) {
        itsEmployees.put(empId, employee);
    }

    public void clear() {
        itsEmployees.clear();
    }

    public void deleteEmployee(int empId) {
        itsEmployees.remove(empId);
    }

    public void addUnionMember(int memberId, int empId) {
        itsAffilication.put(memberId, empId);
    }

    public void removeUnionMember(int memberId) {
        itsAffilication.remove(memberId);
    }

    public Employee getUnionMember(int memberId) {
        Integer empId = itsAffilication.get(memberId);
        return itsEmployees.get(empId);
    }
}
