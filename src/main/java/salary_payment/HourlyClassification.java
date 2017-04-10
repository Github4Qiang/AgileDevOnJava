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
        double pay = 0.0;
        for (Long payDate : itsTimeCards.keySet()) {
            double hours = itsTimeCards.get(payDate).getHours();
            double overtime = Math.max(hours - 8.0, 0.0);
            double worktime = Math.min(hours, 8.0);
            pay += worktime * hourlyRate + overtime * 1.5 * hourlyRate;
        }
        return pay;
    }
}
