/**
 * 
 */
package wolf_j.com.github.relation.presistence;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Iterator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import wolf_j.com.github.relation.presistence.UserEntity;
import wolf_j.com.github.relation.presistence.UserRepository;

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
	
	@Test
	public void testUserRepositoryPagingWhenGivenUser() {
		
		UserEntity user1 = new UserEntity("wolf1", new BCryptPasswordEncoder().encode("1111"), "ROLE_user");
		this.entityManager.persist(user1);
		UserEntity user2 = new UserEntity("wolf2", new BCryptPasswordEncoder().encode("2222"), "ROLE_user");
		this.entityManager.persist(user2);
		UserEntity user3 = new UserEntity("wolf3", new BCryptPasswordEncoder().encode("3333"), "ROLE_user3");
		this.entityManager.persist(user3);
		
		Page<UserEntity> pages = userRepositoryTest.findAll(PageRequest.of(1, 2, Sort.Direction.ASC, "id"));
		Iterator<UserEntity> users = pages.filter((userEntity) -> {return userEntity.getRole() == "ROLE_user3";}).iterator();
		assertThat(users.next()).isEqualTo(user3);
		
		Page<UserEntity> rolePages = userRepositoryTest.findByRole("ROLE_user", PageRequest.of(0, 2, Sort.Direction.DESC, "id"));
		assertThat(rolePages.getNumberOfElements()).isEqualTo(2);
		assertThat(rolePages.getContent().get(0)).isEqualTo(user2);
		
		

	}

}
