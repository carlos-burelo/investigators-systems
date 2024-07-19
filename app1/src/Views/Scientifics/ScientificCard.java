package Views.Scientifics;

import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import Components.Button;
import Components.SubTitle;
import Components.Title;
import DB.db;
import DB.Models.Scientific;
import Data.Constants;
import Data.Palette;
import Layout.Div;
import State.Window.W;

public class ScientificCard extends Div {
  public ScientificCard(Scientific scientific) {
    setLayout(new GridLayout(3, 2));
    setBackground(Palette.GREEN);
    setBorder(Constants.BORDERS);
    Title $Name = new Title(scientific.name);
    $Name.setForeground(Palette.WHITE);
    $Name.setAlignmentY(CENTER_ALIGNMENT);
    add($Name);

    SubTitle $Category = new SubTitle("Categoría: " + scientific.category);
    $Category.setForeground(Palette.WHITE);
    $Category.setAlignmentY(CENTER_ALIGNMENT);
    add($Category);

    SubTitle $Grade = new SubTitle("Grado: " + scientific.grade);
    $Grade.setForeground(Palette.WHITE);
    $Grade.setAlignmentY(CENTER_ALIGNMENT);
    add($Grade);

    SubTitle $Email = new SubTitle("Email: " + scientific.email);
    $Email.setForeground(Palette.WHITE);
    $Email.setAlignmentY(CENTER_ALIGNMENT);
    add($Email);

    SubTitle $Phone = new SubTitle("Teléfono: " + scientific.phone);
    $Phone.setForeground(Palette.WHITE);
    $Phone.setAlignmentY(CENTER_ALIGNMENT);
    add($Phone);

    Button $Edit = new Button("Eliminar");
    $Edit.setForeground(Palette.WHITE);
    $Edit.setAlignmentY(CENTER_ALIGNMENT);

    $Edit.addActionListener(e -> {
      String msg = "Eliminar el proyecto: " + scientific.name + "?";
      int response = JOptionPane.showConfirmDialog(null, msg);
      if (response == JOptionPane.YES_OPTION) {
        db.scientifics.deleteScientific(scientific.id);
        W.navigate(this, new ScientificView());
      }
    });

    add($Edit);

    addMouseListener(
        new MouseAdapter() {
          public void mouseEntered(MouseEvent evt) {
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            setBackground(Palette.DARK_GREEN);
          }

          public void mouseExited(MouseEvent evt) {
            setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            setBackground(Palette.GREEN);
          }

          public void mousePressed(MouseEvent evt) {
            setBackground(Palette.DARK_GREEN);
            W.navigate(this, new EditScientificView(scientific));
          }
        });
  }
}
