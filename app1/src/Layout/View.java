package Layout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import Components.Title;
import Data.Palette;

public class View extends JPanel {
  public View(String title) {
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    setName(View.class.getSimpleName());
    setBackground(Palette.WHITE);
    setAlignmentX(CENTER_ALIGNMENT);
    setAlignmentY(CENTER_ALIGNMENT);
    add(new Title(title));
  }
}
