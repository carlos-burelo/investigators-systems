
package proyecto.interfaz.cientificos;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import proyecto.constantes.Colores;
import proyecto.datos.BaseDeDatos;
import proyecto.datos.Cientificos;
import proyecto.datos.Cientificos.Cientifico;
import proyecto.eventos.OnChange;

public class GridCientificos extends JPanel implements OnChange<Cientifico> {

  private JTable tablaCientificos;
  private DefaultTableModel tableModel;

  public GridCientificos(Cientifico[] cientificos) {
    setLayout(new GridLayout(1, 1));
    onChange(cientificos);
  }

  @Override
  public void onChange(Cientifico[] elementos) {
    tableModel = Cientificos.createTableModel(elementos);
    tablaCientificos = new JTable(tableModel) {
      @Override
      public boolean isCellEditable(int row, int column) {
        return false;
      }
    };

    tablaCientificos.removeAll();
    tablaCientificos.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 15));
    tablaCientificos.setRowHeight(30);
    tablaCientificos.setBackground(Colores.BLANCO);
    tablaCientificos.getTableHeader().setDefaultRenderer(new EncabezadoTabla());
    tablaCientificos.setDefaultRenderer(Object.class, new CeldaTabla());

    tablaCientificos.addMouseListener(new MouseAdapter() {

      public void mouseClicked(MouseEvent e) {
        int columna = tablaCientificos.getColumnModel().getColumnIndexAtX(e.getX());
        int fila = e.getY() / tablaCientificos.getRowHeight();
        if (fila >= 0 && columna >= 6 && columna <= 7) {
          int ID = (int) tableModel.getValueAt(fila, 0);
          String nombre = (String) tableModel.getValueAt(fila, 1);
          String email = (String) tableModel.getValueAt(fila, 2);
          String categoria = (String) tableModel.getValueAt(fila, 3);
          String telefono = (String) tableModel.getValueAt(fila, 4);
          String grado_academico = (String) tableModel.getValueAt(fila, 5);
          Cientifico cientifico = new Cientifico(ID, nombre, telefono, email, grado_academico, categoria);
          if (columna == 6) {
            new ModalEditarCientifico(null, cientifico, GridCientificos.this).mostrar();
          }
          if (columna == 7) {
            String msg = "Quieres eliminar al cientifico: " + cientifico.nombre + "?";
            int opt = JOptionPane.showConfirmDialog(tablaCientificos, msg);
            if (opt == 0) {
              BaseDeDatos db = new BaseDeDatos();
              db.cientificos.eliminar(cientifico);
              onChange(db.cientificos.obtenerCientificos());
            }
          }
        }
      }
    });

    tablaCientificos.getColumnModel().getColumn(0).setPreferredWidth(10);
    tablaCientificos.getColumnModel().getColumn(1).setPreferredWidth(100);
    tablaCientificos.getColumnModel().getColumn(2).setPreferredWidth(100);
    tablaCientificos.getColumnModel().getColumn(3).setPreferredWidth(100);
    tablaCientificos.getColumnModel().getColumn(4).setPreferredWidth(100);
    tablaCientificos.getColumnModel().getColumn(5).setPreferredWidth(100);
    tablaCientificos.getColumnModel().getColumn(6).setPreferredWidth(10);
    tablaCientificos.getColumnModel().getColumn(7).setPreferredWidth(10);

    tablaCientificos.getTableHeader().setReorderingAllowed(false);
    tablaCientificos.getTableHeader().setResizingAllowed(false);
    tablaCientificos.setShowGrid(false);
    tablaCientificos.setShowHorizontalLines(true);
    tablaCientificos.setShowVerticalLines(true);
    tablaCientificos.setGridColor(Colores.AZUL_OSCURO);
    tablaCientificos.setRowSelectionAllowed(true);
    tablaCientificos.setColumnSelectionAllowed(false);
    tablaCientificos.setSelectionForeground(Colores.BLANCO);
    tablaCientificos.setFillsViewportHeight(true);
    tablaCientificos.setRowHeight(30);
    tablaCientificos.setRowMargin(5);
    tablaCientificos.revalidate();
    tablaCientificos.repaint();

    removeAll();
    revalidate();
    repaint();

    JScrollPane scrollPane = new JScrollPane(tablaCientificos);
    add(scrollPane);
  }
}

class EncabezadoTabla extends DefaultTableCellRenderer {
  public EncabezadoTabla() {
    setOpaque(true);
    setBackground(Colores.AZUL_OSCURO); // Fondo azul claro
    setForeground(Colores.BLANCO); // Letras blancas
    setAlignmentX(CENTER_ALIGNMENT); // Texto centrado
    setHorizontalAlignment(SwingConstants.CENTER); // Texto centrado
  }
}

class CeldaTabla extends DefaultTableCellRenderer {
  public CeldaTabla() {
    setOpaque(true);
    setBackground(java.awt.Color.WHITE); // Fondo blanco
    setHorizontalAlignment(SwingConstants.CENTER); // Texto centrado
    setFocusable(false);
  }
}