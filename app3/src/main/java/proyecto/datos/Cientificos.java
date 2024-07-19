package proyecto.datos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

public class Cientificos extends Tabla {

  public Sql db;

  public Cientificos(Sql db) {
    this.db = db;
  }

  public static class Cientifico {
    public int id;
    public String nombre;
    public String telefono;
    public String email;
    public String grado_academico;
    public String categoria;

    public Cientifico(int id, String nombre, String telefono, String email, String grado_academico, String categoria) {
      this.id = id;
      this.nombre = nombre;
      this.telefono = telefono;
      this.email = email;
      this.grado_academico = grado_academico;
      this.categoria = categoria;
    }

    // constructor para crear un cientifico
    public Cientifico(String nombre, String telefono, String email, String grado_academico, String categoria) {
      this.nombre = nombre;
      this.telefono = telefono;
      this.email = email;
      this.grado_academico = grado_academico;
      this.categoria = categoria;
    }

  }

  public static DefaultTableModel createTableModel(Cientifico[] cientificos) {
    DefaultTableModel tableModel = new DefaultTableModel();
    String[] columnas = { "ID", "Nombre", "Email", "Categoria", "Telefono", "Grado", "Editar", "Eliminar" };
    tableModel.setColumnIdentifiers(columnas);

    for (Cientifico cientifico : cientificos) {
      Object[] fila = { cientifico.id, cientifico.nombre, cientifico.email, cientifico.categoria, cientifico.telefono,
          cientifico.grado_academico, "📝", "🗑️" };
      tableModel.addRow(fila);
    }
    return tableModel;
  }

  public Cientifico[] obtenerCientificos() {
    try {
      String consulta = "SELECT * FROM cientificos";
      ResultSet resultado = db.ejecutarConsulta(consulta);
      List<Cientifico> cientificos = new ArrayList<>();
      while (resultado.next()) {
        int id = resultado.getInt("id");
        String nombre = resultado.getString("nombre");
        String telefono = resultado.getString("telefono");
        String email = resultado.getString("email");
        String grado_academico = resultado.getString("grado_academico");
        String categoria = resultado.getString("categoria");

        cientificos.add(new Cientifico(id, nombre, telefono, email, grado_academico, categoria));
      }

      return cientificos.toArray(new Cientifico[0]);
    } catch (SQLException e) {
      e.printStackTrace();
      return new Cientifico[0];
    }
  }

  public void insertar(Cientifico cientifico) {
    String consulta = String.format(
        "INSERT INTO cientificos (nombre, telefono, email, grado_academico, categoria) VALUES ('%s', '%s', '%s', '%s', '%s')",
        cientifico.nombre, cientifico.telefono, cientifico.email, cientifico.grado_academico, cientifico.categoria);
    db.ejecutarActualizacion(consulta);
  }

  public void eliminar(Cientifico cientifico) {
    String consulta = String.format("DELETE FROM cientificos WHERE id = %d", cientifico.id);
    db.ejecutarActualizacion(consulta);
  }

  public void actualizar(Cientifico cientifico) {
    String consulta = String.format(
        "UPDATE cientificos SET nombre = '%s', telefono = '%s', email = '%s', grado_academico = '%s', categoria = '%s' WHERE id = %d",
        cientifico.nombre, cientifico.telefono, cientifico.email, cientifico.grado_academico, cientifico.categoria,
        cientifico.id);
    db.ejecutarActualizacion(consulta);
  }
}
