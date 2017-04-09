package salary_payment;

/**
 * Created by Polylanger on 2017/4/9.
 */
public abstract class ChangeMethodTransaction extends ChangeEmployeeTransaction {

    public ChangeMethodTransaction(int empId) {
        super(empId);
    }

    public void change(Employee e) {
        e.setMethod(getMethod());
    }

    public abstract PaymentMethod getMethod();
}
