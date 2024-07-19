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
import javax.swing.JTextField;

import proyecto.datos.BaseDeDatos;
import proyecto.datos.Cientificos.Cientifico;
import proyecto.datos.Globales;

public class ModalEditarCientifico {

  private JDialog dialog;
  private JTextField campoNombreCientifico;
  private JTextField campoTelefono;
  private JTextField campoEmail;

  public ModalEditarCientifico(JFrame parent, Cientifico cientifico, GridCientificos gridCientificos) {
    dialog = new JDialog(parent, "Editar cientifico", true);
    dialog.setBackground(Color.WHITE);
    dialog.setLayout(new GridLayout(0, 2));

    JLabel IdLabel = new JLabel("ID");
    dialog.add(IdLabel);
    JTextField campoID = new JTextField();
    campoID.setText(String.valueOf(cientifico.id));
    campoID.setEditable(false);

    dialog.add(campoID);

    JLabel nombreDelCientifico = new JLabel("Nombre del cientifico");
    dialog.add(nombreDelCientifico);
    campoNombreCientifico = new JTextField();
    campoNombreCientifico.setText(cientifico.nombre);
    dialog.add(campoNombreCientifico);

    JLabel telefono = new JLabel("Teléfono");
    dialog.add(telefono);
    campoTelefono = new JTextField();
    campoTelefono.setText(cientifico.telefono);
    dialog.add(campoTelefono);

    JLabel email = new JLabel("Email");
    dialog.add(email);
    campoEmail = new JTextField();
    campoEmail.setText(cientifico.email);
    dialog.add(campoEmail);

    JLabel gradoAcademico = new JLabel("Grado académico");
    dialog.add(gradoAcademico);
    JComboBox<String> gradoAcademicoField = new JComboBox<>(Globales.GRADOS);
    gradoAcademicoField.setSelectedItem(cientifico.grado_academico);
    dialog.add(gradoAcademicoField);

    JLabel categoria = new JLabel("Categoría");
    dialog.add(categoria);

    JComboBox<String> categoriaField = new JComboBox<>(Globales.CATEGORIAS);
    categoriaField.setSelectedItem(cientifico.categoria);
    dialog.add(categoriaField);

    JButton botonCrear = new JButton("💾 Guardar");
    botonCrear.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {

        int id = campoID.getText().isEmpty() ? 0 : Integer.parseInt(campoID.getText());
        String nombre = campoNombreCientifico.getText();
        String grado = (String) gradoAcademicoField.getSelectedItem();
        String categoria = (String) categoriaField.getSelectedItem();
        String telefono = campoTelefono.getText();
        String email = campoEmail.getText();
        BaseDeDatos db = new BaseDeDatos();
        Cientifico nuevoCientifico = new Cientifico(id, nombre, telefono, email, grado, categoria);
        db.cientificos.actualizar(nuevoCientifico);
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
