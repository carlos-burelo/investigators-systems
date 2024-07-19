package State;

import java.util.EventListener;
import java.util.EventObject;

import javax.swing.event.EventListenerList;

import Layout.View;

public class ViewEvent extends EventObject {

    public View newView;

    public ViewEvent(Object source, View newView) {
        super(source);
        this.newView = newView;
    }

    public View getNewView() {
        return newView;
    }

    public interface ContentChangeListener extends EventListener {
        void contentChanged(ViewEvent event);
    }

    public static class Window {
        private static Window instance = new Window();
        private EventListenerList listenerList = new EventListenerList();

        private Window() {
        }

        public static Window load() {
            return instance;
        }

        public void addContentChangeListener(ContentChangeListener listener) {
            listenerList.add(ContentChangeListener.class, listener);
        }

        public void removeContentChangeListener(ContentChangeListener listener) {
            listenerList.remove(ContentChangeListener.class, listener);
        }

        public void set(ViewEvent event) {
            ContentChangeListener[] listeners = listenerList.getListeners(ContentChangeListener.class);
            for (ContentChangeListener listener : listeners) {
                listener.contentChanged(event);
            }
        }

        public void set(View old, View view) {
            ViewEvent $view = new ViewEvent(old, view);
            ContentChangeListener[] listeners = listenerList.getListeners(ContentChangeListener.class);
            for (ContentChangeListener listener : listeners) {
                listener.contentChanged($view);
            }
        }
    }

}
