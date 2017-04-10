package salary_payment;

import java.util.*;

/**
 * Created by Polylanger on 2017/4/10.
 */
public class PaydayTransaction implements Transaction {

    private Date payDate;
    private HashMap<Integer, Paycheck> paychecks;

    public PaydayTransaction(Date payDate) {
        this.payDate = payDate;
        paychecks = new HashMap<Integer, Paycheck>();
    }

    public void execute() {
        Set<Integer> keySet = PayrollDatabase.GPayroolDatabase.getAllEmployeesId();
        for (Integer empId : keySet) {
            Employee e = PayrollDatabase.GPayroolDatabase.getEmployee(empId);
            if (e.isPayDate(payDate)) {
                Paycheck pc = new Paycheck(payDate);
                paychecks.put(empId, pc);
                e.payDay(pc);
            }
        }
    }

    public Paycheck getPaycheck(int empId) {
        return paychecks.get(empId);
    }
}
