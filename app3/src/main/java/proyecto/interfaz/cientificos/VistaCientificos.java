package proyecto.interfaz.cientificos;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import proyecto.componentes.BarraInformacion;
import proyecto.componentes.Titulo;
import proyecto.datos.BaseDeDatos;
import proyecto.datos.Cientificos.Cientifico;

public class VistaCientificos extends JPanel {

  private GridCientificos gridCientificos;

  public VistaCientificos() {
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    setBackground(Color.WHITE);
    Titulo TITULO = new Titulo("Científicos");
    add(TITULO);
    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
    BarraInformacion barra = new BarraInformacion(
        "Lista de cientificos",
        e -> {
          new ModalCrearCientifico(frame, gridCientificos).mostrar();
        });
    add(barra);

    BaseDeDatos db = new BaseDeDatos();
    Cientifico[] cientificos = db.cientificos.obtenerCientificos();

    gridCientificos = new GridCientificos(cientificos);

    add(gridCientificos);
    setName(VistaCientificos.class.getSimpleName());

  }

  public JPanel getPanel() {
    return this;
  }
}
