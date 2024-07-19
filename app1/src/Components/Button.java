package Components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import Data.Constants;
import Data.Palette;

public class Button extends JButton {
  public Button(String text) {
    setLayout(new GridLayout(1, 1));
    setText(text.toUpperCase());
    setFont(Constants.FONT_BASE);
    setForeground(Palette.WHITE);
    setBackground(Palette.DARK_BLUE);
    setAlignmentX(CENTER_ALIGNMENT);
    setAlignmentY(CENTER_ALIGNMENT);
    setFocusPainted(false);
    setBorderPainted(false);
    setMaximumSize(new Dimension(300, 80));

    addMouseListener(
        new MouseAdapter() {
          public void mouseEntered(MouseEvent evt) {
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            setBackground(Palette.DARK_BLUE);
          }

          public void mouseExited(MouseEvent evt) {
            setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            setBackground(Palette.BLUE);
          }

          public void mousePressed(MouseEvent evt) {
            setBackground(Palette.DARK_BLUE);
          }
        });

  }

  public Button bg(Color color, Color hoverColor) {
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
