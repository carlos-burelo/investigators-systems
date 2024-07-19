package Components;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;

import Data.Constants;
import Data.Palette;
import Layout.Div;

public class DropDown<T> extends Div {

  public DropDown(String label, T[] items) {

    // setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    setLayout(new GridLayout(2, 1));
    setMaximumSize(Constants.MIN_FIELD_SIZE);

    setBorder(Constants.FIELDS_BORDERS);
    setAlignmentX(CENTER_ALIGNMENT);

    JComboBox<T> $ComboBox = new JComboBox<T>(items);

    $ComboBox.setFont(Constants.FONT_BASE);
    $ComboBox.setForeground(Palette.BLACK);
    $ComboBox.setBackground(Palette.WHITE);
    $ComboBox.setMinimumSize(new Dimension(100, 30));
    $ComboBox.setPreferredSize(new Dimension(100, 30));
    $ComboBox.setMaximumSize(new Dimension(300, 30));
    $ComboBox.setAlignmentX(LEFT_ALIGNMENT);

    JLabel $Label = new JLabel(label);
    $Label.setFont(Constants.FONT_SUBTITLE);
    $Label.setAlignmentX(LEFT_ALIGNMENT);
    $Label.setForeground(Palette.BLACK);
    $Label.setLabelFor($ComboBox);

    add($Label);
    add($ComboBox);
  }

  @SuppressWarnings("unchecked")
  public String getValue() {
    return ((JComboBox<T>) getComponent(1)).getSelectedItem().toString();
  }

  @SuppressWarnings("unchecked")
  public void setValue(String value) {
    ((JComboBox<T>) getComponent(1)).setSelectedItem(value);
  }
}
