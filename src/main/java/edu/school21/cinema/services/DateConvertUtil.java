package edu.school21.cinema.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class DateConvertUtil {
    public static String getTime(long millis) {
        millis -= TimeUnit.DAYS.toMillis(TimeUnit.MILLISECONDS.toDays(millis));
        long hours = TimeUnit.MILLISECONDS.toHours(millis);
        millis -= TimeUnit.HOURS.toMillis(hours);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
        return String.format("%02d: %02d", hours, minutes);
    }

    public static String getDate(long milliseconds) {
        DateFormat formatter = new SimpleDateFormat("MMMM dd, yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliseconds);
        return formatter.format(calendar.getTime());
    }
}
