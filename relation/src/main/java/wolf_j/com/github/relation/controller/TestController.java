/**
 * 
 */
package wolf_j.com.github.relation.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import wolf_j.com.github.relation.presistence.UserEntity;

/**
 * @author ASNPHM6
 *
 */
@Controller
@RequestMapping("/test")
public class TestController {

	@RequestMapping(value = "/testReg")
	public String signUp(Model model) {
		return "testReg";
	}

	@RequestMapping(value = "/ajax")
	@ResponseBody
	public List<UserEntity> testAjax(Model model) {
		UserEntity userEntity = new UserEntity("a", "b", "c");
		List<UserEntity> userEntityList = new ArrayList<>();
		userEntityList.add(userEntity);
		return userEntityList;
	}

}
