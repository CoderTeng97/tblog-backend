package com.tg.blog.base.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateUtils {

    public static Date transformToDate(LocalDateTime dateTime){
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = dateTime.atZone(zone).toInstant();
        return Date.from(instant);
    }
}
