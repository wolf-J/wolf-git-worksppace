/**
 * 
 */
package wolf_j.com.github.relation.presistence;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import wolf_j.com.github.relation.presistence.UserMessageEntity;
import wolf_j.com.github.relation.presistence.UserMessageRepository;

/**
 * @author wolf-J
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
	public void testUserMessageRepositoryInsertedUserMessageSuccess() {

		UserMessageEntity userMessageEntity = new UserMessageEntity("一三", "黄", null, null, null, null, null, null);
		userMessageRepositoryTest.save(userMessageEntity);

		UserMessageEntity actualuserMessageEntity = userMessageRepositoryTest.findById(1L).get();
		Page<UserMessageEntity> userMessagePages = userMessageRepositoryTest.findByLikeUserName("一",
				PageRequest.of(0, 10, Sort.Direction.ASC, "id"));

		assertEquals(userMessageEntity, actualuserMessageEntity);
		assertEquals("黄", actualuserMessageEntity.getFullName());
		assertThat(userMessagePages.getContent().get(0)).isEqualTo(userMessageEntity);
	}

}
