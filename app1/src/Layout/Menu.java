package Layout;

import java.awt.event.ActionListener;
import Components.Tab;
import Data.Constants;

public class Menu extends Div {
  public Menu(ActionListener projectEvent, ActionListener scientificEvent, ActionListener reportEvent) {
    setBorder(Constants.BORDERS);
    add(new Tab("📦 Proyectos", projectEvent));
    add(new Tab("🧑‍🔬 Cientificos", scientificEvent));
    add(new Tab("📊 Reportes", reportEvent));
  }
}
