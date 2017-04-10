package salary_payment;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by Polylanger on 2017/4/9.
 */
public class UnionAffiliation implements Affiliation {
    private int memberId;

    private double dues;

    private HashMap<Long, ServiceCharge> itsServiceCharges;
    public UnionAffiliation(int memberId, double dues) {
        this.memberId = memberId;
        this.dues = dues;
        itsServiceCharges = new HashMap<Long, ServiceCharge>();
    }

    public ServiceCharge getServiceCharge(Date date) {
        return itsServiceCharges.get(date.getTime());
    }

    public void addServiceCharge(ServiceCharge serviceCharge) {
        itsServiceCharges.put(serviceCharge.getDate().getTime(), serviceCharge);
    }

    public int getMemberId() {
        return memberId;
    }

    public double getDues() {
        return dues;
    }
}
