/**
 * 
 */
package wolf_j.com.github.relation.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import wolf_j.com.github.relation.config.security.UserService;
import wolf_j.com.github.relation.service.RegisterService;

import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author wolf-J
 *
 */

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = { HomePageController.class, RegisterController.class })
public class TestSpringSecurity {

	@Autowired
	MockMvc mvc;

	@MockBean(name = "registerOrdinaryUserServiceImpl")
	RegisterService registerOrdinaryUserServiceImpl;

	@MockBean
	UserService userService;

	@Test
	@WithMockUser(username = "wolf-j")
	public void testHomePageControllerReturnHomePageWhenGetEmpty() throws Exception {

		this.mvc.perform(get("/index").accept(MediaType.TEXT_HTML)).andExpect(status().isOk())
				.andExpect(content().contentType("text/html;charset=UTF-8"))
				.andExpect(authenticated().withUsername("wolf-j"));

	}

}
