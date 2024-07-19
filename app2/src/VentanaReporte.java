import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import app.BaseDeDatos;
import app.Contenedor;
import app.ModeloCientifico;
import app.ModeloProyecto;
import app.SubTitulo;
import app.TablaProyectoCientificoEnDB.ModeloCientificoProyecto;
import app.Valores;

public class VentanaReporte extends JFrame {
  public VentanaReporte() {
    super("Reportes");
    setVisible(true);
    setSize(Valores.ANCHO_VENTANA, Valores.ALTO_VENTANA);
    setExtendedState(JFrame.MAXIMIZED_BOTH);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    Contenedor $Content = new Contenedor();
    $Content.setLayout(new BoxLayout($Content, BoxLayout.Y_AXIS));
    $Content.setAlignmentX(LEFT_ALIGNMENT);
    $Content.setBorder(Valores.MARGENES);
    SubTitulo $Titulo1 = new SubTitulo("Cientificos asignado a cada proyecto");
    $Content.add($Titulo1);

    ModeloCientificoProyecto[] scientifics_by_project = BaseDeDatos.proyectos_cientificos
        .obtener_cientificos_y_proyectos();

    String $List1 = "";
    for (ModeloCientificoProyecto $Modelo : scientifics_by_project) {
      $List1 += String.format("     - Nombre: %s, Proyecto: %s\n", $Modelo.scientific_nombre, $Modelo.project_nombre);
      $List1 += "\n";
    }

    JLabel $Label = new JLabel($List1);
    $Content.add($Label);

    SubTitulo $Titulo2 = new SubTitulo("Cientificos con mas de 18 meses en un proyecto");
    $Content.add($Titulo2);

    ModeloCientifico[] scientifics_with_18_months = BaseDeDatos.proyectos_cientificos
        .obtener_cientificos_con_18_meses();

    String $List2 = "";
    for (ModeloCientifico $Modelo : scientifics_with_18_months) {
      $List2 += String.format("     - Nombre: %s, Proyecto: %s\n", $Modelo.nombre, $Modelo.id) + "\n";
    }
    JLabel $Label2 = new JLabel($List2);
    $Content.add($Label2);

    SubTitulo $Titulo3 = new SubTitulo("Proyectos financiados con mas de $250,000");
    $Content.add($Titulo3);

    ModeloProyecto[] projects_with_more_than_250k = BaseDeDatos.proyectos
        .obtener_proyecto_financiados_con_mas_de_250k();

    String $List3 = "";
    for (ModeloProyecto $Modelo : projects_with_more_than_250k) {
      $List3 += String.format("     - Nombre: %s, Monto: %s\n", $Modelo.nombre, $Modelo.monto) + "\n";
    }
    JLabel $Label3 = new JLabel($List3);
    $Content.add($Label3);

    ModeloProyecto[] scientifics = BaseDeDatos.proyectos.obtener_avances_y_proyectos();

    SubTitulo $Titulo4 = new SubTitulo("Avances y areas de investigacion por proyecto");
    $Content.add($Titulo4);

    String $List4 = "";
    for (ModeloProyecto $Modelo : scientifics) {
      $List4 += String.format("     - Nombre: %s, Avance: %s, Area: %s\n", $Modelo.nombre, $Modelo.avance, $Modelo.area)
          + "\n";
    }
    JLabel $Label4 = new JLabel($List4);
    $Content.add($Label4);

    JScrollPane $Scroll = new JScrollPane($Content);
    $Scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    $Scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    add($Scroll);
  }
}
