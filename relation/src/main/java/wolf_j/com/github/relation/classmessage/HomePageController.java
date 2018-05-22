/**
 * 
 */
package wolf_j.com.github.relation.classmessage;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wolf-J
 *
 */

@Controller
@RequestMapping(value = "/")
public class HomePageController {
	
    @RequestMapping(value = "")
    public String signUp(Model model) {
        return "signUp";
    }

}
