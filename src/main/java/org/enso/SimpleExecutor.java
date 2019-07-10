package org.enso;

import java.util.function.Function;

public class SimpleExecutor implements RecursiveExecutor {
  @Override
  public <I, O> O execute(Function<I, O> function, I argument) {
    return function.apply(argument);
  }
}
