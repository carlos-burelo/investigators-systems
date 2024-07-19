package proyecto.interfaz.proyectos;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import proyecto.componentes.BarraInformacion;
import proyecto.componentes.Titulo;
import proyecto.datos.BaseDeDatos;
import proyecto.datos.Proyectos.Proyecto;

public class VistaProyectos extends JPanel {

  BaseDeDatos db = new BaseDeDatos();

  private GridProyectos gridProyectos;

  public VistaProyectos() {
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    setBackground(Color.WHITE);
    Titulo TITULO = new Titulo("Proyectos");
    add(TITULO);
    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
    BarraInformacion barra = new BarraInformacion(
        "Lista de proyectos",
        e -> {
          new ModalCrearProyecto(frame, gridProyectos).mostrar();
        });
    add(barra);

    setName(VistaProyectos.class.getSimpleName());

    Proyecto[] proyectos = db.proyectos.lista();

    // if (proyectos.length == 0) {
    // add(new NoHayDatos("No hay proyectos registrados"));
    // } else {
    gridProyectos = new GridProyectos(proyectos);
    add(gridProyectos);
    // }

  }

  public JPanel getPanel() {
    return this;
  }
}
