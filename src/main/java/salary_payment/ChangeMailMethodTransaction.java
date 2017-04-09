package salary_payment;

/**
 * Created by Polylanger on 2017/4/9.
 */
public class ChangeMailMethodTransaction extends ChangeMethodTransaction {
    private String address;

    public ChangeMailMethodTransaction(int empId, String address) {
        super(empId);
        this.address = address;
    }

    public PaymentMethod getMethod() {
        return new MailMethod(address);
    }
}
