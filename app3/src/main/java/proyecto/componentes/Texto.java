package proyecto.componentes;

import java.awt.Font;

import javax.swing.JLabel;

public class Texto extends JLabel {
  public Texto(String texto) {
    super(texto);
    setFont(getFont().deriveFont(20f));
  }

  public Texto size(int size) {
    setFont(getFont().deriveFont((float) size));
    return this;
  }

  public Texto bold() {
    setFont(getFont().deriveFont(getFont().getStyle() | Font.BOLD));
    return this;
  }

  public Texto alignCenter() {
    setHorizontalAlignment(JLabel.CENTER);
    return this;
  }

  public Texto alignRight() {
    setHorizontalAlignment(JLabel.RIGHT);
    return this;
  }

  public Texto alignLeft() {
    setHorizontalAlignment(JLabel.LEFT);
    return this;
  }
}
