package proyecto.interfaz;

import javax.swing.*;

import proyecto.componentes.Boton;

import java.awt.*;
import java.awt.event.ActionListener;

public class Menu extends JPanel {
  private JButton botonProyectos;
  private JButton botonCientificos;
  private JButton botonInforme;

  public Menu(ActionListener proyectosListener, ActionListener cientificosListener,
      ActionListener informeListener) {
    setBackground(Color.WHITE);
    botonProyectos = crearBoton("🧪", proyectosListener);
    botonCientificos = crearBoton("🧑‍🔬", cientificosListener);
    botonInforme = crearBoton("📊", informeListener);
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    add(botonProyectos);
    add(Box.createHorizontalGlue());
    add(botonCientificos);
    add(Box.createHorizontalGlue());
    add(botonInforme);
  }

  private JButton crearBoton(String texto, ActionListener listener) {
    Boton boton = new Boton(texto, 400, 160);
    boton.fontSize(40);
    boton.setMargin(new Insets(10, 10, 10, 10));
    boton.addActionListener(listener);
    return boton;
  }
}
