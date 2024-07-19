package proyecto.componentes;

import java.awt.Font;
import javax.swing.JLabel;

public class Titulo extends JLabel {
  public Titulo(String texto) {
    super(texto);
    setFont(getFont().deriveFont(30f));
    setAlignmentX(CENTER_ALIGNMENT);
  }

  public Titulo size(int size) {
    setFont(getFont().deriveFont((float) size));
    return this;
  }

  public Titulo bold() {
    setFont(getFont().deriveFont(getFont().getStyle() | Font.BOLD));
    return this;
  }

  public Titulo alignCenter() {
    setHorizontalAlignment(JLabel.CENTER);
    return this;
  }

  public Titulo alignRight() {
    setHorizontalAlignment(JLabel.RIGHT);
    return this;
  }

  public Titulo alignLeft() {
    setHorizontalAlignment(JLabel.LEFT);
    return this;
  }
}
