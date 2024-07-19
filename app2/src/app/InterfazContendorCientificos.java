package app;

import app.PanelCientificos.VistaCientificos;

public class InterfazContendorCientificos extends Vista {

  public InterfazContendorCientificos() {
    Cabecera _Cabecera = new Cabecera("TABLA DE CIENTIFICOS", e -> {
      VistaCientificos.mostrar(this, new InterfazNuevoCientifico());
    });
    add(_Cabecera);
    add(new InterfazTablaCientificos());
  }
}
