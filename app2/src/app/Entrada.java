package app;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class Entrada extends Contenedor {

  public Entrada(String label) {
    super();

    setLayout(new GridLayout(2, 1, 0, 0));
    setBorder(Valores.MARGENES);
    setAlignmentX(CENTER_ALIGNMENT);

    JTextField $TextField = new JTextField();

    $TextField.setFont(Valores.FUENTE_BASE);
    $TextField.setForeground(Colores.NEGRO);
    $TextField.setBackground(Colores.BASE);
    $TextField.setMinimumSize(new Dimension(100, 30));
    $TextField.setPreferredSize(new Dimension(100, 30));
    $TextField.setMaximumSize(new Dimension(300, 30));
    $TextField.setAlignmentX(LEFT_ALIGNMENT);

    JLabel $Label = new JLabel(label);
    $Label.setFont(Valores.FUENTE_DE_SUBTITULOS);
    $Label.setAlignmentX(LEFT_ALIGNMENT);
    $Label.setForeground(Colores.NEGRO);
    $Label.setLabelFor($TextField);

    add($Label);
    add($TextField);
  }

  public String getValue() {
    return ((JTextField) getComponent(1)).getText();
  }

  public void setValue(String value) {
    ((JTextField) getComponent(1)).setText(value);
  }

  public Entrada number() {
    JTextField $TextField = (JTextField) getComponent(1);

    ((AbstractDocument) $TextField.getDocument()).setDocumentFilter(new DocumentFilter() {
      @Override
      public void insertString(FilterBypass fb, int offset, String string,
          AttributeSet attr)
          throws BadLocationException {
        if (string.matches("[0-9]*")) {
          super.insertString(fb, offset, string, attr);
        }
      }

      @Override
      public void replace(FilterBypass fb, int offset, int length, String text,
          AttributeSet attrs)
          throws BadLocationException {
        if (text.matches("[0-9]*")) {
          super.replace(fb, offset, length, text, attrs);
        }
      }
    });

    return this;
  }

  public Entrada correo() {
    JTextField $TextField = (JTextField) getComponent(1);

    ((AbstractDocument) $TextField.getDocument()).setDocumentFilter(new DocumentFilter() {
      @Override
      public void insertString(FilterBypass fb, int offset, String string,
          AttributeSet attr)
          throws BadLocationException {
        String newText = fb.getDocument().getText(0, fb.getDocument().getLength()) + string;
        if (newText.matches("[a-zA-Z0-9@.]*") && newText.length() <= 50) {
          super.insertString(fb, offset, string, attr);
          $TextField.setToolTipText(null);
        } else {
          $TextField.setToolTipText("El correo electrónico debe tener menos de 50 caracteres");
        }
      }

      @Override
      public void replace(FilterBypass fb, int offset, int length, String text,
          AttributeSet attrs)
          throws BadLocationException {
        String newText = fb.getDocument().getText(0, fb.getDocument().getLength()) + text;
        if (newText.matches("[a-zA-Z0-9@.]*") && newText.length() - length <= 50) {
          super.replace(fb, offset, length, text, attrs);
          $TextField.setToolTipText(null);
        } else {
          $TextField.setToolTipText("El correo electrónico debe tener menos de 50 caracteres");
        }
      }
    });

    return this;
  }

  public Entrada telefono() {
    JTextField $TextField = (JTextField) getComponent(1);

    ((AbstractDocument) $TextField.getDocument()).setDocumentFilter(new DocumentFilter() {
      @Override
      public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
          throws BadLocationException {
        String newText = fb.getDocument().getText(0, fb.getDocument().getLength()) + string;
        if (newText.matches("[0-9]*") && newText.length() <= 10) {
          super.insertString(fb, offset, string, attr);
          $TextField.setToolTipText(null);
        } else {
          $TextField.setToolTipText("El teléfono debe tener 10 dígitos");
        }
      }

      @Override
      public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
          throws BadLocationException {
        String newText = fb.getDocument().getText(0, fb.getDocument().getLength()) + text;
        if (newText.matches("[0-9]*") && newText.length() - length <= 10) {
          super.replace(fb, offset, length, text, attrs);
          $TextField.setToolTipText(null);
        } else {
          $TextField.setToolTipText("El teléfono debe tener 10 dígitos");
        }
      }
    });

    return this;
  }

}
