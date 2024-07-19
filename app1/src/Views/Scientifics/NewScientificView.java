package Views.Scientifics;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;

import Components.Button;
import Components.DropDown;
import Components.Input;
import DB.db;
import DB.Models.Scientific;
import Data.Fields;
import Data.Palette;
import Layout.Div;
import Layout.View;
import State.Window.W;

public class NewScientificView extends View {

  public NewScientificView() {
    super("Nuevo científico");

    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    setAlignmentX(CENTER_ALIGNMENT);

    Div $Form = new Div();
    $Form.setLayout(new BoxLayout($Form, BoxLayout.Y_AXIS));
    $Form.setAlignmentX(CENTER_ALIGNMENT);

    Input $NameInput = new Input("Nombre");
    $Form.add($NameInput);

    Input $PhoneInput = new Input("Teléfono").phone();
    $Form.add($PhoneInput);

    Input $EmailInput = new Input("Correo electrónico").email();
    $Form.add($EmailInput);

    DropDown<String> $CategoryInput = new DropDown<String>("Categoría", Fields.CATEGORIES);
    $Form.add($CategoryInput);

    DropDown<String> $GradeInput = new DropDown<String>("Grado", Fields.GRADES);
    $Form.add($GradeInput);

    Div $Buttons = new Div();
    $Buttons.setLayout(new BoxLayout($Buttons, BoxLayout.X_AXIS));
    $Buttons.setAlignmentX(CENTER_ALIGNMENT);

    Button $SubmitButton = new Button("💾 Guardar");
    Button $CancelButton = new Button("❌ Cancelar").bg(Palette.ROSE, Palette.DARK_ROSE);

    $CancelButton.addActionListener(
        e -> {
          W.navigate(this, new ScientificView());
        });

    $SubmitButton.addActionListener(
        e -> {
          String name = $NameInput.getValue();
          String phone = $PhoneInput.getValue();
          String email = $EmailInput.getValue();
          String category = $CategoryInput.getValue();
          String grade = $GradeInput.getValue();

          if (name.isEmpty() || phone.isEmpty() || email.isEmpty() || category.isEmpty() || grade.isEmpty()) {
            W.alert("Todos los campos son obligatorios");
            return;
          }

          Scientific scientific = new Scientific()
              .$name(name)
              .$phone(phone)
              .$email(email)
              .$category(category)
              .$grade(grade);

          db.scientifics.insertScientific(scientific);
          W.navigate(this, new ScientificView());
        });

    $Buttons.add(Box.createHorizontalGlue());
    $Buttons.add(Box.createHorizontalGlue());
    $Buttons.add($CancelButton);
    $Buttons.add(Box.createHorizontalGlue());
    $Buttons.add($SubmitButton);
    $Buttons.add(Box.createHorizontalGlue());
    $Buttons.add(Box.createHorizontalGlue());

    $Form.add($Buttons);
    JScrollPane $Scroll = new JScrollPane($Form);
    $Scroll.getVerticalScrollBar().setUnitIncrement(16);
    add($Scroll);
  }
}
