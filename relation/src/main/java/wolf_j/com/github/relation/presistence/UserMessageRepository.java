/**
 * 
 */
package wolf_j.com.github.relation.presistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author wolf-J
 *
 */

@Repository
public interface UserMessageRepository extends JpaRepository<UserMessageEntity, Long> {

	@Query(value = "SELECT user FROM UserMessageEntity user WHERE user.username = :username")
	public UserMessageEntity findByUserName(@Param("username") String username);

	@Query(value = "FROM UserMessageEntity user WHERE user.username like %:partusername%")
	public Page<UserMessageEntity> findByLikeUserName(@Param("partusername") String partusername, Pageable pageable);

	@Query(value = "FROM UserMessageEntity user WHERE user.fullName = :fullName")
	public Page<UserMessageEntity> findByFullName(@Param("fullName") String fullName, Pageable pageable);

	@Query(value = "FROM UserMessageEntity user WHERE user.fullName like %:fullName%")
	public Page<UserMessageEntity> findByLikeFullName(@Param("fullName") String fullName, Pageable pageable);

	@Query(value = "FROM UserMessageEntity user WHERE user.organization = :organization")
	public Page<UserMessageEntity> findByOrganization(@Param("organization") String organization, Pageable pageable);

	@Query(value = "FROM UserMessageEntity user WHERE user.organization like %:organization%")
	public Page<UserMessageEntity> findByLikeOrganization(@Param("organization") String organization,
			Pageable pageable);

	@Query(value = "FROM UserMessageEntity user WHERE user.username like %:username%  AND user.fullName like %:fullName% AND user.sex = :sex AND user.birthDay >= :startDay AND user.birthDay <= :endDay AND user.organization like %:organization%")
	public Page<UserMessageEntity> fuzzyFindByLikeUserMessage(@Param("username") String username,
			@Param("fullName") String fullName, @Param("sex") String sex, @Param("startDay") String startDay,
			@Param("endDay") String endDay, @Param("organization") String organization, Pageable pageable);

}
