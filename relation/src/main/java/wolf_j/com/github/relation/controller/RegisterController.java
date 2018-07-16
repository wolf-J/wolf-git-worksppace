/**
 * 
 */
package wolf_j.com.github.relation.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import wolf_j.com.github.relation.service.RegisterService;
import wolf_j.com.github.relation.service.bean.RegisterMessage;
import wolf_j.com.github.relation.service.bean.UserFromFrontEnd;

/**
 * @author wolf-J
 *
 */

@Controller
public class RegisterController {

	@Autowired
	@Qualifier("registerOrdinaryUserServiceImpl")
	RegisterService registerOrdinaryUserServiceImpl;

	@Autowired
	HttpServletRequest httpServletRequest;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	String getRegisterPage(Model model, @ModelAttribute("userVOFromFrontEnd") UserFromFrontEnd userVOFromFrontEnd) {
		model.addAttribute("registerMessage", new RegisterMessage());
		return "register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	String postRegisterUser(Model model, @ModelAttribute("userVOFromFrontEnd") UserFromFrontEnd userVOFromFrontEnd) {
		RegisterMessage registerMessage = registerOrdinaryUserServiceImpl.signUpUser(userVOFromFrontEnd);
		model.addAttribute("registerMessage", registerMessage);
		return "register";
	}

}
