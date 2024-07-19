package Views.Scientifics;

import Layout.Header;
import Layout.View;
import State.Window.W;

public class ScientificView extends View {

  public ScientificView() {
    super("Cientificos");

    Header $Header = new Header("Lista de cientificos", e -> {
      W.navigate(this, new NewScientificView());
    });
    add($Header);
    add(new ScientificList());
  }
}
