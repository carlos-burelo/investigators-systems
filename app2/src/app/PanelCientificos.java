package app;

import java.util.EventListener;
import java.util.EventObject;

import javax.swing.event.EventListenerList;

public class PanelCientificos extends EventObject {

  public interface EscuchadorCientificos extends EventListener {
    void cuandoCambie(PanelCientificos event);
  }

  public Vista newView;

  public PanelCientificos(Object source, Vista newView) {
    super(source);
    this.newView = newView;
  }

  public static class VistaCientificos {
    private static final EventListenerList listeners = new EventListenerList();

    public static void vincular(EscuchadorCientificos listener) {
      listeners.add(EscuchadorCientificos.class, listener);
    }

    public static void mostrar(Object oldView, Vista newView) {
      EscuchadorCientificos[] listeners = VistaCientificos.listeners.getListeners(EscuchadorCientificos.class);
      for (EscuchadorCientificos listener : listeners) {
        listener.cuandoCambie(new PanelCientificos(oldView, newView));
      }
    }
  }
}
