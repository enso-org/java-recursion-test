/*
 * Copyright (c) 2014, Oracle America, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 *  * Neither the name of Oracle nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.enso;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.RunnerException;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Fork(1)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class Main {

  public static void main(String[] args) throws IOException, RunnerException {
    org.openjdk.jmh.Main.main(args);
  }

  @State(Scope.Benchmark)
  public static class InterruptionsPlan {
    @Param({"10", "100", "1000"}) public int interruptions;
  }

  @State(Scope.Benchmark)
  public static class InputSizePlan {
    @Param({"100", "1000", "10000", "50000", "100000", "1000000"}) public int inputSize;
  }

//  @Benchmark
//  public void testCountedExecutor(BenchPlan plan) {
//    new RecursionTester(new CountedThreadedExecutor(plan.interruptions)).testSum(10000);
//  }

  @Benchmark
  public void testCPS(InputSizePlan plan) {
    new CpsTester().run(plan.inputSize);
  }

//  @Benchmark
//  public void testSOExecutor(InputSizePlan plan) {
//    new RecursionTester(new SOThreadedExecutor()).testSum(plan.inputSize);
//  }
//
//  @Benchmark
//  public void testEmptyThread() {
//    Thread thread = new Thread(() -> {});
//    thread.start();
//    try {
//      thread.join();
//    } catch (InterruptedException e) {
//      e.printStackTrace();
//    }
//  }

}
