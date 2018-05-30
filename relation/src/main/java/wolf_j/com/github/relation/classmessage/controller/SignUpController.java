/**
 * 
 */
package wolf_j.com.github.relation.classmessage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import wolf_j.com.github.relation.classmessage.domain.UserFromFrontEnd;

/**
 * @author wolf-J
 *
 */

@Controller
@RequestMapping(value = "/signUp")
public class SignUpController {
	
    @RequestMapping(value = "")
    public String signUp(Model model) {
        return "signUp";
    }
    
    
    @RequestMapping(value = "/check")
    public String checkAndStorege(@ModelAttribute("user") UserFromFrontEnd userVO,  Model model) {
    	model.addAttribute("user", userVO);
        return "signUpSuccess";
    }

}
