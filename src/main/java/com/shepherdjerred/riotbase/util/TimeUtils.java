package com.shepherdjerred.riotbase.util;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class TimeUtils {

    /**
     * Finds the difference in milliseconds between a given time and
     * the current time
     * @param cooldownStart A time in the past, in milliseconds
     * @return The difference between the given time and current time
     */
    public static long calculateRemainingCooldown(long cooldownStart) {
        Calendar calendar = Calendar.getInstance();
        calendar.getTime();
        return calendar.getTimeInMillis() - cooldownStart;
    }

    /**
     * Creates a string based on a given time
     * @param timeInMillis A time in milliseconds
     * @return A human readable string in the H:M:S format
     */
    public static String convertTimeInMillisToReadableString(Long timeInMillis) {

        long hours = TimeUnit.HOURS.convert(timeInMillis, TimeUnit.MILLISECONDS);
        timeInMillis -= TimeUnit.HOURS.toMillis(hours);

        long minutes = TimeUnit.MINUTES.convert(timeInMillis, TimeUnit.MILLISECONDS);
        timeInMillis -= TimeUnit.MINUTES.toMillis(minutes);

        long seconds = TimeUnit.SECONDS.convert(timeInMillis, TimeUnit.MILLISECONDS);

        if (hours < 1 && minutes < 1 && seconds < 1)
            seconds = 1L;

        String string = "";

        if (hours > 0) {
            string = string.concat(String.valueOf(hours) + " hour");
            if (hours > 1)
                string = string.concat("s");
        }

        if (hours > 0 && minutes > 0)
            string = string.concat(", ");

        if (minutes > 0) {
            string = string.concat(String.valueOf(minutes) + " minute");
            if (minutes > 1)
                string = string.concat("s");
        }

        if (minutes > 0 && seconds > 0 || minutes == 0 && seconds > 0 && hours > 0)
            string = string.concat(", ");

        if (seconds > 0) {
            string = string.concat(String.valueOf(seconds) + " second");
            if (seconds > 1)
                string = string.concat("s");
        }

        return string;
    }

}
