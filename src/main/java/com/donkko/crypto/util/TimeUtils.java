package com.donkko.crypto.util;

import static com.donkko.crypto.constant.TradingConstants.TIMEWINDOW_MINUTES;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;

import org.apache.commons.lang3.ArrayUtils;

public final class TimeUtils {

    private TimeUtils() {}

    public static LocalDateTime getLocalDateTimeWithoutSeconds(long timestamp) {
        return removeSeconds(getLocalDateTime(timestamp));
    }

    public static LocalDateTime getLocalDateTime(long timestamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
    }

    public static LocalDateTime removeSeconds(LocalDateTime localDateTime) {
        return localDateTime.withSecond(0).withNano(0);
    }

    public static LocalDateTime getRepresentativeTimeWindowStamp(LocalDateTime localDateTime) {

        LocalDateTime dateTimeWithoutMinutesAndSeconds = localDateTime.withMinute(0).withSecond(0).withNano(0);

        // TimeWindow의 크기는 반드시 60분으로 나누어 떨어져야 함
        int numberOfWindowsInOneHour = 60 / TIMEWINDOW_MINUTES;

        LocalDateTime[] timeWindowStamps = new LocalDateTime[numberOfWindowsInOneHour];
        for (int i = 0; i < timeWindowStamps.length; i++) {
            timeWindowStamps[i] = dateTimeWithoutMinutesAndSeconds.plusMinutes(i * TIMEWINDOW_MINUTES);
        }

        // reverse!
        ArrayUtils.reverse(timeWindowStamps);
        for (LocalDateTime elem : timeWindowStamps) {
            if (elem.isEqual(localDateTime) || elem.isBefore(localDateTime)) {
                return elem;
            }
        }

        throw new RuntimeException("getRepresentativeTimeWindowStamp error!!!");
    }
}
