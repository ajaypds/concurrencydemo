# ConcurrencyWorld

A Java project demonstrating the differences between thread-safety approaches when working with shared mutable state in concurrent environments.

## Overview

This project illustrates how different synchronization mechanisms handle concurrent access to shared variables:

- **Normal Integer**: A non-thread-safe variable that demonstrates data races
- **Volatile Integer**: A volatile variable that provides visibility guarantees but not atomicity
- **Atomic Integer**: A fully thread-safe atomic variable that handles concurrent access correctly

## Project Structure

```
ConcurrencyWorld/
├── pom.xml                          # Maven configuration
├── README.md                         # Project documentation
└── src/
    ├── main/
    │   ├── java/
    │   │   └── org/example/
    │   │       └── Main.java         # Main demonstration class
    │   └── resources/
    └── test/
        └── java/
```

## How It Works

The program creates a thread pool of 10 threads and submits 5000 tasks that each increment three different types of variables:

1. **`normalInt`**: A regular `int` variable
   - No synchronization
   - Susceptible to data races
   - Result will likely be less than 5000

2. **`volatileInt`**: A `volatile int` variable
   - Ensures visibility of changes across threads
   - Does NOT guarantee atomicity of compound operations
   - Result will likely be less than 5000 (due to non-atomic increment)

3. **`atomicInt`**: An `AtomicInteger` variable
   - Provides both visibility and atomicity
   - Uses atomic operations internally
   - Result will always be exactly 5000

## Prerequisites

- **Java 17+** (configured in pom.xml)
- **Maven 3.6+**

## Building the Project

```bash
mvn clean compile
```

## Running the Project

```bash
mvn exec:java -Dexec.mainClass="org.example.Main"
```

Or compile and run directly:

```bash
mvn clean package
java -cp target/ConcurrencyWorld-1.0-SNAPSHOT.jar org.example.Main
```

## Expected Output

```
Normal Int: 4XXX
Volatile Int: 4XXX
Atomic Int: 5000
```

The exact values for `normalInt` and `volatileInt` may vary with each run due to race conditions, but `atomicInt` will consistently be 5000.

## Learning Objectives

This project helps understand:

- The difference between visibility and atomicity in concurrent programming
- Why `volatile` is not enough for thread-safe mutable operations
- The purpose and usage of `java.util.concurrent.atomic` classes
- How thread pools and the `ExecutorService` work
- Common concurrency pitfalls in Java

## Key Takeaways

| Variable Type | Thread-Safe | Atomic | Visible |
| ------------- | ----------- | ------ | ------- |
| Normal int    | ❌          | ❌     | ❌      |
| volatile int  | ❌          | ❌     | ✅      |
| AtomicInteger | ✅          | ✅     | ✅      |

## Resources

- [Java Concurrency in Practice](https://jcip.net/)
- [AtomicInteger JavaDoc](https://docs.oracle.com/javase/17/docs/api/java.base/java/util/concurrent/atomic/AtomicInteger.html)
- [volatile Keyword Documentation](https://docs.oracle.com/javase/tutorial/essential/concurrency/volatile.html)

## Author

This is a demonstration project for learning Java concurrency concepts.
