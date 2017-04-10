package salary_payment;

import java.util.Date;

/**
 * Created by Polylanger on 2017/4/9.
 */
public class Employee {

    private PaymentMethod method;
    private PaymentClassification classification;
    private PaymentSchedule schedule;

    private Affiliation affiliation;

    private int empId;
    private String name;
    private String address;

    public Employee(int empId, String name, String address) {
        this.empId = empId;
        this.name = name;
        this.address = address;
    }

    public void setMethod(PaymentMethod method) {
        this.method = method;
    }

    public void setClassification(PaymentClassification classification) {
        this.classification = classification;
    }

    public void setSchedule(PaymentSchedule schedule) {
        this.schedule = schedule;
    }

    public String getName() {
        return name;
    }

    public PaymentClassification getClassification() {
        return classification;
    }

    public PaymentSchedule getSchedule() {
        return schedule;
    }

    public PaymentMethod getMethod() {
        return method;
    }

    public void setAffiliation(Affiliation affiliation) {
        this.affiliation = affiliation;
    }

    public Affiliation getAffiliation() {
        return affiliation;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public boolean isPayDate(Date payDate) {
        return schedule.isPayDate(payDate);
    }

    public void payDay(Paycheck pc) {
        double grossPay = classification.calculatePay(pc);
        double deductions = affiliation.calculateDeductions(pc);
        double netPay = grossPay - deductions;
        pc.setGrossPay(grossPay);
        pc.setDeductions(deductions);
        pc.setNetPay(netPay);
        method.pay(pc);
    }
}
