/**
 * 
 */
package wolf_j.com.github.common;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author wolf-J
 *
 */
public class ValidationHolder {
	

	public static Object getValidation(String BeanId) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:spring/**/*.xml");
		
		System.out.println("getBeans Success!");
		return context.getBean(BeanId);
		
	}



}
