package Components;

import javax.swing.JLabel;

import Data.Constants;
import Data.Palette;

public class SubTitle extends JLabel {
  public SubTitle(String text) {
    setText(text.toUpperCase());
    setFont(Constants.FONT_SUBTITLE);
    setForeground(Palette.BLACK);
  }
}
