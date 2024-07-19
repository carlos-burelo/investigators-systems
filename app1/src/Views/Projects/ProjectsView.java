package Views.Projects;

import Layout.Header;
import Layout.View;
import State.Window.W;

public class ProjectsView extends View {

  public ProjectsView() {
    super("Proyectos");
    Header $Header = new Header("Lista de proyectos", e -> {
      W.navigate(this, new NewProjectView());
    });
    add($Header);
    add(new ProjectList());
  }
}
