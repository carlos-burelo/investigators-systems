package proyecto.datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AsignacionCientificosProyectos {
  public static class CientificoProyecto {
    public int cientifico_id;
    public int proyecto_clave;
  }

  public Sql db;

  public AsignacionCientificosProyectos(Sql db) {
    this.db = db;
  }

  public void asignarCientificoProyecto(int cientifico_id, int proyecto_clave) {
    String query = "INSERT OR IGNORE INTO asignacion_cientificos_proyectos (cientifico_id, proyecto_clave) VALUES (?, ?)";
    try (PreparedStatement pstmt = db.prepareStatement(query)) {
      pstmt.setInt(1, cientifico_id);
      pstmt.setInt(2, proyecto_clave);
      pstmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void eliminarCientificoProyecto(int cientifico_id, int proyecto_clave) {
    String query = "DELETE FROM asignacion_cientificos_proyectos WHERE cientifico_id = ? AND proyecto_clave = ?";
    try (PreparedStatement pstmt = db.prepareStatement(query)) {
      pstmt.setInt(1, cientifico_id);
      pstmt.setInt(2, proyecto_clave);
      pstmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public Cientificos.Cientifico[] obtenerCientificosProyecto(int proyecto_clave) {
    String query = "SELECT * FROM cientificos INNER JOIN asignacion_cientificos_proyectos ON cientificos.id = asignacion_cientificos_proyectos.cientifico_id WHERE asignacion_cientificos_proyectos.proyecto_clave = ?";
    try (PreparedStatement pstmt = db.prepareStatement(query)) {
      pstmt.setInt(1, proyecto_clave);
      ResultSet resultado = pstmt.executeQuery();

      List<Cientificos.Cientifico> cientificos = new ArrayList<>();

      while (resultado.next()) {
        int id = resultado.getInt("id");
        String nombre = resultado.getString("nombre");
        String telefono = resultado.getString("telefono");
        String email = resultado.getString("email");
        String grado_academico = resultado.getString("grado_academico");
        String categoria = resultado.getString("categoria");

        cientificos.add(new Cientificos.Cientifico(id, nombre, telefono, email, grado_academico, categoria));
      }

      return cientificos.toArray(new Cientificos.Cientifico[0]);
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
  }
}
