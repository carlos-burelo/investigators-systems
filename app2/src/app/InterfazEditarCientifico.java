package app;

import javax.swing.BoxLayout;
import javax.swing.JScrollPane;

import app.PanelCientificos.VistaCientificos;
import app.PanelProyectos.VistaProyectos;

public class InterfazEditarCientifico extends Vista {

  public InterfazEditarCientifico(ModeloCientifico cientifico) {
    super("EDITAR");

    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    setAlignmentX(CENTER_ALIGNMENT);

    Contenedor _Formulario = new Contenedor();
    _Formulario.setLayout(new BoxLayout(_Formulario, BoxLayout.Y_AXIS));
    _Formulario.setAlignmentX(CENTER_ALIGNMENT);

    Entrada _CampoNombre = new Entrada("NOMBRE DEL CIENTIFICO");
    _CampoNombre.setValue(cientifico.nombre);
    _Formulario.add(_CampoNombre);

    Entrada _CampoTelefono = new Entrada("TELÉFONO").telefono();
    _CampoTelefono.setValue(cientifico.telefono);
    _Formulario.add(_CampoTelefono);

    Entrada _CampoCorreo = new Entrada("EMAIL").correo();
    _CampoCorreo.setValue(cientifico.correo);
    _Formulario.add(_CampoCorreo);

    Combo<String> _CampoCategoria = new Combo<String>("CATEGORÍA", Campos.CATEGORIAS);
    _CampoCategoria.setValue(cientifico.categoria);
    _Formulario.add(_CampoCategoria);

    Combo<String> _CampoGrado = new Combo<String>("GRADO ACADEMICO", Campos.GRADOS_ACADEMICOS);
    _CampoGrado.setValue(cientifico.grado_academico);
    _Formulario.add(_CampoGrado);

    Contenedor _ContendorBotones = new Contenedor();
    _ContendorBotones.setLayout(new BoxLayout(_ContendorBotones, BoxLayout.X_AXIS));
    _ContendorBotones.setAlignmentX(CENTER_ALIGNMENT);

    Boton _BotonGuardar = new Boton("GUARDAR");
    Boton _BotonCancelar = new Boton("CANCELAR").bg(Colores.ERROR, Colores.ERROR);

    _BotonCancelar.addActionListener(
        e -> {
          VistaCientificos.mostrar(this, new InterfazContendorCientificos());
        });

    _BotonGuardar.addActionListener(
        e -> {
          String nombre = _CampoNombre.getValue();
          String telefono = _CampoTelefono.getValue();
          String correo = _CampoCorreo.getValue();
          String categoria = _CampoCategoria.getValue();
          String grado_academico = _CampoGrado.getValue();

          if (nombre.isEmpty() || telefono.isEmpty() || correo.isEmpty() || categoria.isEmpty()
              || grado_academico.isEmpty()) {
            VistaProyectos.alert("LOS CAMPOS NO PUEDEN ESTAR VACIOS");
            return;
          }

          ModeloCientifico updatedScientific = new ModeloCientifico()
              .$id(cientifico.id)
              .$nombre(nombre)
              .$telefono(telefono)
              .$correo(correo)
              .$categoria(categoria)
              .$grado_academico(grado_academico);

          BaseDeDatos.cientificos.actualizar_cientifico(updatedScientific);
          VistaCientificos.mostrar(this, new InterfazContendorCientificos());
        });

    _ContendorBotones.add(_BotonGuardar);
    _ContendorBotones.add(_BotonCancelar);

    _Formulario.add(_ContendorBotones);
    JScrollPane _ContenedorDeslizante = new JScrollPane(_Formulario);
    _ContenedorDeslizante.getVerticalScrollBar().setUnitIncrement(16);
    add(_ContenedorDeslizante);
  }
}
