package app;

import javax.swing.JLabel;

public class Titulo extends JLabel {
  public Titulo(String texto) {
    setText(texto.toUpperCase());
    setFont(Valores.FUENTE_DE_TITULOS);
    setAlignmentX(CENTER_ALIGNMENT);
    setForeground(Colores.PRINCIPAL);
    setVisible(true);
  }
}
