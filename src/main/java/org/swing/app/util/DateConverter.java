package org.swing.app.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateConverter {

    private static final ZoneId DEFAULT_ZONE_ID = ZoneId.systemDefault();

    public static Date toDate(LocalDate localDate) {
        final LocalDateTime localDateTime = localDate.atStartOfDay();
        return DateConverter.toDate(localDateTime);
    }

    public static Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(DEFAULT_ZONE_ID).toInstant());
    }

    public static LocalDate toLocalDate(Date date) {
        final Instant instantOfDate = Instant.ofEpochMilli(date.getTime());
        return instantOfDate.atZone(DEFAULT_ZONE_ID).toLocalDate();
    }

    public static LocalDateTime toLocalDateTime(Date date) {
        final Instant instantOfDate = Instant.ofEpochMilli(date.getTime());
        return instantOfDate.atZone(DEFAULT_ZONE_ID).toLocalDateTime();
    }
}
