package app;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TablaProyectoCientificoEnDB {

  String VERIFICAR_SI_YA_ESTA_ASIGNADO = "SELECT 1 FROM proyecto_cientificos WHERE id_proyecto = ? AND id_cientifico = ?";
  String ASIGNAR_CIENTIFICO_AL_PROYECTO = "INSERT INTO proyecto_cientificos (id_proyecto, id_cientifico) VALUES (?, ?)";
  String REMOVER_CIENTIFICO_DEL_PROYECTO = "DELETE FROM proyecto_cientificos WHERE id_proyecto = ? AND id_cientifico = ?";
  String OBTENER_CIENTIFICO_POR_ID = "SELECT * FROM cientificos INNER JOIN proyecto_cientificos ON cientificos.id = proyecto_cientificos.id_cientifico WHERE proyecto_cientificos.id_proyecto = ?";
  String OBTENER_CIENTIFICOS_Y_PROYECTOS = "SELECT cientificos.nombre AS scientific_nombre, proyectos.nombre AS project_nombre FROM cientificos INNER JOIN proyecto_cientificos ON cientificos.id = proyecto_cientificos.id_cientifico INNER JOIN proyectos ON proyecto_cientificos.id_proyecto = proyectos.id";
  String GET_SCIENTIFICS_WITH_18_MONTHS = "SELECT s.id, s.nombre FROM cientificos s JOIN proyecto_cientificos ps ON s.id = ps.id_cientifico JOIN proyectos p ON ps.id_proyecto = p.id WHERE (p.fecha_fin - p.fecha_inicio) >= 18";
  String OBTENER_Y_SELECCIONAR_CIENTIFICOS_POR_ID = "SELECT s.id, s.nombre, s.telefono, s.correo, s.grado_academico, s.categoria, IFNULL((SELECT 1 FROM proyecto_cientificos ps WHERE ps.id_cientifico = s.id AND ps.id_proyecto = ?), 0) AS selected FROM cientificos s";

  public ModeloCientifico[] lista_cientificos_seleccionados_por_id(int projectId) {
    try {
      List<ModeloCientifico> cientificos = new ArrayList<ModeloCientifico>();
      ResultSet rs = Sqlite.select(OBTENER_Y_SELECCIONAR_CIENTIFICOS_POR_ID, projectId);
      while (rs.next()) {
        int id = rs.getInt("id");
        String nombre = rs.getString("nombre");
        String telefono = rs.getString("telefono");
        String correo = rs.getString("correo");
        String grado_academico = rs.getString("grado_academico");
        String categoria = rs.getString("categoria");
        boolean selected = rs.getBoolean("selected");
        ModeloCientifico scientific = new ModeloCientifico()
            .$nombre(nombre)
            .$id(id)
            .$telefono(telefono)
            .$correo(correo)
            .$grado_academico(grado_academico)
            .$categoria(categoria)
            .$selected(selected);
        cientificos.add(scientific);
      }
      return cientificos.toArray(new ModeloCientifico[cientificos.size()]);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public void asignar_cientifico_al_proyecto(int projectId, int scientificId) {
    try {
      ResultSet rs = Sqlite.select(VERIFICAR_SI_YA_ESTA_ASIGNADO, projectId, scientificId);
      if (!rs.next()) {
        Sqlite.insert(ASIGNAR_CIENTIFICO_AL_PROYECTO, projectId, scientificId);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void remover_cientifico_del_proyecto(int projectId, int scientificId) {
    try {
      Sqlite.delete(REMOVER_CIENTIFICO_DEL_PROYECTO, projectId, scientificId);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static class ModeloCientificoProyecto {
    public String scientific_nombre;
    public String project_nombre;

    public ModeloCientificoProyecto(String scientific_nombre, String project_nombre) {
      this.scientific_nombre = scientific_nombre;
      this.project_nombre = project_nombre;
    }
  }

  public ModeloCientificoProyecto[] obtener_cientificos_y_proyectos() {
    try {
      List<ModeloCientificoProyecto> cientificos = new ArrayList<ModeloCientificoProyecto>();
      ResultSet rs = Sqlite.select(OBTENER_CIENTIFICOS_Y_PROYECTOS);
      while (rs.next()) {
        String scientific_nombre = rs.getString("scientific_nombre");
        String project_nombre = rs.getString("project_nombre");
        ModeloCientificoProyecto scientific = new ModeloCientificoProyecto(scientific_nombre, project_nombre);
        cientificos.add(scientific);
      }
      return cientificos.toArray(new ModeloCientificoProyecto[cientificos.size()]);
    } catch (Exception e) {
      return null;
    }
  }

  public ModeloCientifico[] obtener_cientifico_por_id(int projectId) {
    try {
      List<ModeloCientifico> cientificos = new ArrayList<ModeloCientifico>();
      ResultSet rs = Sqlite.select(OBTENER_CIENTIFICO_POR_ID, projectId);
      while (rs.next()) {
        int id = rs.getInt("id");
        String nombre = rs.getString("nombre");
        ModeloCientifico scientific = new ModeloCientifico()
            .$nombre(nombre)
            .$id(id);
        cientificos.add(scientific);
      }
      return cientificos.toArray(new ModeloCientifico[cientificos.size()]);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public ModeloCientifico[] obtener_cientificos_con_18_meses() {
    try {
      List<ModeloCientifico> cientificos = new ArrayList<ModeloCientifico>();
      ResultSet rs = Sqlite.select(GET_SCIENTIFICS_WITH_18_MONTHS);
      while (rs.next()) {
        int id = rs.getInt("id");
        String nombre = rs.getString("nombre");
        ModeloCientifico scientific = new ModeloCientifico()
            .$nombre(nombre)
            .$id(id);
        cientificos.add(scientific);
      }
      return cientificos.toArray(new ModeloCientifico[cientificos.size()]);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
