package app;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class Vista extends JPanel {
  public Vista(String titulo) {
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    setName(Vista.class.getSimpleName());
    setBackground(Colores.BASE);
    setAlignmentX(CENTER_ALIGNMENT);
    setAlignmentY(CENTER_ALIGNMENT);
    add(new Titulo(titulo));
  }

  public Vista() {
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    setName(Vista.class.getSimpleName());
    setBackground(Colores.BASE);
    setAlignmentX(CENTER_ALIGNMENT);
    setAlignmentY(CENTER_ALIGNMENT);
  }
}
