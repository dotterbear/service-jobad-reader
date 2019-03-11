package com.dotterbear.jobad.reader.utils;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Collection;
import java.util.Date;

public class DataUtils {

  public static boolean isEmpty(String str) {
    return str == null || str.isEmpty() || str.trim().isEmpty();
  }

  public static boolean isEmpty(Collection<?> collection) {
    return collection == null || collection.isEmpty();
  }

  public static OffsetDateTime buildUTCOffsetDateTime(Date date) {
    if (date == null)
      return null;
    return date.toInstant().atOffset(ZoneOffset.UTC);
  }

  public static Date buildDate(OffsetDateTime offsetDatetime) {
    return new Date(offsetDatetime.toInstant().toEpochMilli());
  }

}
