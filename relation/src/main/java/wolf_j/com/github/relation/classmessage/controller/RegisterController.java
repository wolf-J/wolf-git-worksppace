/**
 * 
 */
package wolf_j.com.github.relation.classmessage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import wolf_j.com.github.relation.classmessage.domain.UserFromFrontEnd;
import wolf_j.com.github.relation.classmessage.service.RegisterService;

/**
 * @author wolf-J
 *
 */

@Controller
public class RegisterController {

	@Autowired
	@Qualifier("registerOrdinaryUserServiceImpl")
	RegisterService registerOrdinaryUserServiceImpl;

	@RequestMapping(value = "/testReg")
	public String signUp(Model model) {
		return "testReg";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	String getRegisterPage(Model model, @ModelAttribute("userVOFromFrontEnd") UserFromFrontEnd userVOFromFrontEnd) {
		return "register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	String postRegisterUser(Model model, @ModelAttribute("userVOFromFrontEnd") UserFromFrontEnd userVOFromFrontEnd) {

		if (registerOrdinaryUserServiceImpl.signUpUser(userVOFromFrontEnd))
			return "redirect:/register?success";
		return "redirect:/register?error";
	}

}
