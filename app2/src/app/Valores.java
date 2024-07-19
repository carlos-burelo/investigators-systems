package app;

import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class Valores {
  public static int ANCHO_VENTANA = 1300;
  public static int ALTO_VENTANA = 700;
  public static Font FUENTE_BASE = new Font("Comic Sans MS", Font.PLAIN, 15);
  public static Font FUENTE_DE_SUBTITULOS = new Font("Comic Sans MS", Font.BOLD, 15);
  public static Font FUENTE_DE_TITULOS = new Font("Comic Sans MS", Font.BOLD, 24);

  public static Border MARGENES = BorderFactory.createEmptyBorder(10, 10, 10, 10);
  public static Border MARGENES_PEQUEÑOS = BorderFactory.createEmptyBorder(4, 0, 4, 0);
}
