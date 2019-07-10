package org.enso.tailcall;

import java.util.function.Function;

public interface TailCallStatus<O> {
  boolean isDone();
  O getResult();
  TailCallStatus<O> next();
}
