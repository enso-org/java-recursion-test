package org.enso;

import java.util.concurrent.Callable;
import java.util.function.Function;
import java.util.function.Supplier;

public class ValueThread {

  private static class ValueHolder<T> {
    private T value;

    public T getValue() {
      return value;
    }

    public void setValue(T value) {
      this.value = value;
    }
  }

  public static <I, O> O execute(Function<I, O> function, I argument) {
    final ValueHolder<O> holder = new ValueHolder<>();
    Thread thread = new Thread(() -> holder.setValue(function.apply(argument)));
    thread.start();
    try {
      thread.join();
    } catch (InterruptedException e) {
      throw new RuntimeException("Something went wrong.");
    }
    return holder.getValue();
  }
}
