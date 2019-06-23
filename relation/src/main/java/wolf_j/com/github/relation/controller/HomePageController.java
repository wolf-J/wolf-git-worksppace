/**
 * 
 */
package wolf_j.com.github.relation.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import wolf_j.com.github.relation.service.bean.UserFromFrontEnd;

/**
 * @author wolf-J
 *
 */

@Controller
public class HomePageController {

	@RequestMapping(value = "/")
	public String signUp(Model model) {
		return "index";
	}

	@RequestMapping(value = "/index")
	@PreAuthorize("hasRole('USER')")
	public String index(Model model) {
		return "index";
	}

	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	String welcome() {
		return "welcome";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	String login(Model model, @ModelAttribute("user") UserFromFrontEnd user) {
		return "login";
	}

}
