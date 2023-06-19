package org.example;

import RateLimiterAlgorithms.LeakyBucket;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        LeakyBucket algorithm = new LeakyBucket(3,2); // Bucket size: 10, Outgoing rate: 2

        algorithm.start();
        for (int i = 1; i <= 15; i++) {
            boolean allowed = algorithm.grantAccess();
            if (allowed) {
                System.out.println("Request " + i + " allowed");
            } else {
                System.out.println("Request " + i + " denied");
            }
            try {
                Thread.sleep(100); // Sleep for 0.5 seconds between requests
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Thread.sleep(5000);
       // algorithm.stop();
    }
}