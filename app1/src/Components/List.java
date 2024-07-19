package Components;

import java.awt.GridLayout;
import java.lang.reflect.Field;

import javax.swing.JList;
import javax.swing.JScrollPane;

import Data.Palette;
import Layout.Div;

public class List<T> extends Div {
  JList<String> $JList;
  JScrollPane $JScrollPane;

  public List(T[] items, String[] visibleFields, String[] formats) {
    setLayout(new GridLayout(1, 1));
    setBackground(Palette.WHITE);

    String[] listItems = new String[items.length];
    for (int i = 0; i < items.length; i++) {
      StringBuilder itemRepresentation = new StringBuilder();
      try {
        for (int j = 0; j < visibleFields.length; j++) {
          Field field = items[i].getClass().getDeclaredField(visibleFields[j]);
          field.setAccessible(true);

          String formattedValue = String.format(formats[j], field.get(items[i]));
          itemRepresentation.append(formattedValue).append(" ");
        }
        listItems[i] = itemRepresentation.toString().trim();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    $JList = new JList<>(listItems);
    add($JList);
    // $JScrollPane = new JScrollPane($JList);

    // $JScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    // $JScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    // add($JScrollPane);
  }
}

// package Components;

// import java.awt.GridLayout;
// import java.lang.reflect.Field;

// import javax.swing.JList;
// import javax.swing.JScrollPane;

// import Layout.Div;

// public class List<T> extends Div {
// JList<String> $JList;
// JScrollPane $JScrollPane;

// public List(T[] items, String visibleField) {
// setLayout(new GridLayout(1, 1));

// String[] list_items = new String[items.length];
// for (int i = 0; i < items.length; i++) {
// try {
// Field field = items[i].getClass().getDeclaredField(visibleField);
// field.setAccessible(true);
// list_items[i] = field.get(items[i]).toString();
// } catch (Exception e) {
// e.printStackTrace();
// }
// }

// $JList = new JList<String>(list_items);

// $JScrollPane = new JScrollPane($JList);

// $JScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

// $JScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

// add($JScrollPane);
// }
// }
