package app;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class Tabla {
  public static class EncabezadoTabla extends DefaultTableCellRenderer {
    public EncabezadoTabla() {
      setOpaque(true);
      setBackground(Colores.PRINCIPAL); // Fondo azul claro
      setForeground(Colores.BASE); // Letras blancas
      setAlignmentX(CENTER_ALIGNMENT); // Texto centrado
      setHorizontalAlignment(SwingConstants.CENTER); // Texto centrado
    }
  }

  public static class CeldaTabla extends DefaultTableCellRenderer {
    public CeldaTabla() {
      setOpaque(true);
      setBackground(java.awt.Color.WHITE); // Fondo blanco
      setHorizontalAlignment(SwingConstants.CENTER); // Texto centrado
      setFocusable(false);
    }
  }
}
