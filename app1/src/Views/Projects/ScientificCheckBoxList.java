package Views.Projects;

import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

import Components.SubTitle;
import Data.Constants;
import Data.Palette;
import DB.Models.Scientific;

public class ScientificCheckBoxList extends JPanel {
  private Scientific[] items;
  private List<JCheckBox> $CheckBoxes = new ArrayList<JCheckBox>();

  public ScientificCheckBoxList(Scientific[] items) {
    this.items = items;
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    setBackground(Palette.WHITE);
    setBorder(Constants.BORDERS);
    add(new SubTitle("Científicos"));

    for (Scientific item : items) {
      JCheckBox $CheckBox = new JCheckBox(item.name);
      $CheckBox.setSelected(item.selected);
      $CheckBox.setBackground(Palette.WHITE);
      $CheckBox.setFont(Constants.FONT_BASE);
      $CheckBox.putClientProperty("id", item.id);
      $CheckBoxes.add($CheckBox);
      add($CheckBox);
    }
  }

  public Scientific[] getItems() {
    List<Scientific> selected = new ArrayList<>();
    for (int i = 0; i < $CheckBoxes.size(); i++) {
      JCheckBox $CheckBox = $CheckBoxes.get(i);
      int id = (int) $CheckBox.getClientProperty("id");
      items[i].id = id;
      if ($CheckBox.isSelected()) {
        items[i].selected = true;
        selected.add(items[i]);
      } else {
        items[i].selected = false;
        selected.add(items[i]);
      }
    }
    return selected.toArray(new Scientific[selected.size()]);
  }
}
