/**
 * 
 */
package wolf_j.com.github.relation.classmessage.presistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author ASNPHM6
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserMessageRepository {
	
	@Autowired
	UserMessageRepository userMessageRepositoryTest;
	
	@Test
	public void testUserMessageRepositoryInsertedUserNessageSucess() {
		
		UserMessageEntity userMessageEntity = new UserMessageEntity("wolf", null, null, null, null, null, null, null);
		userMessageRepositoryTest.save(userMessageEntity);
		
		UserMessageEntity actualuserMessageEntity = userMessageRepositoryTest.findById(1L).get();
		
		assertEquals(userMessageEntity, actualuserMessageEntity);
	}

}
