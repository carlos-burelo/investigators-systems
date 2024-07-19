package app;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class InterfazListaDeAvances extends Contenedor {

  public InterfazListaDeAvances(ModeloAvance[] avances) {
    setBackground(Colores.BASE);
    JScrollPane _ContenedorDeslizante = new JScrollPane(this);
    _ContenedorDeslizante.setBackground(Colores.BASE);
    JPanel _ContenedorDeslizanteContent = new JPanel();
    _ContenedorDeslizanteContent.setBackground(Colores.BASE);
    SubTitulo $Title = new SubTitulo("AVANCES");
    $Title.setAlignmentX(LEFT_ALIGNMENT);
    add($Title);
    for (ModeloAvance avance : avances) {
      _ContenedorDeslizanteContent.add(new AdvanceItem(avance));
    }
    _ContenedorDeslizanteContent.setLayout(new BoxLayout(_ContenedorDeslizanteContent, BoxLayout.Y_AXIS));
    _ContenedorDeslizante.setViewportView(_ContenedorDeslizanteContent);
    _ContenedorDeslizante.setPreferredSize(new Dimension(400, 200));
    _ContenedorDeslizante.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    _ContenedorDeslizante.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    add(_ContenedorDeslizante);
  }
}

class AdvanceItem extends Contenedor {

  public AdvanceItem(ModeloAvance avance) {
    SubTitulo $Date = new SubTitulo(avance.fecha.toString());
    $Date.setFont(Valores.FUENTE_BASE);
    SubTitulo $Description = new SubTitulo(avance.texto + "\n");
    $Description.setFont(Valores.FUENTE_BASE);
    add($Date);
    add($Description);
  }
}