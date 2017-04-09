package salary_payment;

import java.util.Date;

/**
 * Created by Polylanger on 2017/4/9.
 */
public class ServiceCharge {

    private Date date;
    private double amount;

    public ServiceCharge(Date date, double amount) {
        this.date = date;
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }
}
