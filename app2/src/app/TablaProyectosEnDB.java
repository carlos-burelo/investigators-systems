package app;

import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import java.sql.ResultSet;

public class TablaProyectosEnDB {
  String OBTENER_TODOS_LOS_PROYECTOS = "SELECT * FROM proyectos";
  String OBTENER_AVANCES_Y_AREAS = "SELECT avance, area, nombre FROM proyectos";
  String OBTENER_FINANCIADOS_CON_250K_O_MAS = "SELECT * FROM proyectos WHERE monto = 250000 OR monto > 250000";
  String OBTENER_PROYECTO_POR_ID = "SELECT * FROM proyectos WHERE id = ?";
  String INSERTAR_PROYECTO = "INSERT INTO proyectos (nombre, area, fecha_inicio, fecha_fin, duracion, avance, terminacion, monto) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
  String ACTUALIZAR_PROYECTO = "UPDATE proyectos SET nombre = ?, area = ?, fecha_inicio = ?, fecha_fin = ?, duracion = ?, avance = ?, terminacion = ?, monto = ? WHERE id = ?";
  String ELIMINAR_PROYECTO = "DELETE FROM proyectos WHERE id = ?";
  String OBTENER_ULTIMO_PROYECTO_INSERTADO = "SELECT * FROM proyectos ORDER BY id DESC LIMIT 1";

  public ModeloProyecto[] obtener_todos_los_proyectos() {
    try {
      List<ModeloProyecto> proyectos = new ArrayList<ModeloProyecto>();
      ResultSet rs = Sqlite.select(OBTENER_TODOS_LOS_PROYECTOS);
      while (rs.next()) {
        int id = rs.getInt("id");
        String nombre = rs.getString("nombre");
        String area = rs.getString("area");
        Date fecha_inicio = rs.getDate("fecha_inicio");
        Date fecha_fin = rs.getDate("fecha_fin");
        float duracion = rs.getFloat("duracion");
        float avance = rs.getFloat("avance");
        float terminacion = rs.getFloat("terminacion");
        float monto = rs.getFloat("monto");

        ModeloProyecto project = new ModeloProyecto()
            .$nombre(nombre)
            .$area(area)
            .$fecha_inicio(fecha_inicio)
            .$fecha_fin(fecha_fin)
            .$duracion(duracion)
            .$avance(avance)
            .$terminacion(terminacion)
            .$monto(monto)
            .$id(id);
        proyectos.add(project);
      }
      return proyectos.toArray(new ModeloProyecto[proyectos.size()]);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public ModeloProyecto[] obtener_proyecto_financiados_con_mas_de_250k() {
    try {
      List<ModeloProyecto> proyectos = new ArrayList<ModeloProyecto>();
      ResultSet rs = Sqlite.select(OBTENER_FINANCIADOS_CON_250K_O_MAS);
      while (rs.next()) {
        int id = rs.getInt("id");
        String nombre = rs.getString("nombre");
        String area = rs.getString("area");
        Date fecha_inicio = rs.getDate("fecha_inicio");
        Date fecha_fin = rs.getDate("fecha_fin");
        float duracion = rs.getFloat("duracion");
        float avance = rs.getFloat("avance");
        float terminacion = rs.getFloat("terminacion");
        float monto = rs.getFloat("monto");

        ModeloProyecto project = new ModeloProyecto()
            .$nombre(nombre)
            .$area(area)
            .$fecha_inicio(fecha_inicio)
            .$fecha_fin(fecha_fin)
            .$duracion(duracion)
            .$avance(avance)
            .$terminacion(terminacion)
            .$monto(monto)
            .$id(id);
        proyectos.add(project);
      }
      return proyectos.toArray(new ModeloProyecto[proyectos.size()]);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public ModeloProyecto[] obtener_avances_y_proyectos() {
    try {
      List<ModeloProyecto> proyectos = new ArrayList<ModeloProyecto>();
      ResultSet rs = Sqlite.select(OBTENER_AVANCES_Y_AREAS);
      while (rs.next()) {
        String area = rs.getString("area");
        String nombre = rs.getString("nombre");
        float avance = rs.getFloat("avance");

        ModeloProyecto project = new ModeloProyecto()
            .$area(area)
            .$avance(avance)
            .$nombre(nombre);
        proyectos.add(project);
      }
      return proyectos.toArray(new ModeloProyecto[proyectos.size()]);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public ModeloProyecto obtener_proyecto_por_id(int id) {
    try {
      ResultSet rs = Sqlite.select(OBTENER_PROYECTO_POR_ID, id);
      if (rs.next()) {
        String nombre = rs.getString("nombre");
        String area = rs.getString("area");
        Date fecha_inicio = rs.getDate("fecha_inicio");
        Date fecha_fin = rs.getDate("fecha_fin");
        float duracion = rs.getFloat("duracion");
        float avance = rs.getFloat("avance");
        float terminacion = rs.getFloat("terminacion");
        float monto = rs.getFloat("monto");
        ModeloProyecto project = new ModeloProyecto()
            .$nombre(nombre)
            .$area(area)
            .$fecha_inicio(fecha_inicio)
            .$fecha_fin(fecha_fin)
            .$duracion(duracion)
            .$avance(avance)
            .$terminacion(terminacion)
            .$monto(monto)
            .$id(id);
        return project;
      }
      return null;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public ModeloProyecto obtener_ultimo_proyecto_insertaModeloProyecto() {
    try {
      ResultSet rs = Sqlite.select(OBTENER_ULTIMO_PROYECTO_INSERTADO);
      if (rs.next()) {
        int id = rs.getInt("id");
        String nombre = rs.getString("nombre");
        String area = rs.getString("area");
        Date fecha_inicio = rs.getDate("fecha_inicio");
        Date fecha_fin = rs.getDate("fecha_fin");
        float duracion = rs.getFloat("duracion");
        float avance = rs.getFloat("avance");
        float terminacion = rs.getFloat("terminacion");
        float monto = rs.getFloat("monto");
        ModeloProyecto project = new ModeloProyecto()
            .$nombre(nombre)
            .$area(area)
            .$fecha_inicio(fecha_inicio)
            .$fecha_fin(fecha_fin)
            .$duracion(duracion)
            .$avance(avance)
            .$terminacion(terminacion)
            .$monto(monto)
            .$id(id);
        return project;
      }
      return null;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public int insertar_proyecto(ModeloProyecto proyecto) {
    try {
      return Sqlite.insert(INSERTAR_PROYECTO,
          proyecto.nombre,
          proyecto.area,
          proyecto.fecha_inicio,
          proyecto.fecha_fin,
          proyecto.duracion,
          proyecto.avance,
          proyecto.terminacion,
          proyecto.monto);
    } catch (Exception e) {
      e.printStackTrace();
      return 0;
    }
  }

  public int actualizar_proyecto(ModeloProyecto proyecto) {
    try {
      return Sqlite.update(ACTUALIZAR_PROYECTO,
          proyecto.nombre,
          proyecto.area,
          proyecto.fecha_inicio,
          proyecto.fecha_fin,
          proyecto.duracion,
          proyecto.avance,
          proyecto.terminacion,
          proyecto.monto,
          proyecto.id);
    } catch (Exception e) {
      e.printStackTrace();
      return 0;
    }
  }

  public int deleteProject(int id) {
    try {
      return Sqlite.delete(ELIMINAR_PROYECTO, id);
    } catch (Exception e) {
      e.printStackTrace();
      return 0;
    }
  }
}
