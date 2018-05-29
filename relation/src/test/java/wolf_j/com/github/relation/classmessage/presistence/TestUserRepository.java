/**
 * 
 */
package wolf_j.com.github.relation.classmessage.presistence;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author wolf-J
 *
 */

@RunWith(SpringRunner.class)
@DataJpaTest
public class TestUserRepository {
	
	@Autowired
	TestEntityManager entityManager;

	@Autowired
	UserRepository userRepositoryTest;

	@Test
	public void testUserRepositoryInsertedUserWhenGivenUser() {
		
		UserEntity user = new UserEntity("wolf-J", new BCryptPasswordEncoder().encode("1234"), "ROLE_user");
		this.entityManager.persist(user);
		
		UserEntity userFromDB = userRepositoryTest.findByUserName("wolf-J");
		
		assertThat(userFromDB).isEqualTo(user);

	}

}
