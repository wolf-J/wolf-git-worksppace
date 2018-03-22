/**
 * 
 */
package wolf_J.com.github.testSpringMVC;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonFactory;

import redis.clients.jedis.Response;

/**
 * @author wolf-J
 *
 */
@Controller
@RequestMapping(value = "/spring")
public class UserController {
	
	
    @RequestMapping(value = "")
    public String Create(Model model) {
        return "create";
    }

    @RequestMapping("/save")
    public String Save(@ModelAttribute("user") User user) { // user:视图层传给控制层的表单对象；model：控制层返回给视图层的对象
        //model.addAttribute("user", user);
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