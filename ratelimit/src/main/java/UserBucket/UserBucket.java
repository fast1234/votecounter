package UserBucket;

import RateLimiterAlgorithms.SlidingWindowLimiter;

import java.util.HashMap;
import java.util.Map;

public class UserBucket {
    Map<Integer, SlidingWindowLimiter> userBucketMap;
    public UserBucket() {
        this.userBucketMap = new HashMap<>();
    }

    public void addUser(int id, int capacity) {
        this.userBucketMap.put(id,new SlidingWindowLimiter(capacity));
    }
    public boolean canAccess(int id) {
        boolean canAccessAPI = this.userBucketMap.get(id).grantAccess();
        if(canAccessAPI) {
            System.out.println("can access");
        }
        else {
            System.out.println("can not access");
        }
        return canAccessAPI;
    }
}
