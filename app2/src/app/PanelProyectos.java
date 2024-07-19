package app;

import java.util.EventListener;
import java.util.EventObject;

import javax.swing.JOptionPane;
import javax.swing.event.EventListenerList;

public class PanelProyectos extends EventObject {

  public interface EscuchadorProyectos extends EventListener {
    void cuadoCambie(PanelProyectos event);
  }

  public Vista newView;

  public PanelProyectos(Object source, Vista newView) {
    super(source);
    this.newView = newView;
  }

  public static class VistaProyectos {
    private static final EventListenerList listeners = new EventListenerList();

    public static void vincular(EscuchadorProyectos listener) {
      listeners.add(EscuchadorProyectos.class, listener);
    }

    public static void unbind(EscuchadorProyectos listener) {
      listeners.remove(EscuchadorProyectos.class, listener);
    }

    public static void mostrar(Object oldView, Vista newView) {
      EscuchadorProyectos[] listeners = VistaProyectos.listeners.getListeners(EscuchadorProyectos.class);
      for (EscuchadorProyectos listener : listeners) {
        listener.cuadoCambie(new PanelProyectos(oldView, newView));
      }
    }

    public static void alert(String message) {
      JOptionPane.showMessageDialog(null, message);
    }

  }
}
