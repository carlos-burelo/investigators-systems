package proyecto.datos;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProyectoAvances {
  public static class Avance {
    /**
     * id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
     * proyecto_clave INTEGER,
     * fecha DATE,
     * descripcion TEXT,
     */

    public int id;
    public int proyecto_clave;
    public Date fecha;
    public String descripcion;

    public Avance(int id, int proyecto_clave, Date fecha, String descripcion) {
      this.id = id;
      this.proyecto_clave = proyecto_clave;
      this.fecha = fecha;
      this.descripcion = descripcion;
    }

  }

  public Sql db;

  public ProyectoAvances(Sql db) {
    this.db = db;
  }

  public Date buscarUltimoAvance(int proyecto_clave) {
    String query = "SELECT * FROM avance_proyectos WHERE proyecto_clave = ? ORDER BY fecha DESC LIMIT 1";

    try (PreparedStatement pstmt = db.prepareStatement(query)) {
      pstmt.setInt(1, proyecto_clave);
      ResultSet rs = pstmt.executeQuery();
      if (rs.next()) {
        return Date.valueOf(rs.getString("fecha"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public void registrarAvance(int proyecto_clave, String descripcion, Date fecha) {
    String query = "INSERT INTO avance_proyectos (proyecto_clave, descripcion, fecha) VALUES (?, ?, ?)";
    try (PreparedStatement pstmt = db.prepareStatement(query)) {
      pstmt.setInt(1, proyecto_clave);
      pstmt.setString(2, descripcion);
      pstmt.setString(3, fecha.toString());
      pstmt.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public Avance[] lista(int proyecto_clave) {
    System.out.println("proyecto clave: " + proyecto_clave);
    String query = "SELECT * FROM avance_proyectos WHERE proyecto_clave = ? ORDER BY fecha DESC";

    try (PreparedStatement pstmt = db.prepareStatement(query)) {
      pstmt.setInt(1, proyecto_clave);
      ResultSet rs = pstmt.executeQuery();
      List<Avance> avances = new ArrayList<Avance>();
      while (rs.next()) {
        int id = rs.getInt("id");
        int clave = rs.getInt("proyecto_clave");
        Date fecha = Date.valueOf(rs.getString("fecha"));
        String descripcion = rs.getString("descripcion");
        avances.add(new Avance(id, clave, fecha, descripcion));
      }
      return avances.toArray(new Avance[0]);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

}
