package salary_payment;

import java.util.Date;

/**
 * Created by Polylanger on 2017/4/9.
 */
public class NoAffiliation implements Affiliation {

    public ServiceCharge getServiceCharge(Date date) {
        return null;
    }

    public void addServiceCharge(ServiceCharge serviceCharge) {

    }

    public double calculateDeductions(Paycheck pc) {
        return 0;
    }
}
