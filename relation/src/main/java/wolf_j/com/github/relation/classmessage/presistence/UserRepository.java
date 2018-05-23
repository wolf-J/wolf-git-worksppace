/**
 * 
 */
package wolf_j.com.github.relation.classmessage.presistence;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author wolf-J
 *
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	@Query(value = "SELECT * FROM Users users WHERE users.username = :username", nativeQuery =true)
	public User findByUserName(@Param("username") String username);
	
	
/*	@Modifying
	@Query(value = "insert into Users values (1, 'wolf', '111', 'ROLE_user')", nativeQuery =true)
	public User insertOneUser(@Param("username") String username);
	*/
}
