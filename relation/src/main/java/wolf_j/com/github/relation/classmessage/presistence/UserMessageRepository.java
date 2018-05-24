/**
 * 
 */
package wolf_j.com.github.relation.classmessage.presistence;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author wolf-J
 *
 */

@Repository
public interface UserMessageRepository extends PagingAndSortingRepository<UserMessageEntity, Long> {
}
