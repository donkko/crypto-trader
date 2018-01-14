package com.donkko.crypto.util;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

public class TimeUtilsTest {

    @Test
    public void getLocalDateTime() {
        LocalDateTime result = TimeUtils.getLocalDateTime(1515401921000L);

        assertEquals(2018, result.getYear());
        assertEquals(Month.JANUARY, result.getMonth());
        assertEquals(8, result.getDayOfMonth());
        assertEquals(17, result.getHour());
        assertEquals(58, result.getMinute());
        assertEquals(41, result.getSecond());
    }

    @Test
    public void removeSeconds() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime result = TimeUtils.removeSeconds(now);

        assertEquals(0, result.getSecond());
        assertEquals(0, result.getNano());
    }

    @Test
    public void getRepresentativeTimeWindowStamp() {
        String a = "2016-11-09 10:30";
        String b = "2016-11-09 10:31";
        String c = "2016-11-09 11:00";
        String d = "2016-11-09 11:01";
        String e = "2016-11-09 11:29";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        LocalDateTime aDateTime = LocalDateTime.parse(a, formatter);
        LocalDateTime bDateTime = LocalDateTime.parse(b, formatter);
        LocalDateTime cDateTime = LocalDateTime.parse(c, formatter);
        LocalDateTime dDateTime = LocalDateTime.parse(d, formatter);
        LocalDateTime eDateTime = LocalDateTime.parse(e, formatter);

        System.out.println(TimeUtils.getRepresentativeTimeWindowStamp(aDateTime));
        System.out.println(TimeUtils.getRepresentativeTimeWindowStamp(bDateTime));
        System.out.println(TimeUtils.getRepresentativeTimeWindowStamp(cDateTime));
        System.out.println(TimeUtils.getRepresentativeTimeWindowStamp(dDateTime));
        System.out.println(TimeUtils.getRepresentativeTimeWindowStamp(eDateTime));
    }
}
