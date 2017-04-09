package salary_payment;

/**
 * Created by Polylanger on 2017/4/9.
 */
public class ChangeAddressTransaction extends ChangeEmployeeTransaction {
    private String address;

    public ChangeAddressTransaction(int empId, String address) {
        super(empId);
        this.address = address;
    }

    public void change(Employee e) {
        e.setAddress(address);
    }
}
