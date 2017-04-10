package salary_payment;

import java.util.Date;

/**
 * Created by Polylanger on 2017/4/9.
 */
public interface Affiliation {

    ServiceCharge getServiceCharge(Date date);

    void addServiceCharge(ServiceCharge serviceCharge);

    double calculateDeductions(Paycheck pc);
}
