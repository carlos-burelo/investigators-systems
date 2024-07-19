package app;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public final class TablaCientificosEnDB {
  String OBTENER_TODOS_LOS_CIENTIFICOS = "SELECT * FROM cientificos";
  String OBTENER_CIENTIFICOS_POR_ID = "SELECT * FROM cientificos WHERE id = ?";
  String INSERTAR_CIENTIFICO = "INSERT INTO cientificos (nombre, correo, telefono, categoria, grado_academico) VALUES (?, ?, ?, ?, ?)";
  String ACTUALIZAR_CIENTIFICO = "UPDATE cientificos SET nombre = ?, correo = ?, telefono = ?, categoria = ?, grado_academico = ? WHERE id = ?";
  String ELIMINAR_CIENTIFICO = "DELETE FROM cientificos WHERE id = ?";

  public ModeloCientifico[] obtener_todo_los_cientificos() {
    try {
      List<ModeloCientifico> cientificos = new ArrayList<ModeloCientifico>();
      ResultSet rs = Sqlite.select(OBTENER_TODOS_LOS_CIENTIFICOS);
      while (rs.next()) {
        int id = rs.getInt("id");
        String nombre = rs.getString("nombre");
        String correo = rs.getString("correo");
        String telefono = rs.getString("telefono");
        String categoria = rs.getString("categoria");
        String grado_academico = rs.getString("grado_academico");

        ModeloCientifico cientifico = new ModeloCientifico()
            .$nombre(nombre)
            .$correo(correo)
            .$telefono(telefono)
            .$categoria(categoria)
            .$grado_academico(grado_academico)
            .$id(id);
        cientificos.add(cientifico);
      }
      return cientificos.toArray(new ModeloCientifico[cientificos.size()]);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public ModeloCientifico obtener_cientifico_por_id(int id) {
    try {
      ResultSet rs = Sqlite.select(OBTENER_CIENTIFICOS_POR_ID, id);
      if (rs.next()) {
        String nombre = rs.getString("nombre");
        String correo = rs.getString("correo");
        String telefono = rs.getString("telefono");
        String categoria = rs.getString("categoria");
        String grado_academico = rs.getString("grado_academico");
        ModeloCientifico scientific = new ModeloCientifico()
            .$nombre(nombre)
            .$correo(correo)
            .$telefono(telefono)
            .$categoria(categoria)
            .$grado_academico(grado_academico)
            .$id(id);
        return scientific;
      }
      return null;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public int insertar_cientifico(ModeloCientifico cientifico) {
    try {
      return Sqlite.insert(INSERTAR_CIENTIFICO,
          cientifico.nombre,
          cientifico.correo,
          cientifico.telefono,
          cientifico.categoria,
          cientifico.grado_academico);
    } catch (Exception e) {
      e.printStackTrace();
      return 0;
    }
  }

  public int actualizar_cientifico(ModeloCientifico cientifico) {
    try {
      return Sqlite.update(ACTUALIZAR_CIENTIFICO,
          cientifico.nombre,
          cientifico.correo,
          cientifico.telefono,
          cientifico.categoria,
          cientifico.grado_academico,
          cientifico.id);
    } catch (Exception e) {
      e.printStackTrace();
      return 0;
    }
  }

  public int eliminar_cientifico(int id) {
    try {
      return Sqlite.delete(ELIMINAR_CIENTIFICO, id);
    } catch (Exception e) {
      e.printStackTrace();
      return 0;
    }
  }

}
