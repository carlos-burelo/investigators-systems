package proyecto.componentes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ListaDeCheck<T> extends JPanel {
  private JComboBox<T> comboBox;
  private JCheckBox[] checkBoxes;

  public ListaDeCheck(T[] items) {
    setLayout(new BorderLayout());

    comboBox = new JComboBox<>(items);

    JPanel checkboxesPanel = new JPanel();
    checkboxesPanel.setLayout(new GridLayout(items.length, 1));

    checkBoxes = new JCheckBox[items.length];
    for (int i = 0; i < items.length; i++) {
      checkBoxes[i] = new JCheckBox(items[i].toString());
      checkBoxes[i].setVisible(false);
      checkboxesPanel.add(checkBoxes[i]);
    }

    comboBox.addItemListener(new ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
          for (JCheckBox checkBox : checkBoxes) {
            checkBox.setVisible(true);
          }
        } else {
          for (JCheckBox checkBox : checkBoxes) {
            checkBox.setVisible(false);
            checkBox.setSelected(false);
          }
        }
      }
    });

    add(comboBox, BorderLayout.NORTH);
    add(checkboxesPanel, BorderLayout.CENTER);
  }

  public void setItemChecked(int index, boolean checked) {
    if (index >= 0 && index < checkBoxes.length) {
      checkBoxes[index].setSelected(checked);
    }
  }

  public boolean isItemChecked(int index) {
    if (index >= 0 && index < checkBoxes.length) {
      return checkBoxes[index].isSelected();
    }
    return false;
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        JFrame frame = new JFrame("Lista de Checkboxes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(250, 150);

        String[] items = { "Item 1", "Item 2", "Item 3", "Item 4" };
        ListaDeCheck<String> listaDeCheck = new ListaDeCheck<>(items);

        listaDeCheck.setItemChecked(1, true); // Marcar el ítem 2 por defecto

        frame.add(listaDeCheck);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
      }
    });
  }
}
