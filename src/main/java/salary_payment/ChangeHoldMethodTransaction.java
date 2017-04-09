package salary_payment;

/**
 * Created by Polylanger on 2017/4/9.
 */
public class ChangeHoldMethodTransaction extends ChangeMethodTransaction {

    public ChangeHoldMethodTransaction(int empId) {
        super(empId);
    }

    public PaymentMethod getMethod() {
        return new HoldMethod();
    }
}
