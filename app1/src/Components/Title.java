package Components;

import javax.swing.JLabel;

import Data.Constants;
import Data.Palette;

public class Title extends JLabel {
  public Title(String text) {
    setText(text.toUpperCase());
    setFont(Constants.FONT_TITLE);
    setAlignmentX(CENTER_ALIGNMENT);
    setForeground(Palette.DARK_BLUE);
    setVisible(true);
  }
}
