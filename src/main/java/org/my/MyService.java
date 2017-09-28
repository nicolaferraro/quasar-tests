package org.my;

import co.paralleluniverse.fibers.Fiber;
import co.paralleluniverse.fibers.SuspendExecution;
import co.paralleluniverse.fibers.Suspendable;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class MyService {

    private Queue<Fiber> parked = new LinkedBlockingQueue<>();

    public MyService() {
        new Fiber<>(() -> {

            while (true) {
                System.out.println("--- trying to unpark");
                if (!parked.isEmpty()) {
                    Fiber f = parked.poll();
                    System.out.println("--- about to unpark");
                    f.unpark();
                    System.out.println("--- unparked");
                }
                Fiber.sleep(300);
            }

        }).start();
    }


    @Suspendable
    public String suspendableHello(String name) throws SuspendExecution, InterruptedException {

        parked.add(Fiber.currentFiber());
        System.out.println("--- parking");
        Fiber.park();
        System.out.println("--- resume");

        return "Hello " + new Capitalizer().capitalize(name);
    }

}
