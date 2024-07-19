
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import app.Boton;
import app.Contenedor;
import app.Contraseña;
import app.Entrada;
import app.Valores;

public class VentanaIniciarSesion extends JFrame {
  public VentanaIniciarSesion() {
    super("Login");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(300, 250);
    setVisible(true);
    Contenedor $Form = new Contenedor();
    $Form.setBorder(Valores.MARGENES);
    $Form.setAlignmentX(CENTER_ALIGNMENT);
    $Form.setLayout(new BoxLayout($Form, BoxLayout.Y_AXIS));

    Entrada $User = new Entrada("Usuario");
    Contraseña $Password = new Contraseña("Contraseña");

    $Form.add($User);
    $Form.add($Password);

    Boton $Boton = new Boton("INICIAR SESION");
    $Boton.addActionListener(e -> {

      String DEFAULT_USER = "root";
      String DEFAULT_PASSWORD = "root";

      String user = $User.getValue();
      String password = $Password.getValue();
      if (user.equals(DEFAULT_USER) && password.equals(DEFAULT_PASSWORD)) {
        new Main();
        dispose();
        return;
      } else {
        JOptionPane.showMessageDialog(null, "Datos de sesion incorrectos", "Error", JOptionPane.ERROR_MESSAGE);

      }
    });

    $Form.add($Boton);
    add($Form);
  }
}
