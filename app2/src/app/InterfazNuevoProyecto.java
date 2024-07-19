package app;

import java.awt.GridLayout;
import java.sql.Date;

import javax.swing.BoxLayout;
import javax.swing.JScrollPane;

import app.PanelProyectos.VistaProyectos;

public class InterfazNuevoProyecto extends Vista {

  InterfazScientificCheckBoxList $CheckBoxList;

  public InterfazNuevoProyecto() {
    super("NUEVO PROYECTO");

    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    Contenedor _Formulario = new Contenedor();

    _Formulario.setLayout(new GridLayout(2, 1, 10, 10));

    setAlignmentX(CENTER_ALIGNMENT);

    Contenedor $ProjectForm = new Contenedor();
    $ProjectForm.setLayout(new BoxLayout($ProjectForm, BoxLayout.Y_AXIS));
    $ProjectForm.setAlignmentX(CENTER_ALIGNMENT);

    Entrada $nombreInput = new Entrada("NOMBRE DEL PROYECTO");
    $ProjectForm.add($nombreInput);

    Combo<String> $CategoryInput = new Combo<String>("AREA", Campos.AREAS);
    $ProjectForm.add($CategoryInput);

    Entrada $MontoInput = new Entrada("PRESUPUESTO").number();
    $ProjectForm.add($MontoInput);

    Contenedor $Buttons = new Contenedor();
    $Buttons.setLayout(new BoxLayout($Buttons, BoxLayout.X_AXIS));
    $Buttons.setAlignmentX(CENTER_ALIGNMENT);

    Fecha $StartDate = new Fecha("INICIO");
    Fecha $EndDate = new Fecha("FIN");

    $ProjectForm.add($StartDate);
    $ProjectForm.add($EndDate);

    Boton $SubmitButton = new Boton("GUARDAR");
    Boton $CancelButton = new Boton("CANCELAR").bg(Colores.ERROR, Colores.ERROR);

    Contenedor $ScientificForm = new Contenedor();
    $ScientificForm.setLayout(new BoxLayout($ScientificForm, BoxLayout.Y_AXIS));

    ModeloCientifico[] allScientifics = BaseDeDatos.cientificos.obtener_todo_los_cientificos();
    $CheckBoxList = new InterfazScientificCheckBoxList(allScientifics);

    $ScientificForm.add($CheckBoxList);

    $CancelButton.addActionListener(e -> VistaProyectos.mostrar(this, new InterfazContenedorProyectos()));

    $SubmitButton.addActionListener(
        e -> {
          String nombre = $nombreInput.getValue();
          String area = $CategoryInput.getValue();
          String monto = $MontoInput.getValue();
          Date startDate = $StartDate.getValue();
          Date endDate = $EndDate.getValue();

          if (nombre.isEmpty() || area.isEmpty() || monto.isEmpty() || startDate == null || endDate == null) {
            return;
          }

          float duracion = Temporizador.calculateDurationInMonths(startDate, endDate);

          ModeloProyecto project = new ModeloProyecto()
              .$nombre(nombre)
              .$area(area)
              .$monto(Float.parseFloat(monto))
              .$fecha_inicio(startDate)
              .$fecha_fin(endDate)
              .$duracion(duracion) // meses de duracion
              .$terminacion(duracion) // meses restantes
              .$avance(0); // avance

          BaseDeDatos.proyectos.insertar_proyecto(project);
          ModeloProyecto ultimo = BaseDeDatos.proyectos.obtener_ultimo_proyecto_insertaModeloProyecto();

          ModeloCientifico[] selectedScientifics = $CheckBoxList.getItems();

          for (ModeloCientifico scientific : selectedScientifics) {
            BaseDeDatos.proyectos_cientificos.asignar_cientifico_al_proyecto(ultimo.id, scientific.id);
          }

          VistaProyectos.mostrar(this, new InterfazContenedorProyectos());
        });

    $Buttons.add($SubmitButton);
    $Buttons.add($CancelButton);
    $ScientificForm.add($Buttons);

    _Formulario.add($ProjectForm);
    _Formulario.add($ScientificForm);

    JScrollPane _ContenedorDeslizante = new JScrollPane(_Formulario);
    _ContenedorDeslizante.getVerticalScrollBar().setUnitIncrement(16);
    add(_ContenedorDeslizante);

  }
}
