/**
 * 
 */
package wolf_j.com.github.relation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import wolf_j.com.github.relation.presistence.UserMessageEntity;
import wolf_j.com.github.relation.presistence.UserMessageRepository;
import wolf_j.com.github.relation.service.RelationListService;

/**
 * @author wolf-J
 *
 */

@Service
public class RelationListServiceImpl implements RelationListService {

	@Autowired
	UserMessageRepository userMessageRepository;

	@Override
	public List<UserMessageEntity> getClosePersons(Integer pageIndex) {
		Page<UserMessageEntity> closePersonsPage;
		if (pageIndex == null)
			pageIndex = 0;
		if (pageIndex < 0)
			closePersonsPage = userMessageRepository.findAll(PageRequest.of(pageIndex, 10, Sort.Direction.DESC, "id"));
		closePersonsPage = userMessageRepository.findAll(PageRequest.of(pageIndex, 10, Sort.Direction.ASC, "id"));
		return closePersonsPage.getContent();
	}

}
