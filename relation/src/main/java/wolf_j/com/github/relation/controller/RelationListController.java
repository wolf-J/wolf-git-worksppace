/**
 * 
 */
package wolf_j.com.github.relation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import wolf_j.com.github.relation.presistence.UserMessageEntity;
import wolf_j.com.github.relation.service.RelationListService;

/**
 * @author wolf-J
 *
 */
@Controller
public class RelationListController {
	
	@Autowired
	@Qualifier("relationListServiceImpl")
	RelationListService relationListService;

	@RequestMapping(value = "/relationlist")
	public String showRelationList(Model model, @RequestParam(name = "PageIndex", required = false) Integer pageIndex) {
		List<UserMessageEntity> closePersons = relationListService.getClosePersons(pageIndex);
		model.addAttribute("closePersons", closePersons);
		return "relation/relationlist";
	}
}
