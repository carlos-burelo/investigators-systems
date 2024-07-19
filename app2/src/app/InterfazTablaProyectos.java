package app;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import app.PanelProyectos.VistaProyectos;
import app.Tabla.CeldaTabla;
import app.Tabla.EncabezadoTabla;

public class InterfazTablaProyectos extends Contenedor implements Observador<ModeloProyecto[]> {

  private JTable tabla;

  public InterfazTablaProyectos() {
    setLayout(new GridLayout(1, 1));
    actualizar(BaseDeDatos.proyectos.obtener_todos_los_proyectos());
  }

  @Override
  public void actualizar(ModeloProyecto[] proyectos) {
    DefaultTableModel model = ModeloProyecto.createTableModel(proyectos);
    tabla = new JTable(model) {
      @Override
      public boolean isCellEditable(int row, int column) {
        return false;
      }
    };

    tabla.removeAll();
    tabla.getColumnModel().getColumn(0).setPreferredWidth(10);
    tabla.getColumnModel().getColumn(1).setPreferredWidth(100);
    tabla.getColumnModel().getColumn(2).setPreferredWidth(100);
    tabla.getColumnModel().getColumn(3).setPreferredWidth(60);
    tabla.getColumnModel().getColumn(4).setPreferredWidth(100);
    tabla.getColumnModel().getColumn(5).setPreferredWidth(90);
    tabla.getColumnModel().getColumn(6).setPreferredWidth(10);
    tabla.getColumnModel().getColumn(7).setPreferredWidth(10);

    tabla.removeAll();
    tabla.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
    tabla.setRowHeight(30);
    tabla.setBackground(Colores.BASE);
    tabla.getTableHeader().setDefaultRenderer(new EncabezadoTabla());
    tabla.setDefaultRenderer(Object.class, new CeldaTabla());

    tabla.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent evt) {
        int columna = tabla.getColumnModel().getColumnIndexAtX(evt.getX());
        int fila = evt.getY() / tabla.getRowHeight();
        if (fila >= 0 && columna >= 7 && columna <= 8) {

          int id = (int) model.getValueAt(fila, 0);
          if (columna == 7) {
            ModeloProyecto proyecto = BaseDeDatos.proyectos.obtener_proyecto_por_id(id);
            VistaProyectos.mostrar(this, new InterfazEditarProyecto(proyecto));
          } else if (columna == 8) {
            String msg = "¿Estás seguro de que quieres eliminar este proyecto?";
            int opt = JOptionPane.showConfirmDialog(null, msg, "Eliminar proyecto", JOptionPane.YES_NO_OPTION);
            if (opt == JOptionPane.YES_OPTION) {
              BaseDeDatos.proyectos.deleteProject(id);
              VistaProyectos.mostrar(this, new InterfazContenedorProyectos());
            }
          }
        }
      }
    });

    JScrollPane scrollPane = new JScrollPane(tabla);
    add(scrollPane);
  }
}
