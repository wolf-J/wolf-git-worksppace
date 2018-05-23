/**
 * 
 */
package wolf_j.com.github.relation.classmessage.controller;

import java.util.Date;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import wolf_j.com.github.relation.classmessage.presistence.UserVO;

/**
 * @author wolf-J
 *
 */

@RestController
@RequestMapping(value = "/signUp")
public class SignUpController {
	
    @RequestMapping(value = "")
    public String signUp(Model model) {
        return "signUp";
    }
    
    
    @RequestMapping(value = "check")
    public String checkAndStorege(@ModelAttribute("user") UserVO userVO,  Model model) {
    	Date nowDate = new Date();
    	userVO.setId(1);
    	userVO.setCreatTime(nowDate);
    	model.addAttribute("user", userVO);
        return "signUpSuccess";
    }

}
