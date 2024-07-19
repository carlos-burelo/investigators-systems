package Views.Projects;

import java.awt.GridLayout;
import java.util.Arrays;

import Data.Constants;
import Layout.Div;
import State.Observer;
import DB.db;
import DB.Models.Project;

public class ProjectList extends Div implements Observer<Project[]> {
  public ProjectList() {
    setLayout(new GridLayout(0, 1, 10, 10));
    setBorder(Constants.BORDERS);

    Project[] projects = db.projects.getAllProjects();

    if (projects.length > 0) {
      update(projects);
    }

  }

  @Override
  public void update(Project[] projects) {
    removeAll();
    Arrays.stream(projects).map(ProjectCard::new).forEach(this::add);
  }
}
