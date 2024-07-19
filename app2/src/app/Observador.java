package app;

public interface Observador<T> {
  void actualizar(T value);
}