package app;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import app.PanelCientificos.VistaCientificos;
import app.Tabla.CeldaTabla;
import app.Tabla.EncabezadoTabla;

public class InterfazTablaCientificos extends Contenedor implements Observador<ModeloCientifico[]> {

  private JTable tabla;

  public InterfazTablaCientificos() {
    setLayout(new GridLayout(1, 1));
    setBackground(Colores.BASE);
    actualizar(BaseDeDatos.cientificos.obtener_todo_los_cientificos());
  }

  @Override
  public void actualizar(ModeloCientifico[] cientificos) {
    DefaultTableModel model = ModeloCientifico.createTableModel(cientificos);
    tabla = new JTable(model) {
      @Override
      public boolean isCellEditable(int row, int column) {
        return false;
      }
    };

    tabla.removeAll();
    tabla.removeAll();
    tabla.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
    tabla.setRowHeight(30);
    tabla.setBackground(Colores.BASE);
    tabla.getTableHeader().setDefaultRenderer(new EncabezadoTabla());
    tabla.setDefaultRenderer(Object.class, new CeldaTabla());
    tabla.addMouseListener(new MouseAdapter() {

      public void mouseClicked(MouseEvent e) {
        int columna = tabla.getColumnModel().getColumnIndexAtX(e.getX());
        int fila = e.getY() / tabla.getRowHeight();
        if (fila >= 0 && columna >= 6 && columna <= 7) {
          int ID = (int) model.getValueAt(fila, 0);
          ModeloCientifico cientifico = BaseDeDatos.cientificos.obtener_cientifico_por_id(ID);
          if (columna == 6) {
            VistaCientificos.mostrar(InterfazTablaCientificos.this, new InterfazEditarCientifico(cientifico));
          }
          if (columna == 7) {
            String msg = "Quieres eliminar al cientifico: " + cientifico.nombre + "?";
            int opt = JOptionPane.showConfirmDialog(tabla, msg);
            if (opt == 0) {
              BaseDeDatos.cientificos.eliminar_cientifico(cientifico.id);
              VistaCientificos.mostrar(InterfazTablaCientificos.this, new InterfazContendorCientificos());
            }
          }
        }
      }
    });

    JScrollPane scrollPane = new JScrollPane(tabla);
    add(scrollPane);
  }

}
