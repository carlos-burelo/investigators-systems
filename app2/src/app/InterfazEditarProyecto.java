package app;

import java.awt.GridLayout;
import java.sql.Date;

import javax.swing.BoxLayout;
import javax.swing.JScrollPane;

import app.PanelProyectos.VistaProyectos;

public class InterfazEditarProyecto extends Vista {
  InterfazScientificCheckBoxList $CheckBoxList;

  public InterfazEditarProyecto(ModeloProyecto project) {
    super("EDITAR PROYECTO");
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    setAlignmentX(LEFT_ALIGNMENT);

    Contenedor _Formulario = new Contenedor();
    _Formulario.setLayout(new GridLayout(1, 1, 10, 10));

    Contenedor _FormularioProyecto = new Contenedor();
    _FormularioProyecto.setLayout(new BoxLayout(_FormularioProyecto, BoxLayout.Y_AXIS));
    _FormularioProyecto.setAlignmentX(LEFT_ALIGNMENT);

    Entrada _CampoNombre = new Entrada("NOMBRE DEL PROYECTO");
    _CampoNombre.setValue(project.nombre);
    _FormularioProyecto.add(_CampoNombre);

    Combo<String> _CampoCategoria = new Combo<String>("AREA", Campos.AREAS);
    _CampoCategoria.setValue(project.area);
    _FormularioProyecto.add(_CampoCategoria);

    Entrada _CampoMonto = new Entrada("PRESUPUESTO").number();
    _CampoMonto.setValue(String.format("%.0f", project.monto));
    _FormularioProyecto.add(_CampoMonto);

    Contenedor _ContenedorBotones = new Contenedor();
    _ContenedorBotones.setLayout(new BoxLayout(_ContenedorBotones, BoxLayout.X_AXIS));
    _ContenedorBotones.setAlignmentX(CENTER_ALIGNMENT);

    Fecha _CampoFechaInicio = new Fecha("INICIO");
    _CampoFechaInicio.setDate(project.fecha_inicio);
    Fecha _CampoFechaFin = new Fecha("FIN");
    _CampoFechaFin.setDate(project.fecha_fin);

    _FormularioProyecto.add(_CampoFechaInicio);
    _FormularioProyecto.add(_CampoFechaFin);

    Boton _BotonGuardar = new Boton("GUARDAR");
    Boton _BotonCancelar = new Boton("CANCELAR").bg(Colores.ERROR, Colores.ERROR);

    _BotonCancelar.addActionListener(
        e -> {
          VistaProyectos.mostrar(this, new InterfazContenedorProyectos());
        });

    _BotonGuardar.addActionListener(
        e -> {
          ModeloProyecto proyecto_previo = BaseDeDatos.proyectos.obtener_proyecto_por_id(project.id);

          int id = project.id;
          String nombre = _CampoNombre.getValue();
          String area = _CampoCategoria.getValue();
          float monto = Float.parseFloat(_CampoMonto.getValue());
          Date fecha_inicio = _CampoFechaInicio.getValue();
          Date fecha_fin = _CampoFechaFin.getValue();

          ModeloProyecto proyecto_actualizado = new ModeloProyecto()
              .$id(id)
              .$nombre(nombre)
              .$area(area)
              .$monto(monto)
              .$avance(proyecto_previo.avance)
              .$terminacion(proyecto_previo.terminacion)
              .$fecha_inicio(fecha_inicio)
              .$fecha_fin(fecha_fin);
          BaseDeDatos.proyectos.actualizar_proyecto(proyecto_actualizado);
          ModeloCientifico[] cientificos_seleccionados = $CheckBoxList.getItems();
          for (ModeloCientifico cientifico : cientificos_seleccionados) {
            if (cientifico.selected) {
              BaseDeDatos.proyectos_cientificos.asignar_cientifico_al_proyecto(project.id, cientifico.id);
            } else {
              BaseDeDatos.proyectos_cientificos.remover_cientifico_del_proyecto(project.id, cientifico.id);
            }
          }
          VistaProyectos.mostrar(this, new InterfazContenedorProyectos());
        });

    _ContenedorBotones.add(_BotonGuardar);
    _ContenedorBotones.add(_BotonCancelar);

    ModeloCientifico[] todos_los_cientificos = BaseDeDatos.proyectos_cientificos
        .lista_cientificos_seleccionados_por_id(project.id);

    $CheckBoxList = new InterfazScientificCheckBoxList(todos_los_cientificos);
    _FormularioProyecto.add($CheckBoxList);

    InterfazFormularioNuevoAvance _FormularioNuevoAvance = new InterfazFormularioNuevoAvance(project);

    _FormularioProyecto.add(_FormularioNuevoAvance);
    _FormularioProyecto.add(_ContenedorBotones);
    _Formulario.add(_FormularioProyecto);

    JScrollPane _ContenedorDeslizante = new JScrollPane(_Formulario);
    _ContenedorDeslizante.getVerticalScrollBar().setUnitIncrement(16);
    add(_ContenedorDeslizante);
  }
}
