package org.enso;

import java.util.function.Function;

public class CountedThreadedExecutor implements RecursiveExecutor {
  private final int depthLimit;
  private volatile int depth = 0;

  public CountedThreadedExecutor(int depthLimit) {
    this.depthLimit = depthLimit;
  }

  @Override
  public <I, O> O execute(Function<I, O> function, I argument) {
    try {
      if (depth < depthLimit) {
        depth += 1;
        return function.apply(argument);
      } else {
        int oldDepth = depth;
        depth = 0;
        O result = ValueThread.execute(function, argument);
        depth = oldDepth;
        return result;
      }
    } catch (NullPointerException e) {
      e.printStackTrace();
      throw new RuntimeException("LOL");
    }
  }
}
