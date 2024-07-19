package proyecto.interfaz.proyectos;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.NumberFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.text.NumberFormatter;

import proyecto.componentes.DatePicker;
import proyecto.datos.BaseDeDatos;
import proyecto.datos.Cientificos.Cientifico;
import proyecto.datos.Proyectos.Proyecto;

interface ProyectoGuardadoListener {
  void nuevoProyectoGuardado(Proyecto nuevoProyecto);

  void proyectoActualizado(Proyecto proyecto);
}

public class ModalCrearProyecto {

  private JDialog dialog;
  private JTextField nombreProyectoField;
  private JTextField areaField;
  private JFormattedTextField presupuestoField;
  private DatePicker fechaInicioPanel;
  private DatePicker fechaFinPanel;

  public ModalCrearProyecto(JFrame parent, GridProyectos gridProyectos) {

    dialog = new JDialog(parent, "Crear Nuevo Proyecto", true);
    dialog.setBackground(Color.WHITE);
    dialog.setPreferredSize(new Dimension(500, 500));
    dialog.setLayout(new GridLayout(7, 2));

    dialog.add(new JLabel("Nombre del Proyecto:"));
    nombreProyectoField = new JTextField();
    dialog.add(nombreProyectoField);

    dialog.add(new JLabel("Área:"));
    areaField = new JTextField();
    dialog.add(areaField);

    fechaInicioPanel = new DatePicker();
    fechaFinPanel = new DatePicker();

    dialog.add(new JLabel("Fecha de Inicio:"));
    dialog.add(fechaInicioPanel);

    dialog.add(new JLabel("Fecha de Fin:"));
    dialog.add(fechaFinPanel);

    // Crea un formato de número
    NumberFormat format = NumberFormat.getIntegerInstance();
    NumberFormatter formatter = new NumberFormatter(format);
    formatter.setValueClass(Integer.class);
    formatter.setMinimum(0);

    dialog.add(new JLabel("Presupuesto:"));
    presupuestoField = new JFormattedTextField();

    dialog.add(presupuestoField);

    BaseDeDatos db = new BaseDeDatos();

    Cientifico[] cientificos = db.cientificos.obtenerCientificos();

    JLabel labelCientificos = new JLabel("Cientificos:");
    dialog.add(labelCientificos);

    JScrollPane scrollPane = new JScrollPane();
    scrollPane.setPreferredSize(new Dimension(500, 200));
    JPanel panelChecks = new JPanel();
    panelChecks.setLayout(new BoxLayout(panelChecks, BoxLayout.Y_AXIS));
    // checkbox, value is scientifico.id and label is scientifico. nombre
    JCheckBox[] checkBoxes = new JCheckBox[cientificos.length];
    for (int i = 0; i < cientificos.length; i++) {
      checkBoxes[i] = new JCheckBox(cientificos[i].nombre);
      panelChecks.add(checkBoxes[i]);
    }
    panelChecks.setVisible(true);

    scrollPane.setViewportView(panelChecks);
    dialog.add(scrollPane);

    JButton botonCrear = new JButton("💾 Guardar");
    botonCrear.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String nombre = nombreProyectoField.getText();

        if (nombre.isEmpty()) {
          JOptionPane.showMessageDialog(null, "El nombre del proyecto no puede estar vacío");
          return;
        }

        String area = areaField.getText();

        if (area.isEmpty()) {
          JOptionPane.showMessageDialog(null, "El área del proyecto no puede estar vacía");
          return;
        }

        String presupuestoString = presupuestoField.getText();

        if (presupuestoString.isEmpty()) {
          JOptionPane.showMessageDialog(null, "El presupuesto no puede estar vacío");
          return;
        }

        // Validar que sea un número
        if (!presupuestoString.matches("([0-9]+([.][0-9]*)?|[.][0-9]+)")) {
          JOptionPane.showMessageDialog(null, "El presupuesto debe ser un número");
          return;
        }

        float presupuesto = Float.parseFloat(presupuestoField.getText());

        if (presupuesto <= 0) {
          JOptionPane.showMessageDialog(null, "El presupuesto debe ser mayor a 0");
          return;
        }

        Date inicio = fechaInicioPanel.getDate();
        Date fin = fechaFinPanel.getDate();

        // Convertir java.sql.Date a LocalDate
        LocalDate localDateInicio = inicio.toLocalDate();
        LocalDate localDateFin = fin.toLocalDate();

        // Obtener el Instant desde LocalDate (ahora es posible)
        Instant instantInicio = localDateInicio.atStartOfDay(ZoneId.systemDefault()).toInstant();
        Instant instantFin = localDateFin.atStartOfDay(ZoneId.systemDefault()).toInstant();

        // Convertir el Instant a LocalDate
        LocalDate fechaInicio = instantInicio.atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate fechaFin = instantFin.atZone(ZoneId.systemDefault()).toLocalDate();

        long duracionEnDias = ChronoUnit.DAYS.between(fechaInicio, fechaFin);
        float duracionEnMeses = duracionEnDias / 30.44f; // Promedio de días por mes

        // Calculamos la terminación en meses desde el inicio hasta la fecha actual
        LocalDate hoy = LocalDate.now();
        long terminacionEnDias = ChronoUnit.DAYS.between(fechaInicio, hoy);
        float terminacionEnMeses = terminacionEnDias / 30.44f;

        // Calculamos el porcentaje de avance
        float porcentajeAvance = 0;

        BaseDeDatos db = new BaseDeDatos();
        Proyecto nuevoProyecto = new Proyecto(nombre, area, presupuesto, inicio, fin, duracionEnMeses,
            terminacionEnMeses,
            porcentajeAvance);
        int id = db.proyectos.insertar(nuevoProyecto);

        for (int i = 0; i < checkBoxes.length; i++) {
          if (checkBoxes[i].isSelected()) {
            db.asignacionCientificosProyectos.asignarCientificoProyecto(cientificos[i].id, id);
          }
        }

        gridProyectos.onChange(db.proyectos.lista());
        dialog.dispose();
      }
    });

    JButton botonCancelar = new JButton("Cancelar");
    botonCancelar.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        dialog.dispose();
      }
    });

    dialog.add(botonCrear);
    dialog.add(botonCancelar);

    dialog.setSize(500, 400);
    dialog.setLocationRelativeTo(parent);
  }

  public void mostrar() {
    dialog.setVisible(true);
  }

}
