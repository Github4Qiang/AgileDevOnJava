package salary_payment;

import java.util.Date;

/**
 * Created by Polylanger on 2017/4/9.
 */
public class SalesReceipt {

    private Date date;
    private double amount;

    public SalesReceipt(Date date, double amount) {
        this.date = date;
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }
}
