package salary_payment;

/**
 * Created by Polylanger on 2017/4/9.
 */
public class MailMethod implements PaymentMethod {

    private String address;

    public MailMethod(String address) {
        this.address = address;
    }
}
