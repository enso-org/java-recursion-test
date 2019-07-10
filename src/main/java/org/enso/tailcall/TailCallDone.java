package org.enso.tailcall;

import java.util.function.Function;

public class TailCallDone<O> implements TailCallStatus<O> {
  private O result;

  public TailCallDone(O result) {
    this.result = result;
  }

  @Override
  public boolean isDone() {
    return true;
  }

  @Override
  public O getResult() {
    return result;
  }

  @Override
  public TailCallStatus<O> next() {
    return this;
  }
}
