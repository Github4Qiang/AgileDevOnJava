package salary_payment;

/**
 * Created by Polylanger on 2017/4/9.
 */
public class Employee {

    private PaymentMethod method;
    private PaymentClassification classification;
    private PaymentSchedule schedule;

    private int empId;
    private String name;
    private String home;

    public Employee(int empId, String name, String home) {
        this.empId = empId;
        this.name = name;
        this.home = home;
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
}
