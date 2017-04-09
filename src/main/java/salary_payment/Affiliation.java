package salary_payment;

import java.util.Date;

/**
 * Created by Polylanger on 2017/4/9.
 */
public abstract class Affiliation {

    public abstract ServiceCharge getServiceCharge(Date date);

    public abstract void addServiceCharge(ServiceCharge serviceCharge);

}
