/**
 * 
 */
package wolf_j.com.github.relation.classmessage.presistence;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author wolf-J
 *
 */
@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {

	@Query(value = "SELECT user FROM UserEntity user WHERE user.username = :username")
	public UserEntity findByUserName(@Param("username") String username);
	
	
/*	@Modifying
	@Query(value = "insert into Users values (1, 'wolf', '111', 'ROLE_user')", nativeQuery =true)
	public User insertOneUser(@Param("username") String username);
	*/
}
