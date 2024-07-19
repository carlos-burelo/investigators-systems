package app;

import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class InterfazScientificCheckBoxList extends JPanel {
  private ModeloCientifico[] items;
  private List<JCheckBox> $CheckBoxes = new ArrayList<JCheckBox>();

  public InterfazScientificCheckBoxList(ModeloCientifico[] items) {
    this.items = items;
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    setBackground(Colores.BASE);
    setBorder(Valores.MARGENES);
    add(new SubTitulo("CIENTÍFICOS"));

    for (ModeloCientifico item : items) {
      JCheckBox $CheckBox = new JCheckBox(item.nombre);
      $CheckBox.setSelected(item.selected);
      $CheckBox.setBackground(Colores.BASE);
      $CheckBox.setFont(Valores.FUENTE_BASE);
      $CheckBox.putClientProperty("id", item.id);
      $CheckBoxes.add($CheckBox);
      add($CheckBox);
    }
  }

  public ModeloCientifico[] getItems() {
    List<ModeloCientifico> selected = new ArrayList<>();
    for (int i = 0; i < $CheckBoxes.size(); i++) {
      JCheckBox $CheckBox = $CheckBoxes.get(i);
      if ($CheckBox.isSelected()) {
        items[i].selected = true;
        selected.add(items[i]);
      } else {
        items[i].selected = false;
        selected.add(items[i]);
      }
    }
    return selected.toArray(new ModeloCientifico[selected.size()]);
  }
}
