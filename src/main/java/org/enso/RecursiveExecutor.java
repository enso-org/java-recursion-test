package org.enso;

import java.util.function.Function;

public interface RecursiveExecutor {
  public <I,O> O execute(Function<I, O> function, I argument);
}
