package app;

import javax.swing.table.DefaultTableModel;

public class ModeloCientifico {
  public int id;
  public String nombre;
  public boolean selected;
  public String correo;
  public String telefono;
  public String categoria;
  public String grado_academico;

  public ModeloCientifico $nombre(String nombre) {
    this.nombre = nombre;
    return this;
  }

  public ModeloCientifico $selected(boolean selected) {
    this.selected = selected;
    return this;
  }

  public ModeloCientifico $correo(String correo) {
    this.correo = correo;
    return this;
  }

  public ModeloCientifico $telefono(String telefono) {
    this.telefono = telefono;
    return this;
  }

  public ModeloCientifico $categoria(String categoria) {
    this.categoria = categoria;
    return this;
  }

  public ModeloCientifico $grado_academico(String grado_academico) {
    this.grado_academico = grado_academico;
    return this;
  }

  public ModeloCientifico $id(int id) {
    this.id = id;
    return this;
  }

  public ModeloCientifico $selected(int selected) {
    this.selected = selected == 1;
    return this;
  }

  public static DefaultTableModel createTableModel(ModeloCientifico[] cientificos) {
    DefaultTableModel tableModel = new DefaultTableModel();
    String[] columnas = { "ID", "Nombre", "Email", "Categoria", "Telefono", "Grado", "#", "X" };
    tableModel.setColumnIdentifiers(columnas);

    for (ModeloCientifico cientifico : cientificos) {
      Object[] fila = { cientifico.id, cientifico.nombre, cientifico.correo, cientifico.categoria, cientifico.telefono,
          cientifico.grado_academico, "#", "X" };
      tableModel.addRow(fila);
    }
    return tableModel;
  }

}
