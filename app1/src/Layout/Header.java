package Layout;

import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;

import Components.Button;
import Components.SubTitle;
import Data.Constants;

public class Header extends Div {

  public Header(String Section, ActionListener event) {
    setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    setBorder(Constants.BORDERS);
    add(new SubTitle(Section));
    add(Box.createHorizontalGlue());
    Button $Button = new Button("Nuevo");
    $Button.addActionListener(event);
    add($Button);

  }
}
