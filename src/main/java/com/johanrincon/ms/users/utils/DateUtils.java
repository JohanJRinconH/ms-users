package com.johanrincon.ms.users.utils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

public final class DateUtils {

  private DateUtils() {}

  public static Date getDate() {
    LocalDateTime now = LocalDateTime.now();
    return Date.from(now.toInstant(ZoneOffset.UTC));
  }

  public static Date getDatePlusDays(int daysToAdd) {
    LocalDateTime now = LocalDateTime.now();
    return Date.from(now.plusDays(daysToAdd).toInstant(ZoneOffset.UTC));
  }
}
