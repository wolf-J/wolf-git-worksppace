/**
 * 
 */
package wolf_j.com.github.relation.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.BDDMockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import wolf_j.com.github.relation.controller.HomePageController;
import wolf_j.com.github.relation.controller.RegisterController;
import wolf_j.com.github.relation.service.RegisterService;
import wolf_j.com.github.relation.service.bean.RegisterMessage;
import wolf_j.com.github.relation.service.bean.UserFromFrontEnd;

/**
 * @author wolf-J
 *
 */

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = { HomePageController.class, RegisterController.class }, secure = false)
public class TestHomePageController {

	@Autowired
	MockMvc mvc;

	@MockBean(name = "registerOrdinaryUserServiceImpl")
	RegisterService registerOrdinaryUserServiceImpl;

	@Test
	public void testHomePageControllerReturnHomePageWhenGetEmpty() throws Exception {

		this.mvc.perform(get("").accept(MediaType.TEXT_HTML)).andExpect(status().isOk())
				.andExpect(content().contentType("text/html;charset=UTF-8"));
	}

	@Test
	public void testRegisterControllerReturnSuccessWhenCallRegister() throws Exception {
		UserFromFrontEnd userVOFromFrontEnd = null;
		given(this.registerOrdinaryUserServiceImpl.signUpUser(userVOFromFrontEnd)).willReturn(new RegisterMessage());

		this.mvc.perform(get("/register").accept(MediaType.TEXT_HTML)).andExpect(status().isOk())
				.andExpect(content().encoding("UTF-8"));
	}

}
