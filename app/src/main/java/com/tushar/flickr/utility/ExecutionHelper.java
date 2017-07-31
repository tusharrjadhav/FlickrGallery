package com.tushar.flickr.utility;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * Executes code in main thread.
 */
public class ExecutionHelper {

    private static Handler handler = initializeUIThreadHandler();

    private static final ThreadFactory sThreadFactory = new ThreadFactory() {
        private final AtomicInteger mCount = new AtomicInteger(1);

        @NonNull
        public Thread newThread(@NonNull Runnable r) {
            return new Thread(r, "ExecutorTask #" + mCount.getAndIncrement());
        }
    };

    private static final Executor THREAD_EXECUTOR;

    static {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        int numberOfThreads = availableProcessors >= 2 ? availableProcessors : 2; // minimum 2 worker threads
        THREAD_EXECUTOR = Executors.newFixedThreadPool(numberOfThreads, sThreadFactory);
    }

    public static void runInMainThread(@NonNull Runnable runnable) {
        if (isInMainThread()) {
            runnable.run();
        } else {
            post(runnable);
        }
    }

    public static void post(Runnable runnable) {
        handler.post(runnable);
    }

    public static void postDelayed(Runnable runnable, long delay) {
        handler.postDelayed(runnable, delay);
    }

    public static void post(Runnable runnable, Object token) {
        postDelayed(runnable, token, 0);
    }

    public static void postDelayed(Runnable runnable, Object token, long delay) {
        handler.postAtTime(runnable, token, SystemClock.uptimeMillis() + delay);
    }

    public static void runNonMainThread(@NonNull Runnable runnable) {
        if (isInMainThread()) {
            THREAD_EXECUTOR.execute(runnable);
        } else {
            runnable.run();
        }
    }

    public static void runNonMainThreadSafe(Runnable runnable) {
        new Thread(runnable).start();
    }

    public static boolean isInMainThread() {
        return Thread.currentThread() == Looper.getMainLooper().getThread();
    }

    public static void removeCallbacksAndMessages(Object token) {
        handler.removeCallbacksAndMessages(token);
    }

    public static void removeCallbacks(@Nullable Runnable runnable) {
        handler.removeCallbacks(runnable);
    }

    @NonNull
    private static Handler initializeUIThreadHandler() {
        return new Handler(Looper.getMainLooper());
    }
}
