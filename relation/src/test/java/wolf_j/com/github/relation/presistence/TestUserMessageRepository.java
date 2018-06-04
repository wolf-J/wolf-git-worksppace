/**
 * 
 */
package wolf_j.com.github.relation.presistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import wolf_j.com.github.relation.presistence.UserMessageEntity;
import wolf_j.com.github.relation.presistence.UserMessageRepository;

/**
 * @author ASNPHM6
 *
 */

@RunWith(SpringRunner.class)
@DataJpaTest
public class TestUserMessageRepository {
	
	@Autowired
	TestEntityManager entityManager;

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
