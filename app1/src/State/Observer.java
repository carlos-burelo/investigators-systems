package State;

public interface Observer<T> {
  void update(T value);
}