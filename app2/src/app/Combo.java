package app;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JComboBox;

public class Combo<T> extends Contenedor {

  public Combo(String label, T[] items) {

    setLayout(new GridLayout(2, 1, 0, 0));

    setBorder(Valores.MARGENES);
    setAlignmentX(CENTER_ALIGNMENT);

    JComboBox<T> $ComboBox = new JComboBox<T>(items);

    $ComboBox.setFont(Valores.FUENTE_BASE);
    $ComboBox.setForeground(Colores.NEGRO);
    $ComboBox.setBackground(Colores.BASE);
    $ComboBox.setMinimumSize(new Dimension(100, 30));
    $ComboBox.setPreferredSize(new Dimension(100, 30));
    $ComboBox.setMaximumSize(new Dimension(300, 30));
    $ComboBox.setAlignmentX(LEFT_ALIGNMENT);

    SubTitulo $Label = new SubTitulo(label);
    $Label.setFont(Valores.FUENTE_DE_SUBTITULOS);
    $Label.setAlignmentX(LEFT_ALIGNMENT);
    $Label.setForeground(Colores.NEGRO);
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
