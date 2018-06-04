/**
 * 
 */
package wolf_j.com.github.relation.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wolf-J
 *
 */
public class RelationListController {
	@RequestMapping(value = "/relationlist")
	public String showRelationlist(Model model) {
		return "relationlist";
	}
}
