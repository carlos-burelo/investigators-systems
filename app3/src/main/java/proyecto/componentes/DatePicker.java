package proyecto.componentes;

import java.awt.GridLayout;
import java.sql.Date;
import java.time.LocalDate;

import javax.swing.JComboBox;
import javax.swing.JPanel;

public class DatePicker extends JPanel {

  JComboBox<Integer> dia = new JComboBox<>();
  JComboBox<Integer> mes = new JComboBox<>();
  JComboBox<Integer> año = new JComboBox<>();

  public DatePicker() {
    setLayout(new GridLayout(1, 3));

    for (int i = 1; i <= 31; i++) {
      dia.addItem(i);
    }

    for (int i = 1; i <= 12; i++) {
      mes.addItem(i);
    }

    for (int i = 2023; i <= 2030; i++) {
      año.addItem(i);
    }

    add(dia);
    add(mes);
    add(año);
  }

  public int getDay() {
    return (int) dia.getSelectedItem();
  }

  public int getMonth() {
    return (int) mes.getSelectedItem();
  }

  public int getYear() {
    return (int) año.getSelectedItem();
  }

  public Date getDate() {
    return Date.valueOf(LocalDate.of(getYear(), getMonth(), getDay()));
  }

  public void setDate(Date date) {
    LocalDate localDate = date.toLocalDate();
    dia.setSelectedItem(localDate.getDayOfMonth());
    mes.setSelectedItem(localDate.getMonthValue());
    año.setSelectedItem(localDate.getYear());
  }
}