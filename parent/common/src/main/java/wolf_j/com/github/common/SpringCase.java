/**
 * 
 */
package wolf_j.com.github.common;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author wolf-J
 *
 */
public class SpringCase {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:spring/**/*.xml");
		String[] beans = context.getBeanDefinitionNames();
		context.close();
		for (int i = 0; i < beans.length; i++) {
			System.out.println(beans[i]);
		}

		System.out.println("Listen End!");

		Object bean = ValidationHolder.getValidation("validationCase1");
		System.out.println(((ValidationCase)bean).getValue());
		
		bean = ValidationHolder.getValidation("validationCase2");
		System.out.println(((ValidationCase2)bean).getValue());
	}

}
