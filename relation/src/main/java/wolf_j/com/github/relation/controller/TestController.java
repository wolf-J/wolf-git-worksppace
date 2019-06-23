/**
 * 
 */
package wolf_j.com.github.relation.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
		UserEntity userEntity = new UserEntity("username", "password", "role");
		List<UserEntity> userEntityList = new ArrayList<>();
		userEntityList.add(userEntity);
		return userEntityList;
	}

	@RequestMapping(value = "/file", method = RequestMethod.POST)
	@ResponseBody
	public String getFile(HttpServletRequest request, MultipartFile file) throws IOException {
		int size = file.getBytes().length;
		String originalFilename = file.getOriginalFilename();
		String name = file.getName();
		return "originalFilename : " + originalFilename + "\nname : " + name + "\nsize : " + size;
	}

}
