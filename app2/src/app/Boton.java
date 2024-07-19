package app;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class Boton extends JButton {
  public Boton(String text) {

    setLayout(new GridLayout(1, 1));
    setText(text.toUpperCase());
    setFont(Valores.FUENTE_BASE);
    setForeground(Colores.BASE);
    setBackground(Colores.PRINCIPAL);
    setAlignmentX(CENTER_ALIGNMENT);
    setAlignmentY(CENTER_ALIGNMENT);
    setFocusPainted(false);
    setBorderPainted(false);

    addMouseListener(
        new MouseAdapter() {
          public void mouseEntered(MouseEvent evt) {
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            setBackground(Colores.PRINCIPAL);
          }

          public void mouseExited(MouseEvent evt) {
            setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            setBackground(Colores.PRINCIPAL);
          }

          public void mousePressed(MouseEvent evt) {
            setBackground(Colores.PRINCIPAL);
          }
        });

  }

  public Boton bg(Color color, Color hoverColor) {
    setBackground(color);

    addMouseListener(
        new MouseAdapter() {
          public void mouseEntered(MouseEvent evt) {
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            setBackground(hoverColor);
          }

          public void mouseExited(MouseEvent evt) {
            setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            setBackground(color);
          }

          public void mousePressed(MouseEvent evt) {
            setBackground(hoverColor);
          }
        });

    return this;
  }

}
