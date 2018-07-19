package com.xufree.learning.util;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class TransformUtil {
    private static final ZoneId defaultZoneId = ZoneId.systemDefault();

    public static Long localDateTimeToLong(LocalDateTime dateTime) {
        return dateTime == null ? null : dateTime.atZone(defaultZoneId).toInstant().toEpochMilli();
    }
    public static LocalDateTime longToLocalDateTime(Long time) {
        return time == null ? null : LocalDateTime.ofInstant(new Date(time).toInstant(), defaultZoneId);
    }
    public static BigDecimal floatToBigDecimal(Float number) {
        return number == null ? null : new BigDecimal(number.toString());
    }
    public static BigDecimal doubleToBigDecimal(Double number) {
        return number == null ? null : new BigDecimal(number.toString());
    }
    public static BigDecimal integerToBigDecimal(Integer number) {
        return number == null ? null : new BigDecimal(number.toString());
    }
}
