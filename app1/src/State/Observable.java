package State;

import java.util.ArrayList;
import java.util.List;

public class Observable<T> {
  private T data;

  public Observable(T payload) {
    data = payload;
  }

  private List<Observer<T>> observers = new ArrayList<>();

  public T get() {
    return data;
  }

  public void update() {
    notifyObservers();
  }

  public void attach(Observer<T> observer) {
    observers.add(observer);
  }

  public void detach(Observer<T> observer) {
    observers.remove(observer);
  }

  private void notifyObservers() {
    for (Observer<T> observer : observers) {
      observer.update(data);
    }
  }
}