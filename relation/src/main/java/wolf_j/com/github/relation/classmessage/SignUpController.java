/**
 * 
 */
package wolf_j.com.github.relation.classmessage;

import java.util.Date;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public String checkAndStorege(@ModelAttribute("classMember") ClassMember classMember,  Model model) {
    	Date nowDate = new Date();
    	classMember.setId(1);
    	classMember.setCreatTime(nowDate);
    	model.addAttribute("classMember", classMember);
        return "signUpSuccess";
    }

}
