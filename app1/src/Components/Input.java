package Components;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import Data.Constants;
import Data.Palette;
import Layout.Div;

public class Input extends Div {

  public Input(String label) {
    super();
    setLayout(new GridLayout(2, 1));
    setMaximumSize(Constants.MIN_FIELD_SIZE);
    setBorder(Constants.FIELDS_BORDERS);
    setAlignmentX(CENTER_ALIGNMENT);

    JTextField $TextField = new JTextField();

    $TextField.setFont(Constants.FONT_BASE);
    $TextField.setForeground(Palette.BLACK);
    $TextField.setBackground(Palette.WHITE);
    $TextField.setMinimumSize(new Dimension(100, 30));
    $TextField.setPreferredSize(new Dimension(100, 30));
    $TextField.setMaximumSize(new Dimension(300, 30));
    $TextField.setAlignmentX(LEFT_ALIGNMENT);

    JLabel $Label = new JLabel(label);
    $Label.setFont(Constants.FONT_SUBTITLE);
    $Label.setAlignmentX(LEFT_ALIGNMENT);
    $Label.setForeground(Palette.BLACK);
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

  public Input number() {
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

  public Input email() {
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

  public Input phone() {
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

  public JPasswordField password() {
    JTextField $TextField = (JTextField) getComponent(1);
    JPasswordField $PasswordField = new JPasswordField();

    $PasswordField.setFont(Constants.FONT_BASE);
    $PasswordField.setForeground(Palette.BLACK);
    $PasswordField.setBackground(Palette.WHITE);
    $PasswordField.setMinimumSize(new Dimension(100, 30));
    $PasswordField.setPreferredSize(new Dimension(100, 30));
    $PasswordField.setMaximumSize(new Dimension(300, 30));
    $PasswordField.setAlignmentX(LEFT_ALIGNMENT);

    $PasswordField.setEchoChar('*');

    $PasswordField.getDocument().addDocumentListener(new DocumentListener() {
      @Override
      public void insertUpdate(DocumentEvent e) {
        $TextField.setText(String.valueOf($PasswordField.getPassword()));
      }

      @Override
      public void removeUpdate(DocumentEvent e) {
        $TextField.setText(String.valueOf($PasswordField.getPassword()));
      }

      @Override
      public void changedUpdate(DocumentEvent e) {
        $TextField.setText(String.valueOf($PasswordField.getPassword()));
      }
    });
    return $PasswordField;
  }

}
