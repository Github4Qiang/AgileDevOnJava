package salary_payment;

import java.util.Date;

/**
 * Created by Polylanger on 2017/4/9.
 */
public class TimeCardTransaction implements Transaction {
    private Date date;
    private double hours;
    private int empId;

    public TimeCardTransaction(Date date, double hours, int empId) {
        this.date = date;
        this.hours = hours;
        this.empId = empId;
    }

    public void execute() {
        Employee e = PayrollDatabase.GPayroolDatabase.getEmployee(empId);
        if (e != null) {
            PaymentClassification pc = e.getClassification();
            if (pc instanceof HourlyClassification) {
                ((HourlyClassification) pc).addTimeCard(new TimeCard(date, hours));
            } else {
                throw new RuntimeException("Tried to add timecard to non-hourly employee");
            }
        } else {
            throw new RuntimeException("No such employee: empId=" + empId);
        }
    }
}
