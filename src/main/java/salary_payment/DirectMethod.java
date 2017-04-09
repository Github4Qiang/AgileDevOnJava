package salary_payment;

/**
 * Created by Polylanger on 2017/4/9.
 */
public class DirectMethod implements PaymentMethod {

    private String bank;
    private int account;

    public DirectMethod(String bank, int account) {
        this.bank = bank;
        this.account = account;
    }

}
