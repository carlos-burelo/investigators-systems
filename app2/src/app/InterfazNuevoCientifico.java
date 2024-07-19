package app;

import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import app.PanelCientificos.VistaCientificos;

public class InterfazNuevoCientifico extends Vista {

  public InterfazNuevoCientifico() {
    super("NUEVO CIENTIFICO");

    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    Contenedor _Formulario = new Contenedor();

    _Formulario.setLayout(new GridLayout(1, 1, 10, 10));
    _Formulario.setAlignmentX(CENTER_ALIGNMENT);

    Contenedor $ScientificForm = new Contenedor();
    $ScientificForm.setLayout(new BoxLayout($ScientificForm, BoxLayout.Y_AXIS));

    Entrada $nombreInput = new Entrada("NOMBRE DEL CIENTIFICO");
    $ScientificForm.add($nombreInput);

    Entrada $telefonoInput = new Entrada("TELÉFONO").telefono();
    $ScientificForm.add($telefonoInput);

    Entrada $EmailInput = new Entrada("EMAIL").correo();
    $ScientificForm.add($EmailInput);

    Combo<String> $CategoryInput = new Combo<String>("CATEGORÍA", Campos.CATEGORIAS);
    $ScientificForm.add($CategoryInput);

    Combo<String> $GradeInput = new Combo<String>("GRDADO ACADEMICO", Campos.GRADOS_ACADEMICOS);
    $ScientificForm.add($GradeInput);

    Contenedor $Buttons = new Contenedor();
    $Buttons.setLayout(new BoxLayout($Buttons, BoxLayout.X_AXIS));
    $Buttons.setAlignmentX(CENTER_ALIGNMENT);

    Boton $SubmitButton = new Boton("GUARDAR");
    Boton $CancelButton = new Boton("CANCELAR").bg(Colores.ERROR, Colores.ERROR);

    $CancelButton.addActionListener(
        e -> {
          VistaCientificos.mostrar(this, new InterfazContendorCientificos());
        });

    $SubmitButton.addActionListener(
        e -> {
          String nombre = $nombreInput.getValue();
          String telefono = $telefonoInput.getValue();
          String correo = $EmailInput.getValue();
          String categoria = $CategoryInput.getValue();
          String grado_academico = $GradeInput.getValue();

          if (nombre.isEmpty() || telefono.isEmpty() || correo.isEmpty() || categoria.isEmpty()
              || grado_academico.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, llena todos los campos", "Error",
                JOptionPane.ERROR_MESSAGE);
            return;
          }

          ModeloCientifico scientific = new ModeloCientifico()
              .$nombre(nombre)
              .$telefono(telefono)
              .$correo(correo)
              .$categoria(categoria)
              .$grado_academico(grado_academico);

          BaseDeDatos.cientificos.insertar_cientifico(scientific);
          VistaCientificos.mostrar(this, new InterfazContendorCientificos());
        });

    $Buttons.add(Box.createHorizontalGlue());
    $Buttons.add(Box.createHorizontalGlue());
    $Buttons.add($SubmitButton);
    $Buttons.add(Box.createHorizontalGlue());
    $Buttons.add($CancelButton);
    $Buttons.add(Box.createHorizontalGlue());
    $Buttons.add(Box.createHorizontalGlue());

    $ScientificForm.add($Buttons);
    _Formulario.add($ScientificForm);

    JScrollPane _ContenedorDeslizante = new JScrollPane(_Formulario);
    _ContenedorDeslizante.getVerticalScrollBar().setUnitIncrement(16);
    add(_ContenedorDeslizante);

  }
}
