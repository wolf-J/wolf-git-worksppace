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
 */

@Repository
public interface UserMessageRepository extends JpaRepository<UserMessageEntity, Long> {

    @Query(value = "SELECT user FROM UserMessageEntity user WHERE user.username = :username")
    UserMessageEntity findByUserName(@Param("username") String username);

    @Query(value = "FROM UserMessageEntity user WHERE UPPER(user.username) like '%'||UPPER(:username)||'%'")
    Page<UserMessageEntity> findByLikeUserName(@Param("username") String username, Pageable pageable);

    @Query(value = "FROM UserMessageEntity user WHERE UPPER(user.fullName) = UPPER(:fullName)")
    Page<UserMessageEntity> findByFullName(@Param("fullName") String fullName, Pageable pageable);

    @Query(value = "FROM UserMessageEntity user WHERE UPPER(user.fullName) like '%'||UPPER(:fullName)||'%'")
    Page<UserMessageEntity> findByLikeFullName(@Param("fullName") String fullName, Pageable pageable);

    @Query(value = "FROM UserMessageEntity user WHERE UPPER(user.organization) = UPPER(:organization)")
    Page<UserMessageEntity> findByOrganization(@Param("organization") String organization, Pageable pageable);

    @Query(value = "FROM UserMessageEntity user WHERE UPPER(user.organization) like '%'||UPPER(:organization)||'%'")
    Page<UserMessageEntity> findByLikeOrganization(@Param("organization") String organization,
                                                   Pageable pageable);

    @Query(value = "FROM UserMessageEntity user WHERE UPPER(user.username) like '%'||UPPER(:username)||'%' AND UPPER(user.fullName) like '%'||UPPER(:fullName)||'%' AND user.sex = UPPER(:sex) AND user.birthDay >= :startDay AND user.birthDay <= :endDay AND UPPER(user.organization) like '%'||UPPER(:organization)||'%'")
    Page<UserMessageEntity> fuzzySearchUserMessage(@Param("username") String username,
                                                   @Param("fullName") String fullName, @Param("sex") String sex, @Param("startDay") String startDay,
                                                   @Param("endDay") String endDay, @Param("organization") String organization, Pageable pageable);

}
