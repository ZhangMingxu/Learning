package com.xufree.learning.common.util;

import org.apache.commons.lang3.StringUtils;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class FormatUtil {
    private static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final DateTimeFormatter defaultDateTimeFormatter = DateTimeFormatter.ofPattern(DEFAULT_DATE_PATTERN);
    private static final DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");

    public static String formatTimeFromDate(Date time) {
        return formatTimeFromLong(time.getTime(), null);
    }
    public static String formatTimeFromLong(Long time) {
        return formatTimeFromLocalDateTime(TransformUtil.longToLocalDateTime(time), null);
    }
    public static String formatTimeFromLocalDateTime(LocalDateTime time) {
        return formatTimeFromLocalDateTime(time, null);
    }
    public static String formatTimeFromDate(Date time, String pattern) {
        return formatTimeFromLong(time.getTime(), pattern);
    }
    public static String formatTimeFromLong(Long time, String pattern) {
        return formatTimeFromLocalDateTime(TransformUtil.longToLocalDateTime(time), pattern);
    }
    public static String formatTimeFromLocalDateTime(LocalDateTime time, String pattern) {
        DateTimeFormatter formatter;
        if (StringUtils.isNotBlank(pattern)) {
            formatter = DateTimeFormatter.ofPattern(pattern);
        } else {
            formatter = defaultDateTimeFormatter;
        }
        return time.format(formatter);
    }
    /**
     * 保留两位小数并且每三位加一个,
     *
     * @param data
     * @return
     */
    public static String formatToSepara(Double data) {
        return decimalFormat.format(data);
    }
    public static String formatToSepara(Float data) {
        return formatToSepara(data.doubleValue());
    }
}
