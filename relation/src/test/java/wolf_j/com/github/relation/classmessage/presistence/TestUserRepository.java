/**
 * 
 */
package wolf_j.com.github.relation.classmessage.presistence;

import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author wolf-J
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserRepository {

	@Autowired
	UserRepository userRepositoryTest;

	@Test
	public void TestuserRepositoryNotNullWhenDBHaveUser() {
		UserEntity user = userRepositoryTest.findByUserName("wolf");

		assertNotNull(user);
	}

	@Test
	public void TestuserRepositorySuccessWhenDBHaveUser() {
		UserEntity user = userRepositoryTest.findByUserName("wolf");
		Optional<UserEntity> user1 = userRepositoryTest.findById(1L);

		assertEquals(true, user1.get().isSameUserProperties(user));
	}

	@Test
	public void TestuserRepositoryInsertedUserWhenGivenUser() {
		UserEntity userFromDB = userRepositoryTest.findByUserName("wolf-J");
		if (userFromDB == null) {
			UserEntity user = new UserEntity("wolf-J", new BCryptPasswordEncoder().encode("1234"), "ROLE_user");
			UserEntity actualUser = userRepositoryTest.save(user);

			assertEquals("ROLE_user", actualUser.getRole());
		}
	}

}
