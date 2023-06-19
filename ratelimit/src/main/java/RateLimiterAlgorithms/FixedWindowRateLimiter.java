package org.example;

import RateLimiterAlgorithms.RateLimiter;

import java.util.concurrent.*;

public class FixedWindowRateLimiter implements RateLimiter {
    private final int capacity;
    private final int rate;
    private final BlockingQueue<Long> requestQueue;
    private final ScheduledExecutorService executor;
    private final Object lock;
    private long windowStart;

    public FixedWindowRateLimiter(int capacity, int rate) {
        this.capacity = capacity;
        this.rate = rate;
        this.requestQueue = new LinkedBlockingQueue<>();
        this.executor = Executors.newSingleThreadScheduledExecutor();
        this.lock = new Object();
        this.windowStart = System.currentTimeMillis();
    }

    public void start() {
        executor.scheduleAtFixedRate(this::resetWindow, 1, 1, TimeUnit.SECONDS);
    }

    private void resetWindow() {
        synchronized (lock) {
            long currentTime = System.currentTimeMillis();
            long windowEnd = windowStart + 1000;
            if (currentTime >= windowEnd) {
                requestQueue.clear();
                windowStart = currentTime;
            }
        }
    }

    public boolean grantAccess() {
        synchronized (lock) {
            long currentTime = System.currentTimeMillis();
            long windowEnd = windowStart + 1000;
            if (currentTime >= windowEnd) {
                requestQueue.clear();
                windowStart = currentTime;
            }

            if (requestQueue.size() < capacity) {
                requestQueue.offer(currentTime);
                return true;
            } else {
                return false;
            }
        }
    }
}
