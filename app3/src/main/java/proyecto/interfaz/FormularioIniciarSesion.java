package proyecto.interfaz;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import proyecto.constantes.Colores;

public class FormularioIniciarSesion extends JFrame {
  public FormularioIniciarSesion() {
    super("Iniciar sesion");
    setBackground(Colores.BLANCO);
    setLayout(new GridLayout(3, 1));

    JLabel nombreLabel = new JLabel("Nombre de usuario");
    JTextField camposNombre = new JTextField();

    add(nombreLabel);
    add(camposNombre);

    JLabel contraseñaLabel = new JLabel("Contraseña");
    JPasswordField campoContraseña = new JPasswordField();

    add(contraseñaLabel);
    add(campoContraseña);

    JButton botonIngresar = new JButton("Ingresar");
    botonIngresar.addActionListener(e -> {
      String nombreUsuario = camposNombre.getText();
      String contraseña = String.valueOf(campoContraseña.getPassword());

      String USUARIO = "admin";
      String PASS = "admin123";

      if (USUARIO.equals(nombreUsuario) && PASS.equals(contraseña)) {
        new VentanaPrincipal();
        dispose();
      } else {
        JOptionPane.showMessageDialog(null, "CREDENCIALES INCORRECTAS");
      }

    });
    add(botonIngresar);

    JButton botonCancelar = new JButton("Cancelar");
    add(botonCancelar);

    setSize(new Dimension(300, 200));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }
}
