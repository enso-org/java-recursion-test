package org.enso;

import org.enso.tailcall.TailCallDone;
import org.enso.tailcall.TailCallNext;
import org.enso.tailcall.TailCallRunner;
import org.enso.tailcall.TailCallStatus;

import java.util.function.BiFunction;
import java.util.function.Function;

//type cpsed i o = (cont: (i -> TCO o)) -> in: i ->

public class CpsTester {

  private static class CPSFun {}

  private final Function<Function<Long, TailCallStatus<Long>>, Function<Long, TailCallStatus<Long>>> fun;

  public Function<Function<Long, TailCallStatus<Long>>, Function<Long, TailCallStatus<Long>>> getFun() {
    return fun;
  }

  public CpsTester() {
    this.fun = cont -> i -> {
      if (i == 0) {
        return  new TailCallNext<>(cont, i); //cont.apply(i);
      } else {
        Function<Long, TailCallStatus<Long>> nextCont = (rest) -> new TailCallNext<>(cont, rest+i);
        return new TailCallNext<>(getFun().apply(nextCont), i-1);
      }
    };
  }

  public Long run(long k) {
    return TailCallRunner.run(fun.apply(TailCallDone::new), k);
  };

}
