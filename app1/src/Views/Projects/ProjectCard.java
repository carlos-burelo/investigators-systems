package Views.Projects;

import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import Components.Button;
import Components.SubTitle;
import Components.Title;
import DB.db;
import DB.Models.Project;
import Data.Constants;
import Data.Palette;
import Layout.Div;
import State.Window.W;

public class ProjectCard extends Div {

  public ProjectCard(Project project) {
    setLayout(new GridLayout(3, 2));
    setBackground(Palette.ROSE);
    setBorder(Constants.BORDERS);
    Title $Name = new Title(project.name);
    $Name.setForeground(Palette.WHITE);
    $Name.setAlignmentY(CENTER_ALIGNMENT);
    add($Name);

    SubTitle $Area = new SubTitle(project.area);
    $Area.setForeground(Palette.WHITE);
    $Area.setAlignmentY(CENTER_ALIGNMENT);
    add($Area);

    SubTitle $Monto = new SubTitle("Financiación: $" + String.format("%.0f", project.financing));
    $Monto.setForeground(Palette.WHITE);
    $Monto.setAlignmentY(CENTER_ALIGNMENT);
    add($Monto);

    String range = project.start.toString() + " - " + project.end.toString();
    SubTitle $Date = new SubTitle("Fecha: " + range);
    $Date.setForeground(Palette.WHITE);
    $Date.setAlignmentY(CENTER_ALIGNMENT);
    add($Date);

    SubTitle $Advance = new SubTitle("Avance: " + String.format("%.0f", project.advance) + "%");
    $Advance.setForeground(Palette.WHITE);
    $Advance.setAlignmentY(CENTER_ALIGNMENT);
    add($Advance);

    Button $Edit = new Button("Eliminar");
    $Edit.setForeground(Palette.WHITE);
    $Edit.setAlignmentY(CENTER_ALIGNMENT);

    $Edit.addActionListener(e -> {
      String msg = "Eliminar el proyecto: " + project.name + "?";
      int response = JOptionPane.showConfirmDialog(null, msg);
      if (response == JOptionPane.YES_OPTION) {
        db.projects.deleteProject(project.id);
        W.navigate(this, new ProjectsView());
      }
    });

    add($Edit);

    addMouseListener(
        new MouseAdapter() {
          public void mouseEntered(MouseEvent evt) {
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            setBackground(Palette.DARK_ROSE);
          }

          public void mouseExited(MouseEvent evt) {
            setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            setBackground(Palette.ROSE);
          }

          public void mousePressed(MouseEvent evt) {
            setBackground(Palette.DARK_ROSE);
            W.navigate(this, new EditProjectView(project));
          }
        });
  }
}
