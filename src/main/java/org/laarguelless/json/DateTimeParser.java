package org.laarguelless.json;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

class DateTimeParser {
    public static OffsetDateTime parse(String rawDateTime) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ISO_DATE_TIME;
        return OffsetDateTime.parse(rawDateTime, timeFormatter)
                .withOffsetSameInstant(ZoneOffset.UTC);
    }

    static String format(OffsetDateTime dateTime) {
        return dateTime.withOffsetSameInstant(ZoneOffset.UTC).toString();
    }
}
