package hr.matvidako.ideamachine.utils;

import org.joda.time.DateTime;

public class DateUtils {

    public static long getStartOfTodayMilis() {
        return  new DateTime().withTime(0, 0, 0, 0).getMillis();
    }

    public static long getStartOfYesterdayMilis() {
        return  getStartOfToday().minusDays(1).getMillis();
    }

    public static long getStartOfDayMilis(DateTime date) {
        return date.withTime(0, 0, 0, 0).getMillis();
    }

    private static DateTime getStartOfToday() {
        return  new DateTime().withTime(0, 0, 0, 0);
    }

}
