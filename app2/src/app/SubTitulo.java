package app;

import javax.swing.JLabel;

public class SubTitulo extends JLabel {
  public SubTitulo(String text) {
    setText(text.toUpperCase());
    setFont(Valores.FUENTE_DE_SUBTITULOS);
    setForeground(Colores.NEGRO);
  }
}
