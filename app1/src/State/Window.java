package State;

import java.util.EventListener;
import java.util.EventObject;

import javax.swing.JOptionPane;
import javax.swing.event.EventListenerList;

import Layout.View;

public class Window extends EventObject {

  public interface WindowListener extends EventListener {
    void onChange(Window event);
  }

  public View newView;

  public Window(Object source, View newView) {
    super(source);
    this.newView = newView;
  }

  public static class W {
    private static final EventListenerList listeners = new EventListenerList();

    public static void bind(WindowListener listener) {
      listeners.add(WindowListener.class, listener);
    }

    public static void unbind(WindowListener listener) {
      listeners.remove(WindowListener.class, listener);
    }

    public static void navigate(Object oldView, View newView) {
      WindowListener[] listeners = W.listeners.getListeners(WindowListener.class);
      for (WindowListener listener : listeners) {
        listener.onChange(new Window(oldView, newView));
      }
    }

    public static void alert(String message) {
      JOptionPane.showMessageDialog(null, message);
    }

  }
}
