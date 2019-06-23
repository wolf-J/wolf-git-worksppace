/**
 * 
 */
package wolf_j.com.github.relation.service;

import java.util.List;

import wolf_j.com.github.relation.presistence.UserMessageEntity;

/**
 * @author wolf-J
 *
 */
public interface RelationListService {

	List<UserMessageEntity> getClosePersons(Integer pageIndex);

}
