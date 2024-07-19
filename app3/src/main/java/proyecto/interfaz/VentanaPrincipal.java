package proyecto.interfaz;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import proyecto.interfaz.cientificos.VistaCientificos;
import proyecto.interfaz.informe.VistaInforme;
import proyecto.interfaz.proyectos.VistaProyectos;

public class VentanaPrincipal extends JFrame {

  private JSplitPane ventanaDividida;
  private Menu panelSidebar;
  private JPanel contenido;
  private VistaProyectos vistaProyectos = new VistaProyectos();
  private VistaCientificos vistaCientificos = new VistaCientificos();
  private VistaInforme vistaInforme = new VistaInforme();

  public VentanaPrincipal() {
    super("Proyecto");
    crearComponentes();
    configurarVentana();
    setBackground(Color.WHITE);
  }

  private void crearComponentes() {
    panelSidebar = new Menu(
        e -> mostrarPanel(vistaProyectos),
        e -> mostrarPanel(vistaCientificos),
        e -> mostrarPanel(vistaInforme));
    contenido = new JPanel();
    contenido.setLayout(new CardLayout());
    contenido.add(vistaProyectos.getPanel(), vistaProyectos.getName());
    contenido.add(vistaCientificos.getPanel(), vistaCientificos.getName());
    contenido.add(vistaInforme.getPanel(), vistaInforme.getName());
    ventanaDividida = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelSidebar, contenido);
    ventanaDividida.setDividerLocation(90); // Posición del divisor
    ventanaDividida.setEnabled(false); // Bloquea el ajuste del JSplitPane
  }

  private void configurarVentana() {
    add(ventanaDividida);
    setSize(800, 600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }

  private void mostrarPanel(JPanel panel) {
    CardLayout cardLayout = (CardLayout) contenido.getLayout();
    cardLayout.show(contenido, panel.getName());
  }

  public static void main(String[] args) {
    new VentanaPrincipal();
  }
}