/**
 * 
 */
package wolf_j.com.github.web.classMessage;

import java.util.Date;

import org.apache.tomcat.jni.Time;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

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
    
    
    @RequestMapping(value = "check")
    public String checkAndStorege(@ModelAttribute("classMember") ClassMember classMember,  Model model) {
    	Date nowDate = new Date();
    	classMember.setId(1);
    	classMember.setCreatTime(nowDate);
    	model.addAttribute("classMember", classMember);
        return "signUpSuccess";
    }

}
