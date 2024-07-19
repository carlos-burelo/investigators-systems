package proyecto.componentes;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import proyecto.constantes.Colores;

public class NoHayDatos extends JPanel {
  public NoHayDatos(String texto) {
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    setBackground(Colores.BLANCO);
    JLabel EMOJI = new JLabel("😕\n");
    EMOJI.setAlignmentX(CENTER_ALIGNMENT);
    EMOJI.setFont(EMOJI.getFont().deriveFont(50f));
    add(EMOJI);
    JLabel TEXTO = new JLabel(texto);
    TEXTO.setAlignmentX(CENTER_ALIGNMENT);
    TEXTO.setFont(TEXTO.getFont().deriveFont(20f));
    add(TEXTO);
  }
}
