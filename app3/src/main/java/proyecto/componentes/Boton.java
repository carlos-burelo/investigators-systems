package proyecto.componentes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import proyecto.constantes.Colores;

public class Boton extends JButton {
  public Boton(String texto, int ancho, int alto) {
    super(texto);
    setBackground(Colores.COLOR_AZUL);
    setFont(new Font("Segoe UI Emoji", Font.PLAIN, 20));
    setForeground(new Color(255, 255, 255));
    setPreferredSize(new Dimension(ancho, alto));
    setBorderPainted(false);
    setFocusPainted(false);

    // events

    // :hover
    addMouseListener(new MouseAdapter() {
      public void mouseEntered(MouseEvent evt) {
        setBackground(Colores.AZUL_OSCURO);
      }

      public void mouseExited(MouseEvent evt) {
        setBackground(Colores.COLOR_AZUL);
      }

      public void mousePressed(MouseEvent evt) {
        setBackground(Colores.AZUL_OSCURO);
      }

      public void mouseReleased(MouseEvent evt) {
        setBackground(Colores.AZUL_OSCURO);
      }

      public void mouseClicked(MouseEvent evt) {
        setBackground(Colores.AZUL_OSCURO);
      }
    });

  }

  public Boton fontSize(int size) {
    setFont(new Font("Segoe UI Emoji", Font.PLAIN, size));
    return this;
  }

  public Boton color(Color color) {
    setForeground(color);
    return this;
  }

  public Boton background(Color color) {
    setBackground(color);
    return this;
  }

  public Boton bold() {
    setFont(new Font("Segoe UI Emoji", Font.BOLD, getFont().getSize()));
    return this;
  }

}
