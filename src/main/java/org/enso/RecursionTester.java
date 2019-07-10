package org.enso;

import java.util.function.Function;

public class RecursionTester {
  private final RecursiveExecutor executor;
  private final Function<Long, Long> function;

  public Function<Long, Long> getFunction() {
    return function;
  }

  private Long runRecursive(Long in) { return executor.execute(function, in); }

  public RecursionTester(RecursiveExecutor executor) {
    this.executor = executor;
    function = (i) -> {
      if (i == 0) {
        return 0L;
      } else {
        return i + runRecursive(i-1);
      }
    };
  }

  public long testSum(long input) {
    return runRecursive(input);
  }
}
