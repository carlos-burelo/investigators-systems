package DB;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// SQLite database
public class Sqlite {
  private static Connection connection;

  static {
    try {
      Class.forName("org.sqlite.JDBC");
      connection = DriverManager.getConnection("jdbc:sqlite:database.db");
      try (BufferedReader br = new BufferedReader(new FileReader("script.sql"))) {
        StringBuilder script = new StringBuilder();
        String linea;
        while ((linea = br.readLine()) != null) {
          script.append(linea).append("\n");
        }
        try (Statement statement = connection.createStatement()) {
          statement.executeUpdate(script.toString());
        } catch (SQLException e) {
          e.printStackTrace();
        }
      } catch (IOException e) {
        System.out.println("[Error] al leer el script SQL" + e.getMessage() + "\u001B[31m");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static ResultSet select(String query, Object... args) throws SQLException {
    PreparedStatement statement = connection.prepareStatement(query);
    for (int i = 0; i < args.length; i++) {
      statement.setObject(i + 1, args[i]);
    }
    return statement.executeQuery();
  }

  public static ResultSet select(String query) throws SQLException {
    return connection.createStatement().executeQuery(query);
  }

  public static int insert(String query, Object... args) throws SQLException {
    PreparedStatement statement = connection.prepareStatement(query);
    for (int i = 0; i < args.length; i++) {
      statement.setObject(i + 1, args[i]);
    }
    return statement.executeUpdate();
  }

  public static int update(String query, Object... args) throws SQLException {
    PreparedStatement statement = connection.prepareStatement(query);
    for (int i = 0; i < args.length; i++) {
      statement.setObject(i + 1, args[i]);
    }
    return statement.executeUpdate();
  }

  public static int delete(String query, Object... args) throws SQLException {
    PreparedStatement statement = connection.prepareStatement(query);
    for (int i = 0; i < args.length; i++) {
      statement.setObject(i + 1, args[i]);
    }
    return statement.executeUpdate();
  }
}
