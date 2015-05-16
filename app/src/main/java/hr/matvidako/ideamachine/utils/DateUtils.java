package hr.matvidako.ideamachine.utils;

import org.joda.time.DateTime;

public class DateUtils {

    public static long getStartOfTodayMilis() {
        return  new DateTime().withTime(0, 0, 0, 0).getMillis();
    }

}
