package salary_payment;

import java.util.Date;

/**
 * Created by Polylanger on 2017/4/9.
 */
public class TimeCard {
    private Date date;

    private double hours;

    public TimeCard(Date date, double hours) {
        this.date = date;
        this.hours = hours;
    }

    public double getHours() {
        return hours;
    }

    public Date getDate() {
        return date;
    }
}
