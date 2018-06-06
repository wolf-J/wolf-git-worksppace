/**
 * 
 */
package wolf_j.com.github.relation.presistence;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author wolf-J
 *
 */

@Repository
public interface UserMessageRepository extends PagingAndSortingRepository<UserMessageEntity, Long> {
	
	@Query(value = "SELECT user FROM UserMessageEntity user WHERE user.username = :username")
	public UserMessageEntity findByUserName(@Param("username") String username);
	
}
