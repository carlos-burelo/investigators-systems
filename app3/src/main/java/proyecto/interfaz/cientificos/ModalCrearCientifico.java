package proyecto.interfaz.cientificos;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import proyecto.datos.BaseDeDatos;
import proyecto.datos.Cientificos.Cientifico;
import proyecto.datos.Globales;

public class ModalCrearCientifico {

  private JDialog dialog;
  private JTextField campoNombreCientifico;
  private JTextField campoTelefono;
  private JTextField campoEmail;

  public ModalCrearCientifico(JFrame parent, GridCientificos gridCientificos) {
    dialog = new JDialog(parent, "Registrar nuevo cientifico", true);
    dialog.setBackground(Color.WHITE);
    dialog.setLayout(new GridLayout(0, 2));
    JLabel nombreDelCientifico = new JLabel("Nombre del cientifico");
    dialog.add(nombreDelCientifico);
    campoNombreCientifico = new JTextField();
    dialog.add(campoNombreCientifico);

    JLabel telefono = new JLabel("Teléfono");
    dialog.add(telefono);
    campoTelefono = new JTextField();
    dialog.add(campoTelefono);

    JLabel email = new JLabel("Email");
    dialog.add(email);
    campoEmail = new JTextField();
    dialog.add(campoEmail);

    JLabel gradoAcademico = new JLabel("Grado académico");
    dialog.add(gradoAcademico);
    JComboBox<String> gradoAcademicoField = new JComboBox<>(Globales.GRADOS);
    dialog.add(gradoAcademicoField);

    dialog.add(gradoAcademico);
    dialog.add(gradoAcademicoField);

    JLabel categoria = new JLabel("Categoría");
    dialog.add(categoria);

    JComboBox<String> categoriaField = new JComboBox<>(Globales.CATEGORIAS);
    dialog.add(categoriaField);

    JButton botonCrear = new JButton("💾 Guardar");
    botonCrear.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {

        String nombre = campoNombreCientifico.getText();

        if (nombre.isEmpty()) {
          JOptionPane.showMessageDialog(dialog, "El nombre del cientifico no puede estar vacío");
          return;
        }

        String grado = (String) gradoAcademicoField.getSelectedItem();
        String categoria = (String) categoriaField.getSelectedItem();
        String telefono = campoTelefono.getText();

        if (telefono.isEmpty()) {
          JOptionPane.showMessageDialog(dialog, "El teléfono del cientifico no puede estar vacío");
          return;
        }

        // validar que el teléfono sea un número

        if (telefono.matches("[0-9]+") == false) {
          JOptionPane.showMessageDialog(dialog, "El teléfono debe ser un número");
          return;
        }

        String email = campoEmail.getText();

        if (email.isEmpty()) {
          JOptionPane.showMessageDialog(dialog, "El email del cientifico no puede estar vacío");
          return;
        }

        BaseDeDatos db = new BaseDeDatos();
        Cientifico nuevoCientifico = new Cientifico(nombre, telefono, email, grado, categoria);
        db.cientificos.insertar(nuevoCientifico);
        gridCientificos.onChange(db.cientificos.obtenerCientificos());

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

    dialog.setSize(300, 200);
    dialog.setLocationRelativeTo(parent);
  }

  public void mostrar() {
    dialog.setVisible(true);
  }

}
