package salary_payment;

import java.util.Date;

/**
 * Created by Polylanger on 2017/4/9.
 */
public class WeeklySchedule implements PaymentSchedule {
    public boolean isPayDate(Date date) {
        return DateUtils.GetDayOfWeek(date) == 5;
    }
}
