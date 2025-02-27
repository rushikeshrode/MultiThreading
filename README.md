# MultiThreading
**MultiThreading Playlist**: by Rushikesh Rode

## Chapter One : Create Thread

To create a thread in Java, you can:
1. Either extend a class [`Thread`]
2. Or you can implement an interface [`Runnable`]

### Methods:
- `t1.start();`  
  Starts the thread. (This method is from the `Thread` class.)
- `t1.join();`  
  Waits for the thread to completely finish.
- `Thread.currentThread().getName()`  
  Returns the thread's name (e.g., `main`, `Thread-0`, `Thread-1`).
- `t1.getState()`  
  Tells the state of the thread. (There are 5 states.)
- `Thread.sleep(2000);`  
  Tells a thread to sleep for a specified amount of time (in milliseconds). For example, `2000` milliseconds = 2 seconds.

## Chapter Two : Thread Priority, Interrupt & Yield

### How to name a Thread [ThreadName.class]
### Methods: [ThreadPriority.class]
- `Thread.currentThread().getPriority()`  
  Gets the thread priority. (`10` for high, `5` for medium, `1` for low.)
- `t1.setPriority(MAX_PRIORITY);`  
  Sets the thread priority to the highest. It can also accept an integer (`1`, `5`, or `10`).
- `t1.setPriority(MIN_PRIORITY);`  
  Sets the thread priority to the lowest.
- `t1.interrupt();`  
  Stops the execution of a thread, irrespective of its state.
- `Thread.yield();`  
  Suggests that the current thread can yield(produce) and allow another thread to execute.

### [DaemonThread.class]
If the JVM completes and reaches the end of the main method, the JVM won't wait for this (`t1`) thread to finish execution. It will terminate the thread as soon as all other tasks are complete.

- `t1.setDaemon(true);`  
  Sets the thread as a daemon thread.

## Chapter Three : synchronized

### synchronized keyword / synchronized block
- `synchronized` methods only allow one thread to execute at a time, helping avoid race conditions.

# Chapter Four: Locks

## Problem with synchronized
Synchronized blocks other threads until the current thread completes execution. But if the current thread gets stuck for hours, every other operation will be stuck as well. That's where Locks come in.

### Benefits
- Fairness
- Blocking
- Interruptibly
- Read/Write locking

## Methods: [Main.class]

- `lock.tryLock()`, returns boolean
    - Checks if the thread is already locked by another thread. If `tryLock()` returns `true`, the thread is locked.
    - It will not wait for it to unlock; it will interrupt (end). In `synchronized`, the thread will wait forever for it to unlock.
- `lock.unlock()`
    - Make sure to unlock the locked thread.
- `lock.tryLock(5000, TimeUnit.MILLISECONDS)`
    - Specifies how much time (in millis, secs) it should wait for the locked thread to unlock, else move on.
- `Lock lock = new ReentrantLock(true);`
    - `fair:true`, When fairness is set to true, the thread that runs first gets the resource first (FIFO). Which thread runs first depends on the local system.
- `lock.lockInterruptibly();`
    - If the lock is not available, the current thread becomes disabled for thread execution.

## [DeadlockPrevention.class]
How you can avoid deadlock using a synchronized block.

## [ReadWriteLockThread.class]
A `ReadWriteLock` maintains a pair of associated locks: one for read-only operations and one for writing. A read-write lock allows for greater concurrency in accessing shared data than that permitted by a mutual exclusion lock. It exploits the fact that while only a single thread (a writer thread) can modify the shared data, many threads can concurrently read the data (reader threads).

**Crux:** Multiple reader threads can access read-only methods/variables as long as there is no writer thread operation modifying that thread.

## [ThreadCommunication.class]
### Methods
1. `wait()`
    - A thread pauses execution and releases the lock on an object.
    - It will only resume when another thread calls `notify()` or `notifyAll()` on the same object.
2. `notify()`
    - Wakes up one waiting thread (if any) on the same object.
    - That thread will continue only after it regains the lock.
3. `notifyAll()`
    - Wakes up all waiting threads.
    - Only one thread will proceed first (whichever gets the lock first).

# Chapter Five: Executors Framework

## Utility Methods
- `System.currentTimeMillis();`
    - Returns time passed from 1970 till now (in millis).
- `Executors.newFixedThreadPool(3);`
    - Creates `n` (3 in this case) threads to perform operations.
- `Executors.newSingleThreadExecutor();`
    - Creates one (single) thread to perform operations.
- `executorService.execute(Runnable);`
    - Starts executing the task (defined in `Executor` interface, parent).
- `executorService.submit(Runnable);`
    - Starts executing the task (defined in `ExecutorService` interface, child of `Executor`, returns `Future`, can do things with `Future`).
- `executorService.isShutdown();`
    - Initiates an orderly shutdown where previously submitted tasks are executed, but no new tasks will be accepted. This method does not wait for tasks to complete execution. Use `awaitTermination` for that.
- `executorService.awaitTermination(1, TimeUnit.MINUTES);`
    - Blocks until all tasks have completed execution after a shutdown request, or the timeout occurs, or the current thread is interrupted, whichever happens first.

## `executorService.submit(Runnable);` Overloaded Methods
1. `submit(Runnable)` → returns `void`.
2. `submit(Callable<T>)` → returns `Generic`.
3. `submit(Runnable, success)` → returns `"success"` that we declare in the parameter.

### Runnable vs Callable
- `Runnable` → returns `void`.
- `Callable` → returns `Generic`.
- If you need to store a result, use `Callable`. Otherwise, use `Runnable`.

## Batch Execution Methods
- `executorService.invokeAll(list);`
    - Takes a list as a parameter, executes the given tasks, and blocks other operations (like below code) until these threads complete execution.
- `executorService.invokeAll(list, 2, TimeUnit.SECONDS);`
    - Executes for only the specified amount of time, then terminates automatically (does not wait for all threads to complete execution).
- `executorService.invokeAny(list);`
    - Executes the given tasks, returning the result of one that has completed successfully (any one of the threads).
- `executorService.invokeAny(list, 1, TimeUnit.SECONDS);`

### `Future` Methods
- `future.get();`
    - Waits if necessary for the computation to complete, then retrieves its result.
- `future.isDone();`
    - Returns `true` if this task has completed.
- `future.cancel(true);`
    - Attempts to cancel execution of this task.

## [ScheduledThread.class]
How we can schedule a thread to invoke execution after a fixed time.

### Scheduling Methods
- `ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);`
    - Initializes the scheduler.
- `scheduler.schedule(runnable, initial delay, TimeUnit.SECONDS);`
    - Executes after a specified delay.
- `scheduler.scheduleAtFixedRate(runnable, initial delay, period, TimeUnit.SECONDS);`
    - Executes `runnable` after every specified period (time).
# Chapter Six: CountDownLatch, CyclicBarrier, CompletableFuture, Atomic/Volatile

## [Countdownlatch.class]

```java
CountDownLatch latch = new CountDownLatch(numberOfServices); // numberOfServices - the number of times countDown must be invoked before threads can pass through & start main method
`latch.countDown()  // Make sure to use this .countDown() method where countdown (latch) is going on
latch.await();    // Causes the current thread to wait until the latch has counted down to zero, or unless the thread is interrupted.
latch.await(1, TimeUnit.SECONDS); // It will wait for a maximum of n(1sec) second for the latch count to reach zero. If count doesn't reach zero within param(1sec) time, other execution starts. Latches will still be running and executing even after n(1sec).
```

### Why Use CountDownLatch Instead of Just ExecutorService?
- **Thread Coordination**: CountDownLatch allows threads to wait for other threads to complete before proceeding. Useful for waiting for multiple background tasks to finish before continuing.
- **One-Time Use**: CountDownLatch is a one-time synchronization tool, whereas ExecutorService is for managing thread execution over time.
- **Blocking Mechanism**: With CountDownLatch, a thread can block until other threads finish a specific task, which ExecutorService alone doesn’t provide.

---

## [CyclicBarrierRR.class]

- CyclicBarrier waits for all threads to arrive before starting execution.
- Unlike CountDownLatch, **you can reset the barrier**.
- **It does not block the main method execution**, other things execute simultaneously.
- **When to use CyclicBarrier**: When you want all (or specific) threads to reach a certain point before executing them all together.

---

## [CompletableFutureRR.class]

- These are **daemon threads by default**; they won't wait for execution to complete. As soon as the main method finishes, it exits.
- However, you can catch the returned result of `CompletableFuture` and use it to wait until threads complete execution using:

```java
future.get(); // Throws exception
future.join();  // Does not throw exception
```

### Key Features of CompletableFuture:
- **Non-blocking & Async execution**
- **Chaining multiple tasks** (`thenApply()`, `thenCompose()`)
- **Combining multiple futures** (`thenCombine()`, `allOf()`, `anyOf()`)
- **Exception handling** (`exceptionally()`, `handle()`)

### Methods:
```java
runAsync(() -> { ... }) // Runs a task that does not return a result.
supplyAsync(() -> { return value; }) // Runs a task that returns a result.
```

---

## [VolatileExample.class]

- The `volatile` keyword ensures **visibility** of variable updates across multiple threads.
- It prevents **thread caching issues**, ensuring that changes made to a variable by one thread are immediately visible to others.
- **Thread caching issue**: Each thread has a copy of the class to execute. If a variable is changed at runtime, it might not get updated in other threads’ copies. Using `volatile` ensures all threads see the latest value.

---

## [AtomicExample.class]

- **Atomic variables provide lock-free, thread-safe operations** on single variables, ensuring both **visibility and atomicity** in multithreading.
- **Why not `volatile`?**: `volatile` ensures visibility but **does not guarantee atomicity** for operations like `count++`.
- **Atomic variables ensure both atomicity & visibility** without locks.

### When to Use Atomic Variables?
 When multiple threads update a shared variable (e.g., counters, accumulators).
 When you need **lock-free, high-performance atomic operations**.
 Not suitable for **complex operations involving multiple variables**—use `synchronized` or `ReentrantLock` instead.









