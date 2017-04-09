package salary_payment;

/**
 * Created by Polylanger on 2017/4/9.
 */
public class ChangeDirectMethodTransaction extends ChangeMethodTransaction {
    private String bank;
    private int account;

    public ChangeDirectMethodTransaction(int empId, String bank, int account) {
        super(empId);
        this.bank = bank;
        this.account = account;
    }

    public PaymentMethod getMethod() {
        return new DirectMethod(bank, account);
    }
}
