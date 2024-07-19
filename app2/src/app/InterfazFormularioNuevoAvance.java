package app;

import java.sql.Date;
import java.time.LocalDate;

import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import app.PanelProyectos.VistaProyectos;

public class InterfazFormularioNuevoAvance extends Contenedor {

  private final ModeloProyecto proyecto;

  public InterfazFormularioNuevoAvance(ModeloProyecto proyecto) {

    this.proyecto = proyecto;
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    Fecha $Date = new Fecha("FECHA");
    add($Date);

    Entrada $Description = new Entrada("DESCRIPCIÓN");
    add($Description);

    Boton $SubmitButton = new Boton("GUARDAR NUEVO AVANCE");
    $SubmitButton.addActionListener(e -> onSave($Date, $Description));
    ModeloAvance[] avances = BaseDeDatos.avances.obtener_avances_por_id_del_proyecto(proyecto.id);
    InterfazListaDeAvances _ListaDeAvance = new InterfazListaDeAvances(avances);
    add(new JScrollPane(_ListaDeAvance));
    add($SubmitButton);
  }

  private void onSave(Fecha $Date, Entrada $Description) {
    ModeloProyecto proyecto_previo = BaseDeDatos.proyectos.obtener_proyecto_por_id(proyecto.id);

    if (proyecto.avance >= 100) {
      JOptionPane.showMessageDialog(
          this,
          "No se puede editar un proyecto que ya ha sido completado.",
          "Error",
          JOptionPane.ERROR_MESSAGE);
      return;
    }

    Date currentDate = $Date.getValue();
    Date startDate = proyecto.fecha_inicio;
    Date endDate = proyecto.fecha_fin;

    if (!validateDate(currentDate, startDate, endDate) || !validateDescription($Description.getValue())) {
      return;
    }

    float duration = (float) (endDate.getTime() - startDate.getTime());
    float advanceDuration = (float) (currentDate.getTime() - startDate.getTime());
    float advancePercentage = advanceDuration / duration * 100;

    ModeloAvance avance = new ModeloAvance()
        .$fecha(currentDate)
        .$texto($Description.getValue())
        .$id_proyecto(proyecto.id);

    ModeloProyecto proyecto = new ModeloProyecto()
        .$id(proyecto_previo.id)
        .$nombre(proyecto_previo.nombre)
        .$area(proyecto_previo.area)
        .$monto(proyecto_previo.monto)
        .$fecha_inicio(proyecto_previo.fecha_inicio)
        .$fecha_fin(proyecto_previo.fecha_fin)
        .$duracion(proyecto_previo.duracion)
        .$terminacion(proyecto_previo.terminacion)
        .$avance(advancePercentage);

    BaseDeDatos.avances.insertar_avance(avance);
    BaseDeDatos.proyectos.actualizar_proyecto(proyecto);

    VistaProyectos.mostrar(this, new InterfazContenedorProyectos());
  }

  private boolean validateDate(Date date, Date startDate, Date endDate) {
    LocalDate localDate = date.toLocalDate();
    LocalDate localStartDate = startDate.toLocalDate();
    LocalDate localEndDate = endDate.toLocalDate();

    if (localDate.isBefore(localStartDate) || localDate.isAfter(localEndDate)) {
      String min = localStartDate.toString();
      String max = localEndDate.toString();
      String message = String.format("La fecha debe estar entre %s y %s", min, max);
      JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
      return false;
    }
    return true;
  }

  private boolean validateDescription(String description) {
    if (description.isEmpty()) {
      JOptionPane.showMessageDialog(null, "La descripción no puede estar vacía", "Error", JOptionPane.ERROR_MESSAGE);
      return false;
    }
    return true;
  }
}
