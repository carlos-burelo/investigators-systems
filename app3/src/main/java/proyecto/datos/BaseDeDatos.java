package proyecto.datos;

public class BaseDeDatos {
  Sql instancia = Sql.obtenerInstancia();
  public Cientificos cientificos = new Cientificos(instancia);
  public Proyectos proyectos = new Proyectos(instancia);
  public AsignacionCientificosProyectos asignacionCientificosProyectos = new AsignacionCientificosProyectos(instancia);
  public ProyectoAvances avances = new ProyectoAvances(instancia);
}
