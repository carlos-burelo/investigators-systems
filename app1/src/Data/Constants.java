package Data;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class Constants {
  public static final Dimension MIN_FIELD_SIZEe = null;
  public static int WINDOW_WIDTH = 900;
  public static int WINDOW_HEIGHT = 600;
  public static Font FONT_BASE = new Font("Segoe UI Emoji", Font.PLAIN, 15);
  public static Font FONT_SUBTITLE = new Font("Segoe UI Emoji", Font.BOLD, 15);
  public static Font FONT_TITLE = new Font("Tahoma", Font.BOLD, 24);

  public static Border BORDERS = BorderFactory.createEmptyBorder(10, 10, 10, 10);
  public static Border FIELDS_BORDERS = BorderFactory.createEmptyBorder(5, 5, 5, 5);
  public static Border MIN_BORDERS = BorderFactory.createEmptyBorder(4, 0, 4, 0);

  public static int CARD_WIDTH = 200;
  public static int CARD_HEIGHT = 100;

  public static Dimension CARD_SIZE = new Dimension(CARD_WIDTH, CARD_HEIGHT);
  public static Dimension MIN_FIELD_SIZE = new Dimension(300, 80);
}
