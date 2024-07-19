package app;

import java.sql.Date;

import javax.swing.table.DefaultTableModel;

public class ModeloProyecto {
  public int id;
  public String nombre;
  public String area;
  public Date fecha_inicio;
  public Date fecha_fin;
  public float duracion;
  public float avance;
  public float terminacion;
  public float monto;

  public ModeloProyecto $nombre(String nombre) {
    this.nombre = nombre;
    return this;
  }

  public ModeloProyecto $area(String area) {
    this.area = area;
    return this;
  }

  public ModeloProyecto $fecha_inicio(Date fecha_inicio) {
    this.fecha_inicio = fecha_inicio;
    return this;
  }

  public ModeloProyecto $fecha_fin(Date fecha_fin) {
    this.fecha_fin = fecha_fin;
    return this;
  }

  public ModeloProyecto $duracion(float duracion) {
    this.duracion = duracion;
    return this;
  }

  public ModeloProyecto $avance(float avance) {
    this.avance = avance;
    return this;
  }

  public ModeloProyecto $terminacion(float terminacion) {
    this.terminacion = terminacion;
    return this;
  }

  public ModeloProyecto $monto(float monto) {
    this.monto = monto;
    return this;
  }

  public ModeloProyecto $id(int id) {
    this.id = id;
    return this;
  }

  public static DefaultTableModel createTableModel(ModeloProyecto[] proyectos) {
    DefaultTableModel tableModel = new DefaultTableModel();
    String[] columnas = { "ID", "Nombre", "Area", "Monto", "Avance", "Terminacion", "Avance", "#", "X" };
    tableModel.setColumnIdentifiers(columnas);

    for (ModeloProyecto proyecto : proyectos) {
      Object[] fila = { proyecto.id, proyecto.nombre, proyecto.area, String.format("%.0f", proyecto.monto),
          proyecto.avance,
          proyecto.terminacion, Math.floor(proyecto.avance), "#", "X" };
      tableModel.addRow(fila);
    }
    return tableModel;
  }

}
