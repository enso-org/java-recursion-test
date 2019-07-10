package org.enso.tailcall;

import java.util.function.Function;

public class TailCallRunner {
  public static <I, O> O run(Function<I, TailCallStatus<O>> function, I argument) {
    TailCallStatus<O> result = function.apply(argument);
    while (!result.isDone())
      result = result.next();
    return result.getResult();
  }
}
