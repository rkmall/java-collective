package f_concurrency.f_thread_management.threadpools;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CustomThreadPool {
    // ThreadPoolExecutor implements ThreadPoolExecutorService
    private ThreadPoolExecutor mThreadPoolExecutor;

    /**
     * ThreadPoolExecutor will create new Threads for incoming Runnables/Callables
     * as long as the ThreadPool size is less than Core pool size.
     * <p>
     * If it reaches Core pool size, then the next Runnables that arrive for the
     * execution will not cause new Threads to be created but will go into Blocking Queue
     * <p>
     * Only after the Task Queue is full and all Core Threads are busy then the
     * ThreadPool can create Threads upto Max ThreadPool size specified
     * <p>
     * This means that ThreadPool with MaxSize 10 and Work Queue size 20 can accommodate 30 Runnables
     * simultaneously.
     * @return the custom config ThreadPollExecutor instance
     */
    public ThreadPoolExecutor getThreadPool() {
        if (mThreadPoolExecutor == null) {
            mThreadPoolExecutor = new ThreadPoolExecutor(
                    3,    // core pool size, these core threads will not be garbage collected
                    10,		          // max no of thread that can be created in this ThreadPool
                    3,                // defines the time that each specific Thread will be kept alive
                    TimeUnit.SECONDS, // in ThreadPool after it is no longer in use
                    new ArrayBlockingQueue<>(20),   // task Queue where Runnable/Callable are kept
                    new ThreadFactory() {
                        @Override
                        public Thread newThread(Runnable r) {
                            System.out.println(
                                    "size: " + mThreadPoolExecutor.getPoolSize() +
                                            " active count: " + mThreadPoolExecutor.getActiveCount() +
                                            " queue remaining: " + mThreadPoolExecutor.getQueue().remainingCapacity()
                            );
                            return new Thread(r);
                        }
                    }
            );
        }
        return mThreadPoolExecutor;
    }
}
