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
public interface UserRepository extends JpaRepository<UserEntity, Long> {

	@Query(value = "SELECT user FROM UserEntity user WHERE user.username = :username")
	public UserEntity findByUserName(@Param("username") String username);
	
	@Query(value = "SELECT user FROM UserEntity user WHERE user.role = :role")
	public Page<UserEntity> findByRole(@Param("role") String role, Pageable pageable);
	
	
/*	@Modifying
	@Transactional
	@Query(value = "insert into Users values (1, 'wolf', '111', 'ROLE_user')", nativeQuery =true)
	public User insertOneUser(@Param("username") String username);*/
	
}
