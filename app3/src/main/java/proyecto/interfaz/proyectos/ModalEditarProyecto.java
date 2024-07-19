package proyecto.interfaz.proyectos;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Period;

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

public class ModalEditarProyecto {
  private JDialog dialog;
  private JTextField nombreProyectoField;
  private JTextField areaField;
  private JTextField presupuestoField;
  private DatePicker fechaInicioPanel;
  private DatePicker fechaFinPanel;

  public ModalEditarProyecto(JFrame parent, Proyecto proyecto, GridProyectos gridProyectos) {
    dialog = new JDialog(parent, "Editar Proyecto", true);
    dialog.setBackground(Color.WHITE);
    dialog.setLayout(new GridLayout(7, 2));

    dialog.add(new JLabel("Nombre del Proyecto:"));
    nombreProyectoField = new JTextField();
    nombreProyectoField.setText(proyecto.nombre);
    dialog.add(nombreProyectoField);
    dialog.add(new JLabel("Área:"));
    areaField = new JTextField();
    areaField.setText(proyecto.area);
    dialog.add(areaField);

    NumberFormat format = NumberFormat.getIntegerInstance();
    NumberFormatter formatter = new NumberFormatter(format);
    formatter.setValueClass(Integer.class);
    formatter.setMinimum(0);

    dialog.add(new JLabel("Presupuesto:"));
    presupuestoField = new JFormattedTextField();
    presupuestoField.setText("" + proyecto.monto_financiamiento);
    dialog.add(presupuestoField);

    fechaInicioPanel = new DatePicker();
    fechaInicioPanel.setDate(proyecto.fecha_inicio);

    fechaFinPanel = new DatePicker();
    fechaFinPanel.setDate(proyecto.fecha_fin);

    dialog.add(new JLabel("Fecha de Inicio:"));
    dialog.add(fechaInicioPanel);

    dialog.add(new JLabel("Fecha de Fin:"));
    dialog.add(fechaFinPanel);

    BaseDeDatos db = new BaseDeDatos();

    Cientifico[] cientificos = db.cientificos.obtenerCientificos();
    Cientifico[] cientificosAsignados = db.asignacionCientificosProyectos.obtenerCientificosProyecto(proyecto.clave);

    JLabel labelCientificos = new JLabel("Cientificos:");
    dialog.add(labelCientificos);

    JScrollPane scrollPane = new JScrollPane();
    scrollPane.setPreferredSize(new Dimension(500, 200));
    JPanel panelChecks = new JPanel();
    panelChecks.setLayout(new BoxLayout(panelChecks, BoxLayout.Y_AXIS));
    // checkbox, value is scientifico.id and label is scientifico. nombre
    JCheckBox[] checkBoxes = new JCheckBox[cientificos.length];

    for (int i = 0; i < cientificos.length; i++) {
      Cientifico cientifico = cientificos[i];
      JCheckBox checkBox = new JCheckBox(cientifico.nombre);
      checkBoxes[i] = checkBox;
      checkBox.putClientProperty("cientifico", cientifico);
      checkBox.setSelected(false);
      for (Cientifico cientificoAsignado : cientificosAsignados) {
        if (cientificoAsignado.id == cientifico.id) {
          checkBox.setSelected(true);
        }
      }
      panelChecks.add(checkBox);
    }

    panelChecks.setVisible(true);

    scrollPane.setViewportView(panelChecks);
    dialog.add(scrollPane);

    JButton botonCrear = new JButton("💾 Guardar");
    botonCrear.setFont(new Font("Segoe UI Emoji", Font.BOLD, 15));
    botonCrear.addActionListener(e -> {
      String nombre = nombreProyectoField.getText();
      String area = areaField.getText();
      String presupuestoString = presupuestoField.getText();

      if (nombre.isEmpty() || area.isEmpty() || presupuestoString.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Por favor, completa todos los campos");
        return;
      }

      if (!presupuestoString.matches("([0-9]+([.][0-9]*)?|[.][0-9]+)")) {
        JOptionPane.showMessageDialog(null, "El presupuesto debe ser un número");
        return;
      }

      float presupuesto = Float.parseFloat(presupuestoField.getText());
      Date inicio = fechaInicioPanel.getDate();
      Date fin = fechaFinPanel.getDate();

      if (inicio.compareTo(fin) > 0) {
        JOptionPane.showMessageDialog(null, "La fecha de inicio debe ser menor a la fecha de fin");
        return;
      }

      Period periodo = Period.between(inicio.toLocalDate(), fin.toLocalDate());
      float duracionEnMeses = periodo.getMonths() + periodo.getYears() * 12;

      LocalDate hoy = LocalDate.now();
      Period periodoTranscurrido = Period.between(inicio.toLocalDate(), hoy);
      float terminacionEnMeses = periodoTranscurrido.getMonths() + periodoTranscurrido.getYears() * 12;

      float porcentajeAvance = proyecto.avances_meses;

      for (int i = 0; i < checkBoxes.length; i++) {
        JCheckBox checkBox = checkBoxes[i];
        Cientifico cientifico = (Cientifico) checkBox.getClientProperty("cientifico");
        if (checkBox.isSelected()) {
          db.asignacionCientificosProyectos.asignarCientificoProyecto(cientifico.id, proyecto.clave);
        } else {
          db.asignacionCientificosProyectos.eliminarCientificoProyecto(cientifico.id, proyecto.clave);
        }
      }

      Proyecto proyectoActualizado = new Proyecto(proyecto.clave, nombre, area, presupuesto, inicio, fin,
          duracionEnMeses, terminacionEnMeses, porcentajeAvance);
      db.proyectos.actualizar(proyectoActualizado);

      gridProyectos.onChange(db.proyectos.lista());
      dialog.dispose();
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
