package Views.Projects;

import java.awt.GridLayout;
import java.sql.Date;

import javax.swing.JOptionPane;

import Components.Button;
import Components.DatePicker;
import Components.Input;
import DB.db;
import DB.Models.Advance;
import DB.Models.Project;
import Data.Constants;
import Layout.Div;
import State.Window.W;

public class NewAdvance extends Div {
  private final Project project;

  public NewAdvance(Project project) {
    this.project = project;
    setLayout(new GridLayout(3, 1));
    setBorder(Constants.BORDERS);
    initializeComponents();
  }

  private void initializeComponents() {
    DatePicker $Date = new DatePicker("Fecha");
    add($Date);

    Input $Description = new Input("Descripción");
    add($Description);

    Button $SubmitButton = new Button("💾 Guardar avance");

    $SubmitButton.addActionListener(e -> onSave($Date, $Description));

    add($SubmitButton);
  }

  private void onSave(DatePicker $Date, Input $Description) {
    Date currentDate = $Date.getValue();
    Date startDate = project.start;
    Date endDate = project.end;

    if (!validateDate(currentDate, startDate, endDate) || !validateDescription($Description.getValue())) {
      return;
    }

    float duration = (float) (endDate.getTime() - startDate.getTime());
    float advanceDuration = (float) (currentDate.getTime() - startDate.getTime());
    float advancePercentage = advanceDuration / duration * 100;

    Advance advance = new Advance()
        .$date(currentDate)
        .$description($Description.getValue())
        .$project_id(project.id);

    project.$advance(advancePercentage);

    db.advances.insertAdvance(advance);
    db.projects.updateProject(project);

    W.navigate(this, new ProjectsView());
  }

  private boolean validateDate(Date date, Date startDate, Date endDate) {
    if (date.before(startDate) || date.after(endDate)) {
      String min = startDate.toString();
      String max = endDate.toString();
      String message = String.format("La fecha debe estar entre %s y %s", min, max);
      JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
      return false;
    }
    return true;
  }

  private boolean validateDescription(String description) {
    if (description.isEmpty()) {
      JOptionPane.showMessageDialog(null, "La descripción no puede estar vacía", "Error", JOptionPane.ERROR_MESSAGE);
      return false;
    }
    return true;
  }
}
