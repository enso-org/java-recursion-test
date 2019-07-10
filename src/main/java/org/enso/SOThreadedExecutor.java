package org.enso;

import java.util.function.Function;

public class SOThreadedExecutor implements RecursiveExecutor {
  @Override
  public <I, O> O execute(Function<I, O> function, I argument) {
    try {
      return function.apply(argument);
    } catch (StackOverflowError e) {
      return ValueThread.execute(function, argument);
    }
  }
}
