package app;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Contraseña extends Contenedor {

  public Contraseña(String label) {
    super();

    setLayout(new GridLayout(2, 1, 0, 0));
    setBorder(Valores.MARGENES);
    setAlignmentX(CENTER_ALIGNMENT);

    JPasswordField $TextField = new JPasswordField();

    $TextField.setFont(Valores.FUENTE_BASE);
    $TextField.setForeground(Colores.NEGRO);
    $TextField.setBackground(Colores.BASE);
    $TextField.setMinimumSize(new Dimension(100, 30));
    $TextField.setPreferredSize(new Dimension(100, 30));
    $TextField.setMaximumSize(new Dimension(300, 30));
    $TextField.setAlignmentX(LEFT_ALIGNMENT);

    JLabel $Label = new JLabel(label);
    $Label.setFont(Valores.FUENTE_DE_SUBTITULOS);
    $Label.setAlignmentX(LEFT_ALIGNMENT);
    $Label.setForeground(Colores.NEGRO);
    $Label.setLabelFor($TextField);

    add($Label);
    add($TextField);
  }

  public String getValue() {
    return ((JTextField) getComponent(1)).getText();
  }

  public void setValue(String value) {
    ((JTextField) getComponent(1)).setText(value);
  }

}
