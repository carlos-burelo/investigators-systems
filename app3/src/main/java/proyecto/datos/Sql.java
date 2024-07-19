package proyecto.datos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Sql {

  private static Sql instanciaSingleton;
  private Connection conexion;

  private Sql() {
    try {
      Class.forName("org.sqlite.JDBC");
      conexion = DriverManager.getConnection("jdbc:sqlite:database.db");
      System.out.println("Conectado a la base de datos");
      ejecutarScript("script.sql");
    } catch (Exception e) {
      System.out.println("[Error] al conectar a la base de datos" + e.getMessage() + "\u001B[31m\n\n");
      e.printStackTrace();
    }
  }

  public static synchronized Sql obtenerInstancia() {
    if (instanciaSingleton == null) {
      instanciaSingleton = new Sql();
    }
    return instanciaSingleton;
  }

  public Connection getConexion() {
    return conexion;
  }

  public ResultSet ejecutarConsulta(String sql) {
    try {
      System.out.println(sql + "\u001B[35m\n\n");
      return conexion.createStatement().executeQuery(sql);

    } catch (Exception e) {
      System.out.println("Error al ejecutar la consulta" + e.getMessage() + "\u001B[31m");
      e.printStackTrace();
      return null;
    }
  }

  public void ejecutarConsultaSinResultado(String sql) {
    try {
      System.out.println(sql + "\u001B[35m\n\n");
      conexion.createStatement().execute(sql);
    } catch (Exception e) {
      System.out.println("[Error] al ejecutar la consulta" + e.getMessage() + "\u001B[31m");
      e.printStackTrace();
    }
  }

  public void ejecutarActualizacion(String sql) {
    try {
      System.out.println(sql + "\u001B[35m\n\n");
      conexion.createStatement().executeUpdate(sql);
    } catch (SQLException e) {
      System.out.println("[Error] al ejecutar la actualizacion" + e.getMessage() + "\u001B[31m");
    }
  }

  public void cerrarConexion() throws Exception {
    if (conexion != null && !conexion.isClosed()) {
      conexion.close();
    }
  }

  public PreparedStatement prepareStatement(String sql) {
    try {
      System.out.println(sql + "\u001B[35m\n\n");
      return conexion.prepareStatement(sql);
    } catch (Exception e) {
      System.out.println("[Error] al preparar la consulta" + e.getMessage() + "\u001B[31m");
      return null;
    }
  }

  private void ejecutarScript(String rutaScript) {
    try (BufferedReader br = new BufferedReader(new FileReader(rutaScript))) {
      StringBuilder script = new StringBuilder();
      String linea;
      while ((linea = br.readLine()) != null) {
        script.append(linea).append("\n");
      }
      try (Statement statement = conexion.createStatement()) {
        statement.executeUpdate(script.toString());
      } catch (SQLException e) {
        // System.out.println("[Error] al ejecutar el script SQL" + e.getMessage() +
        // "\u001B[31m");
        e.printStackTrace();
      }
    } catch (IOException e) {
      System.out.println("[Error] al leer el script SQL" + e.getMessage() + "\u001B[31m");
    }
  }
}