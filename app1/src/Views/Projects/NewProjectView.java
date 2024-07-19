package Views.Projects;

import java.awt.GridLayout;
import java.sql.Date;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;

import Components.Button;
import Components.DatePicker;
import Components.DropDown;
import Components.Input;
import DB.db;
import DB.Models.Project;
import DB.Models.Scientific;
import Data.Fields;
import Data.Palette;
import Layout.Div;
import Layout.View;
import State.Window.W;
import Utils.Timer;

public class NewProjectView extends View {

  ScientificCheckBoxList $CheckBoxList;

  public NewProjectView() {
    super("Nuevo proyecto");

    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    Div $Form = new Div();

    $Form.setLayout(new GridLayout(1, 2, 10, 10));

    setAlignmentX(CENTER_ALIGNMENT);

    Div $ProjectForm = new Div();
    $ProjectForm.setLayout(new BoxLayout($ProjectForm, BoxLayout.Y_AXIS));
    $ProjectForm.setAlignmentX(CENTER_ALIGNMENT);

    Input $NameInput = new Input("Nombre");
    $ProjectForm.add($NameInput);

    DropDown<String> $CategoryInput = new DropDown<String>("Area", Fields.AREAS);
    $ProjectForm.add($CategoryInput);

    Input $MontoInput = new Input("Monto de financiamiento").number();
    $ProjectForm.add($MontoInput);

    Div $Buttons = new Div();
    $Buttons.setLayout(new BoxLayout($Buttons, BoxLayout.X_AXIS));
    $Buttons.setAlignmentX(CENTER_ALIGNMENT);

    DatePicker $StartDate = new DatePicker("Fecha de inicio");
    DatePicker $EndDate = new DatePicker("Fecha de finalización");

    $ProjectForm.add($StartDate);
    $ProjectForm.add($EndDate);

    Button $SubmitButton = new Button("💾 Guardar");
    Button $CancelButton = new Button("❌ Cancelar").bg(Palette.ROSE, Palette.DARK_ROSE);

    Div $ScientificForm = new Div();
    $ScientificForm.setLayout(new BoxLayout($ScientificForm, BoxLayout.Y_AXIS));

    Scientific[] allScientifics = db.scientifics.getAllScientifics();
    $CheckBoxList = new ScientificCheckBoxList(allScientifics);

    $ScientificForm.add($CheckBoxList);

    $CancelButton.addActionListener(e -> W.navigate(this, new ProjectsView()));

    $SubmitButton.addActionListener(
        e -> {
          String name = $NameInput.getValue();
          String area = $CategoryInput.getValue();
          String financing = $MontoInput.getValue();
          Date startDate = $StartDate.getValue();
          Date endDate = $EndDate.getValue();

          if (name.isEmpty() || area.isEmpty() || financing.isEmpty() || startDate == null || endDate == null) {
            return;
          }

          float duration = Timer.calculateDurationInMonths(startDate, endDate);

          Project project = new Project()
              .$name(name)
              .$area(area)
              .$financing(Float.parseFloat(financing))
              .$start(startDate)
              .$end(endDate)
              .$duration(duration) // meses de duracion
              .$termination(duration) // meses restantes
              .$advance(0); // avance

          db.projects.insertProject(project);
          Project insertedProject = db.projects.getLastProjectInserted();

          Scientific[] selectedScientifics = $CheckBoxList.getItems();

          for (Scientific scientific : selectedScientifics) {
            if (scientific.selected) {
              db.projectScientific.assignScientificToProject(insertedProject.id, scientific.id);
            }
          }
          W.navigate(this, new ProjectsView());
        });

    $Buttons.add(Box.createHorizontalGlue());
    $Buttons.add(Box.createHorizontalGlue());
    $Buttons.add($CancelButton);
    $Buttons.add(Box.createHorizontalGlue());
    $Buttons.add($SubmitButton);
    $Buttons.add(Box.createHorizontalGlue());
    $Buttons.add(Box.createHorizontalGlue());

    $ProjectForm.add($Buttons);
    $Form.add($ProjectForm);
    $Form.add($ScientificForm);

    JScrollPane $Scroll = new JScrollPane($Form);
    $Scroll.getVerticalScrollBar().setUnitIncrement(16);
    add($Scroll);
  }
}
