/**
 * 
 */
package wolf_j.com.github.testspringmvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author wolf-J
 *
 */
@Controller
@RequestMapping(value = "/spring")
public class UserController {
	
	
    @RequestMapping(value = "")
    public String create(Model model) {
        return "create";
    }

    @RequestMapping("/save")
    public String save(@ModelAttribute("user") User user) { // user:视图层传给控制层的表单对象；model：控制层返回给视图层的对象
        return "detail";
    }
    
    @ResponseBody
    @RequestMapping("/json")
    public User json() {
    	User user = new User();
    	user.setAge(11);
    	user.setName("asdf");
    	return user;
    }
}