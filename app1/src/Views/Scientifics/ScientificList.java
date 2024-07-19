package Views.Scientifics;

import java.awt.GridLayout;
import java.util.Arrays;

import Data.Constants;
import Layout.Div;
import DB.db;
import DB.Models.Scientific;
import State.Observer;

public class ScientificList extends Div implements Observer<Scientific[]> {
  public ScientificList() {
    setLayout(new GridLayout(0, 1, 10, 10));
    setBorder(Constants.BORDERS);

    Scientific[] scientifics = db.scientifics.getAllScientifics();

    if (scientifics.length > 0) {
      update(scientifics);
    }
  }

  @Override
  public void update(Scientific[] scientifics) {
    removeAll();
    Arrays.stream(scientifics).map(ScientificCard::new).forEach(this::add);
  }

}
