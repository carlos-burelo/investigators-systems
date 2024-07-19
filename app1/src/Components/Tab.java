package Components;

import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import Data.Constants;
import Data.Palette;

public class Tab extends JButton {
  public Tab(String name, ActionListener e) {
    super(name);
    setFont(Constants.FONT_BASE);
    setBackground(Palette.BLUE);
    setForeground(Palette.WHITE);
    setFocusable(false);
    setBorderPainted(false);
    addActionListener(e);

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
}
