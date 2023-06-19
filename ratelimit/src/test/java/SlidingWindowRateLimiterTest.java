import UserBucket.UserBucket;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SlidingWindowRateLimiterTest {
    private UserBucket userBucket;

    @Before
    public void setUp(){
        this.userBucket = new UserBucket();
    }

    @Test
    public void accessRequestTest() throws InterruptedException {
        this.userBucket.addUser(1,3);
        this.userBucket.canAccess(1);
        this.userBucket.canAccess(1);
        this.userBucket.canAccess(1);
        this.userBucket.canAccess(1);
        Thread.sleep(2000);
        this.userBucket.canAccess(1);
        this.userBucket.canAccess(1);
        this.userBucket.canAccess(1);
       // Assert.assertTrue(userBucket.canAccess(1));
    }

}
