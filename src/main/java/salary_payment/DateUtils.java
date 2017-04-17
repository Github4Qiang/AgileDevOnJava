package salary_payment;

import java.util.Date;

/**
 * Created by Polylanger on 2017/4/10.
 */
public class DateUtils {

    public static final String[] WEEKDAYS = {"Mon", "Tus", "Wes", "Thu", "Fri", "Sat", "Sun"};
    public static long DAY_OF_MILLISECONDS = 24 * 60 * 60 * 1000;

    public static Date SomeDaysAgo(Date date, int Period) {
        for (int i = 0; i < Period; i++) {
            date = new Date(YesterdayOfDay(date).getTime());
        }
        return date;
    }

    public static boolean IsLastDayOfMonth(Date date) {
        int m1 = date.getMonth();
        Date tomorrow = TomorrowOfDay(date);
        int m2 = tomorrow.getMonth();
        return m1 != m2;
    }

    public static Date TomorrowOfDay(Date date) {
        return new Date(date.getTime() + DAY_OF_MILLISECONDS);
    }

    public static Date YesterdayOfDay(Date date) {
        return new Date(date.getTime() - DAY_OF_MILLISECONDS);
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

    public static boolean After(Date date1, Date date2) {
        return date1.getTime() > date2.getTime();
    }

    public static boolean Before(Date date1, Date date2) {
        return date1.getTime() < date2.getTime();
    }
}
