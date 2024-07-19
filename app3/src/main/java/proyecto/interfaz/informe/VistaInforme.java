package proyecto.interfaz.informe;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import proyecto.componentes.Titulo;
import proyecto.datos.BaseDeDatos;
import proyecto.datos.Proyectos.Proyecto;

public class VistaInforme extends JPanel {
  BaseDeDatos db = new BaseDeDatos();

  public VistaInforme() {
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    setBackground(Color.WHITE);
    Titulo TITULO = new Titulo("Proyectos");
    add(TITULO);
    setName(VistaInforme.class.getSimpleName());
    Proyecto[] proyectos = db.proyectos.lista();
    JLabel titulo1 = new JLabel("Proyectos con financiamiento mayor a $250,000");
    titulo1.setFont(titulo1.getFont().deriveFont(25f));
    titulo1.setAlignmentX(LEFT_ALIGNMENT);
    add(titulo1);

    for (Proyecto proyecto : proyectos) {
      if (proyecto.monto_financiamiento >= 250000) {
        String texto = String.format(
            "Clave: %d      Nombre: %s      Monto: $%.2f\n",
            proyecto.clave,
            proyecto.nombre,
            proyecto.monto_financiamiento);
        JLabel textoLabel = new JLabel(texto);
        textoLabel.setAlignmentX(LEFT_ALIGNMENT);
        add(textoLabel);
      }
    }

    JLabel titulo2 = new JLabel("Area/diciplina de los proyectos");
    titulo2.setFont(titulo2.getFont().deriveFont(25f));
    add(titulo2);

    for (Proyecto proyecto2 : proyectos) {
      String texto = String.format(
          "Clave: %d      Nombre: %s      Area: %s\n",
          proyecto2.clave,
          proyecto2.nombre,
          proyecto2.area);

      JLabel textoLabel = new JLabel(texto);
      textoLabel.setAlignmentX(LEFT_ALIGNMENT);
      add(textoLabel);
    }

  }

  public JPanel getPanel() {
    return this;
  }
}
