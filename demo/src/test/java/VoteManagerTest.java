import org.example.VoteManager;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VoteManagerTest {
    @Test
    public void findRankTest(){
        VoteManager vm = new VoteManager();
        List<String> votes = Arrays.asList("Yash,Arjun","Arjun,Yash,Arpit","Yash,Arjun");
//        votes.add(Arrays.asList("Arjun","Yash","Arpit"));
//        votes.add(Arrays.asList(""));
        Assert.assertArrayEquals(Arrays.asList("Yash","Arjun","Arpit").toArray(),
                vm.rankCandidates(votes).toArray());
       // Assert.assertTrue(Arrays.deepEquals());
    }
}
