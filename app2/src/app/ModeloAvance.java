package app;

import java.sql.Date;

public final class ModeloAvance {
  public int id;
  public int id_proyecto;
  public String texto;
  public Date fecha;

  public ModeloAvance $id_proyecto(int id_proyecto) {
    this.id_proyecto = id_proyecto;
    return this;
  }

  public ModeloAvance $texto(String texto) {
    this.texto = texto;
    return this;
  }

  public ModeloAvance $fecha(Date fecha) {
    this.fecha = fecha;
    return this;
  }

  public ModeloAvance $id(int id) {
    this.id = id;
    return this;
  }
}
