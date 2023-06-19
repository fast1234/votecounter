package RateLimiterAlgorithms;

import RateLimiterAlgorithms.RateLimiter;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class LeakyBucket implements RateLimiter {
    private final int bucketSize;
    private final int outgoingRate;
    private final AtomicInteger currentSize;
    private final BlockingQueue<Integer> requestBucket;
    private final Semaphore semaphore;
    private final ScheduledExecutorService executor;

    private ExecutorService executors;

    public LeakyBucket(int bucketSize, int outgoingRate) {
        this.bucketSize = bucketSize;
        this.outgoingRate = outgoingRate;
        this.currentSize = new AtomicInteger(0);
        this.requestBucket = new LinkedBlockingQueue<>();
        this.semaphore = new Semaphore(outgoingRate);
        this.executor = Executors.newScheduledThreadPool(1);
        this.executors = Executors.newFixedThreadPool(1);
    }

    public void start() {
        executors.execute(()->{
            while(true){
                leak();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void leak() {
        int packetsToLeak = Math.min(outgoingRate, currentSize.get());
        try {
            semaphore.acquire(packetsToLeak);
            for (int i = 0; i < packetsToLeak; i++) {
                requestBucket.poll();
                currentSize.decrementAndGet();
            }
            semaphore.release(packetsToLeak);
            System.out.println("Bucket leaked. Current size: " + currentSize.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean grantAccess() {
        if (currentSize.get() < bucketSize) {
            requestBucket.offer(1);
            currentSize.incrementAndGet();
            System.out.println("Packet added to the bucket. Current size: " + currentSize.get());
            return true;
        } else {
            System.out.println("Bucket overflow! Packet dropped.");
            return false;
        }
    }
}
