package org.enso.tailcall;

import java.util.function.Function;

public class TailCallNext<I,O> implements TailCallStatus<O> {
  Function<I, TailCallStatus<O>> function;
  I argument;

  public TailCallNext(Function<I, TailCallStatus<O>> function, I argument) {
    this.function = function;
    this.argument = argument;
  }

  @Override
  public boolean isDone() {
    return false;
  }

  @Override
  public O getResult() {
    return null;
  }

  @Override
  public TailCallStatus<O> next() {
    return function.apply(argument);
  }
}
