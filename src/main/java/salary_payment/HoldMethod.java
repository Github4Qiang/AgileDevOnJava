package salary_payment;

/**
 * Created by Polylanger on 2017/4/9.
 */
public class HoldMethod implements PaymentMethod {

    public void pay(Paycheck pc) {
        pc.setMethod("Hold");
    }

}
