package org.my;

import co.paralleluniverse.fibers.FiberUtil;
import co.paralleluniverse.fibers.SuspendExecution;
import co.paralleluniverse.fibers.Suspendable;
import co.paralleluniverse.strands.Strand;
import co.paralleluniverse.strands.SuspendableRunnable;

public class App implements SuspendableRunnable {

    public static void main(String[] args) throws Exception {
        FiberUtil.runInFiber(new App());
    }

    @Suspendable
    public void run() throws SuspendExecution, InterruptedException {
        System.out.println("Hello Quasar!");
        System.out.println("Inside a Fiber: " + Strand.isCurrentFiber());

        MyService service = new MyService();
        System.out.println(service.suspendableHello("World"));

    }
}
