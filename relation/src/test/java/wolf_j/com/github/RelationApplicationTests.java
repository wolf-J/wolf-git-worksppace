package wolf_j.com.github;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import wolf_j.com.github.relation.RelationApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RelationApplication.class})
public class RelationApplicationTests {

    @Test
    public void contextLoads() {
        Assert.assertTrue(true);
    }

}
