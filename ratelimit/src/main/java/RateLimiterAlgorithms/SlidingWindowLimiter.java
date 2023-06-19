package RateLimiterAlgorithms;

import java.util.Queue;
import java.util.concurrent.*;

public class SlidingWindowLimiter implements RateLimiter{
    int capacity;
    Queue<Long> requestTimestamps;

    public SlidingWindowLimiter(int capacity){
        this.capacity = capacity;
        this.requestTimestamps = new ConcurrentLinkedQueue<>();
    }

    public boolean grantAccess(){
        long currentTime = System.currentTimeMillis();
        checkAndUpdate(currentTime);
        if(requestTimestamps.size()<this.capacity) {
            requestTimestamps.offer(currentTime);
            return true;
        }
        return false;
    }

    private void checkAndUpdate(long currentTime) {
        if(this.requestTimestamps.isEmpty()) {
            return ;
        }
        long timeDiff = (currentTime - this.requestTimestamps.peek())/1000;
        System.out.println(timeDiff);
        while(timeDiff>1) {
            //System.out.println(timeDiff);
            this.requestTimestamps.poll();
            if(this.requestTimestamps.isEmpty()) {
                break;
            }
            timeDiff = (currentTime - this.requestTimestamps.peek())/1000;
        }
    }
}
