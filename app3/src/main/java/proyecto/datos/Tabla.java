package proyecto.datos;

public class Tabla {

  public String query(String query, Object... params) {
    return String.format(query, params);
  }

  public String query(String query) {
    return String.format(query);
  }

}
