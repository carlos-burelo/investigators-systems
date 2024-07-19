package Views.Report;

import java.awt.GridLayout;

import javax.swing.JScrollPane;

import Components.List;
import Components.SubTitle;
import DB.db;
import DB.Models.Project;
import DB.Models.Scientific;
import DB.Tables.ProjectScientific.ScientificProject;
import Data.Constants;
import Layout.Div;
import Layout.View;

public class ReportView extends View {

  public ReportView() {
    super("Reportes");

    Div $Content = new Div();
    $Content.setLayout(new GridLayout(0, 1));
    $Content.setAlignmentX(LEFT_ALIGNMENT);
    $Content.setBorder(Constants.BORDERS);
    SubTitle $Titulo1 = new SubTitle("🟥 Cientificos por proyecto");
    $Content.add($Titulo1);

    ScientificProject[] scientifics_by_project = db.projectScientific.getScientificsByProyects();

    List<ScientificProject> $List = new List<ScientificProject>(
        scientifics_by_project,
        new String[] { "scientific_name", "project_name" },
        new String[] { "Nombre: %s", "Proyecto: %s" });
    $Content.add($List);

    SubTitle $Titulo2 = new SubTitle("🟥 Cientificos que han laborado por mas de 18 meses en un proyecto");
    $Content.add($Titulo2);

    Scientific[] scientifics_with_18_months = db.projectScientific.getScientificWith18MonthsWorking();

    List<Scientific> $List2 = new List<Scientific>(
        scientifics_with_18_months,
        new String[] { "name" },
        new String[] { "Nombre: %s" });
    $Content.add($List2);

    SubTitle $Titulo3 = new SubTitle("🟥 Proyectos con un monto asignado mayor a 250,000");
    $Content.add($Titulo3);

    List<Project> $List3 = new List<Project>(
        db.projects.getProjectsWith250kOrMore(),
        new String[] { "name", "financing" },
        new String[] { "Nombre: %s", "Financiamiento: %s" });

    $Content.add($List3);

    Project[] scientifics = db.projects.getAdvancesAndProjects();

    SubTitle $Titulo4 = new SubTitle("🟥 Avances y areas de investigacion de los proyecto");
    $Content.add($Titulo4);

    List<Project> $List4 = new List<Project>(
        scientifics,
        new String[] { "name", "advance", "area", },
        new String[] { "Nombre: %s", "Avance: %s", "Area: %s" });

    $Content.add($List4);
    JScrollPane $Scroll = new JScrollPane($Content);
    $Scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    $Scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    add($Scroll);
  }
}
