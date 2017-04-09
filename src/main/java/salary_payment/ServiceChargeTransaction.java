package salary_payment;

import java.util.Date;

/**
 * Created by Polylanger on 2017/4/9.
 */
public class ServiceChargeTransaction implements Transaction {
    private int memberId;
    private Date date;
    private double serviceCharge;

    public ServiceChargeTransaction(int memberId, Date date, double serviceCharge) {
        this.memberId = memberId;
        this.date = date;
        this.serviceCharge = serviceCharge;
    }

    public void execute() {
        Employee e = PayrollDatabase.GPayroolDatabase.getUnionMember(memberId);
        Affiliation af = e.getAffiliation();
        if (af instanceof UnionAffiliation) {
            ((UnionAffiliation) af).addServiceCharge(new ServiceCharge(date, serviceCharge));
        } else {
            throw new RuntimeException("Tried to add ServiceCharge to non-affiliation");
        }
    }
}
