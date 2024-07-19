package Components;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JLabel;

import Data.Constants;
import Data.Palette;
import Layout.Div;

public class DatePicker extends Div {
  public DatePicker(String label) {

    setLayout(new GridLayout(2, 1));
    setMaximumSize(Constants.MIN_FIELD_SIZE);
    setBorder(Constants.FIELDS_BORDERS);
    setAlignmentX(CENTER_ALIGNMENT);
    setAlignmentY(CENTER_ALIGNMENT);

    JLabel $Label = new JLabel(label);
    $Label.setAlignmentX(CENTER_ALIGNMENT);
    $Label.setAlignmentY(CENTER_ALIGNMENT);
    add($Label);

    Div $Date = new Div();
    $Date.setLayout(new GridLayout(1, 3));
    $Date.setAlignmentX(CENTER_ALIGNMENT);

    JComboBox<Integer> $Day = new JComboBox<Integer>();
    $Day.setMaximumSize(new Dimension(50, 50));
    $Day.setBackground(Palette.WHITE);
    JComboBox<String> $Month = new JComboBox<String>();
    $Month.setMaximumSize(new Dimension(100, 50));
    $Month.setBackground(Palette.WHITE);
    JComboBox<Integer> $Year = new JComboBox<Integer>();
    $Year.setMaximumSize(new Dimension(50, 50));
    $Year.setBackground(Palette.WHITE);

    for (int i = 1; i <= 31; i++) {
      $Day.addItem(i);
    }

    $Month.addItem("Enero");
    $Month.addItem("Febrero");
    $Month.addItem("Marzo");
    $Month.addItem("Abril");
    $Month.addItem("Mayo");
    $Month.addItem("Junio");
    $Month.addItem("Julio");
    $Month.addItem("Agosto");
    $Month.addItem("Septiembre");
    $Month.addItem("Octubre");
    $Month.addItem("Noviembre");
    $Month.addItem("Diciembre");

    // GET CURRENT YEAR
    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
    int maxYear = currentYear + 10;
    int currentMonth = Calendar.getInstance().get(Calendar.MONTH);
    int currentDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

    for (int i = currentYear; i <= maxYear; i++) {
      $Year.addItem(i);
    }

    $Day.setSelectedItem(currentDay);
    $Month.setSelectedIndex(currentMonth);
    $Year.setSelectedItem(currentYear);

    $Date.add($Day);
    $Date.add($Month);
    $Date.add($Year);

    $Date.setMaximumSize(new Dimension(210, 40));
    $Date.setAlignmentX(CENTER_ALIGNMENT);
    $Date.setAlignmentY(CENTER_ALIGNMENT);

    add($Date);
  }

  @SuppressWarnings("unchecked")
  public Date getDate() {
    int day = (int) ((JComboBox<Integer>) ((Div) getComponent(1)).getComponent(0)).getSelectedItem();
    int month = ((JComboBox<String>) ((Div) getComponent(1)).getComponent(1)).getSelectedIndex();
    int year = (int) ((JComboBox<Integer>) ((Div) getComponent(1)).getComponent(2)).getSelectedItem();

    Calendar calendar = Calendar.getInstance();
    calendar.set(year, month, day);
    return calendar.getTime();
  }

  public java.sql.Date getValue() {
    return new java.sql.Date(getDate().getTime());
  }

  @SuppressWarnings("unchecked")
  public void setDate(java.sql.Date date) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    int day = calendar.get(Calendar.DAY_OF_MONTH);
    int month = calendar.get(Calendar.MONTH);
    int year = calendar.get(Calendar.YEAR);

    ((JComboBox<Integer>) ((Div) getComponent(1)).getComponent(0)).setSelectedItem(day);
    ((JComboBox<String>) ((Div) getComponent(1)).getComponent(1)).setSelectedIndex(month);
    ((JComboBox<Integer>) ((Div) getComponent(1)).getComponent(2)).setSelectedItem(year);
  }

}
