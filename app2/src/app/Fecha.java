package app;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JComboBox;

public class Fecha extends Contenedor {
  public Fecha(String label) {
    setBorder(Valores.MARGENES);
    setLayout(new GridLayout(2, 1, 0, 0));
    setAlignmentX(CENTER_ALIGNMENT);
    setAlignmentY(CENTER_ALIGNMENT);

    SubTitulo $Label = new SubTitulo(label);
    $Label.setAlignmentX(CENTER_ALIGNMENT);
    $Label.setAlignmentY(CENTER_ALIGNMENT);
    add($Label);

    Contenedor $Date = new Contenedor();
    $Date.setLayout(new GridLayout(1, 3, 10, 10));

    JComboBox<Integer> $Day = new JComboBox<Integer>();
    $Day.setFont(Valores.FUENTE_BASE);
    $Day.setMaximumSize(new Dimension(50, 50));
    $Day.setBackground(Colores.BASE);
    JComboBox<Integer> $Month = new JComboBox<Integer>();
    $Month.setFont(Valores.FUENTE_BASE);
    $Month.setMaximumSize(new Dimension(50, 50));
    $Month.setBackground(Colores.BASE);
    JComboBox<Integer> $Year = new JComboBox<Integer>();
    $Year.setFont(Valores.FUENTE_BASE);
    $Year.setMaximumSize(new Dimension(50, 50));
    $Year.setBackground(Colores.BASE);

    for (int i = 1; i <= 31; i++) {
      $Day.addItem(i);
    }

    $Month.addItem(1);
    $Month.addItem(2);
    $Month.addItem(3);
    $Month.addItem(4);
    $Month.addItem(5);
    $Month.addItem(6);
    $Month.addItem(7);
    $Month.addItem(8);
    $Month.addItem(9);
    $Month.addItem(10);
    $Month.addItem(11);
    $Month.addItem(12);

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
    int day = (int) ((JComboBox<Integer>) ((Contenedor) getComponent(1)).getComponent(0)).getSelectedItem();
    int month = ((JComboBox<String>) ((Contenedor) getComponent(1)).getComponent(1)).getSelectedIndex();
    int year = (int) ((JComboBox<Integer>) ((Contenedor) getComponent(1)).getComponent(2)).getSelectedItem();

    Calendar calendar = Calendar.getInstance();
    calendar.set(year, month, day);
    return calendar.getTime();
  }

  public java.sql.Date getValue() {
    return new java.sql.Date(getDate().getTime());
  }

  @SuppressWarnings("unchecked")
  public void setDate(java.sql.Date fecha) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(fecha);
    int day = calendar.get(Calendar.DAY_OF_MONTH);
    int month = calendar.get(Calendar.MONTH);
    int year = calendar.get(Calendar.YEAR);

    ((JComboBox<Integer>) ((Contenedor) getComponent(1)).getComponent(0)).setSelectedItem(day);
    ((JComboBox<String>) ((Contenedor) getComponent(1)).getComponent(1)).setSelectedIndex(month);
    ((JComboBox<Integer>) ((Contenedor) getComponent(1)).getComponent(2)).setSelectedItem(year);
  }

}
