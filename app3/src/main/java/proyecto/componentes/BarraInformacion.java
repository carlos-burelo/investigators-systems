package proyecto.componentes;

import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import proyecto.constantes.Colores;

public class BarraInformacion extends JPanel {
  public BarraInformacion(String seccion, ActionListener eventoBoton) {
    setBackground(Colores.BLANCO);
    JLabel TEXTO_IZQUIERDO = new JLabel(seccion);
    TEXTO_IZQUIERDO.setFont(TEXTO_IZQUIERDO.getFont().deriveFont(15f));
    Boton btn = new Boton("➕ Nuevo", 150, 30);
    btn.addActionListener(eventoBoton);
    setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    add(TEXTO_IZQUIERDO);
    add(Box.createHorizontalGlue());
    add(btn);
  }
}
