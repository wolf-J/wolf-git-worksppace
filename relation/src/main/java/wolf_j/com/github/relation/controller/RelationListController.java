/**
 * 
 */
package wolf_j.com.github.relation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wolf-J
 *
 */
@Controller
public class RelationListController {
	@RequestMapping(value = "/relationlist")
	public String showRelationlist(Model model) {
		return "relationlist";
	}
}
