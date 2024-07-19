package app;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

public class Temporizador {
  public static float calculateDurationInMonths(Date fecha_inicio, Date fecha_fin) {
    LocalDate startDate = Instant.ofEpochMilli(fecha_inicio.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    LocalDate endDate = Instant.ofEpochMilli(fecha_fin.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    long days = ChronoUnit.DAYS.between(startDate, endDate);
    float months = days / 30.44f;
    float roundedMonths = Math.round(months * 100.0f) / 100.0f;
    return roundedMonths;
  }

  public static float calculateTerminationInMonths(Date fecha_inicio, Date fecha_fin, float duracion) {
    LocalDate startDate = Instant.ofEpochMilli(fecha_inicio.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    LocalDate now = LocalDate.now();
    long months = ChronoUnit.MONTHS.between(startDate, now);
    return months / duracion;
  }
}
