package org.joelson.orienteering.util;

import java.time.LocalDate;

public class DateUtil {

    private DateUtil() throws InstantiationException {
        throw new InstantiationException("Should not be instantiated.");
    }

    public static LocalDate parseDate(String text) {
        return LocalDate.parse(text);
    }
}
