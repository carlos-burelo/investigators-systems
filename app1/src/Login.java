import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Components.Input;
import Data.Constants;
import Layout.Div;
import Components.Button;

public class Login extends JFrame {
  public Login() {
    super("Login");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(400, 400);
    setVisible(true);
    Div $Form = new Div();
    $Form.setBorder(Constants.BORDERS);
    $Form.setAlignmentX(CENTER_ALIGNMENT);
    $Form.setLayout(new GridLayout(3, 1));

    Input $User = new Input("Usuario");
    Input $Password = new Input("Contraseña");

    $Form.add($User);
    $Form.add($Password);

    Button $Button = new Button("Ingresar");
    $Button.addActionListener(e -> {

      String DEFAULT_USER = "admin";
      String DEFAULT_PASSWORD = "admin";

      String user = $User.getValue();
      String password = $Password.getValue();
      if (user.equals(DEFAULT_USER) && password.equals(DEFAULT_PASSWORD)) {
        new Main();
        dispose();
        return;
      } else {
        JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
      }
    });

    $Form.add($Button);
    add($Form);
  }
}
