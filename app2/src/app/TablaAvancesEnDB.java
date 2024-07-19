package app;

import java.util.List;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.ResultSet;

public class TablaAvancesEnDB {
  String OBTENER_AVANCES_POR_ID_DEL_PROYECTO = "SELECT * FROM avances WHERE id_proyecto = ?";
  String INSERTAR_AVANCE = "INSERT INTO avances (id_proyecto, fecha, texto) VALUES (?, ?, ?)";
  String OBTENER_EL_ULTIMO_AVANCE = "SELECT * FROM avances WHERE id_proyecto = ? ORDER BY id DESC LIMIT 1";

  public ModeloAvance[] obtener_avances_por_id_del_proyecto(int projectId) {
    try {
      List<ModeloAvance> avances = new ArrayList<ModeloAvance>();
      ResultSet rs = Sqlite.select(OBTENER_AVANCES_POR_ID_DEL_PROYECTO, projectId);
      while (rs.next()) {
        int id = rs.getInt("id");
        int id_proyecto = rs.getInt("id_proyecto");
        Date fecha = rs.getDate("fecha");
        String texto = rs.getString("texto");
        ModeloAvance avance = new ModeloAvance()
            .$id_proyecto(id_proyecto)
            .$fecha(fecha)
            .$id(id)
            .$texto(texto);
        avances.add(avance);
      }
      return avances.toArray(new ModeloAvance[avances.size()]);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public ModeloAvance obtener_el_ultimo_avance_del_proyecto(int id_proyecto) {
    try {
      ResultSet rs = Sqlite.select(OBTENER_EL_ULTIMO_AVANCE, id_proyecto);
      if (rs.next()) {
        int id = rs.getInt("id");
        Date fecha = rs.getDate("fecha");
        String texto = rs.getString("texto");
        ModeloAvance avance = new ModeloAvance()
            .$id_proyecto(id_proyecto)
            .$fecha(fecha)
            .$id(id)
            .$texto(texto);
        return avance;
      } else {
        return null;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public boolean insertar_avance(ModeloAvance avance) {
    try {
      Sqlite.insert(INSERTAR_AVANCE, avance.id_proyecto, avance.fecha, avance.texto);
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }
}
