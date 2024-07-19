package Utils;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

public class Timer {
  public static float calculateDurationInMonths(Date start, Date end) {
    LocalDate startDate = Instant.ofEpochMilli(start.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    LocalDate endDate = Instant.ofEpochMilli(end.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    long months = ChronoUnit.MONTHS.between(startDate, endDate);
    return months;
  }

  public static float calculateTerminationInMonths(Date start, Date end, float duration) {
    LocalDate startDate = Instant.ofEpochMilli(start.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    LocalDate now = LocalDate.now();
    long months = ChronoUnit.MONTHS.between(startDate, now);
    return months / duration;
  }
}
