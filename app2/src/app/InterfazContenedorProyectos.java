package app;

import app.PanelProyectos.VistaProyectos;

public class InterfazContenedorProyectos extends Vista {

  public InterfazContenedorProyectos() {
    Cabecera _Cabecera = new Cabecera("TABLA DE PROYECTOS", e -> {
      VistaProyectos.mostrar(this, new InterfazNuevoProyecto());
    });
    add(_Cabecera);
    add(new InterfazTablaProyectos());
  }
}
