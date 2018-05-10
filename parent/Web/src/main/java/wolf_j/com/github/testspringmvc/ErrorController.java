package wolf_j.com.github.testspringmvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/error")
public class ErrorController {
	
	@RequestMapping(value = "/404")
    public String solve404() {
        return "404";
    }
}
