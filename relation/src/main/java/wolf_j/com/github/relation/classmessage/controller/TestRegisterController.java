/**
 * 
 */
package wolf_j.com.github.relation.classmessage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wolf-J
 *
 */

@Controller
@RequestMapping(value = "/testReg")
public class TestRegisterController {
	
    @RequestMapping(value = "")
    public String signUp(Model model) {
        return "testReg";
    }

}
