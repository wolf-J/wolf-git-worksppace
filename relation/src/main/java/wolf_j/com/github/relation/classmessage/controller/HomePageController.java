/**
 * 
 */
package wolf_j.com.github.relation.classmessage.controller;

import java.io.File;
import java.nio.file.Files;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import wolf_j.com.github.relation.classmessage.presistence.UserEntity;
import wolf_j.com.github.relation.classmessage.presistence.UserMessageEntity;
import wolf_j.com.github.relation.classmessage.service.UserVOFromFrontEnd;

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
	public String index(Model model) {
		return "index";
	}

	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	String welcome() {
		return "welcome";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	String login(Model model, @ModelAttribute("user") UserVOFromFrontEnd user) {
		return "login";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	String getRegisterPage(Model model, @ModelAttribute("userVOFromFrontEnd") UserVOFromFrontEnd userVOFromFrontEnd) {
		return "register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	String postRegisterUser(Model model, @ModelAttribute("userVOFromFrontEnd") UserVOFromFrontEnd userVOFromFrontEnd) {
		// register.registOrdinaryUser(user);
		String password = new BCryptPasswordEncoder().encode(userVOFromFrontEnd.getPassword());
		UserEntity user = new UserEntity(userVOFromFrontEnd.getUsername(), password, "ROLE_user");
		UserMessageEntity userMessage = new UserMessageEntity(userVOFromFrontEnd);
		
		return userVOFromFrontEnd.getBirthDay();
	}
}
