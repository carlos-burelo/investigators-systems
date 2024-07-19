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
import DB.Models.Advance;
import DB.Models.Project;
import DB.Models.Scientific;
import Data.Fields;
import Data.Palette;
import Layout.Div;
import Layout.View;
import State.Window.W;

public class EditProjectView extends View {
  ScientificCheckBoxList $CheckBoxList;

  public EditProjectView(Project project) {
    super("Editar proyecto");
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    setAlignmentX(CENTER_ALIGNMENT);

    Div $Form = new Div();
    $Form.setLayout(new GridLayout(1, 3, 10, 10));

    Div $ProjectForm = new Div();
    $ProjectForm.setLayout(new BoxLayout($ProjectForm, BoxLayout.Y_AXIS));
    $ProjectForm.setAlignmentX(CENTER_ALIGNMENT);

    Input $NameInput = new Input("Nombre");
    $NameInput.setValue(project.name);
    $ProjectForm.add($NameInput);

    DropDown<String> $CategoryInput = new DropDown<String>("Area", Fields.AREAS);
    $CategoryInput.setValue(project.area);
    $ProjectForm.add($CategoryInput);

    Input $MontoInput = new Input("Monto de financiamiento").number();
    $MontoInput.setValue(String.format("%.0f", project.financing));
    $ProjectForm.add($MontoInput);

    Div $Buttons = new Div();
    $Buttons.setLayout(new BoxLayout($Buttons, BoxLayout.X_AXIS));
    $Buttons.setAlignmentX(CENTER_ALIGNMENT);

    DatePicker $StartDate = new DatePicker("Fecha de inicio");
    $StartDate.setDate(project.start);
    DatePicker $EndDate = new DatePicker("Fecha de finalización");
    $EndDate.setDate(project.end);

    $ProjectForm.add($StartDate);
    $ProjectForm.add($EndDate);

    Button $SubmitButton = new Button("💾 Guardar");
    Button $CancelButton = new Button("❌ Cancelar").bg(Palette.ROSE, Palette.DARK_ROSE);

    $CancelButton.addActionListener(
        e -> {
          W.navigate(this, new ProjectsView());
        });

    $SubmitButton.addActionListener(
        e -> {
          int id = project.id;
          String name = $NameInput.getValue();
          String area = $CategoryInput.getValue();
          float financing = Float.parseFloat($MontoInput.getValue());
          Date start = $StartDate.getValue();
          Date end = $EndDate.getValue();
          Project updatedProject = new Project()
              .$id(id)
              .$name(name)
              .$area(area)
              .$financing(financing)
              .$advance(project.advance)
              .$termination(project.termination)
              .$start(start)
              .$end(end);

          db.projects.updateProject(updatedProject);
          Scientific[] selectedScientifics = $CheckBoxList.getItems();
          for (Scientific scientific : selectedScientifics) {
            if (scientific.selected) {
              System.out.println("Assigning " + scientific.name + " to " + project.name);
              db.projectScientific.assignScientificToProject(project.id, scientific.id);
            } else {
              System.out.println("Removing " + scientific.name + " from " + project.name);
              db.projectScientific.removeScientificFromProject(project.id, scientific.id);
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

    Div $ScientificForm = new Div();

    $ScientificForm.setLayout(new BoxLayout($ScientificForm, BoxLayout.Y_AXIS));

    Scientific[] allScientifics = db.projectScientific.getAllScientificAndSelectByProjectId(project.id);

    $CheckBoxList = new ScientificCheckBoxList(allScientifics);

    $ScientificForm.add($CheckBoxList);

    Div $AdvanceForm = new Div();
    $AdvanceForm.setLayout(new BoxLayout($AdvanceForm, BoxLayout.Y_AXIS));

    Advance[] Advances = db.advances.getAdvancesByProjectId(project.id);

    AdvanceList $AdvanceList = new AdvanceList(Advances);
    NewAdvance $NewAdvance = new NewAdvance(project);

    $AdvanceForm.add($NewAdvance);
    $AdvanceForm.add($AdvanceList);

    $Form.add($ProjectForm);
    $Form.add($ScientificForm);
    $Form.add($AdvanceForm);

    JScrollPane $Scroll = new JScrollPane($Form);
    $Scroll.getVerticalScrollBar().setUnitIncrement(16);
    add($Scroll);
  }
}
