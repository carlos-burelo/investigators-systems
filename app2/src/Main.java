import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import app.Boton;
import app.Contenedor;
import app.InterfazContendorCientificos;
import app.InterfazContenedorProyectos;
import app.PanelCientificos;
import app.PanelProyectos;
import app.Valores;
import app.PanelCientificos.EscuchadorCientificos;
import app.PanelCientificos.VistaCientificos;
import app.PanelProyectos.EscuchadorProyectos;
import app.PanelProyectos.VistaProyectos;

public class Main extends JFrame {

    public Contenedor _Ventana = new Contenedor();

    public Main() {
        super("Proyecto");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setSize(Valores.ANCHO_VENTANA, Valores.ALTO_VENTANA);
        _Ventana.setLayout(new GridLayout(2, 2));

        VistaReactivaProyecto _VentanaProyecto = new VistaReactivaProyecto();
        VistaProyectos.vincular(_VentanaProyecto);

        VistaReactivaCientifico _VentanaCientificos = new VistaReactivaCientifico();
        VistaCientificos.vincular(_VentanaCientificos);
        _Ventana.add(_VentanaProyecto);
        _Ventana.add(_VentanaCientificos);
        Boton $Boton = new Boton("Generar reporte");
        $Boton.addActionListener(e -> {
            new VentanaReporte();
        });
        add($Boton);
        add(_Ventana);
        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaIniciarSesion());
    }
}

class VistaReactivaProyecto extends Contenedor implements EscuchadorProyectos {

    public VistaReactivaProyecto() {
        setLayout(new GridLayout(1, 1));
        setSize(Valores.ANCHO_VENTANA / 2, Valores.ALTO_VENTANA);
        add(new InterfazContenedorProyectos());
    }

    @Override
    public void cuadoCambie(PanelProyectos event) {
        removeAll();
        add(event.newView);
        revalidate();
        repaint();
    }
}

class VistaReactivaCientifico extends Contenedor implements EscuchadorCientificos {

    public VistaReactivaCientifico() {
        setLayout(new GridLayout(1, 1));
        setSize(Valores.ANCHO_VENTANA / 2, Valores.ALTO_VENTANA);
        add(new InterfazContendorCientificos());
    }

    @Override
    public void cuandoCambie(PanelCientificos event) {
        removeAll();
        add(event.newView);
        revalidate();
        repaint();
    }
}