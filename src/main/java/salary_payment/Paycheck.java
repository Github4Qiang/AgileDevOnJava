package salary_payment;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by Polylanger on 2017/4/10.
 */
public class Paycheck {

    private Date payDate;
    private double grossPay;
    private double deductions;
    private double netPay;
    private HashMap<String, String> fields;

    public Paycheck(Date payDate) {
        this.payDate = payDate;
        fields = new HashMap<String, String>();
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setGrossPay(double grossPay) {
        this.grossPay = grossPay;
    }

    public double getGrossPay() {
        return grossPay;
    }

    public void setDeductions(double deductions) {
        this.deductions = deductions;
    }

    public double getDeductions() {
        return deductions;
    }

    public void setNetPay(double netPay) {
        this.netPay = netPay;
    }

    public double getNetPay() {
        return netPay;
    }

    public String getField(String disposition) {
        return fields.get(disposition);
    }

    public void setMethod(String method) {
        fields.put("Disposition", method);
    }

    public Date getPayPeriodEndDate() {
        return payDate;
    }
}
