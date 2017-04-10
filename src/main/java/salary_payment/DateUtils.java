package salary_payment;

import java.util.Date;

/**
 * Created by Polylanger on 2017/4/10.
 */
public class DateUtils {

    public static final String[] WEEKDAYS = {"Mon", "Tus", "Wes", "Thu", "Fri", "Sat", "Sun"};

    public static boolean IsLastDayOfMonth(Date date) {
        int m1 = date.getMonth();
        Date tomorrow = TomorrowOfDay(date);
        int m2 = tomorrow.getMonth();
        return m1 != m2;
    }

    public static Date TomorrowOfDay(Date date) {
        long dayMilliseconds = 24 * 60 * 60 * 1000;
        return new Date(date.getTime() + dayMilliseconds);
    }

    public static int GetDayOfWeek(Date date) {
        String dayOfWeek = date.toString().split(" ")[0];
        for (int i = 0; i < WEEKDAYS.length; i++) {
            if (WEEKDAYS[i].equals(dayOfWeek))
                return i + 1;
        }
        return 8;
    }

    public static Date CreateDate(int year, int month, int day) {
        return new Date(year - 1900, month - 1, day);
    }

}
