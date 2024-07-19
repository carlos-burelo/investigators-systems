package proyecto.datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Proyectos extends Tabla {
  private Sql db;

  public Proyectos(Sql conexion) {
    db = conexion;
  }

  public static class Proyecto {
    public int clave;
    public String nombre;
    public String area;
    public float monto_financiamiento;
    public Date fecha_inicio;
    public Date fecha_fin;
    public float duracion_meses;
    public float terminacion_meses;
    public float avances_meses;

    public Proyecto(int clave, String nombre, String area, float monto_financiamiento,
        Date fecha_inicio, Date fecha_fin, float duracion_meses,
        float terminacion_meses, float avances_meses) {
      this.clave = clave;
      this.nombre = nombre;
      this.area = area;
      this.monto_financiamiento = monto_financiamiento;
      this.fecha_inicio = fecha_inicio;
      this.fecha_fin = fecha_fin;
      this.duracion_meses = duracion_meses;
      this.terminacion_meses = terminacion_meses;
      this.avances_meses = avances_meses;
    }

    // constructor para crear un proyecto
    public Proyecto(String nombre, String area, float monto_financiamiento,
        Date fecha_inicio, Date fecha_fin, float duracion_meses,
        float terminacion_meses, float avances_meses) {
      this.nombre = nombre;
      this.area = area;
      this.monto_financiamiento = monto_financiamiento;
      this.fecha_inicio = fecha_inicio;
      this.fecha_fin = fecha_fin;
      this.duracion_meses = duracion_meses;
      this.terminacion_meses = terminacion_meses;
      this.avances_meses = avances_meses;
    }
  }

  String OBTENER_TODOS_LOS_PROYECTOS = "SELECT * FROM proyectos";

  public Proyecto[] lista() {
    try {
      ResultSet resultado = db.ejecutarConsulta(OBTENER_TODOS_LOS_PROYECTOS);

      List<Proyecto> proyectos = new ArrayList<>();

      while (resultado.next()) {
        int clave = resultado.getInt("clave");
        String nombre = resultado.getString("nombre");
        String area = resultado.getString("area");
        float monto_financiamiento = resultado.getFloat("monto_financiamiento");
        Date fecha_inicio = resultado.getDate("fecha_inicio");
        Date fecha_fin = resultado.getDate("fecha_fin");
        float duracion_meses = resultado.getFloat("duracion_meses");
        float terminacion_meses = resultado.getFloat("terminacion_meses");
        float avances_meses = resultado.getFloat("avances_meses");

        proyectos.add(new Proyecto(clave, nombre, area, monto_financiamiento,
            fecha_inicio, fecha_fin, duracion_meses, terminacion_meses, avances_meses));
      }

      return proyectos.toArray(new Proyecto[0]);
    } catch (SQLException e) {
      e.printStackTrace();
      return new Proyecto[0];
    }
  }

  public void eliminar(int claveProyecto) {
    try {
      String consulta = "DELETE FROM proyectos WHERE clave = ?";
      PreparedStatement pstmt = db.prepareStatement(consulta);
      pstmt.setInt(1, claveProyecto);
      pstmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public Proyecto obtener(int id) {
    try {
      String consulta = "SELECT * FROM proyectos WHERE clave = ?";
      PreparedStatement pstmt = db.prepareStatement(consulta);
      pstmt.setInt(1, id);
      ResultSet resultado = pstmt.executeQuery();

      if (resultado.next()) {
        int clave = resultado.getInt("clave");
        String nombre = resultado.getString("nombre");
        String area = resultado.getString("area");
        float monto_financiamiento = resultado.getFloat("monto_financiamiento");
        Date fecha_inicio = resultado.getDate("fecha_inicio");
        Date fecha_fin = resultado.getDate("fecha_fin");
        float duracion_meses = resultado.getFloat("duracion_meses");
        float terminacion_meses = resultado.getFloat("terminacion_meses");
        float avances_meses = resultado.getFloat("avances_meses");

        return new Proyecto(clave, nombre, area, monto_financiamiento,
            fecha_inicio, fecha_fin, duracion_meses, terminacion_meses, avances_meses);
      } else {
        return null;
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
  }

  public int insertar(Proyecto proyecto) {
    try {
      String consulta = "INSERT INTO proyectos (nombre, area, monto_financiamiento, fecha_inicio, fecha_fin, duracion_meses, terminacion_meses, avances_meses) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
      PreparedStatement pstmt = db.prepareStatement(consulta);
      pstmt.setString(1, proyecto.nombre);
      pstmt.setString(2, proyecto.area);
      pstmt.setFloat(3, proyecto.monto_financiamiento);
      pstmt.setDate(4, proyecto.fecha_inicio);
      pstmt.setDate(5, proyecto.fecha_fin);
      pstmt.setFloat(6, proyecto.duracion_meses);
      pstmt.setFloat(7, proyecto.terminacion_meses);
      pstmt.setFloat(8, proyecto.avances_meses);
      pstmt.executeUpdate();

      ResultSet resultado = db.ejecutarConsulta("SELECT last_insert_rowid() AS id");
      resultado.next();
      return resultado.getInt("id");

    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  public void actualizar(Proyecto proyecto) {
    try {
      String consulta = "UPDATE proyectos SET nombre = ?, area = ?, monto_financiamiento = ?, fecha_inicio = ?, fecha_fin = ?, duracion_meses = ?, terminacion_meses = ?, avances_meses = ? WHERE clave = ?";
      PreparedStatement pstmt = db.prepareStatement(consulta);
      pstmt.setString(1, proyecto.nombre);
      pstmt.setString(2, proyecto.area);
      pstmt.setFloat(3, proyecto.monto_financiamiento);
      pstmt.setDate(4, proyecto.fecha_inicio);
      pstmt.setDate(5, proyecto.fecha_fin);
      pstmt.setFloat(6, proyecto.duracion_meses);
      pstmt.setFloat(7, proyecto.terminacion_meses);
      pstmt.setFloat(8, proyecto.avances_meses);
      pstmt.setInt(9, proyecto.clave);
      pstmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void actualizarAvance(int id, float avance, float terminacion) {
    try {
      String consulta = "UPDATE proyectos SET avances_meses = ?, terminacion_meses = ? WHERE clave = ?";
      PreparedStatement pstmt = db.prepareStatement(consulta);
      pstmt.setFloat(1, avance);
      pstmt.setFloat(2, terminacion);
      pstmt.setInt(3, id);
      pstmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
