package salary_payment;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by Polylanger on 2017/4/9.
 */
public class HourlyClassification implements PaymentClassification {

    private HashMap<Long, TimeCard> itsTimeCards;
    private double hourlyRate;

    public HourlyClassification(double hourlyRate) {
        this.hourlyRate = hourlyRate;
        itsTimeCards = new HashMap<Long, TimeCard>();
    }

    public TimeCard getTimeCard(Date date) {
        return itsTimeCards.get(date.getTime());
    }

    public void addTimeCard(TimeCard timeCard) {
        itsTimeCards.put(timeCard.getDate().getTime(), timeCard);
    }

    public double calculatePay(Paycheck pc) {
        double totalPay = 0.0;
        Date payPeriod = pc.getPayDate();
        for (Long payDate : itsTimeCards.keySet()) {
            TimeCard tc = itsTimeCards.get(payDate);
            if (isInPayPeriod(tc, payPeriod)) {
                totalPay = calculatePayForTimeCard(tc);
            }
        }
        return totalPay;
    }

    private double calculatePayForTimeCard(TimeCard tc) {
        double hours = tc.getHours();
        double overtime = Math.max(hours - 8.0, 0.0);
        double worktime = Math.min(hours, 8.0);
        return worktime * hourlyRate + overtime * 1.5 * hourlyRate;
    }

    public boolean isInPayPeriod(TimeCard tc, final Date payPeriod) {
        Date payPeriodEndDate = payPeriod;
        Date payPeriodStartDate = DateUtils.SomeDaysAgo(payPeriod, 7);
        Date timeCardDate = tc.getDate();
        return (DateUtils.Before(payPeriodStartDate, timeCardDate) && DateUtils.After(payPeriodEndDate, timeCardDate));
    }
}
