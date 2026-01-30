package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    private static int normalInt = 0;
    private static volatile int volatileInt = 0;
    private static AtomicInteger atomicInt = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executor = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 5000; i++) {
            executor.submit(() -> {
                normalInt++;    // Not thread-safe
                volatileInt++;  // Not atomic, just volatile
                atomicInt.incrementAndGet();    // Thread-safe
            });
        }
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);

        // Print the results
        System.out.println("Normal Int: " + normalInt);
        System.out.println("Volatile Int: " + volatileInt);
        System.out.println("Atomic Int: " + atomicInt.get());
    }
}