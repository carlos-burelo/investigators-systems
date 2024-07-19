package proyecto.interfaz.proyectos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import proyecto.componentes.DatePicker;
import proyecto.datos.BaseDeDatos;
import proyecto.datos.Proyectos.Proyecto;

public class ModalRegistrarAvance {
  private JDialog dialog;
  private DatePicker fechaInicioPanel;

  public ModalRegistrarAvance(JFrame parent, Proyecto proyecto, GridProyectos gridProyectos) {
    dialog = new JDialog();
    BaseDeDatos db = new BaseDeDatos();
    Date ultimoAvance = db.avances.buscarUltimoAvance(proyecto.clave);

    Date fechaPicker = ultimoAvance != null ? ultimoAvance : proyecto.fecha_inicio;

    dialog.setLayout(new BoxLayout(dialog.getContentPane(), BoxLayout.Y_AXIS));
    dialog.setTitle("Registrar avance");
    dialog.setSize(400, 300);
    dialog.setLocationRelativeTo(parent);
    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

    fechaInicioPanel = new DatePicker();
    fechaInicioPanel.setDate(fechaPicker);
    dialog.add(new JLabel("Fecha de Inicio:"));
    dialog.add(fechaInicioPanel);

    dialog.add(new JLabel("Descripcion:"));
    JTextArea descripcion = new JTextArea();
    dialog.add(descripcion);

    JButton btnRegistrar = new JButton("Registrar");
    btnRegistrar.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (ultimoAvance != null) {
          if (fechaInicioPanel.getDate().before(ultimoAvance) || fechaInicioPanel.getDate().after(proyecto.fecha_fin)) {
            JOptionPane.showMessageDialog(dialog,
                "La fecha de inicio debe ser posterior al último avance y anterior a la fecha de fin del proyecto",
                "Error", JOptionPane.ERROR_MESSAGE);

            // VUELVE A SETEAR LA FECHA DEL ULTIMO AVANCE
            fechaInicioPanel.setDate(ultimoAvance);
            return;
          }
        }

        // Registrar el avance en la base de datos

        if (descripcion.getText().isEmpty()) {
          JOptionPane.showMessageDialog(dialog, "La descripción no puede estar vacía", "Error",
              JOptionPane.ERROR_MESSAGE);
          return;
        }

        db.avances.registrarAvance(proyecto.clave, descripcion.getText(), fechaInicioPanel.getDate());

        // Obtener las fechas del proyecto
        LocalDate fechaInicio = proyecto.fecha_inicio.toLocalDate();
        LocalDate fechaFin = proyecto.fecha_fin.toLocalDate();

        // Calcular la duración total del proyecto
        long duracionProyecto = ChronoUnit.MONTHS.between(fechaInicio, fechaFin);

        // Calcular el avance basado en la fecha del registro del avance
        LocalDate fechaRegistroAvance = fechaInicioPanel.getDate().toLocalDate();
        long terminacion = ChronoUnit.MONTHS.between(fechaInicio, fechaRegistroAvance);

        float avance = (terminacion / (float) duracionProyecto) * 100;
        avance = Math.min(avance, 100); // Asegurar que el avance no sea mayor al 100%

        // Actualizar el avance en la base de datos
        db.proyectos.actualizarAvance(proyecto.clave, avance, terminacion);

        // Actualizar el grid de proyectos
        gridProyectos.onChange(db.proyectos.lista());
        dialog.dispose();
      }
    });

    dialog.add(btnRegistrar);
  }

  public void mostrar() {
    dialog.setVisible(true);
  }
}
