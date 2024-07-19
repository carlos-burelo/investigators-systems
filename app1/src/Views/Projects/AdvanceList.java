package Views.Projects;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Components.SubTitle;
import DB.Models.Advance;
import Data.Constants;
import Layout.Div;

public class AdvanceList extends Div {

  public AdvanceList(Advance[] advances) {
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    setBorder(Constants.BORDERS);
    reload(advances);
  }

  public void reload(Advance[] advances) {
    JScrollPane $Scroll = new JScrollPane(this);
    JPanel $ScrollContent = new JPanel();
    SubTitle $Title = new SubTitle("Avances");
    $Title.setAlignmentX(LEFT_ALIGNMENT);
    add($Title);
    for (Advance advance : advances) {
      $ScrollContent.add(new AdvanceItem(advance));
    }
    $ScrollContent.setLayout(new BoxLayout($ScrollContent, BoxLayout.Y_AXIS));
    $Scroll.setViewportView($ScrollContent);
    $Scroll.setPreferredSize(new Dimension(400, 200));
    // ONLY SCROLL VERTICALLY
    $Scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    $Scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    add($Scroll);
  }
}

class AdvanceItem extends Div {

  public AdvanceItem(Advance advance) {
    SubTitle $Date = new SubTitle(advance.date.toString());
    $Date.setFont(Constants.FONT_BASE);
    SubTitle $Description = new SubTitle(advance.description);
    $Description.setFont(Constants.FONT_BASE);
    add($Date);
    add($Description);
  }
}