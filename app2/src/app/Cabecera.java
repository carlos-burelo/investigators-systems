package app;

import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;

public class Cabecera extends Contenedor {

  public Cabecera(String Section, ActionListener event) {
    setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    setBorder(Valores.MARGENES);
    add(new Titulo(Section));
    add(Box.createHorizontalGlue());
    Boton $Button = new Boton("NUEVO");
    $Button.addActionListener(event);
    add($Button);

  }
}
